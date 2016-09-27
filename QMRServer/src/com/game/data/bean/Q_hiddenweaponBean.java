package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_hiddenweapon Bean
 */
public class Q_hiddenweaponBean {

	//暗器阶数
	private int q_rank;
	
	//暗器名称
	private String q_name;
	
	//名字颜色，支持html语法
	private String q_color;
	
	//暗器面板展示用造型资源编号
	private int q_res_panelid;
	
	//暗器技能ID（0：无技能，组合技能1的ID;组合技能2的ID;组合技能3的ID;组合技能4的ID;组合技能5的ID;）
	private int q_skillid;
	
	//成长潜力值
	private int q_potential;
	
	//进阶所需人物等级
	private int q_need_level;
	
	//进阶消费铜币数量
	private int q_need_money;
	
	//每次进阶所需消耗材料ID
	private int q_need_modelid;
	
	//每次消耗材料数量
	private int q_item_num;
	
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
	private int q_is_announce;
	
	//暗器最大等级
	private int q_max_level;
	
	//开放格子数
	private int q_open_grid;
	
	//投掷次数
	private int q_throw_times;
	
	
	/**
	 * get 暗器阶数
	 * @return 
	 */
	public int getQ_rank(){
		return q_rank;
	}
	
	/**
	 * set 暗器阶数
	 */
	public void setQ_rank(int q_rank){
		this.q_rank = q_rank;
	}
	
	/**
	 * get 暗器名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 暗器名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 名字颜色，支持html语法
	 * @return 
	 */
	public String getQ_color(){
		return q_color;
	}
	
	/**
	 * set 名字颜色，支持html语法
	 */
	public void setQ_color(String q_color){
		this.q_color = q_color;
	}
	
	/**
	 * get 暗器面板展示用造型资源编号
	 * @return 
	 */
	public int getQ_res_panelid(){
		return q_res_panelid;
	}
	
	/**
	 * set 暗器面板展示用造型资源编号
	 */
	public void setQ_res_panelid(int q_res_panelid){
		this.q_res_panelid = q_res_panelid;
	}
	
	/**
	 * get 暗器技能ID（0：无技能，组合技能1的ID;组合技能2的ID;组合技能3的ID;组合技能4的ID;组合技能5的ID;）
	 * @return 
	 */
	public int getQ_skillid(){
		return q_skillid;
	}
	
	/**
	 * set 暗器技能ID（0：无技能，组合技能1的ID;组合技能2的ID;组合技能3的ID;组合技能4的ID;组合技能5的ID;）
	 */
	public void setQ_skillid(int q_skillid){
		this.q_skillid = q_skillid;
	}
	
	/**
	 * get 成长潜力值
	 * @return 
	 */
	public int getQ_potential(){
		return q_potential;
	}
	
	/**
	 * set 成长潜力值
	 */
	public void setQ_potential(int q_potential){
		this.q_potential = q_potential;
	}
	
	/**
	 * get 进阶所需人物等级
	 * @return 
	 */
	public int getQ_need_level(){
		return q_need_level;
	}
	
	/**
	 * set 进阶所需人物等级
	 */
	public void setQ_need_level(int q_need_level){
		this.q_need_level = q_need_level;
	}
	
	/**
	 * get 进阶消费铜币数量
	 * @return 
	 */
	public int getQ_need_money(){
		return q_need_money;
	}
	
	/**
	 * set 进阶消费铜币数量
	 */
	public void setQ_need_money(int q_need_money){
		this.q_need_money = q_need_money;
	}
	
	/**
	 * get 每次进阶所需消耗材料ID
	 * @return 
	 */
	public int getQ_need_modelid(){
		return q_need_modelid;
	}
	
	/**
	 * set 每次进阶所需消耗材料ID
	 */
	public void setQ_need_modelid(int q_need_modelid){
		this.q_need_modelid = q_need_modelid;
	}
	
	/**
	 * get 每次消耗材料数量
	 * @return 
	 */
	public int getQ_item_num(){
		return q_item_num;
	}
	
	/**
	 * set 每次消耗材料数量
	 */
	public void setQ_item_num(int q_item_num){
		this.q_item_num = q_item_num;
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
	public int getQ_is_announce(){
		return q_is_announce;
	}
	
	/**
	 * set 进阶成功是否全服公告（0不公告，1公告）
	 */
	public void setQ_is_announce(int q_is_announce){
		this.q_is_announce = q_is_announce;
	}
	
	/**
	 * get 暗器最大等级
	 * @return 
	 */
	public int getQ_max_level(){
		return q_max_level;
	}
	
	/**
	 * set 暗器最大等级
	 */
	public void setQ_max_level(int q_max_level){
		this.q_max_level = q_max_level;
	}
	
	/**
	 * get 开放格子数
	 * @return 
	 */
	public int getQ_open_grid(){
		return q_open_grid;
	}
	
	/**
	 * set 开放格子数
	 */
	public void setQ_open_grid(int q_open_grid){
		this.q_open_grid = q_open_grid;
	}
	
	/**
	 * get 投掷次数
	 * @return 
	 */
	public int getQ_throw_times(){
		return q_throw_times;
	}
	
	/**
	 * set 投掷次数
	 */
	public void setQ_throw_times(int q_throw_times){
		this.q_throw_times = q_throw_times;
	}
	
}