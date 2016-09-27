package com.game.marriage.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.marriage.bean.LeaveMsgInfo;
import com.game.object.GameObject;
import com.game.player.structs.Player;


/**结婚数据
 * 
 * @author zhangrong
 *
 */


public class Marriage extends GameObject{
	
	private static final long serialVersionUID = -4394804243149534550L;
	//当前婚戒
	private int currringid;
	//旧的，可领取放在包里做纪念的婚戒
	private int oldringid;
	//配偶列表  0是新郎，1是新娘
	List<Spouse> spouseslist = new ArrayList<Spouse>(); 
	//状态，1表示有人强制离婚
	private int status;
	//留言表
	private List<LeaveMsg> leavemsgs = new ArrayList<LeaveMsg>();
	//结婚时间（毫秒）
	private long time;
	
	//下一次可办婚宴时间，CD
	private long cdtiem;
	
	
	/**获取配偶简要信息
	 * 
	 */
	public Spouse getSpouse(Player player){
		if (spouseslist.size() == 2) {
			if(spouseslist.get(0).getPlayerid() == player.getId()){
				return spouseslist.get(1);
			}
			if(spouseslist.get(1).getPlayerid() == player.getId()){
				return spouseslist.get(0);
			}
		}
		return null;
	}
	
	
	
	/**获取本人简要信息
	 * 
	 */
	public Spouse getSelfSpouse(long id){
		if (spouseslist.size() == 2) {
			if(spouseslist.get(0).getPlayerid() == id){
				return spouseslist.get(0);
			}
			if(spouseslist.get(1).getPlayerid() == id){
				return spouseslist.get(1);
			}
		}
		return null;
	}
	
	
	
	/**获取配偶ID
	 * 
	 * @param player
	 * @return
	 */
	public long getSpouseid(Player player){
		if (spouseslist.size() == 2) {
			if(spouseslist.get(0).getPlayerid() == player.getId()){
				return spouseslist.get(1).getPlayerid();
			}
			if(spouseslist.get(1).getPlayerid() == player.getId()){
				return spouseslist.get(0).getPlayerid();
			}
		}
		return 0;
	}

	
	
	/**得到留言信息
	 * 
	 * @param player
	 * @return
	 */
	public List<LeaveMsgInfo> makeLeaveMsgInfo(Player player){
		List<LeaveMsgInfo> list=new ArrayList<LeaveMsgInfo>();
		for (LeaveMsg leaveMsg : getLeavemsgs()) {
			LeaveMsgInfo leaveMsgInfo = new LeaveMsgInfo();
			leaveMsgInfo.setContent(leaveMsg.getContent());
			if (leaveMsg.getPlayerid() == spouseslist.get(0).getPlayerid()) {
				leaveMsgInfo.setPlayername(spouseslist.get(0).getName());
			}else {
				leaveMsgInfo.setPlayername(spouseslist.get(1).getName());
			}
			leaveMsgInfo.setMsgid(leaveMsg.getId());
			leaveMsgInfo.setTime((int) (leaveMsg.getTime()/1000));
			if (player.getId() != leaveMsg.getPlayerid()) {
				leaveMsg.setAlread(true);
			}
			list.add(leaveMsgInfo);
		}
		return list;
	}
	
	/**检查留言是否已读
	 * 
	 * @param player
	 * @return
	 */
	public boolean ckLeaveMsg(Player player){
		for (LeaveMsg leaveMsg : getLeavemsgs()) {
			if (player.getId() != leaveMsg.getPlayerid()) {
				if (!leaveMsg.isAlread() ) {
					return false;
				}
			}
		}
		return true;
	}	
	
	
	
	
	
	public int getCurrringid() {
		return currringid;
	}
	public void setCurrringid(int currringid) {
		this.currringid = currringid;
	}
	public int getOldringid() {
		return oldringid;
	}
	public void setOldringid(int oldringid) {
		this.oldringid = oldringid;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<LeaveMsg> getLeavemsgs() {
		return leavemsgs;
	}
	public void setLeavemsgs(List<LeaveMsg> leavemsgs) {
		this.leavemsgs = leavemsgs;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}


/**配偶列表  0是新郎，1是新娘
 * 
 * @return
 */
	public List<Spouse> getSpouseslist() {
		return spouseslist;
	}

/**配偶列表  0是新郎，1是新娘
 * 
 * @return
 */
	public void setSpouseslist(List<Spouse> spouseslist) {
		this.spouseslist = spouseslist;
	}



public long getCdtiem() {
	return cdtiem;
}



public void setCdtiem(long cdtiem) {
	this.cdtiem = cdtiem;
}

	
	
	
	
	
	
	
	
	
	
}
