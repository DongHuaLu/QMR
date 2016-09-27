package com.game.toplist.structs;

import com.game.toplist.manager.TopListManager;

/**
 * 战斗力排序数据
 *
 * @author 杨鸿岚
 */
public class FightPowerTop extends TopData{
	
	private int fightpower;
	private int level;

	public FightPowerTop(long topid, int fightpower, int level) {
		super(topid);
		this.fightpower = fightpower;
		this.level = level;
	}

	public int getFightpower() {
		return fightpower;
	}

	public void setFightpower(int fightpower) {
		this.fightpower = fightpower;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public boolean checkAddCondition() {
		return TopListManager.SYNC_FIGHTPOWER <= fightpower;
	}

	@Override
	public int compare(TopData otherTopData) {
		if (otherTopData instanceof FightPowerTop) {
			FightPowerTop othTop = (FightPowerTop)otherTopData;
			if (othTop != null) {
				if (othTop.getFightpower() > this.getFightpower()) {
					return 1;
				}else if (othTop.getFightpower() == this.getFightpower()){
					if (this.getLevel() > othTop.getLevel()) {
						return 1;
					}else if (othTop.getLevel() == this.getLevel()){
						//return 1;
						return super.compare(otherTopData);
					}
				}
			}
		}
		return -1;
	}
}
