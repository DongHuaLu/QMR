package com.game.marriage.message;

import com.game.marriage.bean.LeaveMsgInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获取配偶面板信息消息
 */
public class ResSpouseInfoToClientMessage extends Message{

	//配偶信息
	private com.game.player.bean.OtherPlayerInfo otherPlayerInfo;
	
	//留言列表
	private List<LeaveMsgInfo> leavemsglist = new ArrayList<LeaveMsgInfo>();
	//戒指模版ID
	private int ringmodelid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//配偶信息
		writeBean(buf, this.otherPlayerInfo);
		//留言列表
		writeShort(buf, leavemsglist.size());
		for (int i = 0; i < leavemsglist.size(); i++) {
			writeBean(buf, leavemsglist.get(i));
		}
		//戒指模版ID
		writeInt(buf, this.ringmodelid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//配偶信息
		this.otherPlayerInfo = (com.game.player.bean.OtherPlayerInfo)readBean(buf, com.game.player.bean.OtherPlayerInfo.class);
		//留言列表
		int leavemsglist_length = readShort(buf);
		for (int i = 0; i < leavemsglist_length; i++) {
			leavemsglist.add((LeaveMsgInfo)readBean(buf, LeaveMsgInfo.class));
		}
		//戒指模版ID
		this.ringmodelid = readInt(buf);
		return true;
	}
	
	/**
	 * get 配偶信息
	 * @return 
	 */
	public com.game.player.bean.OtherPlayerInfo getOtherPlayerInfo(){
		return otherPlayerInfo;
	}
	
	/**
	 * set 配偶信息
	 */
	public void setOtherPlayerInfo(com.game.player.bean.OtherPlayerInfo otherPlayerInfo){
		this.otherPlayerInfo = otherPlayerInfo;
	}
	
	/**
	 * get 留言列表
	 * @return 
	 */
	public List<LeaveMsgInfo> getLeavemsglist(){
		return leavemsglist;
	}
	
	/**
	 * set 留言列表
	 */
	public void setLeavemsglist(List<LeaveMsgInfo> leavemsglist){
		this.leavemsglist = leavemsglist;
	}
	
	/**
	 * get 戒指模版ID
	 * @return 
	 */
	public int getRingmodelid(){
		return ringmodelid;
	}
	
	/**
	 * set 戒指模版ID
	 */
	public void setRingmodelid(int ringmodelid){
		this.ringmodelid = ringmodelid;
	}
	
	
	@Override
	public int getId() {
		return 163110;
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
		//配偶信息
		if(this.otherPlayerInfo!=null) buf.append("otherPlayerInfo:" + otherPlayerInfo.toString() +",");
		//留言列表
		buf.append("leavemsglist:{");
		for (int i = 0; i < leavemsglist.size(); i++) {
			buf.append(leavemsglist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//戒指模版ID
		buf.append("ringmodelid:" + ringmodelid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}