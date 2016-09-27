package com.game.signwage.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 签到信息
 */
public class SignInfo extends Bean {

	//当前年
	private int year;
	
	//当前月
	private int month;
	
	//当前天
	private int day;
	
	//签到日期
	private List<Integer> daylist = new ArrayList<Integer>();
	//签到奖励列表
	private List<Integer> award = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前年
		writeInt(buf, this.year);
		//当前月
		writeInt(buf, this.month);
		//当前天
		writeInt(buf, this.day);
		//签到日期
		writeShort(buf, daylist.size());
		for (int i = 0; i < daylist.size(); i++) {
			writeInt(buf, daylist.get(i));
		}
		//签到奖励列表
		writeShort(buf, award.size());
		for (int i = 0; i < award.size(); i++) {
			writeInt(buf, award.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前年
		this.year = readInt(buf);
		//当前月
		this.month = readInt(buf);
		//当前天
		this.day = readInt(buf);
		//签到日期
		int daylist_length = readShort(buf);
		for (int i = 0; i < daylist_length; i++) {
			daylist.add(readInt(buf));
		}
		//签到奖励列表
		int award_length = readShort(buf);
		for (int i = 0; i < award_length; i++) {
			award.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 当前年
	 * @return 
	 */
	public int getYear(){
		return year;
	}
	
	/**
	 * set 当前年
	 */
	public void setYear(int year){
		this.year = year;
	}
	
	/**
	 * get 当前月
	 * @return 
	 */
	public int getMonth(){
		return month;
	}
	
	/**
	 * set 当前月
	 */
	public void setMonth(int month){
		this.month = month;
	}
	
	/**
	 * get 当前天
	 * @return 
	 */
	public int getDay(){
		return day;
	}
	
	/**
	 * set 当前天
	 */
	public void setDay(int day){
		this.day = day;
	}
	
	/**
	 * get 签到日期
	 * @return 
	 */
	public List<Integer> getDaylist(){
		return daylist;
	}
	
	/**
	 * set 签到日期
	 */
	public void setDaylist(List<Integer> daylist){
		this.daylist = daylist;
	}
	
	/**
	 * get 签到奖励列表
	 * @return 
	 */
	public List<Integer> getAward(){
		return award;
	}
	
	/**
	 * set 签到奖励列表
	 */
	public void setAward(List<Integer> award){
		this.award = award;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//当前年
		buf.append("year:" + year +",");
		//当前月
		buf.append("month:" + month +",");
		//当前天
		buf.append("day:" + day +",");
		//签到日期
		buf.append("daylist:{");
		for (int i = 0; i < daylist.size(); i++) {
			buf.append(daylist.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//签到奖励列表
		buf.append("award:{");
		for (int i = 0; i < award.size(); i++) {
			buf.append(award.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}