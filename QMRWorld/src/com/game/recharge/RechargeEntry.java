package com.game.recharge;

import java.util.HashMap;
/**
 * 
 * @author 赵聪慧
 *
 */
public class RechargeEntry {
	private static final String UID = "UID";
	private static final String OID = "OID";
	private static final String SID="SID";
	private static final String MONEY = "MONEY";
	private static final String GOLD = "GOLD";
	private static final String BINDGOLD = "BINDGOLD";
	private static final String IP = "IP";
	private static final String TYPE = "TYPE"; // 充值类型 内部充值9  真实充值0  墨龙测试充值100 退订单0
	private static final String REMARK = "REMARK";
	private static final String TIME = "TIME";
	private static final String SIGN="SIGN";
	private static final String RMB="RMB";
	
	private String paramValue;
	private HashMap<String, String> param = new HashMap<String, String>();

	// sid=55&uid=222&oid=111&type=1&num=100&ip=210.110.110.110&time=1254984732&sign=asdfadf
	public RechargeEntry(HashMap<String, String> param,String json) {
		this.param=param;
		paramValue=json;
	}

	@Override
	public String toString() {
		return paramValue;
	}

	public String getIp() {
		return param.get(IP);
	}
	
	public String getGold() {
		return param.get(GOLD);
	}
	
	public String getBindGold() {
		return param.get(BINDGOLD);
	}
	
	public String getRemark(){
		return param.get(REMARK);
	}
	public String getMoney() {
		return param.get(MONEY);
	}
	public String getUid() {
		return param.get(UID);
	}
	public String getOid() {
		return param.get(OID);
	}
	
	public String getRMB(){
		return param.get(RMB);
	}

	public String getSid(){
		return param.get(SID);
	}

	public String getType() {
		return param.get(TYPE);
	}
	
	public String getTime() {
		return param.get(TIME);
	}

	public String getSign(){
		return param.get(SIGN);
	}
}
