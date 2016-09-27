package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_shop Bean
 */
public class Q_shopBean {

	//本表索引编号（会影响到该物品在标签页中的排序）
	private int q_id;
	
	//商店出售模板编号
	private int q_model_id;
	
	//商店类型（0随身商店，1NPC商店，2元宝道具商城）
	private int q_shop_type;
	
	//商店标题
	private String q_shop_name;
	
	//所属商店子页编号
	private int q_page;
	
	//商店子页标签名称
	private String q_page_name;
	
	//标签页开放性别需求（0通用，1男，2女）
	private int q_page_sex;
	
	//出售物品ID
	private int q_sell;
	
	//物品强化等级定义
	private int q_strengthen;
	
	//物品附加属性定义（类型|百分比的分子;类型|百分比的分子;）
	private String q_append;
	
	//购买后的过期时间点（格式 yyyy-mm-dd hh:mm:ss）
	private String q_losttime;
	
	//购买后离自动失效前的存在时间（单位：秒）
	private int q_duration;
	
	//购买时是否立刻绑定（0不是，1是立刻绑定）
	private int q_buy_bind;
	
	//允许使用的货币类型类型（1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买）
	private int q_money_type;
	
	//游戏币价格修正为
	private int q_coin;
	
	//元宝价格
	private int q_gold;
	
	//绑定元宝价格
	private int q_bindgold;
	
	//游戏币原价显示为
	private int q_show_coin;
	
	//元宝原价显示为
	private int q_show_gold;
	
	//绑定元宝原价显示为
	private int q_show_bindgold;
	
	//物品在商城面板中的文字描述（需支持html语法）
	private String q_describe;
	
	//热销标识(0无热销，1热销中，2折扣，3热销+折扣)
	private int q_hot;
	
	//上架时间段(-1下架，0正常在架，[时间格式写法]限时上架的时间)
	private String q_rack;
	
	//显示所需人物等级
	private int q_show_level;
	
	//显示所需人物累计消耗元宝数
	private int q_show_cost;
	
	//打折比例(百分比)
	private int q_sale_rate;
	
	//打折时间表达式
	private String q_discount_time;
	
	//显示顺序
	private int q_index;
	
	//开服时间打折表达式
	private String q_openserver_discount;
	
	//开服时间上架表达式0-6开服前六天,h0-100开服前100个小时
	private String q_openserver_rack;
	
	//影响区服字段    区服；区服-区服；区服-&（无限大）
	private String q_service_area;
	
	//限购上限
	private int q_shop_limit;
	
	
	/**
	 * get 本表索引编号（会影响到该物品在标签页中的排序）
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 本表索引编号（会影响到该物品在标签页中的排序）
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 商店出售模板编号
	 * @return 
	 */
	public int getQ_model_id(){
		return q_model_id;
	}
	
	/**
	 * set 商店出售模板编号
	 */
	public void setQ_model_id(int q_model_id){
		this.q_model_id = q_model_id;
	}
	
	/**
	 * get 商店类型（0随身商店，1NPC商店，2元宝道具商城）
	 * @return 
	 */
	public int getQ_shop_type(){
		return q_shop_type;
	}
	
	/**
	 * set 商店类型（0随身商店，1NPC商店，2元宝道具商城）
	 */
	public void setQ_shop_type(int q_shop_type){
		this.q_shop_type = q_shop_type;
	}
	
	/**
	 * get 商店标题
	 * @return 
	 */
	public String getQ_shop_name(){
		return q_shop_name;
	}
	
	/**
	 * set 商店标题
	 */
	public void setQ_shop_name(String q_shop_name){
		this.q_shop_name = q_shop_name;
	}
	
	/**
	 * get 所属商店子页编号
	 * @return 
	 */
	public int getQ_page(){
		return q_page;
	}
	
	/**
	 * set 所属商店子页编号
	 */
	public void setQ_page(int q_page){
		this.q_page = q_page;
	}
	
	/**
	 * get 商店子页标签名称
	 * @return 
	 */
	public String getQ_page_name(){
		return q_page_name;
	}
	
	/**
	 * set 商店子页标签名称
	 */
	public void setQ_page_name(String q_page_name){
		this.q_page_name = q_page_name;
	}
	
	/**
	 * get 标签页开放性别需求（0通用，1男，2女）
	 * @return 
	 */
	public int getQ_page_sex(){
		return q_page_sex;
	}
	
