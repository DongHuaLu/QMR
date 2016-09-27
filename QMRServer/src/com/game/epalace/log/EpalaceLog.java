package com.game.epalace.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class EpalaceLog  extends BaseLogBean {
	private long playerid;		//玩家ID
	private int lastnum;		//剩余投掷次数
	private int walknum;		//行走步数
	private int targetlattice;		//目标格子
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
	private static final Logger logger=Logger.getLogger("EpalaceLog");
	
	
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





	@Log(logField="lastnum",fieldType="integer")
	public int getLastnum() {
		return lastnum;
	}






	public void setLastnum(int lastnum) {
		this.lastnum = lastnum;
	}





	@Log(logField="walknum",fieldType="integer")
	public int getWalknum() {
		return walknum;
	}






	public void setWalknum(int walknum) {
		this.walknum = walknum;
	}





	@Log(logField="targetlattice",fieldType="integer")
	public int getTargetlattice() {
		return targetlattice;
	}






	public void setTargetlattice(int targetlattice) {
		this.targetlattice = targetlattice;
	}
	
	

	
	
	
	
	
}
