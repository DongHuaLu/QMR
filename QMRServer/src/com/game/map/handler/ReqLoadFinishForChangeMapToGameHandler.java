package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.data.bean.Q_mapBean;
import com.game.login.handler.ReqLoadFinishHandler;
import com.game.login.message.ReqLoadFinishMessage;
import com.game.login.message.ResLoginSuccessToGateMessage;
import com.game.login.message.ResLoginSuccessToWorldMessage;
import com.game.manager.ManagerPool;
import com.game.map.message.ReqEnterMapMessage;
import com.game.map.message.ReqLoadFinishForChangeMapToGameMessage;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.server.impl.MServer;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;

public class ReqLoadFinishForChangeMapToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoadFinishForChangeMapToGameHandler.class);

	public void action(){
		try{
			long start = System.currentTimeMillis();
			
			ReqLoadFinishForChangeMapToGameMessage msg = (ReqLoadFinishForChangeMapToGameMessage)this.getMessage();
			
			boolean reload = false;
			//判断人物是否存在
			Player player = ManagerPool.playerManager.getPlayer(msg.getRoleId().get(0));
			if(player==null){
				//加载人物
				player = ManagerPool.playerManager.loadPlayer(msg.getRoleId().get(0));
				reload = true;
				if(player==null){
					log.error("loadfinishfromchangemap player " + msg.getRoleId().get(0) + " is null!");
					//告诉网关退出,数据库错误
//					ResCreateCharacterFailedMessage failedMessage = new ResCreateCharacterFailedMessage();
//					failedMessage.setCreateServerId(WServer.getInstance().getServerId());
//					failedMessage.setUserId(msg.getUserId());
//					failedMessage.setReason((byte)51);
//					//msg.getSession().write(failedMessage);
//					MessageUtil.write(msg.getSession(), failedMessage);
					return;
				}
				
			}
			
			//分配玩家所在线
			ManagerPool.mapManager.selectLine(player);
			log.debug("上线玩家" + player.getId() + "选线为" + player.getLine());
			
			Map map = ManagerPool.mapManager.getMap(player.getServerId(), player.getLine(),
					player.getMap());
			
			//副本地图已经消失
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
			
//			//死亡复活
//			if (player.isDie() || player.getHp() <= 0) {
//				log.error("loadfinishfromchangemap player " + player.getId() + " die!");
////				//给予pk保护buff
////				if (mapBean.getQ_map_buff() == 0 && player.getKillerType() == 2) {
////					ManagerPool.buffManager.addBuff(player, player, Global.PROTECT_FOR_KILLED, 0, 0, 0);
////				}
//				if(map==null){
//					Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapBean.getQ_map_die());
//					Grid grid = MapUtils.getGrid(mapBean.getQ_map_die_x(), mapBean.getQ_map_die_y(), grids);
//					
//					ManagerPool.mapManager.changeMap(player, mapBean.getQ_map_die(), mapBean.getQ_map_die(), 0, grid.getCenter());
//					return;
//				}
//			}else 
			if (map == null){
				log.error("loadfinishfromchangemap server " + player.getServerId() + " line " + player.getLine() + " map " +
					player.getMap() + " is null!");
				if (mapBean != null && mapBean.getQ_map_quit() != 0) {
					Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapBean.getQ_map_quit());
					Grid grid = MapUtils.getGrid(mapBean.getQ_map_quit_x(), mapBean.getQ_map_quit_y(), grids);
					ManagerPool.mapManager.changeMap(player, mapBean.getQ_map_quit(), mapBean.getQ_map_quit(), 0, grid.getCenter(), this.getClass().getName() + ".action");
					return;
				}
			}
			//选线
			ManagerPool.mapManager.selectLine(player);
			log.debug("选择玩家" + player.getId() + "选线为" + player.getLine());

			player.setGateId(msg.getGateId());
			
			//把进入地图放入所选线主线程
			MServer line = WServer.getInstance().getMServer(player.getLine(), player.getMap());
			if(line==null){
				log.error("loadfinishfromchangemap 所选择的线" + player.getLine() + "和地图" + player.getMap() + "不存在！");
				return;
			}

			//登陆完成，玩家状态改为正常
			player.setState(PlayerState.NORMAL);
			
			if(reload){
				try{
					//加载金币
					ManagerPool.backpackManager.loadGold(player);
				}catch (Exception e) {
					log.error(e, e);
					//告诉网关退出,数据库错误
//					ResCreateCharacterFailedMessage failedMessage = new ResCreateCharacterFailedMessage();
//					failedMessage.setCreateServerId(WServer.getInstance().getServerId());
//					failedMessage.setUserId(msg.getUserId());
//					failedMessage.setReason((byte)51);
//					//msg.getSession().write(failedMessage);
//					MessageUtil.write(msg.getSession(), failedMessage);
					
					return;
				}
				//邮件
				ManagerPool.mailServerManager.loginLoadMail(player);
				
				ManagerPool.playerManager.registerPlayer(player);
				//重加载自身GM等级
				ManagerPool.gmcommandManager.reloadGMLevel(player);
				
				//通知网关服务器用户登录成功
				ResLoginSuccessToGateMessage gate_msg = new ResLoginSuccessToGateMessage();
				gate_msg.setServerId(player.getServerId());
				gate_msg.setCreateServerId(player.getCreateServerId());
				gate_msg.setUserId(player.getUserId());
				gate_msg.setPlayerId(player.getId());
				MessageUtil.send_to_gate(player.getGateId(), player.getId(), gate_msg);
				
				//通知世界服务器用户登录成功
				ResLoginSuccessToWorldMessage world_msg = new ResLoginSuccessToWorldMessage();
				world_msg.setGateId(player.getGateId());
				world_msg.setServerId(player.getServerId());
				world_msg.setUserId(player.getUserId());
				world_msg.setPlayerId(player.getId());
				world_msg.setIsAdult(msg.getIsadult());
				world_msg.setLogintype(player.getLoginType());
				MessageUtil.send_to_world(world_msg);
				
				//重新登陆
				ReqLoadFinishMessage message = new ReqLoadFinishMessage();
				message.setType((byte)1);
				message.setWidth(msg.getWidth());
				message.setHeight(msg.getHeight());
				ReqLoadFinishHandler handler = new ReqLoadFinishHandler();
				handler.setMessage(message);
				handler.setParameter(player);
				line.addCommand(handler);
			}else{
				//进入地图操作
				ReqEnterMapMessage message = new ReqEnterMapMessage();
				message.setWidth(msg.getWidth());
				message.setHeight(msg.getHeight());
				ReqEnterMapHandler handler = new ReqEnterMapHandler();
				handler.setMessage(message);
				handler.setParameter(player);
				line.addCommand(handler);
			}
			
			long end = System.currentTimeMillis();
			log.error("玩家(" + player.getId() + ")进入地图(" + player.getMapModelId() + ")第一步耗时:" + (end - start));
		}catch(Exception e){
			log.error(e, e);
		}
	}
}