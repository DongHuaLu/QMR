package com.game.realm.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回前端错误内容消息
 */
public class ResRealmErrToClientMessage extends Message{

	//1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
	private int type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
		writeInt(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
		this.type = readInt(buf);
		return true;
	}
	
	/**
	 * get 1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
	 */
	public void setType(int type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 158104;
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
		//1道具不足，2铜币不足，3真气不足，4强化已满，5元宝不足
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}