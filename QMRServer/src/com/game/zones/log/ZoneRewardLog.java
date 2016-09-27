package com.game.zones.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**在这里记录剩余奖励
 * 
 * @author zhangrong
 *
 */
public class ZoneRewardLog  extends BaseLogBean {
	private long playerid;		//玩家ID
	
	private int zonemodelid;	//副本ID
	
	private String beforerewardlist ;	//没领取之前的数量

	private String remainderrewardlist ;	//剩余奖励数量

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



	@Log(logField="beforerewardlist",fieldType="text")
	public String getBeforerewardlist() {
		return beforerewardlist;
	}


	public void setBeforerewardlist(String beforerewardlist) {
		this.beforerewardlist = beforerewardlist;
	}

	@Log(logField="remainderrewardlist",fieldType="text")
	public String getRemainderrewardlist() {
		return remainderrewardlist;
	}


	public void setRemainderrewardlist(String remainderrewardlist) {
		this.remainderrewardlist = remainderrewardlist;
	}

	
	
	
}
