package com.game.longyuan.message;

import com.game.longyuan.bean.LongYuanPosTipsInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 鼠标悬停提示信息(星位)消息
 */
public class ResLongYuanPosTipsMessage extends Message{

	//龙元心法星位tips
	private LongYuanPosTipsInfo longyuanpostipsinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//龙元心法星位tips
		writeBean(buf, this.longyuanpostipsinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//龙元心法星位tips
		this.longyuanpostipsinfo = (LongYuanPosTipsInfo)readBean(buf, LongYuanPosTipsInfo.class);
		return true;
	}
	
	/**
	 * get 龙元心法星位tips
	 * @return 
	 */
	public LongYuanPosTipsInfo getLongyuanpostipsinfo(){
		return longyuanpostipsinfo;
	}
	
	/**
	 * set 龙元心法星位tips
	 */
	public void setLongyuanpostipsinfo(LongYuanPosTipsInfo longyuanpostipsinfo){
		this.longyuanpostipsinfo = longyuanpostipsinfo;
	}
	
	
	@Override
	public int getId() {
		return 115103;
	}

	@Override
	public String getQueue() {
		return null;
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//龙元心法星位tips
		if(this.longyuanpostipsinfo!=null) buf.append("longyuanpostipsinfo:" + longyuanpostipsinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}