package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scene_obj Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_scene_objBean;

public class Q_scene_objDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_scene_objBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_scene_objBean> list = (List<Q_scene_objBean>)session.selectList("q_scene_obj.select");
            return list;
    	}finally{
			session.close();
		}
    }
}