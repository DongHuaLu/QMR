package com.game.skill.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 学习技能消息
 */
public class StudySkillMessage extends Message{

	//技能模板Id
	private int skillModelId;
	
	//技能书Id
	private long bookId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能模板Id
		writeInt(buf, this.skillModelId);
		//技能书Id
		writeLong(buf, this.bookId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能模板Id
		this.skillModelId = readInt(buf);
		//技能书Id
		this.bookId = readLong(buf);
		return true;
	}
	
	/**
	 * get 技能模板Id
	 * @return 
	 */
	public int getSkillModelId(){
		return skillModelId;
	}
	
	/**
	 * set 技能模板Id
	 */
	public void setSkillModelId(int skillModelId){
		this.skillModelId = skillModelId;
	}
	
	/**
	 * get 技能书Id
	 * @return 
	 */
	public long getBookId(){
		return bookId;
	}
	
	/**
	 * set 技能书Id
	 */
	public void setBookId(long bookId){
		this.bookId = bookId;
	}
	
	
	@Override
	public int getId() {
		return 107201;
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
		//技能模板Id
		buf.append("skillModelId:" + skillModelId +",");
		//技能书Id
		buf.append("bookId:" + bookId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}