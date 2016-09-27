package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_filterword Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_filterwordBean;

public class Q_filterwordDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_filterwordBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_filterwordBean> list = (List<Q_filterwordBean>)session.selectList("q_filterword.select");
            return list;
    	}finally{
			session.close();
		}
    }
}