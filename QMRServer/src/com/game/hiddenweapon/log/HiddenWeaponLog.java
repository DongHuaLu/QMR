package com.game.hiddenweapon.log;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;


//暗器日志
public class HiddenWeaponLog extends BaseLogBean {

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	
	private int sid; //服务器id
	private String userid; 
	private String roleid;
	private String username;
	private int beforerank; //之前阶数
	private int afterrank; //之后阶数
	private int beforeexp; //之前经验 
	private int afterexp; //之后经验
	private int itemmodel; //消耗道具id
	private int itemnum; //消耗道具数量
	private int costtype; //消耗类型 1-扣道具 2-自动元宝购买 3-激活扣道具
	private long actionid;
	
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}
	
	@Log(logField="userid",fieldType="varchar(1024)")
	public String getUserid() {
		return userid;
	}

	@Log(logField="roleid",fieldType="varchar(1024)")
	public String getRoleid() {
		return roleid;
	}

	@Log(logField="username",fieldType="varchar(1024)")
	public String getUsername() {
		return username;
	}

	@Log(logField="beforerank",fieldType="int")
	public int getBeforerank() {
		return beforerank;
	}

	@Log(logField="afterrank",fieldType="int")
	public int getAfterrank() {
		return afterrank;
	}

	@Log(logField="beforeexp",fieldType="int")
	public int getBeforeexp() {
		return beforeexp;
	}

	@Log(logField="afterexp",fieldType="int")
	public int getAfterexp() {
		return afterexp;
	}
	
	@Log(logField="itemmodel",fieldType="int")
	public int getItemmodel() {
		return itemmodel;
	}
	
	@Log(logField="itemnum",fieldType="int")
	public int getItemnum() {
		return itemnum;
	}
	
	@Log(logField="costtype",fieldType="int")
	public int getCosttype() {
		return costtype;
	}
	
	@Log(logField="actionid",fieldType="bigint")
	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setBeforerank(int beforerank) {
		this.beforerank = beforerank;
	}

	public void setAfterrank(int afterrank) {
		this.afterrank = afterrank;
	}

	public void setBeforeexp(int beforeexp) {
		this.beforeexp = beforeexp;
	}

	public void setAfterexp(int afterexp) {
		this.afterexp = afterexp;
	}

	public void setItemmodel(int itemmodel) {
		this.itemmodel = itemmodel;
	}

	public void setItemnum(int itemnum) {
		this.itemnum = itemnum;
	}

	public void setCosttype(int costtype) {
		this.costtype = costtype;
	}
	
}
