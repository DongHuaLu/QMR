package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_meting_random Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_meting_randomBean;

public class Q_meting_randomDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_meting_randomBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_meting_randomBean> list = (List<Q_meting_randomBean>)session.selectList("q_meting_random.select");
            return list;
    	}finally{
			session.close();
		}
    }
}