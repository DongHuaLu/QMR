package com.game.task.struts;

import org.apache.log4j.Logger;

import java.util.Comparator;

import com.game.data.bean.Q_monsterBean;
import com.game.data.manager.DataManager;

public class MonsterModelComparator implements Comparator<Integer> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MonsterModelComparator.class);

	@Override
	public int compare(Integer model1, Integer model2) {
		Q_monsterBean monster1 = DataManager.getInstance().q_monsterContainer.getMap().get(model1);
		Q_monsterBean monster2 = DataManager.getInstance().q_monsterContainer.getMap().get(model2);
		if(monster1!=null&&monster2!=null){
			//先按目标怪物等级排序
			if(monster1.getQ_grade()!=monster2.getQ_grade()){
				return monster1.getQ_grade()-monster2.getQ_grade();	
			}
			//怪物等级一样的话按任务模型排序
			if(model1!=model2){
				return model1-model2;
			}
		}else{
			logger.error("找不到的怪物ID1:"+model1+"_ID2:"+model2);		
		}
		return 0;
	}

}
