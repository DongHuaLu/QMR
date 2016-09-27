package com.game.card.log;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import org.apache.log4j.Logger;

/**
 * CDKEY日志
 *
 * @author 杨鸿岚
 */
public class CardLog extends BaseLogBean {

	private Logger logger=Logger.getLogger("CardLog");
	
	private long playerid;		//记录日志的玩家ID
	private int argid;		//平台id
	private int type;		//卡使用类型
	private int server;		//区服务器id
	private int useresult;		//使用结果
	private String card;		//卡号
	private int sid;
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.info(buildSql());
	}

	@Log(logField="sid",fieldType="int")
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

	@Log(logField="argid",fieldType="int")
	public int getArgid() {
		return argid;
	}

	public void setArgid(int argid) {
		this.argid = argid;
	}

	@Log(logField="type",fieldType="int")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Log(logField="server",fieldType="int")
	public int getServer() {
		return server;
	}

	public void setServer(int server) {
		this.server = server;
	}

	@Log(logField="useresult",fieldType="int")
	public int getUseresult() {
		return useresult;
	}

	public void setUseresult(int useresult) {
		this.useresult = useresult;
	}

	@Log(logField="card",fieldType="varchar(128)")
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
}
