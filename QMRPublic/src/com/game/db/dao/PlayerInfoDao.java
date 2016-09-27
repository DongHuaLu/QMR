package com.game.db.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.memorycache.structs.PlayerInfo;
import com.game.util.LoggerProvider;
import com.game.util.TimeUtil;

public class PlayerInfoDao {

	protected Logger log = Logger.getLogger(PlayerInfoDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int insert(PlayerInfo playerInfo) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("game_player.insert", playerInfo);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_player.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}finally{
			session.close();
		}
    }

	public PlayerInfo selectByWebAndPlayerId(String web, long playerId) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("web", web);
        	map.put("playerId", playerId);
        	PlayerInfo record = (PlayerInfo) session.selectOne(
					"game_player.selectByWebAndPlayerId", map);
			LoggerProvider.getDbconsuminglog().info(
					"game_player.selectByWebAndPlayerId "
							+ TimeUtil.getDurationToNow(currentTimeMillis)
							+ "ms");
			return record;
		} finally {
			session.close();
		}
	}

	public PlayerInfo selectByDataServerPlayerId(long dataServerPlayerId) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			PlayerInfo record = (PlayerInfo) session.selectOne(
					"game_player.selectByDataServerPlayerId", dataServerPlayerId);
			LoggerProvider.getDbconsuminglog().info(
					"game_player.selectByDataServerPlayerId "
							+ TimeUtil.getDurationToNow(currentTimeMillis)
							+ "ms");
			return record;
		} finally {
			session.close();
		}
	}

    public int update(PlayerInfo playerInfo) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("game_player.update", playerInfo);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_player.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}finally{
			session.close();
		}
    }
}