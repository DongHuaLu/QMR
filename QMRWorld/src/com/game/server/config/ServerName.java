package com.game.server.config;

public class ServerName {
	public static String getName(int server){
		switch (server) {
		case 0:
			return "中立区";
		case 1:
			return "秦国";
		case 2:
			return "齐国";
		case 3:
			return "楚国";
		case 4:
			return "赵国";
		case 5:
			return "魏国";
		case 6:
			return "韩国";
		case 7:
			return "燕国";
		}
		return "";
	}

}
