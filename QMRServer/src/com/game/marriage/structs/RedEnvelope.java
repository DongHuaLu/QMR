package com.game.marriage.structs;

import com.game.marriage.bean.RedEnvelopeInfo;
import com.game.object.GameObject;

/**红包
 * 
 * @author zhangrong
 *
 */
public class RedEnvelope extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8608749204742871091L;
	//玩家id
	private long playerid;
	//玩家名字
	private String playername;
	//玩家等级
	private short playerlv;
	//红包铜币数量
	private int rednum;
	
	
	
	public RedEnvelopeInfo makeRedEnvelopeinfo(){
		RedEnvelopeInfo  redEnvelopeInfo = new RedEnvelopeInfo();
		redEnvelopeInfo.setPlayerid(getPlayerid());
		redEnvelopeInfo.setPlayerlv(getPlayerlv());
		redEnvelopeInfo.setPlayername(getPlayername());
		redEnvelopeInfo.setRednum(getRednum());
		return redEnvelopeInfo;
	}
	
	
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
	public short getPlayerlv() {
		return playerlv;
	}
	public void setPlayerlv(short playerlv) {
		this.playerlv = playerlv;
	}

	public int getRednum() {
		return rednum;
	}

	public void setRednum(int rednum) {
		this.rednum = rednum;
	}

	
}
