package com.game.challenge.message;

import com.game.challenge.bean.ChallengeInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送挑战面板消息消息
 */
public class ResOpenChallengeToClientMessage extends Message{

	//挑战信息
	private ChallengeInfo challengeInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//挑战信息
		writeBean(buf, this.challengeInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//挑战信息
		this.challengeInfo = (ChallengeInfo)readBean(buf, ChallengeInfo.class);
		return true;
	}
	
	/**
	 * get 挑战信息
	 * @return 
	 */
	public ChallengeInfo getChallengeInfo(){
		return challengeInfo;
	}
	
	/**
	 * set 挑战信息
	 */
	public void setChallengeInfo(ChallengeInfo challengeInfo){
		this.challengeInfo = challengeInfo;
	}
	
	
	@Override
	public int getId() {
		return 144101;
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
		//挑战信息
		if(this.challengeInfo!=null) buf.append("challengeInfo:" + challengeInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}