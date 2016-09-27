package com.game.chat.message;

import com.game.chat.bean.GoodsInfoReq;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 聊天请求消息
 */
public class ChatRequestMessage extends Message{

	//消息类型
	private int chattype;
	
	//私聊时需要填写角色名
	private String roleName;
	
	//聊天消息
	private String condition;
	
	//附加属性请求
	private List<GoodsInfoReq> other = new ArrayList<GoodsInfoReq>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消息类型
		writeInt(buf, this.chattype);
		//私聊时需要填写角色名
		writeString(buf, this.roleName);
		//聊天消息
		writeString(buf, this.condition);
		//附加属性请求
		writeShort(buf, other.size());
		for (int i = 0; i < other.size(); i++) {
			writeBean(buf, other.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消息类型
		this.chattype = readInt(buf);
		//私聊时需要填写角色名
		this.roleName = readString(buf);
		//聊天消息
		this.condition = readString(buf);
		//附加属性请求
		int other_length = readShort(buf);
		for (int i = 0; i < other_length; i++) {
			other.add((GoodsInfoReq)readBean(buf, GoodsInfoReq.class));
		}
		return true;
	}
	
	/**
	 * get 消息类型
	 * @return 
	 */
	public int getChattype(){
		return chattype;
	}
	
	/**
	 * set 消息类型
	 */
	public void setChattype(int chattype){
		this.chattype = chattype;
	}
	
	/**
	 * get 私聊时需要填写角色名
	 * @return 
	 */
	public String getRoleName(){
		return roleName;
	}
	
	/**
	 * set 私聊时需要填写角色名
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	
	/**
	 * get 聊天消息
	 * @return 
	 */
	public String getCondition(){
		return condition;
	}
	
	/**
	 * set 聊天消息
	 */
	public void setCondition(String condition){
		this.condition = condition;
	}
	
	/**
	 * get 附加属性请求
	 * @return 
	 */
	public List<GoodsInfoReq> getOther(){
		return other;
	}
	
	/**
	 * set 附加属性请求
	 */
	public void setOther(List<GoodsInfoReq> other){
		this.other = other;
	}
	
	
	@Override
	public int getId() {
		return 111201;
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
		//消息类型
		buf.append("chattype:" + chattype +",");
		//私聊时需要填写角色名
		if(this.roleName!=null) buf.append("roleName:" + roleName.toString() +",");
		//聊天消息
		if(this.condition!=null) buf.append("condition:" + condition.toString() +",");
		//附加属性请求
		buf.append("other:{");
		for (int i = 0; i < other.size(); i++) {
			buf.append(other.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}