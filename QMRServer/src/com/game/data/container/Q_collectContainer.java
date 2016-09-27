package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_collectBean;
import com.game.data.dao.Q_collectDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_collect数据容器
 */
public class Q_collectContainer {

	private List<Q_collectBean> list;
	
	private HashMap<Integer, Q_collectBean> map = new HashMap<Integer, Q_collectBean>();

	private Q_collectDao dao = new Q_collectDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_collectBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_collectBean bean = (Q_collectBean) iter
					.next();
			map.put(bean.getQ_coll_id(), bean);
		}
	}

	public List<Q_collectBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_collectBean> getMap(){
		return map;
	}
}