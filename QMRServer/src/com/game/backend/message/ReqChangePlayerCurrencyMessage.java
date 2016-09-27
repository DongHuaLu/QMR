package com.game.backend.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 向游戏服务器请求修改玩家的现金(铜币或者绑定元宝)消息
 */
public class ReqChangePlayerCurrencyMessage extends Message{

	//角色Id
	private long personId;
	
	//要修改的货币类型(1-铜币 2-绑定元宝)
	private byte type;
	
	//要修改的数量(可正负)
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//要修改的货币类型(1-铜币 2-绑定元宝)
		writeByte(buf, this.type);
		//要修改的数量(可正负)
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//要修改的货币类型(1-铜币 2-绑定元宝)
		this.type = readByte(buf);
		//要修改的数量(可正负)
		this.num = readInt(buf);
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
	 * get 要修改的货币类型(1-铜币 2-绑定元宝)
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 要修改的货币类型(1-铜币 2-绑定元宝)
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 要修改的数量(可正负)
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 要修改的数量(可正负)
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	
	@Override
	public int getId() {
		return 135306;
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
		//要修改的货币类型(1-铜币 2-绑定元宝)
		buf.append("type:" + type +",");
		//要修改的数量(可正负)
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}