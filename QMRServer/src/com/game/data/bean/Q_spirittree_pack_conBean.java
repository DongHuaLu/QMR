package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_pack_con Bean
 */
public class Q_spirittree_pack_conBean {

	//ID编号(ID不能重编，只能添加)
	private int q_id;
	
	//组包ID
	private int q_packet_id;
	
	//物品ID
	private int q_item_id;
	
	//物品件数
	private int q_item_num;
	
	//可被偷取百分比（0不可偷取，>0表示可以被偷取，例如：30表示最多可以被偷走30%）
	private int q_Theft_Percentage;
	
	//被偷后为主人补偿经验值
	private int q_compensate_exp;
	
	//采摘后是否立即使用（0不立即使用，1立即使用）
	private int q_is_use;
	
	//使用时在面板上播放光效资源（资源编号|拼接个数|持续秒数）
	private String q_use_effect;
	
	//参数所需人物等级
	private int q_need_level;
	
	//排除所需人物等级
	private int q_exclude_level;
	
	//中选几率（互斥几率）
	private int q_selected_rnd;
	
	//生成强化等级
	private int q_streng_level;
	
	//生成附加属性条数
	private int q_addProperty_num;
	
	//生成附加属性比例区间min（百分比分子）
	private int q_addProperty_min;
	
	//生成附加属性比例区间max（百分比分子）
	private int q_addProperty_max;
	
	//变更绑定类型为（0不绑定；1获得时绑定；2使用后绑定）
	private int q_is_binding;
	
	//变更存在时间为（秒）
	private int q_existtime;
	
	//采摘后发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
	private int q_notice_type;
	
	
	/**
	 * get ID编号(ID不能重编，只能添加)
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set ID编号(ID不能重编，只能添加)
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 组包ID
	 * @return 
	 */
	public int getQ_packet_id(){
		return q_packet_id;
	}
	
	/**
	 * set 组包ID
	 */
	public void setQ_packet_id(int q_packet_id){
		this.q_packet_id = q_packet_id;
	}
	
	/**
	 * get 物品ID
	 * @return 
	 */
	public int getQ_item_id(){
		return q_item_id;
	}
	
	/**
	 * set 物品ID
	 */
	public void setQ_item_id(int q_item_id){
		this.q_item_id = q_item_id;
	}
	
	/**
	 * get 物品件数
	 * @return 
	 */
	public int getQ_item_num(){
		return q_item_num;
	}
	
	/**
	 * set 物品件数
	 */
	public void setQ_item_num(int q_item_num){
		this.q_item_num = q_item_num;
	}
	
	/**
	 * get 可被偷取百分比（0不可偷取，>0表示可以被偷取，例如：30表示最多可以被偷走30%）
	 * @return 
	 */
	public int getQ_Theft_Percentage(){
		return q_Theft_Percentage;
	}
	
	/**
	 * set 可被偷取百分比（0不可偷取，>0表示可以被偷取，例如：30表示最多可以被偷走30%）
	 */
	public void setQ_Theft_Percentage(int q_Theft_Percentage){
		this.q_Theft_Percentage = q_Theft_Percentage;
	}
	
	/**
	 * get 被偷后为主人补偿经验值
	 * @return 
	 */
	public int getQ_compensate_exp(){
		return q_compensate_exp;
	}
	
	/**
	 * set 被偷后为主人补偿经验值
	 */
	public void setQ_compensate_exp(int q_compensate_exp){
		this.q_compensate_exp = q_compensate_exp;
	}
	
	/**
	 * get 采摘后是否立即使用（0不立即使用，1立即使用）
	 * @return 
	 */
	public int getQ_is_use(){
		return q_is_use;
	}
	
	/**
	 * set 采摘后是否立即使用（0不立即使用，1立即使用）
	 */
	public void setQ_is_use(int q_is_use){
		this.q_is_use = q_is_use;
	}
	
