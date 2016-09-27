package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_kiwi Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_spirittree_kiwiBean;

public class Q_spirittree_kiwiDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_spirittree_kiwiBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_spirittree_kiwiBean> list = (List<Q_spirittree_kiwiBean>)session.selectList("q_spirittree_kiwi.select");
            return list;
    	}finally{
			session.close();
		}
    }
}