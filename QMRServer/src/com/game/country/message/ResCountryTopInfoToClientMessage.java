package com.game.country.message;

import com.game.country.bean.CountryTopInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 王城战排行榜信息消息
 */
public class ResCountryTopInfoToClientMessage extends Message{

	//王城战排行榜信息
	private List<CountryTopInfo> countryTopInfolist = new ArrayList<CountryTopInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//王城战排行榜信息
		writeShort(buf, countryTopInfolist.size());
		for (int i = 0; i < countryTopInfolist.size(); i++) {
			writeBean(buf, countryTopInfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//王城战排行榜信息
		int countryTopInfolist_length = readShort(buf);
		for (int i = 0; i < countryTopInfolist_length; i++) {
			countryTopInfolist.add((CountryTopInfo)readBean(buf, CountryTopInfo.class));
		}
		return true;
	}
	
	/**
	 * get 王城战排行榜信息
	 * @return 
	 */
	public List<CountryTopInfo> getCountryTopInfolist(){
		return countryTopInfolist;
	}
	
	/**
	 * set 王城战排行榜信息
	 */
	public void setCountryTopInfolist(List<CountryTopInfo> countryTopInfolist){
		this.countryTopInfolist = countryTopInfolist;
	}
	
	
	@Override
	public int getId() {
		return 146114;
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
		//王城战排行榜信息
		buf.append("countryTopInfolist:{");
		for (int i = 0; i < countryTopInfolist.size(); i++) {
			buf.append(countryTopInfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}