package com.game.spirittree.structs;

import java.util.ArrayList;

import com.game.object.GameObject;

public class SpiritTree extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8244390874108806130L;
	//仙露浇灌次数
	private byte dewnum;
	//仙露浇灌冷却时间
	private int dewcooldown;
	//互动采摘记录
	private  ArrayList<SpiritTreeLog> spirittreelogs = new ArrayList<SpiritTreeLog>();
	//果实列表
	private Fruit[] fruitlist = new Fruit[8];
	//是否参与新果筛选标记，1参与，0不参与
	private byte newfruit;
	
	//天数记录(用来清除每天的数据)
	private int day;
	
	//每日已经获得经验
	private int dayexp;
	//玩家不在线，补偿经验临时存放
	private int buchangexp;
	//每天抢摘次数
	private int theftviewsnum;
	
	
	
	/**仙露浇灌次数
	 * 
	 * @return
	 */
	public byte getDewnum() {
		return dewnum;
	}
	/**仙露浇灌次数
	 * 
	 * @param dewnum
	 */
	public void setDewnum(byte dewnum) {
		this.dewnum = dewnum;
	}
	/**仙露浇灌冷却时间
	 * 
	 * @return
	 */
	public int getDewcooldown() {
		return dewcooldown;
	}
	/**仙露浇灌冷却时间
	 * 
	 * @param dewcooldown
	 */
	public void setDewcooldown(int dewcooldown) {
		this.dewcooldown = dewcooldown;
	}
	
	/**互动采摘记录
	 * 
	 * @return
	 */
	public ArrayList<SpiritTreeLog> getSpirittreelogs() {
		return spirittreelogs;
	}
	/**互动采摘记录
	 * 
	 * @param spirittreelogs
	 */
	public void setSpirittreelogs(ArrayList<SpiritTreeLog> spirittreelogs) {
		this.spirittreelogs = spirittreelogs;
	}
	
	/**果实列表
	 * 
	 * @return
	 */
	public Fruit[] getFruitlist() {
		return fruitlist;
	}
	/**果实列表
	 * 
	 * @param fruitlist
	 */
	public void setFruitlist(Fruit[] fruitlist) {
		this.fruitlist = fruitlist;
	}
	
	/**是否参与新果筛选标记，1参与，0不参与
	 * 
	 * @return
	 */
	public byte getNewfruit() {
		return newfruit;
	}
	/**是否参与新果筛选标记，1参与，0不参与
	 * 
	 * @param newfruit
	 */
	public void setNewfruit(byte newfruit) {
		this.newfruit = newfruit;
	}
	
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getDayexp() {
		return dayexp;
	}
	
	public void setDayexp(int dayexp) {
		this.dayexp = dayexp;
	}
	public int getBuchangexp() {
		return buchangexp;
	}
	public void setBuchangexp(int buchangexp) {
		this.buchangexp = buchangexp;
	}
	/**每天抢摘次数
	 * 
	 * @return
	 */
	public int getTheftviewsnum() {
		return theftviewsnum;
	}
	/**每天抢摘次数
	 * 
	 * @return
	 */
	public void setTheftviewsnum(int theftviewsnum) {
		this.theftviewsnum = theftviewsnum;
	}
	
	
	
	
	
	
}
