package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 弹出食用婚宴界面消息
 */
public class ResEdibleInfoToClientMessage extends Message{

	//新郎名字
	private String bridegroom;
	
	//新娘名字
	private String bride;
	
	//食物数量
	private int foodnum;
	
	//今日累计获得真气
	private int totalzhenqi;
	
	//今日累计获得经验
	private int totalexp;
	
	//结婚id
	private long marriageid;
	
	//NPC唯一id
	private long npcid;
	
	//婚宴类型，1普通，2大型，3豪华
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//新郎名字
		writeString(buf, this.bridegroom);
		//新娘名字
		writeString(buf, this.bride);
		//食物数量
		writeInt(buf, this.foodnum);
		//今日累计获得真气
		writeInt(buf, this.totalzhenqi);
		//今日累计获得经验
		writeInt(buf, this.totalexp);
		//结婚id
		writeLong(buf, this.marriageid);
		//NPC唯一id
		writeLong(buf, this.npcid);
		//婚宴类型，1普通，2大型，3豪华
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//新郎名字
		this.bridegroom = readString(buf);
		//新娘名字
		this.bride = readString(buf);
		//食物数量
		this.foodnum = readInt(buf);
		//今日累计获得真气
		this.totalzhenqi = readInt(buf);
		//今日累计获得经验
		this.totalexp = readInt(buf);
		//结婚id
		this.marriageid = readLong(buf);
		//NPC唯一id
		this.npcid = readLong(buf);
		//婚宴类型，1普通，2大型，3豪华
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 新郎名字
	 * @return 
	 */
	public String getBridegroom(){
		return bridegroom;
	}
	
	/**
	 * set 新郎名字
	 */
	public void setBridegroom(String bridegroom){
		this.bridegroom = bridegroom;
	}
	
	/**
	 * get 新娘名字
	 * @return 
	 */
	public String getBride(){
		return bride;
	}
	
	/**
	 * set 新娘名字
	 */
	public void setBride(String bride){
		this.bride = bride;
	}
	
	/**
	 * get 食物数量
	 * @return 
	 */
	public int getFoodnum(){
		return foodnum;
	}
	
	/**
	 * set 食物数量
	 */
	public void setFoodnum(int foodnum){
		this.foodnum = foodnum;
	}
	
	/**
	 * get 今日累计获得真气
	 * @return 
	 */
	public int getTotalzhenqi(){
		return totalzhenqi;
	}
	
	/**
	 * set 今日累计获得真气
	 */
	public void setTotalzhenqi(int totalzhenqi){
		this.totalzhenqi = totalzhenqi;
	}
	
	/**
	 * get 今日累计获得经验
	 * @return 
	 */
	public int getTotalexp(){
		return totalexp;
	}
	
	/**
	 * set 今日累计获得经验
	 */
	public void setTotalexp(int totalexp){
		this.totalexp = totalexp;
	}
	
	/**
	 * get 结婚id
	 * @return 
	 */
	public long getMarriageid(){
		return marriageid;
	}
	
	/**
	 * set 结婚id
	 */
	public void setMarriageid(long marriageid){
		this.marriageid = marriageid;
	}
	
	/**
	 * get NPC唯一id
	 * @return 
	 */
	public long getNpcid(){
		return npcid;
	}
	
	/**
	 * set NPC唯一id
	 */
	public void setNpcid(long npcid){
		this.npcid = npcid;
	}
	
	/**
	 * get 婚宴类型，1普通，2大型，3豪华
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 婚宴类型，1普通，2大型，3豪华
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 163113;
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
		//新郎名字
		if(this.bridegroom!=null) buf.append("bridegroom:" + bridegroom.toString() +",");
		//新娘名字
		if(this.bride!=null) buf.append("bride:" + bride.toString() +",");
		//食物数量
		buf.append("foodnum:" + foodnum +",");
		//今日累计获得真气
		buf.append("totalzhenqi:" + totalzhenqi +",");
		//今日累计获得经验
		buf.append("totalexp:" + totalexp +",");
		//结婚id
		buf.append("marriageid:" + marriageid +",");
		//NPC唯一id
		buf.append("npcid:" + npcid +",");
		//婚宴类型，1普通，2大型，3豪华
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}