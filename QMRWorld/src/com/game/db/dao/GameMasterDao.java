package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.GameMaster;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class GameMasterDao {

	private Logger log = Logger.getLogger(GameMasterDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();
	
    public GameMaster selectbyroleid(long roleid) throws SQLException {
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("roleid", roleid);
    		GameMaster gm = (GameMaster)session.selectOne("gamemaster.selectbyroleid", map);
    		LoggerProvider.getDbconsuminglog().info("gamemaster.selectbyroleid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return gm;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return null;
    }
    
    public List<GameMaster> getGameMasterList() throws SQLException {
    	SqlSession session = sqlMapper.openSession();
    	List<GameMaster> gmlist = new ArrayList<GameMaster>();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		gmlist = (List<GameMaster>)session.selectList("gamemaster.select");
    		LoggerProvider.getDbconsuminglog().info("gamemaster.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return gmlist;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return gmlist;
    }
    
    public int insertGameMaster(GameMaster gm) throws SQLException{
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("gamemaster.insert", gm);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("gamemaster.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
    public int updateGameMaster(GameMaster gm) throws SQLException{
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("gamemaster.updatebyuserid", gm);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("gamemaster.updatebyuserid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
}
