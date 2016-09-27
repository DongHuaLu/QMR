package com.game.mail.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 删除邮件,删完会返回 ResMailQueryListToClient消息
 */
public class ReqMailDeleteMailToServerMessage extends Message{

	//1 全删除 全删的话后面不需要添写
	private byte btdeleteall;
	
	//或者 最大支持删除10封信 填写邮件ID
	private List<Long> deleteMailIdList = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//1 全删除 全删的话后面不需要添写
		writeByte(buf, this.btdeleteall);
		//或者 最大支持删除10封信 填写邮件ID
		writeShort(buf, deleteMailIdList.size());
		for (int i = 0; i < deleteMailIdList.size(); i++) {
			writeLong(buf, deleteMailIdList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//1 全删除 全删的话后面不需要添写
		this.btdeleteall = readByte(buf);
		//或者 最大支持删除10封信 填写邮件ID
		int deleteMailIdList_length = readShort(buf);
		for (int i = 0; i < deleteMailIdList_length; i++) {
			deleteMailIdList.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 1 全删除 全删的话后面不需要添写
	 * @return 
	 */
	public byte getBtdeleteall(){
		return btdeleteall;
	}
	
	/**
	 * set 1 全删除 全删的话后面不需要添写
	 */
	public void setBtdeleteall(byte btdeleteall){
		this.btdeleteall = btdeleteall;
	}
	
	/**
	 * get 或者 最大支持删除10封信 填写邮件ID
	 * @return 
	 */
	public List<Long> getDeleteMailIdList(){
		return deleteMailIdList;
	}
	
	/**
	 * set 或者 最大支持删除10封信 填写邮件ID
	 */
	public void setDeleteMailIdList(List<Long> deleteMailIdList){
		this.deleteMailIdList = deleteMailIdList;
	}
	
	
	@Override
	public int getId() {
		return 124155;
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
		//1 全删除 全删的话后面不需要添写
		buf.append("btdeleteall:" + btdeleteall +",");
		//或者 最大支持删除10封信 填写邮件ID
		buf.append("deleteMailIdList:{");
		for (int i = 0; i < deleteMailIdList.size(); i++) {
			buf.append(deleteMailIdList.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}