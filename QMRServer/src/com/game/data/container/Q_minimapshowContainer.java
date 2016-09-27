package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_minimapshowBean;
import com.game.data.dao.Q_minimapshowDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_minimapshow数据容器
 */
public class Q_minimapshowContainer {

	private List<Q_minimapshowBean> list;
	
	private HashMap<Integer, Q_minimapshowBean> map = new HashMap<Integer, Q_minimapshowBean>();

	private Q_minimapshowDao dao = new Q_minimapshowDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_minimapshowBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_minimapshowBean bean = (Q_minimapshowBean) iter
					.next();
			map.put(bean.getQ_mapid(), bean);
		}
	}

	public List<Q_minimapshowBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_minimapshowBean> getMap(){
		return map;
	}
}