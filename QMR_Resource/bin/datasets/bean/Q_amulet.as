package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_amulet
	 */
	public class Q_amulet extends Bean{
	
		//护身符ID(比如1阶0星就填 10,3阶4星就填34)
		private var _q_model_id: int;
		
		//护身符阶数
		private var _q_layer: int;
		
		//护身符星级
		private var _q_star: int;
		
		//护身符文字名称(格式：0_XXXX;1_XXXX;2_XXXXX;3_XXXXX)
		private var _q_name: String;
		
		//护身符名称美术资源编号
		private var _q_name_src: String;
		
		//面板造型资源编号(格式：
		private var _q_panel_src: String;
		
		//场景造型资源编号(格式：
		private var _q_scene_src: String;
		
		//人物属性面板手持面板造型资源
		private var _q_main_panel_src: String;
		
		//护身符战力值
		private var _q_fight_power: int;
		
		//开启技能格数
		private var _q_skill_num: int;
		
		//锻造进度是否会回退
		private var _q_clear_flag: int;
		
		//回退倒计时时间（秒）
		private var _q_clear_sec: int;
		
		//锻造所需人物等级
		private var _q_need_level: int;
		
		//锻造消耗材料（格式：ID_数量;ID_数量)
		private var _q_need_item_str: String;
		
		//锻造消耗铜钱
		private var _q_need_money: int;
		
		//锻造成功所需次数min
		private var _q_times_min: int;
		
		//服务器端计算用锻造成功几率（万分比）
		private var _q_prob: int;
		
		//锻造成功所需次数max
		private var _q_times_max: int;
		
		//每次锻造成功增加锻造进度万分比下限（本处填分子）
		private var _q_add_bless_min: int;
		
		//每次锻造成功增加锻造进度万分比上限（本处填分子）
		private var _q_add_bless_max: int;
		
		//锻造成功后进阶至护身符ID
		private var _q_next_model: int;
		
		//进阶成功是否全服公告（0不公告，1公告）
		private var _q_notify_flag: int;
		
		//生命加成
		private var _q_hp: int;
		
		//攻击
		private var _q_attack: int;
		
		//防御
		private var _q_defence: int;
		
		//暴击
		private var _q_crit: int;
		
		//闪避
		private var _q_avoid: int;
		
		//内力
		private var _q_mp: int;
		
		//体力
		private var _q_sp: int;
		
		//进阶道具在商城中隐藏(0:不隐藏 1:隐藏)
		private var _q_shop_flag: int;
		
		//增加经验
		private var _q_add_exp: int;
		
		//增加经验上限
		private var _q_add_exp_max: int;
		
		//护身符最大星数
		private var _q_maxstar: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//护身符ID(比如1阶0星就填 10,3阶4星就填34)
			_q_model_id = readInt();
			//护身符阶数
			_q_layer = readInt();
			//护身符星级
			_q_star = readInt();
			//护身符文字名称(格式：0_XXXX;1_XXXX;2_XXXXX;3_XXXXX)
			_q_name = readString();
			//护身符名称美术资源编号
			_q_name_src = readString();
			//面板造型资源编号(格式：
			_q_panel_src = readString();
			//场景造型资源编号(格式：
			_q_scene_src = readString();
			//人物属性面板手持面板造型资源
			_q_main_panel_src = readString();
			//护身符战力值
			_q_fight_power = readInt();
			//开启技能格数
			_q_skill_num = readInt();
			//锻造进度是否会回退
			_q_clear_flag = readInt();
			//回退倒计时时间（秒）
			_q_clear_sec = readInt();
			//锻造所需人物等级
			_q_need_level = readInt();
			//锻造消耗材料（格式：ID_数量;ID_数量)
			_q_need_item_str = readString();
			//锻造消耗铜钱
			_q_need_money = readInt();
			//锻造成功所需次数min
			_q_times_min = readInt();
			//服务器端计算用锻造成功几率（万分比）
			_q_prob = readInt();
			//锻造成功所需次数max
			_q_times_max = readInt();
			//每次锻造成功增加锻造进度万分比下限（本处填分子）
			_q_add_bless_min = readInt();
			//每次锻造成功增加锻造进度万分比上限（本处填分子）
			_q_add_bless_max = readInt();
			//锻造成功后进阶至护身符ID
			_q_next_model = readInt();
			//进阶成功是否全服公告（0不公告，1公告）
			_q_notify_flag = readInt();
			//生命加成
			_q_hp = readInt();
			//攻击
			_q_attack = readInt();
			//防御
			_q_defence = readInt();
			//暴击
			_q_crit = readInt();
			//闪避
			_q_avoid = readInt();
			//内力
			_q_mp = readInt();
			//体力
			_q_sp = readInt();
			//进阶道具在商城中隐藏(0:不隐藏 1:隐藏)
			_q_shop_flag = readInt();
			//增加经验
			_q_add_exp = readInt();
			//增加经验上限
			_q_add_exp_max = readInt();
			//护身符最大星数
			_q_maxstar = readInt();
			return true;
		}
		
		/**
		 * get 护身符ID(比如1阶0星就填 10,3阶4星就填34)
		 * @return 
		 */
		public function get q_model_id(): int{
			return _q_model_id;
		}
		
		/**
		 * set 护身符ID(比如1阶0星就填 10,3阶4星就填34)
		 */
		public function set q_model_id(value: int): void{
			this._q_model_id = value;
		}
		
		/**
		 * get 护身符阶数
		 * @return 
		 */
		public function get q_layer(): int{
			return _q_layer;
		}
		
		/**
		 * set 护身符阶数
		 */
		public function set q_layer(value: int): void{
			this._q_layer = value;
		}
		
		/**
		 * get 护身符星级
		 * @return 
		 */
		public function get q_star(): int{
			return _q_star;
		}
		
		/**
		 * set 护身符星级
		 */
		public function set q_star(value: int): void{
			this._q_star = value;
		}
		
		/**
		 * get 护身符文字名称(格式：0_XXXX;1_XXXX;2_XXXXX;3_XXXXX)
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 护身符文字名称(格式：0_XXXX;1_XXXX;2_XXXXX;3_XXXXX)
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 护身符名称美术资源编号
		 * @return 
		 */
		public function get q_name_src(): String{
			return _q_name_src;
		}
		
		/**
		 * set 护身符名称美术资源编号
		 */
		public function set q_name_src(value: String): void{
			this._q_name_src = value;
		}
		
		/**
		 * get 面板造型资源编号(格式：
		 * @return 
		 */
		public function get q_panel_src(): String{
			return _q_panel_src;
		}
		
		/**
		 * set 面板造型资源编号(格式：
		 */
		public function set q_panel_src(value: String): void{
			this._q_panel_src = value;
		}
		
		/**
		 * get 场景造型资源编号(格式：
		 * @return 
		 */
		public function get q_scene_src(): String{
			return _q_scene_src;
		}
		
		/**
		 * set 场景造型资源编号(格式：
		 */
		public function set q_scene_src(value: String): void{
			this._q_scene_src = value;
		}
		
		/**
		 * get 人物属性面板手持面板造型资源
		 * @return 
		 */
		public function get q_main_panel_src(): String{
			return _q_main_panel_src;
		}
		
		/**
		 * set 人物属性面板手持面板造型资源
		 */
		public function set q_main_panel_src(value: String): void{
			this._q_main_panel_src = value;
		}
		
		/**
		 * get 护身符战力值
		 * @return 
		 */
		public function get q_fight_power(): int{
			return _q_fight_power;
		}
		
		/**
		 * set 护身符战力值
		 */
		public function set q_fight_power(value: int): void{
			this._q_fight_power = value;
		}
		
		/**
		 * get 开启技能格数
		 * @return 
		 */
		public function get q_skill_num(): int{
			return _q_skill_num;
		}
		
		/**
		 * set 开启技能格数
		 */
		public function set q_skill_num(value: int): void{
			this._q_skill_num = value;
		}
		
		/**
		 * get 锻造进度是否会回退
		 * @return 
		 */
		public function get q_clear_flag(): int{
			return _q_clear_flag;
		}
		
		/**
		 * set 锻造进度是否会回退
		 */
		public function set q_clear_flag(value: int): void{
			this._q_clear_flag = value;
		}
		
		/**
		 * get 回退倒计时时间（秒）
		 * @return 
		 */
		public function get q_clear_sec(): int{
			return _q_clear_sec;
		}
		
		/**
		 * set 回退倒计时时间（秒）
		 */
		public function set q_clear_sec(value: int): void{
			this._q_clear_sec = value;
		}
		
		/**
		 * get 锻造所需人物等级
		 * @return 
		 */
		public function get q_need_level(): int{
			return _q_need_level;
		}
		
		/**
		 * set 锻造所需人物等级
		 */
		public function set q_need_level(value: int): void{
			this._q_need_level = value;
		}
		
		/**
		 * get 锻造消耗材料（格式：ID_数量;ID_数量)
		 * @return 
		 */
		public function get q_need_item_str(): String{
			return _q_need_item_str;
		}
		
		/**
		 * set 锻造消耗材料（格式：ID_数量;ID_数量)
		 */
		public function set q_need_item_str(value: String): void{
			this._q_need_item_str = value;
		}
		
		/**
		 * get 锻造消耗铜钱
		 * @return 
		 */
		public function get q_need_money(): int{
			return _q_need_money;
		}
		
		/**
		 * set 锻造消耗铜钱
		 */
		public function set q_need_money(value: int): void{
			this._q_need_money = value;
		}
		
		/**
		 * get 锻造成功所需次数min
		 * @return 
		 */
		public function get q_times_min(): int{
			return _q_times_min;
		}
		
		/**
		 * set 锻造成功所需次数min
		 */
		public function set q_times_min(value: int): void{
			this._q_times_min = value;
		}
		
		/**
		 * get 服务器端计算用锻造成功几率（万分比）
		 * @return 
		 */
		public function get q_prob(): int{
			return _q_prob;
		}
		
		/**
		 * set 服务器端计算用锻造成功几率（万分比）
		 */
		public function set q_prob(value: int): void{
			this._q_prob = value;
		}
		
		/**
		 * get 锻造成功所需次数max
		 * @return 
		 */
		public function get q_times_max(): int{
			return _q_times_max;
		}
		
		/**
		 * set 锻造成功所需次数max
		 */
		public function set q_times_max(value: int): void{
			this._q_times_max = value;
		}
		
		/**
		 * get 每次锻造成功增加锻造进度万分比下限（本处填分子）
		 * @return 
		 */
		public function get q_add_bless_min(): int{
			return _q_add_bless_min;
		}
		
		/**
		 * set 每次锻造成功增加锻造进度万分比下限（本处填分子）
		 */
		public function set q_add_bless_min(value: int): void{
			this._q_add_bless_min = value;
		}
		
		/**
		 * get 每次锻造成功增加锻造进度万分比上限（本处填分子）
		 * @return 
		 */
		public function get q_add_bless_max(): int{
			return _q_add_bless_max;
		}
		
		/**
		 * set 每次锻造成功增加锻造进度万分比上限（本处填分子）
		 */
		public function set q_add_bless_max(value: int): void{
			this._q_add_bless_max = value;
		}
		
		/**
		 * get 锻造成功后进阶至护身符ID
		 * @return 
		 */
		public function get q_next_model(): int{
			return _q_next_model;
		}
		
		/**
		 * set 锻造成功后进阶至护身符ID
		 */
		public function set q_next_model(value: int): void{
			this._q_next_model = value;
		}
		
		/**
		 * get 进阶成功是否全服公告（0不公告，1公告）
		 * @return 
		 */
		public function get q_notify_flag(): int{
			return _q_notify_flag;
		}
		
		/**
		 * set 进阶成功是否全服公告（0不公告，1公告）
		 */
		public function set q_notify_flag(value: int): void{
			this._q_notify_flag = value;
		}
		
		/**
		 * get 生命加成
		 * @return 
		 */
		public function get q_hp(): int{
			return _q_hp;
		}
		
		/**
		 * set 生命加成
		 */
		public function set q_hp(value: int): void{
			this._q_hp = value;
		}
		
		/**
		 * get 攻击
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 攻击
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 防御
		 * @return 
		 */
		public function get q_defence(): int{
			return _q_defence;
		}
		
		/**
		 * set 防御
		 */
		public function set q_defence(value: int): void{
			this._q_defence = value;
		}
		
		/**
		 * get 暴击
		 * @return 
		 */
		public function get q_crit(): int{
			return _q_crit;
		}
		
		/**
		 * set 暴击
		 */
		public function set q_crit(value: int): void{
			this._q_crit = value;
		}
		
		/**
		 * get 闪避
		 * @return 
		 */
		public function get q_avoid(): int{
			return _q_avoid;
		}
		
		/**
		 * set 闪避
		 */
		public function set q_avoid(value: int): void{
			this._q_avoid = value;
		}
		
		/**
		 * get 内力
		 * @return 
		 */
		public function get q_mp(): int{
			return _q_mp;
		}
		
		/**
		 * set 内力
		 */
		public function set q_mp(value: int): void{
			this._q_mp = value;
		}
		
		/**
		 * get 体力
		 * @return 
		 */
		public function get q_sp(): int{
			return _q_sp;
		}
		
		/**
		 * set 体力
		 */
		public function set q_sp(value: int): void{
			this._q_sp = value;
		}
		
		/**
		 * get 进阶道具在商城中隐藏(0:不隐藏 1:隐藏)
		 * @return 
		 */
		public function get q_shop_flag(): int{
			return _q_shop_flag;
		}
		
		/**
		 * set 进阶道具在商城中隐藏(0:不隐藏 1:隐藏)
		 */
		public function set q_shop_flag(value: int): void{
			this._q_shop_flag = value;
		}
		
		/**
		 * get 增加经验
		 * @return 
		 */
		public function get q_add_exp(): int{
			return _q_add_exp;
		}
		
		/**
		 * set 增加经验
		 */
		public function set q_add_exp(value: int): void{
			this._q_add_exp = value;
		}
		
		/**
		 * get 增加经验上限
		 * @return 
		 */
		public function get q_add_exp_max(): int{
			return _q_add_exp_max;
		}
		
		/**
		 * set 增加经验上限
		 */
		public function set q_add_exp_max(value: int): void{
			this._q_add_exp_max = value;
		}
		
		/**
		 * get 护身符最大星数
		 * @return 
		 */
		public function get q_maxstar(): int{
			return _q_maxstar;
		}
		
		/**
		 * set 护身符最大星数
		 */
		public function set q_maxstar(value: int): void{
			this._q_maxstar = value;
		}
		
	}
}