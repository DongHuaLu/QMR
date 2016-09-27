package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_realm_basic Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_realm_basicBean;

public class Q_realm_basicDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_realm_basicBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_realm_basicBean> list = (List<Q_realm_basicBean>)session.selectList("q_realm_basic.select");
            return list;
    	}finally{
			session.close();
		}
    }
}