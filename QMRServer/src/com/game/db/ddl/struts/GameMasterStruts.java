package com.game.db.ddl.struts;

import com.game.db.bean.GameMaster;

/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:28:51
 */
public class GameMasterStruts extends DBStruts {

	@Override
	public String primary() {
		return "id";
	}

	@Override
	public String tableName() {
		return "gamemaster";
	}

	@Override
	public String className() {
		return GameMaster.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("id", INT, 11,false);
		add("userid",BIGINT, 20, true);
		add("username",VARCHAR, 100, true);
		add("serverid",INT, 11, true);
		add("gmlevel", INT, 11, true);
		add("addtimes",BIGINT, 20, true);
		add("date", VARCHAR, 50, true);
		add("isdeleted", INT, 11, true);
		add("addtag", VARCHAR, 200, true);
	}

}
