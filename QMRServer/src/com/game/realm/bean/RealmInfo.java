package com.game.realm.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 境界信息
 */
public class RealmInfo extends Bean {

	//境界等级
	private int realmlevel;
	
	//境界强化等级
	private int intensifylevel;
	
	//境界突破祝福值
	private int blessingnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//境界等级
		writeInt(buf, this.realmlevel);
		//境界强化等级
		writeInt(buf, this.intensifylevel);
		//境界突破祝福值
		writeInt(buf, this.blessingnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//境界等级
		this.realmlevel = readInt(buf);
		//境界强化等级
		this.intensifylevel = readInt(buf);
		//境界突破祝福值
		this.blessingnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 境界等级
	 * @return 
	 */
	public int getRealmlevel(){
		return realmlevel;
	}
	
	/**
	 * set 境界等级
	 */
	public void setRealmlevel(int realmlevel){
		this.realmlevel = realmlevel;
	}
	
	/**
	 * get 境界强化等级
	 * @return 
	 */
	public int getIntensifylevel(){
		return intensifylevel;
	}
	
	/**
	 * set 境界强化等级
	 */
	public void setIntensifylevel(int intensifylevel){
		this.intensifylevel = intensifylevel;
	}
	
	/**
	 * get 境界突破祝福值
	 * @return 
	 */
	public int getBlessingnum(){
		return blessingnum;
	}
	
	/**
	 * set 境界突破祝福值
	 */
	public void setBlessingnum(int blessingnum){
		this.blessingnum = blessingnum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//境界等级
		buf.append("realmlevel:" + realmlevel +",");
		//境界强化等级
		buf.append("intensifylevel:" + intensifylevel +",");
		//境界突破祝福值
		buf.append("blessingnum:" + blessingnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}