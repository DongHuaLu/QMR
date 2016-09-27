package com.game.db.ddl.struts;

import com.game.db.bean.ServerParam;


public class WeddingStruts extends DBStruts {

	@Override
	public String primary() {
		return "id";
	}

	@Override
	public String tableName() {
		return "wedding";
	}

	
	
	
	@Override
	public String className() {
		return ServerParam.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("id",BIGINT, 20, false);
		add("data",TEXT, 0, true);
		add("deleted", INT,11, true);
		
	}
	
}
