package com.game.buff.structs;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.game.fight.structs.Fighter;
import com.game.object.GameObject;

public abstract class Buff extends GameObject implements IBuff {

	private static final long serialVersionUID = -7779860908414599813L;
	
	protected Logger log = Logger.getLogger(Buff.class);
	//buff类型
	private int actionType;
	//buff模型id
	private int modelId;
	//替换等级
	private int replaceLevel;
	//叠加层数
	private int overlay;
	//开始时间
	private long start;
	//持续时间 -1永远存在
	private long totalTime;
	//间隔时间
	private int timer;
	//总剩余持续时间 -1永远存在
	private long totalRemainTime;
	//剩余时间
	private int remain;
	//来源
	private long source;
	//来源类型 0-玩家 1-宠物 2-怪物
	private int sourceType;
	//值加成
	private int value;
	//比例加成
	private int percent;
	//参数
	private int parameter;
	//下线是否计时 0-不计时 1-计时
	private int count;
	//参数数组
	private HashMap<String, String> backups = new HashMap<String, String>();
	
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
	public long getTotalRemainTime() {
		return totalRemainTime;
	}
	public void setTotalRemainTime(long totalRemainTime) {
		this.totalRemainTime = totalRemainTime;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public long getSource() {
		return source;
	}
	public void setSource(long source) {
		this.source = source;
	}
	public int getSourceType() {
		return sourceType;
	}
	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}
	public int getParameter() {
		return parameter;
	}
	public void setParameter(int parameter) {
		this.parameter = parameter;
	}
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
//	public List<Object> getParameters() {
//		return parameters;
//	}
//	public void setParameters(List<Object> parameters) {
//		this.parameters = parameters;
//	}
	public int getReplaceLevel() {
		return replaceLevel;
	}
	public void setReplaceLevel(int replaceLevel) {
		this.replaceLevel = replaceLevel;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOverlay() {
		return overlay;
	}
	public void setOverlay(int overlay) {
		this.overlay = overlay;
	}
	public long countTotalRemainTime(Fighter source){
		return getTotalRemainTime();
	}
	public HashMap<String, String> getBackups() {
		return backups;
	}
	public void setBackups(HashMap<String, String> backups) {
		this.backups = backups;
	}
	
}
