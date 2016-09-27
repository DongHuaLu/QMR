package com.game.dataserver.manager;

import org.apache.log4j.Logger;

import com.game.bag.structs.Bag;
import com.game.dataserver.manager.structs.SyncPlayerCrossError;
import com.game.dataserver.manager.structs.SyncPlayerError;
import com.game.dataserver.manager.structs.UserState;
import com.game.dataserver.message.ReqKickPlayerToCrossServerMessage;
import com.game.dataserver.message.ReqPlayerCrossFromDataServerMessage;
import com.game.dataserver.message.ReqPlayerQuitToDataServerMessage;
import com.game.dataserver.message.ReqPlayerStateFromDataServerMessage;
import com.game.dataserver.message.ReqSyncPlayerCrossToDataServerMessage;
import com.game.dataserver.message.ReqSyncPlayerInfoFromDataServerMessage;
import com.game.dataserver.message.ReqSyncPlayerToDataServerMessage;
import com.game.dataserver.message.ResChangeServerMessage;
import com.game.dataserver.message.ResPlayerCrossFromDataServerErrorMessage;
import com.game.dataserver.message.ResPlayerCrossFromDataServerMessage;
import com.game.dataserver.message.ResPlayerQuitToDataServerMessage;
import com.game.dataserver.message.ResPlayerStateFromDataServerMessage;
import com.game.dataserver.message.ResSyncPlayerCrossToDataServerMessage;
import com.game.dataserver.message.ResSyncPlayerInfoFromDataServerErrorMessage;
import com.game.dataserver.message.ResSyncPlayerToDataServerMessage;
import com.game.json.JSONserializable;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.server.impl.WServer;
import com.game.utils.MessageUtil;
import com.game.utils.VersionUpdateUtil;

/**
 * 跨服管理
 * @author heyang
 * 登陆时发现当前账号有角色在跨服中时，将此角色从跨服服务器踢出
 */
public class DataServerManager {
	
	protected Logger log = Logger.getLogger(DataServerManager.class);
	
	private static Object obj = new Object();
	//实例
	private static DataServerManager manager;

	private DataServerManager() {
	}

