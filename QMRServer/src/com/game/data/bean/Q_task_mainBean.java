package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_main Bean
 */
public class Q_task_mainBean {

	//主线剧情大章节编号
	private int q_chapter;
	
	//主线剧情大章节名称
	private String q_chapter_name;
	
	//章节中的子任务编号
	private int q_taskid;
	
	//子任务名称
	private String q_name;
	
	//任务交付类型（1自动交付类任务，2在NPC处交付类任务）
	private int q_finsh_type;
	
	//任务类型(1、对话 2、杀怪 3、杀怪掉落物品 4、采集 5、NPC间道具传递 6、读条播放特效 )
	private int q_task_type;
	
	//该任务在任务面板中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
	private String q_task_desc;
	
	//任务接取时发送的主界面对白
	private String q_start_chat;
	
	//该任务在主界面任务追踪栏中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
	private String q_task_desc2;
	
	//完成任务NPC编号
	private int q_endnpc;
	
	//任务完成条件检测物品（物品ID_需求数量_{寻径链接};物品ID_需求数量_{寻径链接};）
	private String q_end_need_goods;
	
	//任务完成条件检测杀死怪物序列（怪物ID_需求数量_{寻径链接};怪物ID_需求数量_{寻径链接};）
	private String q_end_need_killmonster;
	
	//任务完成条件检测成就序列（成就编号_{寻径链接};成就编号_{寻径链接}）
	private String q_end_need_achieve;
	
	//完成时收取物品序列(物品ID_数量;物品ID_数量）
	private String q_end_resume_goods;
	
	//完成后自动接取任务号
	private int q_next_task;
	
	//任务接取时所需最小等级
	private int q_accept_needmingrade;
	
	//任务奖励达成某成就序列（成就编号;成就编号）
	private String q_rewards_achieve;
	
	//任务奖励经验
	private int q_rewards_exp;
	
	//任务奖励铜钱
	private int q_rewards_coin;
	
	//任务奖励真气
	private int q_rewards_zq;
	
	//任务奖励功勋
	private int q_rewards_exploit;
	
	//任务奖励声望
	private int q_rewards_prestige;
	
	//任务奖励绑定元宝
	private int q_rewards_bindYuanBao;
	
	//任务奖励物品序列（!(不绑定)物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;任务奖励物品序列（物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例）
	private String q_rewards_goods;
	
	//接取任务刷新BOSS
	private int q_taskfinlishboss;
	
	//自动完成类任务在完成时是否弹出任务交付面板（0不弹出，1弹出）
	private int q_ispromp;
	
	//坐骑阶数
	private int q_end_need_horselevel;
	
	//完成讨伐任务数
	private int q_end_need_conquertaskcount;
	
	//完成日常任务数
	private int q_end_need_dailytaskcount;
	
	//奖励侍宠
	private int q_rewards_pet;
	
	//剧情BOSS编号
	private int q_task_brush_monid;
	
	//任务刷怪地图
	private int q_brush_mon_map;
	
	//任务刷怪坐标
	private String q_brush_mon_xy;
	
	//接受时显示npc
	private String q_acc_show;
	
	//接受时隐藏npc
	private String q_acc_hide;
	
	//完成时显示npc
	private String q_end_show;
	
	//完成时隐藏npc
	private String q_end_hide;
	
	//是否需要完成动作（1是，非1即为否）
	private int q_end_needaction;
	
	//奖励脚本
	private int q_rewards_scrpt;
	
	//达成条件后显示
	private String q_accomplish_show;
	
	//达成条件后隐藏
	private String q_accomplish_hide;
	
	//采集目标NPC编号
	private int q_collection_target;
	
	//完成任务显示怪物
	private String q_end_show_moster;
	
	
	/**
	 * get 主线剧情大章节编号
	 * @return 
	 */
	public int getQ_chapter(){
		return q_chapter;
	}
	
	/**
	 * set 主线剧情大章节编号
	 */
	public void setQ_chapter(int q_chapter){
		this.q_chapter = q_chapter;
	}
	
