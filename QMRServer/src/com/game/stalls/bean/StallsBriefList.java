package com.game.stalls.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 全部摊位简要信息列表（供前端排序用）
 */
public class StallsBriefList extends Bean {

	//索引起点
	private int indexlittle;
	
	//索引终点
	private int indexLarge;
	
	//本国在线摊位总数
	private short stallssnum;
	
	//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
	private byte type;
	
	//摊位物品信息列表
	private List<StallsBriefInfo> stallsbrieflist = new ArrayList<StallsBriefInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//索引起点
		writeInt(buf, this.indexlittle);
		//索引终点
		writeInt(buf, this.indexLarge);
		//本国在线摊位总数
		writeShort(buf, this.stallssnum);
		//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
		writeByte(buf, this.type);
		//摊位物品信息列表
		writeShort(buf, stallsbrieflist.size());
		for (int i = 0; i < stallsbrieflist.size(); i++) {
			writeBean(buf, stallsbrieflist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//索引起点
		this.indexlittle = readInt(buf);
		//索引终点
		this.indexLarge = readInt(buf);
		//本国在线摊位总数
		this.stallssnum = readShort(buf);
		//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
		this.type = readByte(buf);
		//摊位物品信息列表
		int stallsbrieflist_length = readShort(buf);
		for (int i = 0; i < stallsbrieflist_length; i++) {
			stallsbrieflist.add((StallsBriefInfo)readBean(buf, StallsBriefInfo.class));
		}
		return true;
	}
	
	/**
	 * get 索引起点
	 * @return 
	 */
	public int getIndexlittle(){
		return indexlittle;
	}
	
	/**
	 * set 索引起点
	 */
	public void setIndexlittle(int indexlittle){
		this.indexlittle = indexlittle;
	}
	
	/**
	 * get 索引终点
	 * @return 
	 */
	public int getIndexLarge(){
		return indexLarge;
	}
	
	/**
	 * set 索引终点
	 */
	public void setIndexLarge(int indexLarge){
		this.indexLarge = indexLarge;
	}
	
	/**
	 * get 本国在线摊位总数
	 * @return 
	 */
	public short getStallssnum(){
		return stallssnum;
	}
	
	/**
	 * set 本国在线摊位总数
	 */
	public void setStallssnum(short stallssnum){
		this.stallssnum = stallssnum;
	}
	
	/**
	 * get 按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 摊位物品信息列表
	 * @return 
	 */
	public List<StallsBriefInfo> getStallsbrieflist(){
		return stallsbrieflist;
	}
	
	/**
	 * set 摊位物品信息列表
	 */
	public void setStallsbrieflist(List<StallsBriefInfo> stallsbrieflist){
		this.stallsbrieflist = stallsbrieflist;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//索引起点
		buf.append("indexlittle:" + indexlittle +",");
		//索引终点
		buf.append("indexLarge:" + indexLarge +",");
		//本国在线摊位总数
		buf.append("stallssnum:" + stallssnum +",");
		//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
		buf.append("type:" + type +",");
		//摊位物品信息列表
		buf.append("stallsbrieflist:{");
		for (int i = 0; i < stallsbrieflist.size(); i++) {
			buf.append(stallsbrieflist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}