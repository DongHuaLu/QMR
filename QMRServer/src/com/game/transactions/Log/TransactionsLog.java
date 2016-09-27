package com.game.transactions.Log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class TransactionsLog extends BaseLogBean {
	private long tradingId;		//本次交易ID
	
	private String goodsName;	//交易道具名字
	private long goodsOnlyid;	//道具唯一ID
	private int goodsModid;	//道具模组ID
	private int goodsNum;		//数量
	private String goodsInfo;	//道具详细信息
	
	private long outId;			//交易（出）玩家ID
	private long intoId;		//交易（入）玩家ID
	
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("TransactionsLog.class");
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(logField="tradingId",fieldType="bigint")
	public long getTradingId() {
		return tradingId;
	}

	public void setTradingId(long tradingId) {
		this.tradingId = tradingId;
	}
	
	
	
	@Log(logField="goodsName",fieldType="varchar(80)")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	
	@Log(logField="goodsOnlyid",fieldType="bigint")
	public long getGoodsOnlyid() {
		return goodsOnlyid;
	}

	public void setGoodsOnlyid(long goodsOnlyid) {
		this.goodsOnlyid = goodsOnlyid;
	}
	
	
	
	@Log(logField="goodsModid",fieldType="integer")
	public int getGoodsModid() {
		return goodsModid;
	}

	public void setGoodsModid(int goodsModid) {
		this.goodsModid = goodsModid;
	}
	
	

	@Log(logField="goodsNum",fieldType="integer")
	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	
	@Log(logField="goodsInfo",fieldType="text")
	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	
	
	@Log(logField="outId",fieldType="bigint")
	public long getOutId() {
		return outId;
	}

	public void setOutId(long outId) {
		this.outId = outId;
	}
	
	
	
	@Log(logField="intoId",fieldType="bigint")
	public long getIntoId() {
		return intoId;
	}

	public void setIntoId(long intoId) {
		this.intoId = intoId;
	}

}
