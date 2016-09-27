package com.game.longyuan.structs;

import java.util.HashMap;

import com.game.object.GameObject;

public class LongYuanData extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1096502753282509482L;
	//龙元心法阶段
	private byte lysection;
	//龙元心法(当前阶段下的星位)
	private byte lylevel;
	//龙元心法经验分享人数
	private HashMap<String,Integer> lyexpshare = new HashMap<String,Integer>();
	//龙元心法分享经验总量
	private HashMap<String,Integer> lyshareexpnum = new HashMap<String,Integer>();
	//龙元心法单个星位修炼失败次数
	private short lyfailnum;
	//心法每天最大可加经验（总计可分享星图暴气经验）
	private int lymaxexp;
	//玩家累计已获得的暴气分享经验总量
	private int lyobtainexp;
	//最后上线天
	private int onlineday;
	//最后离线天
	private int offlineday;
	
	/**
	 * 龙元心法阶段(星图)
	 * @return
	 */
	public byte getLysection() {
		return lysection;
	}
	public void setLysection(byte lysection) {
		this.lysection = lysection;
	}
	/**
	 * 龙元心法(当前阶段下的星位)
	 * @return
	 */
	public byte getLylevel() {
		return lylevel;
	}
	public void setLylevel(byte lylevel) {
		this.lylevel = lylevel;
	}
	
	/**
	 * 龙元心法经验分享人数
	 * @return
	 */
	public HashMap<String,Integer> getLyexpshare() {
		return lyexpshare;
	}
	public void setLyexpshare(HashMap<String,Integer> lyexpshare) {
		this.lyexpshare = lyexpshare;
	}
	
	/**
	 * 龙元心法单个星位修炼失败次数
	 * @return
	 */
	public short getLyfailnum() {
		return lyfailnum;
	}
	public void setLyfailnum(short lyfailnum) {
		this.lyfailnum = lyfailnum;
	}
	/**
	 * 心法每天最大可加经验（总计可分享星图暴气经验）
	 * @return
	 */
	public int getLymaxexp() {
		return lymaxexp;
	}
	public void setLymaxexp(int lymaxexp) {
		this.lymaxexp = lymaxexp;
	}
	
	/**玩家累计已获得的暴气分享经验总量
	 * 
	 * @return
	 */
	public int getLyobtainexp() {
		return lyobtainexp;
	}
	
	
	public void setLyobtainexp(int lyobtainexp) {
		this.lyobtainexp = lyobtainexp;
	}
	
	
	
	/**最后上线 天
	 * 
	 * @return
	 */
	public int getOnlineday() {
		return onlineday;
	}
	
	public void setOnlineday(int onlineday) {
		this.onlineday = onlineday;
	}
	
	/**最后离线  天
	 * 
	 * @return
	 */
	public int getOfflineday() {
		return offlineday;
	}
	public void setOfflineday(int offlineday) {
		this.offlineday = offlineday;
	}
	
	/**心法分享经验总量（显示用）
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getLyshareexpnum() {
		return lyshareexpnum;
	}
	public void setLyshareexpnum(HashMap<String, Integer> lyshareexpnum) {
		this.lyshareexpnum = lyshareexpnum;
	}
	
}
