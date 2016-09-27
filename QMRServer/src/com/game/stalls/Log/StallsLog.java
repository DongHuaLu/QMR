package com.game.stalls.Log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;

public class StallsLog  extends BaseLogBean {
	private long stallsId;		//摊主ID
	
	private String goodsName;	//交易道具名字
	private long goodsOnlyid;	//道具唯一ID
	private int goodsModid;		//道具模组ID
	private int goodsNum;		//数量
	private String goodsInfo;	//道具详细信息
	private int Pricegold ;		//定价金币
	private int Priceyuanbao ;	//定价元宝

	private long buyId;			//购买者ID	//-1商品上架，-2商品调整，-3商品下架

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("StallsLog");
	
	
	//分表时间
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}
	
	

	
	

	@Log(logField="stallsId",fieldType="bigint")
	public long getStallsId() {
		return stallsId;
	}

	public void setStallsId(long stallsId) {
		this.stallsId = stallsId;
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
	
	
	@Log(logField="buyId",fieldType="bigint")
	public long getBuyId() {
		return buyId;
	}

	public void setBuyId(long buyId) {
		this.buyId = buyId;
	}

	
	
	@Log(logField="Pricegold",fieldType="integer")
	public int getPricegold() {
		return Pricegold;
	}


	public void setPricegold(int pricegold) {
		Pricegold = pricegold;
	}

	@Log(logField="Priceyuanbao",fieldType="integer")
	public int getPriceyuanbao() {
		return Priceyuanbao;
	}


	public void setPriceyuanbao(int priceyuanbao) {
		Priceyuanbao = priceyuanbao;
	}
	
	

	
	
	
	
	
}