	public static DataServerManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new DataServerManager();
			}
		}
		return manager;
	}
	
	/**
	 * 同步玩家全部信息到公共服务器
	 * @param player
	 * @return 是否同步成功
	 */
	public boolean reqSyncPlayerToDataServer(Player player){
		//是否已经连接公共数据服务器
		if(WServer.getInstance().isConnectPublic()){
			ReqSyncPlayerToDataServerMessage msg = new ReqSyncPlayerToDataServerMessage();
			msg.setPlayerId(player.getId());
			msg.setUserId(player.getUserId());
			msg.setUserName(player.getUserName());
			msg.setServerId(WServer.getInstance().getServerId());
			msg.setWeb(WServer.getInstance().getServerWeb());
			msg.setData(VersionUpdateUtil.dateSave(JSONserializable.toString(player)));
			WServer.getInstance().getPublicSession().write(msg);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 同步玩家全部信息到公共服务器返回结果
	 * @param msg
	 */
	public void resSyncPlayerToDataServer(ResSyncPlayerToDataServerMessage msg){
		//同步成功
		if(msg.getResult()==UserState.WAIT_CROSS){
			Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
			if(player==null){
				return;
			}
			ResChangeServerMessage returnmsg = new ResChangeServerMessage();
			returnmsg.setUserId(msg.getUserId());
			returnmsg.setDataServerPlayerId(msg.getDataServerPlayerId());
			MessageUtil.tell_player_message(player, returnmsg);
		}
		//TODO 同步失败
		else{
			//提示稍后再试
		}
	}
	
	/**
	 * 请求公共服务器玩家全部信息
	 * @param userId
	 * @param userweb
	 * @param dataServerPlayerId
	 * @param gateId
	 */
	public void reqSyncPlayerInfoFromDataServer(String userId, String userweb, long dataServerPlayerId, int gateId){
		int serverId = WServer.getInstance().getServerId();
		String web = WServer.getInstance().getServerWeb();
		
		ReqSyncPlayerInfoFromDataServerMessage reqmsg = new ReqSyncPlayerInfoFromDataServerMessage();
		reqmsg.setServerId(serverId);
		reqmsg.setServerWeb(web);
		reqmsg.setUserId(userId);
		reqmsg.setWeb(userweb);
		reqmsg.setDataServerPlayerId(dataServerPlayerId);
		reqmsg.setGateId(gateId);
		
		if(WServer.getInstance().isConnectPublic()){
			WServer.getInstance().getPublicSession().write(reqmsg);
		}else{
			//TODO 返回错误
		}
	}
	
	/**
	 * 请求公共服务器玩家全部信息返回错误
	 * @param msg
	 */
	public void resSyncPlayerInfoFromDataServerError(ResSyncPlayerInfoFromDataServerErrorMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getDataServerPlayerId());
		if(player==null){
			return;
		}
		//TODO 提示跨服失败
		switch (msg.getError()) {
			case SyncPlayerError.PLAYER_NOT_EXITS:
			case SyncPlayerError.USER_NOT_EXITS:
			case SyncPlayerError.ALREADY_CROSS_SERVER:
			case SyncPlayerError.CROSS_SERVER_PLAYER_ERROR:
			case SyncPlayerError.NOT_READY_FOR_CROSS:
			break;
		}
		
		//TODO 返回原服务器
	}
	
	/**
	 * 请求人物跨服状态
	 * @param player
	 */
	public void reqPlayerStateFromDataServer(Player player){
		//是否已经连接公共数据服务器
		if(WServer.getInstance().isConnectPublic()){
			ReqPlayerStateFromDataServerMessage msg = new ReqPlayerStateFromDataServerMessage();
			msg.setPlayerId(player.getId());
			msg.setUserId(player.getUserId());
			msg.setWeb(WServer.getInstance().getServerWeb());
			WServer.getInstance().getPublicSession().write(msg);
		}
	}
	
	/**
	 * 请求人物跨服状态返回
	 * @param msg
	 */
	public void resPlayerStateFromDataServer(ResPlayerStateFromDataServerMessage msg){
		//不做处理
	}
	
	/**
	 * 退出时请求公共服务器玩家结束跨服
	 * @param player
	 * @return
	 */
	public void reqPlayerQuitToDataServer(Player player){
		if(WServer.getInstance().isConnectPublic()){
			ReqPlayerQuitToDataServerMessage msg = new ReqPlayerQuitToDataServerMessage();
			msg.setServerId(WServer.getInstance().getServerId());
			msg.setDataServerPlayerId(player.getId());
			msg.setBag(VersionUpdateUtil.dateSave(JSONserializable.toString(player.getGlobalBag())));
			
			WServer.getInstance().getPublicSession().write(msg);
		}
	}
	
	/**
	 * 退出时请求公共服务器玩家结束跨服返回结果
	 * @param player
	 * @return
	 */
	public void resPlayerQuitToDataServer(ResPlayerQuitToDataServerMessage msg){
		//不做处理
	}
	
	/**
	 * 同步公共服务器玩家跨服部分信息
	 * @param player
	 * @return
	 */
	public boolean reqSyncPlayerCrossToDataServer(Player player){
		if(WServer.getInstance().isConnectPublic()){
			ReqSyncPlayerCrossToDataServerMessage msg = new ReqSyncPlayerCrossToDataServerMessage();
			msg.setServerId(WServer.getInstance().getServerId());
			msg.setDataServerPlayerId(player.getId());
			msg.setBag(VersionUpdateUtil.dateSave(JSONserializable.toString(player.getGlobalBag())));
			
			WServer.getInstance().getPublicSession().write(msg);
			return true;
		}else{
			//TODO 提示与公共服务器断开
			//TODO 退出跨服服务器
		}
		
		return false;
	}
	
	/**
	 * 同步公共服务器玩家跨服部分信息返回结果
	 * @param msg
	 */
	public void resSyncPlayerCrossToDataServer(ResSyncPlayerCrossToDataServerMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getDataServerPlayerId());
		if(player==null){
			return;
		}
		switch (msg.getState()) {
			case SyncPlayerCrossError.PLAYER_NOT_EXITS:
			case SyncPlayerCrossError.USER_NOT_EXITS:
			case SyncPlayerCrossError.NOT_CROSS_SERVER:
			case SyncPlayerCrossError.CROSS_SERVER_PLAYER_ERROR:
			case SyncPlayerCrossError.ALREADY_QUIT_CROSS:
			//TODO 退出跨服服务器
				
			break;
		}
		//TODO 返回原服务器
	}
	
	/**
	 * 请求玩家跨服数据
	 * @param player
	 */
	public void reqPlayerCrossFromDataServer(Player player){
		//是否已经连接公共数据故武器
		if(WServer.getInstance().isConnectPublic()){
			ReqPlayerCrossFromDataServerMessage msg = new ReqPlayerCrossFromDataServerMessage();
			msg.setPlayerId(player.getId());
			msg.setUserId(player.getUserId());
			msg.setWeb(WServer.getInstance().getServerWeb());
			WServer.getInstance().getPublicSession().write(msg);
		}
	}
	
	/**
	 * 请求玩家跨服数据返回成功
	 * @param msg
	 */
	public void resPlayerCrossFromDataServer(ResPlayerCrossFromDataServerMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
		if(player==null){
			return;
		}
		
		try{
			player.setGlobalBag((Bag)JSONserializable.toObject(VersionUpdateUtil.dateLoad(msg.getBag()), Bag.class));
			player.getGlobalBag().setLocked(false);
		}catch (Exception e) {
			log.error(e, e);
		}
		
		if(!player.getGlobalBag().isLocked()){
			//TODO 通知前端显示
		}else{
			//TODO 提示前端跨服数据正在同步，稍后再试
		}
	}
	
	/**
	 * 请求玩家跨服数据返回错误
	 * @param msg
	 */
	public void resPlayerCrossFromDataServerError(ResPlayerCrossFromDataServerErrorMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
		if(player==null){
			return;
		}
		switch (msg.getState()) {
			case SyncPlayerCrossError.PLAYER_NOT_EXITS:
			case SyncPlayerCrossError.USER_NOT_EXITS:
			case SyncPlayerCrossError.ALREADY_CROSS_SERVER:
			//不做处理
			break;
		}
		//TODO 提示前端跨服数据正在同步，稍后再试
	}
	
	/**
	 * 请求踢出玩家
	 * @param msg
	 */
	public void reqKickPlayerToCrossServer(ReqKickPlayerToCrossServerMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getDataServerPlayerId());
		if(player!=null){
			ManagerPool.playerManager.quit(player);
		}else{
			ReqPlayerQuitToDataServerMessage reqmsg = new ReqPlayerQuitToDataServerMessage();
			reqmsg.setServerId(WServer.getInstance().getServerId());
			reqmsg.setDataServerPlayerId(msg.getDataServerPlayerId());
			WServer.getInstance().getPublicSession().write(msg);
		}
	}
}
