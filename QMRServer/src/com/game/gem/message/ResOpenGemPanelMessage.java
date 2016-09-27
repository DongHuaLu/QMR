package com.game.gem.message;

import com.game.gem.bean.PosGemInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回客户端全部宝石信息消息
 */
public class ResOpenGemPanelMessage extends Message{

	//装备部位全部宝石信息
	private List<PosGemInfo> posallgeminfo = new ArrayList<PosGemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备部位全部宝石信息
		writeShort(buf, posallgeminfo.size());
		for (int i = 0; i < posallgeminfo.size(); i++) {
			writeBean(buf, posallgeminfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//装备部位全部宝石信息
		int posallgeminfo_length = readShort(buf);
		for (int i = 0; i < posallgeminfo_length; i++) {
			posallgeminfo.add((PosGemInfo)readBean(buf, PosGemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 装备部位全部宝石信息
	 * @return 
	 */
	public List<PosGemInfo> getPosallgeminfo(){
		return posallgeminfo;
	}
	
	/**
	 * set 装备部位全部宝石信息
	 */
	public void setPosallgeminfo(List<PosGemInfo> posallgeminfo){
		this.posallgeminfo = posallgeminfo;
	}
	
	
	@Override
	public int getId() {
		return 132101;
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
		//装备部位全部宝石信息
		buf.append("posallgeminfo:{");
		for (int i = 0; i < posallgeminfo.size(); i++) {
			buf.append(posallgeminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}