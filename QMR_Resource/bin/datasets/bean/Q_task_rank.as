package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_task_rank
	 */
	public class Q_task_rank extends Bean{
	
		//军衔任务编号
		private var _q_id: int;
		
		//任务名称
		private var _q_name: String;
		
		//军衔日常任务描述【每个任务每天可完成一次】
		private var _q_desc: String;
		
		//任务类型
		private var _q_type: int;
		
		//任务要求（[目标,需要条件]）
		private var _q_condition: String;
		
		//前一个任务(没有就填写0)
		private var _q_front_task: int;
		
		//下一个任务(没有就填写0)
		private var _q_next_task: int;
		
		//完成任务军功值奖励
		private var _q_rewards_rank: int;
		
		//任务显示等级
		private var _q_show_level: int;
		
		//快速完成花费元宝数量
		private var _q_quickfinsh_gold: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//军衔任务编号
			_q_id = readInt();
			//任务名称
			_q_name = readString();
			//军衔日常任务描述【每个任务每天可完成一次】
			_q_desc = readString();
			//任务类型
			_q_type = readInt();
			//任务要求（[目标,需要条件]）
			_q_condition = readString();
			//前一个任务(没有就填写0)
			_q_front_task = readInt();
			//下一个任务(没有就填写0)
			_q_next_task = readInt();
			//完成任务军功值奖励
			_q_rewards_rank = readInt();
			//任务显示等级
			_q_show_level = readInt();
			//快速完成花费元宝数量
			_q_quickfinsh_gold = readInt();
			return true;
		}
		
		/**
		 * get 军衔任务编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 军衔任务编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 任务名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 任务名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 军衔日常任务描述【每个任务每天可完成一次】
		 * @return 
		 */
		public function get q_desc(): String{
			return _q_desc;
		}
		
		/**
		 * set 军衔日常任务描述【每个任务每天可完成一次】
		 */
		public function set q_desc(value: String): void{
			this._q_desc = value;
		}
		
		/**
		 * get 任务类型
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 任务类型
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 任务要求（[目标,需要条件]）
		 * @return 
		 */
		public function get q_condition(): String{
			return _q_condition;
		}
		
		/**
		 * set 任务要求（[目标,需要条件]）
		 */
		public function set q_condition(value: String): void{
			this._q_condition = value;
		}
		
		/**
		 * get 前一个任务(没有就填写0)
		 * @return 
		 */
		public function get q_front_task(): int{
			return _q_front_task;
		}
		
		/**
		 * set 前一个任务(没有就填写0)
		 */
		public function set q_front_task(value: int): void{
			this._q_front_task = value;
		}
		
		/**
		 * get 下一个任务(没有就填写0)
		 * @return 
		 */
		public function get q_next_task(): int{
			return _q_next_task;
		}
		
		/**
		 * set 下一个任务(没有就填写0)
		 */
		public function set q_next_task(value: int): void{
			this._q_next_task = value;
		}
		
		/**
		 * get 完成任务军功值奖励
		 * @return 
		 */
		public function get q_rewards_rank(): int{
			return _q_rewards_rank;
		}
		
		/**
		 * set 完成任务军功值奖励
		 */
		public function set q_rewards_rank(value: int): void{
			this._q_rewards_rank = value;
		}
		
		/**
		 * get 任务显示等级
		 * @return 
		 */
		public function get q_show_level(): int{
			return _q_show_level;
		}
		
		/**
		 * set 任务显示等级
		 */
		public function set q_show_level(value: int): void{
			this._q_show_level = value;
		}
		
		/**
		 * get 快速完成花费元宝数量
		 * @return 
		 */
		public function get q_quickfinsh_gold(): int{
			return _q_quickfinsh_gold;
		}
		
		/**
		 * set 快速完成花费元宝数量
		 */
		public function set q_quickfinsh_gold(value: int): void{
			this._q_quickfinsh_gold = value;
		}
		
	}
}