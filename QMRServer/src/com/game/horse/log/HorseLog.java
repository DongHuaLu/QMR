package com.game.horse.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class HorseLog extends BaseLogBean {
	private long playerId;		//玩家ID
	private int layer;			//最高坐骑阶数
	private int blessvalue;		//本次加的祝福值
	private int dayupnum ;		//当前进阶次数
	private int failexp;		//进阶失败获的经验
	private int isupsuccess;		//是否进阶成功，0失败，1成功
	private int money;				//扣金币
	private int itemid;				//扣道具ID
	private int itemnum;			//道具数量
	private int yuanbao;			//道具不足的时候购买道具花费元宝
	private int yblessvalue;			//原祝福值
	private int newblessvalue;		//最后总的祝福值
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("HorseLog");
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	//@Log(logField="layer",fieldType="integer")
	//@Log(logField="goodsName",fieldType="varchar(40)")
	//@Log(logField="goodsOnlyid",fieldType="bigint")
	
	

	@Log(logField="playerId",fieldType="bigint")
	public long getPlayerId() {
		return playerId;
	}


	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	@Log(logField="layer",fieldType="integer")
	public int getLayer() {
		return layer;
	}


	public void setLayer(int layer) {
		this.layer = layer;
	}


	@Log(logField="blessvalue",fieldType="integer")
	public int getBlessvalue() {
		return blessvalue;
	}


	public void setBlessvalue(int blessvalue) {
		this.blessvalue = blessvalue;
	}

	@Log(logField="dayupnum",fieldType="integer")
	public int getDayupnum() {
		return dayupnum;
	}


	public void setDayupnum(int dayupnum) {
		this.dayupnum = dayupnum;
	}

	@Log(logField="failexp",fieldType="integer")
	public int getFailexp() {
		return failexp;
	}


	public void setFailexp(int failexp) {
		this.failexp = failexp;
	}

	
	@Log(logField="isupsuccess",fieldType="integer")
	public int getIsupsuccess() {
		return isupsuccess;
	}


	public void setIsupsuccess(int isupsuccess) {
		this.isupsuccess = isupsuccess;
	}


	@Log(logField="itemid",fieldType="integer")
	public int getItemid() {
		return itemid;
	}



	public void setItemid(int itemid) {
		this.itemid = itemid;
	}


	@Log(logField="money",fieldType="integer")
	public int getMoney() {
		return money;
	}



	public void setMoney(int money) {
		this.money = money;
	}


	@Log(logField="itemnum",fieldType="integer")
	public int getItemnum() {
		return itemnum;
	}



	public void setItemnum(int itemnum) {
		this.itemnum = itemnum;
	}


	@Log(logField="yuanbao",fieldType="integer")
	public int getYuanbao() {
		return yuanbao;
	}



	public void setYuanbao(int yuanbao) {
		this.yuanbao = yuanbao;
	}


	@Log(logField="yblessvalue",fieldType="integer")
	public int getYblessvalue() {
		return yblessvalue;
	}



	public void setYblessvalue(int yblessvalue) {
		this.yblessvalue = yblessvalue;
	}


	@Log(logField="newblessvalue",fieldType="integer")
	public int getNewblessvalue() {
		return newblessvalue;
	}



	public void setNewblessvalue(int newblessvalue) {
		this.newblessvalue = newblessvalue;
	}

	
	

	
	
	
	
	
	
}
