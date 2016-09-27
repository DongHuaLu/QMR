package com.game.skill.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.server.log.ServerOnlineCountLog;

public class SkillLevelUpLog extends BaseLogBean{
	private int skillId;
	private int level;
	private String action;
	private int beforeTime;
	private int result;
	private int beforelevel;
	private int resumegold;	
	private String resumegoods;
	private int resumemoney;
	private int resumezhenqi;
	private long roleId;
	private long actionId;
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
	private static final Logger logger=Logger.getLogger("SkillLevelUpLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(fieldType="int",logField="skillId")
	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	@Log(fieldType="int",logField="level")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	@Log(fieldType="varchar(50)",logField="action")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Log(fieldType="int",logField="beforeTime")
	public int getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(int beforeTime) {
		this.beforeTime = beforeTime;
	}
	
	@Log(fieldType="int",logField="resumegold")
	public int getResumegold() {
		return resumegold;
	}

	public void setResumegold(int resumegold) {
		this.resumegold = resumegold;
	}

	@Log(fieldType="int",logField="result")
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	@Log(fieldType="int",logField="beforelevel")
	public int getBeforelevel() {
		return beforelevel;
	}

	public void setBeforelevel(int beforelevel) {
		this.beforelevel = beforelevel;
	}
	@Log(fieldType="varchar(256)",logField="resumegoods")
	public String getResumegoods() {
		return resumegoods;
	}

	public void setResumegoods(String resumegoods) {
		this.resumegoods = resumegoods;
	}

	@Log(fieldType="int",logField="resumemoney")
	public int getResumemoney() {
		return resumemoney;
	}

	public void setResumemoney(int resumemoney) {
		this.resumemoney = resumemoney;
	}
	@Log(fieldType="int",logField="resumezhenqi")
	public int getResumezhenqi() {
		return resumezhenqi;
	}

	public void setResumezhenqi(int resumezhenqi) {
		this.resumezhenqi = resumezhenqi;
	}
	@Log(fieldType="bigint",logField="roleId")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	@Log(fieldType="bigint",logField="actionId")
	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}


	
}
