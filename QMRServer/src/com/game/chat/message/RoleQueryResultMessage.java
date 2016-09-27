package com.game.chat.message;

import com.game.chat.bean.RoleChatInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 查找结果列表消息
 */
public class RoleQueryResultMessage extends Message{

	//页码
	private int page;
	
	//总数
	private int count;
	
	//结果列表
	private List<RoleChatInfo> list = new ArrayList<RoleChatInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//页码
		writeInt(buf, this.page);
		//总数
		writeInt(buf, this.count);
		//结果列表
		writeShort(buf, list.size());
		for (int i = 0; i < list.size(); i++) {
			writeBean(buf, list.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//页码
		this.page = readInt(buf);
		//总数
		this.count = readInt(buf);
		//结果列表
		int list_length = readShort(buf);
		for (int i = 0; i < list_length; i++) {
			list.add((RoleChatInfo)readBean(buf, RoleChatInfo.class));
		}
		return true;
	}
	
	/**
	 * get 页码
	 * @return 
	 */
	public int getPage(){
		return page;
	}
	
	/**
	 * set 页码
	 */
	public void setPage(int page){
		this.page = page;
	}
	
	/**
	 * get 总数
	 * @return 
	 */
	public int getCount(){
		return count;
	}
	
	/**
	 * set 总数
	 */
	public void setCount(int count){
		this.count = count;
	}
	
	/**
	 * get 结果列表
	 * @return 
	 */
	public List<RoleChatInfo> getList(){
		return list;
	}
	
	/**
	 * set 结果列表
	 */
	public void setList(List<RoleChatInfo> list){
		this.list = list;
	}
	
	
	@Override
	public int getId() {
		return 111102;
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
		//页码
		buf.append("page:" + page +",");
		//总数
		buf.append("count:" + count +",");
		//结果列表
		buf.append("list:{");
		for (int i = 0; i < list.size(); i++) {
			buf.append(list.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}