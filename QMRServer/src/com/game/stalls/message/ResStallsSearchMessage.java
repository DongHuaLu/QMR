package com.game.stalls.message;

import com.game.stalls.bean.StallsBriefList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摊位搜索结果消息
 */
public class ResStallsSearchMessage extends Message{

	//符合条件的摊位简要信息
	private StallsBriefList stallsbrieflist;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//符合条件的摊位简要信息
		writeBean(buf, this.stallsbrieflist);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//符合条件的摊位简要信息
		this.stallsbrieflist = (StallsBriefList)readBean(buf, StallsBriefList.class);
		return true;
	}
	
	/**
	 * get 符合条件的摊位简要信息
	 * @return 
	 */
	public StallsBriefList getStallsbrieflist(){
		return stallsbrieflist;
	}
	
	/**
	 * set 符合条件的摊位简要信息
	 */
	public void setStallsbrieflist(StallsBriefList stallsbrieflist){
		this.stallsbrieflist = stallsbrieflist;
	}
	
	
	@Override
	public int getId() {
		return 123108;
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
		//符合条件的摊位简要信息
		if(this.stallsbrieflist!=null) buf.append("stallsbrieflist:" + stallsbrieflist.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}