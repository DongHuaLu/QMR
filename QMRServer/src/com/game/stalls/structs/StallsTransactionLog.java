package com.game.stalls.structs;


import com.game.backpack.structs.Item;
import com.game.object.GameObject;


public class StallsTransactionLog extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 874865736153348269L;

	//交易时间（秒）
	private long transactiontime;
	
	//交易类型，0出售，1购买
	private byte transactiontype;
	
	//交易者名字
	private String tradersname;
	
	//交易者ID
	private long tradersid;
	
	//金币价格
	private int pricegold;
	
	//元宝价格
	private int priceyuanbao;
	
	//道具信息
	private Item item;

	public long getTransactiontime() {
		return transactiontime;
	}

	public void setTransactiontime(long transactiontime) {
		this.transactiontime = transactiontime;
	}

	public byte getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(byte transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getTradersname() {
		return tradersname;
	}

	public void setTradersname(String tradersname) {
		this.tradersname = tradersname;
	}

	public long getTradersid() {
		return tradersid;
	}

	public void setTradersid(long tradersid) {
		this.tradersid = tradersid;
	}

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

	
}
