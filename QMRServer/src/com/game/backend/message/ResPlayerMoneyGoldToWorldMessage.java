package com.game.backend.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 向世界服返回玩家实时铜币元宝信息消息
 */
public class ResPlayerMoneyGoldToWorldMessage extends Message{

	//角色Id
	private long personId;
	
	//铜币
	private int money;
	
	//元宝
	private int gold;
	
	//绑定元宝
	private int bindgold;
	
	//临时元宝
	private int tmpgold;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//铜币
		writeInt(buf, this.money);
		//元宝
		writeInt(buf, this.gold);
		//绑定元宝
		writeInt(buf, this.bindgold);
		//临时元宝
		writeInt(buf, this.tmpgold);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//铜币
		this.money = readInt(buf);
		//元宝
		this.gold = readInt(buf);
		//绑定元宝
		this.bindgold = readInt(buf);
		//临时元宝
		this.tmpgold = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPersonId(){
		return personId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPersonId(long personId){
		this.personId = personId;
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
	
	/**
	 * get 元宝
	 * @return 
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * set 元宝
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	/**
	 * get 绑定元宝
	 * @return 
	 */
	public int getBindgold(){
		return bindgold;
	}
	
	/**
	 * set 绑定元宝
	 */
	public void setBindgold(int bindgold){
		this.bindgold = bindgold;
	}
	
	/**
	 * get 临时元宝
	 * @return 
	 */
	public int getTmpgold(){
		return tmpgold;
	}
	
	/**
	 * set 临时元宝
	 */
	public void setTmpgold(int tmpgold){
		this.tmpgold = tmpgold;
	}
	
	
	@Override
	public int getId() {
		return 135303;
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
		//角色Id
		buf.append("personId:" + personId +",");
		//铜币
		buf.append("money:" + money +",");
		//元宝
		buf.append("gold:" + gold +",");
		//绑定元宝
		buf.append("bindgold:" + bindgold +",");
		//临时元宝
		buf.append("tmpgold:" + tmpgold +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}