//package com.game.dblog.task;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import javax.sql.DataSource;
//
//import org.apache.log4j.Logger;
//
//import com.game.horse.manager.HorseManager;
//import com.game.horse.struts.Horse;
//import com.game.pet.struts.Pet;
//import com.game.player.manager.PlayerManager;
//import com.game.player.structs.Player;
//import com.game.utils.BeanUtil;
//
///**
// * 
// * @author 赵聪慧
// * @2012-10-29 下午6:13:53
// */
//public class UpdateLogTask implements Runnable {
//	private static final Logger logger = Logger.getLogger(DBLogTask.class);
//	public final static AtomicInteger count=new AtomicInteger();
//	private DataSource ds;
//	private List<Long> roleids;
//	
//	public UpdateLogTask(List<Long> roleids,DataSource ds){
//		this.roleids=roleids;
//		this.ds=ds;
//	}
//
//	
//	
//
//	
//	
//	
//	@Override
//	public void run() {
//		Connection connection = null;
//		try {
//			connection=ds.getConnection();
//			PreparedStatement ppstmt = connection.prepareStatement(buildUpdateSqlExpress());
//			if(roleids!=null&&roleids.size()>0){
//				boolean isupdate=false;
//				for (Long roleId : roleids) {
//					Player player = PlayerManager.getInstance().getPlayer(roleId);
//					if(player==null){
//						continue;
//					}
//					if(player.isUpdatelogtag()){
//						isupdate=true;
//						buildParam(player, ppstmt);
//						ppstmt.setLong(59,player.getId());
//						ppstmt.addBatch();
//					}
//				}
//				if(isupdate){
//					ppstmt.execute();
//				}
//				ppstmt = connection.prepareStatement(buildInsertSqlExpress());
//				boolean isinsert=false;
//				for (Long roleId : roleids) {
//					Player player = PlayerManager.getInstance().getPlayer(roleId);
//					if(player==null){
//						continue;
//					}
//					if(player.isUpdatelogtag()){
//						isinsert=true;
//						buildParam(player, ppstmt);
//						ppstmt.addBatch();
//						player.setUpdatelogtag(true);
//					}
//				}
//				if(isinsert){
//					ppstmt.execute();
//				}
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//			logger.error(e1, e1);
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					logger.error(e, e);
//				}
//			}
//		}
//
//	}
//
//	
//	public static void main(String agrs[]){
//		UpdateLogTask task=new UpdateLogTask(null, null);
//		System.out.println(task.buildFields().size());
//		System.out.println("update rolestate set "+task.buildExpress()+" where id=roleid");
//	}
//}
