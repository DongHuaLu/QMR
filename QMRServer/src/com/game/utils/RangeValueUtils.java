package com.game.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_rangevalueBean;
import com.game.manager.ManagerPool;

/**
 * 范围取值工具
 * 返回输入值区间的百分比值
 * @author tangchao
 *
 */
public class RangeValueUtils {

	private static final Logger log = Logger.getLogger(RangeValueUtils.class);
	
	/**
	 * 根据输入的值返回所在区间的百分比值
	 * @author tangchao
	 * @param value
	 */
	public static int getRangeValue(int value, int groupid){		
		int ratio = 0;
		if(value<=0) return ratio;
		List<Q_rangevalueBean> fullbeans = ManagerPool.dataManager.q_rangevalueContainer.getList();			
		if(fullbeans.size()==0){
			log.error("范围值读取为空，请检查配置");
			return 0;
		}
		List<Q_rangevalueBean> beans = new ArrayList<Q_rangevalueBean>();
		//按groupid做筛选
		for(int index=0; index<fullbeans.size(); index++){
			if(fullbeans.get(index).getQ_groupid()==groupid){
				beans.add(fullbeans.get(index));
			}
		}			
		//按最大值降序排列
		Collections.sort(beans, new Comparator<Q_rangevalueBean>(){
			@Override
			public int compare(Q_rangevalueBean o1, Q_rangevalueBean o2) {				
				return o1.getQ_ceiling()-o2.getQ_ceiling();
			}
		});
		//容错判断
		if(value<beans.get(0).getQ_floor()) value = beans.get(0).getQ_floor();
		if(value>beans.get(beans.size()-1).getQ_ceiling()) value = beans.get(beans.size()-1).getQ_ceiling();
		//根据配置范围取得比值
		for(int index=0; index<beans.size(); index++){
			Q_rangevalueBean bean = beans.get(index);
			if(bean.getQ_floor() < value && value<= bean.getQ_ceiling()){
				ratio = bean.getQ_ratio();
				break;
			}
		}		
		return ratio;
	}

}
