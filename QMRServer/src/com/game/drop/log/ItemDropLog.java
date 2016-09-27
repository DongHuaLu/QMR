package com.game.drop.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.drop.structs.MapDropInfo;
import com.game.json.JSONserializable;
import com.game.map.bean.DropGoodsInfo;
import com.game.map.structs.Map;

public class ItemDropLog extends BaseLogBean {
	private long itemid;
	private long ownerid;
	private long droptime;
	private int intensify;
	private int isfullappend;
	private int isfullstrength;
	private int x;
	private int y;
	private int num;
	private String iteminfo;
	private int serverid;
	private long mapid;
	private int mapmodelid;
	private int itemmodelid;
	private int lineid;
	private long mapcreatetime;
	
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("ItemDropLog");
	
	
	public ItemDropLog(){};
	
	public ItemDropLog(MapDropInfo info){
		DropGoodsInfo dropinfo = info.getDropinfo();
		Map map = info.getMapIns();
		setItemid(dropinfo.getDropGoodsId());
		setDroptime(dropinfo.getDropTime());
		dropinfo.getNum();
		setIntensify(dropinfo.getIntensify());
		setOwnerid(dropinfo.getOwnerId());
		setIsfullappend(dropinfo.getIsFullAppend());
		setIsfullstrength(dropinfo.getIsFullStrength());
		setX(dropinfo.getX());
		setY(dropinfo.getY());
		setServerid(map.getServerId());
		setMapid(map.getId());
		setLineid(map.getLineId());
		setMapmodelid(map.getMapModelid());
		setMapcreatetime(map.getCreate());
		setNum(dropinfo.getNum());
		setItemmodelid(info.getDropinfo().getItemModelId());
		setIteminfo(JSONserializable.toString(info.getItem()));
	}

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
	}

	@Log(fieldType="bigint",logField="itemid")
	public long getItemid() {
		return itemid;
	}
	@Log(fieldType="bigint",logField="droptime")
	public long getDroptime() {
		return droptime;
	}
	@Log(fieldType="int",logField="intensify")
	public int getIntensify() {
		return intensify;
	}
	@Log(fieldType="bigint",logField="ownerid")
	public long getOwnerid() {
		return ownerid;
	}

	@Log(fieldType="int",logField="isfullappend")
	public int getIsfullappend() {
		return isfullappend;
	}
	@Log(fieldType="int",logField="isfullstrength")
	public int getIsfullstrength() {
		return isfullstrength;
	}
	@Log(fieldType="int",logField="mapx")
	public int getX() {
		return x;
	}
	@Log(fieldType="int",logField="mapy")
	public int getY() {
		return y;
	}

	@Log(fieldType="int",logField="serverid")
	public int getServerid() {
		return serverid;
	}
	@Log(fieldType="bigint",logField="mapid")
	public long getMapid() {
		return mapid;
	}
	@Log(fieldType="int",logField="mapmodelid")
	public int getMapmodelid() {
		return mapmodelid;
	}
	@Log(fieldType="int",logField="lineid")
	public int getLineid() {
		return lineid;
	}
	@Log(fieldType="bigint",logField="mapcreatetime")
	public long getMapcreatetime() {
		return mapcreatetime;
	}
	@Log(fieldType="int",logField="itemnum")
	public int getNum() {
		return num;
	}
	@Log(fieldType="text",logField="iteminfo")
	public String getIteminfo() {
		return iteminfo;
	}
	
	@Log(fieldType="int",logField="itemmodel")
	public int getItemmodelid() {
		return itemmodelid;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public void setIteminfo(String iteminfo) {
		this.iteminfo = iteminfo;
	}

	public void setItemid(long itemid) {
		this.itemid = itemid;
	}

	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}

	public void setDroptime(long droptime) {
		this.droptime = droptime;
	}

	public void setIntensify(int intensify) {
		this.intensify = intensify;
	}

	public void setIsfullappend(int isfullappend) {
		this.isfullappend = isfullappend;
	}

	public void setIsfullstrength(int isfullstrength) {
		this.isfullstrength = isfullstrength;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setServerid(int serverid) {
		this.serverid = serverid;
	}

	public void setMapid(long mapid) {
		this.mapid = mapid;
	}

	public void setMapmodelid(int mapmodelid) {
		this.mapmodelid = mapmodelid;
	}

	public void setLineid(int lineid) {
		this.lineid = lineid;
	}

	public void setMapcreatetime(long mapcreatetime) {
		this.mapcreatetime = mapcreatetime;
	}

	public void setItemmodelid(int itemmodelid) {
		this.itemmodelid = itemmodelid;
	}

}
