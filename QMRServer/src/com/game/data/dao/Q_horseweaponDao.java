package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horseweapon Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_horseweaponBean;

public class Q_horseweaponDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_horseweaponBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_horseweaponBean> list = (List<Q_horseweaponBean>)session.selectList("q_horseweapon.select");
            return list;
    	}finally{
			session.close();
		}
    }
}