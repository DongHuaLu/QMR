package com.game.team.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 队员信息
 */
public class TeamMemberInfo extends Bean {

	//队员ID
	private long memberid;
	
	//队员名字
	private String membername;
	
	//队员等级
	private int memberlevel;
	
	//队员所在地图
	private int membermapid;
	
	//队员所在线路
	private int memberline;
	
	//队员所在坐标X
	private short mx;
	
	//队员所在坐标Y
	private short my;
	
	//地图唯一ID
	private long membermaponlyid;
	
	//造型信息
	private com.game.player.bean.PlayerAppearanceInfo appearanceInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队员ID
		writeLong(buf, this.memberid);
		//队员名字
		writeString(buf, this.membername);
		//队员等级
		writeInt(buf, this.memberlevel);
		//队员所在地图
		writeInt(buf, this.membermapid);
		//队员所在线路
		writeInt(buf, this.memberline);
		//队员所在坐标X
		writeShort(buf, this.mx);
		//队员所在坐标Y
		writeShort(buf, this.my);
		//地图唯一ID
		writeLong(buf, this.membermaponlyid);
		//造型信息
		writeBean(buf, this.appearanceInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队员ID
		this.memberid = readLong(buf);
		//队员名字
		this.membername = readString(buf);
		//队员等级
		this.memberlevel = readInt(buf);
		//队员所在地图
		this.membermapid = readInt(buf);
		//队员所在线路
		this.memberline = readInt(buf);
		//队员所在坐标X
		this.mx = readShort(buf);
		//队员所在坐标Y
		this.my = readShort(buf);
		//地图唯一ID
		this.membermaponlyid = readLong(buf);
		//造型信息
		this.appearanceInfo = (com.game.player.bean.PlayerAppearanceInfo)readBean(buf, com.game.player.bean.PlayerAppearanceInfo.class);
		return true;
	}
	
	/**
	 * get 队员ID
	 * @return 
	 */
	public long getMemberid(){
		return memberid;
	}
	
	/**
	 * set 队员ID
	 */
	public void setMemberid(long memberid){
		this.memberid = memberid;
	}
	
	/**
	 * get 队员名字
	 * @return 
	 */
	public String getMembername(){
		return membername;
	}
	
	/**
	 * set 队员名字
	 */
	public void setMembername(String membername){
		this.membername = membername;
	}
	
	/**
	 * get 队员等级
	 * @return 
	 */
	public int getMemberlevel(){
		return memberlevel;
	}
	
	/**
	 * set 队员等级
	 */
	public void setMemberlevel(int memberlevel){
		this.memberlevel = memberlevel;
	}
	
	/**
	 * get 队员所在地图
	 * @return 
	 */
	public int getMembermapid(){
		return membermapid;
	}
	
	/**
	 * set 队员所在地图
	 */
	public void setMembermapid(int membermapid){
		this.membermapid = membermapid;
	}
	
	/**
	 * get 队员所在线路
	 * @return 
	 */
	public int getMemberline(){
		return memberline;
	}
	
	/**
	 * set 队员所在线路
	 */
	public void setMemberline(int memberline){
		this.memberline = memberline;
	}
	
	/**
	 * get 队员所在坐标X
	 * @return 
	 */
	public short getMx(){
		return mx;
	}
	
	/**
	 * set 队员所在坐标X
	 */
	public void setMx(short mx){
		this.mx = mx;
	}
	
	/**
	 * get 队员所在坐标Y
	 * @return 
	 */
	public short getMy(){
		return my;
	}
	
	/**
	 * set 队员所在坐标Y
	 */
	public void setMy(short my){
		this.my = my;
	}
	
	/**
	 * get 地图唯一ID
	 * @return 
	 */
	public long getMembermaponlyid(){
		return membermaponlyid;
	}
	
	/**
	 * set 地图唯一ID
	 */
	public void setMembermaponlyid(long membermaponlyid){
		this.membermaponlyid = membermaponlyid;
	}
	
	/**
	 * get 造型信息
	 * @return 
	 */
	public com.game.player.bean.PlayerAppearanceInfo getAppearanceInfo(){
		return appearanceInfo;
	}
	
	/**
	 * set 造型信息
	 */
	public void setAppearanceInfo(com.game.player.bean.PlayerAppearanceInfo appearanceInfo){
		this.appearanceInfo = appearanceInfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//队员ID
		buf.append("memberid:" + memberid +",");
		//队员名字
		if(this.membername!=null) buf.append("membername:" + membername.toString() +",");
		//队员等级
		buf.append("memberlevel:" + memberlevel +",");
		//队员所在地图
		buf.append("membermapid:" + membermapid +",");
		//队员所在线路
		buf.append("memberline:" + memberline +",");
		//队员所在坐标X
		buf.append("mx:" + mx +",");
		//队员所在坐标Y
		buf.append("my:" + my +",");
		//地图唯一ID
		buf.append("membermaponlyid:" + membermaponlyid +",");
		//造型信息
		if(this.appearanceInfo!=null) buf.append("appearanceInfo:" + appearanceInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}