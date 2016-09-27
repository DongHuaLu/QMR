package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_panel_dataBean;
import com.game.data.dao.Q_panel_dataDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_panel_data数据容器
 */
public class Q_panel_dataContainer {

	private List<Q_panel_dataBean> list;
	
	private HashMap<Integer, Q_panel_dataBean> map = new HashMap<Integer, Q_panel_dataBean>();

	private Q_panel_dataDao dao = new Q_panel_dataDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_panel_dataBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_panel_dataBean bean = (Q_panel_dataBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_panel_dataBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_panel_dataBean> getMap(){
		return map;
	}
}