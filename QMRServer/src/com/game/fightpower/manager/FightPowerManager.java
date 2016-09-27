package com.game.fightpower.manager;

import com.game.data.bean.Q_skill_modelBean;
import com.game.data.manager.DataManager;
import com.game.fightpower.message.ReqFightPowerToServerMessage;
import com.game.fightpower.message.ResFightPowerToClientMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.skill.structs.Skill;
import com.game.utils.MessageUtil;

/**
 * 战斗力系统
 *
 * @author 杨洪岚
 */
public class FightPowerManager {

//	private Logger log = Logger.getLogger(FightPowerManager.class);
	private static Object obj = new Object();
	//战斗力系统类实例
	private static FightPowerManager manager;

	private FightPowerManager() {
	}

	public static FightPowerManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new FightPowerManager();
			}
		}
		return manager;
	}
	//分母为10
	private int MaxMaxHpCof = 2;			//最大生命	2
	private int MaxMaxMpCof = 1;			//最大内力	1
	private int MaxDefCof = 10;			//防御		10
	private int MaxDodgeCof = 40;			//闪避		40
	private int MaxCritCof = 40;			//暴击		40
	private int MaxAttackSpeedCOF = 25;		//攻击速度	25
	private int MaxMoveSpeedCof = 15;		//移动速度	15
	private int MaxLuckyCof = 8;			//幸运		8
	private int MaxMaxSpCof = 1;			//体力		1
	private int MaxAttackCof = 14;			//攻击		14
	
	public void Update(Player player){
		ResFightPowerToClientMessage sendMessage = new ResFightPowerToClientMessage();
		player.setFightPower(calAllFightPower(player));
		sendMessage.setFightPower(player.getFightPower());
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public int calAllFightPower(Player player) {
		if (player != null) {
			return calPlayerFightPower(player) + calSkillFightPower(player);
		}
		return 0;
	}

	public int calPlayerFightPower(Player player) {
		if (player != null) {
			return (player.getCalattack() * MaxAttackCof
				+ player.getCaldefense() * MaxDefCof
				+ player.getCalcrit() * MaxCritCof
				+ player.getCaldodge() * MaxDodgeCof
				+ player.getCalmaxHp() * MaxMaxHpCof
				+ player.getCalmaxMp() * MaxMaxMpCof
				+ player.getCalmaxSp() * MaxMaxSpCof
				+ player.getCalattackSpeed() * MaxAttackSpeedCOF
				+ player.getCalspeed() * MaxMoveSpeedCof
				+ player.getCalluck() * MaxLuckyCof) / 10;
		}
		return 0;
	}

	public int calSkillFightPower(Player player) {
		if (player != null) {
			int ret = 0;
			for (int i = 0; i < player.getSkills().size(); i++) {
				Skill skill = player.getSkills().get(i);
				if (skill != null) {
					Q_skill_modelBean q_skill_modelBean = DataManager.getInstance().q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
					if (q_skill_modelBean != null) {
						ret = ret + q_skill_modelBean.getQ_fight_bonus();
					}
				}
			}
			return ret;
		}
		return 0;
	}
	
	public int calAttrFightPower(PlayerAttribute playerAttribute) {
		if (playerAttribute != null) {
			return (playerAttribute.getAttack() * MaxAttackCof
				+ playerAttribute.getDefense() * MaxDefCof
				+ playerAttribute.getCrit() * MaxCritCof
				+ playerAttribute.getDodge() * MaxDodgeCof
				+ playerAttribute.getMaxHp() * MaxMaxHpCof
				+ playerAttribute.getMaxMp() * MaxMaxMpCof
				+ playerAttribute.getMaxSp() * MaxMaxSpCof
				+ playerAttribute.getAttackSpeed() * MaxAttackSpeedCOF
				+ playerAttribute.getSpeed() * MaxMoveSpeedCof
				+ playerAttribute.getLuck() * MaxLuckyCof) / 10;
		}
		return 0;
	}
	
	public void reqFightPowerToServer(Player player, ReqFightPowerToServerMessage message){
		Update(player);
	}
}
