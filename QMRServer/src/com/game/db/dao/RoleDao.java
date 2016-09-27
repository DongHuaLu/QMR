package com.game.db.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.Role;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class RoleDao {

	protected Logger log = Logger.getLogger(RoleDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

//    public int delete(long id) {
//    	SqlSession session = sqlMapper.openSession();
//    	try{
//    		long currentTimeMillis = System.currentTimeMillis();
//    		int rows = session.delete("game_role.delete", id);
//    		LoggerProvider.getDbconsuminglog().info("game_role.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
//    		return rows;
//    	}finally{
//			session.close();
//		}
//    }


    public int insert(Role role) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.insert("game_role.insert", role);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_role.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}finally{
			session.close();
		}
    }

//    @SuppressWarnings("unchecked")
//	public List<Role> selectByUser(long id, String server) {
//        SqlSession session = sqlMapper.openSession();
//        try{
//        	long currentTimeMillis = System.currentTimeMillis();
//        	HashMap<String, Object> map = new HashMap<String, Object>();
//        	map.put("userid", id);
//        	map.put("server", server);
//        	List<Role> list = (List<Role>)session.selectList("game_role.selectByUser", map);
//        	LoggerProvider.getDbconsuminglog().info("game_role.selectByUser "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
//            return list;
//    	}finally{
//			session.close();
//		}
//    }

    @SuppressWarnings("unchecked")
    public List<String> selectNames() {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	List<String> list = (List<String>)session.selectList("game_role.selectNames");
        	LoggerProvider.getDbconsuminglog().info("game_role.selectNames "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}finally{
			session.close();
		}
    }
    
    public Role selectById(long id) {
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		Role record = (Role)session.selectOne("game_role.selectById", id);
    		LoggerProvider.getDbconsuminglog().info("game_role.selectById "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return record;
    	}finally{
			session.close();
		}
    }
//    
//    public int selectByName(String name) throws SQLException {
//    	SqlSession session = sqlMapper.openSession();
//    	try{
//    		int record = (Integer)session.selectOne("game_role.selectByName", name);
//            return record;
//    	}catch (Exception e) {
//			log.error(e);
//		}finally{
//			session.close();
//		}
//    	return 0;
//    }

    public int update(Role role) {
        SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.update("game_role.update", role);
    		session.commit();
    		LoggerProvider.getDbconsuminglog().info("game_role.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return rows;
    	}finally{
			session.close();
		}
    }
}