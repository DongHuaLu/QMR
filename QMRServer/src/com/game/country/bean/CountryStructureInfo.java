package com.game.country.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 王城结构信息
 */
public class CountryStructureInfo extends Bean {

	//攻城时间
	private String Siegetime;
	
	//王城帮会ID
	private long guildid;
	
	//王城帮会名字
	private String guildname;
	
	//王城成员信息列表
	private List<CountryMemberInfo> countrymemberInfolist = new ArrayList<CountryMemberInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//攻城时间
		writeString(buf, this.Siegetime);
		//王城帮会ID
		writeLong(buf, this.guildid);
		//王城帮会名字
		writeString(buf, this.guildname);
		//王城成员信息列表
		writeShort(buf, countrymemberInfolist.size());
		for (int i = 0; i < countrymemberInfolist.size(); i++) {
			writeBean(buf, countrymemberInfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//攻城时间
		this.Siegetime = readString(buf);
		//王城帮会ID
		this.guildid = readLong(buf);
		//王城帮会名字
		this.guildname = readString(buf);
		//王城成员信息列表
		int countrymemberInfolist_length = readShort(buf);
		for (int i = 0; i < countrymemberInfolist_length; i++) {
			countrymemberInfolist.add((CountryMemberInfo)readBean(buf, CountryMemberInfo.class));
		}
		return true;
	}
	
	/**
	 * get 攻城时间
	 * @return 
	 */
	public String getSiegetime(){
		return Siegetime;
	}
	
	/**
	 * set 攻城时间
	 */
	public void setSiegetime(String Siegetime){
		this.Siegetime = Siegetime;
	}
	
	/**
	 * get 王城帮会ID
	 * @return 
	 */
	public long getGuildid(){
		return guildid;
	}
	
	/**
	 * set 王城帮会ID
	 */
	public void setGuildid(long guildid){
		this.guildid = guildid;
	}
	
	/**
	 * get 王城帮会名字
	 * @return 
	 */
	public String getGuildname(){
		return guildname;
	}
	
	/**
	 * set 王城帮会名字
	 */
	public void setGuildname(String guildname){
		this.guildname = guildname;
	}
	
	/**
	 * get 王城成员信息列表
	 * @return 
	 */
	public List<CountryMemberInfo> getCountrymemberInfolist(){
		return countrymemberInfolist;
	}
	
	/**
	 * set 王城成员信息列表
	 */
	public void setCountrymemberInfolist(List<CountryMemberInfo> countrymemberInfolist){
		this.countrymemberInfolist = countrymemberInfolist;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//攻城时间
		if(this.Siegetime!=null) buf.append("Siegetime:" + Siegetime.toString() +",");
		//王城帮会ID
		buf.append("guildid:" + guildid +",");
		//王城帮会名字
		if(this.guildname!=null) buf.append("guildname:" + guildname.toString() +",");
		//王城成员信息列表
		buf.append("countrymemberInfolist:{");
		for (int i = 0; i < countrymemberInfolist.size(); i++) {
			buf.append(countrymemberInfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}