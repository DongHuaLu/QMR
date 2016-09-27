package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_transferBean;
import com.game.data.dao.Q_transferDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_transfer数据容器
 */
public class Q_transferContainer {

	private List<Q_transferBean> list;
	
	private HashMap<Integer, Q_transferBean> map = new HashMap<Integer, Q_transferBean>();

	private Q_transferDao dao = new Q_transferDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_transferBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_transferBean bean = (Q_transferBean) iter
					.next();
			map.put(bean.getQ_tran_id(), bean);
		}
	}

	public List<Q_transferBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_transferBean> getMap(){
		return map;
	}
}