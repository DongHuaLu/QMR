package com.game.dblog;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.utils.BeanUtil;
import com.game.utils.RandomUtils;

import db.util.ColumnInfo;

/**
 * 
 * @author 赵聪慧
 * @2012-10-31 下午3:25:39
 */
public class UpdateThread extends Thread {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UpdateThread.class);

	//命令执行队列
	private LinkedList<Long> queue = new LinkedList<Long>();
	private Object lock=new Object();
	private static boolean stop=false;
	private static int stepnum=100;//每次更新数量
	private static int stepsleep=10*1000;
	private DataSource ds;
	public UpdateThread(ThreadGroup group,String threadName,DataSource ds){
		super(group, threadName);
		this.ds=ds;
	}
	public UpdateThread(DataSource ds){
		super("ROLESTATEUPDATE");
		this.ds=ds;
	}
	
	@Override
	public void run() {
		while (!stop) {
			Connection connection = null;
			try {
				ArrayList<Long> array = new ArrayList<Long>();
				
				synchronized (lock) {
					try{
						while (array.size() < stepnum && queue.size() > 0) {
							array.add(queue.removeLast());
						}	
					}catch (Exception e) {
						logger.error(e,e);
					}
				}
				ArrayList<Player> insert = new ArrayList<Player>();
				ArrayList<Player> update = new ArrayList<Player>();
				for (Long roleId : array) {
					Player player = PlayerManager.getInstance().getPlayer(roleId);
					if (player == null) {
						continue;
					}
					if (player.isUpdatelogtag()) {
						update.add(player);
					} else {
						insert.add(player);
					}
				}
				if(insert.size()>0||update.size()>0){
					connection = ds.getConnection();
				}
				
				if(insert.size()>0){
					String buildInsertSqlExpress = buildInsertSqlExpress();
					PreparedStatement insertStmt = connection.prepareStatement(buildInsertSqlExpress);
					for (Player player : insert) {
						buildParam(player, insertStmt);
						insertStmt.addBatch();
					}
					//批量执行并得到执行结果
					int[] result = new int[insert.size()];
					try{
						result = insertStmt.executeBatch();
					}catch (BatchUpdateException e) {
						result = e.getUpdateCounts();
					}
					//关闭statement
					insertStmt.close();
					//
					for(int index=0; index<result.length; index++){
						Player player = insert.get(index);
						if(result[index]>0){ //对执行插入成功的角色设置更新标记
							player.setUpdatelogtag(true);
						}else{
							logger.error("角色"+player.getName()+"("+player.getId()+")执行插入失败不予标识");
						}
					}
				}
				if(update.size()>0){
					String buildUpdateSqlExpress = buildUpdateSqlExpress();
					PreparedStatement updateStmt = connection.prepareStatement(buildUpdateSqlExpress);
					for (Player player : update) {
						buildParam(player, updateStmt);
						updateStmt.setLong(61, player.getId());
						updateStmt.addBatch();
					}
					//批量执行sql
					int[] updateBatch = new int[update.size()];
					try{
						updateBatch = updateStmt.executeBatch();
					}catch (BatchUpdateException e) {
						updateBatch = e.getUpdateCounts();
					}
					//关闭语句
					updateStmt.close();
					//
					for(int index=0; index<updateBatch.length; index++){
						Player player = update.get(index);
						if(updateBatch[index]<=0){ //对执行插入成功的角色设置更新标记
							logger.error("角色"+player.getName()+"("+player.getId()+")执行更新失败重置插入标记");
							player.setUpdatelogtag(false);
						}
					}
				}
				if(insert.size()>0||update.size()>0){
					logger.info("执行插入"+insert.size()+"执行更新"+update.size());
				}
				
			} catch (Exception e) {
				logger.error(e,e);
			}finally{
				if(connection!=null){
					try{
						connection.close();	
					}catch (Exception e) {
						logger.error(e,e);
					}
				}
				try {
					Thread.sleep(stepsleep+RandomUtils.random(0, 50*1000));
				} catch (InterruptedException e) {
					logger.error(e,e);
				}
			}
		}
	}
	
	public void updateState(long roleId){
		synchronized (lock) {
			try{
				if(queue.size()<=10000&&!queue.contains(roleId)){
					queue.add(roleId);
				}		
			}catch (Exception e) {
				logger.error(e,e);
			}
			
		}
		
	}
	
