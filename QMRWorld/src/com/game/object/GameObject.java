package com.game.object;

import java.io.Serializable;

import com.game.config.Config;

public abstract class GameObject implements Serializable {
	
	private static final long serialVersionUID = 6697306295815768276L;

	protected long id;
	
	protected String clazz;
	
	protected GameObject(){
		this.id = Config.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClazz() {
		return this.getClass().getName();
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	
}
