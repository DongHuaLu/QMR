package com.game.dazuo.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打座收益消息
 */
public class ResDazuoEruptMessage extends Message{

	//打座持续时间
	private int duration;
	
	//打坐获得经验
	private int dazuoexp;
	
	//打坐获得真气
	private int dazuozq;
	
	//暴击次数
	private int eruptCount;
	
	//暴击获得经验
	private int eruptExp;
	
	//暴击获得真气
	private int eruptZQ;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//打座持续时间
		writeInt(buf, this.duration);
		//打坐获得经验
		writeInt(buf, this.dazuoexp);
		//打坐获得真气
		writeInt(buf, this.dazuozq);
		//暴击次数
		writeInt(buf, this.eruptCount);
		//暴击获得经验
		writeInt(buf, this.eruptExp);
		//暴击获得真气
		writeInt(buf, this.eruptZQ);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//打座持续时间
		this.duration = readInt(buf);
		//打坐获得经验
		this.dazuoexp = readInt(buf);
		//打坐获得真气
		this.dazuozq = readInt(buf);
		//暴击次数
		this.eruptCount = readInt(buf);
		//暴击获得经验
		this.eruptExp = readInt(buf);
		//暴击获得真气
		this.eruptZQ = readInt(buf);
		return true;
	}
	
	/**
	 * get 打座持续时间
	 * @return 
	 */
	public int getDuration(){
		return duration;
	}
	
	/**
	 * set 打座持续时间
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	/**
	 * get 打坐获得经验
	 * @return 
	 */
	public int getDazuoexp(){
		return dazuoexp;
	}
	
	/**
	 * set 打坐获得经验
	 */
	public void setDazuoexp(int dazuoexp){
		this.dazuoexp = dazuoexp;
	}
	
	/**
	 * get 打坐获得真气
	 * @return 
	 */
	public int getDazuozq(){
		return dazuozq;
	}
	
	/**
	 * set 打坐获得真气
	 */
	public void setDazuozq(int dazuozq){
		this.dazuozq = dazuozq;
	}
	
	/**
	 * get 暴击次数
	 * @return 
	 */
	public int getEruptCount(){
		return eruptCount;
	}
	
	/**
	 * set 暴击次数
	 */
	public void setEruptCount(int eruptCount){
		this.eruptCount = eruptCount;
	}
	
	/**
	 * get 暴击获得经验
	 * @return 
	 */
	public int getEruptExp(){
		return eruptExp;
	}
	
	/**
	 * set 暴击获得经验
	 */
	public void setEruptExp(int eruptExp){
		this.eruptExp = eruptExp;
	}
	
	/**
	 * get 暴击获得真气
	 * @return 
	 */
	public int getEruptZQ(){
		return eruptZQ;
	}
	
	/**
	 * set 暴击获得真气
	 */
	public void setEruptZQ(int eruptZQ){
		this.eruptZQ = eruptZQ;
	}
	
	
	@Override
	public int getId() {
		return 136103;
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
		//打座持续时间
		buf.append("duration:" + duration +",");
		//打坐获得经验
		buf.append("dazuoexp:" + dazuoexp +",");
		//打坐获得真气
		buf.append("dazuozq:" + dazuozq +",");
		//暴击次数
		buf.append("eruptCount:" + eruptCount +",");
		//暴击获得经验
		buf.append("eruptExp:" + eruptExp +",");
		//暴击获得真气
		buf.append("eruptZQ:" + eruptZQ +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}