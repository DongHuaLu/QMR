package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_hiddenweapon
	 */
	public class Q_hiddenweapon extends Bean{
	
		//暗器阶数
		private var _q_rank: int;
		
		//暗器名称
		private var _q_name: String;
		
		//名字颜色，支持html语法
		private var _q_color: String;
		
		//暗器面板展示用造型资源编号
		private var _q_res_panelid: int;
		
		//暗器技能ID（0：无技能，组合技能1的ID;组合技能2的ID;组合技能3的ID;组合技能4的ID;组合技能5的ID;）
		private var _q_skillid: int;
		
		//成长潜力值
		private var _q_potential: int;
		
		//进阶所需人物等级
		private var _q_need_level: int;
		
		//进阶消费铜币数量
		private var _q_need_money: int;
		
		//每次进阶所需消耗材料ID
		private var _q_need_modelid: int;
		
		//每次消耗材料数量
		private var _q_item_num: int;
		
		//进阶成功所需进阶次数min
		private var _q_up_num_min: int;
		
		//进阶成功所需进阶次数max
		private var _q_up_num_max: int;
		
		//服务器端计算用进阶成功几率（万分比）
		private var _q_up_probability: int;
		
		//客户端显示用成功率（支持HTML）
		private var _q_up_clientshow_rate: String;
		
		//每次失败增加祝福值区间min
		private var _q_blessnum_min: int;
		
		//每次失败增加祝福值区间max
		private var _q_blessnum_max: int;
		
		//祝福值上限值
		private var _q_blessnum_limit: int;
		
		//每次失败常规几率及增加人物经验（格式：几率|经验）
		private var _q_normal_rnd: String;
		
		//每次失败小爆击几率及增加人物经验（格式：几率|经验）
		private var _q_small_crit_rnd: String;
		
		//每次失败大爆击几率及增加人物经验（格式：几率|经验）
		private var _q_large_crit_rnd: String;
		
		//进阶失败所获人物经验上限
		private var _q_up_fail_addexp: int;
		
		//进阶成功是否全服公告（0不公告，1公告）
		private var _q_is_announce: int;
		
		//暗器最大等级
		private var _q_max_level: int;
		
		//开放格子数
		private var _q_open_grid: int;
		
		//投掷次数
		private var _q_throw_times: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//暗器阶数
			_q_rank = readInt();
			//暗器名称
			_q_name = readString();
			//名字颜色，支持html语法
			_q_color = readString();
			//暗器面板展示用造型资源编号
			_q_res_panelid = readInt();
			//暗器技能ID（0：无技能，组合技能1的ID;组合技能2的ID;组合技能3的ID;组合技能4的ID;组合技能5的ID;）
			_q_skillid = readInt();
			//成长潜力值
			_q_potential = readInt();
			//进阶所需人物等级
			_q_need_level = readInt();
			//进阶消费铜币数量
			_q_need_money = readInt();
			//每次进阶所需消耗材料ID
			_q_need_modelid = readInt();
			//每次消耗材料数量
			_q_item_num = readInt();
			//进阶成功所需进阶次数min
			_q_up_num_min = readInt();
			//进阶成功所需进阶次数max
			_q_up_num_max = readInt();
			//服务器端计算用进阶成功几率（万分比）
			_q_up_probability = readInt();
			//客户端显示用成功率（支持HTML）
			_q_up_clientshow_rate = readString();
			//每次失败增加祝福值区间min
			_q_blessnum_min = readInt();
			//每次失败增加祝福值区间max
			_q_blessnum_max = readInt();
			//祝福值上限值
			_q_blessnum_limit = readInt();
			//每次失败常规几率及增加人物经验（格式：几率|经验）
			_q_normal_rnd = readString();
			//每次失败小爆击几率及增加人物经验（格式：几率|经验）
			_q_small_crit_rnd = readString();
			//每次失败大爆击几率及增加人物经验（格式：几率|经验）
			_q_large_crit_rnd = readString();
			//进阶失败所获人物经验上限
			_q_up_fail_addexp = readInt();
			//进阶成功是否全服公告（0不公告，1公告）
			_q_is_announce = readInt();
			//暗器最大等级
			_q_max_level = readInt();
			//开放格子数
			_q_open_grid = readInt();
			//投掷次数
			_q_throw_times = readInt();
			return true;
		}
		
		/**
		 * get 暗器阶数
		 * @return 
		 */
		public function get q_rank(): int{
			return _q_rank;
		}
		
		/**
		 * set 暗器阶数
		 */
		public function set q_rank(value: int): void{
			this._q_rank = value;
		}
		
		/**
		 * get 暗器名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 暗器名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 名字颜色，支持html语法
		 * @return 
		 */
		public function get q_color(): String{
			return _q_color;
		}
		
		/**
		 * set 名字颜色，支持html语法
		 */
		public function set q_color(value: String): void{
			this._q_color = value;
		}
		
		/**
		 * get 暗器面板展示用造型资源编号
		 * @return 
		 */
		public function get q_res_panelid(): int{
			return _q_res_panelid;
		}
		
		/**
		 * set 暗器面板展示用造型资源编号
		 */
		public function set q_res_panelid(value: int): void{
			this._q_res_panelid = value;
		}
		
		/**
		 * get 暗器技能ID（0：无技能，组合技能1的ID;组合技能2的ID;组合技能3的ID;组合技能4的ID;组合技能5的ID;）
		 * @return 
		 */
		public function get q_skillid(): int{
			return _q_skillid;
		}
		
		/**
		 * set 暗器技能ID（0：无技能，组合技能1的ID;组合技能2的ID;组合技能3的ID;组合技能4的ID;组合技能5的ID;）
		 */
		public function set q_skillid(value: int): void{
			this._q_skillid = value;
		}
		
		/**
		 * get 成长潜力值
		 * @return 
		 */
		public function get q_potential(): int{
			return _q_potential;
		}
		
		/**
		 * set 成长潜力值
		 */
		public function set q_potential(value: int): void{
			this._q_potential = value;
		}
		
		/**
		 * get 进阶所需人物等级
		 * @return 
		 */
		public function get q_need_level(): int{
			return _q_need_level;
		}
		
		/**
		 * set 进阶所需人物等级
		 */
		public function set q_need_level(value: int): void{
			this._q_need_level = value;
		}
		
		/**
		 * get 进阶消费铜币数量
		 * @return 
		 */
		public function get q_need_money(): int{
			return _q_need_money;
		}
		
		/**
		 * set 进阶消费铜币数量
		 */
		public function set q_need_money(value: int): void{
			this._q_need_money = value;
		}
		
		/**
		 * get 每次进阶所需消耗材料ID
		 * @return 
		 */
		public function get q_need_modelid(): int{
			return _q_need_modelid;
		}
		
		/**
		 * set 每次进阶所需消耗材料ID
		 */
		public function set q_need_modelid(value: int): void{
			this._q_need_modelid = value;
		}
		
		/**
		 * get 每次消耗材料数量
		 * @return 
		 */
		public function get q_item_num(): int{
			return _q_item_num;
		}
		
		/**
		 * set 每次消耗材料数量
		 */
		public function set q_item_num(value: int): void{
			this._q_item_num = value;
		}
		
		/**
		 * get 进阶成功所需进阶次数min
		 * @return 
		 */
		public function get q_up_num_min(): int{
			return _q_up_num_min;
		}
		
		/**
		 * set 进阶成功所需进阶次数min
		 */
		public function set q_up_num_min(value: int): void{
			this._q_up_num_min = value;
		}
		
		/**
		 * get 进阶成功所需进阶次数max
		 * @return 
		 */
		public function get q_up_num_max(): int{
			return _q_up_num_max;
		}
		
		/**
		 * set 进阶成功所需进阶次数max
		 */
		public function set q_up_num_max(value: int): void{
			this._q_up_num_max = value;
		}
		
		/**
		 * get 服务器端计算用进阶成功几率（万分比）
		 * @return 
		 */
		public function get q_up_probability(): int{
			return _q_up_probability;
		}
		
		/**
		 * set 服务器端计算用进阶成功几率（万分比）
		 */
		public function set q_up_probability(value: int): void{
			this._q_up_probability = value;
		}
		
		/**
		 * get 客户端显示用成功率（支持HTML）
		 * @return 
		 */
		public function get q_up_clientshow_rate(): String{
			return _q_up_clientshow_rate;
		}
		
		/**
		 * set 客户端显示用成功率（支持HTML）
		 */
		public function set q_up_clientshow_rate(value: String): void{
			this._q_up_clientshow_rate = value;
		}
		
		/**
		 * get 每次失败增加祝福值区间min
		 * @return 
		 */
		public function get q_blessnum_min(): int{
			return _q_blessnum_min;
		}
		
		/**
		 * set 每次失败增加祝福值区间min
		 */
		public function set q_blessnum_min(value: int): void{
			this._q_blessnum_min = value;
		}
		
		/**
		 * get 每次失败增加祝福值区间max
		 * @return 
		 */
		public function get q_blessnum_max(): int{
			return _q_blessnum_max;
		}
		
		/**
		 * set 每次失败增加祝福值区间max
		 */
		public function set q_blessnum_max(value: int): void{
			this._q_blessnum_max = value;
		}
		
		/**
		 * get 祝福值上限值
		 * @return 
		 */
		public function get q_blessnum_limit(): int{
			return _q_blessnum_limit;
		}
		
		/**
		 * set 祝福值上限值
		 */
		public function set q_blessnum_limit(value: int): void{
			this._q_blessnum_limit = value;
		}
		
		/**
		 * get 每次失败常规几率及增加人物经验（格式：几率|经验）
		 * @return 
		 */
		public function get q_normal_rnd(): String{
			return _q_normal_rnd;
		}
		
		/**
		 * set 每次失败常规几率及增加人物经验（格式：几率|经验）
		 */
		public function set q_normal_rnd(value: String): void{
			this._q_normal_rnd = value;
		}
		
		/**
		 * get 每次失败小爆击几率及增加人物经验（格式：几率|经验）
		 * @return 
		 */
		public function get q_small_crit_rnd(): String{
			return _q_small_crit_rnd;
		}
		
		/**
		 * set 每次失败小爆击几率及增加人物经验（格式：几率|经验）
		 */
		public function set q_small_crit_rnd(value: String): void{
			this._q_small_crit_rnd = value;
		}
		
		/**
		 * get 每次失败大爆击几率及增加人物经验（格式：几率|经验）
		 * @return 
		 */
		public function get q_large_crit_rnd(): String{
			return _q_large_crit_rnd;
		}
		
		/**
		 * set 每次失败大爆击几率及增加人物经验（格式：几率|经验）
		 */
		public function set q_large_crit_rnd(value: String): void{
			this._q_large_crit_rnd = value;
		}
		
		/**
		 * get 进阶失败所获人物经验上限
		 * @return 
		 */
		public function get q_up_fail_addexp(): int{
			return _q_up_fail_addexp;
		}
		
		/**
		 * set 进阶失败所获人物经验上限
		 */
		public function set q_up_fail_addexp(value: int): void{
			this._q_up_fail_addexp = value;
		}
		
		/**
		 * get 进阶成功是否全服公告（0不公告，1公告）
		 * @return 
		 */
		public function get q_is_announce(): int{
			return _q_is_announce;
		}
		
		/**
		 * set 进阶成功是否全服公告（0不公告，1公告）
		 */
		public function set q_is_announce(value: int): void{
			this._q_is_announce = value;
		}
		
		/**
		 * get 暗器最大等级
		 * @return 
		 */
		public function get q_max_level(): int{
			return _q_max_level;
		}
		
		/**
		 * set 暗器最大等级
		 */
		public function set q_max_level(value: int): void{
			this._q_max_level = value;
		}
		
		/**
		 * get 开放格子数
		 * @return 
		 */
		public function get q_open_grid(): int{
			return _q_open_grid;
		}
		
		/**
		 * set 开放格子数
		 */
		public function set q_open_grid(value: int): void{
			this._q_open_grid = value;
		}
		
		/**
		 * get 投掷次数
		 * @return 
		 */
		public function get q_throw_times(): int{
			return _q_throw_times;
		}
		
		/**
		 * set 投掷次数
		 */
		public function set q_throw_times(value: int): void{
			this._q_throw_times = value;
		}
		
	}
}