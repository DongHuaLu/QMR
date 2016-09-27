package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_rank Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_rankBean;

public class Q_rankDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_rankBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_rankBean> list = (List<Q_rankBean>)session.selectList("q_rank.select");
            return list;
    	}finally{
			session.close();
		}
    }
}