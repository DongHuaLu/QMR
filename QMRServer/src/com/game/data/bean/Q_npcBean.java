package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_npc Bean
 */
public class Q_npcBean {

	//NPC编号
	private int q_id;
	
	//刷新地图ID
	private int q_map;
	
	//刷新X坐标
	private int q_x;
	
	//刷新Y坐标
	private int q_y;
	
	//NPC名字
	private String q_name;
	
	//NPC称号
	private String q_title;
	
	//NPC上绑定的功能列表（格式：功能类型|选项文字描述;功能类型|选项文字描述;）
	private String q_function;
	
	//NPC功能描述（小地图tips描述，支持html）
	private String q_npcdesc;
	
	//0在小地图和列表显示，1只在小地图显示，2只在列表显示，3都不显示
	private int q_isminimap;
	
	//NPC绑定的商店出售模板编号
	private int q_shop;
	
	//NPC传送序列（文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格;文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格）
	private String q_transfer;
	
	//NPC身上绑定的任务编号列表
	private String q_task;
	
	//NPC功能面板上的简单对白（需支持基本的Html语法）
	private String q_dialog;
	
	//NPC在场景中的发言内容(多句以分号分隔)
	private String q_speak;
	
	//发言频率间隔(单位：毫秒)
	private int q_speak_time;
	
	//资源类型（0默认，1怪物，2美人）
	private int q_resources;
	
	//造型是否翻转（0否，1是）
	private int q_isturn;
	
	//默认朝向（0开始，顺时针转到7）
	private int q_direction;
	
	//造型资源编号（资源使用：单方向6帧）
	private String q_resource;
	
	//头像资源编号
	private String q_head;
	
	//任务接取播放（任务ID+动作名）
	private String q_access_play;
	
	//任务完成播放（任务号+动作名）
	private String q_complete_play;
	
	//是否可见（0，1）
	private int q_isvisible;
	
	//NPC默认行为
	private int q_behavior;
	
	//是否跟随玩家
	private int q_follow;
	
	//点击播放动作
	private int q_clickplay;
	
	//玩家状态
	private int q_playerstate;
	
	//点击触发脚本编号
	private int q_clickscriptid;
	
	//进入视野播放动作
	private int q_viewplay;
	
	//npc视野范围
	private int q_field;
	
	//采集后触发脚本编号
	private int q_collectscript;
	
	//采集时间（毫秒）
	private int q_collecttime;
	
	//是否显示NPC
	private String q_displaynpc;
	
	//采集获得物品
	private int q_acquisition_item;
	
	//npc类型（1：代表采集类NPC，没有影子，会出现呼吸提升光效）
	private int q_type;
	
	//像素X坐标
	private int q_px;
	
	//像素Y坐标
	private int q_py;
	
	//采集完成任务
	private int q_acquisition_task;
	
	//npc交互距离
	private int q_Interactive;
	
	
	/**
	 * get NPC编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set NPC编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 刷新地图ID
	 * @return 
	 */
	public int getQ_map(){
		return q_map;
	}
	
	/**
	 * set 刷新地图ID
	 */
	public void setQ_map(int q_map){
		this.q_map = q_map;
	}
	
	/**
	 * get 刷新X坐标
	 * @return 
	 */
	public int getQ_x(){
		return q_x;
	}
	
	/**
	 * set 刷新X坐标
	 */
	public void setQ_x(int q_x){
		this.q_x = q_x;
	}
	
	/**
	 * get 刷新Y坐标
	 * @return 
	 */
	public int getQ_y(){
		return q_y;
	}
	
	/**
	 * set 刷新Y坐标
	 */
	public void setQ_y(int q_y){
		this.q_y = q_y;
	}
	
	/**
	 * get NPC名字
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set NPC名字
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get NPC称号
	 * @return 
	 */
	public String getQ_title(){
		return q_title;
	}
	
	/**
	 * set NPC称号
	 */
	public void setQ_title(String q_title){
		this.q_title = q_title;
	}
	
	/**
	 * get NPC上绑定的功能列表（格式：功能类型|选项文字描述;功能类型|选项文字描述;）
	 * @return 
	 */
	public String getQ_function(){
		return q_function;
	}
	
	/**
	 * set NPC上绑定的功能列表（格式：功能类型|选项文字描述;功能类型|选项文字描述;）
	 */
	public void setQ_function(String q_function){
		this.q_function = q_function;
	}
	
	/**
	 * get NPC功能描述（小地图tips描述，支持html）
	 * @return 
	 */
	public String getQ_npcdesc(){
		return q_npcdesc;
	}
	
	/**
	 * set NPC功能描述（小地图tips描述，支持html）
	 */
	public void setQ_npcdesc(String q_npcdesc){
		this.q_npcdesc = q_npcdesc;
	}
	
