package com.game.server.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class ServerFinancialLog extends BaseLogBean {

	private static final Logger log=Logger.getLogger("ServerFinancialLog");
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.YEAR;
	}

	@Override
	public void logToFile() {
		log.error(buildSql());
	}
	
	private long sconsumebindgold;
	private long sconsumemoney;
	private long sgeneratebindgold;
	private long sgeneratemoney;
	private String datetimes;
	
	public ServerFinancialLog() {
		super();
		
	}

	@Log(fieldType="varchar(255)",logField="datetimes")
	public String getDatetimes() {
		return datetimes;
	}
	@Log(fieldType="bigint",logField="sconsumebindgold")
	public long getSconsumebindgold() {
		return sconsumebindgold;
	}
	@Log(fieldType="bigint",logField="sconsumemoney")
	public long getSconsumemoney() {
		return sconsumemoney;
	}
	@Log(fieldType="bigint",logField="sgeneratebindgold")
	public long getSgeneratebindgold() {
		return sgeneratebindgold;
	}
	@Log(fieldType="bigint",logField="sgeneratemoney")
	public long getSgeneratemoney() {
		return sgeneratemoney;
	}

	public void setSconsumebindgold(long sconsumebindgold) {
		this.sconsumebindgold = sconsumebindgold;
	}

	public void setSconsumemoney(long sconsumemoney) {
		this.sconsumemoney = sconsumemoney;
	}

	public void setSgeneratebindgold(long sgeneratebindgold) {
		this.sgeneratebindgold = sgeneratebindgold;
	}

	public void setSgeneratemoney(long sgeneratemoney) {
		this.sgeneratemoney = sgeneratemoney;
	}

	public void setDatetimes(String datetimes) {
		this.datetimes = datetimes;
	}
	
}
