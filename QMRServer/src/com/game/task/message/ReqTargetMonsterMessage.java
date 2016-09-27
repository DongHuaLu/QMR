package com.game.task.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求任务中需要同步的怪物信息消息
 */
public class ReqTargetMonsterMessage extends Message{

	//服务器ID
	private int serverId;
	
	//角色ID
	private long reqRoleId;
	
	//怪物模型ID
	private List<Integer> modelIds = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务器ID
		writeInt(buf, this.serverId);
		//角色ID
		writeLong(buf, this.reqRoleId);
		//怪物模型ID
		writeShort(buf, modelIds.size());
		for (int i = 0; i < modelIds.size(); i++) {
			writeInt(buf, modelIds.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务器ID
		this.serverId = readInt(buf);
		//角色ID
		this.reqRoleId = readLong(buf);
		//怪物模型ID
		int modelIds_length = readShort(buf);
		for (int i = 0; i < modelIds_length; i++) {
			modelIds.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 服务器ID
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器ID
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getReqRoleId(){
		return reqRoleId;
	}
	
	/**
	 * set 角色ID
	 */
	public void setReqRoleId(long reqRoleId){
		this.reqRoleId = reqRoleId;
	}
	
	/**
	 * get 怪物模型ID
	 * @return 
	 */
	public List<Integer> getModelIds(){
		return modelIds;
	}
	
	/**
	 * set 怪物模型ID
	 */
	public void setModelIds(List<Integer> modelIds){
		this.modelIds = modelIds;
	}
	
	
	@Override
	public int getId() {
		return 120301;
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
		//服务器ID
		buf.append("serverId:" + serverId +",");
		//角色ID
		buf.append("reqRoleId:" + reqRoleId +",");
		//怪物模型ID
		buf.append("modelIds:{");
		for (int i = 0; i < modelIds.size(); i++) {
			buf.append(modelIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}