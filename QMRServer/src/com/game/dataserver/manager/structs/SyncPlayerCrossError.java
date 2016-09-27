package com.game.dataserver.manager.structs;

public class SyncPlayerCrossError {

	//角色不存在
	public final static int PLAYER_NOT_EXITS = 1;
	
	//账号不存在
	public final static int USER_NOT_EXITS = 2;
	
	//不在跨服中
	public final static int NOT_CROSS_SERVER = 3;
	
	//当前跨服对象不正确
	public final static int CROSS_SERVER_PLAYER_ERROR = 4;
	
	//已经退出跨服
	public final static int ALREADY_QUIT_CROSS = 5;
	
	//正在跨服中
	public final static int ALREADY_CROSS_SERVER = 6;
}
