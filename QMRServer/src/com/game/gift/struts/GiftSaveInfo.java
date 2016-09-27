package com.game.gift.struts;

import com.game.object.GameObject;

/**
 * 礼包保存信息
 *
 * @author 杨鸿岚
 */
public class GiftSaveInfo extends GameObject{
	
	//记录时间
	private long recordtime;
		
	//延时时间
	private int time;
	
	//推广人数
	private int invite;
	
	//登入天数领取
	private int login;
	
	//周在线小时领取
	private int online_time;
	
	//签到奖励
	private int qiandao;
	
	//签到次数统计
	private int qiandao_count;

	public int getInvite() {
		return invite;
	}

	public void setInvite(int invite) {
		this.invite = invite;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getOnline_time() {
		return online_time;
	}

	public void setOnline_time(int online_time) {
		this.online_time = online_time;
	}

	public int getQiandao() {
		return qiandao;
	}

	public void setQiandao(int qiandao) {
		this.qiandao = qiandao;
	}

	public int getQiandao_count() {
		return qiandao_count;
	}

	public void setQiandao_count(int qiandao_count) {
		this.qiandao_count = qiandao_count;
	}

	public long getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(long recordtime) {
		this.recordtime = recordtime;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
