package com.game.signwage.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.object.GameObject;
import com.game.spirittree.structs.FruitReward;
import com.game.utils.TimeUtil;

/**工资
 * 
 * @author zhangrong
 *
 */
public class Wage extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7234137074942856266L;
	
	//开区后第几个月（30的倍数）
	private int monthnum;
	
	//本月累计在线时间秒
	private int cumulativetime;
	
	//本月工资领取标记
	private int status;
	
	//摇奖日期
	private int ernieday ;
	//摇奖次数
	private int ernienum ;
	
	//摇奖奖励
	private List<FruitReward> ernierewardlist =new ArrayList<FruitReward>();
	
	/**重置摇奖
	 * 
	 */
	public void clearernie(){
		int day = TimeUtil.getDayOfMonth(System.currentTimeMillis());
		if (this.getErnieday() !=day ) {
			this.setErnieday(day);
			this.setErnienum(0);
			this.getErnierewardlist().clear();
		}
	}
	
	
	
	
	
	
	
	
	
	public int getMonthnum() {
		return monthnum;
	}

	public void setMonthnum(int monthnum) {
		this.monthnum = monthnum;
	}

	public int getCumulativetime() {
		return cumulativetime;
	}

	public void setCumulativetime(int cumulativetime) {
		this.cumulativetime = cumulativetime;
	}

	public int getErnienum() {
		return ernienum;
	}

	public void setErnienum(int ernienum) {
		this.ernienum = ernienum;
	}

	public int getErnieday() {
		return ernieday;
	}

	public void setErnieday(int ernieday) {
		this.ernieday = ernieday;
	}









	public int getStatus() {
		return status;
	}









	public void setStatus(int status) {
		this.status = status;
	}









	public List<FruitReward> getErnierewardlist() {
		return ernierewardlist;
	}









	public void setErnierewardlist(List<FruitReward> ernierewardlist) {
		this.ernierewardlist = ernierewardlist;
	}
}
