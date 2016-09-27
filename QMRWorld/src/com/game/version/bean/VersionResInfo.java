package com.game.version.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * RES文件版本信息
 */
public class VersionResInfo extends Bean {

	//版本号
	private int version;
	
	//表名
	private String tabname;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//版本号
		writeInt(buf, this.version);
		//表名
		writeString(buf, this.tabname);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//版本号
		this.version = readInt(buf);
		//表名
		this.tabname = readString(buf);
		return true;
	}
	
	/**
	 * get 版本号
	 * @return 
	 */
	public int getVersion(){
		return version;
	}
	
	/**
	 * set 版本号
	 */
	public void setVersion(int version){
		this.version = version;
	}
	
	/**
	 * get 表名
	 * @return 
	 */
	public String getTabname(){
		return tabname;
	}
	
	/**
	 * set 表名
	 */
	public void setTabname(String tabname){
		this.tabname = tabname;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//版本号
		buf.append("version:" + version +",");
		//表名
		if(this.tabname!=null) buf.append("tabname:" + tabname.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}