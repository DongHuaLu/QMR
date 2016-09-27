package com.game.db.ddl.struts;

import com.game.db.bean.BlackListBean;

/**
 * 
 * @author 赵聪慧
 * @2012-11-18 下午6:52:47
 */
public class BlackListStruts extends DBStruts{
	
	@Override
	public String tableName() {
		return "blacklist";
	}	

	@Override
	public String className() {
		return BlackListBean.class.getCanonicalName();
	}

	@Override
	public String primary() {
		return "id";
	}

	@Override
	public void buildDbStruts() {
		add("id",INT, 11, false);
		add("username", VARCHAR, 255, true);
		add("endtime", BIGINT,11, true);
		add("endcount",INT, 11, true);
		add("nowcount",INT, 11, true);
		add("type",INT, 11, true);
		add("state",INT, 11, true);
	}

}
