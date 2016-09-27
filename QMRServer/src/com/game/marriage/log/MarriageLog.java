package com.game.marriage.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**婚姻状态日志
 * 
 * @author zhangrong
 *
 */
public class MarriageLog extends BaseLogBean {
	private long bridegroomid;	//新郎ID
	private long brideid;		//新娘ID
	private int ringid	;	//戒指ID(新戒指)
	private int type	;	//类型，0结婚，1离婚，2换戒指
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



	@Log(logField="bridegroomid",fieldType="bigint")
	public long getBridegroomid() {
		return bridegroomid;
	}




	public void setBridegroomid(long bridegroomid) {
		this.bridegroomid = bridegroomid;
	}



	@Log(logField="brideid",fieldType="bigint")
	public long getBrideid() {
		return brideid;
	}




	public void setBrideid(long brideid) {
		this.brideid = brideid;
	}



	@Log(logField="ringid",fieldType="integer")
	public int getRingid() {
		return ringid;
	}




	public void setRingid(int ringid) {
		this.ringid = ringid;
	}




	@Log(logField="marriageid",fieldType="bigint")
	public long getMarriageid() {
		return marriageid;
	}





	public void setMarriageid(long marriageid) {
		this.marriageid = marriageid;
	}



	
	
	
	
	
	
}
