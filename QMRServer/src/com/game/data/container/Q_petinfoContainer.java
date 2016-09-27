package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_petinfoBean;
import com.game.data.dao.Q_petinfoDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_petinfo数据容器
 */
public class Q_petinfoContainer {

	private List<Q_petinfoBean> list;
	
	private HashMap<Integer, Q_petinfoBean> map = new HashMap<Integer, Q_petinfoBean>();

	private Q_petinfoDao dao = new Q_petinfoDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_petinfoBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_petinfoBean bean = (Q_petinfoBean) iter
					.next();
			map.put(bean.getQ_model_id(), bean);
		}
	}

	public List<Q_petinfoBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_petinfoBean> getMap(){
		return map;
	}
}