package com.game.db.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * Q_mail Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.Q_mailBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

public class Q_mailDao {

	private Logger log = Logger.getLogger(Q_mailDao.class);
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<Q_mailBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Q_mailBean> list = (List<Q_mailBean>) session.selectList("mail.select");
			LoggerProvider.getDbconsuminglog().info("mail.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	public Q_mailBean selectbymailid(long mail_id) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			Q_mailBean q_mailBean = (Q_mailBean) session.selectOne("mail.selectbymailid", mail_id);
			LoggerProvider.getDbconsuminglog().info("mail.selectbymailid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return q_mailBean;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Q_mailBean> selectbyuserid(long userid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Q_mailBean> list = (List<Q_mailBean>) session.selectList("mail.selectbyuserid", userid);
			LoggerProvider.getDbconsuminglog().info("mail.selectbyuserid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Q_mailBean> selectnewbyuserid(long userid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Q_mailBean> list = (List<Q_mailBean>) session.selectList("mail.selectnewbyuserid", userid);
			LoggerProvider.getDbconsuminglog().info("mail.selectnewbyuserid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Q_mailBean> selectbysendtime(int sendtime) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Q_mailBean> list = (List<Q_mailBean>) session.selectList("mail.selectbysendtime", sendtime);
			LoggerProvider.getDbconsuminglog().info("mail.selectbysendtime "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	public int insert(Q_mailBean q_mailBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("mail.insert", q_mailBean);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("mail.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}

	public int update(Q_mailBean q_mailBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.update("mail.update", q_mailBean);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("mail.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	public int delete(long mail_id) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("mail.delete", mail_id);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("mail.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	public int deletebyuserid(long userid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("mail.deletebyuserid", userid);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("phone_card.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
}