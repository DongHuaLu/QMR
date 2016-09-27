package com.game.zones.structs;

import java.util.HashMap;
import java.util.List;

import com.game.server.config.MapConfig;

public class ZoneContext {

	//副本唯一id
	private long id;
	//地图信息
	private List<MapConfig> configs;
	//其他信息
	private HashMap<String, Object> others = new HashMap<String, Object>();
	//下一次检查删除时间
	private long cktime;
	
	//副本模组ID
	private int zonemodelid;
	
	//是否受自动删除时间影响,默认true（如果是系统创建的，没人也需要一直保留的副本,设置为false）
	private boolean isautoremove = true;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<MapConfig> getConfigs() {
		return configs;
	}
	public void setConfigs(List<MapConfig> configs) {
		this.configs = configs;
	}
	public HashMap<String, Object> getOthers() {
		return others;
	}
	public void setOthers(HashMap<String, Object> others) {
		this.others = others;
	}
	public long getCktime() {
		return cktime;
	}
	public void setCktime(long cktime) {
		this.cktime = cktime;
	}
	
	/**是否受自动删除时间影响,默认true（如果是系统创建的，没人也需要一直保留的副本,设置为false）
	 * 
	 * @return
	 */
	public boolean isIsautoremove() {
		return isautoremove;
	}
	
	/**是否受自动删除时间影响,默认true（如果是系统创建的，没人也需要一直保留的副本,设置为false）
	 * 
	 * @param isautoremove
	 */
	public void setIsautoremove(boolean isautoremove) {
		this.isautoremove = isautoremove;
	}
	public int getZonemodelid() {
		return zonemodelid;
	}
	public void setZonemodelid(int zonemodelid) {
		this.zonemodelid = zonemodelid;
	}
}
