package com.game.dblog.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.lang.model.type.DeclaredType;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.base.MetaData;
import com.game.utils.BeanUtil;
/**
 * 日志类型基类
 * @author 赵聪慧
 *
 */
public abstract class BaseLogBean{
	/**
	 * Logger for this class
	 */
	protected static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger logger = Logger.getLogger(BaseLogBean.class);
	//字段DDL信息 用于建表
	private static ConcurrentHashMap<Class<? extends BaseLogBean>,HashSet<MetaData>> metadataset=new ConcurrentHashMap<Class<? extends BaseLogBean>,HashSet<MetaData>>();
	private long time=System.currentTimeMillis();
	private static SimpleDateFormat YYYY_MM_DD_HH_MM_SS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat YYYY_MM_DD=new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat YYYY_MM=new SimpleDateFormat("yyyyMM");
	private static SimpleDateFormat YYYY=new SimpleDateFormat("yyyy");
	
	public BaseLogBean(){
		//初始化日志字段信息
		if(!metadataset.containsKey(getClass())){
			HashSet<MetaData> metadata = new HashSet<MetaData>();
			Method[] declaredMethods = getClass().getMethods();
			for (Method method : declaredMethods) {
				Log annotation = method.getAnnotation(Log.class);
				if (annotation != null) {
					metadata.add(new MetaData(annotation.logField(), annotation.fieldType()));
				}
			}
			metadataset.putIfAbsent(getClass(), metadata);
		}
	}
	
	public String buildSql() {
		if (logger.isDebugEnabled()) {
			logger.debug("buildSql(BaseLogBean) - start");
		}
		//构建表名
		String tableName=buildTableName(time);
		String fields = "(";
		String values = "(";
		//构建插入语句主体
		HashMap<String, Object> param =getParam();
		for (String key : param.keySet()) {
			fields += key +",";
			values += dealValue(param.get(key)) + ",";
		}
		if (fields.length() > 1) {
			fields = fields.substring(0, fields.length() - 1);
		}
		if (values.length() > 1) {
			values = values.substring(0, values.length() - 1);
		}
		fields += ")";
		values += ")";
		//构建插入语句
		StringBuffer buffer = new StringBuffer("insert into `" + tableName + "` ").append(fields).append("values").append(values);
		String returnString = buffer.toString();
		if (logger.isDebugEnabled()) {
			logger.debug("buildSql(BaseLogBean) - end");
		}
		return returnString;
	}
	
	public String dealValue(Object object){
		if(object instanceof Date){
			return "'"+YYYY_MM_DD_HH_MM_SS.format(object)+"'";
		}
		if(object instanceof List){
			//暂不作集合类支持
		}
		if(object instanceof String){
			//TODO 防注入处理
//			object=
		}
		return object==null?"''":"'"+object.toString()+"'";
	}
	
	/**
	 * 构建表名加日期滚动
	 * @param tableName
	 * @param time
	 * @param type
	 * @return
	 */
	public String buildTableName(long time) {
		String tablename=getTableName();
		switch (getRollingStep()) {
		case DAY:
			tablename+=YYYY_MM_DD.format(new Date(time));
			break;
		case MONTH:
			tablename+=YYYY_MM.format(new Date(time));
			break;
		case YEAR:
			tablename+=YYYY.format(new Date(time));
			break;
//		case UNROLL:
		}
		return tablename;
	}
	
	public HashMap<String, Object> getParam(){
		HashMap<String,Object> param=new HashMap<String, Object>();
		Method[] declaredMethods = getClass().getMethods();
		for (Method method : declaredMethods) {
			Log annotation = method.getAnnotation(Log.class);
			if(annotation!=null){
				try {
					param.put(annotation.logField(),BeanUtil.invokeMethod(this, method));
				} catch (IllegalArgumentException e) {
					logger.error("getParam()", e);
				} catch (IllegalAccessException e) {
					logger.error("getParam()", e);
				} catch (InvocationTargetException e) {
					logger.error("getParam()", e);
				}	
			}
		}
		return param;
	}
	
	/**
	 * 建表语句
	 * @param tableName
	 * @param metadata
	 * @return
	 */
	public String buildCreateTableSql(long time) {
		StringBuffer DDL=new StringBuffer();
		DDL.append("CREATE TABLE IF NOT EXISTS `").append(buildTableName(time)).append("` (\n`id` int(11) NOT NULL AUTO_INCREMENT,\n");
		for (MetaData metaData : getMetadata()) {
			DDL.append(metaData).append(",\n");
		}
		DDL.append("PRIMARY KEY (`id`)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8");
		return DDL.toString();
	}
	
	/**
	 * 表名
	 * @return
	 */
	public String getTableName(){
		return getClass().getSimpleName();
	}
	
	/**
	 * 日志多长时间建一次表
	 * @return
	 */
	public abstract TableCheckStepEnum getRollingStep();
	
	public HashSet<MetaData> getMetadata(){
		return metadataset.get(getClass());
	}
	
	/**
	 * 日志产生时间
	 * @return
	 */
	@Log(logField="times",fieldType="bigint")
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}

	public static void main(String args[]){
//		RoleCreateLog roleCreateLog = new RoleCreateLog();
//		System.out.println(roleCreateLog.getTableName().toLowerCase());
	}

	public abstract void logToFile() ;
}
