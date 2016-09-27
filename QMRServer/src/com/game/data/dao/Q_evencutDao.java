package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_evencut Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_evencutBean;

public class Q_evencutDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_evencutBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_evencutBean> list = (List<Q_evencutBean>)session.selectList("q_evencut.select");
            return list;
    	}finally{
			session.close();
		}
    }
}