package com.game.map.message;

import com.game.map.bean.EffectInfo;
import com.game.map.bean.PetInfo;
import com.game.map.bean.MonsterInfo;
import com.game.map.bean.PlayerInfo;
import com.game.map.bean.NpcInfo;
import com.game.map.bean.DropGoodsInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围情况变化消息
 */
public class ResRoundObjectsMessage extends Message{

	//周围玩家信息
	private List<PlayerInfo> player = new ArrayList<PlayerInfo>();
	//消失角色列表
	private List<Long> playerIds = new ArrayList<Long>();
	//周围怪物信息
	private List<MonsterInfo> monster = new ArrayList<MonsterInfo>();
	//消失怪物列表
	private List<Long> monstersIds = new ArrayList<Long>();
	//周围掉落物品信息
	private List<DropGoodsInfo> goods = new ArrayList<DropGoodsInfo>();
	//消失物品列表
	private List<Long> goodsIds = new ArrayList<Long>();
	//周围宠物信息
	private List<PetInfo> pets = new ArrayList<PetInfo>();
	//消失物品列表
	private List<Long> petIds = new ArrayList<Long>();
	//NPC信息
	private List<NpcInfo> npcs = new ArrayList<NpcInfo>();
	//消失的
	private List<Long> npcid = new ArrayList<Long>();
	//周围效果信息
	private List<EffectInfo> Effect = new ArrayList<EffectInfo>();
	//消失的效果信息
	private List<Long> effectid = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//周围玩家信息
		writeShort(buf, player.size());
		for (int i = 0; i < player.size(); i++) {
			writeBean(buf, player.get(i));
		}
		//消失角色列表
		writeShort(buf, playerIds.size());
		for (int i = 0; i < playerIds.size(); i++) {
			writeLong(buf, playerIds.get(i));
		}
		//周围怪物信息
		writeShort(buf, monster.size());
		for (int i = 0; i < monster.size(); i++) {
			writeBean(buf, monster.get(i));
		}
		//消失怪物列表
		writeShort(buf, monstersIds.size());
		for (int i = 0; i < monstersIds.size(); i++) {
			writeLong(buf, monstersIds.get(i));
		}
		//周围掉落物品信息
		writeShort(buf, goods.size());
		for (int i = 0; i < goods.size(); i++) {
			writeBean(buf, goods.get(i));
		}
		//消失物品列表
		writeShort(buf, goodsIds.size());
		for (int i = 0; i < goodsIds.size(); i++) {
			writeLong(buf, goodsIds.get(i));
		}
		//周围宠物信息
		writeShort(buf, pets.size());
		for (int i = 0; i < pets.size(); i++) {
			writeBean(buf, pets.get(i));
		}
		//消失物品列表
		writeShort(buf, petIds.size());
		for (int i = 0; i < petIds.size(); i++) {
			writeLong(buf, petIds.get(i));
		}
		//NPC信息
		writeShort(buf, npcs.size());
		for (int i = 0; i < npcs.size(); i++) {
			writeBean(buf, npcs.get(i));
		}
		//消失的
		writeShort(buf, npcid.size());
		for (int i = 0; i < npcid.size(); i++) {
			writeLong(buf, npcid.get(i));
		}
		//周围效果信息
		writeShort(buf, Effect.size());
		for (int i = 0; i < Effect.size(); i++) {
			writeBean(buf, Effect.get(i));
		}
		//消失的效果信息
		writeShort(buf, effectid.size());
		for (int i = 0; i < effectid.size(); i++) {
			writeLong(buf, effectid.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//周围玩家信息
		int player_length = readShort(buf);
		for (int i = 0; i < player_length; i++) {
			player.add((PlayerInfo)readBean(buf, PlayerInfo.class));
		}
		//消失角色列表
		int playerIds_length = readShort(buf);
		for (int i = 0; i < playerIds_length; i++) {
			playerIds.add(readLong(buf));
		}
		//周围怪物信息
		int monster_length = readShort(buf);
		for (int i = 0; i < monster_length; i++) {
			monster.add((MonsterInfo)readBean(buf, MonsterInfo.class));
		}
		//消失怪物列表
		int monstersIds_length = readShort(buf);
		for (int i = 0; i < monstersIds_length; i++) {
			monstersIds.add(readLong(buf));
		}
		//周围掉落物品信息
		int goods_length = readShort(buf);
		for (int i = 0; i < goods_length; i++) {
			goods.add((DropGoodsInfo)readBean(buf, DropGoodsInfo.class));
		}
		//消失物品列表
		int goodsIds_length = readShort(buf);
		for (int i = 0; i < goodsIds_length; i++) {
			goodsIds.add(readLong(buf));
		}
		//周围宠物信息
		int pets_length = readShort(buf);
		for (int i = 0; i < pets_length; i++) {
			pets.add((PetInfo)readBean(buf, PetInfo.class));
		}
		//消失物品列表
		int petIds_length = readShort(buf);
		for (int i = 0; i < petIds_length; i++) {
			petIds.add(readLong(buf));
		}
		//NPC信息
		int npcs_length = readShort(buf);
		for (int i = 0; i < npcs_length; i++) {
			npcs.add((NpcInfo)readBean(buf, NpcInfo.class));
		}
		//消失的
		int npcid_length = readShort(buf);
		for (int i = 0; i < npcid_length; i++) {
			npcid.add(readLong(buf));
		}
		//周围效果信息
		int Effect_length = readShort(buf);
		for (int i = 0; i < Effect_length; i++) {
			Effect.add((EffectInfo)readBean(buf, EffectInfo.class));
		}
		//消失的效果信息
		int effectid_length = readShort(buf);
		for (int i = 0; i < effectid_length; i++) {
			effectid.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 周围玩家信息
	 * @return 
	 */
	public List<PlayerInfo> getPlayer(){
		return player;
	}
	
	/**
	 * set 周围玩家信息
	 */
	public void setPlayer(List<PlayerInfo> player){
		this.player = player;
	}
	
	/**
	 * get 消失角色列表
	 * @return 
	 */
	public List<Long> getPlayerIds(){
		return playerIds;
	}
	
	/**
	 * set 消失角色列表
	 */
	public void setPlayerIds(List<Long> playerIds){
		this.playerIds = playerIds;
	}
	
	/**
	 * get 周围怪物信息
	 * @return 
	 */
	public List<MonsterInfo> getMonster(){
		return monster;
	}
	
	/**
	 * set 周围怪物信息
	 */
	public void setMonster(List<MonsterInfo> monster){
		this.monster = monster;
	}
	
	/**
	 * get 消失怪物列表
	 * @return 
	 */
	public List<Long> getMonstersIds(){
		return monstersIds;
	}
	
	/**
	 * set 消失怪物列表
	 */
	public void setMonstersIds(List<Long> monstersIds){
		this.monstersIds = monstersIds;
	}
	
	/**
	 * get 周围掉落物品信息
	 * @return 
	 */
	public List<DropGoodsInfo> getGoods(){
		return goods;
	}
	
	/**
	 * set 周围掉落物品信息
	 */
	public void setGoods(List<DropGoodsInfo> goods){
		this.goods = goods;
	}
	
	/**
	 * get 消失物品列表
	 * @return 
	 */
	public List<Long> getGoodsIds(){
		return goodsIds;
	}
	
	/**
	 * set 消失物品列表
	 */
	public void setGoodsIds(List<Long> goodsIds){
		this.goodsIds = goodsIds;
	}
	
	/**
	 * get 周围宠物信息
	 * @return 
	 */
	public List<PetInfo> getPets(){
		return pets;
	}
	
	/**
	 * set 周围宠物信息
	 */
	public void setPets(List<PetInfo> pets){
		this.pets = pets;
	}
	
	/**
	 * get 消失物品列表
	 * @return 
	 */
	public List<Long> getPetIds(){
		return petIds;
	}
	
	/**
	 * set 消失物品列表
	 */
	public void setPetIds(List<Long> petIds){
		this.petIds = petIds;
	}
	
	/**
	 * get NPC信息
	 * @return 
	 */
	public List<NpcInfo> getNpcs(){
		return npcs;
	}
	
	/**
	 * set NPC信息
	 */
	public void setNpcs(List<NpcInfo> npcs){
		this.npcs = npcs;
	}
	
	/**
	 * get 消失的
	 * @return 
	 */
	public List<Long> getNpcid(){
		return npcid;
	}
	
	/**
	 * set 消失的
	 */
	public void setNpcid(List<Long> npcid){
		this.npcid = npcid;
	}
	
	/**
	 * get 周围效果信息
	 * @return 
	 */
	public List<EffectInfo> getEffect(){
		return Effect;
	}
	
	/**
	 * set 周围效果信息
	 */
	public void setEffect(List<EffectInfo> Effect){
		this.Effect = Effect;
	}
	
	/**
	 * get 消失的效果信息
	 * @return 
	 */
	public List<Long> getEffectid(){
		return effectid;
	}
	
	/**
	 * set 消失的效果信息
	 */
	public void setEffectid(List<Long> effectid){
		this.effectid = effectid;
	}
	
	
	@Override
	public int getId() {
		return 101125;
	}

	@Override
	public String getQueue() {
		return null;
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//周围玩家信息
		buf.append("player:{");
		for (int i = 0; i < player.size(); i++) {
			buf.append(player.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//消失角色列表
		buf.append("playerIds:{");
		for (int i = 0; i < playerIds.size(); i++) {
			buf.append(playerIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//周围怪物信息
		buf.append("monster:{");
		for (int i = 0; i < monster.size(); i++) {
			buf.append(monster.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//消失怪物列表
		buf.append("monstersIds:{");
		for (int i = 0; i < monstersIds.size(); i++) {
			buf.append(monstersIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//周围掉落物品信息
		buf.append("goods:{");
		for (int i = 0; i < goods.size(); i++) {
			buf.append(goods.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//消失物品列表
		buf.append("goodsIds:{");
		for (int i = 0; i < goodsIds.size(); i++) {
			buf.append(goodsIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//周围宠物信息
		buf.append("pets:{");
		for (int i = 0; i < pets.size(); i++) {
			buf.append(pets.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//消失物品列表
		buf.append("petIds:{");
		for (int i = 0; i < petIds.size(); i++) {
			buf.append(petIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//NPC信息
		buf.append("npcs:{");
		for (int i = 0; i < npcs.size(); i++) {
			buf.append(npcs.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//消失的
		buf.append("npcid:{");
		for (int i = 0; i < npcid.size(); i++) {
			buf.append(npcid.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//周围效果信息
		buf.append("Effect:{");
		for (int i = 0; i < Effect.size(); i++) {
			buf.append(Effect.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//消失的效果信息
		buf.append("effectid:{");
		for (int i = 0; i < effectid.size(); i++) {
			buf.append(effectid.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}