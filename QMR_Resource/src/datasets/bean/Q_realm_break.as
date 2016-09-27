package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_realm_break
	 */
	public class Q_realm_break extends Bean{
	
		//境界阶数
		private var _q_id: int;
		
		//境界说明，支持html语法
		private var _q_explain: String;
		
		//境界面板展示用造型资源编号
		private var _q_panel_res: int;
		
		//场景中境界用造型资源编号
		private var _q_scene_res: int;
		
		//场景境界光效资源
		private var _q_scene_effect: int;
		
		//突破境界所需人物等级
		private var _q_break_level: int;
		
		//突破境界消费铜币数量
		private var _q_break_money: int;
		
		//每次突破境界所需消耗材料（id_数量）
		private var _q_break_item: String;
		
		//突破境界成功所需进阶次数min
		private var _q_success_min: int;
		
		//突破境界成功所需进阶次数max
		private var _q_success_max: int;
		
		//服务器端计算用突破境界成功几率（万分比）
		private var _q_cipher_random: int;
		
		//每次失败增加祝福值区间min
		private var _q_fail_blessing_min: int;
		
		//每次失败增加祝福值区间max
		private var _q_fail_blessing_max: int;
		
		//祝福值上限值
		private var _q_fail_blessing_limit: int;
		
		//每次失败常规几率及增加人物经验（格式：几率|经验）
		private var _q_fail_general_exp: String;
		
		//每次失败小爆击几率及增加人物经验（格式：几率|经验）
		private var _q_fail_crit_exp: String;
		
		//每次失败大爆击几率及增加人物经验（格式：几率|经验）
		private var _q_fail_maxcrit_exp: String;
		
		//进阶失败所获人物经验上限
		private var _q_fail_limit_exp: int;
		
		//进阶成功是否全服公告（0不公告，1公告）
		private var _q_isbulletin: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//境界阶数
			_q_id = readInt();
			//境界说明，支持html语法
			_q_explain = readString();
			//境界面板展示用造型资源编号
			_q_panel_res = readInt();
			//场景中境界用造型资源编号
			_q_scene_res = readInt();
			//场景境界光效资源
			_q_scene_effect = readInt();
			//突破境界所需人物等级
			_q_break_level = readInt();
			//突破境界消费铜币数量
			_q_break_money = readInt();
			//每次突破境界所需消耗材料（id_数量）
			_q_break_item = readString();
			//突破境界成功所需进阶次数min
			_q_success_min = readInt();
			//突破境界成功所需进阶次数max
			_q_success_max = readInt();
			//服务器端计算用突破境界成功几率（万分比）
			_q_cipher_random = readInt();
			//每次失败增加祝福值区间min
			_q_fail_blessing_min = readInt();
			//每次失败增加祝福值区间max
			_q_fail_blessing_max = readInt();
			//祝福值上限值
			_q_fail_blessing_limit = readInt();
			//每次失败常规几率及增加人物经验（格式：几率|经验）
			_q_fail_general_exp = readString();
			//每次失败小爆击几率及增加人物经验（格式：几率|经验）
			_q_fail_crit_exp = readString();
			//每次失败大爆击几率及增加人物经验（格式：几率|经验）
			_q_fail_maxcrit_exp = readString();
			//进阶失败所获人物经验上限
			_q_fail_limit_exp = readInt();
			//进阶成功是否全服公告（0不公告，1公告）
			_q_isbulletin = readInt();
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
		 * get 境界说明，支持html语法
		 * @return 
		 */
		public function get q_explain(): String{
			return _q_explain;
		}
		
		/**
		 * set 境界说明，支持html语法
		 */
		public function set q_explain(value: String): void{
			this._q_explain = value;
		}
		
		/**
		 * get 境界面板展示用造型资源编号
		 * @return 
		 */
		public function get q_panel_res(): int{
			return _q_panel_res;
		}
		
		/**
		 * set 境界面板展示用造型资源编号
		 */
		public function set q_panel_res(value: int): void{
			this._q_panel_res = value;
		}
		
		/**
		 * get 场景中境界用造型资源编号
		 * @return 
		 */
		public function get q_scene_res(): int{
			return _q_scene_res;
		}
		
		/**
		 * set 场景中境界用造型资源编号
		 */
		public function set q_scene_res(value: int): void{
			this._q_scene_res = value;
		}
		
		/**
		 * get 场景境界光效资源
		 * @return 
		 */
		public function get q_scene_effect(): int{
			return _q_scene_effect;
		}
		
		/**
		 * set 场景境界光效资源
		 */
		public function set q_scene_effect(value: int): void{
			this._q_scene_effect = value;
		}
		
		/**
		 * get 突破境界所需人物等级
		 * @return 
		 */
		public function get q_break_level(): int{
			return _q_break_level;
		}
		
		/**
		 * set 突破境界所需人物等级
		 */
		public function set q_break_level(value: int): void{
			this._q_break_level = value;
		}
		
		/**
		 * get 突破境界消费铜币数量
		 * @return 
		 */
		public function get q_break_money(): int{
			return _q_break_money;
		}
		
		/**
		 * set 突破境界消费铜币数量
		 */
		public function set q_break_money(value: int): void{
			this._q_break_money = value;
		}
		
		/**
		 * get 每次突破境界所需消耗材料（id_数量）
		 * @return 
		 */
		public function get q_break_item(): String{
			return _q_break_item;
		}
		
		/**
		 * set 每次突破境界所需消耗材料（id_数量）
		 */
		public function set q_break_item(value: String): void{
			this._q_break_item = value;
		}
		
		/**
		 * get 突破境界成功所需进阶次数min
		 * @return 
		 */
		public function get q_success_min(): int{
			return _q_success_min;
		}
		
		/**
		 * set 突破境界成功所需进阶次数min
		 */
		public function set q_success_min(value: int): void{
			this._q_success_min = value;
		}
		
		/**
		 * get 突破境界成功所需进阶次数max
		 * @return 
		 */
		public function get q_success_max(): int{
			return _q_success_max;
		}
		
		/**
		 * set 突破境界成功所需进阶次数max
		 */
		public function set q_success_max(value: int): void{
			this._q_success_max = value;
		}
		
		/**
		 * get 服务器端计算用突破境界成功几率（万分比）
		 * @return 
		 */
		public function get q_cipher_random(): int{
			return _q_cipher_random;
		}
		
		/**
		 * set 服务器端计算用突破境界成功几率（万分比）
		 */
		public function set q_cipher_random(value: int): void{
			this._q_cipher_random = value;
		}
		
		/**
		 * get 每次失败增加祝福值区间min
		 * @return 
		 */
		public function get q_fail_blessing_min(): int{
			return _q_fail_blessing_min;
		}
		
		/**
		 * set 每次失败增加祝福值区间min
		 */
		public function set q_fail_blessing_min(value: int): void{
			this._q_fail_blessing_min = value;
		}
		
		/**
		 * get 每次失败增加祝福值区间max
		 * @return 
		 */
		public function get q_fail_blessing_max(): int{
			return _q_fail_blessing_max;
		}
		
		/**
		 * set 每次失败增加祝福值区间max
		 */
		public function set q_fail_blessing_max(value: int): void{
			this._q_fail_blessing_max = value;
		}
		
		/**
		 * get 祝福值上限值
		 * @return 
		 */
		public function get q_fail_blessing_limit(): int{
			return _q_fail_blessing_limit;
		}
		
		/**
		 * set 祝福值上限值
		 */
		public function set q_fail_blessing_limit(value: int): void{
			this._q_fail_blessing_limit = value;
		}
		
		/**
		 * get 每次失败常规几率及增加人物经验（格式：几率|经验）
		 * @return 
		 */
		public function get q_fail_general_exp(): String{
			return _q_fail_general_exp;
		}
		
		/**
		 * set 每次失败常规几率及增加人物经验（格式：几率|经验）
		 */
		public function set q_fail_general_exp(value: String): void{
			this._q_fail_general_exp = value;
		}
		
		/**
		 * get 每次失败小爆击几率及增加人物经验（格式：几率|经验）
		 * @return 
		 */
		public function get q_fail_crit_exp(): String{
			return _q_fail_crit_exp;
		}
		
		/**
		 * set 每次失败小爆击几率及增加人物经验（格式：几率|经验）
		 */
		public function set q_fail_crit_exp(value: String): void{
			this._q_fail_crit_exp = value;
		}
		
		/**
		 * get 每次失败大爆击几率及增加人物经验（格式：几率|经验）
		 * @return 
		 */
		public function get q_fail_maxcrit_exp(): String{
			return _q_fail_maxcrit_exp;
		}
		
		/**
		 * set 每次失败大爆击几率及增加人物经验（格式：几率|经验）
		 */
		public function set q_fail_maxcrit_exp(value: String): void{
			this._q_fail_maxcrit_exp = value;
		}
		
		/**
		 * get 进阶失败所获人物经验上限
		 * @return 
		 */
		public function get q_fail_limit_exp(): int{
			return _q_fail_limit_exp;
		}
		
		/**
		 * set 进阶失败所获人物经验上限
		 */
		public function set q_fail_limit_exp(value: int): void{
			this._q_fail_limit_exp = value;
		}
		
		/**
		 * get 进阶成功是否全服公告（0不公告，1公告）
		 * @return 
		 */
		public function get q_isbulletin(): int{
			return _q_isbulletin;
		}
		
		/**
		 * set 进阶成功是否全服公告（0不公告，1公告）
		 */
		public function set q_isbulletin(value: int): void{
			this._q_isbulletin = value;
		}
		
	}
}