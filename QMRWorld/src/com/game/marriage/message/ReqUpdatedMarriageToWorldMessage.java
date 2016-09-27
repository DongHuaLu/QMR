package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器更新婚姻信息消息
 */
public class ReqUpdatedMarriageToWorldMessage extends Message{

	//结婚id
	private long marriageid;
	
	//新郎名字
	private String bridegroom;
	
	//新娘名字
	private String bride;
	
	//新郎id
	private long bridegroomid;
	
	//新娘id
	private long brideid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//结婚id
		writeLong(buf, this.marriageid);
		//新郎名字
		writeString(buf, this.bridegroom);
		//新娘名字
		writeString(buf, this.bride);
		//新郎id
		writeLong(buf, this.bridegroomid);
		//新娘id
		writeLong(buf, this.brideid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//结婚id
		this.marriageid = readLong(buf);
		//新郎名字
		this.bridegroom = readString(buf);
		//新娘名字
		this.bride = readString(buf);
		//新郎id
		this.bridegroomid = readLong(buf);
		//新娘id
		this.brideid = readLong(buf);
		return true;
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
	 * get 新郎id
	 * @return 
	 */
	public long getBridegroomid(){
		return bridegroomid;
	}
	
	/**
	 * set 新郎id
	 */
	public void setBridegroomid(long bridegroomid){
		this.bridegroomid = bridegroomid;
	}
	
	/**
	 * get 新娘id
	 * @return 
	 */
	public long getBrideid(){
		return brideid;
	}
	
	/**
	 * set 新娘id
	 */
	public void setBrideid(long brideid){
		this.brideid = brideid;
	}
	
	
	@Override
	public int getId() {
		return 163301;
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
		//结婚id
		buf.append("marriageid:" + marriageid +",");
		//新郎名字
		if(this.bridegroom!=null) buf.append("bridegroom:" + bridegroom.toString() +",");
		//新娘名字
		if(this.bride!=null) buf.append("bride:" + bride.toString() +",");
		//新郎id
		buf.append("bridegroomid:" + bridegroomid +",");
		//新娘id
		buf.append("brideid:" + brideid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}