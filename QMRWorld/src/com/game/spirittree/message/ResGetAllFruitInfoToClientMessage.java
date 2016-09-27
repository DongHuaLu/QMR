package com.game.spirittree.message;

import com.game.spirittree.bean.FruitInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送个人灵树全部果实消息消息
 */
public class ResGetAllFruitInfoToClientMessage extends Message{

	//果实信息列表
	private List<FruitInfo> fruitinfo = new ArrayList<FruitInfo>();
	//下一个果实成熟的时间
	private int nexttime;
	
	//下次仙露浇灌时间
	private int nextdew;
	
	//仙露浇灌次数
	private int dewnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//果实信息列表
		writeShort(buf, fruitinfo.size());
		for (int i = 0; i < fruitinfo.size(); i++) {
			writeBean(buf, fruitinfo.get(i));
		}
		//下一个果实成熟的时间
		writeInt(buf, this.nexttime);
		//下次仙露浇灌时间
		writeInt(buf, this.nextdew);
		//仙露浇灌次数
		writeInt(buf, this.dewnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//果实信息列表
		int fruitinfo_length = readShort(buf);
		for (int i = 0; i < fruitinfo_length; i++) {
			fruitinfo.add((FruitInfo)readBean(buf, FruitInfo.class));
		}
		//下一个果实成熟的时间
		this.nexttime = readInt(buf);
		//下次仙露浇灌时间
		this.nextdew = readInt(buf);
		//仙露浇灌次数
		this.dewnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 果实信息列表
	 * @return 
	 */
	public List<FruitInfo> getFruitinfo(){
		return fruitinfo;
	}
	
	/**
	 * set 果实信息列表
	 */
	public void setFruitinfo(List<FruitInfo> fruitinfo){
		this.fruitinfo = fruitinfo;
	}
	
	/**
	 * get 下一个果实成熟的时间
	 * @return 
	 */
	public int getNexttime(){
		return nexttime;
	}
	
	/**
	 * set 下一个果实成熟的时间
	 */
	public void setNexttime(int nexttime){
		this.nexttime = nexttime;
	}
	
	/**
	 * get 下次仙露浇灌时间
	 * @return 
	 */
	public int getNextdew(){
		return nextdew;
	}
	
	/**
	 * set 下次仙露浇灌时间
	 */
	public void setNextdew(int nextdew){
		this.nextdew = nextdew;
	}
	
	/**
	 * get 仙露浇灌次数
	 * @return 
	 */
	public int getDewnum(){
		return dewnum;
	}
	
	/**
	 * set 仙露浇灌次数
	 */
	public void setDewnum(int dewnum){
		this.dewnum = dewnum;
	}
	
	
	@Override
	public int getId() {
		return 133101;
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
		//果实信息列表
		buf.append("fruitinfo:{");
		for (int i = 0; i < fruitinfo.size(); i++) {
			buf.append(fruitinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//下一个果实成熟的时间
		buf.append("nexttime:" + nexttime +",");
		//下次仙露浇灌时间
		buf.append("nextdew:" + nextdew +",");
		//仙露浇灌次数
		buf.append("dewnum:" + dewnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}