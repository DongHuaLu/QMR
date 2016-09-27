package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horseweapon_skill Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_horseweapon_skillBean;

public class Q_horseweapon_skillDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_horseweapon_skillBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_horseweapon_skillBean> list = (List<Q_horseweapon_skillBean>)session.selectList("q_horseweapon_skill.select");
            return list;
    	}finally{
			session.close();
		}
    }
}