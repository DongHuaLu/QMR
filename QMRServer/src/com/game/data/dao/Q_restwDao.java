package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_restw Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_restwBean;

public class Q_restwDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_restwBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_restwBean> list = (List<Q_restwBean>)session.selectList("q_restw.select");
            return list;
    	}finally{
			session.close();
		}
    }
}