package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_packet Bean
 */
public class Q_spirittree_packetBean {

	//筛选类型（1普通类，2银色奇异果，3金色奇异果，4特殊类）
	private int q_type;
	
	//组包ID（关联细则表字段）
	private int q_packet_id;
	
	//组包名称（显示于TIPS）
	private String q_packet_name;
	
	//组包描述（显示于TIPS）
	private String q_describe;
	
	//是否参与新果筛选（0不参与，1参与）
	private int q_is_select;
	
	//参选所需最低人物等级
	private int q_need_level;
	
	//是否允许被偷取（0不允许，1允许）
	private int q_is_steal;
	
	//组包天生光效资源编号
	private int q_lightefficiency_id;
	
	//果实组包成长过程造型资源编号（格式：状态1名称|状态1造型资源编号|状态1维持秒数;状态2名称|状态2造型资源编号|状态2维持秒数;状态3名称|状态3造型资源编号|状态3维持秒数;）(如果填0，则意味着本果实在结出时立即成熟)
	private String q_growing_up;
	
	//果实成熟后造型资源编号
	private int q_mature_id;
	
	//干旱判断间隔时间(秒)
	private int q_drought_time;
	
	//出现干旱事件几率（万分比）
	private int q_drought_rnd;
	
	//手动催熟所需元宝（只有奇异果才生效）
	private int q_urgeripening;
	
	//出现几率（普通果实用几率，互斥式几率）
	private int q_arise_rnd;
	
	
	/**
	 * get 筛选类型（1普通类，2银色奇异果，3金色奇异果，4特殊类）
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 筛选类型（1普通类，2银色奇异果，3金色奇异果，4特殊类）
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 组包ID（关联细则表字段）
	 * @return 
	 */
	public int getQ_packet_id(){
		return q_packet_id;
	}
	
	/**
	 * set 组包ID（关联细则表字段）
	 */
	public void setQ_packet_id(int q_packet_id){
		this.q_packet_id = q_packet_id;
	}
	
	/**
	 * get 组包名称（显示于TIPS）
	 * @return 
	 */
	public String getQ_packet_name(){
		return q_packet_name;
	}
	
	/**
	 * set 组包名称（显示于TIPS）
	 */
	public void setQ_packet_name(String q_packet_name){
		this.q_packet_name = q_packet_name;
	}
	
	/**
	 * get 组包描述（显示于TIPS）
	 * @return 
	 */
	public String getQ_describe(){
		return q_describe;
	}
	
	/**
	 * set 组包描述（显示于TIPS）
	 */
	public void setQ_describe(String q_describe){
		this.q_describe = q_describe;
	}
	
	/**
	 * get 是否参与新果筛选（0不参与，1参与）
	 * @return 
	 */
	public int getQ_is_select(){
		return q_is_select;
	}
	
	/**
	 * set 是否参与新果筛选（0不参与，1参与）
	 */
	public void setQ_is_select(int q_is_select){
		this.q_is_select = q_is_select;
	}
	
	/**
	 * get 参选所需最低人物等级
	 * @return 
	 */
	public int getQ_need_level(){
		return q_need_level;
	}
	
	/**
	 * set 参选所需最低人物等级
	 */
	public void setQ_need_level(int q_need_level){
		this.q_need_level = q_need_level;
	}
	
	/**
	 * get 是否允许被偷取（0不允许，1允许）
	 * @return 
	 */
	public int getQ_is_steal(){
		return q_is_steal;
	}
	
	/**
	 * set 是否允许被偷取（0不允许，1允许）
	 */
	public void setQ_is_steal(int q_is_steal){
		this.q_is_steal = q_is_steal;
	}
	
	/**
	 * get 组包天生光效资源编号
	 * @return 
	 */
	public int getQ_lightefficiency_id(){
		return q_lightefficiency_id;
	}
	
	/**
	 * set 组包天生光效资源编号
	 */
	public void setQ_lightefficiency_id(int q_lightefficiency_id){
		this.q_lightefficiency_id = q_lightefficiency_id;
	}
	
	/**
	 * get 果实组包成长过程造型资源编号（格式：状态1名称|状态1造型资源编号|状态1维持秒数;状态2名称|状态2造型资源编号|状态2维持秒数;状态3名称|状态3造型资源编号|状态3维持秒数;）(如果填0，则意味着本果实在结出时立即成熟)
	 * @return 
	 */
	public String getQ_growing_up(){
		return q_growing_up;
	}
	
	/**
	 * set 果实组包成长过程造型资源编号（格式：状态1名称|状态1造型资源编号|状态1维持秒数;状态2名称|状态2造型资源编号|状态2维持秒数;状态3名称|状态3造型资源编号|状态3维持秒数;）(如果填0，则意味着本果实在结出时立即成熟)
	 */
	public void setQ_growing_up(String q_growing_up){
		this.q_growing_up = q_growing_up;
	}
	
	/**
	 * get 果实成熟后造型资源编号
	 * @return 
	 */
	public int getQ_mature_id(){
		return q_mature_id;
	}
	
	/**
	 * set 果实成熟后造型资源编号
	 */
	public void setQ_mature_id(int q_mature_id){
		this.q_mature_id = q_mature_id;
	}
	
	/**
	 * get 干旱判断间隔时间(秒)
	 * @return 
	 */
	public int getQ_drought_time(){
		return q_drought_time;
	}
	
	/**
	 * set 干旱判断间隔时间(秒)
	 */
	public void setQ_drought_time(int q_drought_time){
		this.q_drought_time = q_drought_time;
	}
	
	/**
	 * get 出现干旱事件几率（万分比）
	 * @return 
	 */
	public int getQ_drought_rnd(){
		return q_drought_rnd;
	}
	
	/**
	 * set 出现干旱事件几率（万分比）
	 */
	public void setQ_drought_rnd(int q_drought_rnd){
		this.q_drought_rnd = q_drought_rnd;
	}
	
	/**
	 * get 手动催熟所需元宝（只有奇异果才生效）
	 * @return 
	 */
	public int getQ_urgeripening(){
		return q_urgeripening;
	}
	
	/**
	 * set 手动催熟所需元宝（只有奇异果才生效）
	 */
	public void setQ_urgeripening(int q_urgeripening){
		this.q_urgeripening = q_urgeripening;
	}
	
	/**
	 * get 出现几率（普通果实用几率，互斥式几率）
	 * @return 
	 */
	public int getQ_arise_rnd(){
		return q_arise_rnd;
	}
	
	/**
	 * set 出现几率（普通果实用几率，互斥式几率）
	 */
	public void setQ_arise_rnd(int q_arise_rnd){
		this.q_arise_rnd = q_arise_rnd;
	}
	
}