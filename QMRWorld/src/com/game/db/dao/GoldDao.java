package com.game.db.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.Gold;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class GoldDao {

	protected Logger log = Logger.getLogger(GoldDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();


    public int insert(Gold gold) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("game_gold.insert", gold);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_gold.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}finally{
			session.close();
		}
    }


    public Gold select(String userId, int serverId) {
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("userId", userId);
    		map.put("serverId", serverId);
    		Gold record = (Gold)session.selectOne("game_gold.select", map);
    		LoggerProvider.getDbconsuminglog().info("game_gold.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return record;
    	}finally{
			session.close();
		}
    }

    public int update(Gold gold) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("game_gold.update", gold);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_gold.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}finally{
			session.close();
		}
    }
    
}