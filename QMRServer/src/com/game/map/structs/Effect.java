package com.game.map.structs;

import com.game.object.GameObject;
import com.game.player.structs.Player;
import com.game.structs.Position;

/**
 * 场景效果
 * @author 赵聪慧
 * @2012-8-25 下午2:56:41
 */
public class Effect extends GameObject implements IMapObject{
	
	private static final long serialVersionUID = -4300936327520065322L;

	private int effectModelId;
	
	private Position position;
	
	private int map;
	
	private int mapmodelid;
	
	private int line;
	
	private int serverId;
	//播放类型 0-1次 1-不停
	private byte type;
	//来源id
	private long sourceId;
	
	public int getEffectModelId() {
		return effectModelId;
	}
	public void setEffectModelId(int effectModelId) {
		this.effectModelId = effectModelId;
	}
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
	public long getSourceId() {
		return sourceId;
	}
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}
	@Override
	public boolean canSee(Player player) {
		return true;
	}
	
}
