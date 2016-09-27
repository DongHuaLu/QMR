package com.game.toplist.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知游戏服务器获取排行榜膜拜奖励消息
 */
public class ResGetTopAwardToServerMessage extends Message{

	//副本类型
	private int zonetype;
	
	//副本id
	private int zoneid;
	
	//经验
	private int exp;
	
	//铜币
	private int money;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//副本类型
		writeInt(buf, this.zonetype);
		//副本id
		writeInt(buf, this.zoneid);
		//经验
		writeInt(buf, this.exp);
		//铜币
		writeInt(buf, this.money);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//副本类型
		this.zonetype = readInt(buf);
		//副本id
		this.zoneid = readInt(buf);
		//经验
		this.exp = readInt(buf);
		//铜币
		this.money = readInt(buf);
		return true;
	}
	
	/**
	 * get 副本类型
	 * @return 
	 */
	public int getZonetype(){
		return zonetype;
	}
	
	/**
	 * set 副本类型
	 */
	public void setZonetype(int zonetype){
		this.zonetype = zonetype;
	}
	
	/**
	 * get 副本id
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本id
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	/**
	 * get 经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 铜币
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 铜币
	 */
	public void setMoney(int money){
		this.money = money;
	}
	
	
	@Override
	public int getId() {
		return 142351;
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
		//副本类型
		buf.append("zonetype:" + zonetype +",");
		//副本id
		buf.append("zoneid:" + zoneid +",");
		//经验
		buf.append("exp:" + exp +",");
		//铜币
		buf.append("money:" + money +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}