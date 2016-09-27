package com.game.epalace.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 走路格子信息
 */
public class EpalaceInfo extends Bean {

	//前进方向，上右下左0246，没有就-1
	private byte direction;
	
	//当前位置
	private byte currentpos;
	
	//当前格子触发事件ID
	private int eventid;
	
	//当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
	private byte forkdirection;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//前进方向，上右下左0246，没有就-1
		writeByte(buf, this.direction);
		//当前位置
		writeByte(buf, this.currentpos);
		//当前格子触发事件ID
		writeInt(buf, this.eventid);
		//当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
		writeByte(buf, this.forkdirection);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//前进方向，上右下左0246，没有就-1
		this.direction = readByte(buf);
		//当前位置
		this.currentpos = readByte(buf);
		//当前格子触发事件ID
		this.eventid = readInt(buf);
		//当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
		this.forkdirection = readByte(buf);
		return true;
	}
	
	/**
	 * get 前进方向，上右下左0246，没有就-1
	 * @return 
	 */
	public byte getDirection(){
		return direction;
	}
	
	/**
	 * set 前进方向，上右下左0246，没有就-1
	 */
	public void setDirection(byte direction){
		this.direction = direction;
	}
	
	/**
	 * get 当前位置
	 * @return 
	 */
	public byte getCurrentpos(){
		return currentpos;
	}
	
	/**
	 * set 当前位置
	 */
	public void setCurrentpos(byte currentpos){
		this.currentpos = currentpos;
	}
	
	/**
	 * get 当前格子触发事件ID
	 * @return 
	 */
	public int getEventid(){
		return eventid;
	}
	
	/**
	 * set 当前格子触发事件ID
	 */
	public void setEventid(int eventid){
		this.eventid = eventid;
	}
	
	/**
	 * get 当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
	 * @return 
	 */
	public byte getForkdirection(){
		return forkdirection;
	}
	
	/**
	 * set 当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
	 */
	public void setForkdirection(byte forkdirection){
		this.forkdirection = forkdirection;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//前进方向，上右下左0246，没有就-1
		buf.append("direction:" + direction +",");
		//当前位置
		buf.append("currentpos:" + currentpos +",");
		//当前格子触发事件ID
		buf.append("eventid:" + eventid +",");
		//当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
		buf.append("forkdirection:" + forkdirection +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}