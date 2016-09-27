package com.game.prompt.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 防沉迷信息消息
 */
public class NonagePromptMessage extends Message{

	//信息内容
	private String content;
	
	//数值
	private List<String> values = new ArrayList<String>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//信息内容
		writeString(buf, this.content);
		//数值
		writeShort(buf, values.size());
		for (int i = 0; i < values.size(); i++) {
			writeString(buf, values.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//信息内容
		this.content = readString(buf);
		//数值
		int values_length = readShort(buf);
		for (int i = 0; i < values_length; i++) {
			values.add(readString(buf));
		}
		return true;
	}
	
	/**
	 * get 信息内容
	 * @return 
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * set 信息内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	/**
	 * get 数值
	 * @return 
	 */
	public List<String> getValues(){
		return values;
	}
	
	/**
	 * set 数值
	 */
	public void setValues(List<String> values){
		this.values = values;
	}
	
	
	@Override
	public int getId() {
		return 109109;
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
		//信息内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		//数值
		buf.append("values:{");
		for (int i = 0; i < values.size(); i++) {
			buf.append(values.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}