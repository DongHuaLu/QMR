package com.game.marriage.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 需要在婚宴改变外观的玩家ID消息
 */
public class ResWeddingExteriorToClientMessage extends Message{

	//需要改变外观的角色列表
	private List<Long> roleslist = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//需要改变外观的角色列表
		writeShort(buf, roleslist.size());
		for (int i = 0; i < roleslist.size(); i++) {
			writeLong(buf, roleslist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//需要改变外观的角色列表
		int roleslist_length = readShort(buf);
		for (int i = 0; i < roleslist_length; i++) {
			roleslist.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 需要改变外观的角色列表
	 * @return 
	 */
	public List<Long> getRoleslist(){
		return roleslist;
	}
	
	/**
	 * set 需要改变外观的角色列表
	 */
	public void setRoleslist(List<Long> roleslist){
		this.roleslist = roleslist;
	}
	
	
	@Override
	public int getId() {
		return 163119;
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
		//需要改变外观的角色列表
		buf.append("roleslist:{");
		for (int i = 0; i < roleslist.size(); i++) {
			buf.append(roleslist.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}