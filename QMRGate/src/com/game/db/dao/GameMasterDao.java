package com.game.db.dao;

import java.sql.SQLException;

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
	
    
    public GameMaster selectByUserid(long userid) throws SQLException {
    	GameMaster gm = new GameMaster();
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		gm = (GameMaster) session.selectOne("gamemaster.selectbyuserid", userid);
    		LoggerProvider.getDbconsuminglog().info("gamemaster.selectbyuserid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return gm;
    }
}
