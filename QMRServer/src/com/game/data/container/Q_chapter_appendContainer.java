package com.game.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.data.bean.Q_chapter_appendBean;
import com.game.data.dao.Q_chapter_appendDao;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_chapter_append数据容器
 */
public class Q_chapter_appendContainer {

	private List<Q_chapter_appendBean> list;
	
	private HashMap<Integer, Q_chapter_appendBean> map = new HashMap<Integer, Q_chapter_appendBean>();

	private Q_chapter_appendDao dao = new Q_chapter_appendDao();
	
	public void load(){
		list = dao.select();
		Iterator<Q_chapter_appendBean> iter = list.iterator();
		while (iter.hasNext()) {
			Q_chapter_appendBean bean = (Q_chapter_appendBean) iter
					.next();
			map.put(bean.getQ_chapter(), bean);
		}
	}

	public List<Q_chapter_appendBean> getList(){
		return list;
	}
	
	public HashMap<Integer, Q_chapter_appendBean> getMap(){
		return map;
	}
}