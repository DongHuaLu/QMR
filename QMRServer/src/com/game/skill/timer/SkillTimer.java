package com.game.skill.timer;

import org.apache.log4j.Logger;

import java.util.Iterator;

import com.game.data.bean.Q_skill_modelBean;
import com.game.dblog.LogService;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.skill.log.SkillLevelAction;
import com.game.skill.log.SkillLevelUpLog;
import com.game.skill.structs.Skill;
import com.game.timer.TimerEvent;
import com.game.utils.RandomUtils;

public class SkillTimer extends TimerEvent {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SkillTimer.class);

	private int lineId;
	
	private int serverId;
	
	private int mapId;
	
	private static int step=1000;
	
	public SkillTimer(int serverId, int lineId, int mapId) {
		super(-1,step);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		//遍历玩家列表
		Iterator<Player> iter = map.getPlayers().values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			if (player.getLine() == lineId && player.getServerId()==serverId ) {
				checkSkillLearn(player);
			}
		}
	}
	
	private void checkSkillLearn(Player player) {
		int nowLearnSkillId = player.getNowLearnSkillId();
		if(nowLearnSkillId==-1){
			return;
		}
		Skill skillById = ManagerPool.skillManager.getSkillByModelId(player, nowLearnSkillId);
		if (skillById!= null) {
			Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skillById.getSkillModelId()+"_"+(skillById.getSkillLevel()+1));
			int skillLearnTime = player.getSkillLearnTime() + step;
			if (skillLearnTime >= skillModel.getQ_up_need_time()) {
				int q_up_prob = skillModel.getQ_up_prob();
				int before=skillById.getSkillLevel();
				boolean result=false;
				if(RandomUtils.defaultIsGenerate(q_up_prob)){
					result=true;
					ManagerPool.skillManager.endUpLevel(player, skillById,skillById.getSkillLevel()+1,result);
				}else{
					result=false;
					ManagerPool.skillManager.endUpLevel(player, skillById,skillById.getSkillLevel(),result);
				}
				try {
					SkillLevelUpLog log = new SkillLevelUpLog();
					log.setAction(SkillLevelAction.ENDLEVELUP.toString());
					log.setBeforeTime(skillLearnTime);
					log.setResumegold(0);
					log.setLevel(skillById.getSkillLevel());
					log.setSkillId(skillById.getSkillModelId());
					log.setBeforelevel(before);
					log.setResult(result ? 1 : 0);
					log.setRoleId(player.getId());
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				} catch (Exception e) {
					logger.error(e,e);
				}
			} else {
				player.setSkillLearnTime(skillLearnTime);
			}
		}
	}
}
