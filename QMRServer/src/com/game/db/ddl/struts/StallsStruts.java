package com.game.db.ddl.struts;

import com.game.db.bean.ServerParam;


/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:30:32
 */
public class StallsStruts extends DBStruts {

	@Override
	public String primary() {
		return "roleid";
	}

	@Override
	public String tableName() {
		return "stalls";
	}

	@Override
	public String className() {
		return ServerParam.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("roleid",BIGINT, 20, false);
		add("stallsdata",TEXT, 0, true);

	}

}
