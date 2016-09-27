package com.game.spirittree.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**仙露浇灌日志
 * 发生时间，区服ID，果实唯一序列号，玩家ID，加速时间幅度
 * @author zhangrong
 *
 */
public class SpiritXianLuLog  extends BaseLogBean{
	private long playerid;		//玩家ID
	private int acceleratetime;	//加速时间幅度（秒）
	private long fruitid;//果实ID
	
	private Logger logger=Logger.getLogger("SpiritXianLuLog");
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
	@Log(logField="acceleratetime",fieldType="integer")
	public int getAcceleratetime() {
		return acceleratetime;
	}

	public void setAcceleratetime(int acceleratetime) {
		this.acceleratetime = acceleratetime;
	}
	
	
	@Log(logField="fruitid",fieldType="bigint")
	public long getFruitid() {
		return fruitid;
	}

	public void setFruitid(long fruitid) {
		this.fruitid = fruitid;
	}
	
	
}
