package com.game.collect.manager;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.game.data.bean.Q_collectBean;
import com.game.data.manager.DataManager;
import com.game.utils.BeanUtil;

/**
 * 
 * @author 赵聪慧
 * @2012-11-26 下午9:01:23
 */
public class CollectModelManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CollectModelManager.class);

	private static CollectModelManager instance =new CollectModelManager();
	public static CollectModelManager getInstance(){
		return instance;
	}
	public List<Integer> getTypes(){
		List<Q_collectBean> list = DataManager.getInstance().q_collectContainer.getList();
		List<Integer> typelist=new ArrayList<Integer>();
		for (Q_collectBean model : list) {
			if(typelist.contains(model.getQ_coll_type())){
				continue;
			}
			typelist.add(model.getQ_coll_type());
		}
		return typelist;
	}
	
	public List<Q_collectBean> getCollectModelByType(int type){
		List<Q_collectBean> result=new ArrayList<Q_collectBean>();
		List<Q_collectBean> list = DataManager.getInstance().q_collectContainer.getList();
		for (Q_collectBean model : list) {
			if(model.getQ_coll_type()==type){
				result.add(model);
			}
		}
		return result;
	}
	
	public HashMap<Integer, Integer> getItemModels(Q_collectBean model) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 1; i <= 10; i++) {
			try {
				int itemmodel = (Integer) BeanUtil.getMethodValue(model, "Q_frag" + i + "_id");
				int num = (Integer) BeanUtil.getMethodValue(model, "Q_frag" + i + "_num");
				if (itemmodel>0&&num>0) {
					map.put(itemmodel, num);
				}
			} catch (Exception e) {
				logger.error(e,e);
			}
		}
		return map;
	}
	
	
	
	
	public int getCollectByModelId(int itemmodel){
		return 0;
	}
	
	
	

}
