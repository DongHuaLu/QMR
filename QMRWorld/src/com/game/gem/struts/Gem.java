package com.game.gem.struts;

import com.game.object.GameObject;

public class Gem extends GameObject{

	private static final long serialVersionUID = 1678284698187638432L;
	private int exp;
	private int level;
	private short failactnum;	//宝石激活失败次数
	private byte isact;	//宝石是否激活，0未激活，1已激活
	private byte grid;

	public int getExp() {
		return exp;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**宝石激活失败次数
	 * 
	 * @return
	 */
	public short getFailactnum() {
		return failactnum;
	}
	
	public void setFailactnum(short failactnum) {
		this.failactnum = failactnum;
	}
	
	/**宝石是否激活，0未激活，1已激活
	 * 
	 * @return
	 */
	
	public byte getIsact() {
		return isact;
	}
	public void setIsact(byte isact) {
		this.isact = isact;
	}
	/**宝石位置
	 * 
	 * @return
	 */
	public byte getGrid() {
		return grid;
	}

	public void setGrid(byte grid) {
		this.grid = grid;
	}

	
	
	
	
	
	
	
}
