package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_longyuan_expBean;
import com.game.data.dao.Q_longyuan_expDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_longyuan_exp数据容器
 */
public class Q_longyuan_expContainer {

	private List<Q_longyuan_expBean> list;
	
	private HashMap<Integer, Q_longyuan_expBean> map = new HashMap<Integer, Q_longyuan_expBean>();

	private Q_longyuan_expDao dao = new Q_longyuan_expDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_longyuan_expBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_longyuan_expBean bean = (Q_longyuan_expBean) iter
					.next();
			map.put(bean.getQ_lv(), bean);
		}
	}

	public List<Q_longyuan_expBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_longyuan_expBean> getMap(){
		return map;
	}
}