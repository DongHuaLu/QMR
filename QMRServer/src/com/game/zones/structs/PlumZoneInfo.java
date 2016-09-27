package com.game.zones.structs;

import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_meihuaxuanwuBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;


public class PlumZoneInfo {

	private int zhenxingid;		//梅花桩阵型id
	private int hitnum;		//总击破数
	private int exp;		//累积经验
	private int maxcombo;		//最大连击数
	private int curcombo;		//当前连击数
	private int bosshp;		//扣掉的bosshp
	private int remaintime;		//梅花桩剩余时间
	private int zonetime;		//副本剩余时间

	public int getZhenxingid() {
		return zhenxingid;
	}

	public void setZhenxingid(int zhenxingid) {
		this.zhenxingid = zhenxingid;
	}

	public int getBosshp() {
		return bosshp;
	}

	public void setBosshp(int bosshp) {
		this.bosshp = bosshp;
	}

	public int getCurcombo() {
		return curcombo;
	}

	public void setCurcombo(int curcombo) {
		this.curcombo = curcombo;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getHitnum() {
		return hitnum;
	}

	public void setHitnum(int hitnum) {
		this.hitnum = hitnum;
	}

	public int getMaxcombo() {
		return maxcombo;
	}

	public void setMaxcombo(int maxcombo) {
		this.maxcombo = maxcombo;
	}

	public int getRemaintime() {
		return remaintime;
	}

	public void setRemaintime(int remaintime) {
		this.remaintime = remaintime;
	}

	public int getZonetime() {
		return zonetime;
	}

	public void setZonetime(int zonetime) {
		this.zonetime = zonetime;
	}

	/*
	 * 计算剩余时间：=梅花桩时间=副本时间
	 */
	public void calTime(ZoneContext zoneContext) {
		Integer beanid = (Integer) zoneContext.getOthers().get("bean");
		if (beanid == null || beanid == 0) {
			return;
		}
		Q_meihuaxuanwuBean bean = DataManager.getInstance().q_meihuaxuanwuContainer.getMap().get(beanid);
		if (bean == null) {
			return;
		}
		Long beginzonetime = (Long) zoneContext.getOthers().get("beginzonetime");
		if (beginzonetime == null || beginzonetime == 0) {
			return;
		}
		if (beginzonetime + bean.getQ_limitneedtime() * 1000 >= System.currentTimeMillis()) {
			long time = beginzonetime + bean.getQ_limitneedtime() * 1000 - System.currentTimeMillis();
			setRemaintime((int) (time / 1000));
		} else {
			setRemaintime(0);
		}
		if (getHitnum() >= bean.getQ_limitneedhit()) {
			setRemaintime(0);
		}
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(3004);
		if (zonedata == null) {
			return;
		}
		if (beginzonetime + zonedata.getQ_exist_time() >= System.currentTimeMillis()) {
			long time = beginzonetime + zonedata.getQ_exist_time() - System.currentTimeMillis();
			setZonetime((int) (time / 1000));
		} else {
			setZonetime(0);
		}
	}
}