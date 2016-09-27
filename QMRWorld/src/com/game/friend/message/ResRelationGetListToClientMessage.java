package com.game.friend.message;

import com.game.friend.bean.RelationInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回关系列表消息
 */
public class ResRelationGetListToClientMessage extends Message{

	//自己的关系信息
	private RelationInfo relationMyInfo;
	
	//关系信息列表
	private List<RelationInfo> relationList = new ArrayList<RelationInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//自己的关系信息
		writeBean(buf, this.relationMyInfo);
		//关系信息列表
		writeShort(buf, relationList.size());
		for (int i = 0; i < relationList.size(); i++) {
			writeBean(buf, relationList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//自己的关系信息
		this.relationMyInfo = (RelationInfo)readBean(buf, RelationInfo.class);
		//关系信息列表
		int relationList_length = readShort(buf);
		for (int i = 0; i < relationList_length; i++) {
			relationList.add((RelationInfo)readBean(buf, RelationInfo.class));
		}
		return true;
	}
	
	/**
	 * get 自己的关系信息
	 * @return 
	 */
	public RelationInfo getRelationMyInfo(){
		return relationMyInfo;
	}
	
	/**
	 * set 自己的关系信息
	 */
	public void setRelationMyInfo(RelationInfo relationMyInfo){
		this.relationMyInfo = relationMyInfo;
	}
	
	/**
	 * get 关系信息列表
	 * @return 
	 */
	public List<RelationInfo> getRelationList(){
		return relationList;
	}
	
	/**
	 * set 关系信息列表
	 */
	public void setRelationList(List<RelationInfo> relationList){
		this.relationList = relationList;
	}
	
	
	@Override
	public int getId() {
		return 119101;
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
		//自己的关系信息
		if(this.relationMyInfo!=null) buf.append("relationMyInfo:" + relationMyInfo.toString() +",");
		//关系信息列表
		buf.append("relationList:{");
		for (int i = 0; i < relationList.size(); i++) {
			buf.append(relationList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}