package com.game.map.structs;

import java.util.HashMap;
import java.util.HashSet;

import com.game.drop.structs.MapDropInfo;
import com.game.monster.structs.Monster;
import com.game.npc.struts.NPC;
import com.game.object.GameObject;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;

/**
 * 区域信息
 * @author heyang
 *
 */
public class Area extends GameObject {

	private static final long serialVersionUID = -3661645672123039668L;
	//玩家列表
	private HashSet<Player> players = new HashSet<Player>();
	//怪物列表
	private HashMap<Long, Monster> monsters = new HashMap<Long, Monster>();
	//地上物品列表
	private HashMap<Long, MapDropInfo> dropGoods = new HashMap<Long, MapDropInfo>();
	//NPC列表
	private HashMap<Long, NPC> npcs=new HashMap<Long, NPC>();
	//宠物列表
	private HashSet<Pet> pets=new HashSet<Pet>();
	//场景特效
	private HashMap<Long, Effect> effects=new HashMap<Long, Effect>();
	
	public HashSet<Player> getPlayers() {
		return players;
	}

	public void setPlayers(HashSet<Player> players) {
		this.players = players;
	}

	public HashMap<Long, Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(HashMap<Long, Monster> monsters) {
		this.monsters = monsters;
	}

	
	public HashMap<Long, MapDropInfo> getDropGoods() {
		return dropGoods;
	}

	public void setDropGoods(HashMap<Long, MapDropInfo> dropGoods) {
		this.dropGoods = dropGoods;
	}
	

	public HashSet<Pet> getPets() {
		return pets;
	}

	public void setPets(HashSet<Pet> pets) {
		this.pets = pets;
	}

	

	public HashMap<Long, NPC> getNpcs() {
		return npcs;
	}

	public void setNpcs(HashMap<Long, NPC> npcs) {
		this.npcs = npcs;
	}
	
	public HashMap<Long, Effect> getEffects() {
		return effects;
	}

	public void setEffects(HashMap<Long, Effect> effects) {
		this.effects = effects;
	}

	/**
	 * 获取玩家数量
	 * @return
	 */
	public int getPlayerNumber(){
		return players.size();
	}

	
}
