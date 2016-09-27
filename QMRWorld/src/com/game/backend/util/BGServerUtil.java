package com.game.backend.util;

import com.game.backend.struct.ServerRequest;

/**
 * 
 * @author 赵聪慧
 * @2012-10-31 上午12:26:34
 */
public class BGServerUtil {
	public static String buildResultHttp(ServerRequest req){
		String path="http://"+req.getResultHttp()+":"+req.getResultPort()+"/"+req.getResultproject()+"/"+req.getResultpath()+"/"+req.getResultaction();
		return path;
	}

}
