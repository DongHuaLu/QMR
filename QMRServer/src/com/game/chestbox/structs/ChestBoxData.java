package com.game.chestbox.structs;

import com.game.chestbox.bean.ChestBoxInfo;
import com.game.object.GameObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 宝箱玩法数据
 *
 * @author 杨鸿岚
 */
public class ChestBoxData extends GameObject{
	
	private static final long serialVersionUID = 7164222563413472118L;
	//开启次数
	private int opennum;
	//是否自动
	private int autoopen = 1;
	//内圈选中编号
	private int inchooseidx = -1;
	//外圈选中编号
	private int outchooseidx = -1;
	//内圈格子列表
	private HashMap<String, ChestGridData> ingridmap = new HashMap<String, ChestGridData>();
	//外圈格子列表
	private HashMap<String, ChestGridData> outgridmap = new HashMap<String, ChestGridData>();
	//随机出的外圈进入内圈需要加权的格子编号
	private List<Integer> adddropinList = new ArrayList<Integer>();

	public List<Integer> getAdddropinList() {
		return adddropinList;
	}

	public void setAdddropinList(List<Integer> adddropinList) {
		this.adddropinList = adddropinList;
	}

	public int getAutoopen() {
		return autoopen;
	}

	public void setAutoopen(int autoopen) {
		this.autoopen = autoopen;
	}

	public int getInchooseidx() {
		return inchooseidx;
	}

	public void setInchooseidx(int inchooseidx) {
		this.inchooseidx = inchooseidx;
	}

	public HashMap<String, ChestGridData> getIngridmap() {
		return ingridmap;
	}

	public void setIngridmap(HashMap<String, ChestGridData> ingridmap) {
		this.ingridmap = ingridmap;
	}

	public int getOpennum() {
		return opennum;
	}

	public void setOpennum(int opennum) {
		this.opennum = opennum;
	}

	public int getOutchooseidx() {
		return outchooseidx;
	}

	public void setOutchooseidx(int outchooseidx) {
		this.outchooseidx = outchooseidx;
	}

	public HashMap<String, ChestGridData> getOutgridmap() {
		return outgridmap;
	}

	public void setOutgridmap(HashMap<String, ChestGridData> outgridmap) {
		this.outgridmap = outgridmap;
	}
	
	public ChestBoxInfo toInfo(){
		ChestBoxInfo chestBoxInfo = new ChestBoxInfo();
		chestBoxInfo.setOpennum(opennum);
		chestBoxInfo.setAutoopen(autoopen);
		chestBoxInfo.setInchooseidx(inchooseidx);
		chestBoxInfo.setOutchooseidx(outchooseidx);
		Iterator<ChestGridData> iterator = ingridmap.values().iterator();
		while(iterator.hasNext()) {
			ChestGridData chestGridData =  iterator.next();
			if (chestGridData != null) {
				chestBoxInfo.getIngridlist().add(chestGridData.toInfo());
			}
		}
		iterator = outgridmap.values().iterator();
		while(iterator.hasNext()) {
			ChestGridData chestGridData =  iterator.next();
			if (chestGridData != null) {
				chestBoxInfo.getOutgridlist().add(chestGridData.toInfo());
			}
		}
		return chestBoxInfo;
	}
}
