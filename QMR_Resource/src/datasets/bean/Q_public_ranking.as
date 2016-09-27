package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_public_ranking
	 */
	public class Q_public_ranking extends Bean{
	
		//排名区间MIN
		private var _q_range_min: int;
		
		//排名区间MAX
		private var _q_range_max: int;
		
		//奖励1ID
		private var _q_1_id: int;
		
		//奖励1数量
		private var _q_1_num: int;
		
		//奖励1图标路径
		private var _q_1_path: String;
		
		//奖励1过期时间
		private var _q_1_deadline: int;
		
		//奖励2ID
		private var _q_2_id: int;
		
		//奖励2数量
		private var _q_2_num: int;
		
		//奖励2图标路径
		private var _q_2_path: String;
		
		//奖励2过期时间
		private var _q_2_deadline: int;
		
		//奖励3ID
		private var _q_3_id: int;
		
		//奖励3数量
		private var _q_3_num: int;
		
		//奖励3图标路径
		private var _q_3_path: String;
		
		//奖励3过期时间
		private var _q_3_deadline: int;
		
		//奖励4ID
		private var _q_4_id: int;
		
		//奖励4数量
		private var _q_4_num: int;
		
		//奖励4图标路径
		private var _q_4_path: String;
		
		//奖励4过期时间
		private var _q_4_deadline: int;
		
		//奖励5ID
		private var _q_5_id: int;
		
		//奖励5数量
		private var _q_5_num: int;
		
		//奖励5图标路径
		private var _q_5_path: String;
		
		//奖励5过期时间
		private var _q_5_deadline: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//排名区间MIN
			_q_range_min = readInt();
			//排名区间MAX
			_q_range_max = readInt();
			//奖励1ID
			_q_1_id = readInt();
			//奖励1数量
			_q_1_num = readInt();
			//奖励1图标路径
			_q_1_path = readString();
			//奖励1过期时间
			_q_1_deadline = readInt();
			//奖励2ID
			_q_2_id = readInt();
			//奖励2数量
			_q_2_num = readInt();
			//奖励2图标路径
			_q_2_path = readString();
			//奖励2过期时间
			_q_2_deadline = readInt();
			//奖励3ID
			_q_3_id = readInt();
			//奖励3数量
			_q_3_num = readInt();
			//奖励3图标路径
			_q_3_path = readString();
			//奖励3过期时间
			_q_3_deadline = readInt();
			//奖励4ID
			_q_4_id = readInt();
			//奖励4数量
			_q_4_num = readInt();
			//奖励4图标路径
			_q_4_path = readString();
			//奖励4过期时间
			_q_4_deadline = readInt();
			//奖励5ID
			_q_5_id = readInt();
			//奖励5数量
			_q_5_num = readInt();
			//奖励5图标路径
			_q_5_path = readString();
			//奖励5过期时间
			_q_5_deadline = readInt();
			return true;
		}
		
		/**
		 * get 排名区间MIN
		 * @return 
		 */
		public function get q_range_min(): int{
			return _q_range_min;
		}
		
		/**
		 * set 排名区间MIN
		 */
		public function set q_range_min(value: int): void{
			this._q_range_min = value;
		}
		
		/**
		 * get 排名区间MAX
		 * @return 
		 */
		public function get q_range_max(): int{
			return _q_range_max;
		}
		
		/**
		 * set 排名区间MAX
		 */
		public function set q_range_max(value: int): void{
			this._q_range_max = value;
		}
		
		/**
		 * get 奖励1ID
		 * @return 
		 */
		public function get q_1_id(): int{
			return _q_1_id;
		}
		
		/**
		 * set 奖励1ID
		 */
		public function set q_1_id(value: int): void{
			this._q_1_id = value;
		}
		
		/**
		 * get 奖励1数量
		 * @return 
		 */
		public function get q_1_num(): int{
			return _q_1_num;
		}
		
		/**
		 * set 奖励1数量
		 */
		public function set q_1_num(value: int): void{
			this._q_1_num = value;
		}
		
		/**
		 * get 奖励1图标路径
		 * @return 
		 */
		public function get q_1_path(): String{
			return _q_1_path;
		}
		
		/**
		 * set 奖励1图标路径
		 */
		public function set q_1_path(value: String): void{
			this._q_1_path = value;
		}
		
		/**
		 * get 奖励1过期时间
		 * @return 
		 */
		public function get q_1_deadline(): int{
			return _q_1_deadline;
		}
		
		/**
		 * set 奖励1过期时间
		 */
		public function set q_1_deadline(value: int): void{
			this._q_1_deadline = value;
		}
		
		/**
		 * get 奖励2ID
		 * @return 
		 */
		public function get q_2_id(): int{
			return _q_2_id;
		}
		
		/**
		 * set 奖励2ID
		 */
		public function set q_2_id(value: int): void{
			this._q_2_id = value;
		}
		
		/**
		 * get 奖励2数量
		 * @return 
		 */
		public function get q_2_num(): int{
			return _q_2_num;
		}
		
		/**
		 * set 奖励2数量
		 */
		public function set q_2_num(value: int): void{
			this._q_2_num = value;
		}
		
		/**
		 * get 奖励2图标路径
		 * @return 
		 */
		public function get q_2_path(): String{
			return _q_2_path;
		}
		
		/**
		 * set 奖励2图标路径
		 */
		public function set q_2_path(value: String): void{
			this._q_2_path = value;
		}
		
		/**
		 * get 奖励2过期时间
		 * @return 
		 */
		public function get q_2_deadline(): int{
			return _q_2_deadline;
		}
		
		/**
		 * set 奖励2过期时间
		 */
		public function set q_2_deadline(value: int): void{
			this._q_2_deadline = value;
		}
		
		/**
		 * get 奖励3ID
		 * @return 
		 */
		public function get q_3_id(): int{
			return _q_3_id;
		}
		
		/**
		 * set 奖励3ID
		 */
		public function set q_3_id(value: int): void{
			this._q_3_id = value;
		}
		
		/**
		 * get 奖励3数量
		 * @return 
		 */
		public function get q_3_num(): int{
			return _q_3_num;
		}
		
		/**
		 * set 奖励3数量
		 */
		public function set q_3_num(value: int): void{
			this._q_3_num = value;
		}
		
		/**
		 * get 奖励3图标路径
		 * @return 
		 */
		public function get q_3_path(): String{
			return _q_3_path;
		}
		
		/**
		 * set 奖励3图标路径
		 */
		public function set q_3_path(value: String): void{
			this._q_3_path = value;
		}
		
		/**
		 * get 奖励3过期时间
		 * @return 
		 */
		public function get q_3_deadline(): int{
			return _q_3_deadline;
		}
		
		/**
		 * set 奖励3过期时间
		 */
		public function set q_3_deadline(value: int): void{
			this._q_3_deadline = value;
		}
		
		/**
		 * get 奖励4ID
		 * @return 
		 */
		public function get q_4_id(): int{
			return _q_4_id;
		}
		
		/**
		 * set 奖励4ID
		 */
		public function set q_4_id(value: int): void{
			this._q_4_id = value;
		}
		
		/**
		 * get 奖励4数量
		 * @return 
		 */
		public function get q_4_num(): int{
			return _q_4_num;
		}
		
		/**
		 * set 奖励4数量
		 */
		public function set q_4_num(value: int): void{
			this._q_4_num = value;
		}
		
		/**
		 * get 奖励4图标路径
		 * @return 
		 */
		public function get q_4_path(): String{
			return _q_4_path;
		}
		
		/**
		 * set 奖励4图标路径
		 */
		public function set q_4_path(value: String): void{
			this._q_4_path = value;
		}
		
		/**
		 * get 奖励4过期时间
		 * @return 
		 */
		public function get q_4_deadline(): int{
			return _q_4_deadline;
		}
		
		/**
		 * set 奖励4过期时间
		 */
		public function set q_4_deadline(value: int): void{
			this._q_4_deadline = value;
		}
		
		/**
		 * get 奖励5ID
		 * @return 
		 */
		public function get q_5_id(): int{
			return _q_5_id;
		}
		
		/**
		 * set 奖励5ID
		 */
		public function set q_5_id(value: int): void{
			this._q_5_id = value;
		}
		
		/**
		 * get 奖励5数量
		 * @return 
		 */
		public function get q_5_num(): int{
			return _q_5_num;
		}
		
		/**
		 * set 奖励5数量
		 */
		public function set q_5_num(value: int): void{
			this._q_5_num = value;
		}
		
		/**
		 * get 奖励5图标路径
		 * @return 
		 */
		public function get q_5_path(): String{
			return _q_5_path;
		}
		
		/**
		 * set 奖励5图标路径
		 */
		public function set q_5_path(value: String): void{
			this._q_5_path = value;
		}
		
		/**
		 * get 奖励5过期时间
		 * @return 
		 */
		public function get q_5_deadline(): int{
			return _q_5_deadline;
		}
		
		/**
		 * set 奖励5过期时间
		 */
		public function set q_5_deadline(value: int): void{
			this._q_5_deadline = value;
		}
		
	}
}