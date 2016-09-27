package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家被关监狱情况消息
 */
public class ResPlayerPrisonStateMessage extends Message{

	//关监狱次数
	private int prisontimes;
	
	//监狱剩余时间(单位 秒)
	private int prisonremaintime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//关监狱次数
		writeInt(buf, this.prisontimes);
		//监狱剩余时间(单位 秒)
		writeInt(buf, this.prisonremaintime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//关监狱次数
		this.prisontimes = readInt(buf);
		//监狱剩余时间(单位 秒)
		this.prisonremaintime = readInt(buf);
		return true;
	}
	
	/**
	 * get 关监狱次数
	 * @return 
	 */
	public int getPrisontimes(){
		return prisontimes;
	}
	
	/**
	 * set 关监狱次数
	 */
	public void setPrisontimes(int prisontimes){
		this.prisontimes = prisontimes;
	}
	
	/**
	 * get 监狱剩余时间(单位 秒)
	 * @return 
	 */
	public int getPrisonremaintime(){
		return prisonremaintime;
	}
	
	/**
	 * set 监狱剩余时间(单位 秒)
	 */
	public void setPrisonremaintime(int prisonremaintime){
		this.prisonremaintime = prisonremaintime;
	}
	
	
	@Override
	public int getId() {
		return 103134;
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
		//关监狱次数
		buf.append("prisontimes:" + prisontimes +",");
		//监狱剩余时间(单位 秒)
		buf.append("prisonremaintime:" + prisonremaintime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}