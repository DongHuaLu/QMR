package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_minimapshow Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_minimapshowBean;

public class Q_minimapshowDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_minimapshowBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_minimapshowBean> list = (List<Q_minimapshowBean>)session.selectList("q_minimapshow.select");
            return list;
    	}finally{
			session.close();
		}
    }
}