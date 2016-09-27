package com.game.longyuan.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求鼠标悬停星图提示信息（星图）消息
 */
public class ReqLongYuanStarMapTipsMessage extends Message{

	//当前的龙元心法阶段（星图）
	private byte longyuanactlv;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前的龙元心法阶段（星图）
		writeByte(buf, this.longyuanactlv);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前的龙元心法阶段（星图）
		this.longyuanactlv = readByte(buf);
		return true;
	}
	
	/**
	 * get 当前的龙元心法阶段（星图）
	 * @return 
	 */
	public byte getLongyuanactlv(){
		return longyuanactlv;
	}
	
	/**
	 * set 当前的龙元心法阶段（星图）
	 */
	public void setLongyuanactlv(byte longyuanactlv){
		this.longyuanactlv = longyuanactlv;
	}
	
	
	@Override
	public int getId() {
		return 115204;
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
		//当前的龙元心法阶段（星图）
		buf.append("longyuanactlv:" + longyuanactlv +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}