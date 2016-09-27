package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送升级技能CD时间消息
 */
public class ReshorseCDTimeMessage extends Message{

	//拉杆CD时间
	private int num;
	
	//清除CD需要的元宝数量
	private int cdtimeyuanbao;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//拉杆CD时间
		writeInt(buf, this.num);
		//清除CD需要的元宝数量
		writeInt(buf, this.cdtimeyuanbao);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//拉杆CD时间
		this.num = readInt(buf);
		//清除CD需要的元宝数量
		this.cdtimeyuanbao = readInt(buf);
		return true;
	}
	
	/**
	 * get 拉杆CD时间
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 拉杆CD时间
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 清除CD需要的元宝数量
	 * @return 
	 */
	public int getCdtimeyuanbao(){
		return cdtimeyuanbao;
	}
	
	/**
	 * set 清除CD需要的元宝数量
	 */
	public void setCdtimeyuanbao(int cdtimeyuanbao){
		this.cdtimeyuanbao = cdtimeyuanbao;
	}
	
	
	@Override
	public int getId() {
		return 126111;
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
		//拉杆CD时间
		buf.append("num:" + num +",");
		//清除CD需要的元宝数量
		buf.append("cdtimeyuanbao:" + cdtimeyuanbao +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}