package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_petattributeBean;
import com.game.data.dao.Q_petattributeDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_petattribute数据容器
 */
public class Q_petattributeContainer {

	private List<Q_petattributeBean> list;
	
	private HashMap<String, Q_petattributeBean> map = new HashMap<String, Q_petattributeBean>();

	private Q_petattributeDao dao = new Q_petattributeDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_petattributeBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_petattributeBean bean = (Q_petattributeBean) iter
					.next();
			map.put(bean.getQ_id(), bean);
		}
	}

	public List<Q_petattributeBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_petattributeBean> getMap(){
		return map;
	}
}