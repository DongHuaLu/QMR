package com.game.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.BaseDao;
import com.game.db.bean.Role;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class RoleDao extends BaseDao {

	private Logger log = Logger.getLogger(RoleDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();
	
	public int getcountbylv(int lv) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int count = (Integer) session.selectOne("game_role.getcountbylv", lv);
			LoggerProvider.getDbconsuminglog().info("game_role.getcountbylv "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return count;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Role> selectbylv(int lv) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Role> list = (List<Role>) session.selectList("game_role.selectbylv", lv);
			LoggerProvider.getDbconsuminglog().info("game_role.selectbylv "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return new ArrayList<Role>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> selectbeginandendbylv(int lv, int begin, int end) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("level", lv);
			map.put("beginidx", begin);
			map.put("endidx", end);
			List<Role> list = (List<Role>) session.selectList("game_role.selectbeginandendbylv", map);
			LoggerProvider.getDbconsuminglog().info("game_role.selectbeginandendbylv "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return new ArrayList<Role>();
	}

	@SuppressWarnings("unchecked")
	public List<Role> selectByUser(String name, int server) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("userid", name);
			map.put("server", server);
			List<Role> list = (List<Role>) session.selectList("game_role.selectByUser", map);
			LoggerProvider.getDbconsuminglog().info("game_role.selectByUser "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return new ArrayList<Role>();
	}

	public int selectByName(String name) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int record = (Integer) session.selectOne("game_role.selectByName", name);
			LoggerProvider.getDbconsuminglog().info("game_role.selectByName "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return record;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Role> selectDelByUser(String name, int server) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("userid", name);
			map.put("server", server);
			List<Role> list = (List<Role>) session.selectList("game_role.selectDelByUser", map);
			LoggerProvider.getDbconsuminglog().info("game_role.selectDelByUser "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return new ArrayList<Role>();
	}

	public Role selectById(long id) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			Role role = (Role) session.selectOne("game_role.selectById", id);
			LoggerProvider.getDbconsuminglog().info("game_role.selectById "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return role;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return null;
	}

	public int update(Role role) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.update("game_role.update", role);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("game_role.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}

	public int updateName(Role role) throws SQLException {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.update("game_role.updateName", role);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("game_role.updateName "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
}