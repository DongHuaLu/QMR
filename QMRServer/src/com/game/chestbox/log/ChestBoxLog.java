package com.game.chestbox.log;

import com.alibaba.fastjson.JSON;
import com.game.chestbox.structs.ChestGridData;
import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.player.structs.Player;
import org.apache.log4j.Logger;

/**
 * 宝箱玩法日志
 *
 * @author 杨鸿岚
 */
public class ChestBoxLog extends BaseLogBean {

	private static final Logger logger = Logger.getLogger("ChestBoxLog");

	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Override
	public void logToFile() {
		logger.error(buildSql());
	}

	public ChestBoxLog(Player player, int type, ChestGridData chestGridData) {
		this.userid = Long.valueOf(player.getUserId());
		this.roleid = player.getId();
		this.type = type;
		if (type == 1 || type == 4) {
			this.insideidx = player.getChestBoxData().getInchooseidx();
			this.outsideidx = player.getChestBoxData().getOutchooseidx();
			this.chestboxinfo = JSON.toJSONString(player.getChestBoxData().toInfo());
			this.chestgridinfo = "";
		} else if (type == 2) {
			this.insideidx = player.getChestBoxData().getInchooseidx();
			this.outsideidx = player.getChestBoxData().getOutchooseidx();
			this.chestboxinfo = "";
			this.chestgridinfo = "";
		} else if (type == 3 || type == 5) {
			this.insideidx = player.getChestBoxData().getInchooseidx();
			this.outsideidx = player.getChestBoxData().getOutchooseidx();
			this.chestboxinfo = "";
			this.chestgridinfo = chestGridData == null ? "NULL" : JSON.toJSONString(chestGridData.toInfo());
		} else {
			this.insideidx = player.getChestBoxData().getInchooseidx();
			this.outsideidx = player.getChestBoxData().getOutchooseidx();
			this.chestboxinfo = "类型错误";
			this.chestgridinfo = "类型错误";
		}
		this.sid = player.getCreateServerId();
	}

	public ChestBoxLog() {
	}
	private long userid;		//用户id
	private long roleid;		//角色id
	private int type;		//1 生成 2 开启选中 3 领取 4 掉线退出生成 5 掉线退出领取
	private int insideidx;		//内圈编号
	private int outsideidx;		//外圈编号
	private String chestboxinfo;	//幸运轮盘数据
	private String chestgridinfo;	//选中格子数据
	private int sid;

	@Log(logField = "sid", fieldType = "int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Log(fieldType = "bigint", logField = "roleid")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	@Log(fieldType = "bigint", logField = "userid")
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	@Log(fieldType = "int", logField = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Log(fieldType = "text", logField = "chestboxinfo")
	public String getChestboxinfo() {
		return chestboxinfo;
	}

	public void setChestboxinfo(String chestboxinfo) {
		this.chestboxinfo = chestboxinfo;
	}

	@Log(fieldType = "text", logField = "chestgridinfo")
	public String getChestgridinfo() {
		return chestgridinfo;
	}

	public void setChestgridinfo(String chestgridinfo) {
		this.chestgridinfo = chestgridinfo;
	}

	@Log(fieldType = "int", logField = "insideidx")
	public int getInsideidx() {
		return insideidx;
	}

	public void setInsideidx(int insideidx) {
		this.insideidx = insideidx;
	}

	@Log(fieldType = "int", logField = "outsideidx")
	public int getOutsideidx() {
		return outsideidx;
	}

	public void setOutsideidx(int outsideidx) {
		this.outsideidx = outsideidx;
	}
}
