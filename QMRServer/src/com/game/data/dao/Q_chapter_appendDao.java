package com.game.data.dao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_chapter_append Dao
 */
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.data.BaseDao;
import com.game.data.bean.Q_chapter_appendBean;

public class Q_chapter_appendDao extends BaseDao {

	private SqlSessionFactory sqlMapper = getSqlMapper();

    @SuppressWarnings("unchecked")
	public List<Q_chapter_appendBean> select() {
        SqlSession session = sqlMapper.openSession();
        try{
        	List<Q_chapter_appendBean> list = (List<Q_chapter_appendBean>)session.selectList("q_chapter_append.select");
            return list;
    	}finally{
			session.close();
		}
    }
}