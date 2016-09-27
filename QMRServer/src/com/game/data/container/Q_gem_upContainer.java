package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_gem_upBean;
import com.game.data.dao.Q_gem_upDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gem_up数据容器
 */
public class Q_gem_upContainer {

	private List<Q_gem_upBean> list;
	
	private HashMap<String, Q_gem_upBean> map = new HashMap<String, Q_gem_upBean>();

	private Q_gem_upDao dao = new Q_gem_upDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_gem_upBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_gem_upBean bean = (Q_gem_upBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_gem_upBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_gem_upBean> getMap(){
		return map;
	}
}