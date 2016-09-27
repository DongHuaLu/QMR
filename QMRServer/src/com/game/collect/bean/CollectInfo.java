package com.game.collect.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 藏品信息
 */
public class CollectInfo extends Bean {

	//藏品模型
	private int modelid;
	
	//碎片信息
	private List<FragInfo> fragList = new ArrayList<FragInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//藏品模型
		writeInt(buf, this.modelid);
		//碎片信息
		writeShort(buf, fragList.size());
		for (int i = 0; i < fragList.size(); i++) {
			writeBean(buf, fragList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//藏品模型
		this.modelid = readInt(buf);
		//碎片信息
		int fragList_length = readShort(buf);
		for (int i = 0; i < fragList_length; i++) {
			fragList.add((FragInfo)readBean(buf, FragInfo.class));
		}
		return true;
	}
	
	/**
	 * get 藏品模型
	 * @return 
	 */
	public int getModelid(){
		return modelid;
	}
	
	/**
	 * set 藏品模型
	 */
	public void setModelid(int modelid){
		this.modelid = modelid;
	}
	
	/**
	 * get 碎片信息
	 * @return 
	 */
	public List<FragInfo> getFragList(){
		return fragList;
	}
	
	/**
	 * set 碎片信息
	 */
	public void setFragList(List<FragInfo> fragList){
		this.fragList = fragList;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//藏品模型
		buf.append("modelid:" + modelid +",");
		//碎片信息
		buf.append("fragList:{");
		for (int i = 0; i < fragList.size(); i++) {
			buf.append(fragList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}