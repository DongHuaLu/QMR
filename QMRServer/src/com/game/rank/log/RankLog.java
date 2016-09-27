package com.game.rank.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class RankLog  extends BaseLogBean {
	private long playerid;		//玩家ID
	
	private int oldrankexp	;  //原军功值
	
	private int newrankexp; //新加的军功值
	
	private int oldranklevel;  //原军衔
	
	private int newranklevel; //新军衔
	
	private int reason ; //原因
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
	private static final Logger logger=Logger.getLogger("RankLog");
	
	
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

	@Log(logField="oldrankexp",fieldType="integer")
	public int getOldrankexp() {
		return oldrankexp;
	}


	public void setOldrankexp(int oldrankexp) {
		this.oldrankexp = oldrankexp;
	}

	@Log(logField="newrankexp",fieldType="integer")
	public int getNewrankexp() {
		return newrankexp;
	}


	public void setNewrankexp(int newrankexp) {
		this.newrankexp = newrankexp;
	}

	@Log(logField="oldranklevel",fieldType="integer")
	public int getOldranklevel() {
		return oldranklevel;
	}


	public void setOldranklevel(int oldranklevel) {
		this.oldranklevel = oldranklevel;
	}

	@Log(logField="newranklevel",fieldType="integer")
	public int getNewranklevel() {
		return newranklevel;
	}


	public void setNewranklevel(int newranklevel) {
		this.newranklevel = newranklevel;
	}

	@Log(logField="reason",fieldType="integer")
	public int getReason() {
		return reason;
	}


	public void setReason(int reason) {
		this.reason = reason;
	}
	
	

	
	
	

	
	
	
	
	
}
