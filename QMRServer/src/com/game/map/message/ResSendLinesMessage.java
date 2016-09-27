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
 * 发送当前线数量消息
 */
public class ResSendLinesMessage extends Message{

	//线列表
	private List<Integer> lines = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//线列表
		writeShort(buf, lines.size());
		for (int i = 0; i < lines.size(); i++) {
			writeInt(buf, lines.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//线列表
		int lines_length = readShort(buf);
		for (int i = 0; i < lines_length; i++) {
			lines.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 线列表
	 * @return 
	 */
	public List<Integer> getLines(){
		return lines;
	}
	
	/**
	 * set 线列表
	 */
	public void setLines(List<Integer> lines){
		this.lines = lines;
	}
	
	
	@Override
	public int getId() {
		return 101124;
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
		//线列表
		buf.append("lines:{");
		for (int i = 0; i < lines.size(); i++) {
			buf.append(lines.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}