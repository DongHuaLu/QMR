package com.game.marriage.structs;

import com.game.object.GameObject;
import com.game.player.structs.Player;
/**配偶
 * 
 * @author zhangrong
 *
 */
public class Spouse  extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1236191496819676023L;
	
	public Spouse(){
		
	}
	
	public Spouse(Player player){
		this.playerid = player.getId();
		this.name = player.getName();
		this.level = player.getLevel();
		
	}
	
	
	
	private long playerid;
	//结婚的玩家名字
	private String name;
	//玩家等级
	private int level;
	//婚宴离线获得经验
	private int totalexp;
	//婚宴离线获得真气
	private int totalzhenqi;
	//婚宴通知 (存的是申请时间)
	private int noticewedding;
	//更换戒指通知 (ID)
	private int noticering;
	
	public long getPlayerid() {
		return playerid;
	}
	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTotalexp() {
		return totalexp;
	}
	public void setTotalexp(int totalexp) {
		this.totalexp = totalexp;
	}
	public int getTotalzhenqi() {
		return totalzhenqi;
	}
	public void setTotalzhenqi(int totalzhenqi) {
		this.totalzhenqi = totalzhenqi;
	}

	
	/**
	 * 婚宴通知 
	 */
	public int getNoticewedding() {
		return noticewedding;
	}
	
	/**
	 * 婚宴通知
	 */
	public void setNoticewedding(int noticewedding) {
		this.noticewedding = noticewedding;
	}
/**更换戒指通知 (ID)
 * 
 * @return
 */
	public int getNoticering() {
		return noticering;
	}
/**更换戒指通知 (ID)
 * 
 * @param noticering
 */
	public void setNoticering(int noticering) {
		this.noticering = noticering;
	}
	
	
	
	
	
	
	

}
