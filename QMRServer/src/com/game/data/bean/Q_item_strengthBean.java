package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_item_strength Bean
 */
public class Q_item_strengthBean {

	//装备ID+强化等级
	private String q_id;
	
	//装备ID
	private int q_item_id;
	
	//强化等级
	private int q_level;
	
	//是否允许强化（0不允许，1允许）
	private int q_is_streng;
	
	//强化消耗材料序列（格式：材料ID_数量;材料ID_数量;）
	private String q_streng_item;
	
	//强化消耗铜钱
	private int q_streng_money;
	
	//强化耗时（单位：秒）
	private int q_streng_time;
	
	//立即完成功能消耗元宝数量
	private int q_fast_streng_yuanbao;
	
	//客户端显示用强化成功几率（需支持html）
	private String q_streng_show_pby;
	
	//强化成功所需强化次数min
	private int q_streng_min;
	
	//服务器端计算用成功几率（万分比，本处填分子）
	private int q_streng_pby;
	
	//强化成功所需强化次数max
	private int q_streng_max;
	
	//强化失败给予物品序列（格式：几率万分比分子|物品ID_数量;几率万分比分子|物品ID_数量）
	private String q_streng_fail_item;
	
	//强化失败降星颗数
	private int q_streng_fail_reduce;
	
	//强化成功是否全服公告（0不公告，1公告）
	private int q_streng_notice;
	
	//攻击附加值
	private int q_attack;
	
	//防御附加值
	private int q_defence;
	
	//爆击附加值
	private int q_crit;
	
	//闪避附加值
	private int q_dodge;
	
	//生命上限附加值
	private int q_maxhp;
	
	//内力上限附加值
	private int q_maxmp;
	
	//体力上限附加值
	private int q_maxsp;
	
	//攻击速度附加值
	private int q_attackspeed;
	
	//移动速度附加值
	private int q_speed;
	
	//幸运附加值
	private int q_luck;
	
	//是否允许进阶（0不允许，1允许）
	private int q_is_up_stage;
	
	//进阶消耗材料序列（格式：材料ID_数量;材料ID_数量;）
	private String q_up_stage_item;
	
	//进阶消耗铜钱
	private int q_up_stage_money;
	
	//客户端显示用进阶成功几率（需支持html）
	private String q_stage_show_pby;
	
	//进阶成功所需操作次数min
	private int q_up_stage_min;
	
	//服务器端计算用进阶成功几率
	private int q_stage_probability;
	
	//进阶成功所需操作次数max
	private int q_up_stage_max;
	
	//进阶至装备ID
	private int q_stage_newitem;
	
	//进阶后装备强化等级
	private int q_newitem_level;
	
	//进阶成功是否全服公告（0不公告，1公告）
	private int q_stage_notice;
	
	
	/**
	 * get 装备ID+强化等级
	 * @return 
	 */
	public String getQ_id(){
		return q_id;
	}
	
