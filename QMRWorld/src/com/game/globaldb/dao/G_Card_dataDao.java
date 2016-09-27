package com.game.globaldb.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * g_card_data Dao
 */
import com.game.globaldb.BaseDao;
import com.game.globaldb.bean.G_Card_dataBean;
import com.game.utils.LoggerProvider;
import com.game.utils.TimeUtil;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

public class G_Card_dataDao extends BaseDao {

	private Logger log = Logger.getLogger(G_Card_dataDao.class);
	private SqlSessionFactory sqlMapper = getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<G_Card_dataBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<G_Card_dataBean> list = (List<G_Card_dataBean>) session.selectList("g_card_data.select");
			LoggerProvider.getDbconsuminglog().info("g_card_data.select "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<G_Card_dataBean> selectbyid(String cardid) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<G_Card_dataBean> list = (List<G_Card_dataBean>) session.selectList("g_card_data.selectbyid", cardid);
			LoggerProvider.getDbconsuminglog().info("g_card_data.selectbyid "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<G_Card_dataBean> selectbyaccountandagidonly(G_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<G_Card_dataBean> list = (List<G_Card_dataBean>) session.selectList("g_card_data.selectbyaccountandagidonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("g_card_data.selectbyaccountandagidonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<G_Card_dataBean> selectbyaccountandtypeonly(G_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<G_Card_dataBean> list = (List<G_Card_dataBean>) session.selectList("g_card_data.selectbyaccountandtypeonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("g_card_data.selectbyaccountandtypeonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<G_Card_dataBean> selectbyroleidandagidonly(G_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<G_Card_dataBean> list = (List<G_Card_dataBean>) session.selectList("g_card_data.selectbyroleidandagidonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("g_card_data.selectbyroleidandagidonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<G_Card_dataBean> selectbyroleidandtypeonly(G_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			List<G_Card_dataBean> list = (List<G_Card_dataBean>) session.selectList("g_card_data.selectbyroleidandtypeonly", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("g_card_data.selectbyroleidandtypeonly "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			return list;
		} finally {
			session.close();
		}
	}

	public int insert(G_Card_dataBean card_dataBean) {
		SqlSession session = sqlMapper.openSession();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			int rows = session.insert("g_card_data.insert", card_dataBean);
			LoggerProvider.getDbconsuminglog().info("g_card_data.insert "+TimeUtil.getDurationToNow(currentTimeMillis)+"ms");
			session.commit();
			return rows;
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		return 0;
	}
}