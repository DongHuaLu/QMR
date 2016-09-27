package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gem_up Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_gem_upBean;

public class Q_gem_upDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_gem_upBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_gem_upBean> list = (List<Q_gem_upBean>)session.selectList("q_gem_up.select");
            return list;
    	}finally{
			session.close();
		}
    }
}