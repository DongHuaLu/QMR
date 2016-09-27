package com.game.dianjiangchun.message;

import com.game.dianjiangchun.bean.DianjiangchunInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 点绛唇信息发送到客户端消息
 */
public class SendDianjiangchunInfoToClientMessage extends Message{

	//点绛唇保存信息
	private DianjiangchunInfo stCurInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//点绛唇保存信息
		writeBean(buf, this.stCurInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//点绛唇保存信息
		this.stCurInfo = (DianjiangchunInfo)readBean(buf, DianjiangchunInfo.class);
		return true;
	}
	
	/**
	 * get 点绛唇保存信息
	 * @return 
	 */
	public DianjiangchunInfo getStCurInfo(){
		return stCurInfo;
	}
	
	/**
	 * set 点绛唇保存信息
	 */
	public void setStCurInfo(DianjiangchunInfo stCurInfo){
		this.stCurInfo = stCurInfo;
	}
	
	
	@Override
	public int getId() {
		return 116101;
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
		//点绛唇保存信息
		if(this.stCurInfo!=null) buf.append("stCurInfo:" + stCurInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}