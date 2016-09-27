package com.game.db.ddl.struts;

import com.game.db.bean.ServerParam;


/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:30:32
 */
public class SystemGrantStruts extends DBStruts {

	@Override
	public String primary() {
		return "q_id";
	}

	@Override
	public String tableName() {
		return "system_grant";
	}

	@Override
	public String className() {
		return ServerParam.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("q_id",INT, 11, false);
		add("q_key",VARCHAR, 512, true);
		add("q_type",INT, 11, true);
		add("q_platform",VARCHAR, 512, true);
		add("q_exclude_platform",VARCHAR, 512, true);
		add("q_items",TEXT, 0, true);
		add("q_time_interval",VARCHAR, 512, true);
		add("q_expired",VARCHAR, 512, true);
		add("q_rolecreated",VARCHAR, 512, true);
		add("q_caption",VARCHAR, 512, true);
		add("q_level",VARCHAR, 512, true);
		add("q_other",TEXT, 0, true);
	}

}
