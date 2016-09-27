package com.game.marriage.message;

import com.game.marriage.bean.RedEnvelopeInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 红包信息消息
 */
public class ResRedEnvelopeToClientMessage extends Message{

	//红包信息列表
	private List<RedEnvelopeInfo> redenvelopelist = new ArrayList<RedEnvelopeInfo>();
	//红包总收益
	private int redsum;
	
	//1已经领取，0没有领取
	private byte isreceive;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//红包信息列表
		writeShort(buf, redenvelopelist.size());
		for (int i = 0; i < redenvelopelist.size(); i++) {
			writeBean(buf, redenvelopelist.get(i));
		}
		//红包总收益
		writeInt(buf, this.redsum);
		//1已经领取，0没有领取
		writeByte(buf, this.isreceive);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//红包信息列表
		int redenvelopelist_length = readShort(buf);
		for (int i = 0; i < redenvelopelist_length; i++) {
			redenvelopelist.add((RedEnvelopeInfo)readBean(buf, RedEnvelopeInfo.class));
		}
		//红包总收益
		this.redsum = readInt(buf);
		//1已经领取，0没有领取
		this.isreceive = readByte(buf);
		return true;
	}
	
	/**
	 * get 红包信息列表
	 * @return 
	 */
	public List<RedEnvelopeInfo> getRedenvelopelist(){
		return redenvelopelist;
	}
	
	/**
	 * set 红包信息列表
	 */
	public void setRedenvelopelist(List<RedEnvelopeInfo> redenvelopelist){
		this.redenvelopelist = redenvelopelist;
	}
	
	/**
	 * get 红包总收益
	 * @return 
	 */
	public int getRedsum(){
		return redsum;
	}
	
	/**
	 * set 红包总收益
	 */
	public void setRedsum(int redsum){
		this.redsum = redsum;
	}
	
	/**
	 * get 1已经领取，0没有领取
	 * @return 
	 */
	public byte getIsreceive(){
		return isreceive;
	}
	
	/**
	 * set 1已经领取，0没有领取
	 */
	public void setIsreceive(byte isreceive){
		this.isreceive = isreceive;
	}
	
	
	@Override
	public int getId() {
		return 163107;
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
		//红包信息列表
		buf.append("redenvelopelist:{");
		for (int i = 0; i < redenvelopelist.size(); i++) {
			buf.append(redenvelopelist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//红包总收益
		buf.append("redsum:" + redsum +",");
		//1已经领取，0没有领取
		buf.append("isreceive:" + isreceive +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}