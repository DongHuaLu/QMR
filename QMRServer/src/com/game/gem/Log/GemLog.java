package com.game.gem.Log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class GemLog extends BaseLogBean{
	private long playerId ; //玩家ID
	private int type ;//类型：0激活，1升级
	private int zhenqi ;//使用真气
	private String item ;//消耗道具
	private int Result;//结果：1成功，0失败
	private int equiptype;//装备类型； 装备位置
	private int pos;//位置			五棵宝石
	private int lv;//当前等级
	private int exp;//之前经验
	private int addexp;//获得经验
	private int iscrit;//是否暴击：0没有暴击，1小暴击，2大暴击  
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
	private static final Logger logger=Logger.getLogger("GemLog");
	

	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	//@Log(logField="layer",fieldType="integer")
	//@Log(logField="goodsName",fieldType="varchar(40)")
	//@Log(logField="goodsOnlyid",fieldType="bigint")









	@Log(logField="type",fieldType="integer")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}



	@Log(logField="zhenqi",fieldType="integer")
	public int getZhenqi() {
		return zhenqi;
	}

	public void setZhenqi(int zhenqi) {
		this.zhenqi = zhenqi;
	}


	
	@Log(logField="item",fieldType="varchar(100)")
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}



	@Log(logField="Result",fieldType="integer")
	public int getResult() {
		return Result;
	}


	public void setResult(int result) {
		Result = result;
	}



	@Log(logField="pos",fieldType="integer")
	public int getPos() {
		return pos;
	}

	
	public void setPos(int pos) {
		this.pos = pos;
	}
	@Log(logField="lv",fieldType="integer")
	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}



	@Log(logField="exp",fieldType="integer")
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}


	@Log(logField="addexp",fieldType="integer")
	public int getAddexp() {
		return addexp;
	}

	public void setAddexp(int addexp) {
		this.addexp = addexp;
	}


	@Log(logField="iscrit",fieldType="integer")
	public int getIscrit() {
		return iscrit;
	}

	public void setIscrit(int iscrit) {
		this.iscrit = iscrit;
	}

	@Log(logField="equiptype",fieldType="integer")
	public int getEquiptype() {
		return equiptype;
	}

	public void setEquiptype(int equiptype) {
		this.equiptype = equiptype;
	}



	@Log(logField="playerId",fieldType="bigint")
	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	
	
	
	
	

	
	
	
	
	
	
	
}
