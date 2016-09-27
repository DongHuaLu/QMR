package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_spirittree_kiwiBean;
import com.game.data.dao.Q_spirittree_kiwiDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_kiwi数据容器
 */
public class Q_spirittree_kiwiContainer {

	private List<Q_spirittree_kiwiBean> list;
	
	private HashMap<String, Q_spirittree_kiwiBean> map = new HashMap<String, Q_spirittree_kiwiBean>();

	private Q_spirittree_kiwiDao dao = new Q_spirittree_kiwiDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_spirittree_kiwiBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_spirittree_kiwiBean bean = (Q_spirittree_kiwiBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_spirittree_kiwiBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_spirittree_kiwiBean> getMap(){
		return map;
	}
}