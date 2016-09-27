package com.game.country.message;

import com.game.country.bean.CountryWarInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 地图广播攻城玉玺即时消息消息
 */
public class ResCountrySiegeYuXiImmediateToClientMessage extends Message{

	//攻城玉玺消息
	private CountryWarInfo countrywarinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//攻城玉玺消息
		writeBean(buf, this.countrywarinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//攻城玉玺消息
		this.countrywarinfo = (CountryWarInfo)readBean(buf, CountryWarInfo.class);
		return true;
	}
	
	/**
	 * get 攻城玉玺消息
	 * @return 
	 */
	public CountryWarInfo getCountrywarinfo(){
		return countrywarinfo;
	}
	
	/**
	 * set 攻城玉玺消息
	 */
	public void setCountrywarinfo(CountryWarInfo countrywarinfo){
		this.countrywarinfo = countrywarinfo;
	}
	
	
	@Override
	public int getId() {
		return 146104;
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
		//攻城玉玺消息
		if(this.countrywarinfo!=null) buf.append("countrywarinfo:" + countrywarinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}