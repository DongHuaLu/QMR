package com.game.dianjiangchun.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 点绛唇保存信息
 */
public class DianjiangchunInfo extends Bean {

	//状态
	private int status;
	
	//真气值
	private int nInfuriatingvalue;
	
	//领取真气值
	private int nReceiveintinfuriatingvalue;
	
	//使用次数
	private byte btUsecount;
	
	//最大使用次数
	private byte btMaxcount;
	
	//免费改运次数
	private byte btFreechangeluckcount;
	
	//免费改运最大次数
	private byte btFreechangeluckMaxcount;
	
	//色子信息列表
	private List<Integer> bosonList = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//状态
		writeInt(buf, this.status);
		//真气值
		writeInt(buf, this.nInfuriatingvalue);
		//领取真气值
		writeInt(buf, this.nReceiveintinfuriatingvalue);
		//使用次数
		writeByte(buf, this.btUsecount);
		//最大使用次数
		writeByte(buf, this.btMaxcount);
		//免费改运次数
		writeByte(buf, this.btFreechangeluckcount);
		//免费改运最大次数
		writeByte(buf, this.btFreechangeluckMaxcount);
		//色子信息列表
		writeShort(buf, bosonList.size());
		for (int i = 0; i < bosonList.size(); i++) {
			writeInt(buf, bosonList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//状态
		this.status = readInt(buf);
		//真气值
		this.nInfuriatingvalue = readInt(buf);
		//领取真气值
		this.nReceiveintinfuriatingvalue = readInt(buf);
		//使用次数
		this.btUsecount = readByte(buf);
		//最大使用次数
		this.btMaxcount = readByte(buf);
		//免费改运次数
		this.btFreechangeluckcount = readByte(buf);
		//免费改运最大次数
		this.btFreechangeluckMaxcount = readByte(buf);
		//色子信息列表
		int bosonList_length = readShort(buf);
		for (int i = 0; i < bosonList_length; i++) {
			bosonList.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 状态
	 * @return 
	 */
	public int getStatus(){
		return status;
	}
	
	/**
	 * set 状态
	 */
	public void setStatus(int status){
		this.status = status;
	}
	
	/**
	 * get 真气值
	 * @return 
	 */
	public int getNInfuriatingvalue(){
		return nInfuriatingvalue;
	}
	
	/**
	 * set 真气值
	 */
	public void setNInfuriatingvalue(int nInfuriatingvalue){
		this.nInfuriatingvalue = nInfuriatingvalue;
	}
	
	/**
	 * get 领取真气值
	 * @return 
	 */
	public int getNReceiveintinfuriatingvalue(){
		return nReceiveintinfuriatingvalue;
	}
	
	/**
	 * set 领取真气值
	 */
	public void setNReceiveintinfuriatingvalue(int nReceiveintinfuriatingvalue){
		this.nReceiveintinfuriatingvalue = nReceiveintinfuriatingvalue;
	}
	
	/**
	 * get 使用次数
	 * @return 
	 */
	public byte getBtUsecount(){
		return btUsecount;
	}
	
	/**
	 * set 使用次数
	 */
	public void setBtUsecount(byte btUsecount){
		this.btUsecount = btUsecount;
	}
	
	/**
	 * get 最大使用次数
	 * @return 
	 */
	public byte getBtMaxcount(){
		return btMaxcount;
	}
	
	/**
	 * set 最大使用次数
	 */
	public void setBtMaxcount(byte btMaxcount){
		this.btMaxcount = btMaxcount;
	}
	
	/**
	 * get 免费改运次数
	 * @return 
	 */
	public byte getBtFreechangeluckcount(){
		return btFreechangeluckcount;
	}
	
	/**
	 * set 免费改运次数
	 */
	public void setBtFreechangeluckcount(byte btFreechangeluckcount){
		this.btFreechangeluckcount = btFreechangeluckcount;
	}
	
	/**
	 * get 免费改运最大次数
	 * @return 
	 */
	public byte getBtFreechangeluckMaxcount(){
		return btFreechangeluckMaxcount;
	}
	
	/**
	 * set 免费改运最大次数
	 */
	public void setBtFreechangeluckMaxcount(byte btFreechangeluckMaxcount){
		this.btFreechangeluckMaxcount = btFreechangeluckMaxcount;
	}
	
	/**
	 * get 色子信息列表
	 * @return 
	 */
	public List<Integer> getBosonList(){
		return bosonList;
	}
	
	/**
	 * set 色子信息列表
	 */
	public void setBosonList(List<Integer> bosonList){
		this.bosonList = bosonList;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//状态
		buf.append("status:" + status +",");
		//真气值
		buf.append("nInfuriatingvalue:" + nInfuriatingvalue +",");
		//领取真气值
		buf.append("nReceiveintinfuriatingvalue:" + nReceiveintinfuriatingvalue +",");
		//使用次数
		buf.append("btUsecount:" + btUsecount +",");
		//最大使用次数
		buf.append("btMaxcount:" + btMaxcount +",");
		//免费改运次数
		buf.append("btFreechangeluckcount:" + btFreechangeluckcount +",");
		//免费改运最大次数
		buf.append("btFreechangeluckMaxcount:" + btFreechangeluckMaxcount +",");
		//色子信息列表
		buf.append("bosonList:{");
		for (int i = 0; i < bosonList.size(); i++) {
			buf.append(bosonList.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}