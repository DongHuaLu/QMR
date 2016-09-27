package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 商品调整，返回多余道具（暂时无用）消息
 */
public class ResStallsAdjustPricesToGameMessage extends Message{

	//摆摊的玩家ID
	private long stallsplayerid;
	
	//序列化的道具
	private String item;
	
	//返还数量
	private int num;
	
	//商品调整状态，0成功，1失败
	private byte status;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摆摊的玩家ID
		writeLong(buf, this.stallsplayerid);
		//序列化的道具
		writeString(buf, this.item);
		//返还数量
		writeInt(buf, this.num);
		//商品调整状态，0成功，1失败
		writeByte(buf, this.status);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//摆摊的玩家ID
		this.stallsplayerid = readLong(buf);
		//序列化的道具
		this.item = readString(buf);
		//返还数量
		this.num = readInt(buf);
		//商品调整状态，0成功，1失败
		this.status = readByte(buf);
		return true;
	}
	
	/**
	 * get 摆摊的玩家ID
	 * @return 
	 */
	public long getStallsplayerid(){
		return stallsplayerid;
	}
	
	/**
	 * set 摆摊的玩家ID
	 */
	public void setStallsplayerid(long stallsplayerid){
		this.stallsplayerid = stallsplayerid;
	}
	
	/**
	 * get 序列化的道具
	 * @return 
	 */
	public String getItem(){
		return item;
	}
	
	/**
	 * set 序列化的道具
	 */
	public void setItem(String item){
		this.item = item;
	}
	
	/**
	 * get 返还数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 返还数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 商品调整状态，0成功，1失败
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 商品调整状态，0成功，1失败
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	
	@Override
	public int getId() {
		return 123313;
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
		//摆摊的玩家ID
		buf.append("stallsplayerid:" + stallsplayerid +",");
		//序列化的道具
		if(this.item!=null) buf.append("item:" + item.toString() +",");
		//返还数量
		buf.append("num:" + num +",");
		//商品调整状态，0成功，1失败
		buf.append("status:" + status +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}