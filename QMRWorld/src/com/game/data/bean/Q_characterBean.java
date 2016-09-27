package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_character Bean
 */
public class Q_characterBean {

	//等级
	private int q_level;
	
	//本级攻击力
	private int q_attack;
	
	//本级防御力
	private int q_defense;
	
	//本级暴击值
	private int q_crit;
	
	//本级闪避值
	private int q_dodge;
	
	//本级生命值
	private int q_hp;
	
	//本级内力值
	private int q_mp;
	
	//本级体力值
	private int q_sp;
	
	//本级攻击速度
	private int q_attackspeed;
	
	//本级移动速度
	private int q_speed;
	
	//本级自动领悟的技能ID
	private int q_skill;
	
	//该级升级所需的经验总值（注意：本值是总值，不是增量值）
	private long q_exp;
	
	//打坐获得经验
	private int q_dazuoexp;
	
	//打坐获得真气
	private int q_dazuozq;
	
	//打坐获得生命值
	private int q_dazuohp;
	
	//打坐获得内力值
	private int q_dazuomp;
	
	//打坐获得体力值
	private int q_dazuosp;
	
	//白天打坐暴击概率
	private int q_eurpt_day_prob;
	
	//白天打坐暴击收益经验
	private int q_erupt_day_exp;
	
	//白天打坐暴击收益真气
	private int q_erupt_day_zq;
	
	//夜晚打坐暴击概率
	private int q_erupt_night_prob;
	
	//夜晚打坐暴击收益经验
	private int q_erupt_night_exp;
	
	//夜晚打坐暴击收益真气
	private int q_erupt_night_zq;
	
	//宠物双修获得经验
	private int q_pet_sxexp;
	
	//宠物双修获得真气
	private int q_pet_sxzq;
	
	//宠物双修获得生命值
	private int q_pet_sxhp;
	
	//宠物双修获得内力值
	private int q_pet_sxmp;
	
	//宠物双修获得体力值
	private int q_pet_sxsp;
	
	//宠物双修白天暴击概率
	private int q_petdaysx_prob;
	
	//宠物双修白天暴击收益经验
	private int q_petdaysx_exp;
	
	//宠物双修白天暴击收益真气
	private int q_petdaysx_zq;
	
	//宠物双修夜晚暴击概率
	private int q_petnightsx_prob;
	
	//宠物双修打坐收益经验
	private int q_petnightsx_exp;
	
	//宠物双修夜晚暴击收益真气
	private int q_petnightsx_zq;
	
	//玩家双修获得经验
	private int q_role_sxexp;
	
	//玩家双修获得真气
	private int q_role_sxzq;
	
	//玩家双修获得生命值
	private int q_role_sxhp;
	
	//玩家双修获得内力值
	private int q_role_sxmp;
	
	//玩家双修获得体力值
	private int q_role_sxsp;
	
	//玩家双修白天暴击概率
	private int q_roledaysx_prob;
	
	//玩家双修白天暴击收益经验
	private int q_roledaysx_exp;
	
	//玩家双修白天暴击收益真气
	private int q_roledaysx_zq;
	
	//玩家双修夜晚暴击概率
	private int q_rolenightsx_prob;
	
	//玩家双修打坐收益经验
	private int q_rolenightsx_exp;
	
	//玩家双修夜晚暴击收益真气
	private int q_rolenightsx_zq;
	
	//浇灌个人神树干旱果实增加经验
	private int q_spirittree_arid_exp;
	
	//浇灌仙露成功增加经验
	private int q_spirittree_dew_exp;
	
	//神树系统收获每日经验上限
	private int q_spirittree_exp_limit;
	
	//对boss连击加成攻击
	private int q_batter_atk;
	
	//经验奖励基础值
	private int q_basis_exp;
	
	//铜币奖励基础值
	private int q_basis_money;
	
	//真气奖励基础值
	private int q_basis_zhenqi;
	
	//礼金奖励基础值
	private int q_basis_bindgold;
	
	
	/**
	 * get 等级
	 * @return 
	 */
	public int getQ_level(){
		return q_level;
	}
	
	/**
	 * set 等级
	 */
	public void setQ_level(int q_level){
		this.q_level = q_level;
	}
	
	/**
	 * get 本级攻击力
	 * @return 
	 */
	public int getQ_attack(){
		return q_attack;
	}
	
	/**
	 * set 本级攻击力
	 */
	public void setQ_attack(int q_attack){
		this.q_attack = q_attack;
	}
	
	/**
	 * get 本级防御力
	 * @return 
	 */
	public int getQ_defense(){
		return q_defense;
	}
	
