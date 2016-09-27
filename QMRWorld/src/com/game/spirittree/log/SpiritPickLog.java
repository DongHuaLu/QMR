package com.game.spirittree.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**采摘日志
 * 
 * @author zhangrong
 *发生时间，区服ID，果实唯一序列号，玩家ID，采摘时的果实剩余产量比例
 */
public class SpiritPickLog extends BaseLogBean{
	private long playerid;		//玩家ID
	private long fruitid;		//果实ID
	private String rewardinfo;	//奖励内容
	private int yield;	//产量比例
	private long eventid; //事件ID
	
	
	
	
	private Logger logger=Logger.getLogger("SpiritPickLog");
	@Override
	public void logToFile() {
		logger.info(buildSql());
	}
	
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	
	
	@Log(logField="playerid",fieldType="bigint")
	public long getPlayerid() {
		return playerid;
	}

	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}

	
	@Log(logField="fruitid",fieldType="bigint")
	public long getFruitid() {
		return fruitid;
	}

	public void setFruitid(long fruitid) {
		this.fruitid = fruitid;
	}

	@Log(logField="rewardinfo",fieldType="text")
	public String getRewardinfo() {
		return rewardinfo;
	}

	public void setRewardinfo(String rewardinfo) {
		this.rewardinfo = rewardinfo;
	}

	
	@Log(logField="yield",fieldType="integer")
	public int getYield() {
		return yield;
	}

	public void setYield(int yield) {
		this.yield = yield;
	}
	@Log(logField="eventid",fieldType="bigint")
	public long getEventid() {
		return eventid;
	}

	public void setEventid(long eventid) {
		this.eventid = eventid;
	}
	
	
	
}
