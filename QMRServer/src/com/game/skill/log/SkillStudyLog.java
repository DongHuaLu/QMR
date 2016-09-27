package com.game.skill.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.server.log.ServerOnlineCountLog;

public class SkillStudyLog extends BaseLogBean {
	private int skillmodelId;
	private long resumebookid;
	private long roleId;
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("SkillStudyLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	@Log(fieldType="int",logField="skillmodelId")
	public int getSkillmodelId() {
		return skillmodelId;
	}
	public void setSkillmodelId(int skillmodelId) {
		this.skillmodelId = skillmodelId;
	}
	@Log(fieldType="bigint",logField="resumebook")
	public long getResumebookid() {
		return resumebookid;
	}
	public void setResumebookid(long resumebookid) {
		this.resumebookid = resumebookid;
	}
	@Log(fieldType="bigint",logField="roleId")
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
