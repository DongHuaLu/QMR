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
 * 个人通知消息
 */
public class PersonalNoticeMessage extends Message{

	//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
	private byte type;
	
	//信息内容
	private String content;
	
	//付费引导类型
	private int subtype;
	
	//数值
	private List<String> values = new ArrayList<String>();
	//物品内容
	private List<com.game.chat.bean.GoodsInfoRes> goodsinfos = new ArrayList<com.game.chat.bean.GoodsInfoRes>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		writeByte(buf, this.type);
		//信息内容
		writeString(buf, this.content);
		//付费引导类型
		writeInt(buf, this.subtype);
		//数值
		writeShort(buf, values.size());
		for (int i = 0; i < values.size(); i++) {
			writeString(buf, values.get(i));
		}
		//物品内容
		writeShort(buf, goodsinfos.size());
		for (int i = 0; i < goodsinfos.size(); i++) {
			writeBean(buf, goodsinfos.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		this.type = readByte(buf);
		//信息内容
		this.content = readString(buf);
		//付费引导类型
		this.subtype = readInt(buf);
		//数值
		int values_length = readShort(buf);
		for (int i = 0; i < values_length; i++) {
			values.add(readString(buf));
		}
		//物品内容
		int goodsinfos_length = readShort(buf);
		for (int i = 0; i < goodsinfos_length; i++) {
			goodsinfos.add((com.game.chat.bean.GoodsInfoRes)readBean(buf, com.game.chat.bean.GoodsInfoRes.class));
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
	 * get 付费引导类型
	 * @return 
	 */
	public int getSubtype(){
		return subtype;
	}
	
	/**
	 * set 付费引导类型
	 */
	public void setSubtype(int subtype){
		this.subtype = subtype;
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
	
	/**
	 * get 物品内容
	 * @return 
	 */
	public List<com.game.chat.bean.GoodsInfoRes> getGoodsinfos(){
		return goodsinfos;
	}
	
	/**
	 * set 物品内容
	 */
	public void setGoodsinfos(List<com.game.chat.bean.GoodsInfoRes> goodsinfos){
		this.goodsinfos = goodsinfos;
	}
	
	
	@Override
	public int getId() {
		return 109102;
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
		//信息内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		//付费引导类型
		buf.append("subtype:" + subtype +",");
		//数值
		buf.append("values:{");
		for (int i = 0; i < values.size(); i++) {
			buf.append(values.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//物品内容
		buf.append("goodsinfos:{");
		for (int i = 0; i < goodsinfos.size(); i++) {
			buf.append(goodsinfos.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}