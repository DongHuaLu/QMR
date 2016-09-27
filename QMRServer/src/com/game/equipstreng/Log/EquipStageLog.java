package com.game.equipstreng.Log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class EquipStageLog  extends BaseLogBean {
	//装备升阶日志
	private long playerid;		//玩家ID
	private long equiponlyid;	//道具唯一ID
	private String consumeitem;	//消耗道具
	private String equip;		//升阶前原来的装备属性
	private int money ;			//消耗金币
	private int result ;		//升阶结果，0失败，1成功 
	private int targetModel ;	//升阶后道具ID
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("EquipStageLog");


	
//	发生时间，区服ID，玩家ID，装备ID，装备唯一身份识别号，消耗铜币数，消耗材料ID，消耗材料数量，
//	消耗元宝数，进行强化操作的当前星数，强化是否成功，强化失败倒退星数，强化失败返回物品序列ID以及数量，
	//@Log(logField="layer",fieldType="integer")
	//@Log(logField="goodsName",fieldType="varchar(40)")
	//@Log(logField="goodsOnlyid",fieldType="bigint")
	
	
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}


	@Log(logField="playerid",fieldType="bigint")
	public long getPlayerid() {
		return playerid;
	}



	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}


	@Log(logField="equiponlyid",fieldType="bigint")
	public long getEquiponlyid() {
		return equiponlyid;
	}



	public void setEquiponlyid(long equiponlyid) {
		this.equiponlyid = equiponlyid;
	}


	@Log(logField="consumeitem",fieldType="varchar(50)")
	public String getConsumeitem() {
		return consumeitem;
	}



	public void setConsumeitem(String consumeitem) {
		this.consumeitem = consumeitem;
	}


	@Log(logField="money",fieldType="integer")
	public int getMoney() {
		return money;
	}



	public void setMoney(int money) {
		this.money = money;
	}


	@Log(logField="result",fieldType="integer")
	public int getResult() {
		return result;
	}



	public void setResult(int result) {
		this.result = result;
	}



	@Log(logField="equip",fieldType="longtext")
	public String getEquip() {
		return equip;
	}


	public void setEquip(String equip) {
		this.equip = equip;
	}

	@Log(logField="targetModel",fieldType="integer")
	public int getTargetModel() {
		return targetModel;
	}


	public void setTargetModel(int targetModel) {
		this.targetModel = targetModel;
	}
	

	
	
	
	
	
}
