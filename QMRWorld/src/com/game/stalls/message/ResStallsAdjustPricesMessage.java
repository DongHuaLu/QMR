package com.game.stalls.message;

import com.game.stalls.bean.StallsInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 商品调整完成，更新列表消息
 */
public class ResStallsAdjustPricesMessage extends Message{

	//查看指定摊位信息
	private StallsInfo stallsinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//查看指定摊位信息
		writeBean(buf, this.stallsinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//查看指定摊位信息
		this.stallsinfo = (StallsInfo)readBean(buf, StallsInfo.class);
		return true;
	}
	
	/**
	 * get 查看指定摊位信息
	 * @return 
	 */
	public StallsInfo getStallsinfo(){
		return stallsinfo;
	}
	
	/**
	 * set 查看指定摊位信息
	 */
	public void setStallsinfo(StallsInfo stallsinfo){
		this.stallsinfo = stallsinfo;
	}
	
	
	@Override
	public int getId() {
		return 123106;
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
		//查看指定摊位信息
		if(this.stallsinfo!=null) buf.append("stallsinfo:" + stallsinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}