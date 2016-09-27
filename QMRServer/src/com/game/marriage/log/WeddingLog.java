package com.game.marriage.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**婚宴状态日志
 * 
 * @author zhangrong
 *
 */
public class WeddingLog extends BaseLogBean {
	private int type	;	//类型，0申请，1进行中，2结束
	private long marriageid; //婚姻ID


	
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("LongYuanLog");
	
	
	
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


	@Log(logField="marriageid",fieldType="bigint")
	public long getMarriageid() {
		return marriageid;
	}

	public void setMarriageid(long marriageid) {
		this.marriageid = marriageid;
	}



	
	
	
	
	
	
}
