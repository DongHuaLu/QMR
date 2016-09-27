package com.game.transactions.structs;

import java.util.HashMap;

public class TsInfo {
	private byte status = 0;
	private int yuanbao = 0;
	private int gold = 0;
	
	HashMap<Long,Integer> items =  new HashMap<Long,Integer>();
	
	
	
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public int getYuanbao() {
		return yuanbao;
	}
	public void setYuanbao(int yuanbao) {
		this.yuanbao = yuanbao;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public HashMap<Long, Integer> getItems() {
		return items;
	}
	public void setItems(HashMap<Long, Integer> items) {
		this.items = items;
	}
}
