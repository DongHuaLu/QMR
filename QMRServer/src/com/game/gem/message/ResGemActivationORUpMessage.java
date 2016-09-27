package com.game.gem.message;

import com.game.gem.bean.GemInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宝石激活或者升级结果消息
 */
public class ResGemActivationORUpMessage extends Message{

	//装备部位
	private byte pos;
	
	//升级的宝石信息
	private GemInfo geminfo;
	
	//结果，0失败，1成功
	private byte result;
	
	//类型：0激活，1升级
	private byte type;
	
	//获得的经验(升级才有用)
	private int exp;
	
	//经验类型(升级才有用)：0普通，1小暴击，2大暴击
	private byte exptype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备部位
		writeByte(buf, this.pos);
		//升级的宝石信息
		writeBean(buf, this.geminfo);
		//结果，0失败，1成功
		writeByte(buf, this.result);
		//类型：0激活，1升级
		writeByte(buf, this.type);
		//获得的经验(升级才有用)
		writeInt(buf, this.exp);
		//经验类型(升级才有用)：0普通，1小暴击，2大暴击
		writeByte(buf, this.exptype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//装备部位
		this.pos = readByte(buf);
		//升级的宝石信息
		this.geminfo = (GemInfo)readBean(buf, GemInfo.class);
		//结果，0失败，1成功
		this.result = readByte(buf);
		//类型：0激活，1升级
		this.type = readByte(buf);
		//获得的经验(升级才有用)
		this.exp = readInt(buf);
		//经验类型(升级才有用)：0普通，1小暴击，2大暴击
		this.exptype = readByte(buf);
		return true;
	}
	
	/**
	 * get 装备部位
	 * @return 
	 */
	public byte getPos(){
		return pos;
	}
	
	/**
	 * set 装备部位
	 */
	public void setPos(byte pos){
		this.pos = pos;
	}
	
	/**
	 * get 升级的宝石信息
	 * @return 
	 */
	public GemInfo getGeminfo(){
		return geminfo;
	}
	
	/**
	 * set 升级的宝石信息
	 */
	public void setGeminfo(GemInfo geminfo){
		this.geminfo = geminfo;
	}
	
	/**
	 * get 结果，0失败，1成功
	 * @return 
	 */
	public byte getResult(){
		return result;
	}
	
	/**
	 * set 结果，0失败，1成功
	 */
	public void setResult(byte result){
		this.result = result;
	}
	
	/**
	 * get 类型：0激活，1升级
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：0激活，1升级
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 获得的经验(升级才有用)
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 获得的经验(升级才有用)
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 经验类型(升级才有用)：0普通，1小暴击，2大暴击
	 * @return 
	 */
	public byte getExptype(){
		return exptype;
	}
	
	/**
	 * set 经验类型(升级才有用)：0普通，1小暴击，2大暴击
	 */
	public void setExptype(byte exptype){
		this.exptype = exptype;
	}
	
	
	@Override
	public int getId() {
		return 132103;
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
		//装备部位
		buf.append("pos:" + pos +",");
		//升级的宝石信息
		if(this.geminfo!=null) buf.append("geminfo:" + geminfo.toString() +",");
		//结果，0失败，1成功
		buf.append("result:" + result +",");
		//类型：0激活，1升级
		buf.append("type:" + type +",");
		//获得的经验(升级才有用)
		buf.append("exp:" + exp +",");
		//经验类型(升级才有用)：0普通，1小暴击，2大暴击
		buf.append("exptype:" + exptype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}