package com.game.fight.timer;

import org.apache.log4j.Logger;

import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.skill.structs.Skill;
import com.game.timer.TimerEvent;

/**
 * 击中事件
 * @author heyang
 *
 */
public class HitTimer extends TimerEvent {
	
	protected Logger log = Logger.getLogger(HitTimer.class);
	//攻击Id
	private long fightId;
	//攻击者
	private Fighter attacker;
	//使用技能
	private Skill skill;
	//防御者
	private Fighter defender;
	//方向
	private int direction;
	//是否触发技能或buff
	private boolean trigger;
	
	public HitTimer(long fightId, Fighter attacker, Fighter defender,Skill skill, int direction, long delay, boolean trigger) {
		super(1, delay);
		this.fightId = fightId;
		this.attacker = attacker;
		this.defender =defender;
		this.skill = skill;
		this.direction = direction;
		this.trigger = trigger;
	}

	@Override
	public void action() {
		try{
			if(attacker==null){
				return;
			}
			if(defender!=null){
				if(attacker.getServerId()!=defender.getServerId() || attacker.getLine()!=defender.getLine() || attacker.getMap()!=defender.getMap()) return;
			}
			ManagerPool.fightManager.attack(fightId, attacker, defender, skill, direction, trigger);
		}catch (Exception e) {
			log.error(e, e);
		}
	}

}
