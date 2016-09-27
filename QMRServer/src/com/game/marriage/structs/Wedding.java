package com.game.marriage.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.object.GameObject;


/**婚宴信息
 * 
 * @author zhangrong
 *
 */
public class Wedding extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2276217298204677746L;
	//婚宴类型,1,2,3
	private byte type;
	//开始时间 （排序和显示日期用）
	private long time;
	//婚姻ID
	private long marriageid;
	//婚宴状态，0未开始，1正在进行，2已经结束
	private byte status;
	//宾客红包列表
	private List<RedEnvelope> redenvelopes = new ArrayList<RedEnvelope>();
	//总红包收益
	private int redsum;
	//玩家是否领取红包(玩家ID)
	private List<Long> redreceives = new ArrayList<Long>();
	//距离现在多少天后举办
	private transient int day;
	
	
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	/**开始时间 （排序和显示日期用）
	 * 
	 * @return
	 */
	public long getTime() {
		return time;
	}
	/**开始时间 （排序和显示日期用）
	 * 
	 * @return
	 */
	public void setTime(long time) {
		this.time = time;
	}
	public long getMarriageid() {
		return marriageid;
	}
	public void setMarriageid(long marriageid) {
		this.marriageid = marriageid;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public List<RedEnvelope> getRedenvelopes() {
		return redenvelopes;
	}
	public void setRedenvelopes(List<RedEnvelope> redenvelopes) {
		this.redenvelopes = redenvelopes;
	}
	public int getRedsum() {
		return redsum;
	}
	public void setRedsum(int redsum) {
		this.redsum = redsum;
	}
	/**
	 * 玩家是否领取红包(玩家ID)
	 * @return
	 */
	public List<Long> getRedreceives() {
		return redreceives;
	}
	/**
	 * 玩家是否领取红包(玩家ID)
	 * @return
	 */
	public void setRedreceives(List<Long> redreceives) {
		this.redreceives = redreceives;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

}
