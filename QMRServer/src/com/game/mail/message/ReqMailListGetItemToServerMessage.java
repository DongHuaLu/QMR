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
 * 批量收取附件消息
 */
public class ReqMailListGetItemToServerMessage extends Message{

	//邮件Id列表
	private List<Long> mailidlist = new ArrayList<Long>();
	//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
	private long itemid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//邮件Id列表
		writeShort(buf, mailidlist.size());
		for (int i = 0; i < mailidlist.size(); i++) {
			writeLong(buf, mailidlist.get(i));
		}
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		writeLong(buf, this.itemid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//邮件Id列表
		int mailidlist_length = readShort(buf);
		for (int i = 0; i < mailidlist_length; i++) {
			mailidlist.add(readLong(buf));
		}
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		this.itemid = readLong(buf);
		return true;
	}
	
	/**
	 * get 邮件Id列表
	 * @return 
	 */
	public List<Long> getMailidlist(){
		return mailidlist;
	}
	
	/**
	 * set 邮件Id列表
	 */
	public void setMailidlist(List<Long> mailidlist){
		this.mailidlist = mailidlist;
	}
	
	/**
	 * get 要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
	 * @return 
	 */
	public long getItemid(){
		return itemid;
	}
	
	/**
	 * set 要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
	 */
	public void setItemid(long itemid){
		this.itemid = itemid;
	}
	
	
	@Override
	public int getId() {
		return 124158;
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
		//邮件Id列表
		buf.append("mailidlist:{");
		for (int i = 0; i < mailidlist.size(); i++) {
			buf.append(mailidlist.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		buf.append("itemid:" + itemid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}