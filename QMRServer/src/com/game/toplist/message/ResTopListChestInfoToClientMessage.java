package com.game.toplist.message;

import com.game.toplist.bean.ChestInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送角色宝箱信息消息
 */
public class ResTopListChestInfoToClientMessage extends Message{

	//玩家当前宝箱列表
	private List<ChestInfo> chestinfolist = new ArrayList<ChestInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家当前宝箱列表
		writeShort(buf, chestinfolist.size());
		for (int i = 0; i < chestinfolist.size(); i++) {
			writeBean(buf, chestinfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家当前宝箱列表
		int chestinfolist_length = readShort(buf);
		for (int i = 0; i < chestinfolist_length; i++) {
			chestinfolist.add((ChestInfo)readBean(buf, ChestInfo.class));
		}
		return true;
	}
	
	/**
	 * get 玩家当前宝箱列表
	 * @return 
	 */
	public List<ChestInfo> getChestinfolist(){
		return chestinfolist;
	}
	
	/**
	 * set 玩家当前宝箱列表
	 */
	public void setChestinfolist(List<ChestInfo> chestinfolist){
		this.chestinfolist = chestinfolist;
	}
	
	
	@Override
	public int getId() {
		return 142151;
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
		//玩家当前宝箱列表
		buf.append("chestinfolist:{");
		for (int i = 0; i < chestinfolist.size(); i++) {
			buf.append(chestinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}