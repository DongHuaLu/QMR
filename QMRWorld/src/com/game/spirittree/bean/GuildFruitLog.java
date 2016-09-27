package com.game.spirittree.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 帮会神树操作日志
 */
public class GuildFruitLog extends Bean {

	//时间（秒）
	private int time;
	
	//操作的组包ID（果实名称）
	private int groupid;
	
	//类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
	private byte type;
	
	//浇水得到经验 或者 被偷补偿奖励
	private int exp;
	
	//对方ID
	private long otherid;
	
	//对方名字
	private String othername;
	
	//是否在线，0不在线，1在线
	private byte isonline;
	
	//最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
	private int itemmodelid;
	
	//偷取的数量（ID为经验才需要显示）
	private int itemnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//时间（秒）
		writeInt(buf, this.time);
		//操作的组包ID（果实名称）
		writeInt(buf, this.groupid);
		//类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
		writeByte(buf, this.type);
		//浇水得到经验 或者 被偷补偿奖励
		writeInt(buf, this.exp);
		//对方ID
		writeLong(buf, this.otherid);
		//对方名字
		writeString(buf, this.othername);
		//是否在线，0不在线，1在线
		writeByte(buf, this.isonline);
		//最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
		writeInt(buf, this.itemmodelid);
		//偷取的数量（ID为经验才需要显示）
		writeInt(buf, this.itemnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//时间（秒）
		this.time = readInt(buf);
		//操作的组包ID（果实名称）
		this.groupid = readInt(buf);
		//类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
		this.type = readByte(buf);
		//浇水得到经验 或者 被偷补偿奖励
		this.exp = readInt(buf);
		//对方ID
		this.otherid = readLong(buf);
		//对方名字
		this.othername = readString(buf);
		//是否在线，0不在线，1在线
		this.isonline = readByte(buf);
		//最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
		this.itemmodelid = readInt(buf);
		//偷取的数量（ID为经验才需要显示）
		this.itemnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 时间（秒）
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 时间（秒）
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 操作的组包ID（果实名称）
	 * @return 
	 */
	public int getGroupid(){
		return groupid;
	}
	
	/**
	 * set 操作的组包ID（果实名称）
	 */
	public void setGroupid(int groupid){
		this.groupid = groupid;
	}
	
	/**
	 * get 类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 浇水得到经验 或者 被偷补偿奖励
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 浇水得到经验 或者 被偷补偿奖励
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 对方ID
	 * @return 
	 */
	public long getOtherid(){
		return otherid;
	}
	
	/**
	 * set 对方ID
	 */
	public void setOtherid(long otherid){
		this.otherid = otherid;
	}
	
	/**
	 * get 对方名字
	 * @return 
	 */
	public String getOthername(){
		return othername;
	}
	
	/**
	 * set 对方名字
	 */
	public void setOthername(String othername){
		this.othername = othername;
	}
	
	/**
	 * get 是否在线，0不在线，1在线
	 * @return 
	 */
	public byte getIsonline(){
		return isonline;
	}
	
	/**
	 * set 是否在线，0不在线，1在线
	 */
	public void setIsonline(byte isonline){
		this.isonline = isonline;
	}
	
	/**
	 * get 最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
	 * @return 
	 */
	public int getItemmodelid(){
		return itemmodelid;
	}
	
	/**
	 * set 最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
	 */
	public void setItemmodelid(int itemmodelid){
		this.itemmodelid = itemmodelid;
	}
	
	/**
	 * get 偷取的数量（ID为经验才需要显示）
	 * @return 
	 */
	public int getItemnum(){
		return itemnum;
	}
	
	/**
	 * set 偷取的数量（ID为经验才需要显示）
	 */
	public void setItemnum(int itemnum){
		this.itemnum = itemnum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//时间（秒）
		buf.append("time:" + time +",");
		//操作的组包ID（果实名称）
		buf.append("groupid:" + groupid +",");
		//类型： 0抢收，1被抢收，2互助（浇水），3互助（被浇水）
		buf.append("type:" + type +",");
		//浇水得到经验 或者 被偷补偿奖励
		buf.append("exp:" + exp +",");
		//对方ID
		buf.append("otherid:" + otherid +",");
		//对方名字
		if(this.othername!=null) buf.append("othername:" + othername.toString() +",");
		//是否在线，0不在线，1在线
		buf.append("isonline:" + isonline +",");
		//最终奖励ID，-1铜币，-2元宝，-3真气，-4经验，>0道具
		buf.append("itemmodelid:" + itemmodelid +",");
		//偷取的数量（ID为经验才需要显示）
		buf.append("itemnum:" + itemnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}