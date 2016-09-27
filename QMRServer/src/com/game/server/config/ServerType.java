package com.game.server.config;

public enum ServerType {
	/**
	 * 秦国
	 */
	QIN  		(1),
	/**
	 * 齐国
	 */
	QI   		(2),
	/**
	 * 楚国
	 */
	CHU	    	(3),
	/**
	 * 赵国
	 */
	ZHAO   		(4),
	/**
	 * 魏国
	 */
	WEI		 	(5),
	/**
	 * 韩国
	 */
	HAN		   	(6),
	/**
	 * 燕国
	 */
	YAN     	(7),
	/**
	 * 公共区
	 */
	PUBLIC   	(0);
	
	private int value;
	
	ServerType(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
