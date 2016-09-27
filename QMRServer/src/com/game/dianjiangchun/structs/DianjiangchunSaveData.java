/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.dianjiangchun.structs;

import com.game.dianjiangchun.bean.DianjiangchunInfo;
import com.game.object.GameObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class DianjiangchunSaveData extends GameObject {

	public DianjiangchunSaveData() {
		this.nInfuriatingvalue = 0;
		this.nReceiveintinfuriatingvalue = 0;
		this.btUsecount = 0;
		this.btFreechangeluckcount = 0;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -6926638140171047181L;

	//真气值
	private int nInfuriatingvalue;
	
	//领取真气值
	private int nReceiveintinfuriatingvalue;
	
	//使用次数
	private byte btUsecount;
	
	//免费改运次数
	private byte btFreechangeluckcount;
	
	//色子信息列表
	private List<Integer> bosonList = new ArrayList<Integer>();

	public List<Integer> getBosonList() {
		return bosonList;
	}

	public void setBosonList(List<Integer> bosonList) {
		this.bosonList = bosonList;
	}

	public byte getBtFreechangeluckcount() {
		return btFreechangeluckcount;
	}

	public void setBtFreechangeluckcount(byte btFreechangeluckcount) {
		this.btFreechangeluckcount = btFreechangeluckcount;
	}

	public byte getBtUsecount() {
		return btUsecount;
	}

	public void setBtUsecount(byte btUsecount) {
		this.btUsecount = btUsecount;
	}

	public int getnInfuriatingvalue() {
		return nInfuriatingvalue;
	}

	public void setnInfuriatingvalue(int nInfuriatingvalue) {
		this.nInfuriatingvalue = nInfuriatingvalue;
	}

	public int getnReceiveintinfuriatingvalue() {
		return nReceiveintinfuriatingvalue;
	}

	public void setnReceiveintinfuriatingvalue(int nReceiveintinfuriatingvalue) {
		this.nReceiveintinfuriatingvalue = nReceiveintinfuriatingvalue;
	}
	
	public void SaveInfo(DianjiangchunInfo saveDianjiangchunInfo){
		this.setnInfuriatingvalue(saveDianjiangchunInfo.getNInfuriatingvalue());
		this.setnReceiveintinfuriatingvalue(saveDianjiangchunInfo.getNReceiveintinfuriatingvalue());
		this.setBtUsecount(saveDianjiangchunInfo.getBtUsecount());
		this.setBtFreechangeluckcount(saveDianjiangchunInfo.getBtFreechangeluckcount());
		this.getBosonList().clear();
		this.getBosonList().addAll(saveDianjiangchunInfo.getBosonList());
	}
	
	public void LoadInfo(DianjiangchunInfo loadDianjiangchunInfo){
		loadDianjiangchunInfo.setNInfuriatingvalue(this.getnInfuriatingvalue());
		loadDianjiangchunInfo.setNReceiveintinfuriatingvalue(this.getnReceiveintinfuriatingvalue());
		loadDianjiangchunInfo.setBtUsecount(this.getBtUsecount());
		loadDianjiangchunInfo.setBtFreechangeluckcount(this.getBtFreechangeluckcount());
		loadDianjiangchunInfo.getBosonList().clear();
		loadDianjiangchunInfo.getBosonList().addAll(this.getBosonList());
	}
}
