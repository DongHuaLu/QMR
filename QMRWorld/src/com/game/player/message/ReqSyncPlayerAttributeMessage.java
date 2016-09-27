package com.game.player.message;

import com.game.player.bean.PlayerAttributeItem;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家属性消息
 */
public class ReqSyncPlayerAttributeMessage extends Message{

	//角色id
	private long playerId;
	
	//角色经验
	private long exp;
	
	//角色真气
	private int zhenqi;
	
	//头象ID
	private int avatar;
	
	//当前章节
	private int chapter;
	
	//战场声望
	private int prestige;
	
	//装备信息
	private String equip;
	
	//宝石信息
	private String gem;
	
	//技能信息
	private String skills;
	
	//属性列表
	private List<PlayerAttributeItem> attributes = new ArrayList<PlayerAttributeItem>();
	//战斗力
	private int fightPower;
	
	//坐骑装备
	private String horseEquip;
	
	//坐骑技能
	private String horseSkill;
	
	//宠物信息
	private String pets;
	
	//王城BUFFid
	private int kingcitybuffid;
	
	//VIPid
	private int vipid;
	
	//消耗元宝
	private int costgold;
	
	//弓箭信息
	private String arrowinfo;
	
	//骑兵技能列表
	private String horseWeaponSkill;
	
	//暗器技能列表
	private String hiddenWeaponSkill;
	
	//境界等级
	private int realmlevel;
	
	//境界强化等级
	private int realmintensifylevel;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色经验
		writeLong(buf, this.exp);
		//角色真气
		writeInt(buf, this.zhenqi);
		//头象ID
		writeInt(buf, this.avatar);
		//当前章节
		writeInt(buf, this.chapter);
		//战场声望
		writeInt(buf, this.prestige);
		//装备信息
		writeString(buf, this.equip);
		//宝石信息
		writeString(buf, this.gem);
		//技能信息
		writeString(buf, this.skills);
		//属性列表
		writeShort(buf, attributes.size());
		for (int i = 0; i < attributes.size(); i++) {
			writeBean(buf, attributes.get(i));
		}
		//战斗力
		writeInt(buf, this.fightPower);
		//坐骑装备
		writeString(buf, this.horseEquip);
		//坐骑技能
		writeString(buf, this.horseSkill);
		//宠物信息
		writeString(buf, this.pets);
		//王城BUFFid
		writeInt(buf, this.kingcitybuffid);
		//VIPid
		writeInt(buf, this.vipid);
		//消耗元宝
		writeInt(buf, this.costgold);
		//弓箭信息
		writeString(buf, this.arrowinfo);
		//骑兵技能列表
		writeString(buf, this.horseWeaponSkill);
		//暗器技能列表
		writeString(buf, this.hiddenWeaponSkill);
		//境界等级
		writeInt(buf, this.realmlevel);
		//境界强化等级
		writeInt(buf, this.realmintensifylevel);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色经验
		this.exp = readLong(buf);
		//角色真气
		this.zhenqi = readInt(buf);
		//头象ID
		this.avatar = readInt(buf);
		//当前章节
		this.chapter = readInt(buf);
		//战场声望
		this.prestige = readInt(buf);
		//装备信息
		this.equip = readString(buf);
		//宝石信息
		this.gem = readString(buf);
		//技能信息
		this.skills = readString(buf);
		//属性列表
		int attributes_length = readShort(buf);
		for (int i = 0; i < attributes_length; i++) {
			attributes.add((PlayerAttributeItem)readBean(buf, PlayerAttributeItem.class));
		}
		//战斗力
		this.fightPower = readInt(buf);
		//坐骑装备
		this.horseEquip = readString(buf);
		//坐骑技能
		this.horseSkill = readString(buf);
		//宠物信息
		this.pets = readString(buf);
		//王城BUFFid
		this.kingcitybuffid = readInt(buf);
		//VIPid
		this.vipid = readInt(buf);
		//消耗元宝
		this.costgold = readInt(buf);
		//弓箭信息
		this.arrowinfo = readString(buf);
		//骑兵技能列表
		this.horseWeaponSkill = readString(buf);
		//暗器技能列表
		this.hiddenWeaponSkill = readString(buf);
		//境界等级
		this.realmlevel = readInt(buf);
		//境界强化等级
		this.realmintensifylevel = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 角色经验
	 * @return 
	 */
	public long getExp(){
		return exp;
	}
	
