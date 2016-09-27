package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_backpack_gridBean;
import com.game.data.dao.Q_backpack_gridDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_backpack_grid数据容器
 */
public class Q_backpack_gridContainer {

	private List<Q_backpack_gridBean> list;
	
	private HashMap<String, Q_backpack_gridBean> map = new HashMap<String, Q_backpack_gridBean>();

	private Q_backpack_gridDao dao = new Q_backpack_gridDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_backpack_gridBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_backpack_gridBean bean = (Q_backpack_gridBean) iter
					.next();
			map.put(bean.getQ_backpack_q_grid(), bean);
		}
	}

	public List<Q_backpack_gridBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_backpack_gridBean> getMap(){
		return map;
	}
}