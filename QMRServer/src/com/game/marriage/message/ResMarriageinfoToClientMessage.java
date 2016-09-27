package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 婚姻信息消息
 */
public class ResMarriageinfoToClientMessage extends Message{

	//配偶ID
	private long spouseid;
	
	//配偶名字
	private String spousename;
	
	//婚姻唯一ID
	private long marriageid;
	
	//结婚时间（秒）
	private int time;
	
	//戒指模版ID
	private int ringmodelid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//配偶ID
		writeLong(buf, this.spouseid);
		//配偶名字
		writeString(buf, this.spousename);
		//婚姻唯一ID
		writeLong(buf, this.marriageid);
		//结婚时间（秒）
		writeInt(buf, this.time);
		//戒指模版ID
		writeInt(buf, this.ringmodelid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//配偶ID
		this.spouseid = readLong(buf);
		//配偶名字
		this.spousename = readString(buf);
		//婚姻唯一ID
		this.marriageid = readLong(buf);
		//结婚时间（秒）
		this.time = readInt(buf);
		//戒指模版ID
		this.ringmodelid = readInt(buf);
		return true;
	}
	
	/**
	 * get 配偶ID
	 * @return 
	 */
	public long getSpouseid(){
		return spouseid;
	}
	
	/**
	 * set 配偶ID
	 */
	public void setSpouseid(long spouseid){
		this.spouseid = spouseid;
	}
	
	/**
	 * get 配偶名字
	 * @return 
	 */
	public String getSpousename(){
		return spousename;
	}
	
	/**
	 * set 配偶名字
	 */
	public void setSpousename(String spousename){
		this.spousename = spousename;
	}
	
	/**
	 * get 婚姻唯一ID
	 * @return 
	 */
	public long getMarriageid(){
		return marriageid;
	}
	
	/**
	 * set 婚姻唯一ID
	 */
	public void setMarriageid(long marriageid){
		this.marriageid = marriageid;
	}
	
	/**
	 * get 结婚时间（秒）
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 结婚时间（秒）
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 戒指模版ID
	 * @return 
	 */
	public int getRingmodelid(){
		return ringmodelid;
	}
	
	/**
	 * set 戒指模版ID
	 */
	public void setRingmodelid(int ringmodelid){
		this.ringmodelid = ringmodelid;
	}
	
	
	@Override
	public int getId() {
		return 163114;
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
		//配偶ID
		buf.append("spouseid:" + spouseid +",");
		//配偶名字
		if(this.spousename!=null) buf.append("spousename:" + spousename.toString() +",");
		//婚姻唯一ID
		buf.append("marriageid:" + marriageid +",");
		//结婚时间（秒）
		buf.append("time:" + time +",");
		//戒指模版ID
		buf.append("ringmodelid:" + ringmodelid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}