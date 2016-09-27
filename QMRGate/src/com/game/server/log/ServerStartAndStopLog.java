package com.game.server.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-8-17 下午11:24:42
 */
public class ServerStartAndStopLog extends BaseLogBean {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ServerStartAndStopLog.class);

	private String action;
	private String datatime;
	private String local;
	private String ServerId;
	private String appName;
	private int consuming;
	private String identity;
	private String pid;
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.YEAR;
	}
	@Log(fieldType="varchar(255)",logField="action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Log(fieldType="varchar(255)",logField="datatime")
	public String getDatatime() {
		return datatime;
	}
	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}
	@Log(fieldType="varchar(255)",logField="local")
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	@Log(fieldType="varchar(255)",logField="serverid")
	public String getServerId() {
		return ServerId;
	}
	public void setServerId(String serverId) {
		ServerId = serverId;
	}
	@Log(fieldType="varchar(255)",logField="consuming")
	public int getConsuming() {
		return consuming;
	}
	public void setConsuming(int consuming) {
		this.consuming = consuming;
	}
	@Log(fieldType="varchar(255)",logField="appname")
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	@Log(fieldType="varchar(255)",logField="identity")
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	@Log(fieldType="varchar(255)",logField="pid")
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@Override
	public void logToFile() {
		logger.info(buildSql());
	}
}
