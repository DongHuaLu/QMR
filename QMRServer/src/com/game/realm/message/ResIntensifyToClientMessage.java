package com.game.realm.message;

import com.game.realm.bean.RealmInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回前端强化境界信息消息
 */
public class ResIntensifyToClientMessage extends Message{

	//境界信息
	private RealmInfo bealmInfo;
	
	//0失败，1成功
	private int result;
	
	//提示内容
	private String prompt;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//境界信息
		writeBean(buf, this.bealmInfo);
		//0失败，1成功
		writeInt(buf, this.result);
		//提示内容
		writeString(buf, this.prompt);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//境界信息
		this.bealmInfo = (RealmInfo)readBean(buf, RealmInfo.class);
		//0失败，1成功
		this.result = readInt(buf);
		//提示内容
		this.prompt = readString(buf);
		return true;
	}
	
	/**
	 * get 境界信息
	 * @return 
	 */
	public RealmInfo getBealmInfo(){
		return bealmInfo;
	}
	
	/**
	 * set 境界信息
	 */
	public void setBealmInfo(RealmInfo bealmInfo){
		this.bealmInfo = bealmInfo;
	}
	
	/**
	 * get 0失败，1成功
	 * @return 
	 */
	public int getResult(){
		return result;
	}
	
	/**
	 * set 0失败，1成功
	 */
	public void setResult(int result){
		this.result = result;
	}
	
	/**
	 * get 提示内容
	 * @return 
	 */
	public String getPrompt(){
		return prompt;
	}
	
	/**
	 * set 提示内容
	 */
	public void setPrompt(String prompt){
		this.prompt = prompt;
	}
	
	
	@Override
	public int getId() {
		return 158103;
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
		//境界信息
		if(this.bealmInfo!=null) buf.append("bealmInfo:" + bealmInfo.toString() +",");
		//0失败，1成功
		buf.append("result:" + result +",");
		//提示内容
		if(this.prompt!=null) buf.append("prompt:" + prompt.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}