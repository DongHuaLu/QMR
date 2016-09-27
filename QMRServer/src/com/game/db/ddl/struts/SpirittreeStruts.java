package com.game.db.ddl.struts;

import com.game.db.bean.ServerParam;


/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:30:32
 */
public class SpirittreeStruts extends DBStruts {

	@Override
	public String primary() {
		return "roleid";
	}

	@Override
	public String tableName() {
		return "spirittree";
	}

	@Override
	public String className() {
		return ServerParam.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("roleid",BIGINT, 20, false);
		add("data",TEXT, 0, true);
		add("time",INT,11, true);
		add("deleted",INT,11, true);
	}

}
