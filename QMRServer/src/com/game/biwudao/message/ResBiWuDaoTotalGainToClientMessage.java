package com.game.biwudao.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送比武岛累计收益消息
 */
public class ResBiWuDaoTotalGainToClientMessage extends Message{

	//累计获得经验
	private int totalexp;
	
	//累计获得真气
	private int totalzhenqi;
	
	//累计获得军功
	private int totalrank;
	
	//累计开启宝箱
	private int totalBox;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//累计获得经验
		writeInt(buf, this.totalexp);
		//累计获得真气
		writeInt(buf, this.totalzhenqi);
		//累计获得军功
		writeInt(buf, this.totalrank);
		//累计开启宝箱
		writeInt(buf, this.totalBox);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//累计获得经验
		this.totalexp = readInt(buf);
		//累计获得真气
		this.totalzhenqi = readInt(buf);
		//累计获得军功
		this.totalrank = readInt(buf);
		//累计开启宝箱
		this.totalBox = readInt(buf);
		return true;
	}
	
	/**
	 * get 累计获得经验
	 * @return 
	 */
	public int getTotalexp(){
		return totalexp;
	}
	
	/**
	 * set 累计获得经验
	 */
	public void setTotalexp(int totalexp){
		this.totalexp = totalexp;
	}
	
	/**
	 * get 累计获得真气
	 * @return 
	 */
	public int getTotalzhenqi(){
		return totalzhenqi;
	}
	
	/**
	 * set 累计获得真气
	 */
	public void setTotalzhenqi(int totalzhenqi){
		this.totalzhenqi = totalzhenqi;
	}
	
	/**
	 * get 累计获得军功
	 * @return 
	 */
	public int getTotalrank(){
		return totalrank;
	}
	
	/**
	 * set 累计获得军功
	 */
	public void setTotalrank(int totalrank){
		this.totalrank = totalrank;
	}
	
	/**
	 * get 累计开启宝箱
	 * @return 
	 */
	public int getTotalBox(){
		return totalBox;
	}
	
	/**
	 * set 累计开启宝箱
	 */
	public void setTotalBox(int totalBox){
		this.totalBox = totalBox;
	}
	
	
	@Override
	public int getId() {
		return 157103;
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
		//累计获得经验
		buf.append("totalexp:" + totalexp +",");
		//累计获得真气
		buf.append("totalzhenqi:" + totalzhenqi +",");
		//累计获得军功
		buf.append("totalrank:" + totalrank +",");
		//累计开启宝箱
		buf.append("totalBox:" + totalBox +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}