package com.game.dataserver.manager;

public class PlayerQuitError {

	//角色不存在
	public static int PLAYER_NOT_EXITS = 1;
	
	//账号不存在
	public static int USER_NOT_EXITS = 2;
	
	//没有在跨服中
	public static int NOT_CROSS_SERVER = 3;
	
	//当前跨服对象不正确
	public static int CROSS_SERVER_PLAYER_ERROR = 4;
	
	//已经退出跨服
	public static int ALREADY_QUIT_CROSS = 5;
}
