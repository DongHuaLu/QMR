package com.game.backpack.structs;
/**
 * 
 * @author 赵聪慧
 * @2012-9-24 下午5:32:19
 */
public class ItemDefine {
	private int modelId;
	private long lostTime;
	private int num;
	private boolean bind;
	public ItemDefine(int modelid,long lostTime,int num,boolean bind){
		this.modelId=modelid;
		this.lostTime=lostTime;
		this.num=num;
		this.bind=bind;
	}
	public int getModelId() {
		return modelId;
	}
	public long getLostTime() {
		return lostTime;
	}
	public int getNum() {
		return num;
	}
	public boolean isBind() {
		return bind;
	}


}
