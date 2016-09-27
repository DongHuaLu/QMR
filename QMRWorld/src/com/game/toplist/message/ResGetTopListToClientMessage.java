package com.game.toplist.message;

import com.game.toplist.bean.TopInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送给客户端获取排行榜信息消息
 */
public class ResGetTopListToClientMessage extends Message{

	//错误代码
	private byte errorcode;
	
	//自己今天膜拜次数
	private byte worshipnum;
	
	//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩
	private byte toptype;
	
	//前5排行榜信息列表
	private List<TopInfo> top5infolist = new ArrayList<TopInfo>();
	//自己排行榜信息列表
	private List<TopInfo> topselfinfolist = new ArrayList<TopInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.errorcode);
		//自己今天膜拜次数
		writeByte(buf, this.worshipnum);
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩
		writeByte(buf, this.toptype);
		//前5排行榜信息列表
		writeShort(buf, top5infolist.size());
		for (int i = 0; i < top5infolist.size(); i++) {
			writeBean(buf, top5infolist.get(i));
		}
		//自己排行榜信息列表
		writeShort(buf, topselfinfolist.size());
		for (int i = 0; i < topselfinfolist.size(); i++) {
			writeBean(buf, topselfinfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.errorcode = readByte(buf);
		//自己今天膜拜次数
		this.worshipnum = readByte(buf);
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩
		this.toptype = readByte(buf);
		//前5排行榜信息列表
		int top5infolist_length = readShort(buf);
		for (int i = 0; i < top5infolist_length; i++) {
			top5infolist.add((TopInfo)readBean(buf, TopInfo.class));
		}
		//自己排行榜信息列表
		int topselfinfolist_length = readShort(buf);
		for (int i = 0; i < topselfinfolist_length; i++) {
			topselfinfolist.add((TopInfo)readBean(buf, TopInfo.class));
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
	 * get 自己今天膜拜次数
	 * @return 
	 */
	public byte getWorshipnum(){
		return worshipnum;
	}
	
	/**
	 * set 自己今天膜拜次数
	 */
	public void setWorshipnum(byte worshipnum){
		this.worshipnum = worshipnum;
	}
	
	/**
	 * get 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩
	 * @return 
	 */
	public byte getToptype(){
		return toptype;
	}
	
	/**
	 * set 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩
	 */
	public void setToptype(byte toptype){
		this.toptype = toptype;
	}
	
	/**
	 * get 前5排行榜信息列表
	 * @return 
	 */
	public List<TopInfo> getTop5infolist(){
		return top5infolist;
	}
	
	/**
	 * set 前5排行榜信息列表
	 */
	public void setTop5infolist(List<TopInfo> top5infolist){
		this.top5infolist = top5infolist;
	}
	
	/**
	 * get 自己排行榜信息列表
	 * @return 
	 */
	public List<TopInfo> getTopselfinfolist(){
		return topselfinfolist;
	}
	
	/**
	 * set 自己排行榜信息列表
	 */
	public void setTopselfinfolist(List<TopInfo> topselfinfolist){
		this.topselfinfolist = topselfinfolist;
	}
	
	
	@Override
	public int getId() {
		return 142101;
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
		//自己今天膜拜次数
		buf.append("worshipnum:" + worshipnum +",");
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩
		buf.append("toptype:" + toptype +",");
		//前5排行榜信息列表
		buf.append("top5infolist:{");
		for (int i = 0; i < top5infolist.size(); i++) {
			buf.append(top5infolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//自己排行榜信息列表
		buf.append("topselfinfolist:{");
		for (int i = 0; i < topselfinfolist.size(); i++) {
			buf.append(topselfinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}