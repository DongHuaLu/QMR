package com.game.stalls.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摊位交易日志列表（发送前端）
 */
public class StallsLogInfo extends Bean {

	//摊位物品信息列表（最大20条）
	private List<StallsSingleLogInfo> stallsloglist = new ArrayList<StallsSingleLogInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摊位物品信息列表（最大20条）
		writeShort(buf, stallsloglist.size());
		for (int i = 0; i < stallsloglist.size(); i++) {
			writeBean(buf, stallsloglist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//摊位物品信息列表（最大20条）
		int stallsloglist_length = readShort(buf);
		for (int i = 0; i < stallsloglist_length; i++) {
			stallsloglist.add((StallsSingleLogInfo)readBean(buf, StallsSingleLogInfo.class));
		}
		return true;
	}
	
	/**
	 * get 摊位物品信息列表（最大20条）
	 * @return 
	 */
	public List<StallsSingleLogInfo> getStallsloglist(){
		return stallsloglist;
	}
	
	/**
	 * set 摊位物品信息列表（最大20条）
	 */
	public void setStallsloglist(List<StallsSingleLogInfo> stallsloglist){
		this.stallsloglist = stallsloglist;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//摊位物品信息列表（最大20条）
		buf.append("stallsloglist:{");
		for (int i = 0; i < stallsloglist.size(); i++) {
			buf.append(stallsloglist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}