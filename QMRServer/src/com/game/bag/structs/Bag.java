package com.game.bag.structs;

import java.util.concurrent.ConcurrentHashMap;

import com.game.backpack.structs.Item;
import com.game.object.GameObject;

public class Bag extends GameObject {
	
	private static final long serialVersionUID = 3231047845365822356L;

	private ConcurrentHashMap<String, Item> items = new ConcurrentHashMap<String, Item>();
	
	private int cellNum = 100;
	
	private int cellTime = 0;
	
	private boolean locked = false;
	
	public ConcurrentHashMap<String, Item> getItems() {
		return items;
	}
	public void setItems(ConcurrentHashMap<String, Item> items) {
		this.items = items;
	}
	
	public int getCellNum() {
		return cellNum;
	}
	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}
	
	public int getCellTime() {
		return cellTime;
	}
	public void setCellTime(int cellTime) {
		this.cellTime = cellTime;
	}
	
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
}
