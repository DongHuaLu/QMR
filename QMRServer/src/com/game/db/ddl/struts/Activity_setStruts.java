package com.game.db.ddl.struts;

import com.game.db.bean.ServerParam;


/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:30:32
 */
public class Activity_setStruts extends DBStruts {

	@Override
	public String primary() {
		return "idx";
	}

	@Override
	public String tableName() {
		return "activity_set";
	}

	@Override
	public String className() {
		return ServerParam.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("idx",INT, 11, false);
		add("int_value",INT, 11, true);
		add("num",INT,11, true);
		add("string_value",VARCHAR,256, true);
		add("day_value",BIGINT,20, true);
		add("name",VARCHAR,256, true);
	}

}
