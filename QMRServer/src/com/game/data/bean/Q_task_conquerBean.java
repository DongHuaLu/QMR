package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_conquer Bean
 */
public class Q_task_conquerBean {

	//讨伐任务编号
	private int q_id;
	
	//玩家等级区间min
	private int q_mingrade;
	
	//玩家等级区间max
	private int q_maxgrade;
	
	//卷轴颜色（0白色，1黄色，2绿色，3蓝色，4紫色）颜色越高则完成难度越低，而且奖励越高
	private int q_scroll_type;
	
	//美术图片资源编号
	private int q_image;
	
	//任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
	private String q_monstercount;
	
	//任务接取时需检测达成某成就序列（成就编号;成就编号）
	private String q_accept_needAchieve;
	
	//接取任务时发送的屏幕提示信息文字
	private String q_accept_prompt;
	
	//任务奖励达成某成就序列（成就编号;成就编号）
	private String q_rewards_achieve;
	
	//任务奖励经验
	private int q_rewards_exp;
	
	//任务奖励铜钱
	private int q_rewards_coin;
	
	//任务奖励真气
	private int q_rewards_zq;
	
	//任务奖励声望
	private int q_rewards_prestige;
	
	//任务奖励绑定元宝
	private int q_rewards_bindyuanbao;
	
	//互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	private String q_rewards_goods;
	
	//男互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	private String q_man_rewards_goods;
	
	//女互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	private String q_women_rewards_goods;
	
	//是否允许被吞噬（0不允许，1允许）
	private int q_devour_able;
	
	//被吞噬后的奖励合并比例幅度
	private int q_devour_prop;
	
	
	/**
	 * get 讨伐任务编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 讨伐任务编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 玩家等级区间min
	 * @return 
	 */
	public int getQ_mingrade(){
		return q_mingrade;
	}
	
	/**
	 * set 玩家等级区间min
	 */
	public void setQ_mingrade(int q_mingrade){
		this.q_mingrade = q_mingrade;
	}
	
	/**
	 * get 玩家等级区间max
	 * @return 
	 */
	public int getQ_maxgrade(){
		return q_maxgrade;
	}
	
	/**
	 * set 玩家等级区间max
	 */
	public void setQ_maxgrade(int q_maxgrade){
		this.q_maxgrade = q_maxgrade;
	}
	
	/**
	 * get 卷轴颜色（0白色，1黄色，2绿色，3蓝色，4紫色）颜色越高则完成难度越低，而且奖励越高
	 * @return 
	 */
	public int getQ_scroll_type(){
		return q_scroll_type;
	}
	
	/**
	 * set 卷轴颜色（0白色，1黄色，2绿色，3蓝色，4紫色）颜色越高则完成难度越低，而且奖励越高
	 */
	public void setQ_scroll_type(int q_scroll_type){
		this.q_scroll_type = q_scroll_type;
	}
	
	/**
	 * get 美术图片资源编号
	 * @return 
	 */
	public int getQ_image(){
		return q_image;
	}
	
	/**
	 * set 美术图片资源编号
	 */
	public void setQ_image(int q_image){
		this.q_image = q_image;
	}
	
	/**
	 * get 任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
	 * @return 
	 */
	public String getQ_monstercount(){
		return q_monstercount;
	}
	
	/**
	 * set 任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
	 */
	public void setQ_monstercount(String q_monstercount){
		this.q_monstercount = q_monstercount;
	}
	
	/**
	 * get 任务接取时需检测达成某成就序列（成就编号;成就编号）
	 * @return 
	 */
	public String getQ_accept_needAchieve(){
		return q_accept_needAchieve;
	}
	
	/**
	 * set 任务接取时需检测达成某成就序列（成就编号;成就编号）
	 */
	public void setQ_accept_needAchieve(String q_accept_needAchieve){
		this.q_accept_needAchieve = q_accept_needAchieve;
	}
	
	/**
	 * get 接取任务时发送的屏幕提示信息文字
	 * @return 
	 */
	public String getQ_accept_prompt(){
		return q_accept_prompt;
	}
	
	/**
	 * set 接取任务时发送的屏幕提示信息文字
	 */
	public void setQ_accept_prompt(String q_accept_prompt){
		this.q_accept_prompt = q_accept_prompt;
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
	public int getQ_rewards_bindyuanbao(){
		return q_rewards_bindyuanbao;
	}
	
	/**
	 * set 任务奖励绑定元宝
	 */
	public void setQ_rewards_bindyuanbao(int q_rewards_bindyuanbao){
		this.q_rewards_bindyuanbao = q_rewards_bindyuanbao;
	}
	
	/**
	 * get 互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	 * @return 
	 */
	public String getQ_rewards_goods(){
		return q_rewards_goods;
	}
	
	/**
	 * set 互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	 */
	public void setQ_rewards_goods(String q_rewards_goods){
		this.q_rewards_goods = q_rewards_goods;
	}
	
	/**
	 * get 男互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	 * @return 
	 */
	public String getQ_man_rewards_goods(){
		return q_man_rewards_goods;
	}
	
	/**
	 * set 男互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	 */
	public void setQ_man_rewards_goods(String q_man_rewards_goods){
		this.q_man_rewards_goods = q_man_rewards_goods;
	}
	
	/**
	 * get 女互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	 * @return 
	 */
	public String getQ_women_rewards_goods(){
		return q_women_rewards_goods;
	}
	
	/**
	 * set 女互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
	 */
	public void setQ_women_rewards_goods(String q_women_rewards_goods){
		this.q_women_rewards_goods = q_women_rewards_goods;
	}
	
	/**
	 * get 是否允许被吞噬（0不允许，1允许）
	 * @return 
	 */
	public int getQ_devour_able(){
		return q_devour_able;
	}
	
	/**
	 * set 是否允许被吞噬（0不允许，1允许）
	 */
	public void setQ_devour_able(int q_devour_able){
		this.q_devour_able = q_devour_able;
	}
	
	/**
	 * get 被吞噬后的奖励合并比例幅度
	 * @return 
	 */
	public int getQ_devour_prop(){
		return q_devour_prop;
	}
	
	/**
	 * set 被吞噬后的奖励合并比例幅度
	 */
	public void setQ_devour_prop(int q_devour_prop){
		this.q_devour_prop = q_devour_prop;
	}
	
}