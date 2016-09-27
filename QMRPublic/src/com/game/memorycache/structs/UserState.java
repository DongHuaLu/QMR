package com.game.memorycache.structs;

public class UserState {
	/**
	 * 未跨服
	 */
	public static int NO_CROSS = 0;
	/**
	 * 等待跨服中
	 */
	public static int WAIT_CROSS = 1;
	
	/**
	 * 跨服中
	 */
	public static int CROSS_SERVER = 2;
	
	/**
	 * 退出跨服
	 */
	public static int QUIT_CROSS = 3;
}
