package com.game.skill.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.player.structs.Player;
import com.game.skill.message.NowLeranSkillMessage;
import com.game.utils.MessageUtil;

public class NowLearnSkillQueryHandler extends Handler{

	Logger log = Logger.getLogger(NowLearnSkillQueryHandler.class);

	public void action(){
		try{
			Player player = (Player) getParameter();
			if(player==null)return;
			NowLeranSkillMessage res=new NowLeranSkillMessage();
			res.setSkillModelId(player.getNowLearnSkillId());
			res.setRemainingTime(player.getSkillLearnTime());
			MessageUtil.tell_player_message(player, res);
			
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}