package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_collect Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_collectBean;

public class Q_collectDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_collectBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_collectBean> list = (List<Q_collectBean>)session.selectList("q_collect.select");
            return list;
    	}finally{
			session.close();
		}
    }
}