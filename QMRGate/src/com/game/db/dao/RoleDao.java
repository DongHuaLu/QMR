package com.game.db.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.Role;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class RoleDao {

	private Logger log = Logger.getLogger(RoleDao.class);
	
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

    public int delete(long id) {
    	SqlSession session = sqlMapper.openSession();
    	try{
    		long currentTimeMillis = System.currentTimeMillis();
    		int rows = session.delete("game_role.delete", id);
    		LoggerProvider.getDbconsuminglog().info("game_role.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
    		return rows;
    	}finally{
			session.close();
		}
    }

    @SuppressWarnings("unchecked")
	public List<Role> selectByUser(String name, int server) {
        SqlSession session = sqlMapper.openSession();
        try{
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("userid", name);
        	long start = System.currentTimeMillis();
        	long currentTimeMillis = System.currentTimeMillis();
        	List<Role> list = (List<Role>)session.selectList("game_role.selectByUser", map);
        	long end = System.currentTimeMillis();
        	log.error("select rolelist cost:" + (end - start));
        	LoggerProvider.getDbconsuminglog().info("game_role.selectByUser "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return list;
    	}finally{
			session.close();
		}
    }
    
	public Role selectById(long id) {
        SqlSession session = sqlMapper.openSession();
        try{
        	long currentTimeMillis = System.currentTimeMillis();
        	long start = System.currentTimeMillis();
        	Role role = (Role)session.selectOne("game_role.selectById", id);
        	long end = System.currentTimeMillis();
        	log.error("select role cost:" + (end - start));
        	LoggerProvider.getDbconsuminglog().info("game_role.selectById "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
            return role;
    	}finally{
			session.close();
		}
    }
	
//	public int update(Role role) {
//        SqlSession session = sqlMapper.openSession();
//    	try{
//    		long currentTimeMillis = System.currentTimeMillis();
//    		int rows = session.update("game_role.update", role);
//    		session.commit();
//    		LoggerProvider.getDbconsuminglog().info("game_role.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
//            return rows;
//    	}finally{
//			session.close();
//		}
//    }

}