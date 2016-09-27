package com.game.dazuo.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 拒绝其他玩家的双修申请消息
 */
public class ReqRefuseShuangXiuMessage extends Message{

	//他人ID
	private long otherRoleId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//他人ID
		writeLong(buf, this.otherRoleId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//他人ID
		this.otherRoleId = readLong(buf);
		return true;
	}
	
	/**
	 * get 他人ID
	 * @return 
	 */
	public long getOtherRoleId(){
		return otherRoleId;
	}
	
	/**
	 * set 他人ID
	 */
	public void setOtherRoleId(long otherRoleId){
		this.otherRoleId = otherRoleId;
	}
	
	
	@Override
	public int getId() {
		return 136204;
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
		//他人ID
		buf.append("otherRoleId:" + otherRoleId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}