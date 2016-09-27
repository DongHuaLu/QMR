package com.game.toplist.structs;

import com.game.toplist.manager.TopListManager;

/**
 * 侍宠排序数据
 *
 * @author 杨鸿岚
 */
public class PetTop extends TopData {

	private int petorder;
	private int makelovenum;
	private int petlv;

	public PetTop(long topid, int petorder, int makelovenum, int petlv) {
		super(topid);
		this.petorder = petorder;
		this.makelovenum = makelovenum;
		this.petlv = petlv;
	}

	public int getPetorder() {
		return petorder;
	}

	public void setPetorder(int petorder) {
		this.petorder = petorder;
	}

	public int getMakelovenum() {
		return makelovenum;
	}

	public void setMakelovenum(int makelovenum) {
		this.makelovenum = makelovenum;
	}

	public int getPetlv() {
		return petlv;
	}

	public void setPetlv(int petlv) {
		this.petlv = petlv;
	}

	@Override
	public boolean checkAddCondition() {
		return TopListManager.SYNC_PET <= petorder;
	}

	@Override
	public int compare(TopData otherTopData) {
		if (otherTopData instanceof PetTop) {
			PetTop othTop = (PetTop) otherTopData;
			if (othTop != null) {
				if (othTop.getPetorder() > this.getPetorder()) {
					return 1;
				} else if (othTop.getPetorder() == this.getPetorder()) {
					if (othTop.getMakelovenum() > this.getMakelovenum()) {
						return 1;
					} else if (othTop.getMakelovenum() == this.getMakelovenum()) {
						if (othTop.getPetlv() > this.getPetlv()) {
							return 1;
						} else if (othTop.getPetlv() == this.getPetlv()) {
							//return 1;
							return super.compare(otherTopData);
						}
					}
				}
			}
		}
		return -1;
	}
}
