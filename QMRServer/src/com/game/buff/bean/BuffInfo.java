package com.game.buff.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * buff信息
 */
public class BuffInfo extends Bean {

	//Buff Id
	private long buffId;
	
	//Buff 模板Id
	private int modelId;
	
	//Buff 总时间
	private int total;
	
	//Buff 剩余时间
	private int remain;
	
	//Buff 叠加层数
	private int overlay;
	
	//Buff 数值(血池时是血量)
	private int value;
	
	//Buff 比例
	private int percent;
	
	//buff 参数
	private List<BuffPara> buffparas = new ArrayList<BuffPara>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//Buff Id
		writeLong(buf, this.buffId);
		//Buff 模板Id
		writeInt(buf, this.modelId);
		//Buff 总时间
		writeInt(buf, this.total);
		//Buff 剩余时间
		writeInt(buf, this.remain);
		//Buff 叠加层数
		writeInt(buf, this.overlay);
		//Buff 数值(血池时是血量)
		writeInt(buf, this.value);
		//Buff 比例
		writeInt(buf, this.percent);
		//buff 参数
		writeShort(buf, buffparas.size());
		for (int i = 0; i < buffparas.size(); i++) {
			writeBean(buf, buffparas.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//Buff Id
		this.buffId = readLong(buf);
		//Buff 模板Id
		this.modelId = readInt(buf);
		//Buff 总时间
		this.total = readInt(buf);
		//Buff 剩余时间
		this.remain = readInt(buf);
		//Buff 叠加层数
		this.overlay = readInt(buf);
		//Buff 数值(血池时是血量)
		this.value = readInt(buf);
		//Buff 比例
		this.percent = readInt(buf);
		//buff 参数
		int buffparas_length = readShort(buf);
		for (int i = 0; i < buffparas_length; i++) {
			buffparas.add((BuffPara)readBean(buf, BuffPara.class));
		}
		return true;
	}
	
	/**
	 * get Buff Id
	 * @return 
	 */
	public long getBuffId(){
		return buffId;
	}
	
	/**
	 * set Buff Id
	 */
	public void setBuffId(long buffId){
		this.buffId = buffId;
	}
	
	/**
	 * get Buff 模板Id
	 * @return 
	 */
	public int getModelId(){
		return modelId;
	}
	
	/**
	 * set Buff 模板Id
	 */
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	/**
	 * get Buff 总时间
	 * @return 
	 */
	public int getTotal(){
		return total;
	}
	
	/**
	 * set Buff 总时间
	 */
	public void setTotal(int total){
		this.total = total;
	}
	
	/**
	 * get Buff 剩余时间
	 * @return 
	 */
	public int getRemain(){
		return remain;
	}
	
	/**
	 * set Buff 剩余时间
	 */
	public void setRemain(int remain){
		this.remain = remain;
	}
	
	/**
	 * get Buff 叠加层数
	 * @return 
	 */
	public int getOverlay(){
		return overlay;
	}
	
	/**
	 * set Buff 叠加层数
	 */
	public void setOverlay(int overlay){
		this.overlay = overlay;
	}
	
	/**
	 * get Buff 数值(血池时是血量)
	 * @return 
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * set Buff 数值(血池时是血量)
	 */
	public void setValue(int value){
		this.value = value;
	}
	
	/**
	 * get Buff 比例
	 * @return 
	 */
	public int getPercent(){
		return percent;
	}
	
	/**
	 * set Buff 比例
	 */
	public void setPercent(int percent){
		this.percent = percent;
	}
	
	/**
	 * get buff 参数
	 * @return 
	 */
	public List<BuffPara> getBuffparas(){
		return buffparas;
	}
	
	/**
	 * set buff 参数
	 */
	public void setBuffparas(List<BuffPara> buffparas){
		this.buffparas = buffparas;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//Buff Id
		buf.append("buffId:" + buffId +",");
		//Buff 模板Id
		buf.append("modelId:" + modelId +",");
		//Buff 总时间
		buf.append("total:" + total +",");
		//Buff 剩余时间
		buf.append("remain:" + remain +",");
		//Buff 叠加层数
		buf.append("overlay:" + overlay +",");
		//Buff 数值(血池时是血量)
		buf.append("value:" + value +",");
		//Buff 比例
		buf.append("percent:" + percent +",");
		//buff 参数
		buf.append("buffparas:{");
		for (int i = 0; i < buffparas.size(); i++) {
			buf.append(buffparas.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}