package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_spirittree_packet
	 */
	public class Q_spirittree_packet extends Bean{
	
		//筛选类型（1普通类，2银色奇异果，3金色奇异果，4特殊类）
		private var _q_type: int;
		
		//组包ID（关联细则表字段）
		private var _q_packet_id: int;
		
		//组包名称（显示于TIPS）
		private var _q_packet_name: String;
		
		//组包描述（显示于TIPS）
		private var _q_describe: String;
		
		//是否参与新果筛选（0不参与，1参与）
		private var _q_is_select: int;
		
		//参选所需最低人物等级
		private var _q_need_level: int;
		
		//是否允许被偷取（0不允许，1允许）
		private var _q_is_steal: int;
		
		//组包天生光效资源编号
		private var _q_lightefficiency_id: int;
		
		//果实组包成长过程造型资源编号（格式：状态1名称|状态1造型资源编号|状态1维持秒数;状态2名称|状态2造型资源编号|状态2维持秒数;状态3名称|状态3造型资源编号|状态3维持秒数;）(如果填0，则意味着本果实在结出时立即成熟)
		private var _q_growing_up: String;
		
		//果实成熟后造型资源编号
		private var _q_mature_id: int;
		
		//干旱判断间隔时间(秒)
		private var _q_drought_time: int;
		
		//出现干旱事件几率（万分比）
		private var _q_drought_rnd: int;
		
		//手动催熟所需元宝（只有奇异果才生效）
		private var _q_urgeripening: int;
		
		//出现几率（普通果实用几率，互斥式几率）
		private var _q_arise_rnd: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//筛选类型（1普通类，2银色奇异果，3金色奇异果，4特殊类）
			_q_type = readInt();
			//组包ID（关联细则表字段）
			_q_packet_id = readInt();
			//组包名称（显示于TIPS）
			_q_packet_name = readString();
			//组包描述（显示于TIPS）
			_q_describe = readString();
			//是否参与新果筛选（0不参与，1参与）
			_q_is_select = readInt();
			//参选所需最低人物等级
			_q_need_level = readInt();
			//是否允许被偷取（0不允许，1允许）
			_q_is_steal = readInt();
			//组包天生光效资源编号
			_q_lightefficiency_id = readInt();
			//果实组包成长过程造型资源编号（格式：状态1名称|状态1造型资源编号|状态1维持秒数;状态2名称|状态2造型资源编号|状态2维持秒数;状态3名称|状态3造型资源编号|状态3维持秒数;）(如果填0，则意味着本果实在结出时立即成熟)
			_q_growing_up = readString();
			//果实成熟后造型资源编号
			_q_mature_id = readInt();
			//干旱判断间隔时间(秒)
			_q_drought_time = readInt();
			//出现干旱事件几率（万分比）
			_q_drought_rnd = readInt();
			//手动催熟所需元宝（只有奇异果才生效）
			_q_urgeripening = readInt();
			//出现几率（普通果实用几率，互斥式几率）
			_q_arise_rnd = readInt();
			return true;
		}
		
		/**
		 * get 筛选类型（1普通类，2银色奇异果，3金色奇异果，4特殊类）
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 筛选类型（1普通类，2银色奇异果，3金色奇异果，4特殊类）
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
		 * get 是否参与新果筛选（0不参与，1参与）
		 * @return 
		 */
		public function get q_is_select(): int{
			return _q_is_select;
		}
		
		/**
		 * set 是否参与新果筛选（0不参与，1参与）
		 */
		public function set q_is_select(value: int): void{
			this._q_is_select = value;
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
		 * get 是否允许被偷取（0不允许，1允许）
		 * @return 
		 */
		public function get q_is_steal(): int{
			return _q_is_steal;
		}
		
		/**
		 * set 是否允许被偷取（0不允许，1允许）
		 */
		public function set q_is_steal(value: int): void{
			this._q_is_steal = value;
		}
		
		/**
		 * get 组包天生光效资源编号
		 * @return 
		 */
		public function get q_lightefficiency_id(): int{
			return _q_lightefficiency_id;
		}
		
		/**
		 * set 组包天生光效资源编号
		 */
		public function set q_lightefficiency_id(value: int): void{
			this._q_lightefficiency_id = value;
		}
		
		/**
		 * get 果实组包成长过程造型资源编号（格式：状态1名称|状态1造型资源编号|状态1维持秒数;状态2名称|状态2造型资源编号|状态2维持秒数;状态3名称|状态3造型资源编号|状态3维持秒数;）(如果填0，则意味着本果实在结出时立即成熟)
		 * @return 
		 */
		public function get q_growing_up(): String{
			return _q_growing_up;
		}
		
		/**
		 * set 果实组包成长过程造型资源编号（格式：状态1名称|状态1造型资源编号|状态1维持秒数;状态2名称|状态2造型资源编号|状态2维持秒数;状态3名称|状态3造型资源编号|状态3维持秒数;）(如果填0，则意味着本果实在结出时立即成熟)
		 */
		public function set q_growing_up(value: String): void{
			this._q_growing_up = value;
		}
		
		/**
		 * get 果实成熟后造型资源编号
		 * @return 
		 */
		public function get q_mature_id(): int{
			return _q_mature_id;
		}
		
		/**
		 * set 果实成熟后造型资源编号
		 */
		public function set q_mature_id(value: int): void{
			this._q_mature_id = value;
		}
		
		/**
		 * get 干旱判断间隔时间(秒)
		 * @return 
		 */
		public function get q_drought_time(): int{
			return _q_drought_time;
		}
		
		/**
		 * set 干旱判断间隔时间(秒)
		 */
		public function set q_drought_time(value: int): void{
			this._q_drought_time = value;
		}
		
		/**
		 * get 出现干旱事件几率（万分比）
		 * @return 
		 */
		public function get q_drought_rnd(): int{
			return _q_drought_rnd;
		}
		
		/**
		 * set 出现干旱事件几率（万分比）
		 */
		public function set q_drought_rnd(value: int): void{
			this._q_drought_rnd = value;
		}
		
		/**
		 * get 手动催熟所需元宝（只有奇异果才生效）
		 * @return 
		 */
		public function get q_urgeripening(): int{
			return _q_urgeripening;
		}
		
		/**
		 * set 手动催熟所需元宝（只有奇异果才生效）
		 */
		public function set q_urgeripening(value: int): void{
			this._q_urgeripening = value;
		}
		
		/**
		 * get 出现几率（普通果实用几率，互斥式几率）
		 * @return 
		 */
		public function get q_arise_rnd(): int{
			return _q_arise_rnd;
		}
		
		/**
		 * set 出现几率（普通果实用几率，互斥式几率）
		 */
		public function set q_arise_rnd(value: int): void{
			this._q_arise_rnd = value;
		}
		
	}
}