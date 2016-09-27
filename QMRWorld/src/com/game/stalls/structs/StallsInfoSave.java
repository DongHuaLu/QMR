package com.game.stalls.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.object.GameObject;



public class StallsInfoSave extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9172025872214908226L;
	
	//  摊位等级，0，1，2，3

	//摊位评分
	private int stallsscore;
	
	//摊位名字
	private String stallsname;
	
	//摊位物品信息列表
	private List<StallsItem> stallsgoodsinfo = new ArrayList<StallsItem>();
	//摊位锁  0正常，1锁定
	private transient byte lockstatus;
	
	//玩家评分记录
	private RatingData ratingdata = new RatingData();
	
	
	/**
	 * 摊位评分
	 * @return
	 */
	public int getStallsscore() {
		return stallsscore;
	}

	public void setStallsscore(int stallsscore) {
		this.stallsscore = stallsscore;
	}

	/**
	 * 摊位名字
	 * @return
	 */
	public String getStallsname() {
		return stallsname;
	}

	public void setStallsname(String stallsname) {
		this.stallsname = stallsname;
	}
	/**
	 * 摊位物品信息列表
	 * @return
	 */
	public List<StallsItem> getStallsgoodsinfo() {
		return stallsgoodsinfo;
	}

	public void setStallsgoodsinfo(List<StallsItem> stallsgoodsinfo) {
		this.stallsgoodsinfo = stallsgoodsinfo;
	}
	

	public byte getLockstatus() {
		return lockstatus;
	}

	public void setLockstatus(byte lockstatus) {
		this.lockstatus = lockstatus;
	}

	public RatingData getRatingdata() {
		return ratingdata;
	}

	public void setRatingdata(RatingData ratingdata) {
		this.ratingdata = ratingdata;
	}
	
	

//	无名小贩	1000
//	垄断一方	5000
//	商通大秦	15000
//	四海商圣	50000
	public byte acqStallslv() {
		if (getStallsscore() < 5000) {
			return 0;
		}else if (getStallsscore() >= 5000 && getStallsscore() < 15000  ) {
			return 1;
		}else if (getStallsscore() >= 15000 && getStallsscore() < 50000  ) {
			return 2;
		}else if (getStallsscore() >= 50000) {
			return 3;
		}
		return 0;
	}
	
	
	
}
