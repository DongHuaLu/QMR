package com.game.marriage.structs;

import com.game.object.GameObject;

/**留言
 * 
 * @author zhangrong
 *
 */
public class LeaveMsg extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6815101298343319915L;
	//发布时间
	private long time ;
	//内容
	private String content ;
	//发布人
	private long playerid ;
	//对方是否已读
	private boolean alread = false;

	
	public LeaveMsg(){
		time = System.currentTimeMillis();
	}
	
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getPlayerid() {
		return playerid;
	}
	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}
	



	public boolean isAlread() {
		return alread;
	}


	public void setAlread(boolean alread) {
		this.alread = alread;
	}
	

	
	
}
