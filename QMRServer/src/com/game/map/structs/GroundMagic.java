package com.game.map.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.object.GameObject;
import com.game.player.structs.Player;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;

/**
 * 场景魔法
 * @author 何洋
 * @2013-3-12 下午2:56:41
 */
public class GroundMagic extends GameObject implements IMapObject{
	
	private static final long serialVersionUID = -4300936327520065322L;

	private Skill skill;
	
	private Position position;
	
	private int map;
	
	private int mapmodelid;
	
	private int line;
	
	private int serverId;
	//播放类型 0-1次 1-不停
	private byte type;
	//释放者id
	private long sourceId;
	//分布格子
	private List<Grid> grids = new ArrayList<Grid>();
	//开始时间
	private long startTime;
	//持续时间
	private long lastTime;
	//持续特效
	private Effect effect;
	
	public int getMap() {
		return map;
	}
	public void setMap(int map) {
		this.map = map;
	}
	public int getMapmodelid() {
		return mapmodelid;
	}
	public void setMapmodelid(int mapmodelid) {
		this.mapmodelid = mapmodelid;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	@Override
	public boolean canSee(Player player) {
		return true;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public long getSourceId() {
		return sourceId;
	}
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}
	public List<Grid> getGrids() {
		return grids;
	}
	public void setGrids(List<Grid> grids) {
		this.grids = grids;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	public Effect getEffect() {
		return effect;
	}
	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	
}
