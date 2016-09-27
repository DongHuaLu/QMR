package com.game.monster.structs;

import com.game.fight.structs.Fighter;
import com.game.pool.MemoryObject;

/**
 * 仇恨度
 * @author heyang E-mail: szy_heyang@163.com
 *
 */
public class Hatred implements MemoryObject, Comparable<Hatred> {

	//仇恨度
	private int hatred;
	//仇恨对象
	private Fighter target;
	//第一次攻击时间
	private long firstAttack;
	//最后一次攻击时间
	private long lastAttack;
	
	public int getHatred() {
		return hatred;
	}
	
	public void setHatred(int hatred) {
		this.hatred = hatred;
	}
	
	public Fighter getTarget() {
		return target;
	}
	
	public void setTarget(Fighter target) {
		this.target = target;
	}
	
	public long getFirstAttack() {
		return firstAttack;
	}
	
	public void setFirstAttack(long firstAttack) {
		this.firstAttack = firstAttack;
	}
	
	public long getLastAttack() {
		return lastAttack;
	}
	
	public void setLastAttack(long lastAttack) {
		this.lastAttack = lastAttack;
	}
	
	@Override
	public int compareTo(Hatred o) {
		return o.getHatred() - getHatred();
	}
	
	@Override
	public void release() {
		this.firstAttack = 0;
		this.hatred = 0;
		this.lastAttack = 0;
		this.target = null;
	}
	
}
