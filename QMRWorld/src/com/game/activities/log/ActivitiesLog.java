package com.game.activities.log;

import org.apache.log4j.Logger;

import com.game.activities.bean.ActivityInfo;
import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;

public class ActivitiesLog extends BaseLogBean {

	private Logger logger = Logger.getLogger(ActivitiesLog.class);

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}

	public ActivitiesLog() {
	}

	public ActivitiesLog(Player player, ActivityInfo ac, int type) {
		this.userid = Long.valueOf(player.getUserId());
		this.roleid = player.getId();
		this.activityid = ac.getActivityId();
		this.reward = ac.getActivityReward();
		this.type = type;
	}

	public ActivitiesLog(long userid, long roleid, int activityid, String reward, int type,int serverid) {
		this.userid = userid;
		this.roleid = roleid;
		this.activityid = activityid;
		this.reward = reward;
		this.type = type;
	}
	private long userid;    //用户id
	private long roleid;    //角色id
	private int activityid; //活动id
	private String reward;  //奖励内容
	private int type;       //记录类型  1成功领取奖励

	@Log(fieldType = "bigint", logField = "userid")
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	@Log(fieldType = "bigint", logField = "roleid")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	@Log(fieldType = "int", logField = "activityid")
	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	@Log(fieldType = "varchar(1000)", logField = "reward")
	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	@Log(fieldType = "int", logField = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
