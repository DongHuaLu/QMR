package com.game.arrow.message;

import com.game.arrow.bean.FightSpiritInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回战魂信息消息
 */
public class ResFightSpiritInfoMessage extends Message{

	//通知类型
	private int notifytype;
	
	//战魂信息列表
	private List<FightSpiritInfo> fightspiritList = new ArrayList<FightSpiritInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知类型
		writeInt(buf, this.notifytype);
		//战魂信息列表
		writeShort(buf, fightspiritList.size());
		for (int i = 0; i < fightspiritList.size(); i++) {
			writeBean(buf, fightspiritList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知类型
		this.notifytype = readInt(buf);
		//战魂信息列表
		int fightspiritList_length = readShort(buf);
		for (int i = 0; i < fightspiritList_length; i++) {
			fightspiritList.add((FightSpiritInfo)readBean(buf, FightSpiritInfo.class));
		}
		return true;
	}
	
	/**
	 * get 通知类型
	 * @return 
	 */
	public int getNotifytype(){
		return notifytype;
	}
	
	/**
	 * set 通知类型
	 */
	public void setNotifytype(int notifytype){
		this.notifytype = notifytype;
	}
	
	/**
	 * get 战魂信息列表
	 * @return 
	 */
	public List<FightSpiritInfo> getFightspiritList(){
		return fightspiritList;
	}
	
	/**
	 * set 战魂信息列表
	 */
	public void setFightspiritList(List<FightSpiritInfo> fightspiritList){
		this.fightspiritList = fightspiritList;
	}
	
	
	@Override
	public int getId() {
		return 151104;
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
		//通知类型
		buf.append("notifytype:" + notifytype +",");
		//战魂信息列表
		buf.append("fightspiritList:{");
		for (int i = 0; i < fightspiritList.size(); i++) {
			buf.append(fightspiritList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}