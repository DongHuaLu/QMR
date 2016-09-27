package com.game.toplist.structs;

/**
 * 校场副本排行
 *
 * @author 杨鸿岚
 */
public class JiaoChangZoneTop extends ZoneTop {

	private int jifen;	//积分
	private long comptime;	//完成时间
	
	public JiaoChangZoneTop(){
		super();
	}

	public JiaoChangZoneTop(long id, String name, int zoneid, int jifen, long comptime) {
		super(id, name, zoneid);
		this.jifen = jifen;
		this.comptime = comptime;
	}

	public long getComptime() {
		return comptime;
	}

	public void setComptime(long comptime) {
		this.comptime = comptime;
	}

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	@Override
	public int compare(ZoneTop zoneTop) {
		if (zoneTop instanceof JiaoChangZoneTop) {
			JiaoChangZoneTop jiaoChangZoneTop = (JiaoChangZoneTop) zoneTop;
			if (jiaoChangZoneTop != null) {
				if (jiaoChangZoneTop.getJifen() > this.getJifen()) {
					return 1;
				} else if (jiaoChangZoneTop.getJifen() == this.getJifen()) {
					if (this.getComptime() > jiaoChangZoneTop.getComptime()) {
						return 1;
					} else if (jiaoChangZoneTop.getComptime() == this.getComptime()) {
						return super.compare(zoneTop);
					}
				}
			}
		}
		return -1;
	}
}
