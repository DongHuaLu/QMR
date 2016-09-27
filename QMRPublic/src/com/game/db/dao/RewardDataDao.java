package com.game.db.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.memorycache.structs.RewardData;
import com.game.util.LoggerProvider;
import com.game.util.TimeUtil;

public class RewardDataDao {

	protected Logger log = Logger.getLogger(RewardDataDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int insert(RewardData rewardData) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("game_reward.insert", rewardData);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_reward.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}finally{
			session.close();
		}
    }

	@SuppressWarnings("unchecked")
	public List<RewardData> selectByWebAndPlayerId(String web, long playerId) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("web", web);
        	map.put("playerId", playerId);
        	List<RewardData> records = (List<RewardData>) session.selectList(
					"game_reward.selectByWebAndPlayerId", map);
			LoggerProvider.getDbconsuminglog().info(
					"game_reward.selectByWebAndPlayerId "
							+ TimeUtil.getDurationToNow(currentTimeMillis)
							+ "ms");
			return records;
		} finally {
			session.close();
		}
	}

    public int update(String web, long playerId) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("web", web);
        	map.put("playerId", playerId);
    		int rows = session.update("game_reward.update", map);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_reward.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}finally{
			session.close();
		}
    }
}