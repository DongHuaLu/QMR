package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_fightspirit Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_fightspiritBean;

public class Q_fightspiritDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_fightspiritBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_fightspiritBean> list = (List<Q_fightspiritBean>)session.selectList("q_fightspirit.select");
            return list;
    	}finally{
			session.close();
		}
    }
}