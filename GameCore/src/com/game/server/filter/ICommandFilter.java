package com.game.server.filter;

import com.game.command.ICommand;

public interface ICommandFilter {

	public boolean filter(ICommand command);
}
