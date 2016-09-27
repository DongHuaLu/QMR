package com.game.spirittree.message;

import com.game.spirittree.bean.FruitInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送行会灵树消息消息
 */
public class ResOpenGuildFruitToClientMessage extends Message{

	//成熟果实信息列表
	private List<FruitInfo> fruitinfo = new ArrayList<FruitInfo>();
	//干旱果实列表
	private List<FruitInfo> aridfruitinfo = new ArrayList<FruitInfo>();
	//抢摘次数
	private int theftnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//成熟果实信息列表
		writeShort(buf, fruitinfo.size());
		for (int i = 0; i < fruitinfo.size(); i++) {
			writeBean(buf, fruitinfo.get(i));
		}
		//干旱果实列表
		writeShort(buf, aridfruitinfo.size());
		for (int i = 0; i < aridfruitinfo.size(); i++) {
			writeBean(buf, aridfruitinfo.get(i));
		}
		//抢摘次数
		writeInt(buf, this.theftnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//成熟果实信息列表
		int fruitinfo_length = readShort(buf);
		for (int i = 0; i < fruitinfo_length; i++) {
			fruitinfo.add((FruitInfo)readBean(buf, FruitInfo.class));
		}
		//干旱果实列表
		int aridfruitinfo_length = readShort(buf);
		for (int i = 0; i < aridfruitinfo_length; i++) {
			aridfruitinfo.add((FruitInfo)readBean(buf, FruitInfo.class));
		}
		//抢摘次数
		this.theftnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 成熟果实信息列表
	 * @return 
	 */
	public List<FruitInfo> getFruitinfo(){
		return fruitinfo;
	}
	
	/**
	 * set 成熟果实信息列表
	 */
	public void setFruitinfo(List<FruitInfo> fruitinfo){
		this.fruitinfo = fruitinfo;
	}
	
	/**
	 * get 干旱果实列表
	 * @return 
	 */
	public List<FruitInfo> getAridfruitinfo(){
		return aridfruitinfo;
	}
	
	/**
	 * set 干旱果实列表
	 */
	public void setAridfruitinfo(List<FruitInfo> aridfruitinfo){
		this.aridfruitinfo = aridfruitinfo;
	}
	
	/**
	 * get 抢摘次数
	 * @return 
	 */
	public int getTheftnum(){
		return theftnum;
	}
	
	/**
	 * set 抢摘次数
	 */
	public void setTheftnum(int theftnum){
		this.theftnum = theftnum;
	}
	
	
	@Override
	public int getId() {
		return 133103;
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
		//成熟果实信息列表
		buf.append("fruitinfo:{");
		for (int i = 0; i < fruitinfo.size(); i++) {
			buf.append(fruitinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//干旱果实列表
		buf.append("aridfruitinfo:{");
		for (int i = 0; i < aridfruitinfo.size(); i++) {
			buf.append(aridfruitinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//抢摘次数
		buf.append("theftnum:" + theftnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}