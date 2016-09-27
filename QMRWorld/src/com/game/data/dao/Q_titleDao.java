package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_title Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_titleBean;

public class Q_titleDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_titleBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_titleBean> list = (List<Q_titleBean>)session.selectList("q_title.select");
            return list;
    	}finally{
			session.close();
		}
    }
}