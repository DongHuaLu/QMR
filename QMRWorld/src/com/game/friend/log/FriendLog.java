package com.game.friend.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**
 * 好友日志
 * @author 杨洪岚
 */
public class FriendLog extends BaseLogBean {
	
	private long friendId;		//记录日志的玩家ID
	private String friendName;	//玩家名字
	private String logString;	//日志内容
	private Logger logger=Logger.getLogger("FriendLog");
	@Override
	public void logToFile() {
		logger.info(buildSql());
	}
//	@Override
//	public String getTableName() {
//		return "friendLog";
//	}

	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	/**
	 * @return the friendId
	 */
	@Log(logField="friendId",fieldType="bigint")
	public long getFriendId() {
		return friendId;
	}

	/**
	 * @param friendId the friendId to set
	 */
	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	/**
	 * @return the friendName
	 */
	@Log(logField="friendName",fieldType="varchar(40)")
	public String getFriendName() {
		return friendName;
	}

	/**
	 * @param friendName the friendName to set
	 */
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	/**
	 * @return the logString
	 */
	@Log(logField="logstring",fieldType="text")
	public String getLogString() {
		return logString;
	}

	/**
	 * @param logString the logString to set
	 */
	public void setLogString(String logString) {
		this.logString = logString;
	}


	
}
