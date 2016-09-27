package com.game.task.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.server.log.ServerOnlineCountLog;

public class MainTaskLog extends BaseLogBean {
	private long   roleId;
	private String userId;
	
	
	private long   finishmodelId;
	private String finishtaskInfo;
	private String finishbeforeReceiveAble;
	private String finishafterReceiveAble;
	private long   finishonlinetime;
	private int    finishlevel;
	
	
	private long   acceptmodelId;
	private String accepttaskInfo;
	private String acceptbeforeReceiveAble;
	private String acceptafterReceiveAble;
	private long   acceptonlinetime;
	private int    acceptlevel;
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
	private static final Logger logger=Logger.getLogger("MainTaskLog");
	
	@Log(logField="userid",fieldType="varchar(512)")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(logField="roleId",fieldType="bigint")
	public long getRoleId() {
		return roleId;
	}

	@Log(logField="fshmodelId",fieldType="int")
	public long getFinishmodelId() {
		return finishmodelId;
	}
	@Log(logField="fshtaskInfo",fieldType="longtext")
	public String getFinishtaskInfo() {
		return finishtaskInfo;
	}
	@Log(logField="fshbeforeReceiveAble",fieldType="longtext")
	public String getFinishbeforeReceiveAble() {
		return finishbeforeReceiveAble;
	}
	@Log(logField="fshafterReceiveAble",fieldType="longtext")
	public String getFinishafterReceiveAble() {
		return finishafterReceiveAble;
	}
	@Log(logField="fshonlinetime",fieldType="bigint")
	public long getFinishonlinetime() {
		return finishonlinetime;
	}
	@Log(logField="fshlevel",fieldType="int")
	public int getFinishlevel() {
		return finishlevel;
	}
	@Log(logField="accmodelId",fieldType="int")
	public long getAcceptmodelId() {
		return acceptmodelId;
	}
	@Log(logField="acctaskInfo",fieldType="longtext")
	public String getAccepttaskInfo() {
		return accepttaskInfo;
	}
	@Log(logField="accbeforeReceiveAble",fieldType="longtext")
	public String getAcceptbeforeReceiveAble() {
		return acceptbeforeReceiveAble;
	}
	@Log(logField="accafterReceiveAble",fieldType="longtext")
	public String getAcceptafterReceiveAble() {
		return acceptafterReceiveAble;
	}
	@Log(logField="acconlinetime",fieldType="bigint")
	public long getAcceptonlinetime() {
		return acceptonlinetime;
	}
	@Log(logField="acclevel",fieldType="int")
	public int getAcceptlevel() {
		return acceptlevel;
	}

	public void setFinishmodelId(long finishmodelId) {
		this.finishmodelId = finishmodelId;
	}

	public void setFinishtaskInfo(String finishtaskInfo) {
		this.finishtaskInfo = finishtaskInfo;
	}

	public void setFinishbeforeReceiveAble(String finishbeforeReceiveAble) {
		this.finishbeforeReceiveAble = finishbeforeReceiveAble;
	}

	public void setFinishafterReceiveAble(String finishafterReceiveAble) {
		this.finishafterReceiveAble = finishafterReceiveAble;
	}

	public void setAcceptmodelId(long acceptmodelId) {
		this.acceptmodelId = acceptmodelId;
	}

	public void setAccepttaskInfo(String accepttaskInfo) {
		this.accepttaskInfo = accepttaskInfo;
	}

	public void setAcceptbeforeReceiveAble(String acceptbeforeReceiveAble) {
		this.acceptbeforeReceiveAble = acceptbeforeReceiveAble;
	}

	public void setAcceptafterReceiveAble(String acceptafterReceiveAble) {
		this.acceptafterReceiveAble = acceptafterReceiveAble;
	}

	public void setFinishonlinetime(long finishonlinetime) {
		this.finishonlinetime = finishonlinetime;
	}

	public void setFinishlevel(int finishlevel) {
		this.finishlevel = finishlevel;
	}

	public void setAcceptonlinetime(long acceptonlinetime) {
		this.acceptonlinetime = acceptonlinetime;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public void setAcceptlevel(int acceptlevel) {
		this.acceptlevel = acceptlevel;
	}
	
}
