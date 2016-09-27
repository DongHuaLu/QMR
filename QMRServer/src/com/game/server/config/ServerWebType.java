package com.game.server.config;


/**平台标识
 * 
 * @author zhangrong
 *
 */
public enum ServerWebType {
	/**
	 * 37wan平台
	 */
	Web_37wan  		("37wan"),
	
	
	/**
	 * 360平台
	 */
	Web_360  		("360"),
	
	
	/**
	 * pps平台
	 */
	Web_pps			("pps"),
	
	
	Web_95k			("95k"),
	
	Web_6711			("6711"),
	
	Web_baidu			("baidu"),

	Web_kuaiwan			("kuaiwan"),
	
	Web_gtv			("gtv"),
	
	Web_duowan			("duowan"),
	
	Web_fengxing		("fengxing"),
	
	Web_7k7k			("7k7k"),
	
	Web_pptv			("pptv"),
	
	Web_swkedou			("swkedou"),
	
	Web_sougou			("sougou"),
	
	Web_mlxyrms			("mlxyrms"),
	
	Web_xunlei			("xunlei"),
	
	web_jinshan 		("jinshan"),//金山
	
	/**台湾繁体
	 * 
	 */
	web_twgmei			("twgmei"),
	
	/**马来西亚
	 * 
	 */
	web_mlxyrms			("mlxyrms"),
	
	/**韩国版
	 * 
	 */
	web_hgpupugame		("hgpupugame"),
	;
	
	private String value;
	
	ServerWebType(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
