package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_skill_model Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_skill_modelBean;

public class Q_skill_modelDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_skill_modelBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_skill_modelBean> list = (List<Q_skill_modelBean>)session.selectList("q_skill_model.select");
            return list;
    	}finally{
			session.close();
		}
    }
}