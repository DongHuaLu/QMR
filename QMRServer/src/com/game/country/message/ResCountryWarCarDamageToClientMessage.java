package com.game.country.message;

import com.game.country.bean.PlayerDamageInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 炮弹击中人物信息消息
 */
public class ResCountryWarCarDamageToClientMessage extends Message{

	//攻击坐标X
	private short x;
	
	//攻击坐标Y
	private short y;
	
	//地图人物信息
	private List<PlayerDamageInfo> damageinfo = new ArrayList<PlayerDamageInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//攻击坐标X
		writeShort(buf, this.x);
		//攻击坐标Y
		writeShort(buf, this.y);
		//地图人物信息
		writeShort(buf, damageinfo.size());
		for (int i = 0; i < damageinfo.size(); i++) {
			writeBean(buf, damageinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//攻击坐标X
		this.x = readShort(buf);
		//攻击坐标Y
		this.y = readShort(buf);
		//地图人物信息
		int damageinfo_length = readShort(buf);
		for (int i = 0; i < damageinfo_length; i++) {
			damageinfo.add((PlayerDamageInfo)readBean(buf, PlayerDamageInfo.class));
		}
		return true;
	}
	
	/**
	 * get 攻击坐标X
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 攻击坐标X
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 攻击坐标Y
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 攻击坐标Y
	 */
	public void setY(short y){
		this.y = y;
	}
	
	/**
	 * get 地图人物信息
	 * @return 
	 */
	public List<PlayerDamageInfo> getDamageinfo(){
		return damageinfo;
	}
	
	/**
	 * set 地图人物信息
	 */
	public void setDamageinfo(List<PlayerDamageInfo> damageinfo){
		this.damageinfo = damageinfo;
	}
	
	
	@Override
	public int getId() {
		return 146112;
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
		//攻击坐标X
		buf.append("x:" + x +",");
		//攻击坐标Y
		buf.append("y:" + y +",");
		//地图人物信息
		buf.append("damageinfo:{");
		for (int i = 0; i < damageinfo.size(); i++) {
			buf.append(damageinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}