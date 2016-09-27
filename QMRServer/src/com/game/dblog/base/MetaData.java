package com.game.dblog.base;

import db.util.ColumnInfo;

/**
 * 日志字段格式
 * @author 赵聪慧
 *
 */
public class MetaData {
	private String fieldName;
	private String fieldType;
	private String mate;
	public MetaData(String fieldName,String fieldType){
		this.fieldName=fieldName;
		this.fieldType=fieldType;
		this.mate="`"+fieldName+"`\t"+fieldType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public String toString(){
		return mate;
	}
	
	public ColumnInfo toColumnInfo(){
		ColumnInfo info=new ColumnInfo();
		info.setName(getFieldName());
		if(fieldType.contains("(")){
			String replace = fieldType.replace(")", "");
			String[] split = replace.split("\\(");
			info.setType(split[0]);
			info.setSize(Integer.valueOf(split[1]));
			info.setNullable(true);
		}else{
			info.setType(getFieldType());
			info.setSize(0);
			info.setNullable(true);
		}
		return info;
	}
}
