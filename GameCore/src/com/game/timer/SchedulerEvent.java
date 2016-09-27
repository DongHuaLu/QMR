package com.game.timer;

import com.game.command.ICommand;

public abstract class SchedulerEvent implements ICommand {
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
