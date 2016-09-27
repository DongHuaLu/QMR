package com.game.player.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class OnlineCountLog extends BaseLogBean {
	private int onlinecount;//总在线
	private int teamcount;//组队玩家
	private int male;//男玩家
	private int female;//女玩家
	private String countrycount;//国家在线
	private String datetime;
	private Logger logger=Logger.getLogger("OnlineCountLog");
	@Override
	public void logToFile() {
		logger.info(buildSql());
	}
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.YEAR;
	}
	@Log(fieldType="int",logField="onlinecount")
	public int getOnlinecount() {
		return onlinecount;
	}
	@Log(fieldType="int",logField="teamcount")
	public int getTeamcount() {
		return teamcount;
	}
	@Log(fieldType="int",logField="mealcount")
	public int getMale() {
		return male;
	}
	@Log(fieldType="int",logField="femealcount")
	public int getFemale() {
		return female;
	}
	@Log(fieldType="varchar(256)",logField="countrycount")
	public String getCountrycount() {
		return countrycount;
	}
	@Log(fieldType="varchar(256)",logField="datetimes")
	public String getDatetime() {
		return datetime;
	}
	public void setOnlinecount(int onlinecount) {
		this.onlinecount = onlinecount;
	}
	public void setTeamcount(int teamcount) {
		this.teamcount = teamcount;
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

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	
}
