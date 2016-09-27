package com.game.collect.message;

import com.game.collect.bean.CollectInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 个人藏品信息消息
 */
public class ResCollectInfoMessage extends Message{

	//0 初始 1物品提交 2批量提交
	private byte type;
	
	//藏品信息
	private List<CollectInfo> collectinfo = new ArrayList<CollectInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0 初始 1物品提交 2批量提交
		writeByte(buf, this.type);
		//藏品信息
		writeShort(buf, collectinfo.size());
		for (int i = 0; i < collectinfo.size(); i++) {
			writeBean(buf, collectinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0 初始 1物品提交 2批量提交
		this.type = readByte(buf);
		//藏品信息
		int collectinfo_length = readShort(buf);
		for (int i = 0; i < collectinfo_length; i++) {
			collectinfo.add((CollectInfo)readBean(buf, CollectInfo.class));
		}
		return true;
	}
	
	/**
	 * get 0 初始 1物品提交 2批量提交
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0 初始 1物品提交 2批量提交
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 藏品信息
	 * @return 
	 */
	public List<CollectInfo> getCollectinfo(){
		return collectinfo;
	}
	
	/**
	 * set 藏品信息
	 */
	public void setCollectinfo(List<CollectInfo> collectinfo){
		this.collectinfo = collectinfo;
	}
	
	
	@Override
	public int getId() {
		return 153101;
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
		//0 初始 1物品提交 2批量提交
		buf.append("type:" + type +",");
		//藏品信息
		buf.append("collectinfo:{");
		for (int i = 0; i < collectinfo.size(); i++) {
			buf.append(collectinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}