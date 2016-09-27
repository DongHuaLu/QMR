package com.game.json;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.NewBeanInstanceStrategy;
import net.sf.json.util.PropertyFilter;
import net.sf.json.util.PropertySetStrategy;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JSONserializable {

	//日志
	private static Logger log = LogManager.getLogger(JSONserializable.class);
		
	/**
	 * 字段缓存 第一层key为类的名字 第二层key为字段名称
	 */
	private static HashMap<String, HashMap<String, Field>> fields = new HashMap<String, HashMap<String, Field>>();
	
	/**
	 * 序列化配置
	 */
	private static JsonConfig serializableConfig = new JsonConfig();
	//初始化
	static{
		// 忽略public字段
		serializableConfig.setIgnorePublicFields(true);
		// 忽略transient字段
		serializableConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				try {
					Field field = getDeclaredField(source.getClass(), name);
					// 检查是否为transient字段
					if (field != null
							&& Modifier.isTransient(field.getModifiers())) {
						return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		});
	}
	
	/**
	 * 反序列化配置
	 */
	private static JsonConfig unserializableConfig = new JsonConfig();
	//初始化
	static{
		//设置类初始化策略
		unserializableConfig.setNewBeanInstanceStrategy(new NewBeanInstanceStrategy() {
			@SuppressWarnings("rawtypes")
			@Override
			public Object newInstance(Class c, JSONObject jo)
					throws InstantiationException, IllegalAccessException {
				//是否为抽象类
				if (Modifier.isAbstract(c.getModifiers())) {
					try {
						//返回类
						return Class.forName(jo.getString("clazz")).newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return c.newInstance();
			}
		});

		//设置属性赋值策略
		unserializableConfig.setPropertySetStrategy(new PropertySetStrategy() {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void setProperty(Object bean, String key, Object value)
					throws JSONException {
				//System.out.println(bean);
				if(!(bean instanceof List) && !(bean instanceof Map) && !(bean instanceof Set)){
					if(getDeclaredField(bean.getClass(), key)==null){
//						log.error("bean:" + bean + ", key:" + key + ", value:" + value);
						return;
					}
				}
				//value是否被反序列化为MorphDynaBean
				if (value!=null && MorphDynaBean.class.isAssignableFrom(value.getClass())) {
					MorphDynaBean _bean = (MorphDynaBean) value;
					try{
						_bean.get("clazz");
					}catch (Exception e) {
						return;
					}
					try {
						//System.out.println((String)_bean.get("clazz"));
						
						Class clazz = null;
						try{
							clazz = Class.forName((String)_bean.get("clazz"));
						}catch (Exception e) {}
						if(clazz==null) return;
						
						// json反序列化
						JsonConfig jsonConfig = unserializableConfig.copy();
						
						jsonConfig.setRootClass(clazz);
						//重新反序列化
						value = JSONObject.toBean(
								(JSONObject)JSONSerializer.toJSON(_bean, serializableConfig),
								jsonConfig);
					} catch (Exception e) {
						throw new JSONException( e );
					}
				}else if(value!=null && value instanceof List){
					List list = (List)value;
					if(list.size()==0) return;
					Object obj = list.get(0);
					if(MorphDynaBean.class.isAssignableFrom(obj.getClass())){
						List temp = new ArrayList();
						for (int i = 0; i < list.size(); i++) {
							MorphDynaBean _bean = (MorphDynaBean) list.get(i);
							try{
								_bean.get("clazz");
							}catch (Exception e) {
								continue;
							}
							try {
								
								
								//System.out.println((String)_bean.get("clazz"));
								Class clazz = null;
								try{
									clazz = Class.forName((String)_bean.get("clazz"));
								}catch (Exception e) {}
								if(clazz==null) return;
								
								// json反序列化
								JsonConfig jsonConfig = unserializableConfig.copy();
								
								jsonConfig.setRootClass(clazz);
								//重新反序列化
								temp.add(JSONObject.toBean(
										(JSONObject)JSONSerializer.toJSON(_bean, serializableConfig),
										jsonConfig));
							} catch (Exception e) {
								throw new JSONException( e );
							}
						}
						
						value = temp;
					}
				}
				//赋值
				if (bean instanceof Map) {
					((Map) bean).put(key, value);
				} else {
					try {
			            PropertyUtils.setSimpleProperty( bean, key, value );
			        } catch( NoSuchMethodException e ) {
			        	throw new JSONException( e );
			        } catch( Exception e ) {
			            throw new JSONException( e );
			        }
				}

			}
		});
	}

	/**
	 * 序列化对象
	 * @param object 对象
	 * @return 字符串
	 */
	public static String toString(Object obj) {
		try{
			// 序列化
			Object object = JSONSerializer.toJSON(obj, serializableConfig);
			// 返回字符串
			return object.toString();
		}catch (Exception e) {
			log.error(e, e);
			log.error(JSONSerializer.toJSON(obj, serializableConfig));
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * @param data 序列化后的字符串
	 * @return 对象
	 */
	public static Object toObject(String data, Class<?> clazz) {
		try{
			// json反序列化
			JsonConfig jsonConfig = unserializableConfig.copy();
			// 设置反序列化类
			jsonConfig.setRootClass(clazz);
			//反序列化
			JSONObject object = JSONObject.fromObject(data);
	
			return JSONObject.toBean(object, jsonConfig);
		}catch (Exception e) {
			log.error(e, e);
			log.error(data);
			e.printStackTrace();
		}
		return null;
	}
	
//	/**
//	 * 反序列化对象
//	 * @param data 序列化后的字符串
//	 * @return 对象
//	 */
//	public static Object toArray(String data, Class<?> clazz) {
//		try{
//			// json反序列化
//			JsonConfig jsonConfig = unserializableConfig.copy();
//			// 设置反序列化类
//			jsonConfig.setRootClass(clazz);
//			//反序列化
//			JSONArray object = JSONArray.fromObject(data);
//	
//			return JSONArray.toArray(object, jsonConfig);
//		}catch (Exception e) {
//			log.error(e, e);
//			log.error(data);
//		}
//		return null;
//	}
	
	/**
	 * 反序列化对象
	 * @param data 序列化后的字符串
	 * @return 对象
	 */
	public static Object toList(String data, Class<?> clazz) {
		try{
			// json反序列化
			JsonConfig jsonConfig = unserializableConfig.copy();
			// 设置反序列化类
			jsonConfig.setRootClass(clazz);
			//反序列化
			JSONArray object = JSONArray.fromObject(data);
	
			return JSONArray.toCollection(object, jsonConfig);
		}catch (Exception e) {
			log.error(e, e);
			log.error(data);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取出类c中name字段
	 * @param c 类
	 * @param name 字段名
	 * @return 字段
	 */
	private static Field getDeclaredField(Class<?> c, String name) {
		// 缓存中是否储存了类包含的字段列表
		if (fields.containsKey(c.getName())) {
			// 取出要获取的字段
			return fields.get(c.getName()).get(name);
		} else {
			Class<?> _c = c;
			// 建立字段映射表
			HashMap<String, Field> fieldMap = new HashMap<String, Field>();
			// 循环获取c以及c父类的字段列表
			while (_c != null) {
				// 获得c的字段列表
				Field[] _fields = _c.getDeclaredFields();
				for (int i = 0; i < _fields.length; i++) {
					// 子类中是否已包含同名字段
					if (!fieldMap.containsKey(_fields[i].getName())) {
						// 不包含则放入
						fieldMap.put(_fields[i].getName(), _fields[i]);
					}
				}
				// 获得c的父类
				_c = _c.getSuperclass();
			}
			// 放入字段缓存
			fields.put(c.getName(), fieldMap);

			// 取出要获取的字段
			return fields.get(c.getName()).get(name);
		}
	}
//	
//	public static void main(String[] args) {
//	//	String data="{\"addBlackCount\":0,\"addBlackTime\":0,\"backpackItems\":{},\"bagCellTimeCount\":1578,\"bagCellsNum\":46,\"bindGold\":0,\"blackRoleList\":[],\"buffs\":[{\"actionType\":27,\"clazz\":\"com.game.buff.structs.StateBuff\",\"id\":527152252806038,\"modelId\":10013,\"parameter\":0,\"percent\":0,\"remain\":0,\"source\":527092097434588,\"sourceType\":0,\"start\":1335188599493,\"timer\":0,\"totalTime\":5100,\"value\":0}],\"buybackList\":[],\"canreceiveyuanbao\":0,\"clazz\":\"com.game.player.structs.Player\",\"cooldowns\":{\"SKILL_1\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":527152626689002,\"key\":\"SKILL_1\",\"start\":1335188605198,\"type\":\"SKILL\"},\"RECOVER\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":5000,\"id\":527152495616975,\"key\":\"RECOVER\",\"start\":1335188603198,\"type\":\"RECOVER\"},\"SKILL_10003\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":524718645715943,\"key\":\"SKILL_10003\",\"start\":1335151465596,\"type\":\"SKILL\"},\"SKILL_10002\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":527152140215157,\"key\":\"SKILL_10002\",\"start\":1335188597775,\"type\":\"SKILL\"},\"SKILL_10011\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":527055764992247,\"key\":\"SKILL_10011\",\"start\":1335187127206,\"type\":\"SKILL\"},\"SKILL_10010\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":527073953135180,\"key\":\"SKILL_10010\",\"start\":1335187404735,\"type\":\"SKILL\"},\"SKILL_10013\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":524564918008778,\"key\":\"SKILL_10013\",\"start\":1335149119897,\"type\":\"SKILL\"},\"SKILL_10012\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":527064557762004,\"key\":\"SKILL_10012\",\"start\":1335187261373,\"type\":\"SKILL\"},\"SKILL_10015\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":524561874057399,\"key\":\"SKILL_10015\",\"start\":1335149073450,\"type\":\"SKILL\"},\"SKILL_10014\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":524561244125182,\"key\":\"SKILL_10014\",\"start\":1335149063838,\"type\":\"SKILL\"},\"CLEAR_DEBUFF\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":2000,\"id\":524565486271591,\"key\":\"CLEAR_DEBUFF\",\"start\":1335188503954,\"type\":\"CLEAR_DEBUFF\"},\"SKILL_PUBLIC_1\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":952,\"id\":527152626689003,\"key\":\"SKILL_PUBLIC_1\",\"start\":1335188605198,\"type\":\"SKILL_PUBLIC\"},\"JUMP\":{\"clazz\":\"com.game.cooldown.structs.Cooldown\",\"delay\":500,\"id\":527143815437691,\"key\":\"JUMP\",\"start\":1335188470749,\"type\":\"JUMP\"}},\"createServerId\":1,\"defaultSkill\":0,\"delete\":false,\"dieTime\":1335152544338,\"direction\":6,\"equips\":[null,null,null,null,null,null,null,null,null,null,null,null],\"exp\":35990,\"forbid\":false,\"gold\":0,\"hp\":9877077,\"icon\":\"resource/icon/head/boyHead\",\"id\":524527519811646,\"level\":100,\"lgDianjiangchunDay\":0,\"longinTime\":1335148551086,\"map\":20001,\"modelId\":0,\"money\":100000000,\"mp\":0,\"name\":\"FUCK~~`or~~``FUCK?\",\"nowLearnSkillId\":0,\"petList\":[],\"position\":{\"x\":1950,\"y\":1550},\"prestige\":0,\"prohibitChatEndTime\":0,\"serverId\":1,\"sex\":1,\"shortCuts\":[null,null,null,null,null,null,null,null,null,null],\"skillLearnTime\":0,\"skills\":[{\"clazz\":\"com.game.skill.structs.Skill\",\"id\":524527519811648,\"skillLevel\":1,\"skillModelId\":1}],\"sp\":101,\"stDianjiangchunSaveData\":{\"bosonList\":[],\"btFreechangeluckcount\":0,\"btUsecount\":0,\"clazz\":\"com.game.dianjiangchun.structs.DianjiangchunSaveData\",\"id\":524527519811644,\"nInfuriatingvalue\":0,\"nReceiveintinfuriatingvalue\":0},\"stSaveRankinfo\":{\"clazz\":\"com.game.rank.structs.Rank\",\"combinationskill\":[],\"id\":524527519811645,\"militaryexp\":0,\"ranklv\":0},\"storeCellTimeCount\":2504,\"storeCellsNum\":53,\"storeItems\":{},\"tianyuanlv\":0,\"tianyuannum\":0,\"userId\":8444492352365723655,\"zhenqi\":0}";
//		//List<String> data = new ArrayList<String>();
//		String data = "{\"攻击\":100, \"防御\":100, \"生命上限\":100, \"暴击\":100}";
//		
//		String temp = "[{\"clazz\":\"com.game.guild.structs.ApplyAndInvite\",\"destid\":0,\"guildid\":5629587862101811668,\"id\":5629587862245166765,\"srcid\":1,\"type\":4},{\"clazz\":\"com.game.guild.structs.ApplyAndInvite\",\"destid\":0,\"guildid\":5629587862101811668,\"id\":5629587862246158492,\"srcid\":2,\"type\":4}]";
////		//反序列化
////		JSONObject object = JSONObject.fromObject(data);
//		
//		//System.out.println(JSONserializable.toObject(data, HashMap.class));
//		ArrayList obj = (ArrayList)JSONserializable.toList(temp, ApplyAndInvite.class);
//		System.out.println(obj);
//	}
}
