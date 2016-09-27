package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.TxConsumeLog;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;


public class TxConsumeLogDAO  {
	private static Logger logger = Logger.getLogger(TxConsumeLogDAO.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

	public TxConsumeLog selectById(String oid) throws SQLException {
        SqlSession session = sqlMapper.openSession();
        TxConsumeLog selectOne=null;
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	selectOne = (TxConsumeLog) session.selectOne("txconsumelog.selectByOid", oid);
        	LoggerProvider.getDbconsuminglog().info("txconsumelog.selectByOid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    	}catch (Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
    	return selectOne;
    }

	@SuppressWarnings("unchecked")
	public List<TxConsumeLog> selectByUseridServerid(String userid, String serverid) throws SQLException {
		List<TxConsumeLog> loglist = new ArrayList<TxConsumeLog>();
		TxConsumeLog param = new TxConsumeLog();
		param.setUserid(userid); param.setServerid(serverid);
		SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	loglist = session.selectList("txconsumelog.selectByUseridServerid", param);
        	LoggerProvider.getDbconsuminglog().info("txconsumelog.selectByUseridServerid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    	}catch (Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return loglist;
	}
	
	@SuppressWarnings("unchecked")
	public List<TxConsumeLog> selectByIsconfirmTime(int isconfirm, long leasttimes) throws SQLException {
		List<TxConsumeLog> loglist = new ArrayList<TxConsumeLog>();
		TxConsumeLog param = new TxConsumeLog();
		Map<String, String> paramap = new HashMap<String, String>();
		paramap.put("isconfirm", String.valueOf(isconfirm));
		paramap.put("times", String.valueOf(leasttimes));
		SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	loglist = session.selectList("txconsumelog.selectByIsconfirmTime", param);
        	LoggerProvider.getDbconsuminglog().info("txconsumelog.selectByIsconfirmTime "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    	}catch (Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return loglist;
	}
	
	public int insert(TxConsumeLog log){
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("txconsumelog.insert", log);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("txconsumelog.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} finally {
			session.close();
		}
	}
	
	public int update(TxConsumeLog log){
		int rows = -1;
		SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		rows = session.update("txconsumelog.update", log);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("txconsumelog.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    	}finally{
			session.close();
		}
		return rows;
	}
	
	public static void main(String[] args){
//		插入测试
//		TxConsumeLog log = new TxConsumeLog();
//		log.setUsername("tcimpk"); log.setUserid("tcid"); log.setContent("content");
//		log.setConsume(100); log.setTimes(System.currentTimeMillis());
//		log.setDate("2012-10-10 12:12:12"); log.setItems("[{\"itmeid\":-2,\"num\":3,\"bind\":1,\"losttime\":1},{\"itmeid\":1001,\"num\":3,\"bind\":1,\"losttime\":1}]");
//		log.setServerid("1"); log.setOid("oooo111"); log.setState(5); log.setDesc("desc"); log.setMsg("msg"); log.setRet("ret");
		TxConsumeLogDAO dao = new TxConsumeLogDAO();
//		dao.insert(log);
//		读取测试
		try {
//			TxConsumeLog selectById = dao.selectById("oooo111");
			List<TxConsumeLog> selectByUseridServerid = dao.selectByUseridServerid("tcid", "0");
			System.out.println(selectByUseridServerid.toString());
//			List<TxConsumeLog> selectByUseridServerid = dao.selectByUseridServerid("tcid", "1");
//			System.out.println(selectByUseridServerid.get(0).getUsername());
//			System.out.println(selectById.toString());
//			List<TxConsumeLog> selectByIsconfirmTime = dao.selectByIsconfirmTime(0, 1355709566917L);
//			System.out.println(selectByIsconfirmTime.toString());
//			selectById.setUsername("newtc");
//			dao.update(selectById);
		} catch (SQLException e) {
			logger.error(e, e);
		}
	}
}