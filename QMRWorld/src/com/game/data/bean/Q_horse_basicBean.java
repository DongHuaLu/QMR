package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horse_basic Bean
 */
public class Q_horse_basicBean {

	//坐骑阶数
	private int q_layer;
	
	//坐骑名称
	private String q_name;
	
	//名字颜色，支持html语法
	private String q_name_color;
	
	//是否允许骑战（0不允许，1允许）
	private int q_is_ridingwar;
	
	//头像ICO编号
	private int q_head_ico_id;
	
	//坐骑面板展示用造型资源编号
	private int q_panel_show_id;
	
	//场景中骑乘用造型资源编号
	private int q_scene_show_id;
	
	//场景中的蹄子光效资源
	private int q_huf_wirkung;
	
	//坐骑自身最大可升级等级
	private int q_level_max;
	
	//本阶坐骑所允许的最大技能等级
	private int q_skill_level_max;
	
	//成长潜力值
	private int q_growup;
	
	//进阶操作所需人物等级
	private int q_up_figure_level;
	
	//进阶消耗铜币数量
	private int q_up_gold_num;
	
	//进阶所需消耗材料ID
	private int q_up_item_id;
	
	//每次消耗材料数量
	private int q_up_item_num;
	
	//进阶成功所需进阶次数min
	private int q_up_num_min;
	
	//进阶成功所需进阶次数max
	private int q_up_num_max;
	
	//服务器端计算用进阶成功几率（万分比）
	private int q_up_probability;
	
	//客户端显示用成功率（支持HTML）
	private String q_up_clientshow_rate;
	
	//每次失败增加祝福值区间min
	private int q_blessnum_min;
	
	//每次失败增加祝福值区间max
	private int q_blessnum_max;
	
	//祝福值上限值
	private int q_blessnum_limit;
	
	//每次失败常规几率及增加人物经验（格式：几率|经验）
	private String q_normal_rnd;
	
	//每次失败小爆击几率及增加人物经验（格式：几率|经验）
	private String q_small_crit_rnd;
	
	//每次失败大爆击几率及增加人物经验（格式：几率|经验）
	private String q_large_crit_rnd;
	
	//进阶失败所获人物经验上限
	private int q_up_fail_addexp;
	
	//进阶成功是否全服公告（0不公告，1公告）
	private int q_notice;
	
	
	/**
	 * get 坐骑阶数
	 * @return 
	 */
	public int getQ_layer(){
		return q_layer;
	}
	
	/**
	 * set 坐骑阶数
	 */
	public void setQ_layer(int q_layer){
		this.q_layer = q_layer;
	}
	
	/**
	 * get 坐骑名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 坐骑名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 名字颜色，支持html语法
	 * @return 
	 */
	public String getQ_name_color(){
		return q_name_color;
	}
	
	/**
	 * set 名字颜色，支持html语法
	 */
	public void setQ_name_color(String q_name_color){
		this.q_name_color = q_name_color;
	}
	
	/**
	 * get 是否允许骑战（0不允许，1允许）
	 * @return 
	 */
	public int getQ_is_ridingwar(){
		return q_is_ridingwar;
	}
	
	/**
	 * set 是否允许骑战（0不允许，1允许）
	 */
	public void setQ_is_ridingwar(int q_is_ridingwar){
		this.q_is_ridingwar = q_is_ridingwar;
	}
	
	/**
	 * get 头像ICO编号
	 * @return 
	 */
	public int getQ_head_ico_id(){
		return q_head_ico_id;
	}
	
	/**
	 * set 头像ICO编号
	 */
	public void setQ_head_ico_id(int q_head_ico_id){
		this.q_head_ico_id = q_head_ico_id;
	}
	
	/**
	 * get 坐骑面板展示用造型资源编号
	 * @return 
	 */
	public int getQ_panel_show_id(){
		return q_panel_show_id;
	}
	
	/**
	 * set 坐骑面板展示用造型资源编号
	 */
	public void setQ_panel_show_id(int q_panel_show_id){
		this.q_panel_show_id = q_panel_show_id;
	}
	
