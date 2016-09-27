package com.game.epalace.structs;



import java.util.ArrayList;
import java.util.List;

import com.game.object.GameObject;

public class Epalace extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6807714894163590211L;
	
	/**恢复次数冷却时间
	 * 
	 */
	private int time;
	
	/**剩余移动次数
	 * 
	 */
	private int movenum = 12;
	
	/**当前位置
	 * 
	 */
	private int pos =1;
	
	/**前进方向 
	 * 
	 */
	private byte direction = 2; //默认向右
	
	
	
	/**当前任务
	 * 
	 */
	private int taskid;
	
	/**行动格子临时存放list
	 * 
	 */
	private transient List<Epos> eposlist= new ArrayList<Epos>();

	
	
	/**恢复次数冷却时间
	 * 
	 */
	public int getTime() {
		return time;
	}
	/**恢复次数冷却时间
	 * 
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**剩余移动次数
	 * 
	 */
	public int getMovenum() {
		return movenum;
	}
	/**剩余移动次数
	 * 
	 */
	public void setMovenum(int movenum) {
		this.movenum = movenum;
	}
	/**当前位置
	 * 
	 */
	public int getPos() {
		return pos;
	}
	/**当前位置
	 * 
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}
	/**当前任务
	 * 
	 */
	public int getTaskid() {
		return taskid;
	}
	/**当前任务
	 * 
	 */
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	/**行动格子临时存放list
	 * 
	 */
	public List<Epos> getEposlist() {
		return eposlist;
	}
	/**行动格子临时存放list
	 * 
	 */
	public void setEposlist(List<Epos> eposlist) {
		this.eposlist = eposlist;
	}
	public byte getDirection() {
		return direction;
	}
	public void setDirection(byte direction) {
		this.direction = direction;
	}
	
	
	
	
	
	
	
	
	
}
