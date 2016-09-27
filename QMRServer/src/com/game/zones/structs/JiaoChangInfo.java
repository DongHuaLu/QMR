package com.game.zones.structs;
/**校场使用的类
 * 
 * @author zhangrong
 *
 */
public  class JiaoChangInfo {

	private int maxjifen;
	private int curjifen;
	private int maxcombom;
	private int curcombom;
	private int actiontype;
	private int dataid;

	public int getDataid() {
		return dataid;
	}

	public void setDataid(int dataid) {
		this.dataid = dataid;
	}

	public int getCurcombom() {
		return curcombom;
	}

	public void setCurcombom(int curcombom) {
		this.curcombom = curcombom;
	}

	public int getCurjifen() {
		return curjifen;
	}

	public void setCurjifen(int curjifen) {
		this.curjifen = curjifen;
	}

	public int getMaxcombom() {
		return maxcombom;
	}

	public void setMaxcombom(int maxcombom) {
		this.maxcombom = maxcombom;
	}

	public int getMaxjifen() {
		return maxjifen;
	}

	public void setMaxjifen(int maxjifen) {
		this.maxjifen = maxjifen;
	}

	public int getActiontype() {
		return actiontype;
	}

	public void setActiontype(int actiontype) {
		this.actiontype = actiontype;
	}
}