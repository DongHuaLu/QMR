package com.game.login.manager;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import com.game.dblog.LogService;
import com.game.dblog.UpdateLogService;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.login.message.ReqCreateCharacterToGameMessage;
import com.game.login.message.ReqJustCreateCharacterToGameMessage;
import com.game.login.message.ResCreateCharacterFailedMessage;
import com.game.login.message.ResLoginSuccessToGateMessage;
import com.game.login.message.ResLoginSuccessToWorldMessage;
import com.game.login.message.ResRolesCreateReportToGateMessage;
import com.game.manager.ManagerPool;
import com.game.player.log.RoleCreateLog;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.server.impl.WServer;
import com.game.utils.MessageUtil;
import com.game.utils.WordFilter;

public class LoginManager {
	private static LoginManager me;
	private static Object obj = new Object();
	private LoginManager() {
	}
	public static LoginManager getInstance() {
		synchronized (obj) {
			if (me == null) me = new LoginManager();
		}
		return me;
	}
	
	Logger log = Logger.getLogger(LoginManager.class);
	
	public void createCharacter(ReqJustCreateCharacterToGameMessage msg) {
		Player player = createCharacter(msg.getSession(), msg.getCreateServer(), msg.getName(), msg.getSex(), msg.getUserId()
				, msg.getAuto(), msg.getUserName(), msg.getIcon(), msg.getLogintype(), msg.getOptIp()
				, msg.getAgentPlusdata(), msg.getAgentColdatas(), msg.getIsGuest(), msg.getGateId());
		
		if (player == null) {
			return ;
		}
		
		ResRolesCreateReportToGateMessage reqortmsg = new ResRolesCreateReportToGateMessage();
		reqortmsg.setServerId(WServer.getInstance().getServerId());
		reqortmsg.setCreateServerId(player.getCreateServerId());
		reqortmsg.setUserId(msg.getUserId());
		reqortmsg.setPlayerId(player.getId());
		reqortmsg.setMapId(player.getMapModelId());
		reqortmsg.setPlayername(player.getName());
		MessageUtil.send_to_gate(msg.getGateId(), player.getId(), reqortmsg);
	}
	
	public void createCharacter(ReqCreateCharacterToGameMessage msg) {
		Player player = createCharacter(msg.getSession(), msg.getCreateServer(), msg.getName(), msg.getSex(), msg.getUserId()
				, msg.getAuto(), msg.getUserName(), msg.getIcon(), msg.getLogintype(), msg.getOptIp()
				, msg.getAgentPlusdata(), msg.getAgentColdatas(), msg.getIsGuest(), msg.getGateId());
		
		if (player == null) {
			return ;
		}
		
		//分配玩家所在线
		ManagerPool.mapManager.selectLine(player);
		log.debug("创建玩家" + player.getId() + "选线为" + player.getLine());
		
		int serverId = WServer.getInstance().getServerId();
		//通知网关服务器用户登录成功
		ResLoginSuccessToGateMessage gate_msg = new ResLoginSuccessToGateMessage();
		gate_msg.setServerId(serverId);
		gate_msg.setCreateServerId(player.getCreateServerId());
		gate_msg.setUserId(msg.getUserId());
		gate_msg.setPlayerId(player.getId());
		gate_msg.setMapId(player.getMapModelId());
		MessageUtil.send_to_gate(msg.getGateId(), player.getId(), gate_msg);
		
		ResRolesCreateReportToGateMessage reqortmsg = new ResRolesCreateReportToGateMessage();
		reqortmsg.setServerId(serverId);
		reqortmsg.setCreateServerId(player.getCreateServerId());
		reqortmsg.setUserId(msg.getUserId());
		reqortmsg.setPlayerId(player.getId());
		reqortmsg.setMapId(player.getMapModelId());
		reqortmsg.setPlayername(player.getName());
		MessageUtil.send_to_gate(msg.getGateId(), player.getId(), reqortmsg);
		
		
		//通知世界服务器用户登录成功
		ResLoginSuccessToWorldMessage world_msg = new ResLoginSuccessToWorldMessage();
		world_msg.setGateId(msg.getGateId());
		world_msg.setServerId(serverId);
		world_msg.setUserId(msg.getUserId());
		world_msg.setPlayerId(player.getId());
		world_msg.setIsAdult(msg.getIsAdult());
		world_msg.setLoginIp(msg.getOptIp());
		world_msg.setLogintype(msg.getLogintype());
		MessageUtil.send_to_world(world_msg);
	}
	
