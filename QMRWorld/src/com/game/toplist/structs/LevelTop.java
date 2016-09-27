package com.game.toplist.structs;

import com.game.toplist.manager.TopListManager;

/**
 * 等级排序数据
 *
 * @author 杨鸿岚
 */
public class LevelTop extends TopData {

	private int lv;
	private long lvtime;
	
	public LevelTop(long topid, int lvnum, long lvtimenum){
		super(topid);
		lv = lvnum;
		lvtime = lvtimenum;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public long getLvtime() {
		return lvtime;
	}

	public void setLvtime(long lvtime) {
		this.lvtime = lvtime;
	}

	@Override
	public boolean checkAddCondition() {
		return TopListManager.SYNC_PLAYER_LEVEL <= lv;
	}

	@Override
	public int compare(TopData otherTopData) {
		if (otherTopData instanceof LevelTop) {
			LevelTop othTop = (LevelTop)otherTopData;
			if (othTop != null) {
				if (othTop.getLv() > this.getLv()) {
					return 1;
				}else if (othTop.getLv() == this.getLv()){
					if (this.getLvtime() > othTop.getLvtime()) {
						return 1;
					}else if (othTop.getLvtime() == this.getLvtime()){
						//return 1;
						return super.compare(otherTopData);
					}
				}
			}
		}
		return -1;
	}
}
