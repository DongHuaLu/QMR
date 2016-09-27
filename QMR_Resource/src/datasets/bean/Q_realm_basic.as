package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_realm_basic
	 */
	public class Q_realm_basic extends Bean{
	
		//境界阶数
		private var _q_id: int;
		
		//境界名称
		private var _q_name: String;
		
		//攻击上限(默认属性|上限属性）
		private var _q_attack_limit: String;
		
		//防御上限(默认属性|上限属性）
		private var _q_defence_limit: String;
		
		//暴击上限(默认属性|上限属性）
		private var _q_crit_limit: String;
		
		//闪避上限(默认属性|上限属性）
		private var _q_dodge_limit: String;
		
		//血量上限(默认属性|上限属性）
		private var _q_maxhp_limit: String;
		
		//内力上限(默认属性|上限属性）
		private var _q_maxmp_limit: String;
		
		//体力上限(默认属性|上限属性）
		private var _q_maxsp_limit: String;
		
		//攻速上限(默认属性|上限属性）
		private var _q_attackspeed_limit: String;
		
		//移速上限(默认属性|上限属性）
		private var _q_speed_limit: String;
		
		//忽视防御上限(默认属性|上限属性）
		private var _q_neg_defence_limit: String;
		
		//弓箭几率（万分比）(默认属性|上限属性）
		private var _q_arrow_probability_limit: String;
		
		//强化消耗真气
		private var _q_use_zhenqi: int;
		
		//面板显示强化成功几率描述，支持HTML
		private var _q_show_random: String;
		
		//真实强化成功几率（万分比）
		private var _q_true_random: int;
		
		//强化增加攻击
		private var _q_attack: int;
		
		//强化增加防御
		private var _q_defence: int;
		
		//强化增加暴击
		private var _q_crit: int;
		
		//强化增加闪避
		private var _q_dodge: int;
		
		//强化增加血量
		private var _q_maxhp: int;
		
		//强化增加内力
		private var _q_maxmp: int;
		
		//强化增加体力
		private var _q_maxsp: int;
		
		//强化增加攻速
		private var _q_attackspeed: int;
		
		//强化增加移速
		private var _q_speed: int;
		
		//强化增加忽视防御
		private var _q_neg_defence: int;
		
		//强化增加弓箭几率（万分比）
		private var _q_arrow_probability: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//境界阶数
			_q_id = readInt();
			//境界名称
			_q_name = readString();
			//攻击上限(默认属性|上限属性）
			_q_attack_limit = readString();
			//防御上限(默认属性|上限属性）
			_q_defence_limit = readString();
			//暴击上限(默认属性|上限属性）
			_q_crit_limit = readString();
			//闪避上限(默认属性|上限属性）
			_q_dodge_limit = readString();
			//血量上限(默认属性|上限属性）
			_q_maxhp_limit = readString();
			//内力上限(默认属性|上限属性）
			_q_maxmp_limit = readString();
			//体力上限(默认属性|上限属性）
			_q_maxsp_limit = readString();
			//攻速上限(默认属性|上限属性）
			_q_attackspeed_limit = readString();
			//移速上限(默认属性|上限属性）
			_q_speed_limit = readString();
			//忽视防御上限(默认属性|上限属性）
			_q_neg_defence_limit = readString();
			//弓箭几率（万分比）(默认属性|上限属性）
			_q_arrow_probability_limit = readString();
			//强化消耗真气
			_q_use_zhenqi = readInt();
			//面板显示强化成功几率描述，支持HTML
			_q_show_random = readString();
			//真实强化成功几率（万分比）
			_q_true_random = readInt();
			//强化增加攻击
			_q_attack = readInt();
			//强化增加防御
			_q_defence = readInt();
			//强化增加暴击
			_q_crit = readInt();
			//强化增加闪避
			_q_dodge = readInt();
			//强化增加血量
			_q_maxhp = readInt();
			//强化增加内力
			_q_maxmp = readInt();
			//强化增加体力
			_q_maxsp = readInt();
			//强化增加攻速
			_q_attackspeed = readInt();
			//强化增加移速
			_q_speed = readInt();
			//强化增加忽视防御
			_q_neg_defence = readInt();
			//强化增加弓箭几率（万分比）
			_q_arrow_probability = readInt();
			return true;
		}
		
		/**
		 * get 境界阶数
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 境界阶数
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 境界名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 境界名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 攻击上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_attack_limit(): String{
			return _q_attack_limit;
		}
		
		/**
		 * set 攻击上限(默认属性|上限属性）
		 */
		public function set q_attack_limit(value: String): void{
			this._q_attack_limit = value;
		}
		
		/**
		 * get 防御上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_defence_limit(): String{
			return _q_defence_limit;
		}
		
		/**
		 * set 防御上限(默认属性|上限属性）
		 */
		public function set q_defence_limit(value: String): void{
			this._q_defence_limit = value;
		}
		
		/**
		 * get 暴击上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_crit_limit(): String{
			return _q_crit_limit;
		}
		
		/**
		 * set 暴击上限(默认属性|上限属性）
		 */
		public function set q_crit_limit(value: String): void{
			this._q_crit_limit = value;
		}
		
		/**
		 * get 闪避上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_dodge_limit(): String{
			return _q_dodge_limit;
		}
		
		/**
		 * set 闪避上限(默认属性|上限属性）
		 */
		public function set q_dodge_limit(value: String): void{
			this._q_dodge_limit = value;
		}
		
		/**
		 * get 血量上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_maxhp_limit(): String{
			return _q_maxhp_limit;
		}
		
		/**
		 * set 血量上限(默认属性|上限属性）
		 */
		public function set q_maxhp_limit(value: String): void{
			this._q_maxhp_limit = value;
		}
		
		/**
		 * get 内力上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_maxmp_limit(): String{
			return _q_maxmp_limit;
		}
		
		/**
		 * set 内力上限(默认属性|上限属性）
		 */
		public function set q_maxmp_limit(value: String): void{
			this._q_maxmp_limit = value;
		}
		
		/**
		 * get 体力上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_maxsp_limit(): String{
			return _q_maxsp_limit;
		}
		
		/**
		 * set 体力上限(默认属性|上限属性）
		 */
		public function set q_maxsp_limit(value: String): void{
			this._q_maxsp_limit = value;
		}
		
		/**
		 * get 攻速上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_attackspeed_limit(): String{
			return _q_attackspeed_limit;
		}
		
		/**
		 * set 攻速上限(默认属性|上限属性）
		 */
		public function set q_attackspeed_limit(value: String): void{
			this._q_attackspeed_limit = value;
		}
		
		/**
		 * get 移速上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_speed_limit(): String{
			return _q_speed_limit;
		}
		
		/**
		 * set 移速上限(默认属性|上限属性）
		 */
		public function set q_speed_limit(value: String): void{
			this._q_speed_limit = value;
		}
		
		/**
		 * get 忽视防御上限(默认属性|上限属性）
		 * @return 
		 */
		public function get q_neg_defence_limit(): String{
			return _q_neg_defence_limit;
		}
		
		/**
		 * set 忽视防御上限(默认属性|上限属性）
		 */
		public function set q_neg_defence_limit(value: String): void{
			this._q_neg_defence_limit = value;
		}
		
		/**
		 * get 弓箭几率（万分比）(默认属性|上限属性）
		 * @return 
		 */
		public function get q_arrow_probability_limit(): String{
			return _q_arrow_probability_limit;
		}
		
		/**
		 * set 弓箭几率（万分比）(默认属性|上限属性）
		 */
		public function set q_arrow_probability_limit(value: String): void{
			this._q_arrow_probability_limit = value;
		}
		
		/**
		 * get 强化消耗真气
		 * @return 
		 */
		public function get q_use_zhenqi(): int{
			return _q_use_zhenqi;
		}
		
		/**
		 * set 强化消耗真气
		 */
		public function set q_use_zhenqi(value: int): void{
			this._q_use_zhenqi = value;
		}
		
		/**
		 * get 面板显示强化成功几率描述，支持HTML
		 * @return 
		 */
		public function get q_show_random(): String{
			return _q_show_random;
		}
		
		/**
		 * set 面板显示强化成功几率描述，支持HTML
		 */
		public function set q_show_random(value: String): void{
			this._q_show_random = value;
		}
		
		/**
		 * get 真实强化成功几率（万分比）
		 * @return 
		 */
		public function get q_true_random(): int{
			return _q_true_random;
		}
		
		/**
		 * set 真实强化成功几率（万分比）
		 */
		public function set q_true_random(value: int): void{
			this._q_true_random = value;
		}
		
		/**
		 * get 强化增加攻击
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 强化增加攻击
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 强化增加防御
		 * @return 
		 */
		public function get q_defence(): int{
			return _q_defence;
		}
		
		/**
		 * set 强化增加防御
		 */
		public function set q_defence(value: int): void{
			this._q_defence = value;
		}
		
		/**
		 * get 强化增加暴击
		 * @return 
		 */
		public function get q_crit(): int{
			return _q_crit;
		}
		
		/**
		 * set 强化增加暴击
		 */
		public function set q_crit(value: int): void{
			this._q_crit = value;
		}
		
		/**
		 * get 强化增加闪避
		 * @return 
		 */
		public function get q_dodge(): int{
			return _q_dodge;
		}
		
		/**
		 * set 强化增加闪避
		 */
		public function set q_dodge(value: int): void{
			this._q_dodge = value;
		}
		
		/**
		 * get 强化增加血量
		 * @return 
		 */
		public function get q_maxhp(): int{
			return _q_maxhp;
		}
		
		/**
		 * set 强化增加血量
		 */
		public function set q_maxhp(value: int): void{
			this._q_maxhp = value;
		}
		
		/**
		 * get 强化增加内力
		 * @return 
		 */
		public function get q_maxmp(): int{
			return _q_maxmp;
		}
		
		/**
		 * set 强化增加内力
		 */
		public function set q_maxmp(value: int): void{
			this._q_maxmp = value;
		}
		
		/**
		 * get 强化增加体力
		 * @return 
		 */
		public function get q_maxsp(): int{
			return _q_maxsp;
		}
		
		/**
		 * set 强化增加体力
		 */
		public function set q_maxsp(value: int): void{
			this._q_maxsp = value;
		}
		
		/**
		 * get 强化增加攻速
		 * @return 
		 */
		public function get q_attackspeed(): int{
			return _q_attackspeed;
		}
		
		/**
		 * set 强化增加攻速
		 */
		public function set q_attackspeed(value: int): void{
			this._q_attackspeed = value;
		}
		
		/**
		 * get 强化增加移速
		 * @return 
		 */
		public function get q_speed(): int{
			return _q_speed;
		}
		
		/**
		 * set 强化增加移速
		 */
		public function set q_speed(value: int): void{
			this._q_speed = value;
		}
		
		/**
		 * get 强化增加忽视防御
		 * @return 
		 */
		public function get q_neg_defence(): int{
			return _q_neg_defence;
		}
		
		/**
		 * set 强化增加忽视防御
		 */
		public function set q_neg_defence(value: int): void{
			this._q_neg_defence = value;
		}
		
		/**
		 * get 强化增加弓箭几率（万分比）
		 * @return 
		 */
		public function get q_arrow_probability(): int{
			return _q_arrow_probability;
		}
		
		/**
		 * set 强化增加弓箭几率（万分比）
		 */
		public function set q_arrow_probability(value: int): void{
			this._q_arrow_probability = value;
		}
		
	}
}