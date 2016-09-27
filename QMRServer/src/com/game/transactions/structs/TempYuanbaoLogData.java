package com.game.transactions.structs;

import com.game.object.GameObject;


public class TempYuanbaoLogData extends GameObject {

	private static final long serialVersionUID = -1644253368134386357L;

	//和本人交易的玩家ID
	private long playerid;
	
	//玩家名字
	private String playername;
	
	//交易类型	 1面对面交易，2摆摊
	private byte type;
	
	//交易时间
	private long time;
	
	//交易道具的信息
	private ItemLogData itemlogdata;
	
	//交易的道具数量
	private int num;
	
	//获得金币数量
	private int goldnum;
	
	//获得元宝数量
	private int yuanbaonum;
	

	public long getPlayerid() {
		return playerid;
	}

	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getGoldnum() {
		return goldnum;
	}

	public void setGoldnum(int goldnum) {
		this.goldnum = goldnum;
	}

	public int getYuanbaonum() {
		return yuanbaonum;
	}

	public void setYuanbaonum(int yuanbaonum) {
		this.yuanbaonum = yuanbaonum;
	}
	
	
	/**保存临时元宝记录
	 * 
	 * @param name
	 * @param rid
	 * @param type
	 * @param iteminfo
	 * @param num
	 * @param goldnum
	 * @param yuanbaonum
	 */
	public void stTmpYuanbaoLog(String name,long rid ,byte type , ItemLogData itemlogdata,int num,int goldnum,int yuanbaonum){
		this.setPlayerid(rid);
		this.setPlayername(name);
		this.setItemlogdata(itemlogdata);
		this.setNum(num);
		this.setTime(System.currentTimeMillis()/1000);
		this.setType(type);
		this.setGoldnum(goldnum);
		this.setYuanbaonum(yuanbaonum);
	}


	
	
	
	
	public ItemLogData getItemlogdata() {
		return itemlogdata;
	}

	public void setItemlogdata(ItemLogData itemlogdata) {
		this.itemlogdata = itemlogdata;
	}
	
}
