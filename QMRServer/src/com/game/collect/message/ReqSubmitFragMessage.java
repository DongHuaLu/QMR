package com.game.collect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 提交碎片根据物品消息
 */
public class ReqSubmitFragMessage extends Message{

	//碎片
	private int itemmodel;
	
	//数量
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//碎片
		writeInt(buf, this.itemmodel);
		//数量
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//碎片
		this.itemmodel = readInt(buf);
		//数量
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 碎片
	 * @return 
	 */
	public int getItemmodel(){
		return itemmodel;
	}
	
	/**
	 * set 碎片
	 */
	public void setItemmodel(int itemmodel){
		this.itemmodel = itemmodel;
	}
	
	/**
	 * get 数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	
	@Override
	public int getId() {
		return 153202;
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
		//碎片
		buf.append("itemmodel:" + itemmodel +",");
		//数量
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}