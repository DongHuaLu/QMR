package com.game.stalls.message;

import com.game.stalls.bean.StallsInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 购买商品后，更新列表消息
 */
public class ResStallsBuyMessage extends Message{

	//查看指定摊位信息
	private StallsInfo stallsinfo;
	
	//是否允许评分，0允许，1不允许
	private byte israting;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//查看指定摊位信息
		writeBean(buf, this.stallsinfo);
		//是否允许评分，0允许，1不允许
		writeByte(buf, this.israting);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//查看指定摊位信息
		this.stallsinfo = (StallsInfo)readBean(buf, StallsInfo.class);
		//是否允许评分，0允许，1不允许
		this.israting = readByte(buf);
		return true;
	}
	
	/**
	 * get 查看指定摊位信息
	 * @return 
	 */
	public StallsInfo getStallsinfo(){
		return stallsinfo;
	}
	
	/**
	 * set 查看指定摊位信息
	 */
	public void setStallsinfo(StallsInfo stallsinfo){
		this.stallsinfo = stallsinfo;
	}
	
	/**
	 * get 是否允许评分，0允许，1不允许
	 * @return 
	 */
	public byte getIsrating(){
		return israting;
	}
	
	/**
	 * set 是否允许评分，0允许，1不允许
	 */
	public void setIsrating(byte israting){
		this.israting = israting;
	}
	
	
	@Override
	public int getId() {
		return 123104;
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
		//查看指定摊位信息
		if(this.stallsinfo!=null) buf.append("stallsinfo:" + stallsinfo.toString() +",");
		//是否允许评分，0允许，1不允许
		buf.append("israting:" + israting +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}