package com.game.signwage.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摇奖得到奖励消息
 */
public class ResWageERNIEinofMessage extends Message{

	//位置
	private List<Integer> pos = new ArrayList<Integer>();
	//道具奖励
	private List<com.game.spirittree.bean.FruitRewardinfo> fruitRewardinfo = new ArrayList<com.game.spirittree.bean.FruitRewardinfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//位置
		writeShort(buf, pos.size());
		for (int i = 0; i < pos.size(); i++) {
			writeInt(buf, pos.get(i));
		}
		//道具奖励
		writeShort(buf, fruitRewardinfo.size());
		for (int i = 0; i < fruitRewardinfo.size(); i++) {
			writeBean(buf, fruitRewardinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//位置
		int pos_length = readShort(buf);
		for (int i = 0; i < pos_length; i++) {
			pos.add(readInt(buf));
		}
		//道具奖励
		int fruitRewardinfo_length = readShort(buf);
		for (int i = 0; i < fruitRewardinfo_length; i++) {
			fruitRewardinfo.add((com.game.spirittree.bean.FruitRewardinfo)readBean(buf, com.game.spirittree.bean.FruitRewardinfo.class));
		}
		return true;
	}
	
	/**
	 * get 位置
	 * @return 
	 */
	public List<Integer> getPos(){
		return pos;
	}
	
	/**
	 * set 位置
	 */
	public void setPos(List<Integer> pos){
		this.pos = pos;
	}
	
	/**
	 * get 道具奖励
	 * @return 
	 */
	public List<com.game.spirittree.bean.FruitRewardinfo> getFruitRewardinfo(){
		return fruitRewardinfo;
	}
	
	/**
	 * set 道具奖励
	 */
	public void setFruitRewardinfo(List<com.game.spirittree.bean.FruitRewardinfo> fruitRewardinfo){
		this.fruitRewardinfo = fruitRewardinfo;
	}
	
	
	@Override
	public int getId() {
		return 152103;
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
		//位置
		buf.append("pos:{");
		for (int i = 0; i < pos.size(); i++) {
			buf.append(pos.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//道具奖励
		buf.append("fruitRewardinfo:{");
		for (int i = 0; i < fruitRewardinfo.size(); i++) {
			buf.append(fruitRewardinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}