package com.game.toplist.structs;

/**
 * 副本排行
 *
 * @author 杨鸿岚
 */
public abstract class ZoneTop {

	private long id;
	private String name;
	private int zoneid;	//副本id
	
	public ZoneTop(){
	}

	public ZoneTop(long id, String name, int zoneid) {
		this.id = id;
		this.name = name;
		this.zoneid = zoneid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getZoneid() {
		return zoneid;
	}

	public void setZoneid(int zoneid) {
		this.zoneid = zoneid;
	}

	public int compare(ZoneTop zoneTop) {
		if (zoneTop.getId() > this.getId()) {
			return -1;
		} else if (zoneTop.getId() == this.getId()) {
			return 0;
		} else {
			return 1;
		}
	}
}
