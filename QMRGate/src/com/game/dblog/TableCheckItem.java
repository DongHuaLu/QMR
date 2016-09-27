package com.game.dblog;

import java.util.List;

import com.game.dblog.bean.BaseLogBean;
/**
 * 
 * @author 赵聪慧
 *
 */
public class TableCheckItem {
	//时间跨度
	private TableCheckStepEnum step;
	//注册的日志列表
	private List<BaseLogBean> list;
	//最后建表时间
	private long lastCreateTable;
	
	public TableCheckItem(TableCheckStepEnum step,List<BaseLogBean> list,long lastCreateTable){
		this.step=step;
		this.list=list;
		this.lastCreateTable=lastCreateTable;
	}
	public TableCheckStepEnum getStep() {
		return step;
	}
	public void setStep(TableCheckStepEnum step) {
		this.step = step;
	}
	public List<BaseLogBean> getList() {
		return list;
	}
	public void setList(List<BaseLogBean> list) {
		this.list = list;
	}
	public long getLastCreateTable() {
		return lastCreateTable;
	}
	public void setLastCreateTable(long lastCreateTable) {
		this.lastCreateTable = lastCreateTable;
	}
}