	private Player createCharacter(IoSession session, int createServer, String name, byte sex, String userId
			, byte auto, String userName, String icon, int loginType, String optIp, String gentPlusdata
			, String gentColdatas, byte guest, int gateId) {
		
		long start = System.currentTimeMillis();
		
		boolean guestName = false;
		if(name==null || "".equals(name)){
			name = ManagerPool.randomNameManager.randomRoleName(sex==1);
			guestName = true;
		}else if(name==null || name.length() < 2 || name.length() > 6){
			//重名提示
			log.debug("玩家名字非法长度：" + name);
			
			ResCreateCharacterFailedMessage failedMessage = new ResCreateCharacterFailedMessage();
			failedMessage.setCreateServerId(createServer);
			failedMessage.setUserId(userId);
			failedMessage.setReason((byte)1);
			MessageUtil.write(session, failedMessage);
			return null;
		}
		
		if(!guestName && WordFilter.getInstance().hashNoLimitedWords(name)){
			//非法提示
			log.debug("玩家名字非法字符：" + name);
			
			ResCreateCharacterFailedMessage failedMessage = new ResCreateCharacterFailedMessage();
			failedMessage.setCreateServerId(createServer);
			failedMessage.setUserId(userId);
			failedMessage.setReason((byte)2);
			MessageUtil.write(session, failedMessage);
			return null;
		}
		
		if(!guestName && WordFilter.getInstance().hashBadWords(name)){
			//非法提示
			log.debug("玩家名字非法字符：" + name);
			
			ResCreateCharacterFailedMessage failedMessage = new ResCreateCharacterFailedMessage();
			failedMessage.setCreateServerId(createServer);
			failedMessage.setUserId(userId);
			failedMessage.setReason((byte)3);
			MessageUtil.write(session, failedMessage);
			return null;
		}
		
		//重名检测
		name = "[" + createServer + ResManager.getInstance().getString("区") + "]" + name;
		
		boolean repeat = ManagerPool.playerManager.checkName(name);
		while(repeat){
			if(guestName || auto==1){
				name = "[" + createServer + ResManager.getInstance().getString("区") + "]" + ManagerPool.randomNameManager.randomRoleName(sex==1);
				repeat = ManagerPool.playerManager.checkName(name);
			}else{
				//重名提示
				log.debug("玩家名字重名：" + name);
				
				ResCreateCharacterFailedMessage failedMessage = new ResCreateCharacterFailedMessage();
				failedMessage.setCreateServerId(createServer);
				failedMessage.setUserId(userId);
				failedMessage.setReason((byte)4);
				MessageUtil.write(session, failedMessage);
				return null;
			}
		}
		
		//创建玩家
		Player player = ManagerPool.playerManager.createPlayer(name, icon, sex);
		//账号id
		player.setUserId(userId);
		//账号名字
		player.setUserName(userName);
		//服务器名
		player.setServerName(WServer.getInstance().getServerName());
		//平台
		player.setWebName(WServer.getInstance().getServerWeb());
		//创建服务器
		player.setCreateServerId(createServer);
		//登陆类型
		player.setLoginType(loginType);
		//创建时间
		player.setCreateTime(System.currentTimeMillis());
		//ip
		player.setLoginIp(optIp);
		//平台参数1
		player.setAgentPlusdata(gentPlusdata);
		//平台参数2
		player.setAgentColdatas(gentColdatas);
		//玩家为登陆状态
		player.setState(PlayerState.LOGIN);
		try{
			//保存玩家
			ManagerPool.playerManager.insertPlayer(player);
		}catch (Exception e) {
			log.error(e, e);
			
			ManagerPool.playerManager.removeName(name);
			//告诉网关退出,数据库错误
			ResCreateCharacterFailedMessage failedMessage = new ResCreateCharacterFailedMessage();
			failedMessage.setCreateServerId(createServer);
			failedMessage.setUserId(userId);
			failedMessage.setReason((byte)53);
			MessageUtil.write(session, failedMessage);
			
			return null;
		}
		
		try{
			//加载金币
			ManagerPool.backpackManager.loadGold(player);
		}catch (Exception e) {
			log.error(e, e);
			//告诉网关退出,数据库错误
			ResCreateCharacterFailedMessage failedMessage = new ResCreateCharacterFailedMessage();
			failedMessage.setCreateServerId(createServer);
			failedMessage.setUserId(userId);
			failedMessage.setReason((byte)53);
			MessageUtil.write(session, failedMessage);
			return null;
		}
		
		ManagerPool.gmcommandManager.reloadGMLevel(player);//加载GM等级
		
		//载入平台VIP
		try{
			if("qq3366".equals(WServer.getInstance().getServerWeb())){
				@SuppressWarnings("unchecked")
				HashMap<String, String> paras = (HashMap<String, String>)JSONserializable.toObject(player.getAgentPlusdata(), HashMap.class);
				int qq_is_blue_vip = Integer.parseInt(paras.get("qq_is_blue_vip"));
				int qq_is_blue_year_vip = Integer.parseInt(paras.get("qq_is_blue_year_vip"));
				int qq_is_super_blue_vip = Integer.parseInt(paras.get("qq_is_super_blue_vip"));
				int qq_blue_vip_level = Integer.parseInt(paras.get("qq_blue_vip_level"));
				int vip = 0;
				if(qq_is_blue_vip >0 || qq_is_blue_year_vip>0 || qq_is_super_blue_vip>0){
					if(qq_is_blue_year_vip > 0){
						vip = 0x0100;
					}else if(qq_is_super_blue_vip > 0){
						vip = 0x0200;
					}
					vip = vip | qq_blue_vip_level;
				}
				int a3366_grow_level = Integer.parseInt(paras.get("3366_grow_level"));
				
				player.getVipright().setWebVipLevel(vip);
				player.getVipright().setWebVipLevel2(a3366_grow_level);
			}
		}catch (Exception e) {
			log.error(e, e);
		}
		player.setChangeUser(guest);
		player.setChangeName(guestName?(byte)1:(byte)0);
		log.info(Thread.currentThread() + "-->Create Player cost:" + (System.currentTimeMillis() - start));
		
		//注册玩家
		ManagerPool.playerManager.registerPlayer(player);
		//注册玩家状态
		UpdateLogService.getInstance().updateRoleDate(player.getId());
		//注册网关
		player.setGateId(gateId);

		//角色创建日志
		RoleCreateLog createLog=new RoleCreateLog(player);
		LogService.getInstance().execute(createLog);
		return player;
	}
}
