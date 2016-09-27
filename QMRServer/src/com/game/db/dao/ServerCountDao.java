package com.game.db.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.ServerCount;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

/**
 * 
 * @author 赵聪慧
 * @2012-9-24 下午3:36:22
 */
public class ServerCountDao {
	
	private Logger log = Logger.getLogger(RoleDao.class);
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();
	public int insert(ServerCount count){
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("servercount.insert", count);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("servercount.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} finally {
			session.close();
		}
	}
	public int update(ServerCount count){
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("servercount.update", count);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("servercount.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		}catch (Exception e) {
			log.error(e,e);
		} finally {
			session.close();
		}
		return 0;
		
	}
	
	public List<ServerCount> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<ServerCount> list = ((List<ServerCount>) session.selectList("servercount.select"));
			LoggerProvider.getDbconsuminglog().info("servercount.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
}
