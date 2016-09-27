package com.game.utils;

import java.io.File;

public class FileUtil {

	public static boolean isExists(String name){
		File file = new File(name);
		return file.exists();
	}
}
