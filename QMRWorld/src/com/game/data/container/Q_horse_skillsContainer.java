package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_horse_skillsBean;
import com.game.data.dao.Q_horse_skillsDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horse_skills数据容器
 */
public class Q_horse_skillsContainer {

	private List<Q_horse_skillsBean> list;
	
	private HashMap<Integer, Q_horse_skillsBean> map = new HashMap<Integer, Q_horse_skillsBean>();

	private Q_horse_skillsDao dao = new Q_horse_skillsDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_horse_skillsBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_horse_skillsBean bean = (Q_horse_skillsBean) iter
					.next();
			map.put(bean.getQ_skillid(), bean);
		}
	}

	public List<Q_horse_skillsBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_horse_skillsBean> getMap(){
		return map;
	}
}