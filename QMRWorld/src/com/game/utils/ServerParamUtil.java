package com.game.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.db.bean.ServerParam;
import com.game.db.dao.ServerParamDao;
import com.game.server.WorldServer;

/**
 * 服务器参数
 *
 * @author 杨鸿岚
 */
public class ServerParamUtil {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ServerParamUtil.class);
	private static ServerParamDao serverParamDao = new ServerParamDao();
	private static HashMap<String, String> NormalParamMap = new HashMap<String, String>();
	private static HashMap<String, String> ImportantParamMap = new HashMap<String, String>();
	//Key
	public static String JIAOCHANGZONETOP = "JIAOCHANGZONETOP";
	public static String DITUZONETOPMAP = "DITUZONETOPMAP";
	public static String KINGCITYWAR = "KINGCITYWAR";//王城争霸战KEY
	public static String TESTZONETOPLIST = "TESTZONETOPLIST";//测试区排行榜
	public static String SHOPBUY = "SHOPBUY";	//商店购买
	public static String RANKTASK = "RANKTASK";	//军功任务
	public static String WEDDING = "WEDDING";	//婚宴
	
	public static ServerParamDao getServerParamDao() {
		return serverParamDao;
	}

	public static HashMap<String, String> getImportantParamMap() {
		return ImportantParamMap;
	}

	public static HashMap<String, String> getNormalParamMap() {
		return NormalParamMap;
	}

	public static void immediateSaveNormal(String paramKey, String paramValue) {
		ServerParam serverParam = new ServerParam();
		serverParam.setParamkey(paramKey);
		serverParam.setServerid(WorldServer.getInstance().getServerId());
		serverParam.setParamvalue(paramValue);
		if (NormalParamMap.containsKey(paramKey)) {
			NormalParamMap.put(paramKey, paramValue);
			try {
				if (getServerParamDao().update(serverParam) == 0) {
					logger.error(String.format("NormalParamMap保存update错误！paramKey = %s", paramKey));
				}
			} catch (SQLException ex) {
				logger.error(ex);
				logger.error(String.format("NormalParamMap保存update错误！paramKey = %s", paramKey));
			}
		} else {
			NormalParamMap.put(paramKey, paramValue);
			try {
				if (getServerParamDao().insert(serverParam) == 0) {
					logger.error(String.format("NormalParamMap保存insert错误！paramKey = %s", paramKey));
				}
			} catch (SQLException ex) {
				logger.error(ex);
				logger.error(String.format("NormalParamMap保存insert错误！paramKey = %s", paramKey));
			}
		}
	}
	
	public static void immediateSaveImportant(String paramKey, String paramValue) {
		ServerParam serverParam = new ServerParam();
		serverParam.setParamkey(paramKey);
		serverParam.setServerid(0);
		serverParam.setParamvalue(paramValue);
		if (ImportantParamMap.containsKey(paramKey)) {
			ImportantParamMap.put(paramKey, paramValue);
			try {
				if (getServerParamDao().update(serverParam) == 0) {
					logger.error(String.format("ImportantParamMap保存update错误！paramKey = %s", paramKey));
				}
			} catch (SQLException ex) {
				logger.error(ex);
				logger.error(String.format("ImportantParamMap保存update错误！paramKey = %s", paramKey));
			}
		} else {
			ImportantParamMap.put(paramKey, paramValue);
			try {
				if (getServerParamDao().insert(serverParam) == 0) {
					logger.error(String.format("ImportantParamMap保存insert错误！paramKey = %s", paramKey));
				}
			} catch (SQLException ex) {
				logger.error(ex);
				logger.error(String.format("ImportantParamMap保存insert错误！paramKey = %s", paramKey));
			}
		}
	}

	public static void saveServerParam() {
		for (Map.Entry<String, String> entry : NormalParamMap.entrySet()) {
			String paramKey = entry.getKey();
			String paramValue = entry.getValue();
			if (paramKey != null && paramValue != null) {
				ServerParam serverParam = new ServerParam();
				serverParam.setParamkey(paramKey);
				serverParam.setServerid(WorldServer.getInstance().getServerId());
				serverParam.setParamvalue(paramValue);
				try {
					if (getServerParamDao().update(serverParam) == 0) {
						if (getServerParamDao().insert(serverParam) == 0) {
							logger.error(String.format("NormalParamMap保存错误！paramKey = %s", paramKey));
						}
					}
				} catch (SQLException ex) {
					logger.error(ex);
					logger.error(String.format("NormalParamMap保存错误！paramKey = %s", paramKey));
				}
			}
		}
		for (Map.Entry<String, String> entry : ImportantParamMap.entrySet()) {
			String paramKey = entry.getKey();
			String paramValue = entry.getValue();
			if (paramKey != null && paramValue != null) {
				ServerParam serverParam = new ServerParam();
				serverParam.setParamkey(paramKey);
				serverParam.setServerid(0);
				serverParam.setParamvalue(paramValue);
				try {
					if (getServerParamDao().update(serverParam) == 0) {
						if (getServerParamDao().insert(serverParam) == 0) {
							logger.error(String.format("ImportantParamMap保存错误！paramKey = %s", paramKey));
						}
					}
				} catch (SQLException ex) {
					logger.error(ex);
					logger.error(String.format("ImportantParamMap保存错误！paramKey = %s", paramKey));
				}
			}
		}
	}

	public static boolean loadServerParam(int serverid) {
		try {
			List<ServerParam> serverParams = getServerParamDao().selectByid(serverid);
			for (int i = 0; i < serverParams.size(); i++) {
				ServerParam serverParam = serverParams.get(i);
				if (serverParam != null) {
					NormalParamMap.put(serverParam.getParamkey(), serverParam.getParamvalue());
				}
			}
			serverParams = getServerParamDao().selectByid(0);
			for (int i = 0; i < serverParams.size(); i++) {
				ServerParam serverParam = serverParams.get(i);
				if (serverParam != null) {
					ImportantParamMap.put(serverParam.getParamkey(), serverParam.getParamvalue());
				}
			}
			logger.info("服务器参数加载成功！");
			return true;
		} catch (SQLException ex) {
			logger.error(ex);
			logger.error("服务器参数加载失败！");
		}
		return false;
	}
}
