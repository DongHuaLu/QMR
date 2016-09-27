package com.game.player.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;

public class RoleFinancialLog extends BaseLogBean {

	private static final Logger logger=Logger.getLogger("RoleFinancialLog");
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	
	public RoleFinancialLog(){
		
	}
	
	public RoleFinancialLog(Player player){
		this.userid = Long.valueOf(player.getUserId());
		this.roleid = player.getId();
		this.totalgetbindgold = player.getTotalgetbindgold();
		this.totalgetmoney = player.getTotalgetmoney();
		this.totalconsumebindgold = player.getTotalconsumebindgold();
		this.totalconsumemoney = player.getTotalconsumemoney();
	}
	
	private long userid;
	private long roleid;
	
	private long totalgetbindgold;
	private long totalgetmoney;
	private long totalconsumebindgold;
	private long totalconsumemoney;

	public static Logger getLogger() {
		return logger;
	}

	@Log(logField="userid",fieldType="bigint")
	public long getUserid() {
		return userid;
	}
	
	@Log(logField="roleid",fieldType="bigint")
	public long getRoleid() {
		return roleid;
	}

	@Log(logField="tgetbindgold",fieldType="bigint")
	public long getTotalgetbindgold() {
		return totalgetbindgold;
	}

	@Log(logField="tgetmoney",fieldType="bigint")
	public long getTotalgetmoney() {
		return totalgetmoney;
	}

	@Log(logField="tconsumebindgold",fieldType="bigint")
	public long getTotalconsumebindgold() {
		return totalconsumebindgold;
	}

	@Log(logField="tconsumemoney",fieldType="bigint")
	public long getTotalconsumemoney() {
		return totalconsumemoney;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public void setTotalgetbindgold(long totalgetbindgold) {
		this.totalgetbindgold = totalgetbindgold;
	}

	public void setTotalgetmoney(long totalgetmoney) {
		this.totalgetmoney = totalgetmoney;
	}

	public void setTotalconsumebindgold(long totalconsumebindgold) {
		this.totalconsumebindgold = totalconsumebindgold;
	}

	public void setTotalconsumemoney(long totalconsumemoney) {
		this.totalconsumemoney = totalconsumemoney;
	}

}
