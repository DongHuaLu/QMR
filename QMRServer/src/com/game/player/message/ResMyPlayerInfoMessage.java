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
 * 本人玩家信息消息
 */
public class ResMyPlayerInfoMessage extends Message{

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
	
	//角色武功境界层数
	private int skills;
	
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
	
	//人物面对方向
	private byte dir;
	
	//头象ID
	private int avatar;
	
	//属性列表
	private List<PlayerAttributeItem> attributes = new ArrayList<PlayerAttributeItem>();
	//跑步坐标集合
	private List<com.game.structs.Position> positions = new ArrayList<com.game.structs.Position>();
	//装备列表信息
	private List<com.game.equip.bean.EquipInfo> equips = new ArrayList<com.game.equip.bean.EquipInfo>();
	//背包格子数
	private short cellnum;
	
	//仓库格子数
	private short storecellnum;
	
	//物品信息列表
	private List<com.game.backpack.bean.ItemInfo> items = new ArrayList<com.game.backpack.bean.ItemInfo>();
	//金币
	private int money;
	
	//元宝
	private int gold;
	
	//绑定元宝
	private int bindgold;
	
	//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
	private byte nonage;
	
	//角色当前坐骑
	private short horseid;
	
	//角色当前骑战兵器
	private short horseweaponid;
	
	//角色当前暗器
	private short hiddenweaponid;
	
	//装备部位全部宝石信息
	private List<com.game.gem.bean.PosGemInfo> posallgeminfo = new ArrayList<com.game.gem.bean.PosGemInfo>();
	//主线任务ID
	private int maintaskId;
	
	//王城BUFFid
	private int kingcitybuffid;
	
	//VIPid
	private int vipid;
	
	//龙元心法阶段（星图）
	private byte longyuanlv;
	
	//龙元心法星位
	private byte longyuannum;
	
	//军衔等级
	private byte ranklevel;
	
	//弓箭信息
	private com.game.arrow.bean.ArrowInfo arrowInfo;
	
	//平台VIP
	private int webvip;
	
