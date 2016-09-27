package com.game.db.ddl.struts;

import com.game.db.bean.Gold;

/**
 * 
 * @author 赵聪慧
 * @2012-11-19 下午3:29:10
 */
public class GoldStruts extends DBStruts {


	@Override
	public String primary() {
		return "userid";
	}

	@Override
	public String tableName() {
		return "gold";
	}

	@Override
	public String className() {
		return Gold.class.getCanonicalName();
	}

	@Override
	public void buildDbStruts() {
		add("userId", VARCHAR, 255, false);
		add("serverid", INT, 11, false);
		add("totalgold", INT,11, true);
		add("gold", INT,11, true);
		add("costgold", INT,11, true);
		add("twgmadd", INT,11, true);
		add("ybxiajiaadd", INT,11, true);
		add("huokuanadd", INT,11, true);
		add("jiaoyiybadd", INT,11, true);
		add("faildrollbackadd", INT,11, true);
		add("gettempybadd", INT,11, true);
		add("jiaoyiybadd", INT,11, true);
		add("faildrollbackadd", INT,11, true);
		add("gettempybadd", INT,11, true);
		add("jiaoyiresume", INT,11, true);
		add("shangjiaresume", INT,11, true);
		add("buyitemresume", INT,11, true);
		add("isinner", INT,11, false);
	}

}
