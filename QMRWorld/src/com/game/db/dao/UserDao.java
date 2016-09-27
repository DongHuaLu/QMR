package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.GameUser;
import com.game.player.structs.User;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class UserDao {

	private Logger log = Logger.getLogger(UserDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int insert(User info) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("online.insert", info);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("online.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }

    @SuppressWarnings("unchecked")
	public List<User> select() throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<User> list = (List<User>)session.selectList("online.select");
        	LoggerProvider.getDbconsuminglog().info("online.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return new ArrayList<User>();
    }

    public int update(User info) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("online.update", info);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("online.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
    public int delete(User info) throws SQLException {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.delete("online.delete", info);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("online.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }
    //------------------------------------------
    
    /**检查相同账户数量
     * 
     * @param name
     * @return
     * @throws SQLException
     */
    public int selectByName(String name,int server) {
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("username", name);
    		map.put("server", server);
    		int record = (Integer)session.selectOne("game_user.selectByUserName", map);
    		LoggerProvider.getDbconsuminglog().info("game_user.selectByUserName "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return record;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
    /**更新用户信息
     * 
     * @param info
     * @return
     * @throws SQLException
     */
    public int updateGameUser(GameUser info) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("game_user.updateUserName", info);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_user.updateUserName "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}catch (Exception e) {
			log.error(e,e);
		}finally{
			session.close();
		}
    	return 0;
    }
    
    /**根据username读取单个用户
     * 
     * @return
     * @throws SQLException
     */
	public GameUser selectGameUser(String name ,int server) {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("username", name);
    		map.put("server", server);
        	GameUser gameuser = (GameUser)session.selectOne("game_user.selectDataByName",map);
        	LoggerProvider.getDbconsuminglog().info("game_user.selectDataByName "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return gameuser;
    	}catch (Exception e) {
			log.error(e, e);
		}finally{
			session.close();
		}
    	return null;
    }
    
    /**根据userid读取单个用户
     * 
     * @return
     * @throws SQLException
     */
	public GameUser selectGameUser(long id,int server) throws SQLException {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("userid", id);
    		map.put("server", server);
        	GameUser gameuser = (GameUser)session.selectOne("game_user.selectDataById",map);
        	LoggerProvider.getDbconsuminglog().info("game_user.selectDataById "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return gameuser;
    	}catch (Exception e) {
			log.error(e);
		}finally{
			session.close();
		}
    	return null;
    }
	
	
	
}


