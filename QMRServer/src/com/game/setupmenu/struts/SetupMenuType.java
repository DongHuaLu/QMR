package com.game.setupmenu.struts;

/**系统设置枚举
 * 
 * @author zhangrong
 *
 */
public enum SetupMenuType{
	/**
	 * 禁止他人添加我为好友
	 */
	FRIEND		(1), 
	
	/**
	 * 禁止他人向我发起交易
	 */
	TRANSACTION 	(2),
	
	/**
	 * 禁止他人邀请我加帮会
	 */
	JOIN_GUILD		(4),
	
	/**
	 * 禁止他人邀请我加队伍
	 */
	JOIN_TEAM		(8),
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	;
	private int value;
	
	SetupMenuType(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public boolean compare(int value){
		return this.value == value;
	}
 
}