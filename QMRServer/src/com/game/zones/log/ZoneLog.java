package com.game.zones.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**副本通关记录
 * 
 * @author zhangrong
 *
 */
public class ZoneLog  extends BaseLogBean {
	private long playerid;		//玩家ID
	
	private int zonemodelid;	//通关副本ID
	
	private int type ;	//扫荡类型 0手动，1自动，2连续扫荡 3多人副本
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
	private static final Logger logger=Logger.getLogger("ZoneLog");
	
	
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



	@Log(logField="zonemodelid",fieldType="integer")
	public int getZonemodelid() {
		return zonemodelid;
	}


	public void setZonemodelid(int zonemodelid) {
		this.zonemodelid = zonemodelid;
	}

/**扫荡类型 0手动，1自动，2连续扫荡
 * 
 * @return
 */
	@Log(logField="type",fieldType="integer")
	public int getType() {
		return type;
	}


/**扫荡类型 0手动，1自动，2连续扫荡
 * 
 * @param type
 */
	public void setType(int type) {
		this.type = type;
	}

	
	
	
}
