package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_arrow_star Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_arrow_starBean;

public class Q_arrow_starDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_arrow_starBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_arrow_starBean> list = (List<Q_arrow_starBean>)session.selectList("q_arrow_star.select");
            return list;
    	}finally{
			session.close();
		}
    }
}