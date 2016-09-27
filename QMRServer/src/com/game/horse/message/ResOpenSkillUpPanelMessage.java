package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打开技能升级面板消息消息
 */
public class ResOpenSkillUpPanelMessage extends Message{

	//已使用连珠和拉杆次数
	private byte boxnum;
	
	//拉杆需要的金币
	private int money;
	
	//连珠需要的元宝
	private int yuanbao;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//已使用连珠和拉杆次数
		writeByte(buf, this.boxnum);
		//拉杆需要的金币
		writeInt(buf, this.money);
		//连珠需要的元宝
		writeInt(buf, this.yuanbao);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//已使用连珠和拉杆次数
		this.boxnum = readByte(buf);
		//拉杆需要的金币
		this.money = readInt(buf);
		//连珠需要的元宝
		this.yuanbao = readInt(buf);
		return true;
	}
	
	/**
	 * get 已使用连珠和拉杆次数
	 * @return 
	 */
	public byte getBoxnum(){
		return boxnum;
	}
	
	/**
	 * set 已使用连珠和拉杆次数
	 */
	public void setBoxnum(byte boxnum){
		this.boxnum = boxnum;
	}
	
	/**
	 * get 拉杆需要的金币
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 拉杆需要的金币
	 */
	public void setMoney(int money){
		this.money = money;
	}
	
	/**
	 * get 连珠需要的元宝
	 * @return 
	 */
	public int getYuanbao(){
		return yuanbao;
	}
	
	/**
	 * set 连珠需要的元宝
	 */
	public void setYuanbao(int yuanbao){
		this.yuanbao = yuanbao;
	}
	
	
	@Override
	public int getId() {
		return 126113;
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
		//已使用连珠和拉杆次数
		buf.append("boxnum:" + boxnum +",");
		//拉杆需要的金币
		buf.append("money:" + money +",");
		//连珠需要的元宝
		buf.append("yuanbao:" + yuanbao +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}