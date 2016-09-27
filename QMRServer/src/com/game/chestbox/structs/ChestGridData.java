package com.game.chestbox.structs;

import com.game.backpack.bean.GoodsAttribute;
import com.game.backpack.bean.ItemInfo;
import com.game.chestbox.bean.ChestGridInfo;
import com.game.object.GameObject;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 宝箱格子数据
 *
 * @author 杨鸿岚
 */
public class ChestGridData extends GameObject {

	private static final long serialVersionUID = 7164222536143472118L;
	//格子编号
	private int grididx;
	//格子类型
	private int gridtype;
	//当前格子中的物品信息
	private FruitReward curFruitReward;
	//当前格子中的多倍奖励物品信息
	private List<FruitReward> fruitRewardlist = new ArrayList<FruitReward>();

	public FruitReward getCurFruitReward() {
		return curFruitReward;
	}

	public void setCurFruitReward(FruitReward curFruitReward) {
		this.curFruitReward = curFruitReward;
	}

	public List<FruitReward> getFruitRewardlist() {
		return fruitRewardlist;
	}

	public void setFruitRewardlist(List<FruitReward> fruitRewardlist) {
		this.fruitRewardlist = fruitRewardlist;
	}

	public int getGrididx() {
		return grididx;
	}

	public void setGrididx(int grididx) {
		this.grididx = grididx;
	}

	public int getGridtype() {
		return gridtype;
	}

	public void setGridtype(int gridtype) {
		this.gridtype = gridtype;
	}

	public ChestGridInfo toInfo() {
		ChestGridInfo chestGridInfo = new ChestGridInfo();
		chestGridInfo.setGrididx(grididx);
		chestGridInfo.setGridtype(gridtype);
		if (curFruitReward != null) {
			chestGridInfo.setCuriteminfo(getItemInfo(curFruitReward));
		}else{
			chestGridInfo.setCuriteminfo(new ItemInfo());
		}
		Iterator<FruitReward> iterator = fruitRewardlist.iterator();
		while (iterator.hasNext()) {
			FruitReward fruitReward = iterator.next();
			if (fruitReward != null) {
				chestGridInfo.getItemlist().add(getItemInfo(fruitReward));
			}
		}
		return chestGridInfo;
	}

	/**
	 * 生成ItemInfo
	 *
	 * @param fruitReward
	 * @return
	 */
	public ItemInfo getItemInfo(FruitReward fruitReward) {
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setIntensify((byte) fruitReward.getStrenglevel());
		if (fruitReward.isBind()) {
			itemInfo.setIsbind((byte) 1);
		}
		itemInfo.setItemModelId(fruitReward.getItemModelid());
		itemInfo.setNum(fruitReward.getNum());
		if (fruitReward.getLosttime() > 0) {
			itemInfo.setLostTime((int) (System.currentTimeMillis() / 1000 + fruitReward.getLosttime()));
		}
		else {
			itemInfo.setLostTime(0);
		}
		itemInfo.setAttributs((byte) fruitReward.getFruitRewardAttrslist().size());
		for (FruitRewardAttr fruitRewardAttr : fruitReward.getFruitRewardAttrslist()) {
			GoodsAttribute goodsatt = new GoodsAttribute();
			goodsatt.setType(fruitRewardAttr.getType());
			goodsatt.setValue(fruitRewardAttr.getValue());
			itemInfo.getGoodAttributes().add(goodsatt);
		}
		itemInfo.setGridId(-1);
		itemInfo.setItemId(fruitReward.getId());
		return itemInfo;
	}
}
