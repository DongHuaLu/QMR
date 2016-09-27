package com.game.db.ddl.struts;

import com.game.backpack.log.GoldChangeLog;

/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:29:24
 */
public class GoldRechargeLogStruts extends DBStruts {
//	CREATE TABLE `goldrechargelog` (
//			  `oid` varchar(255) NOT NULL COMMENT '订单号',
//			  `uid` varchar(255) NOT NULL,
//			  `serverid` varchar(255) NOT NULL COMMENT '服务器ID',
//			  `gold` int(11) NOT NULL COMMENT '元宝数',
//			  `time` bigint(20) NOT NULL COMMENT '充值时间',
//			  `type` int(11) NOT NULL COMMENT '充值类型',
//			  `userid` bigint(20) NOT NULL DEFAULT '0',
//			  `rmb` varchar(512) DEFAULT NULL,
//			  `content` text,
//			  PRIMARY KEY (`oid`),
//			  KEY `userid` (`userid`,`type`)
//			) ENGINE=MyISAM DEFAULT CHARSET=utf8;
	@Override
	public void buildDbStruts() {
		add("oid", VARCHAR, 255, false);
		add("uid", VARCHAR, 255, false);
		add("serverid", VARCHAR, 255, false);
		add("gold", INT, 11, false);
		add("time", BIGINT,20, false);
		add("type", INT,11, false);
		add("userid", BIGINT,20, false);
		add("rmb", VARCHAR,512, true);
		add("content", TEXT,0, false);
		
	}

	@Override
	public String primary() {
		return "oid";
	}

	@Override
	public String tableName() {
		return "goldrechargelog";
	}

	@Override
	public String className() {
		return GoldChangeLog.class.getCanonicalName();
	}


}
