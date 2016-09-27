package com.game.spirittree.structs;

import java.util.ArrayList;

import com.game.object.GameObject;
import com.game.spirittree.bean.FruitRewardinfo;
import com.game.spirittree.bean.TempAttributes;

public class FruitReward extends GameObject{

	private static final long serialVersionUID = 7164222522223472118L;
	//数据表奖励索引
	private int idx;
	//道具模型ID，-1铜币，-2元宝，-3经验，-4真气
	private int itemModelid;	
	//当前数量
	private int num;
	//道具强化等级
	private int strenglevel;
	//道具附加属性
	private ArrayList<FruitRewardAttr> fruitRewardAttrslist = new ArrayList<FruitRewardAttr>();
	//存在时间
	private long losttime;
	//是否绑定
	private boolean bind;
	//是否立即使用
	private boolean use;
	//总数量
	private int sum;
	
	/**得到奖励信息
	 * 
	 * @return
	 */
	public FruitRewardinfo makeinfo(){
		FruitRewardinfo info = new FruitRewardinfo();
		info.setIndex(getIdx());
		info.setItemModelid(getItemModelid());
		info.setNum(getNum());
		if (getSum() == 0) {
			this.setSum(getNum());
		}
		info.setSum(getSum());
		info.setStrenglevel(getStrenglevel());
		for (FruitRewardAttr att : fruitRewardAttrslist) {
			TempAttributes tempAttributes= new TempAttributes();
			tempAttributes.setAttributeType((byte) att.getType());
			tempAttributes.setAttributeValue(att.getValue());
			info.getTempAttributes().add(tempAttributes);
		}
		return info;
	}
	
	

	
	
	
	
	
	
	/**道具模型ID，-1铜币，-2元宝，-3经验，-4真气
	 * 
	 * @return
	 */
	public int getItemModelid() {
		return itemModelid;
	}
	public void setItemModelid(int itemModelid) {
		this.itemModelid = itemModelid;
	}
	
	
	/**数量
	 * 
	 * @return
	 */
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	/**道具强化等级
	 * 
	 * @return
	 */
	public int getStrenglevel() {
		return strenglevel;
	}
	public void setStrenglevel(int strenglevel) {
		this.strenglevel = strenglevel;
	}
	
	
	/**道具附加属性
	 * 
	 * @return
	 */
	public ArrayList<FruitRewardAttr> getFruitRewardAttrslist() {
		return fruitRewardAttrslist;
	}
	public void setFruitRewardAttrslist(ArrayList<FruitRewardAttr> fruitRewardAttrslist) {
		this.fruitRewardAttrslist = fruitRewardAttrslist;
	}
	
	
	/**数据表奖励索引
	 * 
	 * @return
	 */
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}

	public long getLosttime() {
		return losttime;
	}

	public void setLosttime(long losttime) {
		this.losttime = losttime;
	}

	public boolean isBind() {
		return bind;
	}

	public void setBind(boolean bind) {
		this.bind = bind;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}


	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
