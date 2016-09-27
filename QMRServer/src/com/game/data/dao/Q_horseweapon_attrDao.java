package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horseweapon_attr Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_horseweapon_attrBean;

public class Q_horseweapon_attrDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_horseweapon_attrBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_horseweapon_attrBean> list = (List<Q_horseweapon_attrBean>)session.selectList("q_horseweapon_attr.select");
            return list;
    	}finally{
			session.close();
		}
    }
}