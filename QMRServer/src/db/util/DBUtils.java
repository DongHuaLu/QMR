package db.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 
 * @author 赵聪慧
 * @2012-10-23 下午3:58:48
 */
public class DBUtils {
	
	/**
	 * String url="jdbc:mysql://localhost:3306/db_name";    //连接的URL,db_name为数据库名      
	 * String Username="username";    //用户名  
	 * String Password="password";    //密码  
	 * @param URL
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static Connection createConnection(String URL,String username,String password) throws SQLException{
		DbUtils.loadDriver("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(URL+"?characterEncoding=utf8",username,password);
		return connection;
	}
	
	public static Connection createConnection(String serverip,int port,String dbname,String username,String pwd) throws SQLException{
		return DBUtils.createConnection("jdbc:mysql://" + serverip + ":" + port + "/" + dbname,	username, pwd);
	}
	
	
	public static <T> T query(Connection conn,String sql,ResultSetHandler<T> rsh) throws SQLException{
		Statement stmt;
        ResultSet rs;
        T result;
        if(conn == null)
            throw new SQLException("Null connection");
        if(sql == null)
        {
            throw new SQLException("Null SQL statement");
        }
        if(rsh == null)
        {
            throw new SQLException("Null ResultSetHandler");
        }
        stmt = null;
        rs = null;
        result = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        result = rsh.handle(rs);
        stmt.close();
        return result;
	}
	
	
	
	/**
	 * 执行SQL 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int execture(Connection conn, String sql) throws SQLException{
		Statement stmt;
        if(conn == null)
            throw new SQLException("Null connection");
        if(sql == null)
        {
            throw new SQLException("Null SQL statement");
        }
        stmt = null;
        stmt=conn.createStatement();
        return stmt.executeUpdate(sql);
        
	}
	
	
	public static List<String> getTableName(Connection conn) throws SQLException{
		ResultSet tableRet = conn.getMetaData().getTables(null, null,null,null);
		List<String> tablenames=new ArrayList<String>();
		while(tableRet.next()) {
			tablenames.add(tableRet.getString("TABLE_NAME").toLowerCase());
		}
		return tablenames;
	}
	public static List<ColumnInfo> getColumnDefine(Connection conn,String tableName) throws SQLException{
		DatabaseMetaData metaData = conn.getMetaData();
		ResultSet columns =metaData.getColumns(null,"%", tableName,"%");
		ResultSet primaryKey = metaData.getPrimaryKeys(null,"%", tableName);
		primaryKey.next();
		List<ColumnInfo> infos=new ArrayList<ColumnInfo>();
		while (columns.next()) {
			ColumnInfo info=new ColumnInfo();
			info.setName(columns.getString("COLUMN_NAME").toLowerCase());  
            info.setType(columns.getString("TYPE_NAME").toLowerCase());  
            info.setSize(columns.getInt("COLUMN_SIZE"));  
            info.setNullable(columns.getBoolean("IS_NULLABLE"));
            info.setPrimary(primaryKey.getString(4));
			infos.add(info);
		}
		return infos;
	}
	
	
	public static void main(String args[]) {
		try {
			Connection createConnection = createConnection("jdbc:mysql://192.168.1.121:3306/game", "game", "game");
			List<String> tableName = getTableName(createConnection);
			for (String string : tableName) {
				System.out.println(string);
				List<ColumnInfo> clomnDefine = getColumnDefine(createConnection, string);
				for (ColumnInfo columnInfo : clomnDefine) {
					System.out.println("\t" + columnInfo);
				}
			}

			// QueryRunner queryRunner = new QueryRunner();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
