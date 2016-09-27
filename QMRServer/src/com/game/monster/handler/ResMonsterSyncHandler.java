package com.game.monster.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.message.ResMonsterSyncMessage;
import com.game.player.structs.Player;
import com.game.server.config.MapConfig;
import com.game.server.config.ServerType;
import com.game.server.impl.MServer;
import com.game.server.impl.WServer;
import com.game.task.bean.TargetMonsterInfo;
import com.game.task.message.ResTargetMonsterChangeMessage;
import com.game.task.struts.ConquerTask;
import com.game.utils.MessageUtil;

public class ResMonsterSyncHandler extends Handler{
	Logger log = Logger.getLogger(ResMonsterSyncHandler.class);
	public void action(){
		try{
			final ResMonsterSyncMessage msg = (ResMonsterSyncMessage)this.getMessage();			
			final int publicserver= WServer.getGameConfig().getServerByCountry(ServerType.PUBLIC.getValue());
			final TargetMonsterInfo info=new TargetMonsterInfo();
			info.setHp(msg.getCurrentHp());
			info.setKiller(msg.getKiller());
			info.setReliveTime((int)(msg.getRevive()/1000));
			info.setModelId(msg.getModelId());
			info.setState((byte) msg.getState());
			info.setLineId(msg.getLineId());
			info.setMapId(msg.getMapmodelid());
			info.setBirthX(msg.getBirthX());
			info.setBirthY(msg.getBirthY());
//			MonsterStateManager.getInstance().getMonsterInfo().put(msg.getServerId()+"_"+info.getModelId(),info);
			MServer[] servers = WServer.getInstance().getMServers();
			for (int i = 0; i < servers.length; i++) {
				final MServer server=servers[i];
				for (int j = 0; j < server.getMapConfigs().size(); j++) {
					MapConfig config = server.getMapConfigs().get(j);
					final Map map = ManagerPool.mapManager.getMap(config.getServerId(), config.getLineId(), config.getMapId());
					if(map==null||map.getPlayers().size()<=0){
						continue;
					}
					server.addCommand(new Handler() {					
						@Override
						public void action() {
							List<Player> needSendRole=new ArrayList<Player>();
							Iterator<Player> iter = map.getPlayers().values().iterator();
							while (iter.hasNext()) {
								Player player = (Player) iter.next();
								int serverByCountry = WServer.getGameConfig().getServerByCountry(player.getCountry());
								if(msg.getServerId()!=serverByCountry&&msg.getServerId()!=publicserver){
									//只通知公共区的怪和本服的怪
									continue;
								}
								List<ConquerTask> currentConquerTasks = player.getCurrentConquerTasks();
								for (ConquerTask task : currentConquerTasks) {
									if (task.endNeedKillMonster().keySet().contains(msg.getModelId())) {
										needSendRole.add(player);
									}
								}
							}

							if(needSendRole.size()>0){
								ResTargetMonsterChangeMessage resMsg=new ResTargetMonsterChangeMessage();
								
								resMsg.setMonsterInfo(info);
								for (Player player : needSendRole) {
									resMsg.getRoleId().add(player.getId());
								}
								MessageUtil.send_to_gate(map.getSendId(),resMsg);
							}
						}
					});
				}
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}