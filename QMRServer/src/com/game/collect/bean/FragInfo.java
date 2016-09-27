package com.game.collect.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 碎片信息
 */
public class FragInfo extends Bean {

	//物品模型
	private int modelid;
	
	//当前数量
	private int num;
	
	//所需数量
	private int neednum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品模型
		writeInt(buf, this.modelid);
		//当前数量
		writeInt(buf, this.num);
		//所需数量
		writeInt(buf, this.neednum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品模型
		this.modelid = readInt(buf);
		//当前数量
		this.num = readInt(buf);
		//所需数量
		this.neednum = readInt(buf);
		return true;
	}
	
	/**
	 * get 物品模型
	 * @return 
	 */
	public int getModelid(){
		return modelid;
	}
	
	/**
	 * set 物品模型
	 */
	public void setModelid(int modelid){
		this.modelid = modelid;
	}
	
	/**
	 * get 当前数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 当前数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 所需数量
	 * @return 
	 */
	public int getNeednum(){
		return neednum;
	}
	
	/**
	 * set 所需数量
	 */
	public void setNeednum(int neednum){
		this.neednum = neednum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//物品模型
		buf.append("modelid:" + modelid +",");
		//当前数量
		buf.append("num:" + num +",");
		//所需数量
		buf.append("neednum:" + neednum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}