package com.game.spirittree.message;

import com.game.spirittree.bean.Rewardbriefinfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 连续催熟返回奖励消息消息
 */
public class ResContinuousRipeningToClientMessage extends Message{

	//奖励道具简要信息
	private List<Rewardbriefinfo> rewardbriefinfo = new ArrayList<Rewardbriefinfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//奖励道具简要信息
		writeShort(buf, rewardbriefinfo.size());
		for (int i = 0; i < rewardbriefinfo.size(); i++) {
			writeBean(buf, rewardbriefinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//奖励道具简要信息
		int rewardbriefinfo_length = readShort(buf);
		for (int i = 0; i < rewardbriefinfo_length; i++) {
			rewardbriefinfo.add((Rewardbriefinfo)readBean(buf, Rewardbriefinfo.class));
		}
		return true;
	}
	
	/**
	 * get 奖励道具简要信息
	 * @return 
	 */
	public List<Rewardbriefinfo> getRewardbriefinfo(){
		return rewardbriefinfo;
	}
	
	/**
	 * set 奖励道具简要信息
	 */
	public void setRewardbriefinfo(List<Rewardbriefinfo> rewardbriefinfo){
		this.rewardbriefinfo = rewardbriefinfo;
	}
	
	
	@Override
	public int getId() {
		return 133110;
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
		//奖励道具简要信息
		buf.append("rewardbriefinfo:{");
		for (int i = 0; i < rewardbriefinfo.size(); i++) {
			buf.append(rewardbriefinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}