	/**
	 * set 标签页开放性别需求（0通用，1男，2女）
	 */
	public void setQ_page_sex(int q_page_sex){
		this.q_page_sex = q_page_sex;
	}
	
	/**
	 * get 出售物品ID
	 * @return 
	 */
	public int getQ_sell(){
		return q_sell;
	}
	
	/**
	 * set 出售物品ID
	 */
	public void setQ_sell(int q_sell){
		this.q_sell = q_sell;
	}
	
	/**
	 * get 物品强化等级定义
	 * @return 
	 */
	public int getQ_strengthen(){
		return q_strengthen;
	}
	
	/**
	 * set 物品强化等级定义
	 */
	public void setQ_strengthen(int q_strengthen){
		this.q_strengthen = q_strengthen;
	}
	
	/**
	 * get 物品附加属性定义（类型|百分比的分子;类型|百分比的分子;）
	 * @return 
	 */
	public String getQ_append(){
		return q_append;
	}
	
	/**
	 * set 物品附加属性定义（类型|百分比的分子;类型|百分比的分子;）
	 */
	public void setQ_append(String q_append){
		this.q_append = q_append;
	}
	
	/**
	 * get 购买后的过期时间点（格式 yyyy-mm-dd hh:mm:ss）
	 * @return 
	 */
	public String getQ_losttime(){
		return q_losttime;
	}
	
	/**
	 * set 购买后的过期时间点（格式 yyyy-mm-dd hh:mm:ss）
	 */
	public void setQ_losttime(String q_losttime){
		this.q_losttime = q_losttime;
	}
	
	/**
	 * get 购买后离自动失效前的存在时间（单位：秒）
	 * @return 
	 */
	public int getQ_duration(){
		return q_duration;
	}
	
	/**
	 * set 购买后离自动失效前的存在时间（单位：秒）
	 */
	public void setQ_duration(int q_duration){
		this.q_duration = q_duration;
	}
	
	/**
	 * get 购买时是否立刻绑定（0不是，1是立刻绑定）
	 * @return 
	 */
	public int getQ_buy_bind(){
		return q_buy_bind;
	}
	
	/**
	 * set 购买时是否立刻绑定（0不是，1是立刻绑定）
	 */
	public void setQ_buy_bind(int q_buy_bind){
		this.q_buy_bind = q_buy_bind;
	}
	
	/**
	 * get 允许使用的货币类型类型（1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买）
	 * @return 
	 */
	public int getQ_money_type(){
		return q_money_type;
	}
	
	/**
	 * set 允许使用的货币类型类型（1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买）
	 */
	public void setQ_money_type(int q_money_type){
		this.q_money_type = q_money_type;
	}
	
	/**
	 * get 游戏币价格修正为
	 * @return 
	 */
	public int getQ_coin(){
		return q_coin;
	}
	
	/**
	 * set 游戏币价格修正为
	 */
	public void setQ_coin(int q_coin){
		this.q_coin = q_coin;
	}
	
	/**
	 * get 元宝价格
	 * @return 
	 */
	public int getQ_gold(){
		return q_gold;
	}
	
	/**
	 * set 元宝价格
	 */
	public void setQ_gold(int q_gold){
		this.q_gold = q_gold;
	}
	
	/**
	 * get 绑定元宝价格
	 * @return 
	 */
	public int getQ_bindgold(){
		return q_bindgold;
	}
	
	/**
	 * set 绑定元宝价格
	 */
	public void setQ_bindgold(int q_bindgold){
		this.q_bindgold = q_bindgold;
	}
	
	/**
	 * get 游戏币原价显示为
	 * @return 
	 */
	public int getQ_show_coin(){
		return q_show_coin;
	}
	
	/**
	 * set 游戏币原价显示为
	 */
	public void setQ_show_coin(int q_show_coin){
		this.q_show_coin = q_show_coin;
	}
	
	/**
	 * get 元宝原价显示为
	 * @return 
	 */
	public int getQ_show_gold(){
		return q_show_gold;
	}
	
	/**
	 * set 元宝原价显示为
	 */
	public void setQ_show_gold(int q_show_gold){
		this.q_show_gold = q_show_gold;
	}
	
	/**
	 * get 绑定元宝原价显示为
	 * @return 
	 */
	public int getQ_show_bindgold(){
		return q_show_bindgold;
	}
	
