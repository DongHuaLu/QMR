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
 * 活动提示消息消息
 */
public class PersonalNoticeActivityMessage extends Message{

	//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
	private byte type;
	
	//活动ID
	private int activityId;
	
	//信息内容
	private String content;
	
	//参数
	private List<String> values = new ArrayList<String>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		writeByte(buf, this.type);
		//活动ID
		writeInt(buf, this.activityId);
		//信息内容
		writeString(buf, this.content);
		//参数
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
		//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		this.type = readByte(buf);
		//活动ID
		this.activityId = readInt(buf);
		//信息内容
		this.content = readString(buf);
		//参数
		int values_length = readShort(buf);
		for (int i = 0; i < values_length; i++) {
			values.add(readString(buf));
		}
		return true;
	}
	
	/**
	 * get 信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 活动ID
	 * @return 
	 */
	public int getActivityId(){
		return activityId;
	}
	
	/**
	 * set 活动ID
	 */
	public void setActivityId(int activityId){
		this.activityId = activityId;
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
	 * get 参数
	 * @return 
	 */
	public List<String> getValues(){
		return values;
	}
	
	/**
	 * set 参数
	 */
	public void setValues(List<String> values){
		this.values = values;
	}
	
	
	@Override
	public int getId() {
		return 109111;
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
		//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		buf.append("type:" + type +",");
		//活动ID
		buf.append("activityId:" + activityId +",");
		//信息内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		//参数
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