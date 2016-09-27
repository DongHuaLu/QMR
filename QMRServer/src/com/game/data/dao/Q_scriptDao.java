package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_script Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_scriptBean;

public class Q_scriptDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_scriptBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_scriptBean> list = (List<Q_scriptBean>)session.selectList("q_script.select");
            return list;
    	}finally{
			session.close();
		}
    }
}