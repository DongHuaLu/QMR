package com.game.fight.structs;

import com.game.manager.ManagerPool;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;

/**
 * 战斗者信息
 * @author heyang
 *
 */
public class FighterInfo {
	//生命
	private int hp;
	//最大生命
	private int maxHp;
	//内力
	private int mp;
	//最大内力
	private int maxMp;
	//体力
	private int sp;
	//最大体力
	private int maxSp;
	//速度
	private int speed;
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getMaxMp() {
		return maxMp;
	}
	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}
	public int getSp() {
		return sp;
	}
	public void setSp(int sp) {
		this.sp = sp;
	}
	public int getMaxSp() {
		return maxSp;
	}
	public void setMaxSp(int maxSp) {
		this.maxSp = maxSp;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * 记录fighter信息
	 * @param fighter
	 */
	public void copyFighter(Fighter fighter){
		this.setHp(fighter.getHp());
		this.setMaxHp(fighter.getMaxHp());
		this.setMp(fighter.getMp());
		this.setMaxMp(fighter.getMaxMp());
		this.setSp(fighter.getSp());
		this.setMaxSp(fighter.getMaxSp());
		this.setSpeed(fighter.getSpeed());
	}
	
	/**
	 * 比较属性，广播属性变化
	 * @param fighter
	 */
	public void compareFighter(Fighter fighter){
		if(fighter instanceof Player)
			comparePlayer((Player)fighter);
		else if(fighter instanceof Monster)
			compareMonster((Monster)fighter);
		else if(fighter instanceof Pet)
			comparePet((Pet)fighter);
	}
	
	private void comparePlayer(Player player){
		//生命
		if(this.getHp() != player.getHp()){
			ManagerPool.playerManager.onHpChange(player);
		}
		
		//内力
		if(this.getMp() != player.getMp()){
			ManagerPool.playerManager.onMpChange(player);
		}
		
		//体力
		if(this.getSp() != player.getSp()){
			ManagerPool.playerManager.onSpChange(player);
		}

	}
	
	private void compareMonster(Monster monster){
		boolean broadcastHp = false;
		boolean broadcastMp = false;
		boolean broadcastSp = false;
		
		//最大生命
		int maxhp = monster.getMaxHp();
		if(this.getMaxHp() != maxhp){
			ManagerPool.monsterManager.onMaxHpChange(monster, maxhp);
			broadcastHp = true;
		}
		
		//最大内力
		int maxmp = monster.getMaxMp();
		if(this.getMaxMp() != maxmp){
			ManagerPool.monsterManager.onMaxMpChange(monster, maxmp);
			broadcastMp = true;
		}
		
		//最大体力
		int maxsp = monster.getMaxSp();
		if(this.getMaxSp() != maxsp){
			ManagerPool.monsterManager.onMaxSpChange(monster, maxsp);
			broadcastSp = true;
		}
		
		//生命
		if(this.getHp() != monster.getHp() && !broadcastHp){
			ManagerPool.monsterManager.onHpChange(monster);
		}
		
		//内力
		if(this.getMp() != monster.getMp() && !broadcastMp){
			ManagerPool.monsterManager.onMpChange(monster);
		}
		
		//体力
		if(this.getSp() != monster.getSp() && !broadcastSp){
			ManagerPool.monsterManager.onSpChange(monster);
		}
		
		//速度
		int speed = monster.getSpeed();
		if(this.getSpeed() != speed){
			ManagerPool.monsterManager.onSpeedChange(monster, speed);
		}
	}
	
	private void comparePet(Pet pet){
		
//		//最大生命
//		int maxhp = pet.getMaxHp();
//		if(this.getMaxHp() != maxhp){
//			ManagerPool.petInfoManager.onMaxHpChange(pet);
//		}
//		
//		//最大内力
//		int maxmp = pet.getMaxMp();
//		if(this.getMaxMp() != maxmp){
//			ManagerPool.petInfoManager.onMaxMpChange(pet);
//		}
//		
//		//最大体力
//		int maxsp = pet.getMaxSp();
//		if(this.getMaxSp() != maxsp){
//			ManagerPool.petInfoManager.onMaxSpChange(pet);
//		}
		
		//生命
		if(this.getHp() != pet.getHp()){
			ManagerPool.petInfoManager.onHpChange(pet);
		}
		
		//内力
		if(this.getMp() != pet.getMp()){
			ManagerPool.petInfoManager.onMpChange(pet);
		}
		
		//体力
		if(this.getSp() != pet.getSp()){
			ManagerPool.petInfoManager.onSpChange(pet);
		}
		
		//速度
		int speed = pet.getSpeed();
		if(this.getSpeed() != speed){
			ManagerPool.petInfoManager.onSpeedChange(pet);
		}
	}
}