	/**
	 * set 本级防御力
	 */
	public void setQ_defense(int q_defense){
		this.q_defense = q_defense;
	}
	
	/**
	 * get 本级暴击值
	 * @return 
	 */
	public int getQ_crit(){
		return q_crit;
	}
	
	/**
	 * set 本级暴击值
	 */
	public void setQ_crit(int q_crit){
		this.q_crit = q_crit;
	}
	
	/**
	 * get 本级闪避值
	 * @return 
	 */
	public int getQ_dodge(){
		return q_dodge;
	}
	
	/**
	 * set 本级闪避值
	 */
	public void setQ_dodge(int q_dodge){
		this.q_dodge = q_dodge;
	}
	
	/**
	 * get 本级生命值
	 * @return 
	 */
	public int getQ_hp(){
		return q_hp;
	}
	
	/**
	 * set 本级生命值
	 */
	public void setQ_hp(int q_hp){
		this.q_hp = q_hp;
	}
	
	/**
	 * get 本级内力值
	 * @return 
	 */
	public int getQ_mp(){
		return q_mp;
	}
	
	/**
	 * set 本级内力值
	 */
	public void setQ_mp(int q_mp){
		this.q_mp = q_mp;
	}
	
	/**
	 * get 本级体力值
	 * @return 
	 */
	public int getQ_sp(){
		return q_sp;
	}
	
	/**
	 * set 本级体力值
	 */
	public void setQ_sp(int q_sp){
		this.q_sp = q_sp;
	}
	
	/**
	 * get 本级攻击速度
	 * @return 
	 */
	public int getQ_attackspeed(){
		return q_attackspeed;
	}
	
	/**
	 * set 本级攻击速度
	 */
	public void setQ_attackspeed(int q_attackspeed){
		this.q_attackspeed = q_attackspeed;
	}
	
	/**
	 * get 本级移动速度
	 * @return 
	 */
	public int getQ_speed(){
		return q_speed;
	}
	
	/**
	 * set 本级移动速度
	 */
	public void setQ_speed(int q_speed){
		this.q_speed = q_speed;
	}
	
	/**
	 * get 本级自动领悟的技能ID
	 * @return 
	 */
	public int getQ_skill(){
		return q_skill;
	}
	
	/**
	 * set 本级自动领悟的技能ID
	 */
	public void setQ_skill(int q_skill){
		this.q_skill = q_skill;
	}
	
	/**
	 * get 该级升级所需的经验总值（注意：本值是总值，不是增量值）
	 * @return 
	 */
	public long getQ_exp(){
		return q_exp;
	}
	
	/**
	 * set 该级升级所需的经验总值（注意：本值是总值，不是增量值）
	 */
	public void setQ_exp(long q_exp){
		this.q_exp = q_exp;
	}
	
	/**
	 * get 打坐获得经验
	 * @return 
	 */
	public int getQ_dazuoexp(){
		return q_dazuoexp;
	}
	
	/**
	 * set 打坐获得经验
	 */
	public void setQ_dazuoexp(int q_dazuoexp){
		this.q_dazuoexp = q_dazuoexp;
	}
	
	/**
	 * get 打坐获得真气
	 * @return 
	 */
	public int getQ_dazuozq(){
		return q_dazuozq;
	}
	
	/**
	 * set 打坐获得真气
	 */
	public void setQ_dazuozq(int q_dazuozq){
		this.q_dazuozq = q_dazuozq;
	}
	
	/**
	 * get 打坐获得生命值
	 * @return 
	 */
	public int getQ_dazuohp(){
		return q_dazuohp;
	}
	
	/**
	 * set 打坐获得生命值
	 */
	public void setQ_dazuohp(int q_dazuohp){
		this.q_dazuohp = q_dazuohp;
	}
	
	/**
	 * get 打坐获得内力值
	 * @return 
	 */
	public int getQ_dazuomp(){
		return q_dazuomp;
	}
	
	/**
	 * set 打坐获得内力值
	 */
	public void setQ_dazuomp(int q_dazuomp){
		this.q_dazuomp = q_dazuomp;
	}
	
	/**
	 * get 打坐获得体力值
	 * @return 
	 */
	public int getQ_dazuosp(){
		return q_dazuosp;
	}
	
	/**
	 * set 打坐获得体力值
	 */
	public void setQ_dazuosp(int q_dazuosp){
		this.q_dazuosp = q_dazuosp;
	}
	
	/**
	 * get 白天打坐暴击概率
	 * @return 
	 */
	public int getQ_eurpt_day_prob(){
		return q_eurpt_day_prob;
	}
	
