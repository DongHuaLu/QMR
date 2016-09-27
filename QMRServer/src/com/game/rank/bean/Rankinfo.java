package com.game.rank.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 军衔保存信息
 */
public class Rankinfo extends Bean {

	//军衔等级
	private int ranklevel;
	
	//军功数量
	private int rankexp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//军衔等级
		writeInt(buf, this.ranklevel);
		//军功数量
		writeInt(buf, this.rankexp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//军衔等级
		this.ranklevel = readInt(buf);
		//军功数量
		this.rankexp = readInt(buf);
		return true;
	}
	
	/**
	 * get 军衔等级
	 * @return 
	 */
	public int getRanklevel(){
		return ranklevel;
	}
	
	/**
	 * set 军衔等级
	 */
	public void setRanklevel(int ranklevel){
		this.ranklevel = ranklevel;
	}
	
	/**
	 * get 军功数量
	 * @return 
	 */
	public int getRankexp(){
		return rankexp;
	}
	
	/**
	 * set 军功数量
	 */
	public void setRankexp(int rankexp){
		this.rankexp = rankexp;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//军衔等级
		buf.append("ranklevel:" + ranklevel +",");
		//军功数量
		buf.append("rankexp:" + rankexp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}