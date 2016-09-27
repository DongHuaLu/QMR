package com.game.db.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.ServerParam;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class ServerParamDao {

	protected Logger log = Logger.getLogger(ServerParamDao.class);
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

	public int insert(ServerParam serverParam) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("serverparam.insert", serverParam);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("serverparam.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ServerParam> select() throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<ServerParam> list = (List<ServerParam>) session.selectList("serverparam.select");
			LoggerProvider.getDbconsuminglog().info("serverparam.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ServerParam> selectByid(int serverid) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<ServerParam> list = (List<ServerParam>) session.selectList("serverparam.selectByid", serverid);
			LoggerProvider.getDbconsuminglog().info("serverparam.selectByid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	public int update(ServerParam serverParam) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.update("serverparam.update", serverParam);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("serverparam.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} finally {
			session.close();
		}
	}
}