	/**
	 * get 主线剧情大章节名称
	 * @return 
	 */
	public String getQ_chapter_name(){
		return q_chapter_name;
	}
	
	/**
	 * set 主线剧情大章节名称
	 */
	public void setQ_chapter_name(String q_chapter_name){
		this.q_chapter_name = q_chapter_name;
	}
	
	/**
	 * get 章节中的子任务编号
	 * @return 
	 */
	public int getQ_taskid(){
		return q_taskid;
	}
	
	/**
	 * set 章节中的子任务编号
	 */
	public void setQ_taskid(int q_taskid){
		this.q_taskid = q_taskid;
	}
	
	/**
	 * get 子任务名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 子任务名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 任务交付类型（1自动交付类任务，2在NPC处交付类任务）
	 * @return 
	 */
	public int getQ_finsh_type(){
		return q_finsh_type;
	}
	
	/**
	 * set 任务交付类型（1自动交付类任务，2在NPC处交付类任务）
	 */
	public void setQ_finsh_type(int q_finsh_type){
		this.q_finsh_type = q_finsh_type;
	}
	
	/**
	 * get 任务类型(1、对话 2、杀怪 3、杀怪掉落物品 4、采集 5、NPC间道具传递 6、读条播放特效 )
	 * @return 
	 */
	public int getQ_task_type(){
		return q_task_type;
	}
	
	/**
	 * set 任务类型(1、对话 2、杀怪 3、杀怪掉落物品 4、采集 5、NPC间道具传递 6、读条播放特效 )
	 */
	public void setQ_task_type(int q_task_type){
		this.q_task_type = q_task_type;
	}
	
	/**
	 * get 该任务在任务面板中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
	 * @return 
	 */
	public String getQ_task_desc(){
		return q_task_desc;
	}
	
	/**
	 * set 该任务在任务面板中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
	 */
	public void setQ_task_desc(String q_task_desc){
		this.q_task_desc = q_task_desc;
	}
	
	/**
	 * get 任务接取时发送的主界面对白
	 * @return 
	 */
	public String getQ_start_chat(){
		return q_start_chat;
	}
	
	/**
	 * set 任务接取时发送的主界面对白
	 */
	public void setQ_start_chat(String q_start_chat){
		this.q_start_chat = q_start_chat;
	}
	
	/**
	 * get 该任务在主界面任务追踪栏中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
	 * @return 
	 */
	public String getQ_task_desc2(){
		return q_task_desc2;
	}
	
	/**
	 * set 该任务在主界面任务追踪栏中的描述（支持Html）（支持寻径至地图及坐标格式：{@文字描述_xunjingmap_地图编号_X坐标_Y坐标} 寻径至NPC格式：{@文字描述_xunjingnpc_NPC编号} ）
	 */
	public void setQ_task_desc2(String q_task_desc2){
		this.q_task_desc2 = q_task_desc2;
	}
	
	/**
	 * get 完成任务NPC编号
	 * @return 
	 */
	public int getQ_endnpc(){
		return q_endnpc;
	}
	
	/**
	 * set 完成任务NPC编号
	 */
	public void setQ_endnpc(int q_endnpc){
		this.q_endnpc = q_endnpc;
	}
	
	/**
	 * get 任务完成条件检测物品（物品ID_需求数量_{寻径链接};物品ID_需求数量_{寻径链接};）
	 * @return 
	 */
	public String getQ_end_need_goods(){
		return q_end_need_goods;
	}
	
	/**
	 * set 任务完成条件检测物品（物品ID_需求数量_{寻径链接};物品ID_需求数量_{寻径链接};）
	 */
	public void setQ_end_need_goods(String q_end_need_goods){
		this.q_end_need_goods = q_end_need_goods;
	}
	
	/**
	 * get 任务完成条件检测杀死怪物序列（怪物ID_需求数量_{寻径链接};怪物ID_需求数量_{寻径链接};）
	 * @return 
	 */
	public String getQ_end_need_killmonster(){
		return q_end_need_killmonster;
	}
	