	/**
	 * set 角色经验
	 */
	public void setExp(long exp){
		this.exp = exp;
	}
	
	/**
	 * get 角色真气
	 * @return 
	 */
	public int getZhenqi(){
		return zhenqi;
	}
	
	/**
	 * set 角色真气
	 */
	public void setZhenqi(int zhenqi){
		this.zhenqi = zhenqi;
	}
	
	/**
	 * get 头象ID
	 * @return 
	 */
	public int getAvatar(){
		return avatar;
	}
	
	/**
	 * set 头象ID
	 */
	public void setAvatar(int avatar){
		this.avatar = avatar;
	}
	
	/**
	 * get 当前章节
	 * @return 
	 */
	public int getChapter(){
		return chapter;
	}
	
	/**
	 * set 当前章节
	 */
	public void setChapter(int chapter){
		this.chapter = chapter;
	}
	
	/**
	 * get 战场声望
	 * @return 
	 */
	public int getPrestige(){
		return prestige;
	}
	
	/**
	 * set 战场声望
	 */
	public void setPrestige(int prestige){
		this.prestige = prestige;
	}
	
	/**
	 * get 装备信息
	 * @return 
	 */
	public String getEquip(){
		return equip;
	}
	
	/**
	 * set 装备信息
	 */
	public void setEquip(String equip){
		this.equip = equip;
	}
	
	/**
	 * get 宝石信息
	 * @return 
	 */
	public String getGem(){
		return gem;
	}
	
	/**
	 * set 宝石信息
	 */
	public void setGem(String gem){
		this.gem = gem;
	}
	
	/**
	 * get 技能信息
	 * @return 
	 */
	public String getSkills(){
		return skills;
	}
	
	/**
	 * set 技能信息
	 */
	public void setSkills(String skills){
		this.skills = skills;
	}
	
	/**
	 * get 属性列表
	 * @return 
	 */
	public List<PlayerAttributeItem> getAttributes(){
		return attributes;
	}
	
	/**
	 * set 属性列表
	 */
	public void setAttributes(List<PlayerAttributeItem> attributes){
		this.attributes = attributes;
	}
	
	/**
	 * get 战斗力
	 * @return 
	 */
	public int getFightPower(){
		return fightPower;
	}
	
	/**
	 * set 战斗力
	 */
	public void setFightPower(int fightPower){
		this.fightPower = fightPower;
	}
	
	/**
	 * get 坐骑装备
	 * @return 
	 */
	public String getHorseEquip(){
		return horseEquip;
	}
	
	/**
	 * set 坐骑装备
	 */
	public void setHorseEquip(String horseEquip){
		this.horseEquip = horseEquip;
	}
	
	/**
	 * get 坐骑技能
	 * @return 
	 */
	public String getHorseSkill(){
		return horseSkill;
	}
	
	/**
	 * set 坐骑技能
	 */
	public void setHorseSkill(String horseSkill){
		this.horseSkill = horseSkill;
	}
	
	/**
	 * get 宠物信息
	 * @return 
	 */
	public String getPets(){
		return pets;
	}
	
	/**
	 * set 宠物信息
	 */
	public void setPets(String pets){
		this.pets = pets;
	}
	
	/**
	 * get 王城BUFFid
	 * @return 
	 */
	public int getKingcitybuffid(){
		return kingcitybuffid;
	}
	
	/**
	 * set 王城BUFFid
	 */
	public void setKingcitybuffid(int kingcitybuffid){
		this.kingcitybuffid = kingcitybuffid;
	}
	
	/**
	 * get VIPid
	 * @return 
	 */
	public int getVipid(){
		return vipid;
	}
	
