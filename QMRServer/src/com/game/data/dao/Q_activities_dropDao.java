package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_activities_drop Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_activities_dropBean;

public class Q_activities_dropDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_activities_dropBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_activities_dropBean> list = (List<Q_activities_dropBean>)session.selectList("q_activities_drop.select");
            return list;
    	}finally{
			session.close();
		}
    }
}