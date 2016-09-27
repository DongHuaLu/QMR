package com.game.login.message;

import com.game.login.bean.CharacterInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 角色信息列表消息
 */
public class ResCharacterInfosMessage extends Message{

	//角色信息列表
	private List<CharacterInfo> characters = new ArrayList<CharacterInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色信息列表
		writeShort(buf, characters.size());
		for (int i = 0; i < characters.size(); i++) {
			writeBean(buf, characters.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色信息列表
		int characters_length = readShort(buf);
		for (int i = 0; i < characters_length; i++) {
			characters.add((CharacterInfo)readBean(buf, CharacterInfo.class));
		}
		return true;
	}
	
	/**
	 * get 角色信息列表
	 * @return 
	 */
	public List<CharacterInfo> getCharacters(){
		return characters;
	}
	
	/**
	 * set 角色信息列表
	 */
	public void setCharacters(List<CharacterInfo> characters){
		this.characters = characters;
	}
	
	
	@Override
	public int getId() {
		return 100101;
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
		//角色信息列表
		buf.append("characters:{");
		for (int i = 0; i < characters.size(); i++) {
			buf.append(characters.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}