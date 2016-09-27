package com.game.structs;

import org.apache.mina.core.buffer.IoBuffer;

import com.game.message.Bean;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-8-31
 * 
 * 坐标
 */
public class Position extends Bean {

	private short x;
	
	private short y;
	
	public Position(){}
	
	public Position(short x, short y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//X
		writeShort(buf, this.x);
		//Y
		writeShort(buf, this.y);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//X
		this.x = readShort(buf);
		//Y
		this.y = readShort(buf);
		return true;
	}
	
	public short getX() {
		return x;
	}

	public void setX(short x) {
		this.x = x;
	}

	public short getY() {
		return y;
	}

	public void setY(short y) {
		this.y = y;
	}
	
	public boolean equal(Position pos){
		if(this.x == pos.getX() && this.y == pos.getY()) return true;
		else return false;
	}
	
	public String toString(){
		return "[x:" + x + ",y:" + y + "]";
	}
}