	/**
	 * get 使用时在面板上播放光效资源（资源编号|拼接个数|持续秒数）
	 * @return 
	 */
	public String getQ_use_effect(){
		return q_use_effect;
	}
	
	/**
	 * set 使用时在面板上播放光效资源（资源编号|拼接个数|持续秒数）
	 */
	public void setQ_use_effect(String q_use_effect){
		this.q_use_effect = q_use_effect;
	}
	
	/**
	 * get 参数所需人物等级
	 * @return 
	 */
	public int getQ_need_level(){
		return q_need_level;
	}
	
	/**
	 * set 参数所需人物等级
	 */
	public void setQ_need_level(int q_need_level){
		this.q_need_level = q_need_level;
	}
	
	/**
	 * get 排除所需人物等级
	 * @return 
	 */
	public int getQ_exclude_level(){
		return q_exclude_level;
	}
	
	/**
	 * set 排除所需人物等级
	 */
	public void setQ_exclude_level(int q_exclude_level){
		this.q_exclude_level = q_exclude_level;
	}
	
	/**
	 * get 中选几率（互斥几率）
	 * @return 
	 */
	public int getQ_selected_rnd(){
		return q_selected_rnd;
	}
	
	/**
	 * set 中选几率（互斥几率）
	 */
	public void setQ_selected_rnd(int q_selected_rnd){
		this.q_selected_rnd = q_selected_rnd;
	}
	
	/**
	 * get 生成强化等级
	 * @return 
	 */
	public int getQ_streng_level(){
		return q_streng_level;
	}
	
	/**
	 * set 生成强化等级
	 */
	public void setQ_streng_level(int q_streng_level){
		this.q_streng_level = q_streng_level;
	}
	
	/**
	 * get 生成附加属性条数
	 * @return 
	 */
	public int getQ_addProperty_num(){
		return q_addProperty_num;
	}
	
	/**
	 * set 生成附加属性条数
	 */
	public void setQ_addProperty_num(int q_addProperty_num){
		this.q_addProperty_num = q_addProperty_num;
	}
	
	/**
	 * get 生成附加属性比例区间min（百分比分子）
	 * @return 
	 */
	public int getQ_addProperty_min(){
		return q_addProperty_min;
	}
	
	/**
	 * set 生成附加属性比例区间min（百分比分子）
	 */
	public void setQ_addProperty_min(int q_addProperty_min){
		this.q_addProperty_min = q_addProperty_min;
	}
	
	/**
	 * get 生成附加属性比例区间max（百分比分子）
	 * @return 
	 */
	public int getQ_addProperty_max(){
		return q_addProperty_max;
	}
	
	/**
	 * set 生成附加属性比例区间max（百分比分子）
	 */
	public void setQ_addProperty_max(int q_addProperty_max){
		this.q_addProperty_max = q_addProperty_max;
	}
	
	/**
	 * get 变更绑定类型为（0不绑定；1获得时绑定；2使用后绑定）
	 * @return 
	 */
	public int getQ_is_binding(){
		return q_is_binding;
	}
	
	/**
	 * set 变更绑定类型为（0不绑定；1获得时绑定；2使用后绑定）
	 */
	public void setQ_is_binding(int q_is_binding){
		this.q_is_binding = q_is_binding;
	}
	
	/**
	 * get 变更存在时间为（秒）
	 * @return 
	 */
	public int getQ_existtime(){
		return q_existtime;
	}
	
	/**
	 * set 变更存在时间为（秒）
	 */
	public void setQ_existtime(int q_existtime){
		this.q_existtime = q_existtime;
	}
	
	/**
	 * get 采摘后发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
	 * @return 
	 */
	public int getQ_notice_type(){
		return q_notice_type;
	}
	
	/**
	 * set 采摘后发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
	 */
	public void setQ_notice_type(int q_notice_type){
		this.q_notice_type = q_notice_type;
	}
	
}