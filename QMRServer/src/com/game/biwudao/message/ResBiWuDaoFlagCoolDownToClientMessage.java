package com.game.biwudao.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 夺旗剩余冷却时间消息
 */
public class ResBiWuDaoFlagCoolDownToClientMessage extends Message{

	//夺旗剩余冷却时间
	private int flagcooldown;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//夺旗剩余冷却时间
		writeInt(buf, this.flagcooldown);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//夺旗剩余冷却时间
		this.flagcooldown = readInt(buf);
		return true;
	}
	
	/**
	 * get 夺旗剩余冷却时间
	 * @return 
	 */
	public int getFlagcooldown(){
		return flagcooldown;
	}
	
	/**
	 * set 夺旗剩余冷却时间
	 */
	public void setFlagcooldown(int flagcooldown){
		this.flagcooldown = flagcooldown;
	}
	
	
	@Override
	public int getId() {
		return 157105;
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
		//夺旗剩余冷却时间
		buf.append("flagcooldown:" + flagcooldown +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}