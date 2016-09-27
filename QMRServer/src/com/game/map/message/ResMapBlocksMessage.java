package com.game.map.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 阻挡点信息消息
 */
public class ResMapBlocksMessage extends Message{

	//阻挡点集合
	private List<Integer> blocks = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//阻挡点集合
		writeShort(buf, blocks.size());
		for (int i = 0; i < blocks.size(); i++) {
			writeInt(buf, blocks.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//阻挡点集合
		int blocks_length = readShort(buf);
		for (int i = 0; i < blocks_length; i++) {
			blocks.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 阻挡点集合
	 * @return 
	 */
	public List<Integer> getBlocks(){
		return blocks;
	}
	
	/**
	 * set 阻挡点集合
	 */
	public void setBlocks(List<Integer> blocks){
		this.blocks = blocks;
	}
	
	
	@Override
	public int getId() {
		return 101703;
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
		//阻挡点集合
		buf.append("blocks:{");
		for (int i = 0; i < blocks.size(); i++) {
			buf.append(blocks.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}