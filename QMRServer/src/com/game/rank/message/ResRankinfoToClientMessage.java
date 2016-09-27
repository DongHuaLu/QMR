package com.game.rank.message;

import com.game.rank.bean.Rankinfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 军衔信息发送到客户端消息
 */
public class ResRankinfoToClientMessage extends Message{

	//军衔保存信息
	private Rankinfo rankinfo;
	
	//今日得到的军功值
	private int dayrankexp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//军衔保存信息
		writeBean(buf, this.rankinfo);
		//今日得到的军功值
		writeInt(buf, this.dayrankexp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//军衔保存信息
		this.rankinfo = (Rankinfo)readBean(buf, Rankinfo.class);
		//今日得到的军功值
		this.dayrankexp = readInt(buf);
		return true;
	}
	
	/**
	 * get 军衔保存信息
	 * @return 
	 */
	public Rankinfo getRankinfo(){
		return rankinfo;
	}
	
	/**
	 * set 军衔保存信息
	 */
	public void setRankinfo(Rankinfo rankinfo){
		this.rankinfo = rankinfo;
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
		return 117101;
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
		//军衔保存信息
		if(this.rankinfo!=null) buf.append("rankinfo:" + rankinfo.toString() +",");
		//今日得到的军功值
		buf.append("dayrankexp:" + dayrankexp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}