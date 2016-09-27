package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_sign_wageBean;
import com.game.data.dao.Q_sign_wageDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_sign_wage数据容器
 */
public class Q_sign_wageContainer {

	private List<Q_sign_wageBean> list;
	
	private HashMap<Integer, Q_sign_wageBean> map = new HashMap<Integer, Q_sign_wageBean>();

	private Q_sign_wageDao dao = new Q_sign_wageDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_sign_wageBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_sign_wageBean bean = (Q_sign_wageBean) iter
					.next();
			map.put(bean.getQ_sign_num(), bean);
		}
	}

	public List<Q_sign_wageBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_sign_wageBean> getMap(){
		return map;
	}
}