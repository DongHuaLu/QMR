package com.game.realm.structs;

import com.game.object.GameObject;
import com.game.realm.bean.RealmInfo;
import com.game.utils.TimeUtil;

/**境界
 * 
 * @author zhangrong
 *
 */
public class Realm  extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8290148088841375369L;

	//境界等级
	private int realmlevel;
	//境界强化等级
	private int intensifylevel;
	//突破失败次数
	private int breaknum;
	//祝福值
	private int blessingnum;
	
	//突破失败经验上限
	private int  faillimitexp;
	
	//日期标记（跨天清理祝福值用）
	private int day;
	//1=65级后第一次上线播放动画，2=已经购买激活道具
	private int activation;
	
	//突破提示
	private byte breakprompt;
	
	
	/**境界等级
	 * 
	 * @return
	 */
	public int getRealmlevel() {
		return realmlevel;
	}
	public void setRealmlevel(int realmlevel) {
		this.realmlevel = realmlevel;
	}
	
	/**境界强化等级
	 * 
	 * @return
	 */
	public int getIntensifylevel() {
		return intensifylevel;
	}
	public void setIntensifylevel(int intensifylevel) {
		this.intensifylevel = intensifylevel;
	}
	/**祝福值
	 * 
	 * @return
	 */
	public int getBlessingnum() {
		return blessingnum;
	}
	public void setBlessingnum(int blessingnum) {
		this.blessingnum = blessingnum;
	}
	
	/**突破失败次数
	 * 
	 * @return
	 */
	public int getBreaknum() {
		return breaknum;
	}
	public void setBreaknum(int breaknum) {
		this.breaknum = breaknum;
	}
	
	public int getFaillimitexp() {
		return faillimitexp;
	}
	public void setFaillimitexp(int faillimitexp) {
		this.faillimitexp = faillimitexp;
	}
	
	
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	
	
	/**获得境界信息
	 * 
	 * @return
	 */
	public RealmInfo getRealmInfo(){
		this.clearday();//跨天清理
		RealmInfo realmInfo = new RealmInfo();
		realmInfo.setBlessingnum(this.blessingnum);
		realmInfo.setIntensifylevel(this.intensifylevel);
		realmInfo.setRealmlevel(this.realmlevel);
		return realmInfo;
	}


	/**跨天清理
	 * 
	 */
	public void clearday(){
		int xday = TimeUtil.GetCurDay(0);
		if (this.getDay() != xday) {
			this.setBlessingnum(0);
			this.setDay(xday);
			this.setFaillimitexp(0);
		}
	}
	public int getActivation() {
		return activation;
	}
	public void setActivation(int activation) {
		this.activation = activation;
	}
	public byte getBreakprompt() {
		return breakprompt;
	}
	public void setBreakprompt(byte breakprompt) {
		this.breakprompt = breakprompt;
	}
	
	
	
	
	
}