	/**
	 * set 白天打坐暴击概率
	 */
	public void setQ_eurpt_day_prob(int q_eurpt_day_prob){
		this.q_eurpt_day_prob = q_eurpt_day_prob;
	}
	
	/**
	 * get 白天打坐暴击收益经验
	 * @return 
	 */
	public int getQ_erupt_day_exp(){
		return q_erupt_day_exp;
	}
	
	/**
	 * set 白天打坐暴击收益经验
	 */
	public void setQ_erupt_day_exp(int q_erupt_day_exp){
		this.q_erupt_day_exp = q_erupt_day_exp;
	}
	
	/**
	 * get 白天打坐暴击收益真气
	 * @return 
	 */
	public int getQ_erupt_day_zq(){
		return q_erupt_day_zq;
	}
	
	/**
	 * set 白天打坐暴击收益真气
	 */
	public void setQ_erupt_day_zq(int q_erupt_day_zq){
		this.q_erupt_day_zq = q_erupt_day_zq;
	}
	
	/**
	 * get 夜晚打坐暴击概率
	 * @return 
	 */
	public int getQ_erupt_night_prob(){
		return q_erupt_night_prob;
	}
	
	/**
	 * set 夜晚打坐暴击概率
	 */
	public void setQ_erupt_night_prob(int q_erupt_night_prob){
		this.q_erupt_night_prob = q_erupt_night_prob;
	}
	
	/**
	 * get 夜晚打坐暴击收益经验
	 * @return 
	 */
	public int getQ_erupt_night_exp(){
		return q_erupt_night_exp;
	}
	
	/**
	 * set 夜晚打坐暴击收益经验
	 */
	public void setQ_erupt_night_exp(int q_erupt_night_exp){
		this.q_erupt_night_exp = q_erupt_night_exp;
	}
	
	/**
	 * get 夜晚打坐暴击收益真气
	 * @return 
	 */
	public int getQ_erupt_night_zq(){
		return q_erupt_night_zq;
	}
	
	/**
	 * set 夜晚打坐暴击收益真气
	 */
	public void setQ_erupt_night_zq(int q_erupt_night_zq){
		this.q_erupt_night_zq = q_erupt_night_zq;
	}
	
	/**
	 * get 宠物双修获得经验
	 * @return 
	 */
	public int getQ_pet_sxexp(){
		return q_pet_sxexp;
	}
	
	/**
	 * set 宠物双修获得经验
	 */
	public void setQ_pet_sxexp(int q_pet_sxexp){
		this.q_pet_sxexp = q_pet_sxexp;
	}
	
	/**
	 * get 宠物双修获得真气
	 * @return 
	 */
	public int getQ_pet_sxzq(){
		return q_pet_sxzq;
	}
	
	/**
	 * set 宠物双修获得真气
	 */
	public void setQ_pet_sxzq(int q_pet_sxzq){
		this.q_pet_sxzq = q_pet_sxzq;
	}
	
	/**
	 * get 宠物双修获得生命值
	 * @return 
	 */
	public int getQ_pet_sxhp(){
		return q_pet_sxhp;
	}
	
	/**
	 * set 宠物双修获得生命值
	 */
	public void setQ_pet_sxhp(int q_pet_sxhp){
		this.q_pet_sxhp = q_pet_sxhp;
	}
	
	/**
	 * get 宠物双修获得内力值
	 * @return 
	 */
	public int getQ_pet_sxmp(){
		return q_pet_sxmp;
	}
	
	/**
	 * set 宠物双修获得内力值
	 */
	public void setQ_pet_sxmp(int q_pet_sxmp){
		this.q_pet_sxmp = q_pet_sxmp;
	}
	
	/**
	 * get 宠物双修获得体力值
	 * @return 
	 */
	public int getQ_pet_sxsp(){
		return q_pet_sxsp;
	}
	
	/**
	 * set 宠物双修获得体力值
	 */
	public void setQ_pet_sxsp(int q_pet_sxsp){
		this.q_pet_sxsp = q_pet_sxsp;
	}
	
	/**
	 * get 宠物双修白天暴击概率
	 * @return 
	 */
	public int getQ_petdaysx_prob(){
		return q_petdaysx_prob;
	}
	
	/**
	 * set 宠物双修白天暴击概率
	 */
	public void setQ_petdaysx_prob(int q_petdaysx_prob){
		this.q_petdaysx_prob = q_petdaysx_prob;
	}
	
	/**
	 * get 宠物双修白天暴击收益经验
	 * @return 
	 */
	public int getQ_petdaysx_exp(){
		return q_petdaysx_exp;
	}
	
