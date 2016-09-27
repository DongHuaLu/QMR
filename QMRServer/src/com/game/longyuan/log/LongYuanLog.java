package com.game.longyuan.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class LongYuanLog extends BaseLogBean {
	private long playerId;		//玩家ID
	private String longyuanid	;	//星图
	private int money ;		//使用金币
	private int type ;  	//0失败，1成功
	private int exp;		//获得经验
	
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("LongYuanLog");
	
	
	
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


	@Log(logField="money",fieldType="integer")
	public int getMoney() {
		return money;
	}





	public void setMoney(int money) {
		this.money = money;
	}




	@Log(logField="type",fieldType="integer")
	public int getType() {
		return type;
	}





	public void setType(int type) {
		this.type = type;
	}




	@Log(logField="exp",fieldType="integer")
	public int getExp() {
		return exp;
	}





	public void setExp(int exp) {
		this.exp = exp;
	}



	@Log(logField="longyuanid",fieldType="varchar(50)")
	public String getLongyuanid() {
		return longyuanid;
	}




	public void setLongyuanid(String longyuanid) {
		this.longyuanid = longyuanid;
	}
	
	
	
	
	
	
	
}