	/**
	 * set 绑定元宝原价显示为
	 */
	public void setQ_show_bindgold(int q_show_bindgold){
		this.q_show_bindgold = q_show_bindgold;
	}
	
	/**
	 * get 物品在商城面板中的文字描述（需支持html语法）
	 * @return 
	 */
	public String getQ_describe(){
		return q_describe;
	}
	
	/**
	 * set 物品在商城面板中的文字描述（需支持html语法）
	 */
	public void setQ_describe(String q_describe){
		this.q_describe = q_describe;
	}
	
	/**
	 * get 热销标识(0无热销，1热销中，2折扣，3热销+折扣)
	 * @return 
	 */
	public int getQ_hot(){
		return q_hot;
	}
	
	/**
	 * set 热销标识(0无热销，1热销中，2折扣，3热销+折扣)
	 */
	public void setQ_hot(int q_hot){
		this.q_hot = q_hot;
	}
	
	/**
	 * get 上架时间段(-1下架，0正常在架，[时间格式写法]限时上架的时间)
	 * @return 
	 */
	public String getQ_rack(){
		return q_rack;
	}
	
	/**
	 * set 上架时间段(-1下架，0正常在架，[时间格式写法]限时上架的时间)
	 */
	public void setQ_rack(String q_rack){
		this.q_rack = q_rack;
	}
	
	/**
	 * get 显示所需人物等级
	 * @return 
	 */
	public int getQ_show_level(){
		return q_show_level;
	}
	
	/**
	 * set 显示所需人物等级
	 */
	public void setQ_show_level(int q_show_level){
		this.q_show_level = q_show_level;
	}
	
	/**
	 * get 显示所需人物累计消耗元宝数
	 * @return 
	 */
	public int getQ_show_cost(){
		return q_show_cost;
	}
	
	/**
	 * set 显示所需人物累计消耗元宝数
	 */
	public void setQ_show_cost(int q_show_cost){
		this.q_show_cost = q_show_cost;
	}
	
	/**
	 * get 打折比例(百分比)
	 * @return 
	 */
	public int getQ_sale_rate(){
		return q_sale_rate;
	}
	
	/**
	 * set 打折比例(百分比)
	 */
	public void setQ_sale_rate(int q_sale_rate){
		this.q_sale_rate = q_sale_rate;
	}
	
	/**
	 * get 打折时间表达式
	 * @return 
	 */
	public String getQ_discount_time(){
		return q_discount_time;
	}
	
	/**
	 * set 打折时间表达式
	 */
	public void setQ_discount_time(String q_discount_time){
		this.q_discount_time = q_discount_time;
	}
	
	/**
	 * get 显示顺序
	 * @return 
	 */
	public int getQ_index(){
		return q_index;
	}
	
	/**
	 * set 显示顺序
	 */
	public void setQ_index(int q_index){
		this.q_index = q_index;
	}
	
	/**
	 * get 开服时间打折表达式
	 * @return 
	 */
	public String getQ_openserver_discount(){
		return q_openserver_discount;
	}
	
	/**
	 * set 开服时间打折表达式
	 */
	public void setQ_openserver_discount(String q_openserver_discount){
		this.q_openserver_discount = q_openserver_discount;
	}
	
	/**
	 * get 开服时间上架表达式0-6开服前六天,h0-100开服前100个小时
	 * @return 
	 */
	public String getQ_openserver_rack(){
		return q_openserver_rack;
	}
	
	/**
	 * set 开服时间上架表达式0-6开服前六天,h0-100开服前100个小时
	 */
	public void setQ_openserver_rack(String q_openserver_rack){
		this.q_openserver_rack = q_openserver_rack;
	}
	
	/**
	 * get 影响区服字段    区服；区服-区服；区服-&（无限大）
	 * @return 
	 */
	public String getQ_service_area(){
		return q_service_area;
	}
	
	/**
	 * set 影响区服字段    区服；区服-区服；区服-&（无限大）
	 */
	public void setQ_service_area(String q_service_area){
		this.q_service_area = q_service_area;
	}
	
	/**
	 * get 限购上限
	 * @return 
	 */
	public int getQ_shop_limit(){
		return q_shop_limit;
	}
	
	/**
	 * set 限购上限
	 */
	public void setQ_shop_limit(int q_shop_limit){
		this.q_shop_limit = q_shop_limit;
	}
	
}