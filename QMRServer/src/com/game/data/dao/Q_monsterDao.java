package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_monster Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_monsterBean;

public class Q_monsterDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_monsterBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_monsterBean> list = (List<Q_monsterBean>)session.selectList("q_monster.select");
            return list;
    	}finally{
			session.close();
		}
    }
}