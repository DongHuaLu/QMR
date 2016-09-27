package com.game.player.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;


//37wan用户在游戏内注册时间
public class RegLog extends BaseLogBean{

	private long userid;	//帐号名
	private int createserver; //生成服务器
	private long revisetime;	//注册时间
	private int type;	//类型，0游客转化，1正常途径登录
	private String username;
	private String agentPlusdata;
	private String agentColdatas;
	private String agent;
	private String ad;
	
	
	private Logger log=Logger.getLogger(RegLog.class);
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(logField="createserver",fieldType="integer")
	public int getCreateserver() {
		return createserver;
	}

	public void setCreateserver(int createserver) {
		this.createserver = createserver;
	}

	@Log(logField="username",fieldType="varchar(255)")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Log(logField="revisetime",fieldType="bigint")
	public long getRevisetime() {
		return revisetime;
	}

	public void setRevisetime(long revisetime) {
		this.revisetime = revisetime;
	}

	
	
	@Log(logField="type",fieldType="integer")
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}

	@Log(logField="userid",fieldType="bigint")
	public long getUserid() {
		return userid;
	}


	public void setUserid(long userid) {
		this.userid = userid;
	}

	@Log(logField="agentplusdata",fieldType="varchar(1024)")
	public String getAgentPlusdata() {
		return agentPlusdata;
	}

	public void setAgentPlusdata(String agentPlusdata) {
		this.agentPlusdata = agentPlusdata;
	}

	@Log(logField="agentcoldatas",fieldType="varchar(1024)")
	public String getAgentColdatas() {
		return agentColdatas;
	}

	public void setAgentColdatas(String agentColdatas) {
		this.agentColdatas = agentColdatas;
	}
	@Log(logField="agent",fieldType="varchar(1024)")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	@Log(logField="ad",fieldType="varchar(1024)")
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	@Override
	public void logToFile() {
		log.info(buildSql());
	}
}
