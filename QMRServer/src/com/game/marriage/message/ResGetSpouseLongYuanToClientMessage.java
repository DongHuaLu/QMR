package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回配偶龙元心法消息
 */
public class ResGetSpouseLongYuanToClientMessage extends Message{

	//龙元心法阶段（星图）
	private byte longyuanlv;
	
	//龙元心法星位
	private byte longyuannum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//龙元心法阶段（星图）
		writeByte(buf, this.longyuanlv);
		//龙元心法星位
		writeByte(buf, this.longyuannum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//龙元心法阶段（星图）
		this.longyuanlv = readByte(buf);
		//龙元心法星位
		this.longyuannum = readByte(buf);
		return true;
	}
	
	/**
	 * get 龙元心法阶段（星图）
	 * @return 
	 */
	public byte getLongyuanlv(){
		return longyuanlv;
	}
	
	/**
	 * set 龙元心法阶段（星图）
	 */
	public void setLongyuanlv(byte longyuanlv){
		this.longyuanlv = longyuanlv;
	}
	
	/**
	 * get 龙元心法星位
	 * @return 
	 */
	public byte getLongyuannum(){
		return longyuannum;
	}
	
	/**
	 * set 龙元心法星位
	 */
	public void setLongyuannum(byte longyuannum){
		this.longyuannum = longyuannum;
	}
	
	
	@Override
	public int getId() {
		return 163116;
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
		//龙元心法阶段（星图）
		buf.append("longyuanlv:" + longyuanlv +",");
		//龙元心法星位
		buf.append("longyuannum:" + longyuannum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}