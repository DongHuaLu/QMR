package com.game.shop.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 出售物品列表消息
 */
public class ReqSellItemsMessage extends Message{

	//物品Id
	private List<Long> itemId = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品Id
		writeShort(buf, itemId.size());
		for (int i = 0; i < itemId.size(); i++) {
			writeLong(buf, itemId.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品Id
		int itemId_length = readShort(buf);
		for (int i = 0; i < itemId_length; i++) {
			itemId.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 物品Id
	 * @return 
	 */
	public List<Long> getItemId(){
		return itemId;
	}
	
	/**
	 * set 物品Id
	 */
	public void setItemId(List<Long> itemId){
		this.itemId = itemId;
	}
	
	
	@Override
	public int getId() {
		return 105207;
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
		//物品Id
		buf.append("itemId:{");
		for (int i = 0; i < itemId.size(); i++) {
			buf.append(itemId.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}