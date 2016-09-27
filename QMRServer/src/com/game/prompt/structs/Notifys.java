package com.game.prompt.structs;

/**
 * 通知类型枚举
 * @author heyang
 *
 */

public enum Notifys {

//	1	错误通知
	ERROR		(1),
//	2	正常提示
	NORMAL		(2),
//	3	成功通知
	SUCCESS		(3),
	
//  4	滚动公告	
	SROLL		(4),
//	5	切出
	CUTOUT		(5),
//  5	聊天框系统提示   (聊天框上半部分)
	CHAT_SYSTEM	(6),
	
//聊天框提示
//	队伍聊天框提示
	CHAT_TEAM 			(30),
//	私聊框提示
	CHAT_ROLE			(31),
//	帮会聊天框提示
	CHAT_GROUP			(32),
//	普通聊天框提示
	CHAT_COMMON			(33),
//	世界聊天框提示
	CHAT_WORLD			(34),
//	GM聊天提示
	CHAT_GM				(35),
//	公告聊天提示
	CHAT_BULL			(36),
//  喇叭提示
//	LABA_CHAT			(35);
	//装备强化提示
	EQST				(100),
	//鼠标位置提示
	MOUSEPOS			(101);
	
	
	private byte value;
	
	Notifys(int value){
		this.value = (byte) value;
	}

	public byte getValue() {
		return value;
	}
	
	public boolean compare(int value){
		return this.value == value;
	}
}
