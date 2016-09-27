package com.game.guildflag.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

/**领地战数据记录
 * 
 * @author zhangrong
 *
 */
public class GuildFlagLog extends BaseLogBean {
	private int  type;		//0开始前争夺战 ，1旗帜被砍，2插旗
	private String data;		//领地战数据
	

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("GuildFlagLog");
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
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

	@Log(logField="data",fieldType="text")
	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
	
	
	

	
	
	
	
	
	
}
