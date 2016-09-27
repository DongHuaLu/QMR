package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gem_activation Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_gem_activationBean;

public class Q_gem_activationDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_gem_activationBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_gem_activationBean> list = (List<Q_gem_activationBean>)session.selectList("q_gem_activation.select");
            return list;
    	}finally{
			session.close();
		}
    }
}