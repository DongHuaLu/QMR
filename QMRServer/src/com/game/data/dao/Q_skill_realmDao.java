package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_skill_realm Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_skill_realmBean;

public class Q_skill_realmDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_skill_realmBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_skill_realmBean> list = (List<Q_skill_realmBean>)session.selectList("q_skill_realm.select");
            return list;
    	}finally{
			session.close();
		}
    }
}