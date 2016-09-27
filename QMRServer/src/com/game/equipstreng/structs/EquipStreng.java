package com.game.equipstreng.structs;

import com.game.object.GameObject;

public class EquipStreng extends GameObject{


	private static final long serialVersionUID = 5287720726473468904L;
	
	//剩余时间
	private int starttime;
	//强化中的 道具唯一ID
	private long itemid;
	//强化结果，1成功，0失败
	private byte result;
	
	
	
	
	
	/**
	 * 剩余时间（秒）
	 * @return
	 */
	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	
	/**强化中的 道具唯一ID
	 * 
	 * @return
	 */
	public long getItemid() {
		return itemid;
	}
	public void setItemid(long itemid) {
		this.itemid = itemid;
	}
	
	/**
	 * 强化结果，1成功，0失败
	 * @return
	 */
	
	public byte getResult() {
		return result;
	}
	/**
	 * 强化结果，1成功，0失败
	 * @return
	 */
	public void setResult(byte result) {
		this.result = result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
