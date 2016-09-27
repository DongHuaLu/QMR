package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.player.structs.PlayerWorldInfo;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class WorldDao {

	private Logger log = Logger.getLogger(WorldDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int insert(PlayerWorldInfo info) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("world.insert", info);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("world.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<PlayerWorldInfo> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<PlayerWorldInfo> list = (List<PlayerWorldInfo>)session.selectList("world.select");
        	LoggerProvider.getDbconsuminglog().info("world.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<PlayerWorldInfo>();
    }

    public int update(PlayerWorldInfo info) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("world.update", info);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("world.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
    @SuppressWarnings("unchecked")
	public List<PlayerWorldInfo> selectById(String id) throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<PlayerWorldInfo> list = (List<PlayerWorldInfo>)session.selectList("world.selectById",id);
        	LoggerProvider.getDbconsuminglog().info("world.selectById "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<PlayerWorldInfo>();
    }
    

    public int updatechangaccount(String id) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("world.updatechangaccount", id);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("world.updatechangaccount "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }
    

    public static void main(String[] args){
    	WorldDao wdao = new WorldDao();
		try {
			List<PlayerWorldInfo> select = wdao.selectById("8444337612534448150");
			System.out.println(select.get(0).getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
}