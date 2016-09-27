package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_equip_appendBean;
import com.game.data.dao.Q_equip_appendDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_equip_append数据容器
 */
public class Q_equip_appendContainer {

	private List<Q_equip_appendBean> list;
	
	private HashMap<Integer, Q_equip_appendBean> map = new HashMap<Integer, Q_equip_appendBean>();

	private Q_equip_appendDao dao = new Q_equip_appendDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_equip_appendBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_equip_appendBean bean = (Q_equip_appendBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_equip_appendBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_equip_appendBean> getMap(){
		return map;
	}
}