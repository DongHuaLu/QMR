package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摊位搜索消息
 */
public class ReqStallsSearchToWorldMessage extends Message{

	//角色ID
	private long playerid;
	
	//道具名称
	private String goodsname;
	
	//玩家名字
	private String playername;
	
	//搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
	private byte goldyuanbao;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色ID
		writeLong(buf, this.playerid);
		//道具名称
		writeString(buf, this.goodsname);
		//玩家名字
		writeString(buf, this.playername);
		//搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
		writeByte(buf, this.goldyuanbao);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色ID
		this.playerid = readLong(buf);
		//道具名称
		this.goodsname = readString(buf);
		//玩家名字
		this.playername = readString(buf);
		//搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
		this.goldyuanbao = readByte(buf);
		return true;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 角色ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 道具名称
	 * @return 
	 */
	public String getGoodsname(){
		return goodsname;
	}
	
	/**
	 * set 道具名称
	 */
	public void setGoodsname(String goodsname){
		this.goodsname = goodsname;
	}
	
	/**
	 * get 玩家名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玩家名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
	 * @return 
	 */
	public byte getGoldyuanbao(){
		return goldyuanbao;
	}
	
	/**
	 * set 搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
	 */
	public void setGoldyuanbao(byte goldyuanbao){
		this.goldyuanbao = goldyuanbao;
	}
	
	
	@Override
	public int getId() {
		return 123316;
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
		//角色ID
		buf.append("playerid:" + playerid +",");
		//道具名称
		if(this.goodsname!=null) buf.append("goodsname:" + goodsname.toString() +",");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//搜索金币或者元宝，0不搜索，1金币，2元宝，3两个都搜索
		buf.append("goldyuanbao:" + goldyuanbao +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}