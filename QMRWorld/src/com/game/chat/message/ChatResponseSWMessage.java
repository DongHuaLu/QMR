package com.game.chat.message;

import com.game.chat.bean.GoodsInfoRes;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 广播聊天消息消息
 */
public class ChatResponseSWMessage extends Message{

	//消息类型
	private int chattype;
	
	//发送人id
	private long chater;
	
	//发送人名字
	private String chatername;
	
	//发送人国家id
	private int country;
	
	//发送人王城等级
	private int chaterkinglv;
	
	//接收人名字
	private String receivername;
	
	//聊天消息
	private String condition;
	
	//是否禁言 1禁言 0未禁言
	private byte isprohibit;
	
	//附加属性
	private List<GoodsInfoRes> other = new ArrayList<GoodsInfoRes>();
	//是否GM1是 0否
	private byte isgm;
	
	//发送人VIP类型
	private short senderviptype;
	
	//接收人VIP类型
	private short receiverviptype;
	
	//发送人WEBVIP类型
	private int webvip;
	
	//接收人WEBVIP类型
	private int receiverwebvip;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消息类型
		writeInt(buf, this.chattype);
		//发送人id
		writeLong(buf, this.chater);
		//发送人名字
		writeString(buf, this.chatername);
		//发送人国家id
		writeInt(buf, this.country);
		//发送人王城等级
		writeInt(buf, this.chaterkinglv);
		//接收人名字
		writeString(buf, this.receivername);
		//聊天消息
		writeString(buf, this.condition);
		//是否禁言 1禁言 0未禁言
		writeByte(buf, this.isprohibit);
		//附加属性
		writeShort(buf, other.size());
		for (int i = 0; i < other.size(); i++) {
			writeBean(buf, other.get(i));
		}
		//是否GM1是 0否
		writeByte(buf, this.isgm);
		//发送人VIP类型
		writeShort(buf, this.senderviptype);
		//接收人VIP类型
		writeShort(buf, this.receiverviptype);
		//发送人WEBVIP类型
		writeInt(buf, this.webvip);
		//接收人WEBVIP类型
		writeInt(buf, this.receiverwebvip);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消息类型
		this.chattype = readInt(buf);
		//发送人id
		this.chater = readLong(buf);
		//发送人名字
		this.chatername = readString(buf);
		//发送人国家id
		this.country = readInt(buf);
		//发送人王城等级
		this.chaterkinglv = readInt(buf);
		//接收人名字
		this.receivername = readString(buf);
		//聊天消息
		this.condition = readString(buf);
		//是否禁言 1禁言 0未禁言
		this.isprohibit = readByte(buf);
		//附加属性
		int other_length = readShort(buf);
		for (int i = 0; i < other_length; i++) {
			other.add((GoodsInfoRes)readBean(buf, GoodsInfoRes.class));
		}
		//是否GM1是 0否
		this.isgm = readByte(buf);
		//发送人VIP类型
		this.senderviptype = readShort(buf);
		//接收人VIP类型
		this.receiverviptype = readShort(buf);
		//发送人WEBVIP类型
		this.webvip = readInt(buf);
		//接收人WEBVIP类型
		this.receiverwebvip = readInt(buf);
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
	 * get 发送人id
	 * @return 
	 */
	public long getChater(){
		return chater;
	}
	
	/**
	 * set 发送人id
	 */
	public void setChater(long chater){
		this.chater = chater;
	}
	
	/**
	 * get 发送人名字
	 * @return 
	 */
	public String getChatername(){
		return chatername;
	}
	
	/**
	 * set 发送人名字
	 */
	public void setChatername(String chatername){
		this.chatername = chatername;
	}
	
	/**
	 * get 发送人国家id
	 * @return 
	 */
	public int getCountry(){
		return country;
	}
	
	/**
	 * set 发送人国家id
	 */
	public void setCountry(int country){
		this.country = country;
	}
	
