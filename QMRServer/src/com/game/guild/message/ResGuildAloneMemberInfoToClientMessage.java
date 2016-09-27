package com.game.guild.message;

import com.game.guild.bean.MemberInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送单独成员信息给客户端消息
 */
public class ResGuildAloneMemberInfoToClientMessage extends Message{

	//通知类型 0 创建 1 添加或更新 2 删除 等
	private byte notifyType;
	
	//单独成员信息
	private MemberInfo memberInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知类型 0 创建 1 添加或更新 2 删除 等
		writeByte(buf, this.notifyType);
		//单独成员信息
		writeBean(buf, this.memberInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知类型 0 创建 1 添加或更新 2 删除 等
		this.notifyType = readByte(buf);
		//单独成员信息
		this.memberInfo = (MemberInfo)readBean(buf, MemberInfo.class);
		return true;
	}
	
	/**
	 * get 通知类型 0 创建 1 添加或更新 2 删除 等
	 * @return 
	 */
	public byte getNotifyType(){
		return notifyType;
	}
	
	/**
	 * set 通知类型 0 创建 1 添加或更新 2 删除 等
	 */
	public void setNotifyType(byte notifyType){
		this.notifyType = notifyType;
	}
	
	/**
	 * get 单独成员信息
	 * @return 
	 */
	public MemberInfo getMemberInfo(){
		return memberInfo;
	}
	
	/**
	 * set 单独成员信息
	 */
	public void setMemberInfo(MemberInfo memberInfo){
		this.memberInfo = memberInfo;
	}
	
	
	@Override
	public int getId() {
		return 121391;
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
		//通知类型 0 创建 1 添加或更新 2 删除 等
		buf.append("notifyType:" + notifyType +",");
		//单独成员信息
		if(this.memberInfo!=null) buf.append("memberInfo:" + memberInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}