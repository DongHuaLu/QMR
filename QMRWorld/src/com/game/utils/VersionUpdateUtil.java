package com.game.utils;

import java.io.IOException;


/**
 * 版本升级
 *
 * @author 杨鸿岚
 */
public class VersionUpdateUtil {

	protected static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VersionUpdateUtil.class);
	private static String Version = "20121019";

	public static String versionUpdateKey() {
		return "#" + Version + "#";
	}
	
	public static String dateSave(String saveString) {
		return dateSave(saveString, 1024);
	}

	public static String dateSave(String saveString,int clen) {
		if (saveString.length() > clen && !saveString.startsWith(versionUpdateKey())) {
			try {
				return versionUpdateKey() + CodedUtil.encodeBase64(ZipUtil.compress(saveString));
			} catch (IOException ex) {
				log.error(ex, ex);
			}
			return saveString;
		} else {
			return saveString;
		}
//		return saveString;
	}

	public static String dateLoad(String loadString) throws Exception {
		if (loadString.startsWith(versionUpdateKey())) {
			String parseString = loadString.replaceFirst(versionUpdateKey(), "");
			return ZipUtil.uncompress(CodedUtil.decodeBase64(parseString));
		} else {
			return loadString;
		}
//		return loadString;
	}
}
