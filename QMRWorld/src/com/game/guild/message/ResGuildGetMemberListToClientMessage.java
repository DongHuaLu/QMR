package com.game.guild.message;

import com.game.guild.bean.MemberInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获取成员列表返回消息
 */
public class ResGuildGetMemberListToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//所有成员列表
	private List<MemberInfo> memberList = new ArrayList<MemberInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//所有成员列表
		writeShort(buf, memberList.size());
		for (int i = 0; i < memberList.size(); i++) {
			writeBean(buf, memberList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//所有成员列表
		int memberList_length = readShort(buf);
		for (int i = 0; i < memberList_length; i++) {
			memberList.add((MemberInfo)readBean(buf, MemberInfo.class));
		}
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getBtErrorCode(){
		return btErrorCode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setBtErrorCode(byte btErrorCode){
		this.btErrorCode = btErrorCode;
	}
	
	/**
	 * get 所有成员列表
	 * @return 
	 */
	public List<MemberInfo> getMemberList(){
		return memberList;
	}
	
	/**
	 * set 所有成员列表
	 */
	public void setMemberList(List<MemberInfo> memberList){
		this.memberList = memberList;
	}
	
	
	@Override
	public int getId() {
		return 121106;
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
		//错误代码
		buf.append("btErrorCode:" + btErrorCode +",");
		//所有成员列表
		buf.append("memberList:{");
		for (int i = 0; i < memberList.size(); i++) {
			buf.append(memberList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}