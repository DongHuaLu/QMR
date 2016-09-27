package com.game.batter.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 连击消息消息
 */
public class ResMonsterBatterToClientMessage extends Message{

	//类型：1普通怪连斩，2BOSS连击
	private byte type;
	
	//连击倒计时（秒）
	private int countdowntime;
	
	//增加攻击力
	private int atk;
	
	//连击次数
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型：1普通怪连斩，2BOSS连击
		writeByte(buf, this.type);
		//连击倒计时（秒）
		writeInt(buf, this.countdowntime);
		//增加攻击力
		writeInt(buf, this.atk);
		//连击次数
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型：1普通怪连斩，2BOSS连击
		this.type = readByte(buf);
		//连击倒计时（秒）
		this.countdowntime = readInt(buf);
		//增加攻击力
		this.atk = readInt(buf);
		//连击次数
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 类型：1普通怪连斩，2BOSS连击
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：1普通怪连斩，2BOSS连击
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 连击倒计时（秒）
	 * @return 
	 */
	public int getCountdowntime(){
		return countdowntime;
	}
	
	/**
	 * set 连击倒计时（秒）
	 */
	public void setCountdowntime(int countdowntime){
		this.countdowntime = countdowntime;
	}
	
	/**
	 * get 增加攻击力
	 * @return 
	 */
	public int getAtk(){
		return atk;
	}
	
	/**
	 * set 增加攻击力
	 */
	public void setAtk(int atk){
		this.atk = atk;
	}
	
	/**
	 * get 连击次数
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 连击次数
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	
	@Override
	public int getId() {
		return 141101;
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
		//类型：1普通怪连斩，2BOSS连击
		buf.append("type:" + type +",");
		//连击倒计时（秒）
		buf.append("countdowntime:" + countdowntime +",");
		//增加攻击力
		buf.append("atk:" + atk +",");
		//连击次数
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}