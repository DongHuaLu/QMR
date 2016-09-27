package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_global Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_globalBean;

public class Q_globalDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_globalBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_globalBean> list = (List<Q_globalBean>)session.selectList("q_global.select");
            return list;
    	}finally{
			session.close();
		}
    }
}