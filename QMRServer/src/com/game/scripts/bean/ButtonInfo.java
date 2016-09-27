package com.game.scripts.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 面板按钮信息
 */
public class ButtonInfo extends Bean {

	//按钮控件ID名称
	private String name;
	
	//脚本Id
	private int scriptId;
	
	//调用方法名
	private String method;
	
	//0不关闭，1点击后关闭
	private byte isclose;
	
	//脚本参数
	private List<String> paras = new ArrayList<String>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//按钮控件ID名称
		writeString(buf, this.name);
		//脚本Id
		writeInt(buf, this.scriptId);
		//调用方法名
		writeString(buf, this.method);
		//0不关闭，1点击后关闭
		writeByte(buf, this.isclose);
		//脚本参数
		writeShort(buf, paras.size());
		for (int i = 0; i < paras.size(); i++) {
			writeString(buf, paras.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//按钮控件ID名称
		this.name = readString(buf);
		//脚本Id
		this.scriptId = readInt(buf);
		//调用方法名
		this.method = readString(buf);
		//0不关闭，1点击后关闭
		this.isclose = readByte(buf);
		//脚本参数
		int paras_length = readShort(buf);
		for (int i = 0; i < paras_length; i++) {
			paras.add(readString(buf));
		}
		return true;
	}
	
	/**
	 * get 按钮控件ID名称
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 按钮控件ID名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 脚本Id
	 * @return 
	 */
	public int getScriptId(){
		return scriptId;
	}
	
	/**
	 * set 脚本Id
	 */
	public void setScriptId(int scriptId){
		this.scriptId = scriptId;
	}
	
	/**
	 * get 调用方法名
	 * @return 
	 */
	public String getMethod(){
		return method;
	}
	
	/**
	 * set 调用方法名
	 */
	public void setMethod(String method){
		this.method = method;
	}
	
	/**
	 * get 0不关闭，1点击后关闭
	 * @return 
	 */
	public byte getIsclose(){
		return isclose;
	}
	
	/**
	 * set 0不关闭，1点击后关闭
	 */
	public void setIsclose(byte isclose){
		this.isclose = isclose;
	}
	
	/**
	 * get 脚本参数
	 * @return 
	 */
	public List<String> getParas(){
		return paras;
	}
	
	/**
	 * set 脚本参数
	 */
	public void setParas(List<String> paras){
		this.paras = paras;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//按钮控件ID名称
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//脚本Id
		buf.append("scriptId:" + scriptId +",");
		//调用方法名
		if(this.method!=null) buf.append("method:" + method.toString() +",");
		//0不关闭，1点击后关闭
		buf.append("isclose:" + isclose +",");
		//脚本参数
		buf.append("paras:{");
		for (int i = 0; i < paras.size(); i++) {
			buf.append(paras.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}