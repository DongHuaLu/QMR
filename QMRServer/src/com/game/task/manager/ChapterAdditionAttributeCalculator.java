package com.game.task.manager;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_chapter_appendBean;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.manager.DataManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;
import com.game.task.struts.MainTask;

public class ChapterAdditionAttributeCalculator implements PlayerAttributeCalculator {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChapterAdditionAttributeCalculator.class);

	@Override
	public int getType() {
		return PlayerAttributeType.TASK_CHATPER;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		if(player.getCurrentMainTasks().size()>0){
			MainTask mainTask = player.getCurrentMainTasks().get(0);
			int modelid = mainTask.getModelid();
			Q_task_mainBean taskModel = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
			if(taskModel==null){
				logger.error("主线任务ID"+modelid+"找不到");
				return att;
			}
			int q_chapter = taskModel.getQ_chapter();
//			if(q_chapter>0){
				for(int i=0;i<=q_chapter-1;i++){
					Q_chapter_appendBean addition = DataManager.getInstance().q_chapter_appendContainer.getMap().get(i);
					if(addition!=null){
						att.setAttack(att.getAttack()+addition.getQ_attack());
						att.setAttackSpeed(att.getAttackSpeed()+addition.getQ_attack_speed());
						att.setCrit(att.getCrit()+addition.getQ_crit());
						att.setDefense(att.getDefense()+addition.getQ_defence());
						att.setDodge(att.getDodge()+addition.getQ_dodge());
						att.setLuck(att.getLuck()+addition.getQ_luck());
						att.setMaxHp(att.getMaxHp()+addition.getQ_maxhp());
						att.setMaxMp(att.getMaxMp()+addition.getQ_maxmp());
						att.setMaxSp(att.getMaxSp()+addition.getQ_maxsp());
						att.setSpeed(att.getSpeed()+addition.getQ_movespeed());
					}
				}
//			}
		}
		return att;
	}
}
