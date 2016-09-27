package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_globalBean;
import com.game.data.dao.Q_globalDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_global数据容器
 */
public class Q_globalContainer {

	private List<Q_globalBean> list;
	
	private HashMap<Integer, Q_globalBean> map = new HashMap<Integer, Q_globalBean>();

	private Q_globalDao dao = new Q_globalDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_globalBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_globalBean bean = (Q_globalBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_globalBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_globalBean> getMap(){
		return map;
	}
}