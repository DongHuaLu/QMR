package com.game.player.handler;

import org.apache.log4j.Logger;
import com.game.player.message.ReqSyncPlayerSkillMessage;
import com.game.command.Handler;
import com.game.data.bean.Q_skill_modelBean;
import com.game.data.manager.DataManager;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.GestTop;
import com.game.toplist.structs.TopPlayer;

public class ReqSyncPlayerSkillHandler extends Handler{

	Logger log = Logger.getLogger(ReqSyncPlayerSkillHandler.class);

	public void action(){
		try{
			ReqSyncPlayerSkillMessage msg = (ReqSyncPlayerSkillMessage)this.getMessage();
			// 更新角色
			Q_skill_modelBean q_skill_modelBean = DataManager.getInstance().q_skill_modelContainer.getMap().get(msg.getSkill() + "_1");
			if (q_skill_modelBean == null) return ;
			TopPlayer topPlayer = TopListManager.getTopplayerMap().get(msg.getPlayerId());
			
			if (topPlayer == null) {
				return ;
			}
			
			if (q_skill_modelBean.getQ_panel_type() == 1 && q_skill_modelBean.getQ_trigger_type() == 1) {
				topPlayer.setMoziSkillLevel(topPlayer.getMoziSkillLevel() + msg.getUpLevel());
			}
			if (q_skill_modelBean.getQ_panel_type() == 1 && q_skill_modelBean.getQ_trigger_type() == 2) {
				topPlayer.setMozibackSkillLevel(topPlayer.getMozibackSkillLevel() + msg.getUpLevel());
			}
			if (q_skill_modelBean.getQ_panel_type() == 2 && q_skill_modelBean.getQ_trigger_type() == 1) {
				topPlayer.setLongyuanSkillLevel(topPlayer.getLongyuanSkillLevel() + msg.getUpLevel());
			}
			if (q_skill_modelBean.getQ_panel_type() == 2 && q_skill_modelBean.getQ_trigger_type() == 2) {
				topPlayer.setLongyuanbackSkillLevel(topPlayer.getLongyuanbackSkillLevel() + msg.getUpLevel());
			}
			
			GestTop gestTop = new GestTop(msg.getPlayerId()
					, topPlayer.getMoziSkillLevel() + topPlayer.getMozibackSkillLevel() + topPlayer.getLongyuanSkillLevel() + topPlayer.getLongyuanbackSkillLevel()
					, msg.getSkillTime());
			TopListManager.getInstance().updateTopData(gestTop);
			
		}catch(ClassCastException e){
			log.error(e);
		}
	}
}