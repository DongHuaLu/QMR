package com.game.spirittree.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**偷窃日志
 * 发生时间，区服ID，果实唯一序列号，偷取者玩家ID，被偷取者玩家ID，偷取的产量比例，被偷取者获得经验补偿数量
 * @author zhangrong
 *
 */
public class SpiritStealLog extends BaseLogBean{
	private long playerid;		//操作的玩家ID
	private long stolenplayerid	;//被偷的玩家ID
	private long fruitid;		//果实ID
	private String rewardinfo;	//奖励内容
	private int stolennum;	//被偷取的数量
	private int exp;	//补偿经验
	private long eventid; //事件ID
	
	private Logger logger=Logger.getLogger("SpiritStealLog");
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

	@Log(logField="stolenplayerid",fieldType="bigint")
	public long getStolenplayerid() {
		return stolenplayerid;
	}

	public void setStolenplayerid(long stolenplayerid) {
		this.stolenplayerid = stolenplayerid;
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
	
	
	@Log(logField="stolennum",fieldType="integer")
	public int getStolennum() {
		return stolennum;
	}

	public void setStolennum(int stolennum) {
		this.stolennum = stolennum;
	}
	@Log(logField="exp",fieldType="integer")
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	@Log(logField="eventid",fieldType="bigint")
	public long getEventid() {
		return eventid;
	}

	public void setEventid(long eventid) {
		this.eventid = eventid;
	}
	
	
	
	
	
}
