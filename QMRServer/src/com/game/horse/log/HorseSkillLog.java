package com.game.horse.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class HorseSkillLog extends BaseLogBean {
	private long playerId;		//玩家ID
	private String skills; 		//被选中的技能列表和获得经验JOSN
	private int yuanbao;			//花费的元宝
	private int money;				//花费的金币
	
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("HorseSkillLog");
	
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	//@Log(logField="layer",fieldType="integer")
	//@Log(logField="goodsName",fieldType="varchar(40)")
	//@Log(logField="goodsOnlyid",fieldType="bigint")

	
	@Log(logField="playerId",fieldType="bigint")
	public long getPlayerId() {
		return playerId;
	}



	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}


	@Log(logField="skills",fieldType="varchar(1000)")
	public String getSkills() {
		return skills;
	}



	public void setSkills(String skills) {
		this.skills = skills;
	}


	@Log(logField="yuanbao",fieldType="integer")
	public int getYuanbao() {
		return yuanbao;
	}



	public void setYuanbao(int yuanbao) {
		this.yuanbao = yuanbao;
	}


	@Log(logField="money",fieldType="integer")
	public int getMoney() {
		return money;
	}



	public void setMoney(int money) {
		this.money = money;
	}
	
	
	
	
	
	
	
	
}