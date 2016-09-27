package com.game.marriage.message;

import com.game.marriage.bean.WeddingInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 展示婚宴列表消息
 */
public class ResWeddingListToClientMessage extends Message{

	//婚宴信息列表
	private List<WeddingInfo> weddingInfolist = new ArrayList<WeddingInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//婚宴信息列表
		writeShort(buf, weddingInfolist.size());
		for (int i = 0; i < weddingInfolist.size(); i++) {
			writeBean(buf, weddingInfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//婚宴信息列表
		int weddingInfolist_length = readShort(buf);
		for (int i = 0; i < weddingInfolist_length; i++) {
			weddingInfolist.add((WeddingInfo)readBean(buf, WeddingInfo.class));
		}
		return true;
	}
	
	/**
	 * get 婚宴信息列表
	 * @return 
	 */
	public List<WeddingInfo> getWeddingInfolist(){
		return weddingInfolist;
	}
	
	/**
	 * set 婚宴信息列表
	 */
	public void setWeddingInfolist(List<WeddingInfo> weddingInfolist){
		this.weddingInfolist = weddingInfolist;
	}
	
	
	@Override
	public int getId() {
		return 163108;
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
		//婚宴信息列表
		buf.append("weddingInfolist:{");
		for (int i = 0; i < weddingInfolist.size(); i++) {
			buf.append(weddingInfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}