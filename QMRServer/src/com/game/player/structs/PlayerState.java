package com.game.player.structs;
/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-7-17
 * 
 * 玩家状态枚举 
 */
public enum PlayerState {
	/**
	 * 正常
	 */
	NORMAL  	(0x00000000, 0xFFFFFFF0),
	/**
	 * 登陆
	 */
	LOGIN   	(0x00000001, 0xFFFFFFF0),
	/**
	 * 退出中
	 */
	QUITING    	(0x00000002, 0xFFFFFFF0),
	/**
	 * 退出
	 */
	QUIT   		(0x00000004, 0xFFFFFFF0),
	/**
	 * 无状态
	 */
	NOTHING 	(0x00000000, 0xFFFFFF0F),
	/**
	 * 战斗
	 */
	FIGHT   	(0x00000010, 0xFFFFFF0F),
	/**
	 * 死亡
	 */
	DIE     	(0x00000020, 0xFFFF000F),
	/**
	 * 站立
	 */
	STAND   	(0x00000000, 0xFFFF00FF),
	/**
	 * 走动
	 */
	RUN   		(0x00000100, 0xFFFF00FF),
	/**
	 * 跳跃
	 */
	JUMP    	(0x00000200, 0xFFFF00FF),
	/**
	 * 二次跳跃
	 */
	DOUBLEJUMP	(0x00000400, 0xFFFF00FF),
	/**
	 * 格挡准备
	 */
	BLOCKPREPARE(0x00000800, 0xFFFF00FF),
	/**
	 * 格挡
	 */
	BLOCK		(0x00001000, 0xFFFF00FF),
	/**
	 * 打坐
	 */
	SIT			(0x00002000, 0xFFFF00FF),
	/**
	 * 游泳
	 */
	SWIM		(0x00004000, 0xFFFF00FF),
	/**
	 * 无复活
	 */
	NOREVIVE	(0x00000000, 0xFFFEFFFF),
	/**
	 * 自动复活
	 */
	AUTOREVIVE	(0x00010000, 0xFFFEFFFF),
	/**
	 * 非交易状态
	 */
	NOTRANSACTION	(0x00000000, 0xFFFDFFFF),
	/**
	 * 交易
	 */
	TRANSACTION		(0x00020000, 0xFFFDFFFF),
	/**
	 * 非挂机状态
	 */
	NOAUTOFIGHT		(0x00000000, 0xFFFBFFFF),
	/**
	 * 挂机
	 */
	AUTOFIGHT		(0x00040000, 0xFFFBFFFF),
	/**
	 * 龙元心法计时结束
	 */
	LONGYUANEND		(0x00000000, 0xFFF7FFFF),
	/**
	 * 龙元心法开始计时状态（用来防止计时器重复调用）
	 */
	LONGYUANSTART	(0x00080000, 0xFFF7FFFF),
	
	/**
	 * 原地复活计时结束
	 */
	REVIVEEND		(0x00000000, 0xFFEFFFFF),
	/**
	 * 原地复活开始计时状态（用来防止计时器重复调用）
	 */
	REVIVESTART		(0x00100000, 0xFFEFFFFF),
	/**
	 * 地图中
	 */
	INMAP			(0x00000000, 0xFFDFFFFF),
	/**
	 * 换地图中
	 */
	CHANGEMAP		(0x00200000, 0xFFDFFFFF),
	/**
	 * 非采集
	 */
	NOGATHER		(0x00000000, 0xFFBFFFFF),
	/**
	 * 采集
	 */
	GATHER			(0x00400000, 0xFFBFFFFF),
	;
	
	private int value;
	
	private int mark;
	
	PlayerState(int value, int mark){
		this.value = value;
		this.mark = mark;
	}

	public int getValue() {
		return value;
	}

	public int getMark() {
		return mark;
	}
	
	public boolean compare(int state){
		return ((this.value & state)!=0);
	}
}
