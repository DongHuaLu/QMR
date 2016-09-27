package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_arrowBean;
import com.game.data.dao.Q_arrowDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_arrow数据容器
 */
public class Q_arrowContainer {

	private List<Q_arrowBean> list;
	
	private HashMap<Integer, Q_arrowBean> map = new HashMap<Integer, Q_arrowBean>();

	private Q_arrowDao dao = new Q_arrowDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_arrowBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_arrowBean bean = (Q_arrowBean) iter
					.next();
			map.put(bean.getQ_arrow_id(), bean);
		}
	}

	public List<Q_arrowBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_arrowBean> getMap(){
		return map;
	}
}