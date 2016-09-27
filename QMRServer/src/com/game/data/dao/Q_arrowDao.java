package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_arrow Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_arrowBean;

public class Q_arrowDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_arrowBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_arrowBean> list = (List<Q_arrowBean>)session.selectList("q_arrow.select");
            return list;
    	}finally{
			session.close();
		}
    }
}