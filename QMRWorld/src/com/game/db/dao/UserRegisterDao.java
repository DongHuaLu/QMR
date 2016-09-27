package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.player.structs.UserRegister;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class UserRegisterDao {

	private Logger log = Logger.getLogger(UserRegisterDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int insert(UserRegister info) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("register.insert", info);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("register.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<UserRegister> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<UserRegister> list = (List<UserRegister>)session.selectList("register.select");
        	LoggerProvider.getDbconsuminglog().info("register.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<UserRegister>();
    }

    public int update(UserRegister info) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("register.update", info);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("register.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }

}