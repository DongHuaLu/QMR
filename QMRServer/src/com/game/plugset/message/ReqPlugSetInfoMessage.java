package com.game.plugset.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求设置 自动挂机内容消息
 */
public class ReqPlugSetInfoMessage extends Message{

	//总设置参数
	private int parameter;
	
	//血量百分比
	private byte hpper;
	
	//内力百分比
	private byte mpper;
	
	//体力百分比
	private byte spper;
	
	//自动出售道具颜色类型
	private byte itemcolor;
	
	//自动出售道具强化等级
	private byte itemlv;
	
	//自动打怪技能
	private int skillid;
	
	//挂机范围
	private byte range;
	
	//拾取道具类型
	private byte pickup;
	
	//挂机时间 （分钟）
	private int time;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//总设置参数
		writeInt(buf, this.parameter);
		//血量百分比
		writeByte(buf, this.hpper);
		//内力百分比
		writeByte(buf, this.mpper);
		//体力百分比
		writeByte(buf, this.spper);
		//自动出售道具颜色类型
		writeByte(buf, this.itemcolor);
		//自动出售道具强化等级
		writeByte(buf, this.itemlv);
		//自动打怪技能
		writeInt(buf, this.skillid);
		//挂机范围
		writeByte(buf, this.range);
		//拾取道具类型
		writeByte(buf, this.pickup);
		//挂机时间 （分钟）
		writeInt(buf, this.time);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//总设置参数
		this.parameter = readInt(buf);
		//血量百分比
		this.hpper = readByte(buf);
		//内力百分比
		this.mpper = readByte(buf);
		//体力百分比
		this.spper = readByte(buf);
		//自动出售道具颜色类型
		this.itemcolor = readByte(buf);
		//自动出售道具强化等级
		this.itemlv = readByte(buf);
		//自动打怪技能
		this.skillid = readInt(buf);
		//挂机范围
		this.range = readByte(buf);
		//拾取道具类型
		this.pickup = readByte(buf);
		//挂机时间 （分钟）
		this.time = readInt(buf);
		return true;
	}
	
	/**
	 * get 总设置参数
	 * @return 
	 */
	public int getParameter(){
		return parameter;
	}
	
	/**
	 * set 总设置参数
	 */
	public void setParameter(int parameter){
		this.parameter = parameter;
	}
	
	/**
	 * get 血量百分比
	 * @return 
	 */
	public byte getHpper(){
		return hpper;
	}
	
	/**
	 * set 血量百分比
	 */
	public void setHpper(byte hpper){
		this.hpper = hpper;
	}
	
	/**
	 * get 内力百分比
	 * @return 
	 */
	public byte getMpper(){
		return mpper;
	}
	
	/**
	 * set 内力百分比
	 */
	public void setMpper(byte mpper){
		this.mpper = mpper;
	}
	
	/**
	 * get 体力百分比
	 * @return 
	 */
	public byte getSpper(){
		return spper;
	}
	
	/**
	 * set 体力百分比
	 */
	public void setSpper(byte spper){
		this.spper = spper;
	}
	
	/**
	 * get 自动出售道具颜色类型
	 * @return 
	 */
	public byte getItemcolor(){
		return itemcolor;
	}
	
	/**
	 * set 自动出售道具颜色类型
	 */
	public void setItemcolor(byte itemcolor){
		this.itemcolor = itemcolor;
	}
	
	/**
	 * get 自动出售道具强化等级
	 * @return 
	 */
	public byte getItemlv(){
		return itemlv;
	}
	
	/**
	 * set 自动出售道具强化等级
	 */
	public void setItemlv(byte itemlv){
		this.itemlv = itemlv;
	}
	
	/**
	 * get 自动打怪技能
	 * @return 
	 */
	public int getSkillid(){
		return skillid;
	}
	
	/**
	 * set 自动打怪技能
	 */
	public void setSkillid(int skillid){
		this.skillid = skillid;
	}
	
	/**
	 * get 挂机范围
	 * @return 
	 */
	public byte getRange(){
		return range;
	}
	
	/**
	 * set 挂机范围
	 */
	public void setRange(byte range){
		this.range = range;
	}
	
	/**
	 * get 拾取道具类型
	 * @return 
	 */
	public byte getPickup(){
		return pickup;
	}
	
	/**
	 * set 拾取道具类型
	 */
	public void setPickup(byte pickup){
		this.pickup = pickup;
	}
	
	/**
	 * get 挂机时间 （分钟）
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 挂机时间 （分钟）
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	
	@Override
	public int getId() {
		return 131201;
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
		//总设置参数
		buf.append("parameter:" + parameter +",");
		//血量百分比
		buf.append("hpper:" + hpper +",");
		//内力百分比
		buf.append("mpper:" + mpper +",");
		//体力百分比
		buf.append("spper:" + spper +",");
		//自动出售道具颜色类型
		buf.append("itemcolor:" + itemcolor +",");
		//自动出售道具强化等级
		buf.append("itemlv:" + itemlv +",");
		//自动打怪技能
		buf.append("skillid:" + skillid +",");
		//挂机范围
		buf.append("range:" + range +",");
		//拾取道具类型
		buf.append("pickup:" + pickup +",");
		//挂机时间 （分钟）
		buf.append("time:" + time +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}