	/**
	 * set 任务完成条件检测杀死怪物序列（怪物ID_需求数量_{寻径链接};怪物ID_需求数量_{寻径链接};）
	 */
	public void setQ_end_need_killmonster(String q_end_need_killmonster){
		this.q_end_need_killmonster = q_end_need_killmonster;
	}
	
	/**
	 * get 任务完成条件检测成就序列（成就编号_{寻径链接};成就编号_{寻径链接}）
	 * @return 
	 */
	public String getQ_end_need_achieve(){
		return q_end_need_achieve;
	}
	
	/**
	 * set 任务完成条件检测成就序列（成就编号_{寻径链接};成就编号_{寻径链接}）
	 */
	public void setQ_end_need_achieve(String q_end_need_achieve){
		this.q_end_need_achieve = q_end_need_achieve;
	}
	
	/**
	 * get 完成时收取物品序列(物品ID_数量;物品ID_数量）
	 * @return 
	 */
	public String getQ_end_resume_goods(){
		return q_end_resume_goods;
	}
	
	/**
	 * set 完成时收取物品序列(物品ID_数量;物品ID_数量）
	 */
	public void setQ_end_resume_goods(String q_end_resume_goods){
		this.q_end_resume_goods = q_end_resume_goods;
	}
	
	/**
	 * get 完成后自动接取任务号
	 * @return 
	 */
	public int getQ_next_task(){
		return q_next_task;
	}
	
	/**
	 * set 完成后自动接取任务号
	 */
	public void setQ_next_task(int q_next_task){
		this.q_next_task = q_next_task;
	}
	
	/**
	 * get 任务接取时所需最小等级
	 * @return 
	 */
	public int getQ_accept_needmingrade(){
		return q_accept_needmingrade;
	}
	
	/**
	 * set 任务接取时所需最小等级
	 */
	public void setQ_accept_needmingrade(int q_accept_needmingrade){
		this.q_accept_needmingrade = q_accept_needmingrade;
	}
	
	/**
	 * get 任务奖励达成某成就序列（成就编号;成就编号）
	 * @return 
	 */
	public String getQ_rewards_achieve(){
		return q_rewards_achieve;
	}
	
	/**
	 * set 任务奖励达成某成就序列（成就编号;成就编号）
	 */
	public void setQ_rewards_achieve(String q_rewards_achieve){
		this.q_rewards_achieve = q_rewards_achieve;
	}
	
	/**
	 * get 任务奖励经验
	 * @return 
	 */
	public int getQ_rewards_exp(){
		return q_rewards_exp;
	}
	
	/**
	 * set 任务奖励经验
	 */
	public void setQ_rewards_exp(int q_rewards_exp){
		this.q_rewards_exp = q_rewards_exp;
	}
	
	/**
	 * get 任务奖励铜钱
	 * @return 
	 */
	public int getQ_rewards_coin(){
		return q_rewards_coin;
	}
	
	/**
	 * set 任务奖励铜钱
	 */
	public void setQ_rewards_coin(int q_rewards_coin){
		this.q_rewards_coin = q_rewards_coin;
	}
	
	/**
	 * get 任务奖励真气
	 * @return 
	 */
	public int getQ_rewards_zq(){
		return q_rewards_zq;
	}
	
	/**
	 * set 任务奖励真气
	 */
	public void setQ_rewards_zq(int q_rewards_zq){
		this.q_rewards_zq = q_rewards_zq;
	}
	
	/**
	 * get 任务奖励功勋
	 * @return 
	 */
	public int getQ_rewards_exploit(){
		return q_rewards_exploit;
	}
	
	/**
	 * set 任务奖励功勋
	 */
	public void setQ_rewards_exploit(int q_rewards_exploit){
		this.q_rewards_exploit = q_rewards_exploit;
	}
	
	/**
	 * get 任务奖励声望
	 * @return 
	 */
	public int getQ_rewards_prestige(){
		return q_rewards_prestige;
	}
	
