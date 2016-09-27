package com.game.structs;


public class RoadGrid {
	//权值
	private int weight;
	//父格子
	private int farther = -1;
	//格子
	private Grid grid;
	
	public RoadGrid(){}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getFarther() {
		return farther;
	}
	public void setFarther(int farther) {
		this.farther = farther;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public boolean equal(RoadGrid grid){
		return this.grid.equal(grid.getGrid());
	}
}
