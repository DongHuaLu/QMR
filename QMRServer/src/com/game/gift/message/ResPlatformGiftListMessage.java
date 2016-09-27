package com.game.gift.message;

import com.game.gift.bean.GiftInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送平台礼包信息消息
 */
public class ResPlatformGiftListMessage extends Message{

	//礼包信息列表
	private List<GiftInfo> gifts = new ArrayList<GiftInfo>();
	//面板标识
	private String tag;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//礼包信息列表
		writeShort(buf, gifts.size());
		for (int i = 0; i < gifts.size(); i++) {
			writeBean(buf, gifts.get(i));
		}
		//面板标识
		writeString(buf, this.tag);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//礼包信息列表
		int gifts_length = readShort(buf);
		for (int i = 0; i < gifts_length; i++) {
			gifts.add((GiftInfo)readBean(buf, GiftInfo.class));
		}
		//面板标识
		this.tag = readString(buf);
		return true;
	}
	
	/**
	 * get 礼包信息列表
	 * @return 
	 */
	public List<GiftInfo> getGifts(){
		return gifts;
	}
	
	/**
	 * set 礼包信息列表
	 */
	public void setGifts(List<GiftInfo> gifts){
		this.gifts = gifts;
	}
	
	/**
	 * get 面板标识
	 * @return 
	 */
	public String getTag(){
		return tag;
	}
	
	/**
	 * set 面板标识
	 */
	public void setTag(String tag){
		this.tag = tag;
	}
	
	
	@Override
	public int getId() {
		return 129103;
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
		//礼包信息列表
		buf.append("gifts:{");
		for (int i = 0; i < gifts.size(); i++) {
			buf.append(gifts.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//面板标识
		if(this.tag!=null) buf.append("tag:" + tag.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}