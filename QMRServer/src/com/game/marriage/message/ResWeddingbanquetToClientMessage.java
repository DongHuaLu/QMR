package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 弹出参加婚宴面板消息
 */
public class ResWeddingbanquetToClientMessage extends Message{

	//新郎名字
	private String bridegroom;
	
	//新娘名字
	private String bride;
	
	//食物数量
	private int foodnum;
	
	//结婚id
	private long marriageid;
	
	//NPC唯一id
	private long npcid;
	
	//今日累计送出红包
	private int totalredenvelope;
	
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
		//结婚id
		writeLong(buf, this.marriageid);
		//NPC唯一id
		writeLong(buf, this.npcid);
		//今日累计送出红包
		writeInt(buf, this.totalredenvelope);
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
		//结婚id
		this.marriageid = readLong(buf);
		//NPC唯一id
		this.npcid = readLong(buf);
		//今日累计送出红包
		this.totalredenvelope = readInt(buf);
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
	 * get 今日累计送出红包
	 * @return 
	 */
	public int getTotalredenvelope(){
		return totalredenvelope;
	}
	
	/**
	 * set 今日累计送出红包
	 */
	public void setTotalredenvelope(int totalredenvelope){
		this.totalredenvelope = totalredenvelope;
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
		return 163112;
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
		//结婚id
		buf.append("marriageid:" + marriageid +",");
		//NPC唯一id
		buf.append("npcid:" + npcid +",");
		//今日累计送出红包
		buf.append("totalredenvelope:" + totalredenvelope +",");
		//婚宴类型，1普通，2大型，3豪华
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}