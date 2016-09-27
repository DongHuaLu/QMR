package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.GoldRechargeLog;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;


public class GoldRechargeLogDAO  {
	private Logger logger = Logger.getLogger(GoldRechargeLogDAO.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

	public GoldRechargeLog selectById(String oid) throws SQLException {
        SqlSession session = sqlMapper.openSession();
        GoldRechargeLog selectOne=null;
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	selectOne = (GoldRechargeLog) session.selectOne("rechargelog.selectById", oid);
        	LoggerProvider.getDbconsuminglog().info("rechargelog.selectById "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    	}catch (Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
    	return selectOne;
    }

	@SuppressWarnings("unchecked")
	public List<GoldRechargeLog> selectByUseridType(long userid, int type) throws SQLException {
		List<GoldRechargeLog> loglist = new ArrayList<GoldRechargeLog>();
		GoldRechargeLog param = new GoldRechargeLog();
		param.setUserid(userid); param.setType(type);
		SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	loglist = session.selectList("rechargelog.selectByUseridType", param);
        	LoggerProvider.getDbconsuminglog().info("rechargelog.selectByUseridType "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    	}catch (Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return loglist;
	}
}