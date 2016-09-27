package com.game.dazuo.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 以两个玩家为中心点进行 打座状态变化  广播消息
 */
public class ResDazuoStateBroadCastMessage extends Message{

	//0未打座  1单人 1与宠物双修 2与玩家双修
	private byte state;
	
	//双修玩家A
	private long roleAId;
	
	//双修玩家AX
	private short roleAX;
	
	//双修玩家AY
	private short roleAY;
	
	//玩家A出战的宠物列表
	private List<Long> roleAPets = new ArrayList<Long>();
	//双修玩家B 如果不是与玩家双修 则只发A
	private long roleBId;
	
	//双修玩家BX
	private short roleBX;
	
	//双修玩家BY
	private short roleBY;
	
	//玩家B出战的宠物列表
	private List<Long> roleBPets = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0未打座  1单人 1与宠物双修 2与玩家双修
		writeByte(buf, this.state);
		//双修玩家A
		writeLong(buf, this.roleAId);
		//双修玩家AX
		writeShort(buf, this.roleAX);
		//双修玩家AY
		writeShort(buf, this.roleAY);
		//玩家A出战的宠物列表
		writeShort(buf, roleAPets.size());
		for (int i = 0; i < roleAPets.size(); i++) {
			writeLong(buf, roleAPets.get(i));
		}
		//双修玩家B 如果不是与玩家双修 则只发A
		writeLong(buf, this.roleBId);
		//双修玩家BX
		writeShort(buf, this.roleBX);
		//双修玩家BY
		writeShort(buf, this.roleBY);
		//玩家B出战的宠物列表
		writeShort(buf, roleBPets.size());
		for (int i = 0; i < roleBPets.size(); i++) {
			writeLong(buf, roleBPets.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0未打座  1单人 1与宠物双修 2与玩家双修
		this.state = readByte(buf);
		//双修玩家A
		this.roleAId = readLong(buf);
		//双修玩家AX
		this.roleAX = readShort(buf);
		//双修玩家AY
		this.roleAY = readShort(buf);
		//玩家A出战的宠物列表
		int roleAPets_length = readShort(buf);
		for (int i = 0; i < roleAPets_length; i++) {
			roleAPets.add(readLong(buf));
		}
		//双修玩家B 如果不是与玩家双修 则只发A
		this.roleBId = readLong(buf);
		//双修玩家BX
		this.roleBX = readShort(buf);
		//双修玩家BY
		this.roleBY = readShort(buf);
		//玩家B出战的宠物列表
		int roleBPets_length = readShort(buf);
		for (int i = 0; i < roleBPets_length; i++) {
			roleBPets.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 0未打座  1单人 1与宠物双修 2与玩家双修
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 0未打座  1单人 1与宠物双修 2与玩家双修
	 */
	public void setState(byte state){
		this.state = state;
	}
	
	/**
	 * get 双修玩家A
	 * @return 
	 */
	public long getRoleAId(){
		return roleAId;
	}
	
	/**
	 * set 双修玩家A
	 */
	public void setRoleAId(long roleAId){
		this.roleAId = roleAId;
	}
	
	/**
	 * get 双修玩家AX
	 * @return 
	 */
	public short getRoleAX(){
		return roleAX;
	}
	
	/**
	 * set 双修玩家AX
	 */
	public void setRoleAX(short roleAX){
		this.roleAX = roleAX;
	}
	
	/**
	 * get 双修玩家AY
	 * @return 
	 */
	public short getRoleAY(){
		return roleAY;
	}
	
	/**
	 * set 双修玩家AY
	 */
	public void setRoleAY(short roleAY){
		this.roleAY = roleAY;
	}
	
	/**
	 * get 玩家A出战的宠物列表
	 * @return 
	 */
	public List<Long> getRoleAPets(){
		return roleAPets;
	}
	
	/**
	 * set 玩家A出战的宠物列表
	 */
	public void setRoleAPets(List<Long> roleAPets){
		this.roleAPets = roleAPets;
	}
	
	/**
	 * get 双修玩家B 如果不是与玩家双修 则只发A
	 * @return 
	 */
	public long getRoleBId(){
		return roleBId;
	}
	
	/**
	 * set 双修玩家B 如果不是与玩家双修 则只发A
	 */
	public void setRoleBId(long roleBId){
		this.roleBId = roleBId;
	}
	
	/**
	 * get 双修玩家BX
	 * @return 
	 */
	public short getRoleBX(){
		return roleBX;
	}
	
	/**
	 * set 双修玩家BX
	 */
	public void setRoleBX(short roleBX){
		this.roleBX = roleBX;
	}
	
	/**
	 * get 双修玩家BY
	 * @return 
	 */
	public short getRoleBY(){
		return roleBY;
	}
	
	/**
	 * set 双修玩家BY
	 */
	public void setRoleBY(short roleBY){
		this.roleBY = roleBY;
	}
	
	/**
	 * get 玩家B出战的宠物列表
	 * @return 
	 */
	public List<Long> getRoleBPets(){
		return roleBPets;
	}
	
	/**
	 * set 玩家B出战的宠物列表
	 */
	public void setRoleBPets(List<Long> roleBPets){
		this.roleBPets = roleBPets;
	}
	
	
	@Override
	public int getId() {
		return 136102;
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
		//0未打座  1单人 1与宠物双修 2与玩家双修
		buf.append("state:" + state +",");
		//双修玩家A
		buf.append("roleAId:" + roleAId +",");
		//双修玩家AX
		buf.append("roleAX:" + roleAX +",");
		//双修玩家AY
		buf.append("roleAY:" + roleAY +",");
		//玩家A出战的宠物列表
		buf.append("roleAPets:{");
		for (int i = 0; i < roleAPets.size(); i++) {
			buf.append(roleAPets.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//双修玩家B 如果不是与玩家双修 则只发A
		buf.append("roleBId:" + roleBId +",");
		//双修玩家BX
		buf.append("roleBX:" + roleBX +",");
		//双修玩家BY
		buf.append("roleBY:" + roleBY +",");
		//玩家B出战的宠物列表
		buf.append("roleBPets:{");
		for (int i = 0; i < roleBPets.size(); i++) {
			buf.append(roleBPets.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}