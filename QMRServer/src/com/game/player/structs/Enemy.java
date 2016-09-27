package com.game.player.structs;

/**
 * 敌人信息
 * @author heyang
 *
 */
public class Enemy {
	//敌人id
	private long enemyId;
	//敌人最后一次攻击时间
	private long lastTime;
	
	public long getEnemyId() {
		return enemyId;
	}
	public void setEnemyId(long enemyId) {
		this.enemyId = enemyId;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	public String toString(){
		return "[enemyId:" + enemyId + ",lastTime:" + lastTime + "]";
	}
}
