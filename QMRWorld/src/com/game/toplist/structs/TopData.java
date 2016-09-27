package com.game.toplist.structs;

/**
 * 排序数据
 *
 * @author 杨鸿岚
 */
public abstract class TopData {

	private long id;	//玩家id

	public TopData(long topid) {
		id = topid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean checkAddCondition(){
		return false;
	}

	public int compare(TopData otherTopData) {
		if (otherTopData.getId() > this.getId()) {
			return -1;
		}else if (otherTopData.getId() == this.getId()){
			return 0;
		}else {
			return 1;
		}
//		if (this instanceof LevelTop) {
//			return ((LevelTop) this).compare(otherTopData);
//		} else if (this instanceof HorseTop) {
//			return ((HorseTop) this).compare(otherTopData);
//		} else if (this instanceof GestTop) {
//			return ((GestTop) this).compare(otherTopData);
//		} else if (this instanceof LongYuanTop) {
//			return ((LongYuanTop) this).compare(otherTopData);
//		} else if (this instanceof EvenCutTop) {
//			return ((EvenCutTop) this).compare(otherTopData);
//		}
//		return -1;
	}
}
