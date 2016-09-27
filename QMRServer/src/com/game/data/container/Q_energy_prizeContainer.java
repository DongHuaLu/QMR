package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_energy_prizeBean;
import com.game.data.dao.Q_energy_prizeDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_energy_prize数据容器
 */
public class Q_energy_prizeContainer {

	private List<Q_energy_prizeBean> list;
	
	private HashMap<Integer, Q_energy_prizeBean> map = new HashMap<Integer, Q_energy_prizeBean>();

	private Q_energy_prizeDao dao = new Q_energy_prizeDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_energy_prizeBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_energy_prizeBean bean = (Q_energy_prizeBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_energy_prizeBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_energy_prizeBean> getMap(){
		return map;
	}
}