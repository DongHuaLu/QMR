package com.game.fight.structs;

import java.util.List;

import com.game.buff.structs.Buff;
import com.game.map.structs.IMapObject;
import com.game.player.structs.Player;
import com.game.skill.structs.Skill;
import com.game.structs.Position;

/**
 * 战斗实体接口
 * @author heyang szy_heyang@163.com
 *
 */
public interface Fighter extends IMapObject {

	/**
	 * 获得唯一标识
	 * @return
	 */
	public long getId();
	
	/**
	 * 获得名字
	 * @return
	 */
	public String getName();
	
	/**
	 * 获取当前区
	 */
	public int getServerId();
	
	/**
	 * 获得当前线
	 * @return
	 */
	public int getLine(); 
	
	/**
	 * 获得当前地图
	 * @return
	 */
	public int getMap(); 
	
	/**
	 * 获得当前坐标
	 * @return
	 */
	public Position getPosition(); 
	
	
	/**
	 * 获得当前HP
	 * @return
	 */
	public int getHp();
	
	/**
	 * 设置当前HP
	 * @return
	 */
	public void setHp(int hp);
	
	/**
	 * 获得最大HP
	 * @return
	 */
	public int getMaxHp();
	
	/**
	 * 获得当前MP
	 * @return
	 */
	public int getMp();
	
	/**
	 * 设置当前MP
	 * @return
	 */
	public void setMp(int mp);
	
	/**
	 * 获得最大MP
	 * @return
	 */
	public int getMaxMp();
	
	/**
	 * 获得当前SP
	 * @return
	 */
	public int getSp();
	
	/**
	 * 设置当前SP
	 * @return
	 */
	public void setSp(int sp);
	
	/**
	 * 获得最大SP
	 * @return
	 */
	public int getMaxSp();
	
	/**
	 * 获得攻击力
	 * @return
	 */
	public int getAttack();
	
	/**
	 * 获得攻击力
	 * @return
	 */
	public int getAttack(Skill skill);
	
	/**
	 * 获得防御力
	 * @return
	 */
	public int getDefense();
	
	/**
	 * 获得攻击速度
	 * @return
	 */
	public int getAttackSpeed();
	
	/**
	 * 获得命中
	 * @return
	 */
	public int getLuck();
	
	/**
	 * 获得暴击
	 * @return
	 */
	public int getCrit();
	
	/**
	 * 获得闪避
	 * @return
	 */
	public int getDodge();
	
	/**
	 * 获取BUFF
	 * @return
	 */
	public List<Buff> getBuffs();
	
	/**
	 * 是否死亡
	 * @return
	 */
	public boolean isDie();
	
	/**
	 * 获取等级
	 * @return
	 */
	public int getLevel();
	
	/**
	 * 获取速度
	 * @return
	 */
	public int getSpeed();
	
	/**
	 * 获取战斗状态
	 * @return
	 */
	public int getFightState();
	
	/**
	 * 获取伤害减免
	 * @return
	 */
	public int getReduce();
	
	/**
	 * 设置伤害减免
	 * @return
	 */
	public void setReduce(int reduce);
	
	/**
	 * 增加战斗状态
	 * @param state
	 */
	public void addFightState(FighterState state);
	
	/**
	 * 移除战斗状态
	 * @param state
	 */
	public void removeFightState(FighterState state);
	
	/**
	 * 是否可以看到
	 */
	public boolean canSee(Player player);
}
