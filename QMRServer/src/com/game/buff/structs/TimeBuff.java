package com.game.buff.structs;

import java.util.List;

import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_characterBean;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.structs.Reasons;
import com.game.utils.Global;

public class TimeBuff extends Buff implements IBuff {

	private static final long serialVersionUID = 77215350304432935L;

	@Override
	public int add(Fighter source, Fighter target) {
		return 0;
	}

	@Override
	public int action(Fighter source, Fighter target) {
		Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
		if(buff==null) return 0;
		
		int maxHp = 0;
		int maxMp = 0;
		int maxSp = 0;
		
		int hp = 0;
		int mp = 0;
		int sp = 0;
		
		int min = 0;
		int cost = 0;
		int result = 0;
		long num = ((long)buff.getQ_effect_value() + this.getValue()) * this.getOverlay();
		//计算效果
		switch(this.getActionType()){
				//1	增加等级
			case BuffType.LEVEL:
				for (int i = 1; i <= num; i++) {
					ManagerPool.playerManager.setLevel((Player)source, source.getLevel() + 1, true, Reasons.LEVELUPBUFF);
				}
				ManagerPool.playerManager.levelUpSyn((Player)source);
				return 1;//ManagerPool.playerManager.setLevel((Player)source, source.getLevel() + buff.getQ_effect_value() + this.getValue(), true, Reasons.LEVELUPBUFF);
				//2	增加人物经验
			case BuffType.EXP:
				Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(source.getLevel());
				num += (long)model.getQ_exp() * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY  * this.getOverlay();
				ManagerPool.playerManager.addExp((Player)source, num, AttributeChangeReason.BUFF);
				
				return 1;
				//3	增加人物真气
			case BuffType.ZHENQI:
				return ManagerPool.playerManager.addZhenqi((Player)source, (int)num,AttributeChangeReason.BUFF);
				//4	增加战场声望
			case BuffType.BATTLEEXP:
				return ManagerPool.playerManager.addBattleExp((Player)source, (int)num, AttributeChangeReason.BUFF);
				//5	增加或减少当前生命值
			case BuffType.HP:
				maxHp = source.getMaxHp();
				hp = buff.getQ_effect_value() + this.getValue();
				hp += (long)maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				//无敌不受到伤害
				if(hp<0 && FighterState.WUDI.compare(source.getFightState())) return 0;
				if(source.getHp() >= maxHp) return 0;
				source.setHp(source.getHp() + hp);
				if(source.getHp() > maxHp) source.setHp(maxHp);
				else if(source.getHp() < 1){
					source.setHp(1);
				}
				min = (int) ((long)maxHp * 2000/ Global.MAX_PROBABILITY);
				if(hp >= min){
					log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + hp + ",因为自动恢复BUFF");
				}
				if(source instanceof Player) ManagerPool.playerManager.onHpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onHpChange((Monster)source);
				return 1;
				//6	增加或减少当前内力值
			case BuffType.MP:
				maxMp = source.getMaxMp();
				mp = buff.getQ_effect_value() + this.getValue();
				mp += (long)maxMp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				//禁止内力恢复
				if(mp>0 && FighterState.JINZHINEILIHUIFU.compare(source.getFightState())) return 0;
				//无敌不受到伤害
				if(mp<0 && FighterState.WUDI.compare(source.getFightState())) return 0;
				if(source.getMp() >= maxMp) return 0;
				source.setMp(source.getMp() + mp);
				if(source.getMp() > maxMp) source.setMp(maxMp);
				else if(source.getMp() < 0) source.setMp(0);
				if(source instanceof Player) ManagerPool.playerManager.onMpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onMpChange((Monster)source);
				return 1;
				//7	增加或减少当前体力值
			case BuffType.SP:
				maxSp = source.getMaxSp();
				sp = buff.getQ_effect_value() + this.getValue();
				sp += (long)maxSp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				//禁止体力恢复
				if(sp>0 && FighterState.JINZHITILIHUIFU.compare(source.getFightState())) return 0;
				//无敌不受到伤害
				if(sp<0 && FighterState.WUDI.compare(source.getFightState())) return 0;
				if(source.getSp() >= maxSp) return 0;
				source.setSp(source.getSp() + sp);
				if(source.getSp() > maxSp) source.setSp(maxSp);
				else if(source.getSp() < 0) source.setSp(0);
				if(source instanceof Player) ManagerPool.playerManager.onSpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onSpChange((Monster)source);
				return 1;
				//18  每隔一段时间加满人物的生命，直至剩余容量用完
			case BuffType.FILLHP:
				if(source.isDie()) return 0;
				if(source instanceof Player){
					//战斗中不恢复
					if(PlayerState.FIGHT.compare(((Player) source).getState())){
						return 0;
					}
				}
				maxHp = source.getMaxHp();
				if(source.getHp() >= maxHp) return 0;
				cost = maxHp - source.getHp();
				if(this.getParameter() > cost){
					int num1 = (int)Math.ceil((double)this.getParameter() / buff.getQ_effect_value());
					this.setParameter(this.getParameter() - cost);
					int num2 = (int)Math.ceil((double)this.getParameter() / buff.getQ_effect_value());
					if(num1!=num2 && num2>0){
						this.setOverlay(num2);
						ManagerPool.buffManager.sendChangeBuffMessage(source, source, this);
					}
					source.setHp(maxHp);
					result = 1;
					min = (int) ((long)maxHp * 2000/ Global.MAX_PROBABILITY);
					if(cost >= min){
						log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + cost + ",因为自动恢复生命池");
					}
				}else{
					source.setHp(source.getHp() + this.getParameter());
					min = (int) ((long)maxHp * 2000/ Global.MAX_PROBABILITY);
					if(this.getParameter() >= min){
						log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + this.getParameter() + ",因为自动恢复生命池");
					}
					this.setParameter(0);
					result = 2;
				}
				if(source instanceof Player) ManagerPool.playerManager.onHpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onHpChange((Monster)source);
				return result;
				//19  每隔一段时间加满人物的内力，直至剩余容量用完
			case BuffType.FILLMP:
				if(source.isDie()) return 0;
				if(source instanceof Player){
					//战斗中不恢复
					if(PlayerState.FIGHT.compare(((Player) source).getState())){
						return 0;
					}
				}
				//禁止内力恢复
				if(FighterState.JINZHINEILIHUIFU.compare(source.getFightState())) return 0;
				maxMp = source.getMaxMp();
				if(source.getMp() >= maxMp) return 0;
				cost = maxMp - source.getMp();
				if(this.getParameter() > cost){
					int num1 = (int)Math.ceil((double)this.getParameter() / buff.getQ_effect_value());
					this.setParameter(this.getParameter() - cost);
					int num2 = (int)Math.ceil((double)this.getParameter() / buff.getQ_effect_value());
					if(num1!=num2 && num2>0){
						this.setOverlay(num2);
						ManagerPool.buffManager.sendChangeBuffMessage(source, source, this);
					}
					source.setMp(maxMp);
					result = 1;
				}else{
					source.setMp(source.getMp() + this.getParameter());
					this.setParameter(0);
					result = 2;
				}
				if(source instanceof Player) ManagerPool.playerManager.onMpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onMpChange((Monster)source);
				return result;
				//20  每隔一段时间加满人物的体力，直至剩余容量用完
			case BuffType.FILLSP:
				if(source.isDie()) return 0;
				if(source instanceof Player){
					//战斗中不恢复
					if(PlayerState.FIGHT.compare(((Player) source).getState())){
						return 0;
					}
				}
				//禁止体力恢复
				Map smap = ManagerPool.mapManager.getMap((Player)source);
				if(FighterState.JINZHITILIHUIFU.compare(source.getFightState()) || smap.getBanusesp() != 0) return 0;
				maxSp = source.getMaxSp();
				if(source.getSp() >= maxSp) return 0;
				cost = maxSp - source.getSp();
				if(this.getParameter() > cost){
					int num1 = (int)Math.ceil((double)this.getParameter() / buff.getQ_effect_value());
					this.setParameter(this.getParameter() - cost);
					int num2 = (int)Math.ceil((double)this.getParameter() / buff.getQ_effect_value());
					if(num1!=num2 && num2>0){
						this.setOverlay(num2);
						ManagerPool.buffManager.sendChangeBuffMessage(source, source, this);
					}
					source.setSp(maxSp);
					result = 1;
				}else{
					source.setSp(source.getSp() + this.getParameter());
					this.setParameter(0);
					result = 2;
				}
				if(source instanceof Player) ManagerPool.playerManager.onSpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onSpChange((Monster)source);
				return result;
				//36  有一定成功几率对目标施毒，被施毒后在一定时间内每隔一段时间均按比例减血
			case BuffType.POISON:
				if(FighterState.WUDI.compare(source.getFightState())) return 0;
				if(source.isDie()) return 0;
				maxHp = source.getMaxHp();
				hp = buff.getQ_effect_value() + this.getValue();
				hp += (long)maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				source.setHp(source.getHp() + hp);
				if(source.getHp() < 1) source.setHp(1);
				min = (int) ((long)maxHp * 2000/ Global.MAX_PROBABILITY);
				if(hp >= min){
					log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + hp + ",因为毒药");
				}
				if(source instanceof Player) ManagerPool.playerManager.onHpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onHpChange((Monster)source);
				return 1;
				//63  触发对所选目标造成额外N点伤害
			case BuffType.DAMAGE:
				int damage = buff.getQ_effect_value() + this.getValue();
				damage += (long)damage * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				
				ManagerPool.fightManager.damage(source, target, damage, 0);
				return 1;
				//64  触发减少额外N点伤害
			case BuffType.REDUCE:
				int reduce = buff.getQ_effect_value() + this.getValue();
				reduce += (long)reduce * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				source.setReduce(reduce);
				return 1;
				//72  打坐BUFF
			case BuffType.SITINMEDITATION:
				return PlayerDaZuoManager.getInstacne().buffAction((Player)source);
				//TODO 添加打坐增加真气
				//return 1;
				//89 boss buff（玩家减血,死亡）
			case BuffType.DAMAGEANDDIE:
				if(source.isDie()) return 0;
				if(!(source instanceof Player)) return 0;
				
				Map map = ManagerPool.mapManager.getMap((Player)source);
				if(map==null) return 0;
				
				List<Monster> monsters = ManagerPool.monsterManager.getMonsterByModel(map, 14006);
				Monster monster = null;
				if(monsters.size() > 0){
					monster = monsters.get(0);
				}
				if(monster==null) return 0;
				
				maxHp = source.getMaxHp();
				hp = buff.getQ_effect_value() + this.getValue();
				hp += (long)maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				source.setHp(source.getHp() + hp);
				if(source.getHp() < 0) source.setHp(0);
				min = (int) ((long)maxHp * 2000/ Global.MAX_PROBABILITY);
				if(hp >= min){
					log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + hp + ",因为血崩buff");
				}
				if(source.getHp() < 1) {
					ManagerPool.playerManager.onHpChange((Player)source);
					ManagerPool.playerManager.die((Player)source, monster);
					ManagerPool.buffManager.addBuff(monster, monster, 24009, 0, 0, 0);
					return 3;
				}else{
					ManagerPool.playerManager.onHpChange((Player)source);
					ManagerPool.buffManager.addBuff(monster, monster, 24009, 0, 0, 0);
					return 1;
				}
				
		}
		return 0;
	}

	@Override
	public int remove(Fighter source) {
		return 0;
	}

}
