package com.game.longyuan.message;

import com.game.longyuan.bean.LongYuanStarMapTipsInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 鼠标悬停提示信息(星图)消息
 */
public class ResLongYuanStarMapTipsMessage extends Message{

	//当前的龙元心法星图tips
	private LongYuanStarMapTipsInfo starmaptipsinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前的龙元心法星图tips
		writeBean(buf, this.starmaptipsinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前的龙元心法星图tips
		this.starmaptipsinfo = (LongYuanStarMapTipsInfo)readBean(buf, LongYuanStarMapTipsInfo.class);
		return true;
	}
	
	/**
	 * get 当前的龙元心法星图tips
	 * @return 
	 */
	public LongYuanStarMapTipsInfo getStarmaptipsinfo(){
		return starmaptipsinfo;
	}
	
	/**
	 * set 当前的龙元心法星图tips
	 */
	public void setStarmaptipsinfo(LongYuanStarMapTipsInfo starmaptipsinfo){
		this.starmaptipsinfo = starmaptipsinfo;
	}
	
	
	@Override
	public int getId() {
		return 115104;
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
		//当前的龙元心法星图tips
		if(this.starmaptipsinfo!=null) buf.append("starmaptipsinfo:" + starmaptipsinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}