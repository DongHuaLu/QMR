package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_jiaochang Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_jiaochangBean;

public class Q_jiaochangDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_jiaochangBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_jiaochangBean> list = (List<Q_jiaochangBean>)session.selectList("q_jiaochang.select");
            return list;
    	}finally{
			session.close();
		}
    }
}