package com.game.structs;


public class Grid {
	//x坐标
	private int x;
	//y坐标
	private int y;
	//是否阻挡
	private int block;
	//中心点坐标
	private Position center;
	//是否安全
	private int safe;
	//是否可以跳跃
	private int jump;
	//场景特效
	private int effect;
	//是否禁止布怪
	private int forbid;
	
	public Grid(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public Position getCenter() {
		return center;
	}

	public void setCenter(Position center) {
		this.center = center;
	}

	public int getSafe() {
		return safe;
	}

	public void setSafe(int safe) {
		this.safe = safe;
	}

	public int getJump() {
		return jump;
	}

	public void setJump(int jump) {
		this.jump = jump;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}

	public int getForbid() {
		return forbid;
	}

	public void setForbid(int forbid) {
		this.forbid = forbid;
	}

	public boolean equal(Grid grid){
		if(this.x == grid.getX() && this.y == grid.getY()) return true;
		else return false;
	}
	
	public int getKey(){
		return this.x * 10000 + this.y;
	}
}
