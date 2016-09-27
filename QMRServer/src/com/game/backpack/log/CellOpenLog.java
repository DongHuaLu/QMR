package com.game.backpack.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
/**
 * 
 * @author 赵聪慧
 *
 */
public class CellOpenLog extends BaseLogBean {
	
	private int cellId;
	private long roleId;
	private byte type;
	//包含绑定和不绑定
	private int resumegold;
	private byte action;
	private int beforeCells;
	private int afterCells; 
	private long actionId;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.MONTH;
	}

	@Log(fieldType="int",logField="cellid")
	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}
	@Log(fieldType="bigint",logField="roleid")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Log(fieldType="int",logField="celltype")
	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	@Log(fieldType="int",logField="resumgold")
	public int getResumegold() {
		return resumegold;
	}

	public void setResumegold(int resumegold) {
		this.resumegold = resumegold;
	}
	/**
	 * 1 元宝开启  0时间开启
	 * @return
	 */
	@Log(fieldType="int",logField="actionType")
	public byte getAction() {
		return action;
	}

	public void setAction(byte action) {
		this.action = action;
	}

	@Log(fieldType="int",logField="beforeCells")
	public int getBeforeCells() {
		return beforeCells;
	}

	public void setBeforeCells(int beforeCells) {
		this.beforeCells = beforeCells;
	}

	@Log(fieldType="int",logField="afterCells")
	public int getAfterCells() {
		return afterCells;
	}

	public void setAfterCells(int afterCells) {
		this.afterCells = afterCells;
	}
	@Log(fieldType="bigint",logField="actionId")
	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}

	private static final Logger logger=Logger.getLogger("CellOpenLog");
	
}
