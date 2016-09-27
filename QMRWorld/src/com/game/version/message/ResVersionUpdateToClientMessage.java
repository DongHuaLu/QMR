package com.game.version.message;

import com.game.version.bean.VersionResInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知在线玩家更新RES版本信息消息
 */
public class ResVersionUpdateToClientMessage extends Message{

	//RES文件版本信息表
	private List<VersionResInfo> versionlist = new ArrayList<VersionResInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//RES文件版本信息表
		writeShort(buf, versionlist.size());
		for (int i = 0; i < versionlist.size(); i++) {
			writeBean(buf, versionlist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//RES文件版本信息表
		int versionlist_length = readShort(buf);
		for (int i = 0; i < versionlist_length; i++) {
			versionlist.add((VersionResInfo)readBean(buf, VersionResInfo.class));
		}
		return true;
	}
	
	/**
	 * get RES文件版本信息表
	 * @return 
	 */
	public List<VersionResInfo> getVersionlist(){
		return versionlist;
	}
	
	/**
	 * set RES文件版本信息表
	 */
	public void setVersionlist(List<VersionResInfo> versionlist){
		this.versionlist = versionlist;
	}
	
	
	@Override
	public int getId() {
		return 202101;
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
		//RES文件版本信息表
		buf.append("versionlist:{");
		for (int i = 0; i < versionlist.size(); i++) {
			buf.append(versionlist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}