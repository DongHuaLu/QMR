package db.util;

import java.util.HashMap;

/**
 * 
 * @author 赵聪慧
 * @2012-11-1 下午11:03:55
 */
public class ColumnInfo {
	public static final String[] FIELD=new String[]{"TABLE_CAT","TABLE_SCHEM","TABLE_NAME","COLUMN_NAME","DATA_TYPE","TYPE_NAME","COLUMN_SIZE","BUFFER_LENGTH","DECIMAL_DIGITS","NUM_PREC_RADIX","NULLABLE","REMARKS","COLUMN_DEF","SQL_DATA_TYPE","SQL_DATETIME_SUB","CHAR_OCTET_LENGTH","ORDINAL_POSITION"};
//	private HashMap<String, String> map=new HashMap<String, String>();
	public static final String TABLE_CAT = "TABLE_CAT";
	public static final String TABLE_SCHEM = "TABLE_SCHEM";
	public static final String TABLE_NAME = "TABLE_NAME";
	public static final String COLUMN_NAME = "COLUMN_NAME";
	public static final String DATA_TYPE = "DATA_TYPE";
	public static final String TYPE_NAME = "TYPE_NAME";
	public static final String COLUMN_SIZE = "COLUMN_SIZE";
	public static final String BUFFER_LENGTH = "BUFFER_LENGTH";
	public static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
	public static final String NUM_PREC_RADIX = "NUM_PREC_RADIX";
	public static final String NULLABLE = "NULLABLE";
	public static final String REMARKS = "REMARKS";
	public static final String COLUMN_DEF = "COLUMN_DEF";
	public static final String SQL_DATA_TYPE = "SQL_DATA_TYPE";
	public static final String SQL_DATETIME_SUB = "SQL_DATETIME_SUB";
	public static final String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH";
	public static final String ORDINAL_POSITION = "ORDINAL_POSITION";
	private String name;
	private String type;
	private Integer size;
	private Boolean nullable;
	private String def;
		
	public static ColumnInfo createColumnInfo(String name, String type, Integer size, Boolean nullable, String def, String primary) {
		ColumnInfo info=new ColumnInfo();
		info.setName(name);
		info.setType(type);
		info.setSize(size);
		info.setNullable(nullable);
		info.setDef(def);
		info.setPrimary(primary);
		return info;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Boolean getNullable() {
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
	}
	
	private String primary;
	
	
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	
	
	@Override
	public String toString() {
		return "ColumnInfo [name=" + name + ", type=" + type + ", size=" + size + ", nullable=" + nullable + ", def=" + def + ", primary=" + primary + "]";
	}
	public String toDDL(){
		String ddl=getName()+"\t"+getFieldType()+"\t"+getNullAble();
		
		return ddl;
//		return getName()+"\t"+getFieldType()+"\t"+(getNullable()?"\t":"not null\t")+def==null?"":def;
	}

	private String getFieldType(){
		if(getType().equalsIgnoreCase("text")||getType().equalsIgnoreCase("longtext")){
			return getType();
		}else{
			return getType()+"("+getSize()+")";
		}
	}
	
	private String getNullAble(){
		return (getNullable()?"":"not null");
	}
}