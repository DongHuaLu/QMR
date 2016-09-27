package com.game.player.manager;

import java.util.ArrayList;
import java.util.List;

import com.game.data.bean.Q_role_random_nameBean;
import com.game.data.manager.DataManager;
import com.game.utils.RandomUtils;


public class PlayerRandomNameManager {
	private static PlayerRandomNameManager instance=new PlayerRandomNameManager();
	public static PlayerRandomNameManager getInstance(){
		return instance;
	}
	public String randomRoleName(boolean isMale) {
		List<Q_role_random_nameBean> list = DataManager.getInstance().q_role_random_nameContainer.getList();
		List<String> firstName=new ArrayList<String>();
		List<String> lastName=new ArrayList<String>();
		for (Q_role_random_nameBean q_role_random_nameBean : list) {
			if(q_role_random_nameBean.getQ_type()==1){
				firstName.add(q_role_random_nameBean.getQ_value());
			}else if(q_role_random_nameBean.getQ_type()==2&&isMale){
				lastName.add(q_role_random_nameBean.getQ_value());
			}else if(q_role_random_nameBean.getQ_type()==3&&!isMale){
				lastName.add(q_role_random_nameBean.getQ_value());
			}
		}
		int firstindex = RandomUtils.random(firstName.size());
		int lastindex = RandomUtils.random(lastName.size());
		return firstName.get(firstindex)+lastName.get(lastindex);
	}

}
