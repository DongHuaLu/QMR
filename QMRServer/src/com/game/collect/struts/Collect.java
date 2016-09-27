package com.game.collect.struts;

import java.util.HashMap;
import com.game.object.GameObject;

/**
 * 收藏对象
 * @author 赵聪慧
 * @2012-11-26 下午5:33:30
 */
public class Collect extends GameObject {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, CollectItem> infos=new HashMap<String, CollectItem>();
	
	public HashMap<String, CollectItem> getInfos() {
		return infos;
	}
	
	public void setInfos(HashMap<String,CollectItem> infos) {
		this.infos = infos;
	}
}