	/**
	 * get 0在小地图和列表显示，1只在小地图显示，2只在列表显示，3都不显示
	 * @return 
	 */
	public int getQ_isminimap(){
		return q_isminimap;
	}
	
	/**
	 * set 0在小地图和列表显示，1只在小地图显示，2只在列表显示，3都不显示
	 */
	public void setQ_isminimap(int q_isminimap){
		this.q_isminimap = q_isminimap;
	}
	
	/**
	 * get NPC绑定的商店出售模板编号
	 * @return 
	 */
	public int getQ_shop(){
		return q_shop;
	}
	
	/**
	 * set NPC绑定的商店出售模板编号
	 */
	public void setQ_shop(int q_shop){
		this.q_shop = q_shop;
	}
	
	/**
	 * get NPC传送序列（文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格;文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格）
	 * @return 
	 */
	public String getQ_transfer(){
		return q_transfer;
	}
	
	/**
	 * set NPC传送序列（文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格;文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格）
	 */
	public void setQ_transfer(String q_transfer){
		this.q_transfer = q_transfer;
	}
	
	/**
	 * get NPC身上绑定的任务编号列表
	 * @return 
	 */
	public String getQ_task(){
		return q_task;
	}
	
	/**
	 * set NPC身上绑定的任务编号列表
	 */
	public void setQ_task(String q_task){
		this.q_task = q_task;
	}
	
	/**
	 * get NPC功能面板上的简单对白（需支持基本的Html语法）
	 * @return 
	 */
	public String getQ_dialog(){
		return q_dialog;
	}
	
	/**
	 * set NPC功能面板上的简单对白（需支持基本的Html语法）
	 */
	public void setQ_dialog(String q_dialog){
		this.q_dialog = q_dialog;
	}
	
	/**
	 * get NPC在场景中的发言内容(多句以分号分隔)
	 * @return 
	 */
	public String getQ_speak(){
		return q_speak;
	}
	
	/**
	 * set NPC在场景中的发言内容(多句以分号分隔)
	 */
	public void setQ_speak(String q_speak){
		this.q_speak = q_speak;
	}
	
	/**
	 * get 发言频率间隔(单位：毫秒)
	 * @return 
	 */
	public int getQ_speak_time(){
		return q_speak_time;
	}
	
	/**
	 * set 发言频率间隔(单位：毫秒)
	 */
	public void setQ_speak_time(int q_speak_time){
		this.q_speak_time = q_speak_time;
	}
	
	/**
	 * get 资源类型（0默认，1怪物，2美人）
	 * @return 
	 */
	public int getQ_resources(){
		return q_resources;
	}
	
	/**
	 * set 资源类型（0默认，1怪物，2美人）
	 */
	public void setQ_resources(int q_resources){
		this.q_resources = q_resources;
	}
	
	/**
	 * get 造型是否翻转（0否，1是）
	 * @return 
	 */
	public int getQ_isturn(){
		return q_isturn;
	}
	
	/**
	 * set 造型是否翻转（0否，1是）
	 */
	public void setQ_isturn(int q_isturn){
		this.q_isturn = q_isturn;
	}
	
	/**
	 * get 默认朝向（0开始，顺时针转到7）
	 * @return 
	 */
	public int getQ_direction(){
		return q_direction;
	}
	
	/**
	 * set 默认朝向（0开始，顺时针转到7）
	 */
	public void setQ_direction(int q_direction){
		this.q_direction = q_direction;
	}
	
	/**
	 * get 造型资源编号（资源使用：单方向6帧）
	 * @return 
	 */
	public String getQ_resource(){
		return q_resource;
	}
	
	/**
	 * set 造型资源编号（资源使用：单方向6帧）
	 */
	public void setQ_resource(String q_resource){
		this.q_resource = q_resource;
	}
	
	/**
	 * get 头像资源编号
	 * @return 
	 */
	public String getQ_head(){
		return q_head;
	}
	
	/**
	 * set 头像资源编号
	 */
	public void setQ_head(String q_head){
		this.q_head = q_head;
	}
	
	/**
	 * get 任务接取播放（任务ID+动作名）
	 * @return 
	 */
	public String getQ_access_play(){
		return q_access_play;
	}
	
	/**
	 * set 任务接取播放（任务ID+动作名）
	 */
	public void setQ_access_play(String q_access_play){
		this.q_access_play = q_access_play;
	}
	
	/**
	 * get 任务完成播放（任务号+动作名）
	 * @return 
	 */
	public String getQ_complete_play(){
		return q_complete_play;
	}
	
	/**
	 * set 任务完成播放（任务号+动作名）
	 */
	public void setQ_complete_play(String q_complete_play){
		this.q_complete_play = q_complete_play;
	}
	
	/**
	 * get 是否可见（0，1）
	 * @return 
	 */
	public int getQ_isvisible(){
		return q_isvisible;
	}
	
