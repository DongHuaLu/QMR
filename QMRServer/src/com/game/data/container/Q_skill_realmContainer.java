package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_skill_realmBean;
import com.game.data.dao.Q_skill_realmDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_skill_realm数据容器
 */
public class Q_skill_realmContainer {

	private List<Q_skill_realmBean> list;
	
	private HashMap<Integer, Q_skill_realmBean> map = new HashMap<Integer, Q_skill_realmBean>();

	private Q_skill_realmDao dao = new Q_skill_realmDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_skill_realmBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_skill_realmBean bean = (Q_skill_realmBean) iter
					.next();
			map.put(bean.getQ_jingjieid(), bean);
		}
	}

	public List<Q_skill_realmBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_skill_realmBean> getMap(){
		return map;
	}
}