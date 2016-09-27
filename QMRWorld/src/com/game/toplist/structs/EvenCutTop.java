package com.game.toplist.structs;

import com.game.toplist.manager.TopListManager;

/**
 * 连斩排序数据
 *
 * @author 杨鸿岚
 */
public class EvenCutTop extends TopData {

	private int evencutNum;
	private long evencuttime;
	//角色连斩地图
	private int mapModelId;
	//角色连斩怪物
	private int monsterModelId;
	//角色连斩地图X
	private short mapX;
	//角色连斩地图Y
	private short mapY;

	public EvenCutTop(long topid, int evencutNum, long evencuttime, int mapModelId, int monsterModelId, short mapX, short mapY) {
		super(topid);
		this.evencutNum = evencutNum;
		this.evencuttime = evencuttime;
		this.mapModelId = mapModelId;
		this.monsterModelId = monsterModelId;
		this.mapX = mapX;
		this.mapY = mapY;
	}

	public int getEvencutNum() {
		return evencutNum;
	}

	public void setEvencutNum(int evencutNum) {
		this.evencutNum = evencutNum;
	}

	public long getEvencuttime() {
		return evencuttime;
	}

	public void setEvencuttime(long evencuttime) {
		this.evencuttime = evencuttime;
	}

	public int getMapModelId() {
		return mapModelId;
	}

	public void setMapModelId(int mapModelId) {
		this.mapModelId = mapModelId;
	}

	public short getMapX() {
		return mapX;
	}

	public void setMapX(short mapX) {
		this.mapX = mapX;
	}

	public short getMapY() {
		return mapY;
	}

	public void setMapY(short mapY) {
		this.mapY = mapY;
	}

	public int getMonsterModelId() {
		return monsterModelId;
	}

	public void setMonsterModelId(int monsterModelId) {
		this.monsterModelId = monsterModelId;
	}

	@Override
	public boolean checkAddCondition() {
		return (TopListManager.SYNC_EVENT_CUT <= evencutNum && evencutNum < 850);
	}

	@Override
	public int compare(TopData otherTopData) {
		if (otherTopData instanceof EvenCutTop) {
			EvenCutTop othTop = (EvenCutTop) otherTopData;
			if (othTop != null) {
				if (othTop.getEvencutNum() > this.getEvencutNum()) {
					return 1;
				} else if (othTop.getEvencutNum() == this.getEvencutNum()) {
					if (this.getEvencuttime() > othTop.getEvencuttime()) {
						return 1;
					} else if (othTop.getEvencuttime() == this.getEvencuttime()) {
						//return 0;
						return super.compare(otherTopData);
					}
				}
			}
		}
		return -1;
	}
}
