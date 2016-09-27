package com.game.map.structs;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.monster.structs.Monster;
import com.game.object.GameObject;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;

/**
 * 地图信息
 * @author heyang
 *
 */
public class Map extends GameObject {

	private static final long serialVersionUID = -8549637893123656226L;
	
	protected static Logger log = Logger.getLogger(Map.class);
	//地区列表
	private HashMap<Integer, Area> areas = new HashMap<Integer, Area>();
	//玩家列表
	private HashMap<Long, Player> players = new HashMap<Long, Player>();
	//怪物列表
	private HashMap<Long, Monster> monsters = new HashMap<Long, Monster>();
	//宠物列表
	private HashMap<Long, Pet> pets = new HashMap<Long, Pet>();
	//跑动怪物列表
	private HashMap<Long, Monster> runningMonsters = new HashMap<Long, Monster>();
	//跑动宠物列表
	private HashMap<Long, Pet> runningPets = new HashMap<Long, Pet>();
	//跑动玩家列表
	private HashMap<Long, Player> runningPlayers = new HashMap<Long, Player>();
	//格挡玩家列表
	private HashMap<Long, Player> blockingPlayers = new HashMap<Long, Player>();
	//等待复活怪物列表
	private HashMap<Long, Monster> revives = new HashMap<Long, Monster>();
	//地图魔法列表
	private HashMap<Long, GroundMagic> magics = new HashMap<Long, GroundMagic>();
	//地图模块Id
	private int mapModelid;
	//所在线
	private int lineId;
	//所在服务器
	private int serverId;
	//地图是否为副本
	private boolean copy;
	//地图绑定副本
	private long zoneId;
	//地图绑定副本模板
	private int zoneModelId;
	//地图创建时间
	private long create;
	//地图视野范围
	private int round;
	//地图宽度
	private int width;
	//地图高度
	private int height;
	//发送Id
	private long sendId;
	
	//临时参数列表
	private ConcurrentHashMap<String, Object> parameters = new ConcurrentHashMap<String, Object>();
	
	//地图禁止使用加血道具
	private int banusehp;
	//地图禁止使用加体力道具
	private int banusesp;
	//地图禁止使用加内力道具
	private int banusemp;
	
	
	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public Map(int mapId){
		this.id = mapId;
	}

	public HashMap<Integer, Area> getAreas() {
		return areas;
	}

	public void setAreas(HashMap<Integer, Area> areas) {
		this.areas = areas;
	}

	public int getMapModelid() {
		return mapModelid;
	}

	public void setMapModelid(int mapModelid) {
		this.mapModelid = mapModelid;
	}

	public HashMap<Long, Player> getPlayers() {
		return players;
	}

	public void setPlayers(HashMap<Long, Player> players) {
		this.players = players;
	}

	public HashMap<Long, Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(HashMap<Long, Monster> monsters) {
		this.monsters = monsters;
	}

	public HashMap<Long, Monster> getRunningMonsters() {
		return runningMonsters;
	}

	public void setRunningMonsters(HashMap<Long, Monster> runningMonsters) {
		this.runningMonsters = runningMonsters;
	}

	public HashMap<Long, Player> getRunningPlayers() {
		return runningPlayers;
	}

	public void setRunningPlayers(HashMap<Long, Player> runningPlayers) {
		this.runningPlayers = runningPlayers;
	}

	public HashMap<Long, Player> getBlockingPlayers() {
		return blockingPlayers;
	}

	public void setBlockingPlayers(HashMap<Long, Player> blockingPlayers) {
		this.blockingPlayers = blockingPlayers;
	}

	public HashMap<Long, Monster> getRevives() {
		return revives;
	}

	public void setRevives(HashMap<Long, Monster> revives) {
		this.revives = revives;
	}

	public HashMap<Long, GroundMagic> getMagics() {
		return magics;
	}

	public void setMagics(HashMap<Long, GroundMagic> magics) {
		this.magics = magics;
	}

	public boolean isCopy() {
		return copy;
	}

	public void setCopy(boolean copy) {
		this.copy = copy;
	}

	public long getZoneId() {
		return zoneId;
	}

	public void setZoneId(long zoneId) {
		this.zoneId = zoneId;
	}

	public int getZoneModelId() {
		return zoneModelId;
	}

	public void setZoneModelId(int zoneModelId) {
		this.zoneModelId = zoneModelId;
	}

	public long getCreate() {
		return create;
	}

	public void setCreate(long create) {
		this.create = create;
	}

	public int getPlayerNumber() {
		return players.size();
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public long getSendId() {
		return sendId;
	}

	public void setSendId(long sendId) {
		this.sendId = sendId;
	}

	public HashMap<Long, Pet> getPets() {
		return pets;
	}

	public void setPets(HashMap<Long, Pet> pets) {
		this.pets = pets;
	}

	public HashMap<Long, Pet> getRunningPets() {
		return runningPets;
	}

	public void setRunningPets(HashMap<Long, Pet> runningPets) {
		this.runningPets = runningPets;
	}

	/**
	 * 地图上是否有玩家
	 * @return
	 */
	public boolean isEmpty(){
		return players.size() == 0;
	}

	public ConcurrentHashMap<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(ConcurrentHashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public int getBanusehp() {
		return banusehp;
	}

	public void setBanusehp(int banusehp) {
		this.banusehp = banusehp;
	}

	public int getBanusesp() {
		return banusesp;
	}

	public void setBanusesp(int banusesp) {
		this.banusesp = banusesp;
	}

	public int getBanusemp() {
		return banusemp;
	}

	public void setBanusemp(int banusemp) {
		this.banusemp = banusemp;
	}


}
