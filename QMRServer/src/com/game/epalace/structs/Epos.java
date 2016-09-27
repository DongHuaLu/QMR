package com.game.epalace.structs;

import com.game.epalace.bean.EpalaceInfo;
import com.game.manager.ManagerPool;

public class Epos {
	
	//当前位置ID
	private byte currentpos;
	
	//前一个位置
	private byte previouspos;
	//下一个位置
	private byte nextpos;
	//上方格子id
	private byte onpos;
	
	//右方格子ID
	private  byte rightpos;
	
	//下方格子ID
	private  byte  underpos;
	
	//左方格子ID
	private  byte leftpos;
	
	//事件ID
	private  int eventid;
	
	//持续时间（秒）
	private  int continuedtime;
	
	
	public EpalaceInfo makeeposinfo(){
		EpalaceInfo eposInfo = new EpalaceInfo();
		eposInfo.setCurrentpos(getCurrentpos());
		eposInfo.setDirection((byte) ManagerPool.epalaceManeger.getDirection(getPreviouspos(), getCurrentpos()));
		eposInfo.setEventid(getEventid());
		if (ManagerPool.epalaceManeger.getPositionNum(getCurrentpos()) > 2) {
			eposInfo.setForkdirection((byte) ManagerPool.epalaceManeger.getDirection(getCurrentpos(),getNextpos()));
		}else {
			eposInfo.setForkdirection((byte) -1);
		}
		return eposInfo;
	}
	
	
	

	
	/**当前位置ID
	 * 
	 * @param currentpos
	 */
	public byte getCurrentpos() {
		return currentpos;
	}

	/**当前位置ID
	 * 
	 * @param currentpos
	 */
	public void setCurrentpos(byte currentpos) {
		this.currentpos = currentpos;
	}
	/**上方格子id
	 * 
	 * @return
	 */
	public byte getOnpos() {
		return onpos;
	}
	
	/**上方格子id
	 * 
	 * @return
	 */
	public void setOnpos(byte onpos) {
		this.onpos = onpos;
	}

	/**右方格子id
	 * 
	 * @return
	 */
	public byte getRightpos() {
		return rightpos;
	}
	/**右方格子id
	 * 
	 * @return
	 */
	public void setRightpos(byte rightpos) {
		this.rightpos = rightpos;
	}

	public byte getUnderpos() {
		return underpos;
	}

	public void setUnderpos(byte underpos) {
		this.underpos = underpos;
	}

	public byte getLeftpos() {
		return leftpos;
	}

	public void setLeftpos(byte leftpos) {
		this.leftpos = leftpos;
	}

	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public byte getPreviouspos() {
		return previouspos;
	}

	public void setPreviouspos(byte previouspos) {
		this.previouspos = previouspos;
	}





	public int getContinuedtime() {
		return continuedtime;
	}





	public void setContinuedtime(int continuedtime) {
		this.continuedtime = continuedtime;
	}





	public byte getNextpos() {
		return nextpos;
	}





	public void setNextpos(byte nextpos) {
		this.nextpos = nextpos;
	}
	
	
	
	
	
	
	
	
	
}
