package com.game.transactions.message;

import com.game.transactions.bean.TmpYuanbaoLogInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送临时元宝日志消息
 */
public class ResTmpYuanbaoLogMessage extends Message{

	//汇率
	private String exchange;
	
	//网址
	private String web;
	
	//可领取元宝
	private int canryuanbao;
	
	//临时元宝日志列表
	private List<TmpYuanbaoLogInfo> tpmyuanbaolonginfo = new ArrayList<TmpYuanbaoLogInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//汇率
		writeString(buf, this.exchange);
		//网址
		writeString(buf, this.web);
		//可领取元宝
		writeInt(buf, this.canryuanbao);
		//临时元宝日志列表
		writeShort(buf, tpmyuanbaolonginfo.size());
		for (int i = 0; i < tpmyuanbaolonginfo.size(); i++) {
			writeBean(buf, tpmyuanbaolonginfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//汇率
		this.exchange = readString(buf);
		//网址
		this.web = readString(buf);
		//可领取元宝
		this.canryuanbao = readInt(buf);
		//临时元宝日志列表
		int tpmyuanbaolonginfo_length = readShort(buf);
		for (int i = 0; i < tpmyuanbaolonginfo_length; i++) {
			tpmyuanbaolonginfo.add((TmpYuanbaoLogInfo)readBean(buf, TmpYuanbaoLogInfo.class));
		}
		return true;
	}
	
	/**
	 * get 汇率
	 * @return 
	 */
	public String getExchange(){
		return exchange;
	}
	
	/**
	 * set 汇率
	 */
	public void setExchange(String exchange){
		this.exchange = exchange;
	}
	
	/**
	 * get 网址
	 * @return 
	 */
	public String getWeb(){
		return web;
	}
	
	/**
	 * set 网址
	 */
	public void setWeb(String web){
		this.web = web;
	}
	
	/**
	 * get 可领取元宝
	 * @return 
	 */
	public int getCanryuanbao(){
		return canryuanbao;
	}
	
	/**
	 * set 可领取元宝
	 */
	public void setCanryuanbao(int canryuanbao){
		this.canryuanbao = canryuanbao;
	}
	
	/**
	 * get 临时元宝日志列表
	 * @return 
	 */
	public List<TmpYuanbaoLogInfo> getTpmyuanbaolonginfo(){
		return tpmyuanbaolonginfo;
	}
	
	/**
	 * set 临时元宝日志列表
	 */
	public void setTpmyuanbaolonginfo(List<TmpYuanbaoLogInfo> tpmyuanbaolonginfo){
		this.tpmyuanbaolonginfo = tpmyuanbaolonginfo;
	}
	
	
	@Override
	public int getId() {
		return 122112;
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
		//汇率
		if(this.exchange!=null) buf.append("exchange:" + exchange.toString() +",");
		//网址
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//可领取元宝
		buf.append("canryuanbao:" + canryuanbao +",");
		//临时元宝日志列表
		buf.append("tpmyuanbaolonginfo:{");
		for (int i = 0; i < tpmyuanbaolonginfo.size(); i++) {
			buf.append(tpmyuanbaolonginfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}