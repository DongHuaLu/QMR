package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_arrow_starBean;
import com.game.data.dao.Q_arrow_starDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_arrow_star数据容器
 */
public class Q_arrow_starContainer {

	private List<Q_arrow_starBean> list;
	
	private HashMap<String, Q_arrow_starBean> map = new HashMap<String, Q_arrow_starBean>();

	private Q_arrow_starDao dao = new Q_arrow_starDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_arrow_starBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_arrow_starBean bean = (Q_arrow_starBean) iter
					.next();
			map.put(bean.getQ_star_id(), bean);
		}
	}

	public List<Q_arrow_starBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_arrow_starBean> getMap(){
		return map;
	}
}