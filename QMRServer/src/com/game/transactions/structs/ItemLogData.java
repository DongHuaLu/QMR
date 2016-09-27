package com.game.transactions.structs;
import java.util.ArrayList;
import java.util.List;

import com.game.backpack.bean.GoodsAttribute;
import com.game.backpack.bean.ItemInfo;
import com.game.object.GameObject;

public class ItemLogData extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6925207905739740719L;

	//角色Id
	private long itemId;
	
	//物品模板Id
	private int itemModelId;
	
	//物品数量
	private int num;
	
	//角色所在格子Id
	private int gridId;
	
	//是否绑定 1是 0否 
	private byte isbind;
	
	//强化等级
	private byte intensify;
	
	//扩展属性数量
	private byte attributs;
	
	//是否顶级附加 1是 0否
	private byte isFullAppend;
	
	//过期时间
	private int lostTime;
	
	//扩展属性
	private List<ItemAttributeLogData> itemAttributeLogData = new ArrayList<ItemAttributeLogData>();

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getItemModelId() {
		return itemModelId;
	}

	public void setItemModelId(int itemModelId) {
		this.itemModelId = itemModelId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getGridId() {
		return gridId;
	}

	public void setGridId(int gridId) {
		this.gridId = gridId;
	}

	public byte getIsbind() {
		return isbind;
	}

	public void setIsbind(byte isbind) {
		this.isbind = isbind;
	}

	public byte getIntensify() {
		return intensify;
	}

	public void setIntensify(byte intensify) {
		this.intensify = intensify;
	}

	public byte getAttributs() {
		return attributs;
	}

	public void setAttributs(byte attributs) {
		this.attributs = attributs;
	}

	public byte getIsFullAppend() {
		return isFullAppend;
	}

	public void setIsFullAppend(byte isFullAppend) {
		this.isFullAppend = isFullAppend;
	}

	public int getLostTime() {
		return lostTime;
	}

	public void setLostTime(int lostTime) {
		this.lostTime = lostTime;
	}


	public List<ItemAttributeLogData> getItemAttributeLogData() {
		return itemAttributeLogData;
	}

	public void setItemAttributeLogData(List<ItemAttributeLogData> itemAttributeLogData) {
		this.itemAttributeLogData = itemAttributeLogData;
	}
	
	
	
	/**设定道具展示信息
	 * 
	 * @return
	 */
	public ItemInfo toiteminof(){
		ItemInfo itemInfo =new ItemInfo();
		itemInfo.setAttributs(this.getAttributs());
		for (ItemAttributeLogData iterable : this.getItemAttributeLogData()) {
			GoodsAttribute goodsattribute = new GoodsAttribute();
			goodsattribute.setType(iterable.getType());
			goodsattribute.setValue(iterable.getValue());
			itemInfo.getGoodAttributes().add(goodsattribute);
		}
		itemInfo.setGridId(this.getGridId());
		itemInfo.setIntensify(this.getIntensify());
		itemInfo.setIsbind(this.getIsbind());
		itemInfo.setIsFullAppend(this.getIsFullAppend());
		itemInfo.setItemId(this.getItemId());
		itemInfo.setItemModelId(this.getItemModelId());
		itemInfo.setLostTime(this.getLostTime());
		itemInfo.setNum(this.getNum());
		return itemInfo;
	}

	
	
	
}



	