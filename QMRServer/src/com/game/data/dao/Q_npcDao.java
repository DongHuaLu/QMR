package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_npc Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_npcBean;

public class Q_npcDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_npcBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_npcBean> list = (List<Q_npcBean>)session.selectList("q_npc.select");
            return list;
    	}finally{
			session.close();
		}
    }
}