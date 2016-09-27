package com.game.toplist.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 排行宝箱信息
 */
public class ChestInfo extends Bean {

	//宝箱id(0-最后一个宝箱)
	private int chestid;
	
	//宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
	private int chesttype;
	
	//是否可领取(0-不可领  1-可领)
	private byte canreceive;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宝箱id(0-最后一个宝箱)
		writeInt(buf, this.chestid);
		//宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
		writeInt(buf, this.chesttype);
		//是否可领取(0-不可领  1-可领)
		writeByte(buf, this.canreceive);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宝箱id(0-最后一个宝箱)
		this.chestid = readInt(buf);
		//宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
		this.chesttype = readInt(buf);
		//是否可领取(0-不可领  1-可领)
		this.canreceive = readByte(buf);
		return true;
	}
	
	/**
	 * get 宝箱id(0-最后一个宝箱)
	 * @return 
	 */
	public int getChestid(){
		return chestid;
	}
	
	/**
	 * set 宝箱id(0-最后一个宝箱)
	 */
	public void setChestid(int chestid){
		this.chestid = chestid;
	}
	
	/**
	 * get 宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
	 * @return 
	 */
	public int getChesttype(){
		return chesttype;
	}
	
	/**
	 * set 宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
	 */
	public void setChesttype(int chesttype){
		this.chesttype = chesttype;
	}
	
	/**
	 * get 是否可领取(0-不可领  1-可领)
	 * @return 
	 */
	public byte getCanreceive(){
		return canreceive;
	}
	
	/**
	 * set 是否可领取(0-不可领  1-可领)
	 */
	public void setCanreceive(byte canreceive){
		this.canreceive = canreceive;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//宝箱id(0-最后一个宝箱)
		buf.append("chestid:" + chestid +",");
		//宝箱类型(1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力)
		buf.append("chesttype:" + chesttype +",");
		//是否可领取(0-不可领  1-可领)
		buf.append("canreceive:" + canreceive +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}