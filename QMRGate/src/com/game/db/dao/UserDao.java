package com.game.db.dao;

import java.sql.SQLException;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.User;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * @version 创建时间：2010-11-8
 * 账号表
 */
public class UserDao {
	
	private Logger log = Logger.getLogger(UserDao.class);

	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();
	
	/**
	 * 插入账号
	 * @return
	 * @throws SQLException
	 */
    public int insert(User user) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		long start = System.currentTimeMillis();
    		int rows = session.insert("game_user.insert", user);
    		session.commit();
    		long end = System.currentTimeMillis();
        	log.error("insert user cost:" + (end - start));
        	LoggerProvider.getDbconsuminglog().info("game_user.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}finally{
			session.close();
		}
    }
    
    public User select(String username, int server) {
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		long start = System.currentTimeMillis();
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("username", username);
    		map.put("server", server);
    		User user = (User)session.selectOne("game_user.select", map);
    		long end = System.currentTimeMillis();
        	log.error("select user cost:" + (end - start));
        	LoggerProvider.getDbconsuminglog().info("game_user.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return user;
    	}finally{
			session.close();
		}
    }

    public int update(User user) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("game_user.update", user);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_user.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}finally{
			session.close();
		}
    }
}