	/**
	 * get 场景中骑乘用造型资源编号
	 * @return 
	 */
	public int getQ_scene_show_id(){
		return q_scene_show_id;
	}
	
	/**
	 * set 场景中骑乘用造型资源编号
	 */
	public void setQ_scene_show_id(int q_scene_show_id){
		this.q_scene_show_id = q_scene_show_id;
	}
	
	/**
	 * get 场景中的蹄子光效资源
	 * @return 
	 */
	public int getQ_huf_wirkung(){
		return q_huf_wirkung;
	}
	
	/**
	 * set 场景中的蹄子光效资源
	 */
	public void setQ_huf_wirkung(int q_huf_wirkung){
		this.q_huf_wirkung = q_huf_wirkung;
	}
	
	/**
	 * get 坐骑自身最大可升级等级
	 * @return 
	 */
	public int getQ_level_max(){
		return q_level_max;
	}
	
	/**
	 * set 坐骑自身最大可升级等级
	 */
	public void setQ_level_max(int q_level_max){
		this.q_level_max = q_level_max;
	}
	
	/**
	 * get 本阶坐骑所允许的最大技能等级
	 * @return 
	 */
	public int getQ_skill_level_max(){
		return q_skill_level_max;
	}
	
	/**
	 * set 本阶坐骑所允许的最大技能等级
	 */
	public void setQ_skill_level_max(int q_skill_level_max){
		this.q_skill_level_max = q_skill_level_max;
	}
	
	/**
	 * get 成长潜力值
	 * @return 
	 */
	public int getQ_growup(){
		return q_growup;
	}
	
	/**
	 * set 成长潜力值
	 */
	public void setQ_growup(int q_growup){
		this.q_growup = q_growup;
	}
	
	/**
	 * get 进阶操作所需人物等级
	 * @return 
	 */
	public int getQ_up_figure_level(){
		return q_up_figure_level;
	}
	
	/**
	 * set 进阶操作所需人物等级
	 */
	public void setQ_up_figure_level(int q_up_figure_level){
		this.q_up_figure_level = q_up_figure_level;
	}
	
	/**
	 * get 进阶消耗铜币数量
	 * @return 
	 */
	public int getQ_up_gold_num(){
		return q_up_gold_num;
	}
	
	/**
	 * set 进阶消耗铜币数量
	 */
	public void setQ_up_gold_num(int q_up_gold_num){
		this.q_up_gold_num = q_up_gold_num;
	}
	
	/**
	 * get 进阶所需消耗材料ID
	 * @return 
	 */
	public int getQ_up_item_id(){
		return q_up_item_id;
	}
	
	/**
	 * set 进阶所需消耗材料ID
	 */
	public void setQ_up_item_id(int q_up_item_id){
		this.q_up_item_id = q_up_item_id;
	}
	
	/**
	 * get 每次消耗材料数量
	 * @return 
	 */
	public int getQ_up_item_num(){
		return q_up_item_num;
	}
	
	/**
	 * set 每次消耗材料数量
	 */
	public void setQ_up_item_num(int q_up_item_num){
		this.q_up_item_num = q_up_item_num;
	}
	
	/**
	 * get 进阶成功所需进阶次数min
	 * @return 
	 */
	public int getQ_up_num_min(){
		return q_up_num_min;
	}
	
	/**
	 * set 进阶成功所需进阶次数min
	 */
	public void setQ_up_num_min(int q_up_num_min){
		this.q_up_num_min = q_up_num_min;
	}
	
	/**
	 * get 进阶成功所需进阶次数max
	 * @return 
	 */
	public int getQ_up_num_max(){
		return q_up_num_max;
	}
	
	/**
	 * set 进阶成功所需进阶次数max
	 */
	public void setQ_up_num_max(int q_up_num_max){
		this.q_up_num_max = q_up_num_max;
	}
	
