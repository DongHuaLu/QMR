package com.game.rank.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获得军功改变消息
 */
public class ResRankExpToClientMessage extends Message{

	//当前总军功值
	private int ranksum;
	
	//本次得到的军功值
	private int rankexp;
	
	//今日得到的军功值
	private int dayrankexp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前总军功值
		writeInt(buf, this.ranksum);
		//本次得到的军功值
		writeInt(buf, this.rankexp);
		//今日得到的军功值
		writeInt(buf, this.dayrankexp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前总军功值
		this.ranksum = readInt(buf);
		//本次得到的军功值
		this.rankexp = readInt(buf);
		//今日得到的军功值
		this.dayrankexp = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前总军功值
	 * @return 
	 */
	public int getRanksum(){
		return ranksum;
	}
	
	/**
	 * set 当前总军功值
	 */
	public void setRanksum(int ranksum){
		this.ranksum = ranksum;
	}
	
	/**
	 * get 本次得到的军功值
	 * @return 
	 */
	public int getRankexp(){
		return rankexp;
	}
	
	/**
	 * set 本次得到的军功值
	 */
	public void setRankexp(int rankexp){
		this.rankexp = rankexp;
	}
	
	/**
	 * get 今日得到的军功值
	 * @return 
	 */
	public int getDayrankexp(){
		return dayrankexp;
	}
	
	/**
	 * set 今日得到的军功值
	 */
	public void setDayrankexp(int dayrankexp){
		this.dayrankexp = dayrankexp;
	}
	
	
	@Override
	public int getId() {
		return 117102;
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
		//当前总军功值
		buf.append("ranksum:" + ranksum +",");
		//本次得到的军功值
		buf.append("rankexp:" + rankexp +",");
		//今日得到的军功值
		buf.append("dayrankexp:" + dayrankexp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}