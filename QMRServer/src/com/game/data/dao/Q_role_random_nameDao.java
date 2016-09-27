package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_role_random_name Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_role_random_nameBean;

public class Q_role_random_nameDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_role_random_nameBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_role_random_nameBean> list = (List<Q_role_random_nameBean>)session.selectList("q_role_random_name.select");
            return list;
    	}finally{
			session.close();
		}
    }
}