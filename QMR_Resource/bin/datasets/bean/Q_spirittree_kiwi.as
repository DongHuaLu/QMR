package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_spirittree_kiwi
	 */
	public class Q_spirittree_kiwi extends Bean{
	
		//编号
		private var _q_id: String;
		
		//类型（1银色奇异果，2金色奇异果）
		private var _q_type: int;
		
		//组包ID（关联细则表字段）
		private var _q_packet_id: int;
		
		//组包名称（显示于TIPS）
		private var _q_packet_name: String;
		
		//组包描述（显示于TIPS）
		private var _q_describe: String;
		
		//是否显示于奖励面板上
		private var _q_is_show: int;
		
		//天生光效资源编号
		private var _q_lightefficiency_id: int;
		
		//出现几率（万分比）
		private var _q_arise_rnd: int;
		
		//参选所需最低人物等级
		private var _q_need_level: int;
		
		//中选后的复现次数（最大为10）
		private var _q_check_num: int;
		
		//参选所需排出等级（小于等于）
		private var _q_exclude_level: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//编号
			_q_id = readString();
			//类型（1银色奇异果，2金色奇异果）
			_q_type = readInt();
			//组包ID（关联细则表字段）
			_q_packet_id = readInt();
			//组包名称（显示于TIPS）
			_q_packet_name = readString();
			//组包描述（显示于TIPS）
			_q_describe = readString();
			//是否显示于奖励面板上
			_q_is_show = readInt();
			//天生光效资源编号
			_q_lightefficiency_id = readInt();
			//出现几率（万分比）
			_q_arise_rnd = readInt();
			//参选所需最低人物等级
			_q_need_level = readInt();
			//中选后的复现次数（最大为10）
			_q_check_num = readInt();
			//参选所需排出等级（小于等于）
			_q_exclude_level = readInt();
			return true;
		}
		
		/**
		 * get 编号
		 * @return 
		 */
		public function get q_id(): String{
			return _q_id;
		}
		
		/**
		 * set 编号
		 */
		public function set q_id(value: String): void{
			this._q_id = value;
		}
		
		/**
		 * get 类型（1银色奇异果，2金色奇异果）
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 类型（1银色奇异果，2金色奇异果）
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 组包ID（关联细则表字段）
		 * @return 
		 */
		public function get q_packet_id(): int{
			return _q_packet_id;
		}
		
		/**
		 * set 组包ID（关联细则表字段）
		 */
		public function set q_packet_id(value: int): void{
			this._q_packet_id = value;
		}
		
		/**
		 * get 组包名称（显示于TIPS）
		 * @return 
		 */
		public function get q_packet_name(): String{
			return _q_packet_name;
		}
		
		/**
		 * set 组包名称（显示于TIPS）
		 */
		public function set q_packet_name(value: String): void{
			this._q_packet_name = value;
		}
		
		/**
		 * get 组包描述（显示于TIPS）
		 * @return 
		 */
		public function get q_describe(): String{
			return _q_describe;
		}
		
		/**
		 * set 组包描述（显示于TIPS）
		 */
		public function set q_describe(value: String): void{
			this._q_describe = value;
		}
		
		/**
		 * get 是否显示于奖励面板上
		 * @return 
		 */
		public function get q_is_show(): int{
			return _q_is_show;
		}
		
		/**
		 * set 是否显示于奖励面板上
		 */
		public function set q_is_show(value: int): void{
			this._q_is_show = value;
		}
		
		/**
		 * get 天生光效资源编号
		 * @return 
		 */
		public function get q_lightefficiency_id(): int{
			return _q_lightefficiency_id;
		}
		
		/**
		 * set 天生光效资源编号
		 */
		public function set q_lightefficiency_id(value: int): void{
			this._q_lightefficiency_id = value;
		}
		
		/**
		 * get 出现几率（万分比）
		 * @return 
		 */
		public function get q_arise_rnd(): int{
			return _q_arise_rnd;
		}
		
		/**
		 * set 出现几率（万分比）
		 */
		public function set q_arise_rnd(value: int): void{
			this._q_arise_rnd = value;
		}
		
		/**
		 * get 参选所需最低人物等级
		 * @return 
		 */
		public function get q_need_level(): int{
			return _q_need_level;
		}
		
		/**
		 * set 参选所需最低人物等级
		 */
		public function set q_need_level(value: int): void{
			this._q_need_level = value;
		}
		
		/**
		 * get 中选后的复现次数（最大为10）
		 * @return 
		 */
		public function get q_check_num(): int{
			return _q_check_num;
		}
		
		/**
		 * set 中选后的复现次数（最大为10）
		 */
		public function set q_check_num(value: int): void{
			this._q_check_num = value;
		}
		
		/**
		 * get 参选所需排出等级（小于等于）
		 * @return 
		 */
		public function get q_exclude_level(): int{
			return _q_exclude_level;
		}
		
		/**
		 * set 参选所需排出等级（小于等于）
		 */
		public function set q_exclude_level(value: int): void{
			this._q_exclude_level = value;
		}
		
	}
}