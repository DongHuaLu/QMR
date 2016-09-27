package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_restwBean;
import com.game.data.dao.Q_restwDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_restw数据容器
 */
public class Q_restwContainer {

	private List<Q_restwBean> list;
	
	private HashMap<Integer, Q_restwBean> map = new HashMap<Integer, Q_restwBean>();

	private Q_restwDao dao = new Q_restwDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_restwBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_restwBean bean = (Q_restwBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_restwBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_restwBean> getMap(){
		return map;
	}
}