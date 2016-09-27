package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_fightspiritBean;
import com.game.data.dao.Q_fightspiritDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_fightspirit数据容器
 */
public class Q_fightspiritContainer {

	private List<Q_fightspiritBean> list;
	
	private HashMap<String, Q_fightspiritBean> map = new HashMap<String, Q_fightspiritBean>();

	private Q_fightspiritDao dao = new Q_fightspiritDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_fightspiritBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_fightspiritBean bean = (Q_fightspiritBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_fightspiritBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_fightspiritBean> getMap(){
		return map;
	}
}