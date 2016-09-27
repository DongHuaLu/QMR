package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_map Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_mapBean;

public class Q_mapDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_mapBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_mapBean> list = (List<Q_mapBean>)session.selectList("q_map.select");
            return list;
    	}finally{
			session.close();
		}
    }
}