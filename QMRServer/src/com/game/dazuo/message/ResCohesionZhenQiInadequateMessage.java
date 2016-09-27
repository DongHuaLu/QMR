package com.game.dazuo.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 使用凝丹，灵泉露不足，弹出面板消息
 */
public class ResCohesionZhenQiInadequateMessage extends Message{

	//玩家名字
	private String playername;
	
	//今日使用他人的真气凝丹次数
	private int usenum;
	
	//灵泉露缺少数量
	private int lacknum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家名字
		writeString(buf, this.playername);
		//今日使用他人的真气凝丹次数
		writeInt(buf, this.usenum);
		//灵泉露缺少数量
		writeInt(buf, this.lacknum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家名字
		this.playername = readString(buf);
		//今日使用他人的真气凝丹次数
		this.usenum = readInt(buf);
		//灵泉露缺少数量
		this.lacknum = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玩家名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 今日使用他人的真气凝丹次数
	 * @return 
	 */
	public int getUsenum(){
		return usenum;
	}
	
	/**
	 * set 今日使用他人的真气凝丹次数
	 */
	public void setUsenum(int usenum){
		this.usenum = usenum;
	}
	
	/**
	 * get 灵泉露缺少数量
	 * @return 
	 */
	public int getLacknum(){
		return lacknum;
	}
	
	/**
	 * set 灵泉露缺少数量
	 */
	public void setLacknum(int lacknum){
		this.lacknum = lacknum;
	}
	
	
	@Override
	public int getId() {
		return 136105;
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
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//今日使用他人的真气凝丹次数
		buf.append("usenum:" + usenum +",");
		//灵泉露缺少数量
		buf.append("lacknum:" + lacknum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}