package com.game.db.bean;

public class Gold {

    private String userId;

    private int serverId;

    private int totalGold;

    private int gold;

    private int costGold;
    
//    private int isinner;
    //购买摊位元宝增加
    private int twgmadd;
    //元宝下架增加
    private int ybxiajiaadd;    
    //摊主得到货款元宝
	private int huokuanAdd;
	//交易元宝增加
	private int jiaoyiybadd;
	//购买道具失败，返款增加	
	private int faildrollBackadd;
	//取出临时元宝增加	
	private int gettempybadd;
	
	//元宝交易 减少	
	private int jiaoyiresume;
	//元宝上架 减少
	private int shangjiaresume;
	//元宝购买道具 减少
	private int buyitemresume;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public int getTotalGold() {
		return totalGold;
	}

	public void setTotalGold(int totalGold) {
		this.totalGold = totalGold;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getCostGold() {
		return costGold;
	}

	public void setCostGold(int costGold) {
		this.costGold = costGold;
	}

	public int getTwgmadd() {
		return twgmadd;
	}

	public void setTwgmadd(int twgmadd) {
		this.twgmadd = twgmadd;
	}

	public int getYbxiajiaadd() {
		return ybxiajiaadd;
	}

	public void setYbxiajiaadd(int ybxiajiaadd) {
		this.ybxiajiaadd = ybxiajiaadd;
	}

	public int getHuokuanAdd() {
		return huokuanAdd;
	}

	public void setHuokuanAdd(int huokuanAdd) {
		this.huokuanAdd = huokuanAdd;
	}

	public int getJiaoyiybadd() {
		return jiaoyiybadd;
	}

	public void setJiaoyiybadd(int jiaoyiybadd) {
		this.jiaoyiybadd = jiaoyiybadd;
	}

	public int getFaildrollBackadd() {
		return faildrollBackadd;
	}

	public void setFaildrollBackadd(int faildrollBackadd) {
		this.faildrollBackadd = faildrollBackadd;
	}

	public int getGettempybadd() {
		return gettempybadd;
	}

	public void setGettempybadd(int gettempybadd) {
		this.gettempybadd = gettempybadd;
	}

	public int getJiaoyiresume() {
		return jiaoyiresume;
	}

	public void setJiaoyiresume(int jiaoyiresume) {
		this.jiaoyiresume = jiaoyiresume;
	}

	public int getShangjiaresume() {
		return shangjiaresume;
	}

	public void setShangjiaresume(int shangjiaresume) {
		this.shangjiaresume = shangjiaresume;
	}

	public int getBuyitemresume() {
		return buyitemresume;
	}

	public void setBuyitemresume(int buyitemresume) {
		this.buyitemresume = buyitemresume;
	}
	
//	public int getIsinner() {
//		return isinner;
//	}
//
//	public void setIsinner(int isinner) {
//		this.isinner = isinner;
//	}

	@Override
	public String toString(){
		return "[" + userId + "][" + serverId + "][" + totalGold + "][" + gold + "][" + costGold + "][" + twgmadd + "][" + ybxiajiaadd + "][" + huokuanAdd + "][" + jiaoyiybadd + "][" + faildrollBackadd + "][" + gettempybadd + "][" + jiaoyiresume + "][" + shangjiaresume + "][" + buyitemresume + "]";
	}
}
