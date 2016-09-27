package com.game.longyuan.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 激活星位消息
 */
public class ReqLongYuanActivationMessage extends Message{

	//想要激活的龙元心法阶段（星图）
	private byte longyuanactlv;
	
	//想要激活的龙元心法星位
	private byte longyuanactnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//想要激活的龙元心法阶段（星图）
		writeByte(buf, this.longyuanactlv);
		//想要激活的龙元心法星位
		writeByte(buf, this.longyuanactnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//想要激活的龙元心法阶段（星图）
		this.longyuanactlv = readByte(buf);
		//想要激活的龙元心法星位
		this.longyuanactnum = readByte(buf);
		return true;
	}
	
	/**
	 * get 想要激活的龙元心法阶段（星图）
	 * @return 
	 */
	public byte getLongyuanactlv(){
		return longyuanactlv;
	}
	
	/**
	 * set 想要激活的龙元心法阶段（星图）
	 */
	public void setLongyuanactlv(byte longyuanactlv){
		this.longyuanactlv = longyuanactlv;
	}
	
	/**
	 * get 想要激活的龙元心法星位
	 * @return 
	 */
	public byte getLongyuanactnum(){
		return longyuanactnum;
	}
	
	/**
	 * set 想要激活的龙元心法星位
	 */
	public void setLongyuanactnum(byte longyuanactnum){
		this.longyuanactnum = longyuanactnum;
	}
	
	
	@Override
	public int getId() {
		return 115202;
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
		//想要激活的龙元心法阶段（星图）
		buf.append("longyuanactlv:" + longyuanactlv +",");
		//想要激活的龙元心法星位
		buf.append("longyuanactnum:" + longyuanactnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}