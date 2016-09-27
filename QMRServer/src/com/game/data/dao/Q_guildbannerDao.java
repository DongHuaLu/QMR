package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_guildbanner Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_guildbannerBean;

public class Q_guildbannerDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_guildbannerBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_guildbannerBean> list = (List<Q_guildbannerBean>)session.selectList("q_guildbanner.select");
            return list;
    	}finally{
			session.close();
		}
    }
}