	/**
	 * set 宠物双修白天暴击收益经验
	 */
	public void setQ_petdaysx_exp(int q_petdaysx_exp){
		this.q_petdaysx_exp = q_petdaysx_exp;
	}
	
	/**
	 * get 宠物双修白天暴击收益真气
	 * @return 
	 */
	public int getQ_petdaysx_zq(){
		return q_petdaysx_zq;
	}
	
	/**
	 * set 宠物双修白天暴击收益真气
	 */
	public void setQ_petdaysx_zq(int q_petdaysx_zq){
		this.q_petdaysx_zq = q_petdaysx_zq;
	}
	
	/**
	 * get 宠物双修夜晚暴击概率
	 * @return 
	 */
	public int getQ_petnightsx_prob(){
		return q_petnightsx_prob;
	}
	
	/**
	 * set 宠物双修夜晚暴击概率
	 */
	public void setQ_petnightsx_prob(int q_petnightsx_prob){
		this.q_petnightsx_prob = q_petnightsx_prob;
	}
	
	/**
	 * get 宠物双修打坐收益经验
	 * @return 
	 */
	public int getQ_petnightsx_exp(){
		return q_petnightsx_exp;
	}
	
	/**
	 * set 宠物双修打坐收益经验
	 */
	public void setQ_petnightsx_exp(int q_petnightsx_exp){
		this.q_petnightsx_exp = q_petnightsx_exp;
	}
	
	/**
	 * get 宠物双修夜晚暴击收益真气
	 * @return 
	 */
	public int getQ_petnightsx_zq(){
		return q_petnightsx_zq;
	}
	
	/**
	 * set 宠物双修夜晚暴击收益真气
	 */
	public void setQ_petnightsx_zq(int q_petnightsx_zq){
		this.q_petnightsx_zq = q_petnightsx_zq;
	}
	
	/**
	 * get 玩家双修获得经验
	 * @return 
	 */
	public int getQ_role_sxexp(){
		return q_role_sxexp;
	}
	
	/**
	 * set 玩家双修获得经验
	 */
	public void setQ_role_sxexp(int q_role_sxexp){
		this.q_role_sxexp = q_role_sxexp;
	}
	
	/**
	 * get 玩家双修获得真气
	 * @return 
	 */
	public int getQ_role_sxzq(){
		return q_role_sxzq;
	}
	
	/**
	 * set 玩家双修获得真气
	 */
	public void setQ_role_sxzq(int q_role_sxzq){
		this.q_role_sxzq = q_role_sxzq;
	}
	
	/**
	 * get 玩家双修获得生命值
	 * @return 
	 */
	public int getQ_role_sxhp(){
		return q_role_sxhp;
	}
	
	/**
	 * set 玩家双修获得生命值
	 */
	public void setQ_role_sxhp(int q_role_sxhp){
		this.q_role_sxhp = q_role_sxhp;
	}
	
	/**
	 * get 玩家双修获得内力值
	 * @return 
	 */
	public int getQ_role_sxmp(){
		return q_role_sxmp;
	}
	
	/**
	 * set 玩家双修获得内力值
	 */
	public void setQ_role_sxmp(int q_role_sxmp){
		this.q_role_sxmp = q_role_sxmp;
	}
	
	/**
	 * get 玩家双修获得体力值
	 * @return 
	 */
	public int getQ_role_sxsp(){
		return q_role_sxsp;
	}
	
	/**
	 * set 玩家双修获得体力值
	 */
	public void setQ_role_sxsp(int q_role_sxsp){
		this.q_role_sxsp = q_role_sxsp;
	}
	
	/**
	 * get 玩家双修白天暴击概率
	 * @return 
	 */
	public int getQ_roledaysx_prob(){
		return q_roledaysx_prob;
	}
	
	/**
	 * set 玩家双修白天暴击概率
	 */
	public void setQ_roledaysx_prob(int q_roledaysx_prob){
		this.q_roledaysx_prob = q_roledaysx_prob;
	}
	
	/**
	 * get 玩家双修白天暴击收益经验
	 * @return 
	 */
	public int getQ_roledaysx_exp(){
		return q_roledaysx_exp;
	}
	
	/**
	 * set 玩家双修白天暴击收益经验
	 */
	public void setQ_roledaysx_exp(int q_roledaysx_exp){
		this.q_roledaysx_exp = q_roledaysx_exp;
	}
	
	/**
	 * get 玩家双修白天暴击收益真气
	 * @return 
	 */
	public int getQ_roledaysx_zq(){
		return q_roledaysx_zq;
	}
	
