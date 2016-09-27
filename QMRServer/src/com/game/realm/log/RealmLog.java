package com.game.realm.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class RealmLog  extends BaseLogBean {
	private long playerid;		//玩家ID
	private int type;		//1强化，2突破
	private int oldRealmlevel; //原境界等级
	private int oldBlessingnum; //原祝福值
	private int oldIntensifylevel;  //原境界强化等级
	private int oldbreaknum; 	//原突破失败次数
	private int result ; //突破结果，0失败，1成功
	private int newRealmlevel; //新境界等级
	private int newBlessingnum; //新祝福值
	private int newIntensifylevel;  //新境界强化等级
	private int newbreaknum; 	//新突破失败次数
	private int breakexp; 	//突破失败获得经验
	



	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("RealmLog");
	
	
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


	@Log(logField="type",fieldType="int")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Log(logField="oldRealmlevel",fieldType="int")
	public int getOldRealmlevel() {
		return oldRealmlevel;
	}

	public void setOldRealmlevel(int oldRealmlevel) {
		this.oldRealmlevel = oldRealmlevel;
	}

	@Log(logField="oldBlessingnum",fieldType="int")
	public int getOldBlessingnum() {
		return oldBlessingnum;
	}

	public void setOldBlessingnum(int oldBlessingnum) {
		this.oldBlessingnum = oldBlessingnum;
	}

	@Log(logField="oldIntensifylevel",fieldType="int")
	public int getOldIntensifylevel() {
		return oldIntensifylevel;
	}

	public void setOldIntensifylevel(int oldIntensifylevel) {
		this.oldIntensifylevel = oldIntensifylevel;
	}

	@Log(logField="oldbreaknum",fieldType="int")
	public int getOldbreaknum() {
		return oldbreaknum;
	}

	public void setOldbreaknum(int oldbreaknum) {
		this.oldbreaknum = oldbreaknum;
	}



	@Log(logField="newRealmlevel",fieldType="int")
	public int getNewRealmlevel() {
		return newRealmlevel;
	}

	public void setNewRealmlevel(int newRealmlevel) {
		this.newRealmlevel = newRealmlevel;
	}

	@Log(logField="newBlessingnum",fieldType="int")
	public int getNewBlessingnum() {
		return newBlessingnum;
	}

	public void setNewBlessingnum(int newBlessingnum) {
		this.newBlessingnum = newBlessingnum;
	}

	@Log(logField="newIntensifylevel",fieldType="int")
	public int getNewIntensifylevel() {
		return newIntensifylevel;
	}

	public void setNewIntensifylevel(int newIntensifylevel) {
		this.newIntensifylevel = newIntensifylevel;
	}

	@Log(logField="newbreaknum",fieldType="int")
	public int getNewbreaknum() {
		return newbreaknum;
	}

	public void setNewbreaknum(int newbreaknum) {
		this.newbreaknum = newbreaknum;
	}



	@Log(logField="result",fieldType="int")
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	
	@Log(logField="breakexp",fieldType="int")
	public int getBreakexp() {
		return breakexp;
	}

	public void setBreakexp(int breakexp) {
		this.breakexp = breakexp;
	}
	
	

	
	
	

	
	
	
	
	
}
