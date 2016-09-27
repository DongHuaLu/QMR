package com.game.mail.log;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import org.apache.log4j.Logger;

/**
 * 邮件日志
 * @author 杨鸿岚
 */
public class MailLog extends BaseLogBean {
	
	private static final Logger logger=Logger.getLogger("MailLog");

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	
	private int logtype;
	private long eventid;
	private String account;
	private long eventtime;
	private long sendtime;
	private long roleid;
	private String rolename;
	private int rolelevel;
	private String sendername;
	private String title;
	private String content;
	private int goldtype;
	private int money;
	private String itemdata;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Log(logField="account",fieldType="varchar(50)")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Log(logField="content",fieldType="varchar(500)")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Log(logField="eventid",fieldType="bigint")
	public long getEventid() {
		return eventid;
	}

	public void setEventid(long eventid) {
		this.eventid = eventid;
	}

	@Log(logField="eventtime",fieldType="bigint")
	public long getEventtime() {
		return eventtime;
	}

	public void setEventtime(long eventtime) {
		this.eventtime = eventtime;
	}

	@Log(logField="goldtype",fieldType="integer")
	public int getGoldtype() {
		return goldtype;
	}

	public void setGoldtype(int goldtype) {
		this.goldtype = goldtype;
	}

	@Log(logField="itemdata",fieldType="text")
	public String getItemdata() {
		return itemdata;
	}

	public void setItemdata(String itemdata) {
		this.itemdata = itemdata;
	}

	@Log(logField="logtype",fieldType="integer")
	public int getLogtype() {
		return logtype;
	}

	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}

	@Log(logField="money",fieldType="integer")
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Log(logField="roleid",fieldType="bigint")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	@Log(logField="rolelevel",fieldType="integer")
	public int getRolelevel() {
		return rolelevel;
	}

	public void setRolelevel(int rolelevel) {
		this.rolelevel = rolelevel;
	}

	@Log(logField="rolename",fieldType="varchar(50)")
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Log(logField="sendername",fieldType="varchar(50)")
	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	@Log(logField="sendtime",fieldType="bigint")
	public long getSendtime() {
		return sendtime;
	}

	public void setSendtime(long sendtime) {
		this.sendtime = sendtime;
	}

	@Log(logField="title",fieldType="varchar(50)")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