	/**
	 * set 是否可见（0，1）
	 */
	public void setQ_isvisible(int q_isvisible){
		this.q_isvisible = q_isvisible;
	}
	
	/**
	 * get NPC默认行为
	 * @return 
	 */
	public int getQ_behavior(){
		return q_behavior;
	}
	
	/**
	 * set NPC默认行为
	 */
	public void setQ_behavior(int q_behavior){
		this.q_behavior = q_behavior;
	}
	
	/**
	 * get 是否跟随玩家
	 * @return 
	 */
	public int getQ_follow(){
		return q_follow;
	}
	
	/**
	 * set 是否跟随玩家
	 */
	public void setQ_follow(int q_follow){
		this.q_follow = q_follow;
	}
	
	/**
	 * get 点击播放动作
	 * @return 
	 */
	public int getQ_clickplay(){
		return q_clickplay;
	}
	
	/**
	 * set 点击播放动作
	 */
	public void setQ_clickplay(int q_clickplay){
		this.q_clickplay = q_clickplay;
	}
	
	/**
	 * get 玩家状态
	 * @return 
	 */
	public int getQ_playerstate(){
		return q_playerstate;
	}
	
	/**
	 * set 玩家状态
	 */
	public void setQ_playerstate(int q_playerstate){
		this.q_playerstate = q_playerstate;
	}
	
	/**
	 * get 点击触发脚本编号
	 * @return 
	 */
	public int getQ_clickscriptid(){
		return q_clickscriptid;
	}
	
	/**
	 * set 点击触发脚本编号
	 */
	public void setQ_clickscriptid(int q_clickscriptid){
		this.q_clickscriptid = q_clickscriptid;
	}
	
	/**
	 * get 进入视野播放动作
	 * @return 
	 */
	public int getQ_viewplay(){
		return q_viewplay;
	}
	
	/**
	 * set 进入视野播放动作
	 */
	public void setQ_viewplay(int q_viewplay){
		this.q_viewplay = q_viewplay;
	}
	
	/**
	 * get npc视野范围
	 * @return 
	 */
	public int getQ_field(){
		return q_field;
	}
	
	/**
	 * set npc视野范围
	 */
	public void setQ_field(int q_field){
		this.q_field = q_field;
	}
	
	/**
	 * get 采集后触发脚本编号
	 * @return 
	 */
	public int getQ_collectscript(){
		return q_collectscript;
	}
	
	/**
	 * set 采集后触发脚本编号
	 */
	public void setQ_collectscript(int q_collectscript){
		this.q_collectscript = q_collectscript;
	}
	
	/**
	 * get 采集时间（毫秒）
	 * @return 
	 */
	public int getQ_collecttime(){
		return q_collecttime;
	}
	
	/**
	 * set 采集时间（毫秒）
	 */
	public void setQ_collecttime(int q_collecttime){
		this.q_collecttime = q_collecttime;
	}
	
	/**
	 * get 是否显示NPC
	 * @return 
	 */
	public String getQ_displaynpc(){
		return q_displaynpc;
	}
	
	/**
	 * set 是否显示NPC
	 */
	public void setQ_displaynpc(String q_displaynpc){
		this.q_displaynpc = q_displaynpc;
	}
	
	/**
	 * get 采集获得物品
	 * @return 
	 */
	public int getQ_acquisition_item(){
		return q_acquisition_item;
	}
	
	/**
	 * set 采集获得物品
	 */
	public void setQ_acquisition_item(int q_acquisition_item){
		this.q_acquisition_item = q_acquisition_item;
	}
	
	/**
	 * get npc类型（1：代表采集类NPC，没有影子，会出现呼吸提升光效）
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set npc类型（1：代表采集类NPC，没有影子，会出现呼吸提升光效）
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 像素X坐标
	 * @return 
	 */
	public int getQ_px(){
		return q_px;
	}
	
	/**
	 * set 像素X坐标
	 */
	public void setQ_px(int q_px){
		this.q_px = q_px;
	}
	
	/**
	 * get 像素Y坐标
	 * @return 
	 */
	public int getQ_py(){
		return q_py;
	}
	
	/**
	 * set 像素Y坐标
	 */
	public void setQ_py(int q_py){
		this.q_py = q_py;
	}
	
	/**
	 * get 采集完成任务
	 * @return 
	 */
	public int getQ_acquisition_task(){
		return q_acquisition_task;
	}
	
	/**
	 * set 采集完成任务
	 */
	public void setQ_acquisition_task(int q_acquisition_task){
		this.q_acquisition_task = q_acquisition_task;
	}
	
	/**
	 * get npc交互距离
	 * @return 
	 */
	public int getQ_Interactive(){
		return q_Interactive;
	}
	
	/**
	 * set npc交互距离
	 */
	public void setQ_Interactive(int q_Interactive){
		this.q_Interactive = q_Interactive;
	}
	
}