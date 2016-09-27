package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scene_monster_area Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_scene_monster_areaBean;

public class Q_scene_monster_areaDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_scene_monster_areaBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_scene_monster_areaBean> list = (List<Q_scene_monster_areaBean>)session.selectList("q_scene_monster_area.select");
            return list;
    	}finally{
			session.close();
		}
    }
}