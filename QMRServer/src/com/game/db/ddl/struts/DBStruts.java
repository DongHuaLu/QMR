package com.game.db.ddl.struts;

import java.util.ArrayList;
import java.util.List;

import db.util.ColumnInfo;

/**
 * 
 * @author 赵聪慧
 * @2012-11-18 下午6:43:02
 */
public abstract class DBStruts {
	protected static final String INT="int";
	protected static final String TINYINT="bit";
	protected static final String VARCHAR="varchar";
	protected static final String BIGINT="bigint";
	protected static final String LONGTEXT="longtext";
	protected static final String TEXT="text";
	public DBStruts(){
		buildDbStruts();
	}
	List<ColumnInfo> list=new ArrayList<ColumnInfo>();
	/**
	 * 主键字段名
	 * @return
	 */
	public abstract String primary();
	/**
	 * 表名
	 * @return
	 */
	public abstract String tableName();
	/**
	 * 对应的类名
	 * @return
	 */
	public abstract String className();
	
	public abstract void buildDbStruts();
	/**
	 * 
	 * @param name 字段名
	 * @param type mysql类型
	 * @param size	长度
	 * @param nullable	是否允许为空
	 */
	public void add(String name,String type,int size,boolean nullable){
		ColumnInfo info = ColumnInfo.createColumnInfo(name, type, size, nullable, "", "");
		list.add(info);
	}
	
	public List<ColumnInfo> getFields(){
		return list;
	}
}
