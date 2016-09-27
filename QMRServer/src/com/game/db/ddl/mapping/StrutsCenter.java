package com.game.db.ddl.mapping;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RoleStatus;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.game.db.DBServer;
import com.game.db.bean.GameMaster;
import com.game.db.ddl.struts.Activity_setStruts;
import com.game.db.ddl.struts.BlackListStruts;
import com.game.db.ddl.struts.DBStruts;
import com.game.db.ddl.struts.GameMasterStruts;
import com.game.db.ddl.struts.GoldRechargeLogStruts;
import com.game.db.ddl.struts.GoldStruts;
import com.game.db.ddl.struts.MarriedStruts;
import com.game.db.ddl.struts.Q_mailBeanStruts;
import com.game.db.ddl.struts.RoleStruts;
import com.game.db.ddl.struts.ServerCountStruts;
import com.game.db.ddl.struts.ServerParamStruts;
import com.game.db.ddl.struts.SpirittreeStruts;
import com.game.db.ddl.struts.StallsStruts;
import com.game.db.ddl.struts.SystemGrantStruts;
import com.game.db.ddl.struts.WeddingStruts;
import com.game.db.ddl.struts.YbcardStruts;
import com.game.utils.BeanUtil;

import db.util.ColumnInfo;
import db.util.DBUtils;
import db.util.TableCompar;

/**
 * 
 * @author 赵聪慧
 * @2012-11-18 下午6:52:16
 */
public class StrutsCenter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StrutsCenter.class);

	private static StrutsCenter instance=new StrutsCenter();
	public static StrutsCenter getInstance(){
		return instance;
	}
	
	private StrutsCenter(){
		checkTable();
	}
	
	public void checkTable(){
		SqlSession session= DBServer.getInstance().getSqlMapper().openSession();
		try {
			Connection connection = session.getConnection();
			List<String> tableName = BeanUtil.toLowerCase(DBUtils.getTableName(connection));
			List<DBStruts> tableList = getTableList();
			List<String> lostTable=new ArrayList<String>();
			List<String> changeTable=new ArrayList<String>();
			List<String> alertCommand=new ArrayList<String>();
			for (DBStruts dbStruts : tableList) {
				try {
					if (!tableName.contains(dbStruts.tableName().toLowerCase())) {
						lostTable.add(dbStruts.tableName());
					} else {
						List<ColumnInfo> columnDefine = DBUtils.getColumnDefine(connection, dbStruts.tableName());
						List<String> compartor = TableCompar.getInstance().compartor(dbStruts.tableName(), dbStruts.getFields(), columnDefine);
						if(compartor.size()>0){
							alertCommand.addAll(compartor);
							changeTable.add(dbStruts.tableName());	
						}
					}
				} catch (Exception e) {
					logger.error(e,e);
					changeTable.add(dbStruts.tableName());
				}
			}
			if(lostTable.size()>0||changeTable.size()>0||alertCommand.size()>0){
				for (String string : lostTable) {
					logger.error("表缺失"+string);
				}
				for (String string : changeTable) {
					logger.error("表结构不一至"+string);
				}
				for (String string : alertCommand) {
					logger.error("适配sql"+string);
				}
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			session.commit();
			session.close();
		}
	}
	
	public List<DBStruts> getTableList(){
		List<DBStruts> list=new ArrayList<DBStruts>();
		list.add(new BlackListStruts());
		list.add(new GameMasterStruts());
		list.add(new GoldRechargeLogStruts());
		list.add(new GoldStruts());
		list.add(new Q_mailBeanStruts());
		list.add(new RoleStruts());
		list.add(new ServerCountStruts());
		list.add(new ServerParamStruts());
		list.add(new SpirittreeStruts());
		list.add(new StallsStruts());
		list.add(new YbcardStruts());
		list.add(new Activity_setStruts());
		list.add(new SystemGrantStruts());
		list.add(new MarriedStruts());
		list.add(new WeddingStruts());
		return list;
	}
}
