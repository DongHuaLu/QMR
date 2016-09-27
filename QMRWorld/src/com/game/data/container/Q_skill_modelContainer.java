package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_skill_modelBean;
import com.game.data.dao.Q_skill_modelDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_skill_model数据容器
 */
public class Q_skill_modelContainer {

	private List<Q_skill_modelBean> list;
	
	private HashMap<String, Q_skill_modelBean> map = new HashMap<String, Q_skill_modelBean>();

	private Q_skill_modelDao dao = new Q_skill_modelDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_skill_modelBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_skill_modelBean bean = (Q_skill_modelBean) iter
					.next();
			map.put(bean.getQ_skillID_q_grade(), bean);
		}
	}

	public List<Q_skill_modelBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_skill_modelBean> getMap(){
		return map;
	}
}