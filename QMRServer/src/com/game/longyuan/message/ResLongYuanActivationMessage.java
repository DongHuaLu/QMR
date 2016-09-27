package com.game.longyuan.message;

import com.game.longyuan.bean.LongYuanInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 激活星位消息
 */
public class ResLongYuanActivationMessage extends Message{

	//真气值
	private int zhenqi;
	
	//龙元心法信息
	private LongYuanInfo longyuaninfo;
	
	//是否成功,0成功，1失败
	private byte status;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//真气值
		writeInt(buf, this.zhenqi);
		//龙元心法信息
		writeBean(buf, this.longyuaninfo);
		//是否成功,0成功，1失败
		writeByte(buf, this.status);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//真气值
		this.zhenqi = readInt(buf);
		//龙元心法信息
		this.longyuaninfo = (LongYuanInfo)readBean(buf, LongYuanInfo.class);
		//是否成功,0成功，1失败
		this.status = readByte(buf);
		return true;
	}
	
	/**
	 * get 真气值
	 * @return 
	 */
	public int getZhenqi(){
		return zhenqi;
	}
	
	/**
	 * set 真气值
	 */
	public void setZhenqi(int zhenqi){
		this.zhenqi = zhenqi;
	}
	
	/**
	 * get 龙元心法信息
	 * @return 
	 */
	public LongYuanInfo getLongyuaninfo(){
		return longyuaninfo;
	}
	
	/**
	 * set 龙元心法信息
	 */
	public void setLongyuaninfo(LongYuanInfo longyuaninfo){
		this.longyuaninfo = longyuaninfo;
	}
	
	/**
	 * get 是否成功,0成功，1失败
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 是否成功,0成功，1失败
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	
	@Override
	public int getId() {
		return 115102;
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
		//真气值
		buf.append("zhenqi:" + zhenqi +",");
		//龙元心法信息
		if(this.longyuaninfo!=null) buf.append("longyuaninfo:" + longyuaninfo.toString() +",");
		//是否成功,0成功，1失败
		buf.append("status:" + status +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}