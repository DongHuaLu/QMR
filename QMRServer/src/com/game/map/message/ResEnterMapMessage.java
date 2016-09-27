package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 切换地图返回消息
 */
public class ResEnterMapMessage extends Message{

	//所在线
	private int line;
	
	//坐标
	private com.game.structs.Position pos;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//所在线
		writeInt(buf, this.line);
		//坐标
		writeBean(buf, this.pos);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//所在线
		this.line = readInt(buf);
		//坐标
		this.pos = (com.game.structs.Position)readBean(buf, com.game.structs.Position.class);
		return true;
	}
	
	/**
	 * get 所在线
	 * @return 
	 */
	public int getLine(){
		return line;
	}
	
	/**
	 * set 所在线
	 */
	public void setLine(int line){
		this.line = line;
	}
	
	/**
	 * get 坐标
	 * @return 
	 */
	public com.game.structs.Position getPos(){
		return pos;
	}
	
	/**
	 * set 坐标
	 */
	public void setPos(com.game.structs.Position pos){
		this.pos = pos;
	}
	
	
	@Override
	public int getId() {
		return 101120;
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
		//所在线
		buf.append("line:" + line +",");
		//坐标
		if(this.pos!=null) buf.append("pos:" + pos.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}