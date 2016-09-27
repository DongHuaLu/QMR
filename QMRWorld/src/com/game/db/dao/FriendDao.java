package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.Friend;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class FriendDao {

	private Logger log = Logger.getLogger(FriendDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int insert(Friend friend) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("friend.insert", friend);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("friend.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<Friend> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<Friend> list = (List<Friend>)session.selectList("friend.select");
        	LoggerProvider.getDbconsuminglog().info("friend.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<Friend>();
    }

    public int update(Friend friend) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("friend.update", friend);
    		LoggerProvider.getDbconsuminglog().info("friend.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		session.commit();
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }
}