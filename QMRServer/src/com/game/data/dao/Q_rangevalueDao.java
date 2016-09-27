package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_rangevalue Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_rangevalueBean;

public class Q_rangevalueDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_rangevalueBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_rangevalueBean> list = (List<Q_rangevalueBean>)session.selectList("q_rangevalue.select");
            return list;
    	}finally{
			session.close();
		}
    }
}