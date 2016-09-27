package com.game.db.ddl.struts;

import com.game.db.bean.ServerParam;


/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:30:32
 */
public class ServerParamStruts extends DBStruts {

	@Override
	public String primary() {
		return "paramkey";
	}

	@Override
	public String tableName() {
		return "serverparam";
	}

	@Override
	public String className() {
		return ServerParam.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("paramkey",VARCHAR, 64, false);
		add("serverid",INT, 11, true);
		add("paramvalue",TEXT,0, true);
	}

}
