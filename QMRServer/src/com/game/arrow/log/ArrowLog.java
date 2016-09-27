package com.game.arrow.log;

import com.alibaba.fastjson.JSON;
import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;
import org.apache.log4j.Logger;

/**
 * 弓箭
 *
 * @author 杨鸿岚
 */
public class ArrowLog extends BaseLogBean {

	private static final Logger logger = Logger.getLogger("ArrowLog");

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}

	public ArrowLog(Player player, int type, int fstype, int fsnum, int reason) {
		this.userid = Long.valueOf(player.getUserId());
		this.roleid = player.getId();
		this.type = type;
		this.arrowInfo = JSON.toJSONString(player.getArrowData());
		this.fstype = fstype;
		this.fsnum = fsnum;
		this.reason = reason;
		this.sid=player.getCreateServerId();
	}
	
	public ArrowLog(){
		
	}
	
	private long userid;		//用户id
	private long roleid;		//角色id
	private int type;		//1 弓 2 箭支 3 战魂搜索 4 战魂改变
	private String arrowInfo;	//弓箭数据
	private int fstype;		//战魂类型
	private int fsnum;		//当前战魂值
	private int reason;		//原因
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Log(fieldType="bigint",logField="roleid")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	@Log(fieldType="bigint",logField="userid")
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	@Log(fieldType="text",logField="arrowInfo")
	public String getArrowInfo() {
		return arrowInfo;
	}

	public void setArrowInfo(String arrowInfo) {
		this.arrowInfo = arrowInfo;
	}

	@Log(fieldType="int",logField="fsnum")
	public int getFsnum() {
		return fsnum;
	}

	public void setFsnum(int fsnum) {
		this.fsnum = fsnum;
	}

	@Log(fieldType="int",logField="fstype")
	public int getFstype() {
		return fstype;
	}

	public void setFstype(int fstype) {
		this.fstype = fstype;
	}

	@Log(fieldType="int",logField="reason")
	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	@Log(fieldType="int",logField="type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
