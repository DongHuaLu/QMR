package com.game.transactions.structs;



public class Transactions {
	private long starttime = 0;
	private long aid = 0;
	private long bid = 0;
	TsInfo aInfo = new TsInfo();
	TsInfo bInfo = new TsInfo();

	
	/**
	 * 根据玩家ID得到自身交易信息
	 * @param pid
	 * @return
	 */
	public TsInfo getTsInfo(long  pid) {
		return (aid == pid )?aInfo:( (pid == bid )?bInfo:null);
	}
	/**
	 * 根据玩家ID得到对方交易信息
	 * @param pid
	 * @return
	 */
	public TsInfo getOtherTsInfo(long  pid) {
		return (aid == pid )?bInfo:( (pid == bid )?aInfo:null);
	}
	
	
	public long getStarttime() {
		return starttime;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public TsInfo getaInfo() {
		return aInfo;
	}

	public void setaInfo(TsInfo aInfo) {
		this.aInfo = aInfo;
	}

	public TsInfo getbInfo() {
		return bInfo;
	}

	public void setbInfo(TsInfo bInfo) {
		this.bInfo = bInfo;
	}

}