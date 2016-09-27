package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_longyuan_exp Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_longyuan_expBean;

public class Q_longyuan_expDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_longyuan_expBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_longyuan_expBean> list = (List<Q_longyuan_expBean>)session.selectList("q_longyuan_exp.select");
            return list;
    	}finally{
			session.close();
		}
    }
}