package db.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 表结构对比
 * @author 赵聪慧
 * @2012-11-1 下午9:00:10
 */
public class TableCompar {
	
	private HashMap<String,List<String>> changeMap=new HashMap<String, List<String>>();
	private static TableCompar instance=new TableCompar();
	public static TableCompar getInstance(){
		return instance;
	}
	
	private TableCompar(){
		initChangeMap();
	}
	
	
	private void initChangeMap(){
//		changeMap.put(key, value);
		List<String> bigintlist=new ArrayList<String>();
		bigintlist.add("varchar");
		bigintlist.add("longtext");
		bigintlist.add("text");
		bigintlist.add("bigint");
		changeMap.put("bigint", bigintlist);
		
		List<String> bitlist=new ArrayList<String>();
		bitlist.add("longtext");
		bitlist.add("varchar");
		bitlist.add("text");
		bitlist.add("bigint");
		bitlist.add("integer");
		bitlist.add("int");
		bitlist.add("int unsigend");
		bitlist.add("bit");
		changeMap.put("bit", bitlist);
		
		List<String> intlist=new ArrayList<String>();
		intlist.add("longtext");
		intlist.add("varchar");
		intlist.add("text");
		intlist.add("bigint");
		intlist.add("integer");
		intlist.add("int");
		intlist.add("int unsigned");
		changeMap.put("int", intlist);
		
		changeMap.put("integer", intlist);
		
		List<String> shortlist=new ArrayList<String>();
		shortlist.add("longtext");
		shortlist.add("varchar");
		shortlist.add("text");
		shortlist.add("bigint");
		shortlist.add("int");
		shortlist.add("integer");
		shortlist.add("short");
		changeMap.put("short", shortlist);
		
		List<String> bytelist=new ArrayList<String>();
		bytelist.add("longtext");
		bytelist.add("varchar");
		bytelist.add("text");
		bytelist.add("bigint");
		bytelist.add("int");
		bytelist.add("short");
		bytelist.add("integer");
		changeMap.put("byte", bytelist);
		
		List<String> varcharlist=new ArrayList<String>();
		varcharlist.add("longtext");
		varcharlist.add("varchar");
		varcharlist.add("text");
		changeMap.put("varchar", varcharlist);
		
		List<String> text=new ArrayList<String>();
		text.add("longtext");;
		text.add("text");
		changeMap.put("text", text);
		
		List<String> longtextlist=new ArrayList<String>();
		longtextlist.add("longtext");
		changeMap.put("longtext", longtextlist);
		//TODO 待补全
	}
	
	
	
	
	public String compartor(Connection conn1,Connection conn2,String tableName1,String tableName2){
		try{
//			String[] tableNames = DBUtils.getTableName(conn1);
//			List<ColumnInfo> clomnDefine =DBUtils.getClomnDefine(conn1, tableNames[0]);
//			for (ColumnInfo columnInfo : clomnDefine) {
//				System.out.println(columnInfo.toDDL());
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public List<String> compartor(String tableName,List<ColumnInfo> source,List<ColumnInfo> target) throws Exception{
		HashMap<String, ColumnInfo> targetmap=new HashMap<String, ColumnInfo>();
		List<String> notnull=new ArrayList<String>();
		List<String> result=new ArrayList<String>();
		for (ColumnInfo columnInfo : target) {
			targetmap.put(columnInfo.getName(),columnInfo);
			if(columnInfo.getNullable()!=null&&!columnInfo.getNullable()){
				notnull.add(columnInfo.getName());
			}
		}
		for (ColumnInfo sourceinfo : source) {
			ColumnInfo columnInfo = targetmap.get(sourceinfo.getName().toLowerCase());
			if(columnInfo==null){
				result.add("ALTER TABLE "+tableName+" ADD COLUMN "+sourceinfo.toDDL()+";");
			}else{
				if(ableChange(sourceinfo, columnInfo)){
					String com=compartor(sourceinfo, columnInfo);
					if(!com.equals("")){
						result.add("ALTER TABLE "+tableName+" MODIFY COLUMN "+com+";");	
					}else{
					}
				}else{
					throw new Exception(tableName+" "+sourceinfo.toString()+" to "+columnInfo+"列类型不匹配  无法自动变更");
				}
			}
		}
		return result;
	}
	
	public String compartor(ColumnInfo info,ColumnInfo info2){
		if(info.getType().equals("int")||info.getType().equals("integer")||info.getType().startsWith("int")){
			if(info2.getType().equals("integer")||info2.getType().equals("int")||info2.getType().startsWith("int")){
				return "";	
			}
		}
		if(info.getType().equals("bigint")&&info2.getType().equals(info.getType())){
			return "";
		}
		if(info.getType().equals("text")&&info2.getType().equals(info.getType())){
			return "";
		}
		if(info.getType().equals("longtext")&&info2.getType().equals(info.getType())){
			return "";
		}
		if(info.getType().equals("bit")&&info2.getType().equals(info.getType())){
			return "";
		}		
		
		
		if(info.getType().equals(info2.getType())&&info.getSize()<=info2.getSize()&&info.getNullable()==info2.getNullable()){
			//相同
			return "";
		}else{
			return info.toDDL();	
		}
	}
	
	
	private  boolean ableChange(ColumnInfo info,ColumnInfo info2){
		List<String> list = changeMap.get(info.getType());
		if(list==null){
			return false;
		}
		//匹配列表存在
		if(!list.contains(info2.getType())){
			return false;
		}
		
//		if (info.getType().equals("int")
//				|| info.getType().startsWith("int")
//				|| info.getType().equals("bit")
//				|| info.getType().equals("bigint") 
//				|| info.getType().equals("integer") 
//				|| info.getType().equals("text")
//				|| info.getType().equals("longtext")) {			
//			
//		}else{
//			if(info.getType().equals(info2.getType())&&info.getSize()>info2.getSize()){
//				return false;
//			}
//		}
		//新字段不能比老字段小
		return true;
	}
	
	public static void main(String args[]){		
		
		try {
			// DBFactory db = new DBFactory();
			Connection conn = DBUtils.createConnection("jdbc:mysql://192.168.1.121:3306/gamelog?connectTimeout", "game", "game");
			Connection conn2 = DBUtils.createConnection("jdbc:mysql://192.168.1.120:5849/gamelog?connectTimeout", "game", "game");
			
			List<String> tableNames = DBUtils.getTableName(conn);
			List<String> tableNames2 = DBUtils.getTableName(conn2);
//			TableCompar compar=new TableCompar();
			List<ColumnInfo> columnDefine = DBUtils.getColumnDefine(conn, "rolestate");
			List<ColumnInfo> columnDefine2 = DBUtils.getColumnDefine(conn2, "rolestate");
//			String compartor = TableCompar.getInstance().compartor(columnDefine, columnDefine2);
			List<String> compartor = TableCompar.getInstance().compartor("rolestate", columnDefine2, columnDefine);
			for (String string : compartor) {
				System.out.println(string);	
			} 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	

}