//	public static void main(String arg[]){
//		UpdateThread updateThread = new UpdateThread(null);
//		String buildDDL = updateThread.buildDDL();
//		System.out.println(buildDDL);
//	}

	public static List<ColumnInfo> buildFields(){
		List<ColumnInfo> parm=new ArrayList<ColumnInfo>();
		parm.add(ColumnInfo.createColumnInfo("onlineTime","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("arrowlevel","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("attack","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("attackspeed","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("autoagreeaddguild","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("bagcellsnum","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("bindgold","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("canreceiveyuanbao","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("clientset","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("contributionpoint","int",11,true, "",""));
///帮贡点
		parm.add(ColumnInfo.createColumnInfo("country","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("crit","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("createsid","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("createstime","varchar",50,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("defense","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("dodge","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("fightpower","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("horselevel","int",11,true, "",""));	
		parm.add(ColumnInfo.createColumnInfo("horseboxnum","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("horseclearcdnum","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("horseblessvalue","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("horsehisbless","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("horsehisupnum","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("isopenhighgem","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("longyuanlevel","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("longyuanbtexp","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("longyuansection","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("level","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("luck","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("openmaplocation","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("rankexp","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("ranklevel","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("skills","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("maxhp","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("maxmp","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("maxsp","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("maxlogintimes","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("money","int",11,true, "",""));
		//最大连续登陆天数
		parm.add(ColumnInfo.createColumnInfo("name","varchar",50,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("nonage","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("pets","varchar",1024,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("shutuptime","varchar",50,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("sex","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("showrelation","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("signsum","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("sort","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("speed","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("storecellnum","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("consumebindgold","bigint",20,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("consumemoney","bigint",20,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("gotbindgold","bigint",20,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("gotmoney","bigint",20,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("skilllevel","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("userid","varchar",128,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("username","varchar",255,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("webname","varchar",50,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("forbid","int",11,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("id","bigint",20,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("lastupdatetime","varchar",50,true, "",""));
		parm.add(ColumnInfo.createColumnInfo("ts","bigint",20,false, "",""));
		return parm;
	}
	
	
	@SuppressWarnings("deprecation")
	private void buildParam(Player player,PreparedStatement ppstat){
		try {			
			ppstat.setInt(1, player.getAccunonlinetime());
			ppstat.setInt(2, player.getArrowData().getBowData().getBowmainlv());
			ppstat.setInt(3,player.getAttack());
			ppstat.setInt(4,player.getAttackSpeed());
			ppstat.setInt(5,player.getAutoArgeeAddGuild());
			ppstat.setInt(6,player.getBagCellsNum());
			ppstat.setInt(7,player.getBindGold());
			ppstat.setInt(8,player.getCanreceiveyuanbao());
			ppstat.setInt(9,player.getClientset());
			ppstat.setInt(10,player.getContributionPoint());///帮贡点
			ppstat.setInt(11,player.getCountry());
			ppstat.setInt(12,player.getCrit());
			ppstat.setInt(13,player.getCreateServerId());
			ppstat.setString(14,new Date(player.getCreateTime()).toLocaleString());
			ppstat.setInt(15,player.getDefense());
			ppstat.setInt(16,player.getDodge());
			ppstat.setInt(17,player.getFightPower());
			Horse horse = HorseManager.getInstance().getHorse(player);
			if(BeanUtil.isNull(horse)){
				ppstat.setInt(18,0);
				ppstat.setInt(19,0);
				ppstat.setInt(20,0);
				ppstat.setInt(21,0);
				ppstat.setInt(22,0);
				ppstat.setInt(23,0);
			}else{
				ppstat.setInt(18,horse.getCurlayer());
				ppstat.setInt(19,horse.getBoxnum());
				ppstat.setInt(20,horse.getClearcdnum());
				ppstat.setInt(21,horse.getDayblessvalue());
				ppstat.setInt(22,horse.getHisblessvalue());
				ppstat.setInt(23,horse.getHisupnum());
			}
			ppstat.setInt(24,player.getIsopenhighgem());
			ppstat.setInt(25,player.getLongyuan().getLylevel());
			ppstat.setInt(26,player.getLongyuan().getLyobtainexp());
			ppstat.setInt(27,player.getLongyuan().getLysection());
			ppstat.setInt(28,player.getLevel());
			ppstat.setInt(29,player.getLuck());
			ppstat.setInt(30,player.getOpenMapLocation());
			ppstat.setInt(31,player.getRankexp());
			ppstat.setInt(32,player.getRanklevel());
			ppstat.setInt(33,player.getSkills().size());
			ppstat.setInt(34,player.getMaxHp());
			ppstat.setInt(35,player.getMaxMp());
			ppstat.setInt(36,player.getMaxSp());
			ppstat.setInt(37,player.getMaxLoginTimes());
			//最大连续登陆天数
			ppstat.setInt(38,player.getMoney());
			ppstat.setString(39,player.getName());
			ppstat.setInt(40,player.getNonage());
			
			ArrayList<Pet> petList=new ArrayList<Pet>();
			petList.addAll(player.getPetList());
			String pets="";
			for (Pet pet : petList) {
				pets+=pet.getModelId()+"|";
			}
			ppstat.setString(41,pets);
			if(player.getProhibitChatTime()==0){
				ppstat.setString(42,"");	
			}else{
				ppstat.setString(42,new Date(player.getProhibitChatTime()).toLocaleString());
			}
			ppstat.setInt(43,player.getSex());
			ppstat.setInt(44,player.getShowrelation());
			ppstat.setInt(45,player.getSignsum());
			ppstat.setInt(46,player.getSort());
			ppstat.setInt(47,player.getSpeed());
			ppstat.setInt(48,player.getStoreCellsNum());
			ppstat.setLong(49,player.getTotalconsumebindgold());
			ppstat.setLong(50,player.getTotalconsumemoney());
			ppstat.setLong(51,player.getTotalgetbindgold());
			ppstat.setLong(52,player.getTotalgetmoney());
			ppstat.setInt(53,player.getTotalSkillLevel());
			ppstat.setString(54,player.getUserId());
			ppstat.setString(55,player.getUserName());
			ppstat.setString(56,player.getWebName());
			ppstat.setInt(57,player.isForbid()?1:0);
			ppstat.setLong(58,player.getId());
			ppstat.setString(59, new Date().toLocaleString());
			ppstat.setLong(60, System.currentTimeMillis());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String buildDDL(){
		List<ColumnInfo> buildFields = buildFields();
		StringBuffer ddl=new StringBuffer();
		ddl.append("CREATE TABLE IF NOT EXISTS `rolestate` (");
		for (ColumnInfo info : buildFields) {
			ddl.append("\r\n"+info.toDDL()+",");
		}
		ddl.substring(0, ddl.length()-1);
		
		ddl.append("\r\n"+"PRIMARY KEY (`id`)");
		ddl.append("\r\n"+") ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;");
		return ddl.toString();
	}
	
//	public static void main(String args[]){
//		System.out.println(bui);
//	}
//	
	
	private String buildUpdateSqlExpress(){
		return "update rolestate set "+buildExpress()+" where id=?";
	}
	private String buildInsertSqlExpress(){
		return "insert into rolestate set "+buildExpress();
	}
	
	private String buildExpress(){
		StringBuffer sb=new StringBuffer();
		List<ColumnInfo> buildFields = buildFields();
		for (ColumnInfo info : buildFields) {
			sb.append(info.getName()+"=?,");
		}
		String express = sb.toString();
		if(express.endsWith(",")){
			express=express.substring(0, express.length()-1);			
		}
		return express;
	}
	public LinkedList<Long> getQueue() {
		return queue;
	}
	public void setQueue(LinkedList<Long> queue) {
		this.queue = queue;
	}
	public static boolean isStop() {
		return stop;
	}
	public static void setStop(boolean stop) {
		UpdateThread.stop = stop;
	}
	
}
