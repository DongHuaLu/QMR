package com.game.team.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 前端请求队员名字列表消息
 */
public class ResMapSearchMemberNameClientMessage extends Message{

	//队员名字列表
	private List<String> membernamelist = new ArrayList<String>();
	//队员等级列表
	private List<Short> membernamelv = new ArrayList<Short>();
	//队员线路列表
	private List<Byte> membernameline = new ArrayList<Byte>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队员名字列表
		writeShort(buf, membernamelist.size());
		for (int i = 0; i < membernamelist.size(); i++) {
			writeString(buf, membernamelist.get(i));
		}
		//队员等级列表
		writeShort(buf, membernamelv.size());
		for (int i = 0; i < membernamelv.size(); i++) {
			writeShort(buf, membernamelv.get(i));
		}
		//队员线路列表
		writeShort(buf, membernameline.size());
		for (int i = 0; i < membernameline.size(); i++) {
			writeByte(buf, membernameline.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队员名字列表
		int membernamelist_length = readShort(buf);
		for (int i = 0; i < membernamelist_length; i++) {
			membernamelist.add(readString(buf));
		}
		//队员等级列表
		int membernamelv_length = readShort(buf);
		for (int i = 0; i < membernamelv_length; i++) {
			membernamelv.add(readShort(buf));
		}
		//队员线路列表
		int membernameline_length = readShort(buf);
		for (int i = 0; i < membernameline_length; i++) {
			membernameline.add(readByte(buf));
		}
		return true;
	}
	
	/**
	 * get 队员名字列表
	 * @return 
	 */
	public List<String> getMembernamelist(){
		return membernamelist;
	}
	
	/**
	 * set 队员名字列表
	 */
	public void setMembernamelist(List<String> membernamelist){
		this.membernamelist = membernamelist;
	}
	
	/**
	 * get 队员等级列表
	 * @return 
	 */
	public List<Short> getMembernamelv(){
		return membernamelv;
	}
	
	/**
	 * set 队员等级列表
	 */
	public void setMembernamelv(List<Short> membernamelv){
		this.membernamelv = membernamelv;
	}
	
	/**
	 * get 队员线路列表
	 * @return 
	 */
	public List<Byte> getMembernameline(){
		return membernameline;
	}
	
	/**
	 * set 队员线路列表
	 */
	public void setMembernameline(List<Byte> membernameline){
		this.membernameline = membernameline;
	}
	
	
	@Override
	public int getId() {
		return 118110;
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
		//队员名字列表
		buf.append("membernamelist:{");
		for (int i = 0; i < membernamelist.size(); i++) {
			buf.append(membernamelist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//队员等级列表
		buf.append("membernamelv:{");
		for (int i = 0; i < membernamelv.size(); i++) {
			buf.append(membernamelv.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//队员线路列表
		buf.append("membernameline:{");
		for (int i = 0; i < membernameline.size(); i++) {
			buf.append(membernameline.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}