	/**
	 * set VIPid
	 */
	public void setVipid(int vipid){
		this.vipid = vipid;
	}
	
	/**
	 * get 消耗元宝
	 * @return 
	 */
	public int getCostgold(){
		return costgold;
	}
	
	/**
	 * set 消耗元宝
	 */
	public void setCostgold(int costgold){
		this.costgold = costgold;
	}
	
	/**
	 * get 弓箭信息
	 * @return 
	 */
	public String getArrowinfo(){
		return arrowinfo;
	}
	
	/**
	 * set 弓箭信息
	 */
	public void setArrowinfo(String arrowinfo){
		this.arrowinfo = arrowinfo;
	}
	
	/**
	 * get 骑兵技能列表
	 * @return 
	 */
	public String getHorseWeaponSkill(){
		return horseWeaponSkill;
	}
	
	/**
	 * set 骑兵技能列表
	 */
	public void setHorseWeaponSkill(String horseWeaponSkill){
		this.horseWeaponSkill = horseWeaponSkill;
	}
	
	/**
	 * get 暗器技能列表
	 * @return 
	 */
	public String getHiddenWeaponSkill(){
		return hiddenWeaponSkill;
	}
	
	/**
	 * set 暗器技能列表
	 */
	public void setHiddenWeaponSkill(String hiddenWeaponSkill){
		this.hiddenWeaponSkill = hiddenWeaponSkill;
	}
	
	/**
	 * get 境界等级
	 * @return 
	 */
	public int getRealmlevel(){
		return realmlevel;
	}
	
	/**
	 * set 境界等级
	 */
	public void setRealmlevel(int realmlevel){
		this.realmlevel = realmlevel;
	}
	
	/**
	 * get 境界强化等级
	 * @return 
	 */
	public int getRealmintensifylevel(){
		return realmintensifylevel;
	}
	
	/**
	 * set 境界强化等级
	 */
	public void setRealmintensifylevel(int realmintensifylevel){
		this.realmintensifylevel = realmintensifylevel;
	}
	
	
	@Override
	public int getId() {
		return 103327;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//角色经验
		buf.append("exp:" + exp +",");
		//角色真气
		buf.append("zhenqi:" + zhenqi +",");
		//头象ID
		buf.append("avatar:" + avatar +",");
		//当前章节
		buf.append("chapter:" + chapter +",");
		//战场声望
		buf.append("prestige:" + prestige +",");
		//装备信息
		if(this.equip!=null) buf.append("equip:" + equip.toString() +",");
		//宝石信息
		if(this.gem!=null) buf.append("gem:" + gem.toString() +",");
		//技能信息
		if(this.skills!=null) buf.append("skills:" + skills.toString() +",");
		//属性列表
		buf.append("attributes:{");
		for (int i = 0; i < attributes.size(); i++) {
			buf.append(attributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//战斗力
		buf.append("fightPower:" + fightPower +",");
		//坐骑装备
		if(this.horseEquip!=null) buf.append("horseEquip:" + horseEquip.toString() +",");
		//坐骑技能
		if(this.horseSkill!=null) buf.append("horseSkill:" + horseSkill.toString() +",");
		//宠物信息
		if(this.pets!=null) buf.append("pets:" + pets.toString() +",");
		//王城BUFFid
		buf.append("kingcitybuffid:" + kingcitybuffid +",");
		//VIPid
		buf.append("vipid:" + vipid +",");
		//消耗元宝
		buf.append("costgold:" + costgold +",");
		//弓箭信息
		if(this.arrowinfo!=null) buf.append("arrowinfo:" + arrowinfo.toString() +",");
		//骑兵技能列表
		if(this.horseWeaponSkill!=null) buf.append("horseWeaponSkill:" + horseWeaponSkill.toString() +",");
		//暗器技能列表
		if(this.hiddenWeaponSkill!=null) buf.append("hiddenWeaponSkill:" + hiddenWeaponSkill.toString() +",");
		//境界等级
		buf.append("realmlevel:" + realmlevel +",");
		//境界强化等级
		buf.append("realmintensifylevel:" + realmintensifylevel +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}