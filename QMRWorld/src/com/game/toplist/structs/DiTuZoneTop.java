package com.game.toplist.structs;

/**
 * 地图副本排行
 *
 * @author 杨鸿岚
 */
public class DiTuZoneTop extends ZoneTop {

	private long comptime;	//完成时间

	public DiTuZoneTop() {
		super();
	}

	public DiTuZoneTop(long id, String name, long comptime, int zoneid) {
		super(id, name, zoneid);
		this.comptime = comptime;
	}

	public long getComptime() {
		return comptime;
	}

	public void setComptime(long comptime) {
		this.comptime = comptime;
	}

	@Override
	public int compare(ZoneTop zoneTop) {
		if (zoneTop instanceof DiTuZoneTop) {
			DiTuZoneTop diTuZoneTop = (DiTuZoneTop) zoneTop;
			if (diTuZoneTop != null) {
				if (this.getComptime() > diTuZoneTop.getComptime()) {
					return 1;
				} else if (diTuZoneTop.getComptime() == this.getComptime()) {
					return super.compare(zoneTop);
				}
			}
		}
		return -1;
	}
}
