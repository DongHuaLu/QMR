package com.game.biwudao.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 比武岛信息
 */
public class BiWuInfo extends Bean {

	//区域经验倍率
	private int areadouble;
	
	//每次可获得经验值
	private int availableexp;
	
	//每次可获得真气值
	private int availablezhenqi;
	
	//旗帜占领者帮会名字
	private String guildname;
	
	//旗帜占领者帮会id
	private long guildid;
	
	//累计获得经验
	private int totalexp;
	
	//累计获得真气
	private int totalzhenqi;
	
	//累计获得军功
	private int totalrank;
	
	//累计开启宝箱
	private int totalBox;
	
	//活动剩余时间(秒)
	private int surplustime;
	
	//夺旗剩余冷却时间（秒）
	private int flagcooldown;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//区域经验倍率
		writeInt(buf, this.areadouble);
		//每次可获得经验值
		writeInt(buf, this.availableexp);
		//每次可获得真气值
		writeInt(buf, this.availablezhenqi);
		//旗帜占领者帮会名字
		writeString(buf, this.guildname);
		//旗帜占领者帮会id
		writeLong(buf, this.guildid);
		//累计获得经验
		writeInt(buf, this.totalexp);
		//累计获得真气
		writeInt(buf, this.totalzhenqi);
		//累计获得军功
		writeInt(buf, this.totalrank);
		//累计开启宝箱
		writeInt(buf, this.totalBox);
		//活动剩余时间(秒)
		writeInt(buf, this.surplustime);
		//夺旗剩余冷却时间（秒）
		writeInt(buf, this.flagcooldown);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//区域经验倍率
		this.areadouble = readInt(buf);
		//每次可获得经验值
		this.availableexp = readInt(buf);
		//每次可获得真气值
		this.availablezhenqi = readInt(buf);
		//旗帜占领者帮会名字
		this.guildname = readString(buf);
		//旗帜占领者帮会id
		this.guildid = readLong(buf);
		//累计获得经验
		this.totalexp = readInt(buf);
		//累计获得真气
		this.totalzhenqi = readInt(buf);
		//累计获得军功
		this.totalrank = readInt(buf);
		//累计开启宝箱
		this.totalBox = readInt(buf);
		//活动剩余时间(秒)
		this.surplustime = readInt(buf);
		//夺旗剩余冷却时间（秒）
		this.flagcooldown = readInt(buf);
		return true;
	}
	
	/**
	 * get 区域经验倍率
	 * @return 
	 */
	public int getAreadouble(){
		return areadouble;
	}
	
	/**
	 * set 区域经验倍率
	 */
	public void setAreadouble(int areadouble){
		this.areadouble = areadouble;
	}
	
	/**
	 * get 每次可获得经验值
	 * @return 
	 */
	public int getAvailableexp(){
		return availableexp;
	}
	
	/**
	 * set 每次可获得经验值
	 */
	public void setAvailableexp(int availableexp){
		this.availableexp = availableexp;
	}
	
	/**
	 * get 每次可获得真气值
	 * @return 
	 */
	public int getAvailablezhenqi(){
		return availablezhenqi;
	}
	
	/**
	 * set 每次可获得真气值
	 */
	public void setAvailablezhenqi(int availablezhenqi){
		this.availablezhenqi = availablezhenqi;
	}
	
	/**
	 * get 旗帜占领者帮会名字
	 * @return 
	 */
	public String getGuildname(){
		return guildname;
	}
	
	/**
	 * set 旗帜占领者帮会名字
	 */
	public void setGuildname(String guildname){
		this.guildname = guildname;
	}
	
	/**
	 * get 旗帜占领者帮会id
	 * @return 
	 */
	public long getGuildid(){
		return guildid;
	}
	
	/**
	 * set 旗帜占领者帮会id
	 */
	public void setGuildid(long guildid){
		this.guildid = guildid;
	}
	
	/**
	 * get 累计获得经验
	 * @return 
	 */
	public int getTotalexp(){
		return totalexp;
	}
	
	/**
	 * set 累计获得经验
	 */
	public void setTotalexp(int totalexp){
		this.totalexp = totalexp;
	}
	
	/**
	 * get 累计获得真气
	 * @return 
	 */
	public int getTotalzhenqi(){
		return totalzhenqi;
	}
	
	/**
	 * set 累计获得真气
	 */
	public void setTotalzhenqi(int totalzhenqi){
		this.totalzhenqi = totalzhenqi;
	}
	
	/**
	 * get 累计获得军功
	 * @return 
	 */
	public int getTotalrank(){
		return totalrank;
	}
	
	/**
	 * set 累计获得军功
	 */
	public void setTotalrank(int totalrank){
		this.totalrank = totalrank;
	}
	
	/**
	 * get 累计开启宝箱
	 * @return 
	 */
	public int getTotalBox(){
		return totalBox;
	}
	
	/**
	 * set 累计开启宝箱
	 */
	public void setTotalBox(int totalBox){
		this.totalBox = totalBox;
	}
	
	/**
	 * get 活动剩余时间(秒)
	 * @return 
	 */
	public int getSurplustime(){
		return surplustime;
	}
	
	/**
	 * set 活动剩余时间(秒)
	 */
	public void setSurplustime(int surplustime){
		this.surplustime = surplustime;
	}
	
	/**
	 * get 夺旗剩余冷却时间（秒）
	 * @return 
	 */
	public int getFlagcooldown(){
		return flagcooldown;
	}
	
	/**
	 * set 夺旗剩余冷却时间（秒）
	 */
	public void setFlagcooldown(int flagcooldown){
		this.flagcooldown = flagcooldown;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//区域经验倍率
		buf.append("areadouble:" + areadouble +",");
		//每次可获得经验值
		buf.append("availableexp:" + availableexp +",");
		//每次可获得真气值
		buf.append("availablezhenqi:" + availablezhenqi +",");
		//旗帜占领者帮会名字
		if(this.guildname!=null) buf.append("guildname:" + guildname.toString() +",");
		//旗帜占领者帮会id
		buf.append("guildid:" + guildid +",");
		//累计获得经验
		buf.append("totalexp:" + totalexp +",");
		//累计获得真气
		buf.append("totalzhenqi:" + totalzhenqi +",");
		//累计获得军功
		buf.append("totalrank:" + totalrank +",");
		//累计开启宝箱
		buf.append("totalBox:" + totalBox +",");
		//活动剩余时间(秒)
		buf.append("surplustime:" + surplustime +",");
		//夺旗剩余冷却时间（秒）
		buf.append("flagcooldown:" + flagcooldown +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}