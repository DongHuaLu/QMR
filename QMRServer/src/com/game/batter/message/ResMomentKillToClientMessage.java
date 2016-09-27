package com.game.batter.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 上线发送连斩次数消息
 */
public class ResMomentKillToClientMessage extends Message{

	//连斩攻击
	private int evencutatk;
	
	//连击攻击
	private int batteratk;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//连斩攻击
		writeInt(buf, this.evencutatk);
		//连击攻击
		writeInt(buf, this.batteratk);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//连斩攻击
		this.evencutatk = readInt(buf);
		//连击攻击
		this.batteratk = readInt(buf);
		return true;
	}
	
	/**
	 * get 连斩攻击
	 * @return 
	 */
	public int getEvencutatk(){
		return evencutatk;
	}
	
	/**
	 * set 连斩攻击
	 */
	public void setEvencutatk(int evencutatk){
		this.evencutatk = evencutatk;
	}
	
	/**
	 * get 连击攻击
	 * @return 
	 */
	public int getBatteratk(){
		return batteratk;
	}
	
	/**
	 * set 连击攻击
	 */
	public void setBatteratk(int batteratk){
		this.batteratk = batteratk;
	}
	
	
	@Override
	public int getId() {
		return 141102;
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
		//连斩攻击
		buf.append("evencutatk:" + evencutatk +",");
		//连击攻击
		buf.append("batteratk:" + batteratk +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}