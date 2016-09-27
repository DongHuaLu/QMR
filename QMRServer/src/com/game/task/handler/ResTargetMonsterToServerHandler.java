package com.game.task.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.map.manager.MapManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.task.bean.TargetMonsterInfo;
import com.game.task.message.ResTargetMonsterMessage;
import com.game.task.message.ResTargetMonsterToServerMessage;
import com.game.utils.MessageUtil;
import com.game.command.Handler;

public class ResTargetMonsterToServerHandler extends Handler{

	Logger log = Logger.getLogger(ResTargetMonsterToServerHandler.class);

	public void action(){
		try{
			ResTargetMonsterToServerMessage msg = (ResTargetMonsterToServerMessage)this.getMessage();
			
			Player onLinePlayer = PlayerManager.getInstance().getOnLinePlayer(msg.getRoleId().get(0));
			if(onLinePlayer==null){
				return;
			}			
			
			List<TargetMonsterInfo> monsterInfoList = msg.getMonsterInfoList();
			List<TargetMonsterInfo> list=new ArrayList<TargetMonsterInfo>();
			for (TargetMonsterInfo targetMonsterInfo : monsterInfoList) {
				if(MapManager.checkLineIsOpen(targetMonsterInfo.getMapId(),targetMonsterInfo.getLineId())){
					list.add(targetMonsterInfo);
				}
			}
			ResTargetMonsterMessage rspmsg=new ResTargetMonsterMessage();
			rspmsg.setMonsterInfoList(list);
			MessageUtil.tell_player_message(onLinePlayer, rspmsg);
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}