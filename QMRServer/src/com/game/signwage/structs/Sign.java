package com.game.signwage.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.object.GameObject;
import com.game.utils.TimeUtil;


/**签到
 * 
 * @author zhangrong
 *
 */
public class Sign extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5360309152903919319L;

	//签到天数
	private List<Integer> daylist = new ArrayList<Integer>();
	
	//奖励保存
	private List<Integer> rewardstatelist = new ArrayList<Integer>();

	
	
	
	/**设置签到
	 * 
	 * @return
	 */
	public boolean addSign(){
		int day = (int)TimeUtil.getDayOfMonth(System.currentTimeMillis());
		if(this.getDaylist().contains(day)){
			return false;
		}else {
			this.getDaylist().add(day);
			return true;
		}
	}
	
	
	
	
	
	
	
	public List<Integer> getDaylist() {
		return daylist;
	}

	public void setDaylist(List<Integer> daylist) {
		this.daylist = daylist;
	}

	public List<Integer> getRewardstatelist() {
		return rewardstatelist;
	}

	public void setRewardstatelist(List<Integer> rewardstatelist) {
		this.rewardstatelist = rewardstatelist;
	}
	
	
	
	
	
	
	
	
}
