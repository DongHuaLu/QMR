package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_filterwordBean;
import com.game.data.dao.Q_filterwordDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_filterword数据容器
 */
public class Q_filterwordContainer {

	private List<Q_filterwordBean> list;
	
	private HashMap<String, Q_filterwordBean> map = new HashMap<String, Q_filterwordBean>();

	private Q_filterwordDao dao = new Q_filterwordDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_filterwordBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_filterwordBean bean = (Q_filterwordBean) iter
					.next();
			map.put(bean.getQ_country(), bean);
		}
	}

	public List<Q_filterwordBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_filterwordBean> getMap(){
		return map;
	}
}