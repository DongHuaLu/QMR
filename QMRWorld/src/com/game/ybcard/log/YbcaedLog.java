package com.game.ybcard.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class YbcaedLog  extends BaseLogBean {
	private long playerid;		//玩家角色ID
	private String username;	//玩家账户名
	private int yuanbao ;	//定价元宝
	private int type ;	//1成功，0失败
	private int sid;
	private Logger logger=Logger.getLogger("YbcaedLog");
	@Override
	public void logToFile() {
		logger.info(buildSql());
	}
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	
	@Log(fieldType="int",logField="sid")
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	@Log(logField="playerid",fieldType="bigint")
	public long getPlayerid() {
		return playerid;
	}

	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}



	@Log(logField="username",fieldType="varchar(255)")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Log(logField="yuanbao",fieldType="integer")
	public int getYuanbao() {
		return yuanbao;
	}

	public void setYuanbao(int yuanbao) {
		this.yuanbao = yuanbao;
	}

	@Log(logField="type",fieldType="integer")
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	
	
}