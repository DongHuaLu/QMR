package com.game.hiddenweapon.message;

import com.game.hiddenweapon.bean.HiddenWeaponSkillIco;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 暗器技能Ico消息
 */
public class ResHiddenWeaponSkillIcoMessage extends Message{

	//暗器技能ico
	private HiddenWeaponSkillIco ico;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//暗器技能ico
		writeBean(buf, this.ico);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//暗器技能ico
		this.ico = (HiddenWeaponSkillIco)readBean(buf, HiddenWeaponSkillIco.class);
		return true;
	}
	
	/**
	 * get 暗器技能ico
	 * @return 
	 */
	public HiddenWeaponSkillIco getIco(){
		return ico;
	}
	
	/**
	 * set 暗器技能ico
	 */
	public void setIco(HiddenWeaponSkillIco ico){
		this.ico = ico;
	}
	
	
	@Override
	public int getId() {
		return 162108;
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
		//暗器技能ico
		if(this.ico!=null) buf.append("ico:" + ico.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}