	/**
	 * get 发送人王城等级
	 * @return 
	 */
	public int getChaterkinglv(){
		return chaterkinglv;
	}
	
	/**
	 * set 发送人王城等级
	 */
	public void setChaterkinglv(int chaterkinglv){
		this.chaterkinglv = chaterkinglv;
	}
	
	/**
	 * get 接收人名字
	 * @return 
	 */
	public String getReceivername(){
		return receivername;
	}
	
	/**
	 * set 接收人名字
	 */
	public void setReceivername(String receivername){
		this.receivername = receivername;
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
	 * get 是否禁言 1禁言 0未禁言
	 * @return 
	 */
	public byte getIsprohibit(){
		return isprohibit;
	}
	
	/**
	 * set 是否禁言 1禁言 0未禁言
	 */
	public void setIsprohibit(byte isprohibit){
		this.isprohibit = isprohibit;
	}
	
	/**
	 * get 附加属性
	 * @return 
	 */
	public List<GoodsInfoRes> getOther(){
		return other;
	}
	
	/**
	 * set 附加属性
	 */
	public void setOther(List<GoodsInfoRes> other){
		this.other = other;
	}
	
	/**
	 * get 是否GM1是 0否
	 * @return 
	 */
	public byte getIsgm(){
		return isgm;
	}
	
	/**
	 * set 是否GM1是 0否
	 */
	public void setIsgm(byte isgm){
		this.isgm = isgm;
	}
	
	/**
	 * get 发送人VIP类型
	 * @return 
	 */
	public short getSenderviptype(){
		return senderviptype;
	}
	
	/**
	 * set 发送人VIP类型
	 */
	public void setSenderviptype(short senderviptype){
		this.senderviptype = senderviptype;
	}
	
	/**
	 * get 接收人VIP类型
	 * @return 
	 */
	public short getReceiverviptype(){
		return receiverviptype;
	}
	
	/**
	 * set 接收人VIP类型
	 */
	public void setReceiverviptype(short receiverviptype){
		this.receiverviptype = receiverviptype;
	}
	
	/**
	 * get 发送人WEBVIP类型
	 * @return 
	 */
	public int getWebvip(){
		return webvip;
	}
	
	/**
	 * set 发送人WEBVIP类型
	 */
	public void setWebvip(int webvip){
		this.webvip = webvip;
	}
	
	/**
	 * get 接收人WEBVIP类型
	 * @return 
	 */
	public int getReceiverwebvip(){
		return receiverwebvip;
	}
	
	/**
	 * set 接收人WEBVIP类型
	 */
	public void setReceiverwebvip(int receiverwebvip){
		this.receiverwebvip = receiverwebvip;
	}
	
	
	@Override
	public int getId() {
		return 111301;
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
		//发送人id
		buf.append("chater:" + chater +",");
		//发送人名字
		if(this.chatername!=null) buf.append("chatername:" + chatername.toString() +",");
		//发送人国家id
		buf.append("country:" + country +",");
		//发送人王城等级
		buf.append("chaterkinglv:" + chaterkinglv +",");
		//接收人名字
		if(this.receivername!=null) buf.append("receivername:" + receivername.toString() +",");
		//聊天消息
		if(this.condition!=null) buf.append("condition:" + condition.toString() +",");
		//是否禁言 1禁言 0未禁言
		buf.append("isprohibit:" + isprohibit +",");
		//附加属性
		buf.append("other:{");
		for (int i = 0; i < other.size(); i++) {
			buf.append(other.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//是否GM1是 0否
		buf.append("isgm:" + isgm +",");
		//发送人VIP类型
		buf.append("senderviptype:" + senderviptype +",");
		//接收人VIP类型
		buf.append("receiverviptype:" + receiverviptype +",");
		//发送人WEBVIP类型
		buf.append("webvip:" + webvip +",");
		//接收人WEBVIP类型
		buf.append("receiverwebvip:" + receiverwebvip +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}