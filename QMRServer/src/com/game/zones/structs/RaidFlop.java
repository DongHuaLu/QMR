package com.game.zones.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.object.GameObject;
import com.game.spirittree.structs.FruitReward;
/**副本翻牌奖励
 * 
 * @author zhangrong
 *
 */
public class RaidFlop extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5918264126884506309L;

	//手动扫荡奖励表（现在已经改为临时存放展示奖励用，不再区分手动或者自动）
	private List<FruitReward> manualrewardlist = new ArrayList<FruitReward>();
	
	//自动扫荡奖励表
	private List<FruitReward> autorewardlist = new ArrayList<FruitReward>();
	
	//手动奖励选择3次（位置）
	private List<Integer> manualrewardidx = new ArrayList<Integer>(); 
	
	//自动奖励选择3次（位置）
	private List<Integer> autorewardidx = new ArrayList<Integer>();
	
	//七曜战将 奖励选择
	private List<FruitReward> qiyaorewardlist = new ArrayList<FruitReward>();
	
	/**手动扫荡奖励表（现在已经改为临时存放展示奖励用，不再区分手动或者自动）
	 * 
	 * @return
	 */
	public List<FruitReward> getManualrewardlist() {
		return manualrewardlist;
	}
	
	/**手动扫荡奖励表（现在已经改为临时存放展示奖励用，不再区分手动或者自动）
	 * 
	 * @return
	 */
	public void setManualrewardlist(List<FruitReward> manualrewardlist) {
		this.manualrewardlist = manualrewardlist;
	}
	
	

	public List<FruitReward> getAutorewardlist() {
		return autorewardlist;
	}

	public void setAutorewardlist(List<FruitReward> autorewardlist) {
		this.autorewardlist = autorewardlist;
	}

	
	
	public List<Integer> getManualrewardidx() {
		return manualrewardidx;
	}

	public void setManualrewardidx(List<Integer> manualrewardidx) {
		this.manualrewardidx = manualrewardidx;
	}

	
	
	public List<Integer> getAutorewardidx() {
		return autorewardidx;
	}

	public void setAutorewardidx(List<Integer> autorewardidx) {
		this.autorewardidx = autorewardidx;
	}

	public List<FruitReward> getQiyaorewardlist() {
		return qiyaorewardlist;
	}

	public void setQiyaorewardlist(List<FruitReward> qiyaorewardlist) {
		this.qiyaorewardlist = qiyaorewardlist;
	} 
}
