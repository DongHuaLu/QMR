package com.game.collect.struts;

import java.util.HashMap;

import com.game.object.GameObject;

/**
 * 收藏品
 * @author 赵聪慧
 * @2012-11-26 上午11:48:30
 */
public class CollectItem extends GameObject {

	private static final long serialVersionUID = 3214533833877367994L;
	private int modelId;
	private HashMap<String,Integer> collectCount=new HashMap<String, Integer>();
	
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public HashMap<String, Integer> getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(HashMap<String, Integer> collectCount) {
		this.collectCount = collectCount;
	}	
}
