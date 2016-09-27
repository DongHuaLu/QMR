package com.game.guild.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 公会日志
 * @author 杨洪岚
 */
public class GuildLog extends BaseLogBean {

	private long guildId;		//公会ID
	private String guildName;	//公会名字
	private String runName;		//执行者名字
	private String logString;	//日志内容
	
//	@Override
//	public String getTableName() {
//		return "guildLog";
//	}
	private Logger logger=Logger.getLogger("GuildLog");
	@Override
	public void logToFile() {
		logger.info(buildSql());
	}
	/**
	 * 分表时间
	 */
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	/**
	 * @return the guildId
	 */
	@Log(logField="guildid",fieldType="bigint")
	public long getGuildId() {
		return guildId;
	}

	/**
	 * @param guildId the guildId to set
	 */
	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}

	/**
	 * @return the guildName
	 */
	@Log(logField="guildname",fieldType="varchar(40)")
	public String getGuildName() {
		return guildName;
	}

	/**
	 * @param guildName the guildName to set
	 */
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	/**
	 * @return the runName
	 */
	@Log(logField="runname",fieldType="varchar(40)")
	public String getRunName() {
		return runName;
	}

	/**
	 * @param runName the runName to set
	 */
	public void setRunName(String runName) {
		this.runName = runName;
	}

	/**
	 * @return the logString
	 */
	@Log(logField="logstring",fieldType="text")
	public String getLogString() {
		return logString;
	}

	/**
	 * @param logString the logString to set
	 */
	public void setLogString(String logString) {
		this.logString = logString;
	}
	
}
