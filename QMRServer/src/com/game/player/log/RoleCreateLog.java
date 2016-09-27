package com.game.player.log;

import java.util.Date;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;

/**
 * 角色创建日志
 * @author 赵聪慧
 *
 */
public class RoleCreateLog extends BaseLogBean {
	private String date;
	private long id;
	private byte sex;
	private String name;
	private String userId;
	private String optIp;
	private String agentPlusdata;
	private String agentColdatas;
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
	private static final Logger logger=Logger.getLogger("RoleCreateLog");
	
	@Log(logField="userid",fieldType="varchar(512)")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public RoleCreateLog(){
		
	}
	public RoleCreateLog (Player player){
		date= sdf.format(new Date());
		id=player.getId();
		sex=player.getSex();
		name=player.getName();		
		userId=player.getUserId();
		optIp=player.getLoginIp();
		agentColdatas=player.getAgentColdatas();
		agentPlusdata=player.getAgentPlusdata();
		setSid(player.getCreateServerId());
	}
	
	@Log(logField="createdate",fieldType="varchar(255)")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Log(logField="roleid",fieldType="bigint")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Log(logField="rolesex",fieldType="int")
	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}
	@Log(logField="name",fieldType="varchar(50)")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Log(logField="optip",fieldType="varchar(100)")
	public String getOptIp() {
		return optIp;
	}
	public void setOptIp(String optIp) {
		this.optIp = optIp;
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
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

}