	/**
	 * set 任务奖励声望
	 */
	public void setQ_rewards_prestige(int q_rewards_prestige){
		this.q_rewards_prestige = q_rewards_prestige;
	}
	
	/**
	 * get 任务奖励绑定元宝
	 * @return 
	 */
	public int getQ_rewards_bindYuanBao(){
		return q_rewards_bindYuanBao;
	}
	
	/**
	 * set 任务奖励绑定元宝
	 */
	public void setQ_rewards_bindYuanBao(int q_rewards_bindYuanBao){
		this.q_rewards_bindYuanBao = q_rewards_bindYuanBao;
	}
	
	/**
	 * get 任务奖励物品序列（!(不绑定)物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;任务奖励物品序列（物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例）
	 * @return 
	 */
	public String getQ_rewards_goods(){
		return q_rewards_goods;
	}
	
	/**
	 * set 任务奖励物品序列（!(不绑定)物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;任务奖励物品序列（物品ID_数量_性别要求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例）
	 */
	public void setQ_rewards_goods(String q_rewards_goods){
		this.q_rewards_goods = q_rewards_goods;
	}
	
	/**
	 * get 接取任务刷新BOSS
	 * @return 
	 */
	public int getQ_taskfinlishboss(){
		return q_taskfinlishboss;
	}
	
	/**
	 * set 接取任务刷新BOSS
	 */
	public void setQ_taskfinlishboss(int q_taskfinlishboss){
		this.q_taskfinlishboss = q_taskfinlishboss;
	}
	
	/**
	 * get 自动完成类任务在完成时是否弹出任务交付面板（0不弹出，1弹出）
	 * @return 
	 */
	public int getQ_ispromp(){
		return q_ispromp;
	}
	
	/**
	 * set 自动完成类任务在完成时是否弹出任务交付面板（0不弹出，1弹出）
	 */
	public void setQ_ispromp(int q_ispromp){
		this.q_ispromp = q_ispromp;
	}
	
	/**
	 * get 坐骑阶数
	 * @return 
	 */
	public int getQ_end_need_horselevel(){
		return q_end_need_horselevel;
	}
	
	/**
	 * set 坐骑阶数
	 */
	public void setQ_end_need_horselevel(int q_end_need_horselevel){
		this.q_end_need_horselevel = q_end_need_horselevel;
	}
	
	/**
	 * get 完成讨伐任务数
	 * @return 
	 */
	public int getQ_end_need_conquertaskcount(){
		return q_end_need_conquertaskcount;
	}
	
	/**
	 * set 完成讨伐任务数
	 */
	public void setQ_end_need_conquertaskcount(int q_end_need_conquertaskcount){
		this.q_end_need_conquertaskcount = q_end_need_conquertaskcount;
	}
	
	/**
	 * get 完成日常任务数
	 * @return 
	 */
	public int getQ_end_need_dailytaskcount(){
		return q_end_need_dailytaskcount;
	}
	
	/**
	 * set 完成日常任务数
	 */
	public void setQ_end_need_dailytaskcount(int q_end_need_dailytaskcount){
		this.q_end_need_dailytaskcount = q_end_need_dailytaskcount;
	}
	
	/**
	 * get 奖励侍宠
	 * @return 
	 */
	public int getQ_rewards_pet(){
		return q_rewards_pet;
	}
	
	/**
	 * set 奖励侍宠
	 */
	public void setQ_rewards_pet(int q_rewards_pet){
		this.q_rewards_pet = q_rewards_pet;
	}
	
	/**
	 * get 剧情BOSS编号
	 * @return 
	 */
	public int getQ_task_brush_monid(){
		return q_task_brush_monid;
	}
	
	/**
	 * set 剧情BOSS编号
	 */
	public void setQ_task_brush_monid(int q_task_brush_monid){
		this.q_task_brush_monid = q_task_brush_monid;
	}
	
	/**
	 * get 任务刷怪地图
	 * @return 
	 */
	public int getQ_brush_mon_map(){
		return q_brush_mon_map;
	}
	
