package com.game.map.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围消失效果消息
 */
public class ResRoundEffectDisappearMessage extends Message{

	//消失效果列表
	private List<Long> effectid = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消失效果列表
		writeShort(buf, effectid.size());
		for (int i = 0; i < effectid.size(); i++) {
			writeLong(buf, effectid.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消失效果列表
		int effectid_length = readShort(buf);
		for (int i = 0; i < effectid_length; i++) {
			effectid.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 消失效果列表
	 * @return 
	 */
	public List<Long> getEffectid(){
		return effectid;
	}
	
	/**
	 * set 消失效果列表
	 */
	public void setEffectid(List<Long> effectid){
		this.effectid = effectid;
	}
	
	
	@Override
	public int getId() {
		return 101132;
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
		//消失效果列表
		buf.append("effectid:{");
		for (int i = 0; i < effectid.size(); i++) {
			buf.append(effectid.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}