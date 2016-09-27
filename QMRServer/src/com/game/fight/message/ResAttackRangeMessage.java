package com.game.fight.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 攻击范围(测试专用)消息
 */
public class ResAttackRangeMessage extends Message{

	//格子列表
	private List<Integer> grids = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//格子列表
		writeShort(buf, grids.size());
		for (int i = 0; i < grids.size(); i++) {
			writeInt(buf, grids.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//格子列表
		int grids_length = readShort(buf);
		for (int i = 0; i < grids_length; i++) {
			grids.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 格子列表
	 * @return 
	 */
	public List<Integer> getGrids(){
		return grids;
	}
	
	/**
	 * set 格子列表
	 */
	public void setGrids(List<Integer> grids){
		this.grids = grids;
	}
	
	
	@Override
	public int getId() {
		return 102103;
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
		//格子列表
		buf.append("grids:{");
		for (int i = 0; i < grids.size(); i++) {
			buf.append(grids.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}