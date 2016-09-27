package com.game.db.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * Guildmember Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.BaseDao;
import com.game.db.bean.GuildmemberBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class GuildmemberDao extends BaseDao {

	private Logger log = Logger.getLogger(GuildmemberDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<GuildmemberBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<GuildmemberBean> list = (List<GuildmemberBean>) session.selectList("guildmember.select");
			LoggerProvider.getDbconsuminglog().info("guildmember.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	public List<GuildmemberBean> selectbyid(long guildid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<GuildmemberBean> list = (List<GuildmemberBean>) session.selectList("guildmember.selectbyid",guildid);
			LoggerProvider.getDbconsuminglog().info("guildmember.selectbyid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	public int insert(GuildmemberBean guildmemberBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("guildmember.insert", guildmemberBean);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("guildmember.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}

	public int update(GuildmemberBean guildmemberBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.update("guildmember.update", guildmemberBean);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("guildmember.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}

	public int delete(Long userid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("guildmember.delete", userid);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("guildmember.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
}