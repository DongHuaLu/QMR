package com.game.message;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 消息接口
 */
public abstract class Message extends Bean {

	private long sendId;
	//发送消息玩家
	private List<Long> roleId = new ArrayList<Long>();
	//接收消息IoSession
	private IoSession session;
//	//发送消息的玩家userid
//	private long userId;
	/**
	 * 获取消息Id
	 */
	public abstract int getId();
	
	/**
	 * 获取消息处理队列
	 * @return
	 */
	public abstract String getQueue();
	
	/**
	 * 获取消息处理服务器
	 * @return
	 */
	public abstract String getServer();

	public long getSendId() {
		return sendId;
	}

	public void setSendId(long sendId) {
		this.sendId = sendId;
	}

	public List<Long> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

//	public long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
//	
}
