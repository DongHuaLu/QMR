package com.game.data.dao;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * Q_card Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_cardBean;

public class Q_cardDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

	@SuppressWarnings("unchecked")
	public List<Q_cardBean> select() {
		SqlSession session = sqlMapper.openSession();
		try {
			List<Q_cardBean> list = (List<Q_cardBean>) session.selectList("q_card.select");
			return list;
		} finally {
			session.close();
		}
	}
}