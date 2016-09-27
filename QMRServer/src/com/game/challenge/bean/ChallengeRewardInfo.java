package com.game.challenge.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 挑战奖励信息
 */
public class ChallengeRewardInfo extends Bean {

	//奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
	private int id;
	
	//奖励数量
	private int num;
	
	//奖励类型，0通关或者优胜，1参与奖
	private int type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
		writeInt(buf, this.id);
		//奖励数量
		writeInt(buf, this.num);
		//奖励类型，0通关或者优胜，1参与奖
		writeInt(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
		this.id = readInt(buf);
		//奖励数量
		this.num = readInt(buf);
		//奖励类型，0通关或者优胜，1参与奖
		this.type = readInt(buf);
		return true;
	}
	
	/**
	 * get 奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
	 * @return 
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * set 奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * get 奖励数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 奖励数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 奖励类型，0通关或者优胜，1参与奖
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 奖励类型，0通关或者优胜，1参与奖
	 */
	public void setType(int type){
		this.type = type;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
		buf.append("id:" + id +",");
		//奖励数量
		buf.append("num:" + num +",");
		//奖励类型，0通关或者优胜，1参与奖
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}