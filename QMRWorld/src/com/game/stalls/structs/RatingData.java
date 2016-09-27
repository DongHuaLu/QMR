package com.game.stalls.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.object.GameObject;

public class RatingData extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8103227079573672342L;
	
	//最后评价时间
	private long evaluatetime ;
	//进行评价的玩家ID
	
	private List<Long> evaluatelist = new ArrayList<Long>();
	
	
	
	
	public long getEvaluatetime() {
		return evaluatetime;
	}
	public void setEvaluatetime(long evaluatetime) {
		this.evaluatetime = evaluatetime;
	}
	public List<Long> getEvaluatelist() {
		return evaluatelist;
	}
	public void setEvaluatelist(List<Long> evaluatelist) {
		this.evaluatelist = evaluatelist;
	}

	
	
	/**每天定时清除评分记录
	 * 
	 */
//	public void removeRatingData(){
//		if(TimeUtil.isAfterHourOfCurrentDay(5,getEvaluatetime())) {
//			this.evaluatelist.clear();
//		}
//	}
	
	/**检测是否进行过评分 true表示已经评分
	 * 
	 * @param pid
	 * @return
	 */
	public boolean checkRating(long pid){
		if (this.evaluatelist.size() > 0) {
			for (int i = 0; i < this.evaluatelist.size(); i++) {
				if (pid == this.evaluatelist.get(i)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	

//	public static void main(String[] args) {
//		RatingData sData = new RatingData();
//		sData.getEvaluatelist().add((long) 111111);
//		sData.setEvaluatetime(System.currentTimeMillis());
//		sData.removeRatingData();
//		sData.getEvaluatelist().clear();
//		System.err.println("ddd");
//	}
}
