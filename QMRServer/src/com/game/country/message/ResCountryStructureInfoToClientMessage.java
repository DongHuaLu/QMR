package com.game.country.message;

import com.game.country.bean.CountryStructureInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求王城结构信息（打开面板）消息
 */
public class ResCountryStructureInfoToClientMessage extends Message{

	//王城结构信息
	private CountryStructureInfo countrystructureInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//王城结构信息
		writeBean(buf, this.countrystructureInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//王城结构信息
		this.countrystructureInfo = (CountryStructureInfo)readBean(buf, CountryStructureInfo.class);
		return true;
	}
	
	/**
	 * get 王城结构信息
	 * @return 
	 */
	public CountryStructureInfo getCountrystructureInfo(){
		return countrystructureInfo;
	}
	
	/**
	 * set 王城结构信息
	 */
	public void setCountrystructureInfo(CountryStructureInfo countrystructureInfo){
		this.countrystructureInfo = countrystructureInfo;
	}
	
	
	@Override
	public int getId() {
		return 146105;
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
		//王城结构信息
		if(this.countrystructureInfo!=null) buf.append("countrystructureInfo:" + countrystructureInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}