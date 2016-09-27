package datasets.bean{
	import com.game.utils.long;
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_character
	 */
	public class Q_character extends Bean{
	
		//等级
		private var _q_level: int;
		
		//本级攻击力
		private var _q_attack: int;
		
		//本级防御力
		private var _q_defense: int;
		
		//本级暴击值
		private var _q_crit: int;
		
		//本级闪避值
		private var _q_dodge: int;
		
		//本级生命值
		private var _q_hp: int;
		
		//本级内力值
		private var _q_mp: int;
		
		//本级体力值
		private var _q_sp: int;
		
		//本级攻击速度
		private var _q_attackspeed: int;
		
		//本级移动速度
		private var _q_speed: int;
		
		//本级自动领悟的技能ID
		private var _q_skill: int;
		
		//该级升级所需的经验总值（注意：本值是总值，不是增量值）
		private var _q_exp: long;
		
		//打坐获得经验
		private var _q_dazuoexp: int;
		
		//打坐获得真气
		private var _q_dazuozq: int;
		
		//打坐获得生命值
		private var _q_dazuohp: int;
		
		//打坐获得内力值
		private var _q_dazuomp: int;
		
		//打坐获得体力值
		private var _q_dazuosp: int;
		
		//白天打坐暴击概率
		private var _q_eurpt_day_prob: int;
		
		//白天打坐暴击收益经验
		private var _q_erupt_day_exp: int;
		
		//白天打坐暴击收益真气
		private var _q_erupt_day_zq: int;
		
		//夜晚打坐暴击概率
		private var _q_erupt_night_prob: int;
		
		//夜晚打坐暴击收益经验
		private var _q_erupt_night_exp: int;
		
		//夜晚打坐暴击收益真气
		private var _q_erupt_night_zq: int;
		
		//宠物双修获得经验
		private var _q_pet_sxexp: int;
		
		//宠物双修获得真气
		private var _q_pet_sxzq: int;
		
		//宠物双修获得生命值
		private var _q_pet_sxhp: int;
		
		//宠物双修获得内力值
		private var _q_pet_sxmp: int;
		
		//宠物双修获得体力值
		private var _q_pet_sxsp: int;
		
		//宠物双修白天暴击概率
		private var _q_petdaysx_prob: int;
		
		//宠物双修白天暴击收益经验
		private var _q_petdaysx_exp: int;
		
		//宠物双修白天暴击收益真气
		private var _q_petdaysx_zq: int;
		
		//宠物双修夜晚暴击概率
		private var _q_petnightsx_prob: int;
		
		//宠物双修打坐收益经验
		private var _q_petnightsx_exp: int;
		
		//宠物双修夜晚暴击收益真气
		private var _q_petnightsx_zq: int;
		
		//玩家双修获得经验
		private var _q_role_sxexp: int;
		
		//玩家双修获得真气
		private var _q_role_sxzq: int;
		
		//玩家双修获得生命值
		private var _q_role_sxhp: int;
		
		//玩家双修获得内力值
		private var _q_role_sxmp: int;
		
		//玩家双修获得体力值
		private var _q_role_sxsp: int;
		
		//玩家双修白天暴击概率
		private var _q_roledaysx_prob: int;
		
		//玩家双修白天暴击收益经验
		private var _q_roledaysx_exp: int;
		
		//玩家双修白天暴击收益真气
		private var _q_roledaysx_zq: int;
		
		//玩家双修夜晚暴击概率
		private var _q_rolenightsx_prob: int;
		
		//玩家双修打坐收益经验
		private var _q_rolenightsx_exp: int;
		
		//玩家双修夜晚暴击收益真气
		private var _q_rolenightsx_zq: int;
		
		//浇灌个人神树干旱果实增加经验
		private var _q_spirittree_arid_exp: int;
		
		//浇灌仙露成功增加经验
		private var _q_spirittree_dew_exp: int;
		
		//神树系统收获每日经验上限
		private var _q_spirittree_exp_limit: int;
		
		//对boss连击加成攻击
		private var _q_batter_atk: int;
		
		//经验奖励基础值
		private var _q_basis_exp: int;
		
		//铜币奖励基础值
		private var _q_basis_money: int;
		
		//真气奖励基础值
		private var _q_basis_zhenqi: int;
		
		//礼金奖励基础值
		private var _q_basis_bindgold: int;
		
		//自动回复生命值
		private var _q_auto_recover_hp: int;
		
		//自动回复内力值
		private var _q_auto_recover_mp: int;
		
		//自动回复体力值
		private var _q_auto_recover_sp: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//等级
			_q_level = readInt();
			//本级攻击力
			_q_attack = readInt();
			//本级防御力
			_q_defense = readInt();
			//本级暴击值
			_q_crit = readInt();
			//本级闪避值
			_q_dodge = readInt();
			//本级生命值
			_q_hp = readInt();
			//本级内力值
			_q_mp = readInt();
			//本级体力值
			_q_sp = readInt();
			//本级攻击速度
			_q_attackspeed = readInt();
			//本级移动速度
			_q_speed = readInt();
			//本级自动领悟的技能ID
			_q_skill = readInt();
			//该级升级所需的经验总值（注意：本值是总值，不是增量值）
			_q_exp = readLong();
			//打坐获得经验
			_q_dazuoexp = readInt();
			//打坐获得真气
			_q_dazuozq = readInt();
			//打坐获得生命值
			_q_dazuohp = readInt();
			//打坐获得内力值
			_q_dazuomp = readInt();
			//打坐获得体力值
			_q_dazuosp = readInt();
			//白天打坐暴击概率
			_q_eurpt_day_prob = readInt();
			//白天打坐暴击收益经验
			_q_erupt_day_exp = readInt();
			//白天打坐暴击收益真气
			_q_erupt_day_zq = readInt();
			//夜晚打坐暴击概率
			_q_erupt_night_prob = readInt();
			//夜晚打坐暴击收益经验
			_q_erupt_night_exp = readInt();
			//夜晚打坐暴击收益真气
			_q_erupt_night_zq = readInt();
			//宠物双修获得经验
			_q_pet_sxexp = readInt();
			//宠物双修获得真气
			_q_pet_sxzq = readInt();
			//宠物双修获得生命值
			_q_pet_sxhp = readInt();
			//宠物双修获得内力值
			_q_pet_sxmp = readInt();
			//宠物双修获得体力值
			_q_pet_sxsp = readInt();
			//宠物双修白天暴击概率
			_q_petdaysx_prob = readInt();
			//宠物双修白天暴击收益经验
			_q_petdaysx_exp = readInt();
			//宠物双修白天暴击收益真气
			_q_petdaysx_zq = readInt();
			//宠物双修夜晚暴击概率
			_q_petnightsx_prob = readInt();
			//宠物双修打坐收益经验
			_q_petnightsx_exp = readInt();
			//宠物双修夜晚暴击收益真气
			_q_petnightsx_zq = readInt();
			//玩家双修获得经验
			_q_role_sxexp = readInt();
			//玩家双修获得真气
			_q_role_sxzq = readInt();
			//玩家双修获得生命值
			_q_role_sxhp = readInt();
			//玩家双修获得内力值
			_q_role_sxmp = readInt();
			//玩家双修获得体力值
			_q_role_sxsp = readInt();
			//玩家双修白天暴击概率
			_q_roledaysx_prob = readInt();
			//玩家双修白天暴击收益经验
			_q_roledaysx_exp = readInt();
			//玩家双修白天暴击收益真气
			_q_roledaysx_zq = readInt();
			//玩家双修夜晚暴击概率
			_q_rolenightsx_prob = readInt();
			//玩家双修打坐收益经验
			_q_rolenightsx_exp = readInt();
			//玩家双修夜晚暴击收益真气
			_q_rolenightsx_zq = readInt();
			//浇灌个人神树干旱果实增加经验
			_q_spirittree_arid_exp = readInt();
			//浇灌仙露成功增加经验
			_q_spirittree_dew_exp = readInt();
			//神树系统收获每日经验上限
			_q_spirittree_exp_limit = readInt();
			//对boss连击加成攻击
			_q_batter_atk = readInt();
			//经验奖励基础值
			_q_basis_exp = readInt();
			//铜币奖励基础值
			_q_basis_money = readInt();
			//真气奖励基础值
			_q_basis_zhenqi = readInt();
			//礼金奖励基础值
			_q_basis_bindgold = readInt();
			//自动回复生命值
			_q_auto_recover_hp = readInt();
			//自动回复内力值
			_q_auto_recover_mp = readInt();
			//自动回复体力值
			_q_auto_recover_sp = readInt();
			return true;
		}
		
		/**
		 * get 等级
		 * @return 
		 */
		public function get q_level(): int{
			return _q_level;
		}
		
		/**
		 * set 等级
		 */
		public function set q_level(value: int): void{
			this._q_level = value;
		}
		
		/**
		 * get 本级攻击力
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 本级攻击力
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 本级防御力
		 * @return 
		 */
		public function get q_defense(): int{
			return _q_defense;
		}
		
		/**
		 * set 本级防御力
		 */
		public function set q_defense(value: int): void{
			this._q_defense = value;
		}
		
		/**
		 * get 本级暴击值
		 * @return 
		 */
		public function get q_crit(): int{
			return _q_crit;
		}
		
		/**
		 * set 本级暴击值
		 */
		public function set q_crit(value: int): void{
			this._q_crit = value;
		}
		
		/**
		 * get 本级闪避值
		 * @return 
		 */
		public function get q_dodge(): int{
			return _q_dodge;
		}
		
		/**
		 * set 本级闪避值
		 */
		public function set q_dodge(value: int): void{
			this._q_dodge = value;
		}
		
		/**
		 * get 本级生命值
		 * @return 
		 */
		public function get q_hp(): int{
			return _q_hp;
		}
		
		/**
		 * set 本级生命值
		 */
		public function set q_hp(value: int): void{
			this._q_hp = value;
		}
		
		/**
		 * get 本级内力值
		 * @return 
		 */
		public function get q_mp(): int{
			return _q_mp;
		}
		
		/**
		 * set 本级内力值
		 */
		public function set q_mp(value: int): void{
			this._q_mp = value;
		}
		
		/**
		 * get 本级体力值
		 * @return 
		 */
		public function get q_sp(): int{
			return _q_sp;
		}
		
		/**
		 * set 本级体力值
		 */
		public function set q_sp(value: int): void{
			this._q_sp = value;
		}
		
		/**
		 * get 本级攻击速度
		 * @return 
		 */
		public function get q_attackspeed(): int{
			return _q_attackspeed;
		}
		
		/**
		 * set 本级攻击速度
		 */
		public function set q_attackspeed(value: int): void{
			this._q_attackspeed = value;
		}
		
		/**
		 * get 本级移动速度
		 * @return 
		 */
		public function get q_speed(): int{
			return _q_speed;
		}
		
		/**
		 * set 本级移动速度
		 */
		public function set q_speed(value: int): void{
			this._q_speed = value;
		}
		
		/**
		 * get 本级自动领悟的技能ID
		 * @return 
		 */
		public function get q_skill(): int{
			return _q_skill;
		}
		
		/**
		 * set 本级自动领悟的技能ID
		 */
		public function set q_skill(value: int): void{
			this._q_skill = value;
		}
		
		/**
		 * get 该级升级所需的经验总值（注意：本值是总值，不是增量值）
		 * @return 
		 */
		public function get q_exp(): long{
			return _q_exp;
		}
		
		/**
		 * set 该级升级所需的经验总值（注意：本值是总值，不是增量值）
		 */
		public function set q_exp(value: long): void{
			this._q_exp = value;
		}
		
		/**
		 * get 打坐获得经验
		 * @return 
		 */
		public function get q_dazuoexp(): int{
			return _q_dazuoexp;
		}
		
		/**
		 * set 打坐获得经验
		 */
		public function set q_dazuoexp(value: int): void{
			this._q_dazuoexp = value;
		}
		
		/**
		 * get 打坐获得真气
		 * @return 
		 */
		public function get q_dazuozq(): int{
			return _q_dazuozq;
		}
		
		/**
		 * set 打坐获得真气
		 */
		public function set q_dazuozq(value: int): void{
			this._q_dazuozq = value;
		}
		
		/**
		 * get 打坐获得生命值
		 * @return 
		 */
		public function get q_dazuohp(): int{
			return _q_dazuohp;
		}
		
		/**
		 * set 打坐获得生命值
		 */
		public function set q_dazuohp(value: int): void{
			this._q_dazuohp = value;
		}
		
		/**
		 * get 打坐获得内力值
		 * @return 
		 */
		public function get q_dazuomp(): int{
			return _q_dazuomp;
		}
		
		/**
		 * set 打坐获得内力值
		 */
		public function set q_dazuomp(value: int): void{
			this._q_dazuomp = value;
		}
		
		/**
		 * get 打坐获得体力值
		 * @return 
		 */
		public function get q_dazuosp(): int{
			return _q_dazuosp;
		}
		
		/**
		 * set 打坐获得体力值
		 */
		public function set q_dazuosp(value: int): void{
			this._q_dazuosp = value;
		}
		
		/**
		 * get 白天打坐暴击概率
		 * @return 
		 */
		public function get q_eurpt_day_prob(): int{
			return _q_eurpt_day_prob;
		}
		
		/**
		 * set 白天打坐暴击概率
		 */
		public function set q_eurpt_day_prob(value: int): void{
			this._q_eurpt_day_prob = value;
		}
		
		/**
		 * get 白天打坐暴击收益经验
		 * @return 
		 */
		public function get q_erupt_day_exp(): int{
			return _q_erupt_day_exp;
		}
		
		/**
		 * set 白天打坐暴击收益经验
		 */
		public function set q_erupt_day_exp(value: int): void{
			this._q_erupt_day_exp = value;
		}
		
		/**
		 * get 白天打坐暴击收益真气
		 * @return 
		 */
		public function get q_erupt_day_zq(): int{
			return _q_erupt_day_zq;
		}
		
		/**
		 * set 白天打坐暴击收益真气
		 */
		public function set q_erupt_day_zq(value: int): void{
			this._q_erupt_day_zq = value;
		}
		
		/**
		 * get 夜晚打坐暴击概率
		 * @return 
		 */
		public function get q_erupt_night_prob(): int{
			return _q_erupt_night_prob;
		}
		
		/**
		 * set 夜晚打坐暴击概率
		 */
		public function set q_erupt_night_prob(value: int): void{
			this._q_erupt_night_prob = value;
		}
		
		/**
		 * get 夜晚打坐暴击收益经验
		 * @return 
		 */
		public function get q_erupt_night_exp(): int{
			return _q_erupt_night_exp;
		}
		
		/**
		 * set 夜晚打坐暴击收益经验
		 */
		public function set q_erupt_night_exp(value: int): void{
			this._q_erupt_night_exp = value;
		}
		
		/**
		 * get 夜晚打坐暴击收益真气
		 * @return 
		 */
		public function get q_erupt_night_zq(): int{
			return _q_erupt_night_zq;
		}
		
		/**
		 * set 夜晚打坐暴击收益真气
		 */
		public function set q_erupt_night_zq(value: int): void{
			this._q_erupt_night_zq = value;
		}
		
		/**
		 * get 宠物双修获得经验
		 * @return 
		 */
		public function get q_pet_sxexp(): int{
			return _q_pet_sxexp;
		}
		
		/**
		 * set 宠物双修获得经验
		 */
		public function set q_pet_sxexp(value: int): void{
			this._q_pet_sxexp = value;
		}
		
		/**
		 * get 宠物双修获得真气
		 * @return 
		 */
		public function get q_pet_sxzq(): int{
			return _q_pet_sxzq;
		}
		
		/**
		 * set 宠物双修获得真气
		 */
		public function set q_pet_sxzq(value: int): void{
			this._q_pet_sxzq = value;
		}
		
		/**
		 * get 宠物双修获得生命值
		 * @return 
		 */
		public function get q_pet_sxhp(): int{
			return _q_pet_sxhp;
		}
		
		/**
		 * set 宠物双修获得生命值
		 */
		public function set q_pet_sxhp(value: int): void{
			this._q_pet_sxhp = value;
		}
		
		/**
		 * get 宠物双修获得内力值
		 * @return 
		 */
		public function get q_pet_sxmp(): int{
			return _q_pet_sxmp;
		}
		
		/**
		 * set 宠物双修获得内力值
		 */
		public function set q_pet_sxmp(value: int): void{
			this._q_pet_sxmp = value;
		}
		
		/**
		 * get 宠物双修获得体力值
		 * @return 
		 */
		public function get q_pet_sxsp(): int{
			return _q_pet_sxsp;
		}
		
		/**
		 * set 宠物双修获得体力值
		 */
		public function set q_pet_sxsp(value: int): void{
			this._q_pet_sxsp = value;
		}
		
		/**
		 * get 宠物双修白天暴击概率
		 * @return 
		 */
		public function get q_petdaysx_prob(): int{
			return _q_petdaysx_prob;
		}
		
		/**
		 * set 宠物双修白天暴击概率
		 */
		public function set q_petdaysx_prob(value: int): void{
			this._q_petdaysx_prob = value;
		}
		
		/**
		 * get 宠物双修白天暴击收益经验
		 * @return 
		 */
		public function get q_petdaysx_exp(): int{
			return _q_petdaysx_exp;
		}
		
		/**
		 * set 宠物双修白天暴击收益经验
		 */
		public function set q_petdaysx_exp(value: int): void{
			this._q_petdaysx_exp = value;
		}
		
		/**
		 * get 宠物双修白天暴击收益真气
		 * @return 
		 */
		public function get q_petdaysx_zq(): int{
			return _q_petdaysx_zq;
		}
		
		/**
		 * set 宠物双修白天暴击收益真气
		 */
		public function set q_petdaysx_zq(value: int): void{
			this._q_petdaysx_zq = value;
		}
		
		/**
		 * get 宠物双修夜晚暴击概率
		 * @return 
		 */
		public function get q_petnightsx_prob(): int{
			return _q_petnightsx_prob;
		}
		
		/**
		 * set 宠物双修夜晚暴击概率
		 */
		public function set q_petnightsx_prob(value: int): void{
			this._q_petnightsx_prob = value;
		}
		
		/**
		 * get 宠物双修打坐收益经验
		 * @return 
		 */
		public function get q_petnightsx_exp(): int{
			return _q_petnightsx_exp;
		}
		
		/**
		 * set 宠物双修打坐收益经验
		 */
		public function set q_petnightsx_exp(value: int): void{
			this._q_petnightsx_exp = value;
		}
		
		/**
		 * get 宠物双修夜晚暴击收益真气
		 * @return 
		 */
		public function get q_petnightsx_zq(): int{
			return _q_petnightsx_zq;
		}
		
		/**
		 * set 宠物双修夜晚暴击收益真气
		 */
		public function set q_petnightsx_zq(value: int): void{
			this._q_petnightsx_zq = value;
		}
		
		/**
		 * get 玩家双修获得经验
		 * @return 
		 */
		public function get q_role_sxexp(): int{
			return _q_role_sxexp;
		}
		
		/**
		 * set 玩家双修获得经验
		 */
		public function set q_role_sxexp(value: int): void{
			this._q_role_sxexp = value;
		}
		
		/**
		 * get 玩家双修获得真气
		 * @return 
		 */
		public function get q_role_sxzq(): int{
			return _q_role_sxzq;
		}
		
		/**
		 * set 玩家双修获得真气
		 */
		public function set q_role_sxzq(value: int): void{
			this._q_role_sxzq = value;
		}
		
		/**
		 * get 玩家双修获得生命值
		 * @return 
		 */
		public function get q_role_sxhp(): int{
			return _q_role_sxhp;
		}
		
		/**
		 * set 玩家双修获得生命值
		 */
		public function set q_role_sxhp(value: int): void{
			this._q_role_sxhp = value;
		}
		
		/**
		 * get 玩家双修获得内力值
		 * @return 
		 */
		public function get q_role_sxmp(): int{
			return _q_role_sxmp;
		}
		
		/**
		 * set 玩家双修获得内力值
		 */
		public function set q_role_sxmp(value: int): void{
			this._q_role_sxmp = value;
		}
		
		/**
		 * get 玩家双修获得体力值
		 * @return 
		 */
		public function get q_role_sxsp(): int{
			return _q_role_sxsp;
		}
		
		/**
		 * set 玩家双修获得体力值
		 */
		public function set q_role_sxsp(value: int): void{
			this._q_role_sxsp = value;
		}
		
		/**
		 * get 玩家双修白天暴击概率
		 * @return 
		 */
		public function get q_roledaysx_prob(): int{
			return _q_roledaysx_prob;
		}
		
		/**
		 * set 玩家双修白天暴击概率
		 */
		public function set q_roledaysx_prob(value: int): void{
			this._q_roledaysx_prob = value;
		}
		
		/**
		 * get 玩家双修白天暴击收益经验
		 * @return 
		 */
		public function get q_roledaysx_exp(): int{
			return _q_roledaysx_exp;
		}
		
		/**
		 * set 玩家双修白天暴击收益经验
		 */
		public function set q_roledaysx_exp(value: int): void{
			this._q_roledaysx_exp = value;
		}
		
		/**
		 * get 玩家双修白天暴击收益真气
		 * @return 
		 */
		public function get q_roledaysx_zq(): int{
			return _q_roledaysx_zq;
		}
		
		/**
		 * set 玩家双修白天暴击收益真气
		 */
		public function set q_roledaysx_zq(value: int): void{
			this._q_roledaysx_zq = value;
		}
		
		/**
		 * get 玩家双修夜晚暴击概率
		 * @return 
		 */
		public function get q_rolenightsx_prob(): int{
			return _q_rolenightsx_prob;
		}
		
		/**
		 * set 玩家双修夜晚暴击概率
		 */
		public function set q_rolenightsx_prob(value: int): void{
			this._q_rolenightsx_prob = value;
		}
		
		/**
		 * get 玩家双修打坐收益经验
		 * @return 
		 */
		public function get q_rolenightsx_exp(): int{
			return _q_rolenightsx_exp;
		}
		
		/**
		 * set 玩家双修打坐收益经验
		 */
		public function set q_rolenightsx_exp(value: int): void{
			this._q_rolenightsx_exp = value;
		}
		
		/**
		 * get 玩家双修夜晚暴击收益真气
		 * @return 
		 */
		public function get q_rolenightsx_zq(): int{
			return _q_rolenightsx_zq;
		}
		
		/**
		 * set 玩家双修夜晚暴击收益真气
		 */
		public function set q_rolenightsx_zq(value: int): void{
			this._q_rolenightsx_zq = value;
		}
		
		/**
		 * get 浇灌个人神树干旱果实增加经验
		 * @return 
		 */
		public function get q_spirittree_arid_exp(): int{
			return _q_spirittree_arid_exp;
		}
		
		/**
		 * set 浇灌个人神树干旱果实增加经验
		 */
		public function set q_spirittree_arid_exp(value: int): void{
			this._q_spirittree_arid_exp = value;
		}
		
		/**
		 * get 浇灌仙露成功增加经验
		 * @return 
		 */
		public function get q_spirittree_dew_exp(): int{
			return _q_spirittree_dew_exp;
		}
		
		/**
		 * set 浇灌仙露成功增加经验
		 */
		public function set q_spirittree_dew_exp(value: int): void{
			this._q_spirittree_dew_exp = value;
		}
		
		/**
		 * get 神树系统收获每日经验上限
		 * @return 
		 */
		public function get q_spirittree_exp_limit(): int{
			return _q_spirittree_exp_limit;
		}
		
		/**
		 * set 神树系统收获每日经验上限
		 */
		public function set q_spirittree_exp_limit(value: int): void{
			this._q_spirittree_exp_limit = value;
		}
		
		/**
		 * get 对boss连击加成攻击
		 * @return 
		 */
		public function get q_batter_atk(): int{
			return _q_batter_atk;
		}
		
		/**
		 * set 对boss连击加成攻击
		 */
		public function set q_batter_atk(value: int): void{
			this._q_batter_atk = value;
		}
		
		/**
		 * get 经验奖励基础值
		 * @return 
		 */
		public function get q_basis_exp(): int{
			return _q_basis_exp;
		}
		
		/**
		 * set 经验奖励基础值
		 */
		public function set q_basis_exp(value: int): void{
			this._q_basis_exp = value;
		}
		
		/**
		 * get 铜币奖励基础值
		 * @return 
		 */
		public function get q_basis_money(): int{
			return _q_basis_money;
		}
		
		/**
		 * set 铜币奖励基础值
		 */
		public function set q_basis_money(value: int): void{
			this._q_basis_money = value;
		}
		
		/**
		 * get 真气奖励基础值
		 * @return 
		 */
		public function get q_basis_zhenqi(): int{
			return _q_basis_zhenqi;
		}
		
		/**
		 * set 真气奖励基础值
		 */
		public function set q_basis_zhenqi(value: int): void{
			this._q_basis_zhenqi = value;
		}
		
		/**
		 * get 礼金奖励基础值
		 * @return 
		 */
		public function get q_basis_bindgold(): int{
			return _q_basis_bindgold;
		}
		
		/**
		 * set 礼金奖励基础值
		 */
		public function set q_basis_bindgold(value: int): void{
			this._q_basis_bindgold = value;
		}
		
		/**
		 * get 自动回复生命值
		 * @return 
		 */
		public function get q_auto_recover_hp(): int{
			return _q_auto_recover_hp;
		}
		
		/**
		 * set 自动回复生命值
		 */
		public function set q_auto_recover_hp(value: int): void{
			this._q_auto_recover_hp = value;
		}
		
		/**
		 * get 自动回复内力值
		 * @return 
		 */
		public function get q_auto_recover_mp(): int{
			return _q_auto_recover_mp;
		}
		
		/**
		 * set 自动回复内力值
		 */
		public function set q_auto_recover_mp(value: int): void{
			this._q_auto_recover_mp = value;
		}
		
		/**
		 * get 自动回复体力值
		 * @return 
		 */
		public function get q_auto_recover_sp(): int{
			return _q_auto_recover_sp;
		}
		
		/**
		 * set 自动回复体力值
		 */
		public function set q_auto_recover_sp(value: int): void{
			this._q_auto_recover_sp = value;
		}
		
	}
}