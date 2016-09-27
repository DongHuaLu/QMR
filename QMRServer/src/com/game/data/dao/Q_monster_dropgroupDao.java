package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_monster_dropgroup Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_monster_dropgroupBean;

public class Q_monster_dropgroupDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_monster_dropgroupBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_monster_dropgroupBean> list = (List<Q_monster_dropgroupBean>)session.selectList("q_monster_dropgroup.select");
            return list;
    	}finally{
			session.close();
		}
    }
}