package com.game.spirittree.message;

import com.game.spirittree.bean.FruitInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送灵树单个果实消息消息
 */
public class ResGetSingleFruitInfoToClientMessage extends Message{

	//果实信息
	private FruitInfo fruitinfo;
	
	//类型：0刷新，1保底，2浇水
	private byte type;
	
	//浇水获得经验
	private int exp;
	
	//摘取数量
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//果实信息
		writeBean(buf, this.fruitinfo);
		//类型：0刷新，1保底，2浇水
		writeByte(buf, this.type);
		//浇水获得经验
		writeInt(buf, this.exp);
		//摘取数量
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//果实信息
		this.fruitinfo = (FruitInfo)readBean(buf, FruitInfo.class);
		//类型：0刷新，1保底，2浇水
		this.type = readByte(buf);
		//浇水获得经验
		this.exp = readInt(buf);
		//摘取数量
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 果实信息
	 * @return 
	 */
	public FruitInfo getFruitinfo(){
		return fruitinfo;
	}
	
	/**
	 * set 果实信息
	 */
	public void setFruitinfo(FruitInfo fruitinfo){
		this.fruitinfo = fruitinfo;
	}
	
	/**
	 * get 类型：0刷新，1保底，2浇水
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：0刷新，1保底，2浇水
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 浇水获得经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 浇水获得经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 摘取数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 摘取数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	
	@Override
	public int getId() {
		return 133102;
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
		//果实信息
		if(this.fruitinfo!=null) buf.append("fruitinfo:" + fruitinfo.toString() +",");
		//类型：0刷新，1保底，2浇水
		buf.append("type:" + type +",");
		//浇水获得经验
		buf.append("exp:" + exp +",");
		//摘取数量
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}