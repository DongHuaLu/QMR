package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_fourjinsuoBean;
import com.game.data.dao.Q_fourjinsuoDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_fourjinsuo数据容器
 */
public class Q_fourjinsuoContainer {

	private List<Q_fourjinsuoBean> list;
	
	private HashMap<Integer, Q_fourjinsuoBean> map = new HashMap<Integer, Q_fourjinsuoBean>();

	private Q_fourjinsuoDao dao = new Q_fourjinsuoDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_fourjinsuoBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_fourjinsuoBean bean = (Q_fourjinsuoBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_fourjinsuoBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_fourjinsuoBean> getMap(){
		return map;
	}
}