package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_buff Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_buffBean;

public class Q_buffDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_buffBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_buffBean> list = (List<Q_buffBean>)session.selectList("q_buff.select");
            return list;
    	}finally{
			session.close();
		}
    }
}