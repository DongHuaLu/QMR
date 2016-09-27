package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_meting_randomBean;
import com.game.data.dao.Q_meting_randomDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_meting_random数据容器
 */
public class Q_meting_randomContainer {

	private List<Q_meting_randomBean> list;
	
	private HashMap<String, Q_meting_randomBean> map = new HashMap<String, Q_meting_randomBean>();

	private Q_meting_randomDao dao = new Q_meting_randomDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_meting_randomBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_meting_randomBean bean = (Q_meting_randomBean) iter
					.next();
			map.put(bean.getQ_metingid(), bean);
		}
	}

	public List<Q_meting_randomBean> getList(){
		return list;
	}
	
	public HashMap<String, Q_meting_randomBean> getMap(){
		return map;
	}
}