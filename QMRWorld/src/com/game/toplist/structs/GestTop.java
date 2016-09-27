package com.game.toplist.structs;

import com.game.toplist.manager.TopListManager;

/**
 * 武功排序数据
 *
 * @author 杨鸿岚
 */
public class GestTop extends TopData {

	private int gestlv;
	private long gesttime;
	
	public GestTop(long topid, int gestlvnum, long gesttimenum){
		super(topid);
		gestlv = gestlvnum;
		gesttime = gesttimenum;
	}

	public int getGestlv() {
		return gestlv;
	}

	public void setGestlv(int gestlv) {
		this.gestlv = gestlv;
	}

	public long getGesttime() {
		return gesttime;
	}

	public void setGesttime(long gesttime) {
		this.gesttime = gesttime;
	}

	@Override
	public boolean checkAddCondition() {
		return TopListManager.SYNC_SKILL_LEVEL <= gestlv;
	}

	@Override
	public int compare(TopData otherTopData) {
		if (otherTopData instanceof GestTop) {
			GestTop othTop = (GestTop)otherTopData;
			if (othTop != null) {
				if (othTop.getGestlv() > this.getGestlv()) {
					return 1;
				}else if (othTop.getGestlv() == this.getGestlv()){
					if (this.getGesttime() > othTop.getGesttime()) {
						return 1;
					}else if (othTop.getGesttime() == this.getGesttime()){
						//return 0;
						return super.compare(otherTopData);
					}
				}
			}
		}
		return -1;
	}
}
