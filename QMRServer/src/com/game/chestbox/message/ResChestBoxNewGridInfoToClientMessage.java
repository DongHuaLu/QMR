package com.game.chestbox.message;

import com.game.chestbox.bean.ChestGridInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宝箱盒子生成新物品信息发送到客户端消息
 */
public class ResChestBoxNewGridInfoToClientMessage extends Message{

	//新内圈格子信息
	private List<ChestGridInfo> newingridlist = new ArrayList<ChestGridInfo>();
	//新外圈格子信息
	private List<ChestGridInfo> newoutgridlist = new ArrayList<ChestGridInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//新内圈格子信息
		writeShort(buf, newingridlist.size());
		for (int i = 0; i < newingridlist.size(); i++) {
			writeBean(buf, newingridlist.get(i));
		}
		//新外圈格子信息
		writeShort(buf, newoutgridlist.size());
		for (int i = 0; i < newoutgridlist.size(); i++) {
			writeBean(buf, newoutgridlist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//新内圈格子信息
		int newingridlist_length = readShort(buf);
		for (int i = 0; i < newingridlist_length; i++) {
			newingridlist.add((ChestGridInfo)readBean(buf, ChestGridInfo.class));
		}
		//新外圈格子信息
		int newoutgridlist_length = readShort(buf);
		for (int i = 0; i < newoutgridlist_length; i++) {
			newoutgridlist.add((ChestGridInfo)readBean(buf, ChestGridInfo.class));
		}
		return true;
	}
	
	/**
	 * get 新内圈格子信息
	 * @return 
	 */
	public List<ChestGridInfo> getNewingridlist(){
		return newingridlist;
	}
	
	/**
	 * set 新内圈格子信息
	 */
	public void setNewingridlist(List<ChestGridInfo> newingridlist){
		this.newingridlist = newingridlist;
	}
	
	/**
	 * get 新外圈格子信息
	 * @return 
	 */
	public List<ChestGridInfo> getNewoutgridlist(){
		return newoutgridlist;
	}
	
	/**
	 * set 新外圈格子信息
	 */
	public void setNewoutgridlist(List<ChestGridInfo> newoutgridlist){
		this.newoutgridlist = newoutgridlist;
	}
	
	
	@Override
	public int getId() {
		return 156103;
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
		//新内圈格子信息
		buf.append("newingridlist:{");
		for (int i = 0; i < newingridlist.size(); i++) {
			buf.append(newingridlist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//新外圈格子信息
		buf.append("newoutgridlist:{");
		for (int i = 0; i < newoutgridlist.size(); i++) {
			buf.append(newoutgridlist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}