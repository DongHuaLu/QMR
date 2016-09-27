package com.game.db.ddl.struts;

import com.game.db.bean.ServerParam;


/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:30:32
 */
public class YbcardStruts extends DBStruts {

	@Override
	public String primary() {
		return "username";
	}

	@Override
	public String tableName() {
		return "ybcard";
	}

	@Override
	public String className() {
		return ServerParam.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("username",VARCHAR, 255, false);
		add("yuanbao",INT, 11, true);

	}

}
