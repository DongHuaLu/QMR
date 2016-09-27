package com.game.toplist.structs;

import com.game.toplist.manager.TopListManager;

/**
 * 龙元排序数据
 *
 * @author 杨鸿岚
 */
public class LongYuanTop extends TopData {

	private int lysection;
	private int lylevel;
	private long longyuantime;
	
	public LongYuanTop(long topid, int lysectionnum,int lylevelnum,long longyuantimenum){
		super(topid);
		lysection = lysectionnum;
		lylevel = lylevelnum;
		longyuantime = longyuantimenum;
	}

	public long getLongyuantime() {
		return longyuantime;
	}

	public void setLongyuantime(long longyuantime) {
		this.longyuantime = longyuantime;
	}

	public int getLylevel() {
		return lylevel;
	}

	public void setLylevel(int lylevel) {
		this.lylevel = lylevel;
	}

	public int getLysection() {
		return lysection;
	}

	public void setLysection(int lysection) {
		this.lysection = lysection;
	}

	@Override
	public boolean checkAddCondition() {
		return TopListManager.SYNC_LONGYUAN <= lysection;
	}

	@Override
	public int compare(TopData otherTopData) {
		if (otherTopData instanceof LongYuanTop) {
			LongYuanTop othTop = (LongYuanTop)otherTopData;
			if (othTop != null) {
				if (othTop.getLysection() > this.getLysection()) {
					return 1;
				}else if (othTop.getLysection() == this.getLysection()){
					if (othTop.getLylevel() > this.getLylevel()) {
						return 1;
					}else if (othTop.getLylevel() == this.getLylevel()){
						if (this.getLongyuantime() > othTop.getLongyuantime()) {
							return 1;
						}else if(othTop.getLongyuantime() == this.getLongyuantime()){
							//return 0;
							return super.compare(otherTopData);
						}
					}
				}
			}
		}
		return -1;
	}
}
