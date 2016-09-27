package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_horse_skill_expBean;
import com.game.data.dao.Q_horse_skill_expDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horse_skill_exp数据容器
 */
public class Q_horse_skill_expContainer {

	private List<Q_horse_skill_expBean> list;
	
	private HashMap<String, Q_horse_skill_expBean> map = new HashMap<String, Q_horse_skill_expBean>();

	private Q_horse_skill_expDao dao = new Q_horse_skill_expDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_horse_skill_expBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_horse_skill_expBean bean = (Q_horse_skill_expBean) iter
					.next();
			map.put(bean.getQ_skillid(), bean);
		}
	}

	public List<Q_horse_skill_expBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_horse_skill_expBean> getMap(){
		return map;
	}
}