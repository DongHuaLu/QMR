package com.game.server.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class ServerOnlineCountLog extends BaseLogBean {
	private int nowcount;
	private int male;
	private int female;
	private String countrycount;
	private int teamrolecount;
	private int petcount;				
	private int rechargeer;//有过充值的玩家	
	private String datetimes;
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("ServerOnlineCountLog");
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.YEAR;
	}
	@Log(fieldType="int",logField="nowcount")
	public int getNowcount() {
		return nowcount;
	}
	@Log(fieldType="int",logField="male")
	public int getMale() {
		return male;
	}
	@Log(fieldType="int",logField="female")
	public int getFemale() {
		return female;
	}
	@Log(fieldType="varchar(256)",logField="countrycount")
	public String getCountrycount() {
		return countrycount;
	}
	@Log(fieldType="int",logField="teamrolecount")
	public int getTeamrolecount() {
		return teamrolecount;
	}
	@Log(fieldType="int",logField="petcount")
	public int getPetcount() {
		return petcount;
	}
	@Log(fieldType="int",logField="recharger")
	public int getRechargeer() {
		return rechargeer;
	}
	@Log(fieldType="varchar(256)",logField="datetimes")
	public String getDatetimes() {
		return datetimes;
	}
	public void setNowcount(int nowcount) {
		this.nowcount = nowcount;
	}
	public void setMale(int male) {
		this.male = male;
	}
	public void setFemale(int female) {
		this.female = female;
	}
	public void setCountrycount(String countrycount) {
		this.countrycount = countrycount;
	}
	public void setTeamrolecount(int teamrolecount) {
		this.teamrolecount = teamrolecount;
	}
	public void setPetcount(int petcount) {
		this.petcount = petcount;
	}
	public void setRechargeer(int rechargeer) {
		this.rechargeer = rechargeer;
	}
	public void setDatetimes(String datetimes) {
		this.datetimes = datetimes;
	}	
}
