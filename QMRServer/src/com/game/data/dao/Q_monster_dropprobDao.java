package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_monster_dropprob Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_monster_dropprobBean;

public class Q_monster_dropprobDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_monster_dropprobBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_monster_dropprobBean> list = (List<Q_monster_dropprobBean>)session.selectList("q_monster_dropprob.select");
            return list;
    	}finally{
			session.close();
		}
    }
}