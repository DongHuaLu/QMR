package com.game.stalls.structs;

import com.game.backpack.structs.Item;
import com.game.object.GameObject;

public class StallsItem extends GameObject {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2371033871997072605L;

	//金币价格
	private int pricegold;
	
	//元宝价格
	private int priceyuanbao;
	
	//出售的道具信息
	private Item item;

	//道具锁   当前时间+10秒
	private transient byte itemlockstatus;
	
	//最后购买者ID
	private transient long Lastbuyid;
	
	
	
	public int getPricegold() {
		return pricegold;
	}

	public void setPricegold(int pricegold) {
		this.pricegold = pricegold;
	}

	public int getPriceyuanbao() {
		return priceyuanbao;
	}

	public void setPriceyuanbao(int priceyuanbao) {
		this.priceyuanbao = priceyuanbao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public byte getItemlockstatus() {
		return itemlockstatus;
	}

	public void setItemlockstatus(byte itemlockstatus) {
		this.itemlockstatus = itemlockstatus;
	}

	public long getLastbuyid() {
		return Lastbuyid;
	}

	public void setLastbuyid(long lastbuyid) {
		Lastbuyid = lastbuyid;
	}

}
