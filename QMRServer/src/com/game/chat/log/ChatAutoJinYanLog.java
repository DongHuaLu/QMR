package com.game.chat.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 
 * @author 赵聪慧
 * @2012-11-21 下午10:53:41
 */
public class ChatAutoJinYanLog extends BaseLogBean {
	//角色ID
	private long roleid;
	//禁言开始时间 
	private long startTime;
	//禁言时长
	private long jiyanlong;
	//触发禁言的方式 
	private int reason;
	//触发禁言的发言内容
	private String content;
	//服务器ID
	private int sid;
	
	
	@Log(fieldType="bigint",logField="roleid")
	public long getRoleid() {
		return roleid;
	}

	@Log(fieldType="bigint",logField="starttime")
	public long getStartTime() {
		return startTime;
	}
	@Log(fieldType="bigint",logField="jinyanlong")
	public long getJiyanlong() {
		return jiyanlong;
	}
	@Log(fieldType="bigint",logField="reason")
	public int getReason() {
		return reason;
	}
	@Log(fieldType="varchar(1024)",logField="content")
	public String getContent() {
		return content;
	}
	@Log(fieldType="int",logField="sid")
	public int getSid() {
		return sid;
	}

	
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setJiyanlong(long jiyanlong) {
		this.jiyanlong = jiyanlong;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChatAutoJinYanLog.class);

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.YEAR;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
		
	}
	

}
