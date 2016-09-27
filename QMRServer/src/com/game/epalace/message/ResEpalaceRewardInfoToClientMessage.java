package com.game.epalace.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送奖励消息消息
 */
public class ResEpalaceRewardInfoToClientMessage extends Message{

	//奖励类型，和格子事件对应
	private int type;
	
	//奖励内容
	private List<com.game.spirittree.bean.FruitRewardinfo> fruitrewardinfo = new ArrayList<com.game.spirittree.bean.FruitRewardinfo>();
	//普通奖励的BUFFID，如果是0，表示给其他数值奖励
	private int buffid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//奖励类型，和格子事件对应
		writeInt(buf, this.type);
		//奖励内容
		writeShort(buf, fruitrewardinfo.size());
		for (int i = 0; i < fruitrewardinfo.size(); i++) {
			writeBean(buf, fruitrewardinfo.get(i));
		}
		//普通奖励的BUFFID，如果是0，表示给其他数值奖励
		writeInt(buf, this.buffid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//奖励类型，和格子事件对应
		this.type = readInt(buf);
		//奖励内容
		int fruitrewardinfo_length = readShort(buf);
		for (int i = 0; i < fruitrewardinfo_length; i++) {
			fruitrewardinfo.add((com.game.spirittree.bean.FruitRewardinfo)readBean(buf, com.game.spirittree.bean.FruitRewardinfo.class));
		}
		//普通奖励的BUFFID，如果是0，表示给其他数值奖励
		this.buffid = readInt(buf);
		return true;
	}
	
	/**
	 * get 奖励类型，和格子事件对应
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 奖励类型，和格子事件对应
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 奖励内容
	 * @return 
	 */
	public List<com.game.spirittree.bean.FruitRewardinfo> getFruitrewardinfo(){
		return fruitrewardinfo;
	}
	
	/**
	 * set 奖励内容
	 */
	public void setFruitrewardinfo(List<com.game.spirittree.bean.FruitRewardinfo> fruitrewardinfo){
		this.fruitrewardinfo = fruitrewardinfo;
	}
	
	/**
	 * get 普通奖励的BUFFID，如果是0，表示给其他数值奖励
	 * @return 
	 */
	public int getBuffid(){
		return buffid;
	}
	
	/**
	 * set 普通奖励的BUFFID，如果是0，表示给其他数值奖励
	 */
	public void setBuffid(int buffid){
		this.buffid = buffid;
	}
	
	
	@Override
	public int getId() {
		return 143106;
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
		//奖励类型，和格子事件对应
		buf.append("type:" + type +",");
		//奖励内容
		buf.append("fruitrewardinfo:{");
		for (int i = 0; i < fruitrewardinfo.size(); i++) {
			buf.append(fruitrewardinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//普通奖励的BUFFID，如果是0，表示给其他数值奖励
		buf.append("buffid:" + buffid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}