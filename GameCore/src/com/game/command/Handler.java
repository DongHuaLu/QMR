package com.game.command;

import com.game.message.Message;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-2-18
 * 
 * 客户端消息处理抽象类
 */
public abstract class Handler implements ICommand {
	//待处理消息
	private Message message;
	//参数
	private Object parameter;
	//创建时间
	private long createTime;
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
}
