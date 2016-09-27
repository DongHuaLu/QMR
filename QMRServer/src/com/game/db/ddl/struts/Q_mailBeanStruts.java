package com.game.db.ddl.struts;
/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:29:43
 */
public class Q_mailBeanStruts extends DBStruts {

	@Override
	public String primary() {
		return "mail_id";
	}

	@Override
	public String tableName() {
		return "mail";
	}

	@Override
	public String className() {
		return Q_mailBeanStruts.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
//		  `mail_id` bigint(20) NOT NULL DEFAULT '0',
//		  `send_name` varchar(50) DEFAULT NULL,
//		  `receiver_id` bigint(20) DEFAULT NULL,
//		  `receiver_name` varchar(50) DEFAULT NULL,
//		  `send_time` int(11) DEFAULT NULL,
//		  `btAccessory` int(11) DEFAULT NULL,
//		  `btSystem` int(11) DEFAULT NULL,
//		  `btRead` int(11) DEFAULT NULL,
//		  `btReturn` int(11) DEFAULT NULL,
//		  `mail_data` text,
//		  PRIMARY KEY (`mail_id`),
//		  KEY `mail_id` (`mail_id`),
//		  KEY `receiver_id` (`receiver_id`)
		add("mail_id", BIGINT, 20, false);
		add("send_name", VARCHAR, 50, true);
		add("receiver_id", BIGINT,20, true);
		add("receiver_name", VARCHAR, 50, true);
		add("send_time", INT, 11, true);
		add("btaccessory", INT, 11, true);
		add("btsystem", INT, 11, true);
		add("btread", INT, 11, true);
		add("btreturn", INT, 11, true);
		add("mail_data", TEXT, 0, true);
	}
	
	

}
