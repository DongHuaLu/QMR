package com.game.toplist.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送给客户端称号列表消息
 */
public class ResGetTopTitleToClientMessage extends Message{

	//错误代码
	private byte errorcode;
	
	//称号id列表
	private List<Integer> titleidlist = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.errorcode);
		//称号id列表
		writeShort(buf, titleidlist.size());
		for (int i = 0; i < titleidlist.size(); i++) {
			writeInt(buf, titleidlist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.errorcode = readByte(buf);
		//称号id列表
		int titleidlist_length = readShort(buf);
		for (int i = 0; i < titleidlist_length; i++) {
			titleidlist.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getErrorcode(){
		return errorcode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setErrorcode(byte errorcode){
		this.errorcode = errorcode;
	}
	
	/**
	 * get 称号id列表
	 * @return 
	 */
	public List<Integer> getTitleidlist(){
		return titleidlist;
	}
	
	/**
	 * set 称号id列表
	 */
	public void setTitleidlist(List<Integer> titleidlist){
		this.titleidlist = titleidlist;
	}
	
	
	@Override
	public int getId() {
		return 142150;
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
		//错误代码
		buf.append("errorcode:" + errorcode +",");
		//称号id列表
		buf.append("titleidlist:{");
		for (int i = 0; i < titleidlist.size(); i++) {
			buf.append(titleidlist.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}