	//平台VIP2
	private int webvip2;
	
	
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
		//角色武功境界层数
		writeInt(buf, this.skills);
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
		//人物面对方向
		writeByte(buf, this.dir);
		//头象ID
		writeInt(buf, this.avatar);
		//属性列表
		writeShort(buf, attributes.size());
		for (int i = 0; i < attributes.size(); i++) {
			writeBean(buf, attributes.get(i));
		}
		//跑步坐标集合
		writeShort(buf, positions.size());
		for (int i = 0; i < positions.size(); i++) {
			writeBean(buf, positions.get(i));
		}
		//装备列表信息
		writeShort(buf, equips.size());
		for (int i = 0; i < equips.size(); i++) {
			writeBean(buf, equips.get(i));
		}
		//背包格子数
		writeShort(buf, this.cellnum);
		//仓库格子数
		writeShort(buf, this.storecellnum);
		//物品信息列表
		writeShort(buf, items.size());
		for (int i = 0; i < items.size(); i++) {
			writeBean(buf, items.get(i));
		}
		//金币
		writeInt(buf, this.money);
		//元宝
		writeInt(buf, this.gold);
		//绑定元宝
		writeInt(buf, this.bindgold);
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		writeByte(buf, this.nonage);
		//角色当前坐骑
		writeShort(buf, this.horseid);
		//角色当前骑战兵器
		writeShort(buf, this.horseweaponid);
		//角色当前暗器
		writeShort(buf, this.hiddenweaponid);
		//装备部位全部宝石信息
		writeShort(buf, posallgeminfo.size());
		for (int i = 0; i < posallgeminfo.size(); i++) {
			writeBean(buf, posallgeminfo.get(i));
		}
		//主线任务ID
		writeInt(buf, this.maintaskId);
		//王城BUFFid
		writeInt(buf, this.kingcitybuffid);
		//VIPid
		writeInt(buf, this.vipid);
		//龙元心法阶段（星图）
		writeByte(buf, this.longyuanlv);
		//龙元心法星位
		writeByte(buf, this.longyuannum);
		//军衔等级
		writeByte(buf, this.ranklevel);
		//弓箭信息
		writeBean(buf, this.arrowInfo);
		//平台VIP
		writeInt(buf, this.webvip);
		//平台VIP2
		writeInt(buf, this.webvip2);
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
		//角色武功境界层数
		this.skills = readInt(buf);
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
		//人物面对方向
		this.dir = readByte(buf);
		//头象ID
		this.avatar = readInt(buf);
		//属性列表
		int attributes_length = readShort(buf);
		for (int i = 0; i < attributes_length; i++) {
			attributes.add((PlayerAttributeItem)readBean(buf, PlayerAttributeItem.class));
		}
		//跑步坐标集合
		int positions_length = readShort(buf);
		for (int i = 0; i < positions_length; i++) {
			positions.add((com.game.structs.Position)readBean(buf, com.game.structs.Position.class));
		}
		//装备列表信息
		int equips_length = readShort(buf);
		for (int i = 0; i < equips_length; i++) {
			equips.add((com.game.equip.bean.EquipInfo)readBean(buf, com.game.equip.bean.EquipInfo.class));
		}
		//背包格子数
		this.cellnum = readShort(buf);
		//仓库格子数
		this.storecellnum = readShort(buf);
		//物品信息列表
		int items_length = readShort(buf);
		for (int i = 0; i < items_length; i++) {
			items.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		//金币
		this.money = readInt(buf);
		//元宝
		this.gold = readInt(buf);
		//绑定元宝
		this.bindgold = readInt(buf);
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		this.nonage = readByte(buf);
		//角色当前坐骑
		this.horseid = readShort(buf);
		//角色当前骑战兵器
		this.horseweaponid = readShort(buf);
		//角色当前暗器
		this.hiddenweaponid = readShort(buf);
		//装备部位全部宝石信息
		int posallgeminfo_length = readShort(buf);
		for (int i = 0; i < posallgeminfo_length; i++) {
			posallgeminfo.add((com.game.gem.bean.PosGemInfo)readBean(buf, com.game.gem.bean.PosGemInfo.class));
		}
		//主线任务ID
		this.maintaskId = readInt(buf);
		//王城BUFFid
		this.kingcitybuffid = readInt(buf);
		//VIPid
		this.vipid = readInt(buf);
		//龙元心法阶段（星图）
		this.longyuanlv = readByte(buf);
		//龙元心法星位
		this.longyuannum = readByte(buf);
		//军衔等级
		this.ranklevel = readByte(buf);
		//弓箭信息
		this.arrowInfo = (com.game.arrow.bean.ArrowInfo)readBean(buf, com.game.arrow.bean.ArrowInfo.class);
		//平台VIP
		this.webvip = readInt(buf);
		//平台VIP2
		this.webvip2 = readInt(buf);
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
	 * get 角色武功境界层数
	 * @return 
	 */
	public int getSkills(){
		return skills;
	}
	
	/**
	 * set 角色武功境界层数
	 */
	public void setSkills(int skills){
		this.skills = skills;
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
	 * get 人物面对方向
	 * @return 
	 */
	public byte getDir(){
		return dir;
	}
	
	/**
	 * set 人物面对方向
	 */
	public void setDir(byte dir){
		this.dir = dir;
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
	 * get 跑步坐标集合
	 * @return 
	 */
	public List<com.game.structs.Position> getPositions(){
		return positions;
	}
	
	/**
	 * set 跑步坐标集合
	 */
	public void setPositions(List<com.game.structs.Position> positions){
		this.positions = positions;
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
	
	/**
	 * get 金币
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 金币
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
	 * get 角色当前骑战兵器
	 * @return 
	 */
	public short getHorseweaponid(){
		return horseweaponid;
	}
	
	/**
	 * set 角色当前骑战兵器
	 */
	public void setHorseweaponid(short horseweaponid){
		this.horseweaponid = horseweaponid;
	}
	
	/**
	 * get 角色当前暗器
	 * @return 
	 */
	public short getHiddenweaponid(){
		return hiddenweaponid;
	}
	
	/**
	 * set 角色当前暗器
	 */
	public void setHiddenweaponid(short hiddenweaponid){
		this.hiddenweaponid = hiddenweaponid;
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
	 * get 主线任务ID
	 * @return 
	 */
	public int getMaintaskId(){
		return maintaskId;
	}
	
	/**
	 * set 主线任务ID
	 */
	public void setMaintaskId(int maintaskId){
		this.maintaskId = maintaskId;
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
	 * get 龙元心法阶段（星图）
	 * @return 
	 */
	public byte getLongyuanlv(){
		return longyuanlv;
	}
	
	/**
	 * set 龙元心法阶段（星图）
	 */
	public void setLongyuanlv(byte longyuanlv){
		this.longyuanlv = longyuanlv;
	}
	
	/**
	 * get 龙元心法星位
	 * @return 
	 */
	public byte getLongyuannum(){
		return longyuannum;
	}
	
	/**
	 * set 龙元心法星位
	 */
	public void setLongyuannum(byte longyuannum){
		this.longyuannum = longyuannum;
	}
	
	/**
	 * get 军衔等级
	 * @return 
	 */
	public byte getRanklevel(){
		return ranklevel;
	}
	
	/**
	 * set 军衔等级
	 */
	public void setRanklevel(byte ranklevel){
		this.ranklevel = ranklevel;
	}
	
	/**
	 * get 弓箭信息
	 * @return 
	 */
	public com.game.arrow.bean.ArrowInfo getArrowInfo(){
		return arrowInfo;
	}
	
	/**
	 * set 弓箭信息
	 */
	public void setArrowInfo(com.game.arrow.bean.ArrowInfo arrowInfo){
		this.arrowInfo = arrowInfo;
	}
	
	/**
	 * get 平台VIP
	 * @return 
	 */
	public int getWebvip(){
		return webvip;
	}
	
	/**
	 * set 平台VIP
	 */
	public void setWebvip(int webvip){
		this.webvip = webvip;
	}
	
	/**
	 * get 平台VIP2
	 * @return 
	 */
	public int getWebvip2(){
		return webvip2;
	}
	
	/**
	 * set 平台VIP2
	 */
	public void setWebvip2(int webvip2){
		this.webvip2 = webvip2;
	}
	
	
	@Override
	public int getId() {
		return 103107;
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
		//角色武功境界层数
		buf.append("skills:" + skills +",");
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
		//人物面对方向
		buf.append("dir:" + dir +",");
		//头象ID
		buf.append("avatar:" + avatar +",");
		//属性列表
		buf.append("attributes:{");
		for (int i = 0; i < attributes.size(); i++) {
			buf.append(attributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//跑步坐标集合
		buf.append("positions:{");
		for (int i = 0; i < positions.size(); i++) {
			buf.append(positions.get(i).toString() +",");
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
		//背包格子数
		buf.append("cellnum:" + cellnum +",");
		//仓库格子数
		buf.append("storecellnum:" + storecellnum +",");
		//物品信息列表
		buf.append("items:{");
		for (int i = 0; i < items.size(); i++) {
			buf.append(items.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//金币
		buf.append("money:" + money +",");
		//元宝
		buf.append("gold:" + gold +",");
		//绑定元宝
		buf.append("bindgold:" + bindgold +",");
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		buf.append("nonage:" + nonage +",");
		//角色当前坐骑
		buf.append("horseid:" + horseid +",");
		//角色当前骑战兵器
		buf.append("horseweaponid:" + horseweaponid +",");
		//角色当前暗器
		buf.append("hiddenweaponid:" + hiddenweaponid +",");
		//装备部位全部宝石信息
		buf.append("posallgeminfo:{");
		for (int i = 0; i < posallgeminfo.size(); i++) {
			buf.append(posallgeminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//主线任务ID
		buf.append("maintaskId:" + maintaskId +",");
		//王城BUFFid
		buf.append("kingcitybuffid:" + kingcitybuffid +",");
		//VIPid
		buf.append("vipid:" + vipid +",");
		//龙元心法阶段（星图）
		buf.append("longyuanlv:" + longyuanlv +",");
		//龙元心法星位
		buf.append("longyuannum:" + longyuannum +",");
		//军衔等级
		buf.append("ranklevel:" + ranklevel +",");
		//弓箭信息
		if(this.arrowInfo!=null) buf.append("arrowInfo:" + arrowInfo.toString() +",");
		//平台VIP
		buf.append("webvip:" + webvip +",");
		//平台VIP2
		buf.append("webvip2:" + webvip2 +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}