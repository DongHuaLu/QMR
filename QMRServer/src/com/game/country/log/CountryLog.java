package com.game.country.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class CountryLog extends BaseLogBean {
	private int  type;		//0开始前的王城帮会，1，争夺中的王城帮会，2，结束后的王城帮派
	private String countrydata;		//王城帮派
	

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("CountryLog");
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
	}
	//@Log(logField="layer",fieldType="integer")
	//@Log(logField="goodsName",fieldType="varchar(40)")
	//@Log(logField="goodsOnlyid",fieldType="bigint")
	
	
	@Log(logField="countrydata",fieldType="text")
	public String getCountrydata() {
		return countrydata;
	}
	public void setCountrydata(String countrydata) {
		this.countrydata = countrydata;
	}

	@Log(logField="type",fieldType="integer")
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
	
	

	
	
	
	
	
	
}
