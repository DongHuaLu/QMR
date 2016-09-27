package com.game.db.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * Phone_card Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.db.BaseDao;
import com.game.db.bean.Phone_cardBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Phone_cardDao extends BaseDao {

	private Logger log = Logger.getLogger(Phone_cardDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<Phone_cardBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<Phone_cardBean> list = (List<Phone_cardBean>) session.selectList("phone_card.select");
			LoggerProvider.getDbconsuminglog().info("phone_card.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return new ArrayList<Phone_cardBean>();
	}
	
	public Phone_cardBean selectbyaccount(String account) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			Phone_cardBean retBean = (Phone_cardBean) session.selectOne("phone_card.selectbyaccount", account);
			LoggerProvider.getDbconsuminglog().info("phone_card.selectbyaccount "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return retBean;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return null;
	}
	
	public int insert(Phone_cardBean phone_cardBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("phone_card.insert", phone_cardBean);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("phone_card.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	public int update(Phone_cardBean phone_cardBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.update("phone_card.update", phone_cardBean);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("phone_card.update "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
	
	public int delete(String account) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.delete("phone_card.delete", account);
			session.commit();
			LoggerProvider.getDbconsuminglog().info("phone_card.delete "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return rows;
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			session.close();
		}
		return 0;
	}
}