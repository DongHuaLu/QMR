package com.game.db.ddl.struts;

import com.game.db.bean.Role;

/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:29:56
 */
public class RoleStruts extends DBStruts {

	@Override
	public String primary() {
		return "roleid";
	}

	@Override
	public String tableName() {
		return "role";
	}

	@Override
	public String className() {
		return Role.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
//		  `roleid` bigint(20) NOT NULL,
//		  `userid` varchar(50) NOT NULL,
//		  `create_server` int(11) NOT NULL,
//		  `locate` int(11) NOT NULL,
//		  `level` int(11) NOT NULL,
//		  `name` varchar(50) NOT NULL,
//		  `sex` int(11) NOT NULL,
		add("roleid", BIGINT, 20,false);
		add("userid", VARCHAR, 50, false);
		add("create_server",INT, 11, false);
		add("locate", INT, 11, false);
		add("level", INT, 11, false);
		add("name", VARCHAR, 50, false);
		add("sex", INT, 11, false);
		
//		  `deleted` int(11) unsigned zerofill NOT NULL,
//		  `data` longtext NOT NULL,
//		  `logintime` bigint(20) DEFAULT NULL,
//		  `version` int(11) DEFAULT NULL,
//		  `isDelete` tinyint(1) DEFAULT NULL,
//		  `isForbid` tinyint(1) DEFAULT NULL,
//		  `country` int(11) DEFAULT NULL,
//		  `loginIp` varchar(50) DEFAULT NULL,
//		  `width` int(11) DEFAULT NULL,
//		  `height` int(11) DEFAULT NULL,
//		  `money` int(11) DEFAULT NULL,
		
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
		
//		  `bindGold` int(11) DEFAULT NULL,
//		  `bagCellsNum` int(11) DEFAULT NULL,
//		  `storeCellsNum` int(11) DEFAULT NULL,
//		  `prohibitChatTime` bigint(20) DEFAULT NULL,
//		  `startProhibitChatTime` bigint(20) DEFAULT NULL,
//		  `addBlackTime` bigint(20) DEFAULT NULL,
//		  `addBlackCount` int(11) DEFAULT NULL,
//		  `exp` bigint(20) DEFAULT NULL,
//		  `zhenqi` int(11) DEFAULT NULL,
//		  `prestige` int(11) DEFAULT NULL,
//		  `dieTime` bigint(20) DEFAULT NULL,
//		  `arrowLevel` int(11) DEFAULT NULL,
//		  `tianyuanLevel` int(11) DEFAULT NULL,
//		  `prestigePoint` int(11) DEFAULT NULL,
//		  `achievementPoint` int(11) DEFAULT NULL,
		
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
//		  `fightPower` int(11) DEFAULT NULL,
//		  `horseLevel` int(11) DEFAULT NULL,
//		  `onlineTime` bigint(20) DEFAULT NULL,
//		  `map` int(11) DEFAULT NULL,
//		  `mapModelId` int(11) DEFAULT NULL,
//		  `x` int(11) DEFAULT NULL,
//		  `y` int(11) DEFAULT NULL,
//		  `hp` int(11) DEFAULT NULL,
//		  `mp` int(11) DEFAULT NULL,
//		  `sp` int(11) DEFAULT NULL,
//		  `agentPlusdata` varchar(1024) DEFAULT NULL,
//		  `agentColdatas` varchar(1024) DEFAULT NULL,
//		  PRIMARY KEY (`roleid`),
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
