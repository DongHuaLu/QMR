package com.game.cache;

import com.game.cache.structs.TasksQueue;

public abstract class AbstractWork implements Runnable {

	private TasksQueue<AbstractWork> tasksQueue;

	public TasksQueue<AbstractWork> getTasksQueue() {
		return tasksQueue;
	}

	public void setTasksQueue(TasksQueue<AbstractWork> tasksQueue) {
		this.tasksQueue = tasksQueue;
	}
}
