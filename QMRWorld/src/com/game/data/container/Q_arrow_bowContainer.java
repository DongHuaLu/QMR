package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_arrow_bowBean;
import com.game.data.dao.Q_arrow_bowDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_arrow_bow数据容器
 */
public class Q_arrow_bowContainer {

	private List<Q_arrow_bowBean> list;
	
	private HashMap<String, Q_arrow_bowBean> map = new HashMap<String, Q_arrow_bowBean>();

	private Q_arrow_bowDao dao = new Q_arrow_bowDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_arrow_bowBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_arrow_bowBean bean = (Q_arrow_bowBean) iter
					.next();
			map.put(bean.getQ_bow_id(), bean);
		}
	}

	public List<Q_arrow_bowBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_arrow_bowBean> getMap(){
		return map;
	}
}