	/**
	 * get 服务器端计算用进阶成功几率（万分比）
	 * @return 
	 */
	public int getQ_up_probability(){
		return q_up_probability;
	}
	
	/**
	 * set 服务器端计算用进阶成功几率（万分比）
	 */
	public void setQ_up_probability(int q_up_probability){
		this.q_up_probability = q_up_probability;
	}
	
	/**
	 * get 客户端显示用成功率（支持HTML）
	 * @return 
	 */
	public String getQ_up_clientshow_rate(){
		return q_up_clientshow_rate;
	}
	
	/**
	 * set 客户端显示用成功率（支持HTML）
	 */
	public void setQ_up_clientshow_rate(String q_up_clientshow_rate){
		this.q_up_clientshow_rate = q_up_clientshow_rate;
	}
	
	/**
	 * get 每次失败增加祝福值区间min
	 * @return 
	 */
	public int getQ_blessnum_min(){
		return q_blessnum_min;
	}
	
	/**
	 * set 每次失败增加祝福值区间min
	 */
	public void setQ_blessnum_min(int q_blessnum_min){
		this.q_blessnum_min = q_blessnum_min;
	}
	
	/**
	 * get 每次失败增加祝福值区间max
	 * @return 
	 */
	public int getQ_blessnum_max(){
		return q_blessnum_max;
	}
	
	/**
	 * set 每次失败增加祝福值区间max
	 */
	public void setQ_blessnum_max(int q_blessnum_max){
		this.q_blessnum_max = q_blessnum_max;
	}
	
	/**
	 * get 祝福值上限值
	 * @return 
	 */
	public int getQ_blessnum_limit(){
		return q_blessnum_limit;
	}
	
	/**
	 * set 祝福值上限值
	 */
	public void setQ_blessnum_limit(int q_blessnum_limit){
		this.q_blessnum_limit = q_blessnum_limit;
	}
	
	/**
	 * get 每次失败常规几率及增加人物经验（格式：几率|经验）
	 * @return 
	 */
	public String getQ_normal_rnd(){
		return q_normal_rnd;
	}
	
	/**
	 * set 每次失败常规几率及增加人物经验（格式：几率|经验）
	 */
	public void setQ_normal_rnd(String q_normal_rnd){
		this.q_normal_rnd = q_normal_rnd;
	}
	
	/**
	 * get 每次失败小爆击几率及增加人物经验（格式：几率|经验）
	 * @return 
	 */
	public String getQ_small_crit_rnd(){
		return q_small_crit_rnd;
	}
	
	/**
	 * set 每次失败小爆击几率及增加人物经验（格式：几率|经验）
	 */
	public void setQ_small_crit_rnd(String q_small_crit_rnd){
		this.q_small_crit_rnd = q_small_crit_rnd;
	}
	
	/**
	 * get 每次失败大爆击几率及增加人物经验（格式：几率|经验）
	 * @return 
	 */
	public String getQ_large_crit_rnd(){
		return q_large_crit_rnd;
	}
	
	/**
	 * set 每次失败大爆击几率及增加人物经验（格式：几率|经验）
	 */
	public void setQ_large_crit_rnd(String q_large_crit_rnd){
		this.q_large_crit_rnd = q_large_crit_rnd;
	}
	
	/**
	 * get 进阶失败所获人物经验上限
	 * @return 
	 */
	public int getQ_up_fail_addexp(){
		return q_up_fail_addexp;
	}
	
	/**
	 * set 进阶失败所获人物经验上限
	 */
	public void setQ_up_fail_addexp(int q_up_fail_addexp){
		this.q_up_fail_addexp = q_up_fail_addexp;
	}
	
	/**
	 * get 进阶成功是否全服公告（0不公告，1公告）
	 * @return 
	 */
	public int getQ_notice(){
		return q_notice;
	}
	
	/**
	 * set 进阶成功是否全服公告（0不公告，1公告）
	 */
	public void setQ_notice(int q_notice){
		this.q_notice = q_notice;
	}
	
}