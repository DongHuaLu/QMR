package com.game.task.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.quartz.impl.calendar.MonthlyCalendar;

import com.game.command.Handler;
import com.game.monster.manager.MonsterManager;
import com.game.monster.structs.Monster;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.task.bean.TargetMonsterInfo;
import com.game.task.message.ReqTargetMonsterMessage;
import com.game.task.message.ResTargetMonsterInfoMessage;
import com.game.task.message.ResTargetMonsterToServerMessage;
import com.game.utils.MessageUtil;

public class ReqTargetMonsterHandler extends Handler{

	Logger log = Logger.getLogger(ReqTargetMonsterHandler.class);

	public void action(){
		try{
			ReqTargetMonsterMessage msg = (ReqTargetMonsterMessage)this.getMessage();
			Player player = PlayerManager.getPlayers().get(msg.getReqRoleId());
			if(player==null){
				return;
			}
			List<Integer> modelIds = msg.getModelIds();
			ArrayList<TargetMonsterInfo> monsterInfos=new ArrayList<TargetMonsterInfo>();
			for (Integer integer : modelIds) {
				try{
					ConcurrentHashMap<Long,Monster> monsters = MonsterManager.getInstance().getMonsters(msg.getServerId(),integer);
					if(monsters!=null&&monsters.size()>0){
						Iterator<Monster> iterator = monsters.values().iterator();
						while (iterator.hasNext()) {
							Monster monster = (Monster) iterator.next();
							TargetMonsterInfo info=new TargetMonsterInfo();
							info.setHp(monster.getHp());
							info.setKiller(monster.getKiller());
							info.setLineId(monster.getLineId());
							info.setModelId(monster.getModelId());
							info.setReliveTime((int) (monster.getReviveTime()/1000));
							info.setState((byte) monster.getState());
							info.setMapId(monster.getMapModelId());
							info.setBirthX(monster.getBirthX());
							info.setBirthY(monster.getBirthY());
							monsterInfos.add(info);
//							break;
						}
					}	
				}catch (Exception e) {
					log.error(e,e);
				}
			}
			if (monsterInfos.size() > 0) {
				ResTargetMonsterToServerMessage serverMsg = new ResTargetMonsterToServerMessage();
				serverMsg.setMonsterInfoList(monsterInfos);
				MessageUtil.send_to_game(player, serverMsg);
			}
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}