	/**
	 * set 玩家双修白天暴击收益真气
	 */
	public void setQ_roledaysx_zq(int q_roledaysx_zq){
		this.q_roledaysx_zq = q_roledaysx_zq;
	}
	
	/**
	 * get 玩家双修夜晚暴击概率
	 * @return 
	 */
	public int getQ_rolenightsx_prob(){
		return q_rolenightsx_prob;
	}
	
	/**
	 * set 玩家双修夜晚暴击概率
	 */
	public void setQ_rolenightsx_prob(int q_rolenightsx_prob){
		this.q_rolenightsx_prob = q_rolenightsx_prob;
	}
	
	/**
	 * get 玩家双修打坐收益经验
	 * @return 
	 */
	public int getQ_rolenightsx_exp(){
		return q_rolenightsx_exp;
	}
	
	/**
	 * set 玩家双修打坐收益经验
	 */
	public void setQ_rolenightsx_exp(int q_rolenightsx_exp){
		this.q_rolenightsx_exp = q_rolenightsx_exp;
	}
	
	/**
	 * get 玩家双修夜晚暴击收益真气
	 * @return 
	 */
	public int getQ_rolenightsx_zq(){
		return q_rolenightsx_zq;
	}
	
	/**
	 * set 玩家双修夜晚暴击收益真气
	 */
	public void setQ_rolenightsx_zq(int q_rolenightsx_zq){
		this.q_rolenightsx_zq = q_rolenightsx_zq;
	}
	
	/**
	 * get 浇灌个人神树干旱果实增加经验
	 * @return 
	 */
	public int getQ_spirittree_arid_exp(){
		return q_spirittree_arid_exp;
	}
	
	/**
	 * set 浇灌个人神树干旱果实增加经验
	 */
	public void setQ_spirittree_arid_exp(int q_spirittree_arid_exp){
		this.q_spirittree_arid_exp = q_spirittree_arid_exp;
	}
	
	/**
	 * get 浇灌仙露成功增加经验
	 * @return 
	 */
	public int getQ_spirittree_dew_exp(){
		return q_spirittree_dew_exp;
	}
	
	/**
	 * set 浇灌仙露成功增加经验
	 */
	public void setQ_spirittree_dew_exp(int q_spirittree_dew_exp){
		this.q_spirittree_dew_exp = q_spirittree_dew_exp;
	}
	
	/**
	 * get 神树系统收获每日经验上限
	 * @return 
	 */
	public int getQ_spirittree_exp_limit(){
		return q_spirittree_exp_limit;
	}
	
	/**
	 * set 神树系统收获每日经验上限
	 */
	public void setQ_spirittree_exp_limit(int q_spirittree_exp_limit){
		this.q_spirittree_exp_limit = q_spirittree_exp_limit;
	}
	
	/**
	 * get 对boss连击加成攻击
	 * @return 
	 */
	public int getQ_batter_atk(){
		return q_batter_atk;
	}
	
	/**
	 * set 对boss连击加成攻击
	 */
	public void setQ_batter_atk(int q_batter_atk){
		this.q_batter_atk = q_batter_atk;
	}
	
	/**
	 * get 经验奖励基础值
	 * @return 
	 */
	public int getQ_basis_exp(){
		return q_basis_exp;
	}
	
	/**
	 * set 经验奖励基础值
	 */
	public void setQ_basis_exp(int q_basis_exp){
		this.q_basis_exp = q_basis_exp;
	}
	
	/**
	 * get 铜币奖励基础值
	 * @return 
	 */
	public int getQ_basis_money(){
		return q_basis_money;
	}
	
	/**
	 * set 铜币奖励基础值
	 */
	public void setQ_basis_money(int q_basis_money){
		this.q_basis_money = q_basis_money;
	}
	
	/**
	 * get 真气奖励基础值
	 * @return 
	 */
	public int getQ_basis_zhenqi(){
		return q_basis_zhenqi;
	}
	
	/**
	 * set 真气奖励基础值
	 */
	public void setQ_basis_zhenqi(int q_basis_zhenqi){
		this.q_basis_zhenqi = q_basis_zhenqi;
	}
	
	/**
	 * get 礼金奖励基础值
	 * @return 
	 */
	public int getQ_basis_bindgold(){
		return q_basis_bindgold;
	}
	
	/**
	 * set 礼金奖励基础值
	 */
	public void setQ_basis_bindgold(int q_basis_bindgold){
		this.q_basis_bindgold = q_basis_bindgold;
	}
	
}