package com.game.scripts.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求脚本函数消息
 */
public class ReqScriptExcuteMessage extends Message{

	//脚本Id
	private int scriptId;
	
	//脚本函数
	private String method;
	
	//脚本参数
	private List<String> paras = new ArrayList<String>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//脚本Id
		writeInt(buf, this.scriptId);
		//脚本函数
		writeString(buf, this.method);
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
		//脚本Id
		this.scriptId = readInt(buf);
		//脚本函数
		this.method = readString(buf);
		//脚本参数
		int paras_length = readShort(buf);
		for (int i = 0; i < paras_length; i++) {
			paras.add(readString(buf));
		}
		return true;
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
	 * get 脚本函数
	 * @return 
	 */
	public String getMethod(){
		return method;
	}
	
	/**
	 * set 脚本函数
	 */
	public void setMethod(String method){
		this.method = method;
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
	public int getId() {
		return 148201;
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
		//脚本Id
		buf.append("scriptId:" + scriptId +",");
		//脚本函数
		if(this.method!=null) buf.append("method:" + method.toString() +",");
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