	/**
	 * set 任务刷怪地图
	 */
	public void setQ_brush_mon_map(int q_brush_mon_map){
		this.q_brush_mon_map = q_brush_mon_map;
	}
	
	/**
	 * get 任务刷怪坐标
	 * @return 
	 */
	public String getQ_brush_mon_xy(){
		return q_brush_mon_xy;
	}
	
	/**
	 * set 任务刷怪坐标
	 */
	public void setQ_brush_mon_xy(String q_brush_mon_xy){
		this.q_brush_mon_xy = q_brush_mon_xy;
	}
	
	/**
	 * get 接受时显示npc
	 * @return 
	 */
	public String getQ_acc_show(){
		return q_acc_show;
	}
	
	/**
	 * set 接受时显示npc
	 */
	public void setQ_acc_show(String q_acc_show){
		this.q_acc_show = q_acc_show;
	}
	
	/**
	 * get 接受时隐藏npc
	 * @return 
	 */
	public String getQ_acc_hide(){
		return q_acc_hide;
	}
	
	/**
	 * set 接受时隐藏npc
	 */
	public void setQ_acc_hide(String q_acc_hide){
		this.q_acc_hide = q_acc_hide;
	}
	
	/**
	 * get 完成时显示npc
	 * @return 
	 */
	public String getQ_end_show(){
		return q_end_show;
	}
	
	/**
	 * set 完成时显示npc
	 */
	public void setQ_end_show(String q_end_show){
		this.q_end_show = q_end_show;
	}
	
	/**
	 * get 完成时隐藏npc
	 * @return 
	 */
	public String getQ_end_hide(){
		return q_end_hide;
	}
	
	/**
	 * set 完成时隐藏npc
	 */
	public void setQ_end_hide(String q_end_hide){
		this.q_end_hide = q_end_hide;
	}
	
	/**
	 * get 是否需要完成动作（1是，非1即为否）
	 * @return 
	 */
	public int getQ_end_needaction(){
		return q_end_needaction;
	}
	
	/**
	 * set 是否需要完成动作（1是，非1即为否）
	 */
	public void setQ_end_needaction(int q_end_needaction){
		this.q_end_needaction = q_end_needaction;
	}
	
	/**
	 * get 奖励脚本
	 * @return 
	 */
	public int getQ_rewards_scrpt(){
		return q_rewards_scrpt;
	}
	
	/**
	 * set 奖励脚本
	 */
	public void setQ_rewards_scrpt(int q_rewards_scrpt){
		this.q_rewards_scrpt = q_rewards_scrpt;
	}
	
	/**
	 * get 达成条件后显示
	 * @return 
	 */
	public String getQ_accomplish_show(){
		return q_accomplish_show;
	}
	
	/**
	 * set 达成条件后显示
	 */
	public void setQ_accomplish_show(String q_accomplish_show){
		this.q_accomplish_show = q_accomplish_show;
	}
	
	/**
	 * get 达成条件后隐藏
	 * @return 
	 */
	public String getQ_accomplish_hide(){
		return q_accomplish_hide;
	}
	
	/**
	 * set 达成条件后隐藏
	 */
	public void setQ_accomplish_hide(String q_accomplish_hide){
		this.q_accomplish_hide = q_accomplish_hide;
	}
	
	/**
	 * get 采集目标NPC编号
	 * @return 
	 */
	public int getQ_collection_target(){
		return q_collection_target;
	}
	
	/**
	 * set 采集目标NPC编号
	 */
	public void setQ_collection_target(int q_collection_target){
		this.q_collection_target = q_collection_target;
	}
	
	/**
	 * get 完成任务显示怪物
	 * @return 
	 */
	public String getQ_end_show_moster(){
		return q_end_show_moster;
	}
	
	/**
	 * set 完成任务显示怪物
	 */
	public void setQ_end_show_moster(String q_end_show_moster){
		this.q_end_show_moster = q_end_show_moster;
	}
	
}