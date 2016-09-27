package com.game.spirittree.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**浇水日志
 * 发生时间，区服ID，果实唯一序列号，浇水者玩家ID，被浇水者玩家ID，浇水者获得经验奖励数量
 * @author zhangrong
 *
 */
public class SpiritWateringLog extends BaseLogBean{

	private long playerid;		//浇水者玩家ID
	private long hostplayerid	;//果实主人ID
	private long fruitid;		//果实ID
	private int exp;	//浇水者获得经验奖励数量
	
	
	
	private Logger logger=Logger.getLogger("SpiritWateringLog");
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
	
	
	
	@Log(logField="hostplayerid",fieldType="bigint")
	public long getHostplayerid() {
		return hostplayerid;
	}

	public void setHostplayerid(long hostplayerid) {
		this.hostplayerid = hostplayerid;
	}

	
	@Log(logField="fruitid",fieldType="bigint")
	public long getFruitid() {
		return fruitid;
	}

	public void setFruitid(long fruitid) {
		this.fruitid = fruitid;
	}

	
	@Log(logField="exp",fieldType="integer")
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
}
