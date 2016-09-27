package com.game.map.structs;

import com.game.structs.Position;

/**
 * 跳跃信息类
 * @author heyang
 *
 */
public class Jump {
	//玩家起跳点
	private Position jumpStart;
	//玩家起跳点
	private Position jumpEnd;
	//玩家起跳速度
	private int speed;
	//玩家跳跃花费总时间
	private int totalTime;
	//玩家起跳时间
	private long jumpStartTime;
	
	public Position getJumpStart() {
		return jumpStart;
	}
	public void setJumpStart(Position jumpStart) {
		this.jumpStart = jumpStart;
	}
	public Position getJumpEnd() {
		return jumpEnd;
	}
	public void setJumpEnd(Position jumpEnd) {
		this.jumpEnd = jumpEnd;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public long getJumpStartTime() {
		return jumpStartTime;
	}
	public void setJumpStartTime(long jumpStartTime) {
		this.jumpStartTime = jumpStartTime;
	}
}
