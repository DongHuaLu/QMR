package com.game.toplist.structs;

import com.game.toplist.manager.TopListManager;

/**
 * 弓箭排行数据
 *
 * @author 杨鸿岚
 */
public class ArrowTop extends TopData{
	
	private int arrowlv;
	private int starlv;
	private int bowlv;
	private int level;
	private int costgold;

	public ArrowTop(long topid, int arrowlv, int starlv, int bowlv, int level, int costgold) {
		super(topid);
		this.arrowlv = arrowlv;
		this.starlv = starlv;
		this.bowlv = bowlv;
		this.level = level;
		this.costgold = costgold;
	}

	public int getArrowlv() {
		return arrowlv;
	}

	public void setArrowlv(int arrowlv) {
		this.arrowlv = arrowlv;
	}

	public int getBowlv() {
		return bowlv;
	}

	public void setBowlv(int bowlv) {
		this.bowlv = bowlv;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStarlv() {
		return starlv;
	}

	public void setStarlv(int starlv) {
		this.starlv = starlv;
	}

	public int getCostgold() {
		return costgold;
	}

	public void setCostgold(int costgold) {
		this.costgold = costgold;
	}

	@Override
	public boolean checkAddCondition() {
		return TopListManager.SYNC_ARROW <= arrowlv;
	}

	@Override
	public int compare(TopData otherTopData) {
		if (otherTopData instanceof ArrowTop) {
			ArrowTop othTop = (ArrowTop) otherTopData;
			if (othTop != null) {
				if (othTop.getArrowlv() > this.getArrowlv()) {
					return 1;
				} else if (othTop.getArrowlv() == this.getArrowlv()) {
					if (othTop.getStarlv() > this.getStarlv()) {
						return 1;
					} else if (othTop.getStarlv() == this.getStarlv()) {
						if (othTop.getBowlv() > this.getBowlv()) {
							return 1;
						} else if (othTop.getBowlv() == this.getBowlv()) {
							if (othTop.getLevel() > this.getLevel()) {
								return 1;
							} else if (othTop.getLevel() == this.getLevel()) {
								if (othTop.getCostgold() > this.getCostgold()) {
									return 1;
								} else if (othTop.getCostgold() == this.getCostgold()) {
									//return 1;
									return super.compare(otherTopData);
								}
							}
						}
					}
				}
			}
		}
		return -1;
	}
}
