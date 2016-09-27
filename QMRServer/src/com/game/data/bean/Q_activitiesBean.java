package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_activities Bean
 */
public class Q_activitiesBean {

	//活动编号
	private int q_id;
	
	//活动名称
	private String q_name;
	
	//活动主类型(表示是什么类型的活动)
	private int q_maintype;
	
	//活动类型(同一天的写一个类型，或者一直就开放的写一个类型)
	private int q_type;
	
	//活动奖励类型（1为每天展示奖励(领完之后消失)，2为展示全部奖励）
	private int q_show;
	
	//同面板内的活动ID关联
	private String q_mainicon;
	
	//（1玩家领取，0账号领取）
	private String q_titleimage;
	
	//区别老新服（1新区,2旧，0通用）
	private String q_listimage;
	
	//是否为推荐活动（0为不推荐，1为推荐）
	private int q_tuijian;
	
	//是否可重复领取（0不可1可）
	private int q_canrepeated;
	
	//活动持续时间[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
	private String q_duration;
	
	//定时开放活动[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
	private String q_timingstart;
	
	//活动内容介绍
	private String q_info;
	
	//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
	private String q_items;
	
	//需要登入天数
	private int q_login_limit;
	
	//需要充值元宝数([0(0 有没有充值 1 每日的充值 2 一段时间的充值 3 总充值 4 排行榜),1(充值 0 大于后面一个数字就可以 1 根据充值数字和后面数字取次数， 排行 排行类型), 3(充值 得到奖励的条件数字 ， 排行 名次)， 1212301059开始时间，1212311059结束时间])
	private String q_pay_yuanbao;
	
	//需要等级
	private int q_need_level;
	
	//需要坐骑品阶
	private int q_need_horse;
	
	//需要武功层数和
	private int q_need_skill;
	
	//其他数据([[1(VIP),3(VIP等级)],[2(王城),1(任期)，X（天数）]，[3（商城）,0（购买到数量领取）1（购买数量多次领取），物品ID，需购买数量],[4(军功领奖励)],[6军功，3倍数]])
	private String q_other;
	
	//类型检测
	private String q_flag;
	
	//活动资源控制
	private String q_resflag;
	
	//达成在线时间（秒）
	private int q_onlinetime;
	
	
	/**
	 * get 活动编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 活动编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 活动名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 活动名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 活动主类型(表示是什么类型的活动)
	 * @return 
	 */
	public int getQ_maintype(){
		return q_maintype;
	}
	
	/**
	 * set 活动主类型(表示是什么类型的活动)
	 */
	public void setQ_maintype(int q_maintype){
		this.q_maintype = q_maintype;
	}
	
	/**
	 * get 活动类型(同一天的写一个类型，或者一直就开放的写一个类型)
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 活动类型(同一天的写一个类型，或者一直就开放的写一个类型)
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 活动奖励类型（1为每天展示奖励(领完之后消失)，2为展示全部奖励）
	 * @return 
	 */
	public int getQ_show(){
		return q_show;
	}
	
	/**
	 * set 活动奖励类型（1为每天展示奖励(领完之后消失)，2为展示全部奖励）
	 */
	public void setQ_show(int q_show){
		this.q_show = q_show;
	}
	
	/**
	 * get 同面板内的活动ID关联
	 * @return 
	 */
	public String getQ_mainicon(){
		return q_mainicon;
	}
	
	/**
	 * set 同面板内的活动ID关联
	 */
	public void setQ_mainicon(String q_mainicon){
		this.q_mainicon = q_mainicon;
	}
	
	/**
	 * get （1玩家领取，0账号领取）
	 * @return 
	 */
	public String getQ_titleimage(){
		return q_titleimage;
	}
	
	/**
	 * set （1玩家领取，0账号领取）
	 */
	public void setQ_titleimage(String q_titleimage){
		this.q_titleimage = q_titleimage;
	}
	
	/**
	 * get 区别老新服（1新区,2旧，0通用）
	 * @return 
	 */
	public String getQ_listimage(){
		return q_listimage;
	}
	
	/**
	 * set 区别老新服（1新区,2旧，0通用）
	 */
	public void setQ_listimage(String q_listimage){
		this.q_listimage = q_listimage;
	}
	
	/**
	 * get 是否为推荐活动（0为不推荐，1为推荐）
	 * @return 
	 */
	public int getQ_tuijian(){
		return q_tuijian;
	}
	
	/**
	 * set 是否为推荐活动（0为不推荐，1为推荐）
	 */
	public void setQ_tuijian(int q_tuijian){
		this.q_tuijian = q_tuijian;
	}
	
	/**
	 * get 是否可重复领取（0不可1可）
	 * @return 
	 */
	public int getQ_canrepeated(){
		return q_canrepeated;
	}
	
	/**
	 * set 是否可重复领取（0不可1可）
	 */
	public void setQ_canrepeated(int q_canrepeated){
		this.q_canrepeated = q_canrepeated;
	}
	
