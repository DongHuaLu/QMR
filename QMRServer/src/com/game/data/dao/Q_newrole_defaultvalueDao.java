package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_newrole_defaultvalue Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_newrole_defaultvalueBean;

public class Q_newrole_defaultvalueDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_newrole_defaultvalueBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_newrole_defaultvalueBean> list = (List<Q_newrole_defaultvalueBean>)session.selectList("q_newrole_defaultvalue.select");
            return list;
    	}finally{
			session.close();
		}
    }
}