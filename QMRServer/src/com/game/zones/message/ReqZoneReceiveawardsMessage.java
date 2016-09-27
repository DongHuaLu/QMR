package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 前端手动请求领取奖励消息
 */
public class ReqZoneReceiveawardsMessage extends Message{

	//类型:0手动，1自动扫荡
	private byte type;
	
	//副本ID，0没选中
	private int zid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型:0手动，1自动扫荡
		writeByte(buf, this.type);
		//副本ID，0没选中
		writeInt(buf, this.zid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型:0手动，1自动扫荡
		this.type = readByte(buf);
		//副本ID，0没选中
		this.zid = readInt(buf);
		return true;
	}
	
	/**
	 * get 类型:0手动，1自动扫荡
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型:0手动，1自动扫荡
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 副本ID，0没选中
	 * @return 
	 */
	public int getZid(){
		return zid;
	}
	
	/**
	 * set 副本ID，0没选中
	 */
	public void setZid(int zid){
		this.zid = zid;
	}
	
	
	@Override
	public int getId() {
		return 128207;
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
		//类型:0手动，1自动扫荡
		buf.append("type:" + type +",");
		//副本ID，0没选中
		buf.append("zid:" + zid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}