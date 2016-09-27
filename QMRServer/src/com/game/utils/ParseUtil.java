package com.game.utils;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 客户端解析字符串
 *
 * @author 杨鸿岚
 */
public class ParseUtil {

	private String t;
	private List<Parm> ps = new ArrayList<Parm>();

	public List<Parm> getPs() {
		return ps;
	}

	public void setPs(List<Parm> ps) {
		this.ps = ps;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public void setFormatParseString(String parseString, Object... values) {
		this.t = String.format(parseString, values);
	}

	public void setValue(String parseString, Parm... parms) {
		setT(parseString);
		if (parms != null) {
			for (int i = 0; i < parms.length; i++) {
				Parm parm = parms[i];
				if (parm != null) {
					getPs().add(parm);
				}
			}
		}
	}

	public void setValue(Parm... parms) {
		if (parms != null) {
			for (int i = 0; i < parms.length; i++) {
				Parm parm = parms[i];
				if (parm != null) {
					getPs().add(parm);
				}
			}
		}
	}

	public void setPlayerParm(long playerid, String playerName) {
		getPs().add(new PlayerParm(playerid, playerName));
	}

	public void setMapParm(int mapid, byte line, int x, int y) {
		getPs().add(new MapParm(mapid, line, x, y));
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public static String toString(ParseUtil parseUtil) {
		return JSON.toJSONString(parseUtil);
	}

	public static ParseUtil toObject(String jsonString) {
		return (ParseUtil) JSON.parse(jsonString);
	}

	//------------------------------参数类-------------------------//
	public static class Parm {

		private byte type;

		public Parm(byte type) {
			this.type = type;
		}

		public byte getType() {
			return type;
		}

		public void setType(byte type) {
			this.type = type;
		}
	}

	public static class PlayerParm extends Parm {

		private String pid;
		private String name;

		public PlayerParm(long playerid, String playerName) {
			super((byte) 1);
			this.pid = Long.toString(playerid, 16);
			this.name = playerName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}
	}

	/**扩展名字
	 * 
	 * @author zhangrong
	 *
	 */
	public static class PlayerExParm extends Parm {

		private String pid;
		private String name;
		private String cname;
		private String gname;
		
		public PlayerExParm(long playerid, String playerName  ,String cname , String gname) {
			super((byte) 1);
			this.pid = Long.toString(playerid, 16);
			this.name = playerName;
			this.cname = cname;
			this.gname =gname;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getCname() {
			return cname;
		}

		public void setCname(String cname) {
			this.cname = cname;
		}

		public String getGname() {
			return gname;
		}

		public void setGname(String gname) {
			this.gname = gname;
		}
	}
	
	public static class MapParm extends Parm {

		private int mapid;
		private byte line;
		private int x;
		private int y;

		public MapParm(int mapid, int line, int x, int y) {
			super((byte) 2);
			this.mapid = mapid;
			this.line = (byte) line;
			this.x = x;
			this.y = y;
		}

		public byte getLine() {
			return line;
		}

		public void setLine(byte line) {
			this.line = line;
		}

		public int getMapid() {
			return mapid;
		}

		public void setMapid(int mapid) {
			this.mapid = mapid;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
	
	public static String getMapString(Map<String, String> map, String param){
		String value = "";
		if(map.containsKey(param)){
			value = map.get(param);
		}
		return value;
	}
	public static int getMapInt(Map<String, String> map, String param){
		return getMapInt(map, param, 0);
	}
	public static int getMapInt(Map<String, String> map, String param, int defaultvalue){
		int value = defaultvalue;
		if (map.containsKey(param)) {
			String v = map.get(param);
			if (!StringUtils.isBlank(v) 
					&& (StringUtils.isNumeric(v) || (v.length()>1 && v.startsWith("-")&&StringUtils.isNumeric(v.substring(1)))))
				value = Integer.parseInt(v);
		}
		return value;
	}
	
	public static class VipParm extends Parm {
		
		private int viplv;
		private int idx;

		public VipParm(int viplv, int idx) {
			super((byte) 3);
			this.viplv = viplv;
			this.idx = idx;
		}

		public int getIdx() {
			return idx;
		}

		public void setIdx(int idx) {
			this.idx = idx;
		}

		public int getViplv() {
			return viplv;
		}

		public void setViplv(int viplv) {
			this.viplv = viplv;
		}
	}
	

	public static class WebParm extends Parm {
		private String name;
		private String url;

		public WebParm(String name, String url) {
			super((byte) 4);
			this.setName(name);
			this.setUrl(url);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		
	}
	
	
	
}
