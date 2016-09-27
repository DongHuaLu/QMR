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
 * 发送其他礼包信息消息
 */
public class ResSendGiftInfoToClientMessage extends Message{

	//礼包信息列表
	private List<GiftInfo> gifts = new ArrayList<GiftInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//礼包信息列表
		writeShort(buf, gifts.size());
		for (int i = 0; i < gifts.size(); i++) {
			writeBean(buf, gifts.get(i));
		}
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
	
	
	@Override
	public int getId() {
		return 129102;
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
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}