package com.game.chestbox.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宝箱盒子信息
 */
public class ChestBoxInfo extends Bean {

	//开启次数
	private int opennum;
	
	//是否自动
	private int autoopen;
	
	//内圈选中编号
	private int inchooseidx;
	
	//外圈选中编号
	private int outchooseidx;
	
	//内圈格子列表
	private List<ChestGridInfo> ingridlist = new ArrayList<ChestGridInfo>();
	//外圈格子列表
	private List<ChestGridInfo> outgridlist = new ArrayList<ChestGridInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//开启次数
		writeInt(buf, this.opennum);
		//是否自动
		writeInt(buf, this.autoopen);
		//内圈选中编号
		writeInt(buf, this.inchooseidx);
		//外圈选中编号
		writeInt(buf, this.outchooseidx);
		//内圈格子列表
		writeShort(buf, ingridlist.size());
		for (int i = 0; i < ingridlist.size(); i++) {
			writeBean(buf, ingridlist.get(i));
		}
		//外圈格子列表
		writeShort(buf, outgridlist.size());
		for (int i = 0; i < outgridlist.size(); i++) {
			writeBean(buf, outgridlist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//开启次数
		this.opennum = readInt(buf);
		//是否自动
		this.autoopen = readInt(buf);
		//内圈选中编号
		this.inchooseidx = readInt(buf);
		//外圈选中编号
		this.outchooseidx = readInt(buf);
		//内圈格子列表
		int ingridlist_length = readShort(buf);
		for (int i = 0; i < ingridlist_length; i++) {
			ingridlist.add((ChestGridInfo)readBean(buf, ChestGridInfo.class));
		}
		//外圈格子列表
		int outgridlist_length = readShort(buf);
		for (int i = 0; i < outgridlist_length; i++) {
			outgridlist.add((ChestGridInfo)readBean(buf, ChestGridInfo.class));
		}
		return true;
	}
	
	/**
	 * get 开启次数
	 * @return 
	 */
	public int getOpennum(){
		return opennum;
	}
	
	/**
	 * set 开启次数
	 */
	public void setOpennum(int opennum){
		this.opennum = opennum;
	}
	
	/**
	 * get 是否自动
	 * @return 
	 */
	public int getAutoopen(){
		return autoopen;
	}
	
	/**
	 * set 是否自动
	 */
	public void setAutoopen(int autoopen){
		this.autoopen = autoopen;
	}
	
	/**
	 * get 内圈选中编号
	 * @return 
	 */
	public int getInchooseidx(){
		return inchooseidx;
	}
	
	/**
	 * set 内圈选中编号
	 */
	public void setInchooseidx(int inchooseidx){
		this.inchooseidx = inchooseidx;
	}
	
	/**
	 * get 外圈选中编号
	 * @return 
	 */
	public int getOutchooseidx(){
		return outchooseidx;
	}
	
	/**
	 * set 外圈选中编号
	 */
	public void setOutchooseidx(int outchooseidx){
		this.outchooseidx = outchooseidx;
	}
	
	/**
	 * get 内圈格子列表
	 * @return 
	 */
	public List<ChestGridInfo> getIngridlist(){
		return ingridlist;
	}
	
	/**
	 * set 内圈格子列表
	 */
	public void setIngridlist(List<ChestGridInfo> ingridlist){
		this.ingridlist = ingridlist;
	}
	
	/**
	 * get 外圈格子列表
	 * @return 
	 */
	public List<ChestGridInfo> getOutgridlist(){
		return outgridlist;
	}
	
	/**
	 * set 外圈格子列表
	 */
	public void setOutgridlist(List<ChestGridInfo> outgridlist){
		this.outgridlist = outgridlist;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//开启次数
		buf.append("opennum:" + opennum +",");
		//是否自动
		buf.append("autoopen:" + autoopen +",");
		//内圈选中编号
		buf.append("inchooseidx:" + inchooseidx +",");
		//外圈选中编号
		buf.append("outchooseidx:" + outchooseidx +",");
		//内圈格子列表
		buf.append("ingridlist:{");
		for (int i = 0; i < ingridlist.size(); i++) {
			buf.append(ingridlist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//外圈格子列表
		buf.append("outgridlist:{");
		for (int i = 0; i < outgridlist.size(); i++) {
			buf.append(outgridlist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}