	/**
	 * get 活动持续时间[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
	 * @return 
	 */
	public String getQ_duration(){
		return q_duration;
	}
	
	/**
	 * set 活动持续时间[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
	 */
	public void setQ_duration(String q_duration){
		this.q_duration = q_duration;
	}
	
	/**
	 * get 定时开放活动[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
	 * @return 
	 */
	public String getQ_timingstart(){
		return q_timingstart;
	}
	
	/**
	 * set 定时开放活动[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
	 */
	public void setQ_timingstart(String q_timingstart){
		this.q_timingstart = q_timingstart;
	}
	
	/**
	 * get 活动内容介绍
	 * @return 
	 */
	public String getQ_info(){
		return q_info;
	}
	
	/**
	 * set 活动内容介绍
	 */
	public void setQ_info(String q_info){
		this.q_info = q_info;
	}
	
	/**
	 * get 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
	 * @return 
	 */
	public String getQ_items(){
		return q_items;
	}
	
	/**
	 * set 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
	 */
	public void setQ_items(String q_items){
		this.q_items = q_items;
	}
	
	/**
	 * get 需要登入天数
	 * @return 
	 */
	public int getQ_login_limit(){
		return q_login_limit;
	}
	
	/**
	 * set 需要登入天数
	 */
	public void setQ_login_limit(int q_login_limit){
		this.q_login_limit = q_login_limit;
	}
	
	/**
	 * get 需要充值元宝数([0(0 有没有充值 1 每日的充值 2 一段时间的充值 3 总充值 4 排行榜),1(充值 0 大于后面一个数字就可以 1 根据充值数字和后面数字取次数， 排行 排行类型), 3(充值 得到奖励的条件数字 ， 排行 名次)， 1212301059开始时间，1212311059结束时间])
	 * @return 
	 */
	public String getQ_pay_yuanbao(){
		return q_pay_yuanbao;
	}
	
	/**
	 * set 需要充值元宝数([0(0 有没有充值 1 每日的充值 2 一段时间的充值 3 总充值 4 排行榜),1(充值 0 大于后面一个数字就可以 1 根据充值数字和后面数字取次数， 排行 排行类型), 3(充值 得到奖励的条件数字 ， 排行 名次)， 1212301059开始时间，1212311059结束时间])
	 */
	public void setQ_pay_yuanbao(String q_pay_yuanbao){
		this.q_pay_yuanbao = q_pay_yuanbao;
	}
	
	/**
	 * get 需要等级
	 * @return 
	 */
	public int getQ_need_level(){
		return q_need_level;
	}
	
	/**
	 * set 需要等级
	 */
	public void setQ_need_level(int q_need_level){
		this.q_need_level = q_need_level;
	}
	
	/**
	 * get 需要坐骑品阶
	 * @return 
	 */
	public int getQ_need_horse(){
		return q_need_horse;
	}
	
	/**
	 * set 需要坐骑品阶
	 */
	public void setQ_need_horse(int q_need_horse){
		this.q_need_horse = q_need_horse;
	}
	
	/**
	 * get 需要武功层数和
	 * @return 
	 */
	public int getQ_need_skill(){
		return q_need_skill;
	}
	
	/**
	 * set 需要武功层数和
	 */
	public void setQ_need_skill(int q_need_skill){
		this.q_need_skill = q_need_skill;
	}
	
	/**
	 * get 其他数据([[1(VIP),3(VIP等级)],[2(王城),1(任期)，X（天数）]，[3（商城）,0（购买到数量领取）1（购买数量多次领取），物品ID，需购买数量],[4(军功领奖励)],[6军功，3倍数]])
	 * @return 
	 */
	public String getQ_other(){
		return q_other;
	}
	
	/**
	 * set 其他数据([[1(VIP),3(VIP等级)],[2(王城),1(任期)，X（天数）]，[3（商城）,0（购买到数量领取）1（购买数量多次领取），物品ID，需购买数量],[4(军功领奖励)],[6军功，3倍数]])
	 */
	public void setQ_other(String q_other){
		this.q_other = q_other;
	}
	
	/**
	 * get 类型检测
	 * @return 
	 */
	public String getQ_flag(){
		return q_flag;
	}
	
	/**
	 * set 类型检测
	 */
	public void setQ_flag(String q_flag){
		this.q_flag = q_flag;
	}
	
	/**
	 * get 活动资源控制
	 * @return 
	 */
	public String getQ_resflag(){
		return q_resflag;
	}
	
	/**
	 * set 活动资源控制
	 */
	public void setQ_resflag(String q_resflag){
		this.q_resflag = q_resflag;
	}
	
	/**
	 * get 达成在线时间（秒）
	 * @return 
	 */
	public int getQ_onlinetime(){
		return q_onlinetime;
	}
	
	/**
	 * set 达成在线时间（秒）
	 */
	public void setQ_onlinetime(int q_onlinetime){
		this.q_onlinetime = q_onlinetime;
	}
	
}