	/**
	 * set 装备ID+强化等级
	 */
	public void setQ_id(String q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 装备ID
	 * @return 
	 */
	public int getQ_item_id(){
		return q_item_id;
	}
	
	/**
	 * set 装备ID
	 */
	public void setQ_item_id(int q_item_id){
		this.q_item_id = q_item_id;
	}
	
	/**
	 * get 强化等级
	 * @return 
	 */
	public int getQ_level(){
		return q_level;
	}
	
	/**
	 * set 强化等级
	 */
	public void setQ_level(int q_level){
		this.q_level = q_level;
	}
	
	/**
	 * get 是否允许强化（0不允许，1允许）
	 * @return 
	 */
	public int getQ_is_streng(){
		return q_is_streng;
	}
	
	/**
	 * set 是否允许强化（0不允许，1允许）
	 */
	public void setQ_is_streng(int q_is_streng){
		this.q_is_streng = q_is_streng;
	}
	
	/**
	 * get 强化消耗材料序列（格式：材料ID_数量;材料ID_数量;）
	 * @return 
	 */
	public String getQ_streng_item(){
		return q_streng_item;
	}
	
	/**
	 * set 强化消耗材料序列（格式：材料ID_数量;材料ID_数量;）
	 */
	public void setQ_streng_item(String q_streng_item){
		this.q_streng_item = q_streng_item;
	}
	
	/**
	 * get 强化消耗铜钱
	 * @return 
	 */
	public int getQ_streng_money(){
		return q_streng_money;
	}
	
	/**
	 * set 强化消耗铜钱
	 */
	public void setQ_streng_money(int q_streng_money){
		this.q_streng_money = q_streng_money;
	}
	
	/**
	 * get 强化耗时（单位：秒）
	 * @return 
	 */
	public int getQ_streng_time(){
		return q_streng_time;
	}
	
	/**
	 * set 强化耗时（单位：秒）
	 */
	public void setQ_streng_time(int q_streng_time){
		this.q_streng_time = q_streng_time;
	}
	
	/**
	 * get 立即完成功能消耗元宝数量
	 * @return 
	 */
	public int getQ_fast_streng_yuanbao(){
		return q_fast_streng_yuanbao;
	}
	
	/**
	 * set 立即完成功能消耗元宝数量
	 */
	public void setQ_fast_streng_yuanbao(int q_fast_streng_yuanbao){
		this.q_fast_streng_yuanbao = q_fast_streng_yuanbao;
	}
	
	/**
	 * get 客户端显示用强化成功几率（需支持html）
	 * @return 
	 */
	public String getQ_streng_show_pby(){
		return q_streng_show_pby;
	}
	
	/**
	 * set 客户端显示用强化成功几率（需支持html）
	 */
	public void setQ_streng_show_pby(String q_streng_show_pby){
		this.q_streng_show_pby = q_streng_show_pby;
	}
	
	/**
	 * get 强化成功所需强化次数min
	 * @return 
	 */
	public int getQ_streng_min(){
		return q_streng_min;
	}
	
	/**
	 * set 强化成功所需强化次数min
	 */
	public void setQ_streng_min(int q_streng_min){
		this.q_streng_min = q_streng_min;
	}
	
	/**
	 * get 服务器端计算用成功几率（万分比，本处填分子）
	 * @return 
	 */
	public int getQ_streng_pby(){
		return q_streng_pby;
	}
	
	/**
	 * set 服务器端计算用成功几率（万分比，本处填分子）
	 */
	public void setQ_streng_pby(int q_streng_pby){
		this.q_streng_pby = q_streng_pby;
	}
	
	/**
	 * get 强化成功所需强化次数max
	 * @return 
	 */
	public int getQ_streng_max(){
		return q_streng_max;
	}
	
	/**
	 * set 强化成功所需强化次数max
	 */
	public void setQ_streng_max(int q_streng_max){
		this.q_streng_max = q_streng_max;
	}
	
	/**
	 * get 强化失败给予物品序列（格式：几率万分比分子|物品ID_数量;几率万分比分子|物品ID_数量）
	 * @return 
	 */
	public String getQ_streng_fail_item(){
		return q_streng_fail_item;
	}
	
	/**
	 * set 强化失败给予物品序列（格式：几率万分比分子|物品ID_数量;几率万分比分子|物品ID_数量）
	 */
	public void setQ_streng_fail_item(String q_streng_fail_item){
		this.q_streng_fail_item = q_streng_fail_item;
	}
	
	/**
	 * get 强化失败降星颗数
	 * @return 
	 */
	public int getQ_streng_fail_reduce(){
		return q_streng_fail_reduce;
	}
	
	/**
	 * set 强化失败降星颗数
	 */
	public void setQ_streng_fail_reduce(int q_streng_fail_reduce){
		this.q_streng_fail_reduce = q_streng_fail_reduce;
	}
	
	/**
	 * get 强化成功是否全服公告（0不公告，1公告）
	 * @return 
	 */
	public int getQ_streng_notice(){
		return q_streng_notice;
	}
	
	/**
	 * set 强化成功是否全服公告（0不公告，1公告）
	 */
	public void setQ_streng_notice(int q_streng_notice){
		this.q_streng_notice = q_streng_notice;
	}
	
	/**
	 * get 攻击附加值
	 * @return 
	 */
	public int getQ_attack(){
		return q_attack;
	}
	
	/**
	 * set 攻击附加值
	 */
	public void setQ_attack(int q_attack){
		this.q_attack = q_attack;
	}
	
	/**
	 * get 防御附加值
	 * @return 
	 */
	public int getQ_defence(){
		return q_defence;
	}
	
	/**
	 * set 防御附加值
	 */
	public void setQ_defence(int q_defence){
		this.q_defence = q_defence;
	}
	
	/**
	 * get 爆击附加值
	 * @return 
	 */
	public int getQ_crit(){
		return q_crit;
	}
	
	/**
	 * set 爆击附加值
	 */
	public void setQ_crit(int q_crit){
		this.q_crit = q_crit;
	}
	
	/**
	 * get 闪避附加值
	 * @return 
	 */
	public int getQ_dodge(){
		return q_dodge;
	}
	
	/**
	 * set 闪避附加值
	 */
	public void setQ_dodge(int q_dodge){
		this.q_dodge = q_dodge;
	}
	
	/**
	 * get 生命上限附加值
	 * @return 
	 */
	public int getQ_maxhp(){
		return q_maxhp;
	}
	
	/**
	 * set 生命上限附加值
	 */
	public void setQ_maxhp(int q_maxhp){
		this.q_maxhp = q_maxhp;
	}
	
	/**
	 * get 内力上限附加值
	 * @return 
	 */
	public int getQ_maxmp(){
		return q_maxmp;
	}
	
	/**
	 * set 内力上限附加值
	 */
	public void setQ_maxmp(int q_maxmp){
		this.q_maxmp = q_maxmp;
	}
	
	/**
	 * get 体力上限附加值
	 * @return 
	 */
	public int getQ_maxsp(){
		return q_maxsp;
	}
	
	/**
	 * set 体力上限附加值
	 */
	public void setQ_maxsp(int q_maxsp){
		this.q_maxsp = q_maxsp;
	}
	
	/**
	 * get 攻击速度附加值
	 * @return 
	 */
	public int getQ_attackspeed(){
		return q_attackspeed;
	}
	
	/**
	 * set 攻击速度附加值
	 */
	public void setQ_attackspeed(int q_attackspeed){
		this.q_attackspeed = q_attackspeed;
	}
	
	/**
	 * get 移动速度附加值
	 * @return 
	 */
	public int getQ_speed(){
		return q_speed;
	}
	
	/**
	 * set 移动速度附加值
	 */
	public void setQ_speed(int q_speed){
		this.q_speed = q_speed;
	}
	
	/**
	 * get 幸运附加值
	 * @return 
	 */
	public int getQ_luck(){
		return q_luck;
	}
	
	/**
	 * set 幸运附加值
	 */
	public void setQ_luck(int q_luck){
		this.q_luck = q_luck;
	}
	
	/**
	 * get 是否允许进阶（0不允许，1允许）
	 * @return 
	 */
	public int getQ_is_up_stage(){
		return q_is_up_stage;
	}
	
	/**
	 * set 是否允许进阶（0不允许，1允许）
	 */
	public void setQ_is_up_stage(int q_is_up_stage){
		this.q_is_up_stage = q_is_up_stage;
	}
	
	/**
	 * get 进阶消耗材料序列（格式：材料ID_数量;材料ID_数量;）
	 * @return 
	 */
	public String getQ_up_stage_item(){
		return q_up_stage_item;
	}
	
	/**
	 * set 进阶消耗材料序列（格式：材料ID_数量;材料ID_数量;）
	 */
	public void setQ_up_stage_item(String q_up_stage_item){
		this.q_up_stage_item = q_up_stage_item;
	}
	
	/**
	 * get 进阶消耗铜钱
	 * @return 
	 */
	public int getQ_up_stage_money(){
		return q_up_stage_money;
	}
	
	/**
	 * set 进阶消耗铜钱
	 */
	public void setQ_up_stage_money(int q_up_stage_money){
		this.q_up_stage_money = q_up_stage_money;
	}
	
	/**
	 * get 客户端显示用进阶成功几率（需支持html）
	 * @return 
	 */
	public String getQ_stage_show_pby(){
		return q_stage_show_pby;
	}
	
	/**
	 * set 客户端显示用进阶成功几率（需支持html）
	 */
	public void setQ_stage_show_pby(String q_stage_show_pby){
		this.q_stage_show_pby = q_stage_show_pby;
	}
	
	/**
	 * get 进阶成功所需操作次数min
	 * @return 
	 */
	public int getQ_up_stage_min(){
		return q_up_stage_min;
	}
	
	/**
	 * set 进阶成功所需操作次数min
	 */
	public void setQ_up_stage_min(int q_up_stage_min){
		this.q_up_stage_min = q_up_stage_min;
	}
	
	/**
	 * get 服务器端计算用进阶成功几率
	 * @return 
	 */
	public int getQ_stage_probability(){
		return q_stage_probability;
	}
	
	/**
	 * set 服务器端计算用进阶成功几率
	 */
	public void setQ_stage_probability(int q_stage_probability){
		this.q_stage_probability = q_stage_probability;
	}
	
	/**
	 * get 进阶成功所需操作次数max
	 * @return 
	 */
	public int getQ_up_stage_max(){
		return q_up_stage_max;
	}
	
	/**
	 * set 进阶成功所需操作次数max
	 */
	public void setQ_up_stage_max(int q_up_stage_max){
		this.q_up_stage_max = q_up_stage_max;
	}
	
	/**
	 * get 进阶至装备ID
	 * @return 
	 */
	public int getQ_stage_newitem(){
		return q_stage_newitem;
	}
	
	/**
	 * set 进阶至装备ID
	 */
	public void setQ_stage_newitem(int q_stage_newitem){
		this.q_stage_newitem = q_stage_newitem;
	}
	
	/**
	 * get 进阶后装备强化等级
	 * @return 
	 */
	public int getQ_newitem_level(){
		return q_newitem_level;
	}
	
	/**
	 * set 进阶后装备强化等级
	 */
	public void setQ_newitem_level(int q_newitem_level){
		this.q_newitem_level = q_newitem_level;
	}
	
	/**
	 * get 进阶成功是否全服公告（0不公告，1公告）
	 * @return 
	 */
	public int getQ_stage_notice(){
		return q_stage_notice;
	}
	
	/**
	 * set 进阶成功是否全服公告（0不公告，1公告）
	 */
	public void setQ_stage_notice(int q_stage_notice){
		this.q_stage_notice = q_stage_notice;
	}
	
}