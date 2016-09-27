package com.game.arrow.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 弓箭信息
 */
public class ArrowInfo extends Bean {

	//弓箭等阶
	private int arrowlv;
	
	//星斗信息
	private StarInfo starinfo;
	
	//箭支信息
	private BowInfo bowinfo;
	
	//战魂信息列表
	private List<FightSpiritInfo> fightspiritList = new ArrayList<FightSpiritInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//弓箭等阶
		writeInt(buf, this.arrowlv);
		//星斗信息
		writeBean(buf, this.starinfo);
		//箭支信息
		writeBean(buf, this.bowinfo);
		//战魂信息列表
		writeShort(buf, fightspiritList.size());
		for (int i = 0; i < fightspiritList.size(); i++) {
			writeBean(buf, fightspiritList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//弓箭等阶
		this.arrowlv = readInt(buf);
		//星斗信息
		this.starinfo = (StarInfo)readBean(buf, StarInfo.class);
		//箭支信息
		this.bowinfo = (BowInfo)readBean(buf, BowInfo.class);
		//战魂信息列表
		int fightspiritList_length = readShort(buf);
		for (int i = 0; i < fightspiritList_length; i++) {
			fightspiritList.add((FightSpiritInfo)readBean(buf, FightSpiritInfo.class));
		}
		return true;
	}
	
	/**
	 * get 弓箭等阶
	 * @return 
	 */
	public int getArrowlv(){
		return arrowlv;
	}
	
	/**
	 * set 弓箭等阶
	 */
	public void setArrowlv(int arrowlv){
		this.arrowlv = arrowlv;
	}
	
	/**
	 * get 星斗信息
	 * @return 
	 */
	public StarInfo getStarinfo(){
		return starinfo;
	}
	
	/**
	 * set 星斗信息
	 */
	public void setStarinfo(StarInfo starinfo){
		this.starinfo = starinfo;
	}
	
	/**
	 * get 箭支信息
	 * @return 
	 */
	public BowInfo getBowinfo(){
		return bowinfo;
	}
	
	/**
	 * set 箭支信息
	 */
	public void setBowinfo(BowInfo bowinfo){
		this.bowinfo = bowinfo;
	}
	
	/**
	 * get 战魂信息列表
	 * @return 
	 */
	public List<FightSpiritInfo> getFightspiritList(){
		return fightspiritList;
	}
	
	/**
	 * set 战魂信息列表
	 */
	public void setFightspiritList(List<FightSpiritInfo> fightspiritList){
		this.fightspiritList = fightspiritList;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//弓箭等阶
		buf.append("arrowlv:" + arrowlv +",");
		//星斗信息
		if(this.starinfo!=null) buf.append("starinfo:" + starinfo.toString() +",");
		//箭支信息
		if(this.bowinfo!=null) buf.append("bowinfo:" + bowinfo.toString() +",");
		//战魂信息列表
		buf.append("fightspiritList:{");
		for (int i = 0; i < fightspiritList.size(); i++) {
			buf.append(fightspiritList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}