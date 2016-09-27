package com.game.db.ddl.struts;

import com.game.memorycache.structs.PlayerInfo;

/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:29:56
 */
public class RoleStruts extends DBStruts {

	@Override
	public String primary() {
		return "dataServerPlayerId";
	}

	@Override
	public String tableName() {
		return "player";
	}

	@Override
	public String className() {
		return PlayerInfo.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("roleid", BIGINT, 20,false);
		add("userid", VARCHAR, 50, false);
		add("create_server",INT, 11, false);
		add("locate", INT, 11, false);
		add("level", INT, 11, false);
		add("name", VARCHAR, 50, false);
		add("sex", INT, 11, false);
		add("deleted", INT, 11, false);
		add("data", LONGTEXT, 0, false);
		add("logintime", BIGINT, 20, true);
		add("version", INT, 11, true);
		add("isdelete", TINYINT, 1, true);
		add("isforbid", TINYINT, 1, true);
		add("country", INT, 11, true);
		add("loginip", VARCHAR, 50, true);
		add("width", INT, 11, true);
		add("height", INT, 11, true);
		add("money", INT, 11, true);
		add("bindGold", INT, 11, true);
		add("bagCellsNum", INT, 11,true);
		add("storeCellsNum", INT, 11,true);
		add("prohibitChatTime", BIGINT, 20,true);
		add("startProhibitChatTime", BIGINT, 20,true);
		add("addBlackTime", BIGINT, 20, true);
		add("addBlackCount", INT, 11, true);
		add("exp", BIGINT, 20, true);
		add("zhenqi",INT, 11,true);
		add("prestige",INT, 11,true);
		add("dieTime", BIGINT, 20, true);
		add("arrowLevel",INT, 11,true);
		add("tianyuanLevel",INT, 11,true);
		add("prestigePoint",INT, 11,true);
		add("achievementPoint",INT, 11,true);
		add("fightPower", INT, 11, true);
		add("horseLevel", INT, 11, true);
		add("onlineTime", BIGINT, 20, true);
		add("map", INT, 11, true);
		add("mapModelId", INT, 11, true);
		add("x", INT, 11, true);
		add("y", INT, 11, true);
		add("hp", INT, 11, true);
		add("mp", INT, 11, true);
		add("sp", INT, 11, true);
		add("agentPlusdata", "varchar", 1024, true);
		add("agentColdatas", "varchar", 1024, true);		
	}

}
