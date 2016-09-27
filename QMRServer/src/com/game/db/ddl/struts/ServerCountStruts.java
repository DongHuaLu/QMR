package com.game.db.ddl.struts;

import com.game.db.bean.ServerCount;

/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:30:13
 */
public class ServerCountStruts extends DBStruts {

	@Override
	public String primary() {
		return "countkey";
	}

	@Override
	public String tableName() {
		return "server_count";
	}

	@Override
	public String className() {
		return ServerCount.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("countkey",INT, 11, false);
		add("type",INT, 11, true);
		add("count",BIGINT,20, true);		
	}
}