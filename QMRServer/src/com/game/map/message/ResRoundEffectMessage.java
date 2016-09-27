package com.game.map.message;

import com.game.map.bean.EffectInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围效果消息
 */
public class ResRoundEffectMessage extends Message{

	//周围效果信息
	private EffectInfo Effect;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//周围效果信息
		writeBean(buf, this.Effect);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//周围效果信息
		this.Effect = (EffectInfo)readBean(buf, EffectInfo.class);
		return true;
	}
	
	/**
	 * get 周围效果信息
	 * @return 
	 */
	public EffectInfo getEffect(){
		return Effect;
	}
	
	/**
	 * set 周围效果信息
	 */
	public void setEffect(EffectInfo Effect){
		this.Effect = Effect;
	}
	
	
	@Override
	public int getId() {
		return 101131;
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
		//周围效果信息
		if(this.Effect!=null) buf.append("Effect:" + Effect.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}