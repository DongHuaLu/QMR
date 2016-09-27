package com.game.backend.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 向世界服返回玩家实时详细信息消息
 */
public class ResPlayerInfoToWorldMessage extends Message{

	//角色Id
	private long personId;
	
	//角色名字
	private String name;
	
	//角色性别 1-男 2-女
	private int sex;
	
	//角色等级
	private int level;
	
	//角色所在地图
	private int mapId;
	
	//角色所在X
	private short x;
	
	//角色所在Y
	private short y;
	
	//角色武功境界
	private byte skill;
	
	//角色状态
	private int state;
	
	//角色PK状态
	private int pkState;
	
	//角色经验
	private long exp;
	
	//角色真气
	private int zhenqi;
	
	//角色战场声望
	private int prestige;
	
	//背包格子数
	private short cellnum;
	
	//仓库格子数
	private short storecellnum;
	
	//铜币
	private int money;
	
	//元宝
	private int gold;
	
	//绑定元宝
	private int bindgold;
	
	//临时元宝
	private int tmpgold;
	
	//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
	private byte nonage;
	
	//角色当前坐骑
	private short horseid;
	
	//角色最高坐骑
	private short tophorseid;
	
	//装备部位全部宝石信息
	private List<com.game.gem.bean.PosGemInfo> posallgeminfo = new ArrayList<com.game.gem.bean.PosGemInfo>();
	//属性列表
	private List<com.game.player.bean.PlayerAttributeItem> attributes = new ArrayList<com.game.player.bean.PlayerAttributeItem>();
	//装备列表信息
	private List<com.game.equip.bean.EquipInfo> equips = new ArrayList<com.game.equip.bean.EquipInfo>();
	//物品信息列表
	private List<com.game.backpack.bean.ItemInfo> items = new ArrayList<com.game.backpack.bean.ItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//角色名字
		writeString(buf, this.name);
		//角色性别 1-男 2-女
		writeInt(buf, this.sex);
		//角色等级
		writeInt(buf, this.level);
		//角色所在地图
		writeInt(buf, this.mapId);
		//角色所在X
		writeShort(buf, this.x);
		//角色所在Y
		writeShort(buf, this.y);
		//角色武功境界
		writeByte(buf, this.skill);
		//角色状态
		writeInt(buf, this.state);
		//角色PK状态
		writeInt(buf, this.pkState);
		//角色经验
		writeLong(buf, this.exp);
		//角色真气
		writeInt(buf, this.zhenqi);
		//角色战场声望
		writeInt(buf, this.prestige);
		//背包格子数
		writeShort(buf, this.cellnum);
		//仓库格子数
		writeShort(buf, this.storecellnum);
		//铜币
		writeInt(buf, this.money);
		//元宝
		writeInt(buf, this.gold);
		//绑定元宝
		writeInt(buf, this.bindgold);
		//临时元宝
		writeInt(buf, this.tmpgold);
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		writeByte(buf, this.nonage);
		//角色当前坐骑
		writeShort(buf, this.horseid);
		//角色最高坐骑
		writeShort(buf, this.tophorseid);
		//装备部位全部宝石信息
		writeShort(buf, posallgeminfo.size());
		for (int i = 0; i < posallgeminfo.size(); i++) {
			writeBean(buf, posallgeminfo.get(i));
		}
		//属性列表
		writeShort(buf, attributes.size());
		for (int i = 0; i < attributes.size(); i++) {
			writeBean(buf, attributes.get(i));
		}
		//装备列表信息
		writeShort(buf, equips.size());
		for (int i = 0; i < equips.size(); i++) {
			writeBean(buf, equips.get(i));
		}
		//物品信息列表
		writeShort(buf, items.size());
		for (int i = 0; i < items.size(); i++) {
			writeBean(buf, items.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//角色名字
		this.name = readString(buf);
		//角色性别 1-男 2-女
		this.sex = readInt(buf);
		//角色等级
		this.level = readInt(buf);
		//角色所在地图
		this.mapId = readInt(buf);
		//角色所在X
		this.x = readShort(buf);
		//角色所在Y
		this.y = readShort(buf);
		//角色武功境界
		this.skill = readByte(buf);
		//角色状态
		this.state = readInt(buf);
		//角色PK状态
		this.pkState = readInt(buf);
		//角色经验
		this.exp = readLong(buf);
		//角色真气
		this.zhenqi = readInt(buf);
		//角色战场声望
		this.prestige = readInt(buf);
		//背包格子数
		this.cellnum = readShort(buf);
		//仓库格子数
		this.storecellnum = readShort(buf);
		//铜币
		this.money = readInt(buf);
		//元宝
		this.gold = readInt(buf);
		//绑定元宝
		this.bindgold = readInt(buf);
		//临时元宝
		this.tmpgold = readInt(buf);
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		this.nonage = readByte(buf);
		//角色当前坐骑
		this.horseid = readShort(buf);
		//角色最高坐骑
		this.tophorseid = readShort(buf);
		//装备部位全部宝石信息
		int posallgeminfo_length = readShort(buf);
		for (int i = 0; i < posallgeminfo_length; i++) {
			posallgeminfo.add((com.game.gem.bean.PosGemInfo)readBean(buf, com.game.gem.bean.PosGemInfo.class));
		}
		//属性列表
		int attributes_length = readShort(buf);
		for (int i = 0; i < attributes_length; i++) {
			attributes.add((com.game.player.bean.PlayerAttributeItem)readBean(buf, com.game.player.bean.PlayerAttributeItem.class));
		}
		//装备列表信息
		int equips_length = readShort(buf);
		for (int i = 0; i < equips_length; i++) {
			equips.add((com.game.equip.bean.EquipInfo)readBean(buf, com.game.equip.bean.EquipInfo.class));
		}
		//物品信息列表
		int items_length = readShort(buf);
		for (int i = 0; i < items_length; i++) {
			items.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPersonId(){
		return personId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPersonId(long personId){
		this.personId = personId;
	}
	
	/**
	 * get 角色名字
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 角色名字
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 角色性别 1-男 2-女
	 * @return 
	 */
	public int getSex(){
		return sex;
	}
	
	/**
	 * set 角色性别 1-男 2-女
	 */
	public void setSex(int sex){
		this.sex = sex;
	}
	
	/**
	 * get 角色等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 角色等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 角色所在地图
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 角色所在地图
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
	}
	
	/**
	 * get 角色所在X
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 角色所在X
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 角色所在Y
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 角色所在Y
	 */
	public void setY(short y){
		this.y = y;
	}
	
	/**
	 * get 角色武功境界
	 * @return 
	 */
	public byte getSkill(){
		return skill;
	}
	
	/**
	 * set 角色武功境界
	 */
	public void setSkill(byte skill){
		this.skill = skill;
	}
	
	/**
	 * get 角色状态
	 * @return 
	 */
	public int getState(){
		return state;
	}
	
	/**
	 * set 角色状态
	 */
	public void setState(int state){
		this.state = state;
	}
	
	/**
	 * get 角色PK状态
	 * @return 
	 */
	public int getPkState(){
		return pkState;
	}
	
	/**
	 * set 角色PK状态
	 */
	public void setPkState(int pkState){
		this.pkState = pkState;
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
	 * get 角色战场声望
	 * @return 
	 */
	public int getPrestige(){
		return prestige;
	}
	
	/**
	 * set 角色战场声望
	 */
	public void setPrestige(int prestige){
		this.prestige = prestige;
	}
	
	/**
	 * get 背包格子数
	 * @return 
	 */
	public short getCellnum(){
		return cellnum;
	}
	
	/**
	 * set 背包格子数
	 */
	public void setCellnum(short cellnum){
		this.cellnum = cellnum;
	}
	
	/**
	 * get 仓库格子数
	 * @return 
	 */
	public short getStorecellnum(){
		return storecellnum;
	}
	
	/**
	 * set 仓库格子数
	 */
	public void setStorecellnum(short storecellnum){
		this.storecellnum = storecellnum;
	}
	
	/**
	 * get 铜币
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 铜币
	 */
	public void setMoney(int money){
		this.money = money;
	}
	
	/**
	 * get 元宝
	 * @return 
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * set 元宝
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	/**
	 * get 绑定元宝
	 * @return 
	 */
	public int getBindgold(){
		return bindgold;
	}
	
	/**
	 * set 绑定元宝
	 */
	public void setBindgold(int bindgold){
		this.bindgold = bindgold;
	}
	
	/**
	 * get 临时元宝
	 * @return 
	 */
	public int getTmpgold(){
		return tmpgold;
	}
	
	/**
	 * set 临时元宝
	 */
	public void setTmpgold(int tmpgold){
		this.tmpgold = tmpgold;
	}
	
	/**
	 * get 玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
	 * @return 
	 */
	public byte getNonage(){
		return nonage;
	}
	
	/**
	 * set 玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
	 */
	public void setNonage(byte nonage){
		this.nonage = nonage;
	}
	
	/**
	 * get 角色当前坐骑
	 * @return 
	 */
	public short getHorseid(){
		return horseid;
	}
	
	/**
	 * set 角色当前坐骑
	 */
	public void setHorseid(short horseid){
		this.horseid = horseid;
	}
	
	/**
	 * get 角色最高坐骑
	 * @return 
	 */
	public short getTophorseid(){
		return tophorseid;
	}
	
	/**
	 * set 角色最高坐骑
	 */
	public void setTophorseid(short tophorseid){
		this.tophorseid = tophorseid;
	}
	
	/**
	 * get 装备部位全部宝石信息
	 * @return 
	 */
	public List<com.game.gem.bean.PosGemInfo> getPosallgeminfo(){
		return posallgeminfo;
	}
	
	/**
	 * set 装备部位全部宝石信息
	 */
	public void setPosallgeminfo(List<com.game.gem.bean.PosGemInfo> posallgeminfo){
		this.posallgeminfo = posallgeminfo;
	}
	
	/**
	 * get 属性列表
	 * @return 
	 */
	public List<com.game.player.bean.PlayerAttributeItem> getAttributes(){
		return attributes;
	}
	
	/**
	 * set 属性列表
	 */
	public void setAttributes(List<com.game.player.bean.PlayerAttributeItem> attributes){
		this.attributes = attributes;
	}
	
	/**
	 * get 装备列表信息
	 * @return 
	 */
	public List<com.game.equip.bean.EquipInfo> getEquips(){
		return equips;
	}
	
	/**
	 * set 装备列表信息
	 */
	public void setEquips(List<com.game.equip.bean.EquipInfo> equips){
		this.equips = equips;
	}
	
	/**
	 * get 物品信息列表
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getItems(){
		return items;
	}
	
	/**
	 * set 物品信息列表
	 */
	public void setItems(List<com.game.backpack.bean.ItemInfo> items){
		this.items = items;
	}
	
	
	@Override
	public int getId() {
		return 135304;
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
		//角色Id
		buf.append("personId:" + personId +",");
		//角色名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//角色性别 1-男 2-女
		buf.append("sex:" + sex +",");
		//角色等级
		buf.append("level:" + level +",");
		//角色所在地图
		buf.append("mapId:" + mapId +",");
		//角色所在X
		buf.append("x:" + x +",");
		//角色所在Y
		buf.append("y:" + y +",");
		//角色武功境界
		buf.append("skill:" + skill +",");
		//角色状态
		buf.append("state:" + state +",");
		//角色PK状态
		buf.append("pkState:" + pkState +",");
		//角色经验
		buf.append("exp:" + exp +",");
		//角色真气
		buf.append("zhenqi:" + zhenqi +",");
		//角色战场声望
		buf.append("prestige:" + prestige +",");
		//背包格子数
		buf.append("cellnum:" + cellnum +",");
		//仓库格子数
		buf.append("storecellnum:" + storecellnum +",");
		//铜币
		buf.append("money:" + money +",");
		//元宝
		buf.append("gold:" + gold +",");
		//绑定元宝
		buf.append("bindgold:" + bindgold +",");
		//临时元宝
		buf.append("tmpgold:" + tmpgold +",");
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		buf.append("nonage:" + nonage +",");
		//角色当前坐骑
		buf.append("horseid:" + horseid +",");
		//角色最高坐骑
		buf.append("tophorseid:" + tophorseid +",");
		//装备部位全部宝石信息
		buf.append("posallgeminfo:{");
		for (int i = 0; i < posallgeminfo.size(); i++) {
			buf.append(posallgeminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//属性列表
		buf.append("attributes:{");
		for (int i = 0; i < attributes.size(); i++) {
			buf.append(attributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//装备列表信息
		buf.append("equips:{");
		for (int i = 0; i < equips.size(); i++) {
			buf.append(equips.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//物品信息列表
		buf.append("items:{");
		for (int i = 0; i < items.size(); i++) {
			buf.append(items.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}