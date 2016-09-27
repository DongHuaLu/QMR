package com.game.spirittree.structs;

import com.game.object.GameObject;

public class SpiritTreeLog  extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6053746179264021594L;
	
	//发生时间（1970至今 秒）
	private int time ;
	
	//类型  0抢收，1被抢收，2互助（浇水），3互助（被浇水）
	private byte type ;
	
	//操作的组包ID
	private int groupid;
	
	//奖励经验
	private int exp;
	
	//对方ID
	private long otherid;
	
	//最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0 item
	private int itemid;
	
	//被偷数量
	private int itemnum;
	
	
	/**发生时间（1970至今 秒）
	 * 
	 * @param time
	 */
	public int getTime() {
		return time;
	}
	/**发生时间（1970至今 秒）
	 * 
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public long getOtherid() {
		return otherid;
	}
	public void setOtherid(long otherid) {
		this.otherid = otherid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getItemnum() {
		return itemnum;
	}
	public void setItemnum(int itemnum) {
		this.itemnum = itemnum;
	}
	
	
}


