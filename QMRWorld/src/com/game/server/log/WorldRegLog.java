package com.game.server.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;


//37wan用户在游戏内注册时间
public class WorldRegLog extends BaseLogBean{

	private long userid;	//帐号名
	private int createserver; //生成服务器
	private long revisetime;	//注册时间
	private String username; //账号
	private int type;	//类型，0游客转化，1正常途径登录
	private Logger logger=Logger.getLogger("WorldRegLog");
	@Override
	public void logToFile() {
		logger.info(buildSql());
	}
	
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(logField="username",fieldType="varchar(255)")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Log(logField="createserver",fieldType="integer")
	public int getCreateserver() {
		return createserver;
	}

	public void setCreateserver(int createserver) {
		this.createserver = createserver;
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
	
	
	
	
	
	















}
