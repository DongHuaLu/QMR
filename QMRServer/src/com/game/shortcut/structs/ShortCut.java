package com.game.shortcut.structs;

import com.game.object.GameObject;

public class ShortCut extends GameObject {

	private static final long serialVersionUID = 4154833186911183651L;

	//快捷类型
	private int shortcutType;
	
	//快捷对象
	private long shortcutSource;
	
	//快捷对象模板
	private int shortcutSourceModel;

	public int getShortcutType() {
		return shortcutType;
	}

	public void setShortcutType(int shortcutType) {
		this.shortcutType = shortcutType;
	}

	public long getShortcutSource() {
		return shortcutSource;
	}

	public void setShortcutSource(long shortcutSource) {
		this.shortcutSource = shortcutSource;
	}

	public int getShortcutSourceModel() {
		return shortcutSourceModel;
	}

	public void setShortcutSourceModel(int shortcutSourceModel) {
		this.shortcutSourceModel = shortcutSourceModel;
	}
	
	
}
