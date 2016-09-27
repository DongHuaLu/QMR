package com.game.login.handler;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.data.bean.Q_mapBean;
import com.game.dataserver.manager.DataServerManager;
import com.game.json.JSONserializable;
import com.game.login.message.ReqLoginCharacterToGameMessage;
import com.game.login.message.ResLoginCharacterFailedMessage;
import com.game.login.message.ResLoginSuccessToGateMessage;
import com.game.login.message.ResLoginSuccessToWorldMessage;
import com.game.login.message.ResReloginCharacterMessage;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.server.config.ServerType;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

public class ReqLoginCharacterToGameHandler extends Handler{

	Logger log = Logger.getLogger(ReqLoginCharacterToGameHandler.class);

	public void action(){
		
		ReqLoginCharacterToGameMessage msg = (ReqLoginCharacterToGameMessage)this.getMessage();
		try{
			Player player = null;
			int serverId = WServer.getInstance().getServerId();
			
			player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
			if(player==null){
				player = ManagerPool.playerManager.loadPlayer(msg.getPlayerId());
				if(player==null){
					ResLoginCharacterFailedMessage return_msg = new ResLoginCharacterFailedMessage();
					return_msg.setReason((byte)50);
					return_msg.setCreateServerId(msg.getServerId());
					return_msg.setUserId(msg.getUserId());
					//msg.getSession().write(return_msg);
					MessageUtil.write(msg.getSession(), return_msg);
					return;
				}
			}
			
			if(serverId!=player.getServerId() || msg.getServerId()!=player.getCreateServerId() || !msg.getUserId().equals(player.getUserId())){
				ResLoginCharacterFailedMessage return_msg = new ResLoginCharacterFailedMessage();
				return_msg.setReason((byte)51);
				return_msg.setCreateServerId(msg.getServerId());
				return_msg.setUserId(msg.getUserId());
				//msg.getSession().write(return_msg);
				MessageUtil.write(msg.getSession(), return_msg);
				return;
			}
			
			
//			HashMap<String, Player> user = ManagerPool.playerManager.getOnLineUser();
//			synchronized (user) {
//				if(user.containsKey(player.getUserId())){
//					Player other = user.get(player.getUserId());
//					if(other.getId()!=player.getId()){
//						ResReloginCharacterMessage return_msg = new ResReloginCharacterMessage();
//						return_msg.setPlayerId(other.getId());
//						return_msg.setCreateServerId(msg.getServerId());
//						return_msg.setUserId(msg.getUserId());
//						return_msg.setIsAdult(msg.getIsAdult());
//						//msg.getSession().write(return_msg);
//						MessageUtil.write(msg.getSession(), return_msg);
//					}
//				}else{
//					user.put(player.getUserId(), player);
//				}
//			}
			
			DataServerManager.getInstance().reqPlayerStateFromDataServer(player);
			//玩家为登陆状态
			player.setState(PlayerState.LOGIN);
			//注册网关
			player.setGateId(msg.getGateId());
			if(msg.getLoginIp()==null){
				log.error("错误IP");	
			}
			player.setLoginIp(msg.getLoginIp());
			player.setLoginType(msg.getLogintype());
			
			//账号名字
			player.setUserName(msg.getUserName());
			//服务器名
			player.setServerName(WServer.getInstance().getServerName());
			//平台
			player.setWebName(WServer.getInstance().getServerWeb());
			
//			log.error("登录IP:"+player.getLoginIp());
			//分配玩家所在线
			ManagerPool.mapManager.selectLine(player);
			log.debug("上线玩家" + player.getId() + "选线为" + player.getLine());
			
			Map map = ManagerPool.mapManager.getMap(player.getServerId(), player.getLine(),
					player.getMap());
			
			//副本地图已经消失
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
			if (map == null){
				if (mapBean != null && mapBean.getQ_map_quit() != 0 && mapBean.getQ_map_quit_x() > 0 && mapBean.getQ_map_quit_y() > 0) {
					Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapBean.getQ_map_quit());
					Grid grid = MapUtils.getGrid(mapBean.getQ_map_quit_x(), mapBean.getQ_map_quit_y(), grids);
					player.setMap(mapBean.getQ_map_quit());
					player.setMapModelId(mapBean.getQ_map_quit());
					log.debug("副本消失重置玩家" + player.getId() + "选线为0");
					player.setLine(0);
					player.setPosition(grid.getCenter());
				}else {
					//没有设定回城坐标，进入这里,先随机，找不到就找玩家上次的地图
					Position position = ManagerPool.mapManager.RandomDieBackCity(mapBean);
					int mapid = 0;
					if (position == null) {
						position = player.getFormerposition();
						mapid = player.getFormermapid();
					}else {
						mapid = mapBean.getQ_map_die();
						List<Grid> gridlist = MapUtils.getRoundNoBlockGrid(position,10*MapUtils.GRID_BORDER , mapid);
						int rnd = RandomUtils.random(1, gridlist.size())-1;
						position = gridlist.get(rnd).getCenter();
					}
					//10格范围内随机地点
					ManagerPool.mapManager.changeMap(player, mapid,mapid, 0,position, this.getClass().getName() + ".action");
				}
			}
			
			//分配玩家所在线
			ManagerPool.mapManager.selectLine(player);
			log.info("上线玩家" + player.getId() + "{" + player.getName() + "}" + "选线为" + player.getLine());
			
			map = ManagerPool.mapManager.getMap(player.getServerId(), player.getLine(),
					player.getMap());
			if(map==null){
				//是否切换服务器
				boolean change = false;
				mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
				// 切换服务器
				if (mapBean != null) {
					if (mapBean.getQ_map_public() == 1) {
						if(player.getLocate() != ServerType.PUBLIC.getValue()){
							// 新地图是公共地图
							player.setLocate(ServerType.PUBLIC.getValue());
							change = true;
						}
					} else if (mapBean.getQ_map_public() == 0) {
						if(player.getLocate() != player.getCountry()){
							// 新地图是普通地图
							player.setLocate(player.getCountry());
							change = true;
						}
					}
				}

				
				if(change){
					ManagerPool.playerManager.updatePlayerSync(player);
					ManagerPool.playerManager.removePlayer(player);
					
					ResReloginCharacterMessage return_msg = new ResReloginCharacterMessage();
					return_msg.setPlayerId(msg.getPlayerId());
					return_msg.setCreateServerId(msg.getServerId());
					return_msg.setUserId(msg.getUserId());
					return_msg.setIsAdult(msg.getIsAdult());
					//msg.getSession().write(return_msg);
					MessageUtil.write(msg.getSession(), return_msg);
					return;
				}else{
					//可在这里把玩家甩到设定的初始地图去。
					ResLoginCharacterFailedMessage return_msg = new ResLoginCharacterFailedMessage();
					return_msg.setReason((byte)52);
					return_msg.setCreateServerId(msg.getServerId());
					return_msg.setUserId(msg.getUserId());
					//msg.getSession().write(return_msg);
					MessageUtil.write(msg.getSession(), return_msg);
					return;
				}
			}
			try{
				//加载金币
				ManagerPool.backpackManager.loadGold(player);
			}catch (Exception e) {
				log.error(e, e);
				//告诉网关退出,数据库错误
				ResLoginCharacterFailedMessage failedMessage = new ResLoginCharacterFailedMessage();
				failedMessage.setCreateServerId(WServer.getInstance().getServerId());
				failedMessage.setUserId(msg.getUserId());
				failedMessage.setReason((byte)53);
				//msg.getSession().write(failedMessage);
				MessageUtil.write(msg.getSession(), failedMessage);
				
				return;
			}
			//邮件
			ManagerPool.mailServerManager.loginLoadMail(player);
			
			ManagerPool.playerManager.registerPlayer(player);
			//重加载自身GM等级
			ManagerPool.gmcommandManager.reloadGMLevel(player);
			//载入平台VIP
			try{
				if("qq3366".equals(WServer.getInstance().getServerWeb())){
//					player.setAgentColdatas("{\"agent\":\"qq3366\",\"qq_appid\":\"123123123\",\"qq_pf\":\"qq3366\",\"qq_domain\":\"asdasdasd\",\"qq_is_blue_vip\":\"0\",\"qq_is_blue_year_vip\":\"1\",\"qq_blue_vip_level\":\"6\",\"qq_is_super_blue_vip\":\"0\",\"3366_grow_level\":\"5\",\"3366_level\":\"0\",\"3366_nickname\":\"asdasdasd\",\"3366_figureurl\":\"asdasdasd\"}");
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
			
			//通知网关服务器用户登录成功
			ResLoginSuccessToGateMessage gate_msg = new ResLoginSuccessToGateMessage();
			gate_msg.setServerId(serverId);
			gate_msg.setCreateServerId(player.getCreateServerId());
			gate_msg.setUserId(msg.getUserId());
			gate_msg.setPlayerId(player.getId());
			gate_msg.setMapId(player.getMapModelId());
			MessageUtil.send_to_gate(msg.getGateId(), player.getId(), gate_msg);
			
			//通知世界服务器用户登录成功
			ResLoginSuccessToWorldMessage world_msg = new ResLoginSuccessToWorldMessage();
			world_msg.setGateId(msg.getGateId());
			world_msg.setServerId(serverId);
			world_msg.setUserId(msg.getUserId());
			world_msg.setPlayerId(player.getId());
			world_msg.setIsAdult(msg.getIsAdult());
			world_msg.setLoginIp(msg.getLoginIp());
			world_msg.setLogintype(msg.getLogintype());
			MessageUtil.send_to_world(world_msg);
			
		}catch(Exception e){
			log.error(e, e);
			ResLoginCharacterFailedMessage return_msg = new ResLoginCharacterFailedMessage();
			return_msg.setReason((byte)53);
			return_msg.setCreateServerId(msg.getServerId());
			return_msg.setUserId(msg.getUserId());
			//msg.getSession().write(return_msg);
			MessageUtil.write(msg.getSession(), return_msg);
		}
	}
}