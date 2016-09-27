package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_version Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_versionBean;

public class Q_versionDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_versionBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_versionBean> list = (List<Q_versionBean>)session.selectList("q_version.select");
            return list;
    	}finally{
			session.close();
		}
    }
}