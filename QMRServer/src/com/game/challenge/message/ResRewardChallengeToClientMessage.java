package com.game.challenge.message;

import com.game.challenge.bean.ChallengeRewardInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送挑战面板奖励消息消息
 */
public class ResRewardChallengeToClientMessage extends Message{

	//挑战信息
	private List<ChallengeRewardInfo> rewardInfo = new ArrayList<ChallengeRewardInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//挑战信息
		writeShort(buf, rewardInfo.size());
		for (int i = 0; i < rewardInfo.size(); i++) {
			writeBean(buf, rewardInfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//挑战信息
		int rewardInfo_length = readShort(buf);
		for (int i = 0; i < rewardInfo_length; i++) {
			rewardInfo.add((ChallengeRewardInfo)readBean(buf, ChallengeRewardInfo.class));
		}
		return true;
	}
	
	/**
	 * get 挑战信息
	 * @return 
	 */
	public List<ChallengeRewardInfo> getRewardInfo(){
		return rewardInfo;
	}
	
	/**
	 * set 挑战信息
	 */
	public void setRewardInfo(List<ChallengeRewardInfo> rewardInfo){
		this.rewardInfo = rewardInfo;
	}
	
	
	@Override
	public int getId() {
		return 144102;
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
		buf.append("rewardInfo:{");
		for (int i = 0; i < rewardInfo.size(); i++) {
			buf.append(rewardInfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}