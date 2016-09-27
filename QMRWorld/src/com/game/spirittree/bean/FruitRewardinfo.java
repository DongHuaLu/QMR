package com.game.spirittree.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 单个果实奖励
 */
public class FruitRewardinfo extends Bean {

	//奖励数据索引
	private int index;
	
	//道具模型ID，-1铜币，-2元宝，-3经验，-4真气
	private int itemModelid;
	
	//当前数量
	private int num;
	
	//总数量
	private int sum;
	
	//道具强化等级
	private int strenglevel;
	
	//奖励道具附加属性
	private List<TempAttributes> tempAttributes = new ArrayList<TempAttributes>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//奖励数据索引
		writeInt(buf, this.index);
		//道具模型ID，-1铜币，-2元宝，-3经验，-4真气
		writeInt(buf, this.itemModelid);
		//当前数量
		writeInt(buf, this.num);
		//总数量
		writeInt(buf, this.sum);
		//道具强化等级
		writeInt(buf, this.strenglevel);
		//奖励道具附加属性
		writeShort(buf, tempAttributes.size());
		for (int i = 0; i < tempAttributes.size(); i++) {
			writeBean(buf, tempAttributes.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//奖励数据索引
		this.index = readInt(buf);
		//道具模型ID，-1铜币，-2元宝，-3经验，-4真气
		this.itemModelid = readInt(buf);
		//当前数量
		this.num = readInt(buf);
		//总数量
		this.sum = readInt(buf);
		//道具强化等级
		this.strenglevel = readInt(buf);
		//奖励道具附加属性
		int tempAttributes_length = readShort(buf);
		for (int i = 0; i < tempAttributes_length; i++) {
			tempAttributes.add((TempAttributes)readBean(buf, TempAttributes.class));
		}
		return true;
	}
	
	/**
	 * get 奖励数据索引
	 * @return 
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * set 奖励数据索引
	 */
	public void setIndex(int index){
		this.index = index;
	}
	
	/**
	 * get 道具模型ID，-1铜币，-2元宝，-3经验，-4真气
	 * @return 
	 */
	public int getItemModelid(){
		return itemModelid;
	}
	
	/**
	 * set 道具模型ID，-1铜币，-2元宝，-3经验，-4真气
	 */
	public void setItemModelid(int itemModelid){
		this.itemModelid = itemModelid;
	}
	
	/**
	 * get 当前数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 当前数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 总数量
	 * @return 
	 */
	public int getSum(){
		return sum;
	}
	
	/**
	 * set 总数量
	 */
	public void setSum(int sum){
		this.sum = sum;
	}
	
	/**
	 * get 道具强化等级
	 * @return 
	 */
	public int getStrenglevel(){
		return strenglevel;
	}
	
	/**
	 * set 道具强化等级
	 */
	public void setStrenglevel(int strenglevel){
		this.strenglevel = strenglevel;
	}
	
	/**
	 * get 奖励道具附加属性
	 * @return 
	 */
	public List<TempAttributes> getTempAttributes(){
		return tempAttributes;
	}
	
	/**
	 * set 奖励道具附加属性
	 */
	public void setTempAttributes(List<TempAttributes> tempAttributes){
		this.tempAttributes = tempAttributes;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//奖励数据索引
		buf.append("index:" + index +",");
		//道具模型ID，-1铜币，-2元宝，-3经验，-4真气
		buf.append("itemModelid:" + itemModelid +",");
		//当前数量
		buf.append("num:" + num +",");
		//总数量
		buf.append("sum:" + sum +",");
		//道具强化等级
		buf.append("strenglevel:" + strenglevel +",");
		//奖励道具附加属性
		buf.append("tempAttributes:{");
		for (int i = 0; i < tempAttributes.size(); i++) {
			buf.append(tempAttributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}