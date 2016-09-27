package com.game.server.http.config;

import java.util.ArrayList;
import java.util.List;

public class HttpServerConfig {

	private int port;
	
	private List<String> allows = new ArrayList<String>();

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<String> getAllows() {
		return allows;
	}

	public void setAllows(List<String> allows) {
		this.allows = allows;
	}
	
}
