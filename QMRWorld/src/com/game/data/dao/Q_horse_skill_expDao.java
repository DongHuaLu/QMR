package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horse_skill_exp Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_horse_skill_expBean;

public class Q_horse_skill_expDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_horse_skill_expBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_horse_skill_expBean> list = (List<Q_horse_skill_expBean>)session.selectList("q_horse_skill_exp.select");
            return list;
    	}finally{
			session.close();
		}
    }
}