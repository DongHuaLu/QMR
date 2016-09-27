package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_explorepalace_rewards
	 */
	public class Q_explorepalace_rewards extends Bean{
	
		//奖励编号
		private var _q_id: int;
		
		//玩家等级区间min
		private var _q_mingrade: int;
		
		//玩家等级区间max
		private var _q_maxgrade: int;
		
		//任务奖励丰厚度级别
		private var _q_rich: int;
		
		//奖励几率
		private var _q_rewardsrnd: int;
		
		//任务奖励道具
		private var _q_rewards_item: String;
		
		//任务奖励经验
		private var _q_rewards_exp: int;
		
		//任务奖励铜钱
		private var _q_rewards_coin: int;
		
		//任务奖励真气
		private var _q_rewards_zq: int;
		
		//任务奖励绑定元宝
		private var _q_rewards_bindyuanbao: int;
		
		//获得Buff编号
		private var _q_buff_id: int;
		
		//buff几率
		private var _q_buffrnd: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//奖励编号
			_q_id = readInt();
			//玩家等级区间min
			_q_mingrade = readInt();
			//玩家等级区间max
			_q_maxgrade = readInt();
			//任务奖励丰厚度级别
			_q_rich = readInt();
			//奖励几率
			_q_rewardsrnd = readInt();
			//任务奖励道具
			_q_rewards_item = readString();
			//任务奖励经验
			_q_rewards_exp = readInt();
			//任务奖励铜钱
			_q_rewards_coin = readInt();
			//任务奖励真气
			_q_rewards_zq = readInt();
			//任务奖励绑定元宝
			_q_rewards_bindyuanbao = readInt();
			//获得Buff编号
			_q_buff_id = readInt();
			//buff几率
			_q_buffrnd = readInt();
			return true;
		}
		
		/**
		 * get 奖励编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 奖励编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 玩家等级区间min
		 * @return 
		 */
		public function get q_mingrade(): int{
			return _q_mingrade;
		}
		
		/**
		 * set 玩家等级区间min
		 */
		public function set q_mingrade(value: int): void{
			this._q_mingrade = value;
		}
		
		/**
		 * get 玩家等级区间max
		 * @return 
		 */
		public function get q_maxgrade(): int{
			return _q_maxgrade;
		}
		
		/**
		 * set 玩家等级区间max
		 */
		public function set q_maxgrade(value: int): void{
			this._q_maxgrade = value;
		}
		
		/**
		 * get 任务奖励丰厚度级别
		 * @return 
		 */
		public function get q_rich(): int{
			return _q_rich;
		}
		
		/**
		 * set 任务奖励丰厚度级别
		 */
		public function set q_rich(value: int): void{
			this._q_rich = value;
		}
		
		/**
		 * get 奖励几率
		 * @return 
		 */
		public function get q_rewardsrnd(): int{
			return _q_rewardsrnd;
		}
		
		/**
		 * set 奖励几率
		 */
		public function set q_rewardsrnd(value: int): void{
			this._q_rewardsrnd = value;
		}
		
		/**
		 * get 任务奖励道具
		 * @return 
		 */
		public function get q_rewards_item(): String{
			return _q_rewards_item;
		}
		
		/**
		 * set 任务奖励道具
		 */
		public function set q_rewards_item(value: String): void{
			this._q_rewards_item = value;
		}
		
		/**
		 * get 任务奖励经验
		 * @return 
		 */
		public function get q_rewards_exp(): int{
			return _q_rewards_exp;
		}
		
		/**
		 * set 任务奖励经验
		 */
		public function set q_rewards_exp(value: int): void{
			this._q_rewards_exp = value;
		}
		
		/**
		 * get 任务奖励铜钱
		 * @return 
		 */
		public function get q_rewards_coin(): int{
			return _q_rewards_coin;
		}
		
		/**
		 * set 任务奖励铜钱
		 */
		public function set q_rewards_coin(value: int): void{
			this._q_rewards_coin = value;
		}
		
		/**
		 * get 任务奖励真气
		 * @return 
		 */
		public function get q_rewards_zq(): int{
			return _q_rewards_zq;
		}
		
		/**
		 * set 任务奖励真气
		 */
		public function set q_rewards_zq(value: int): void{
			this._q_rewards_zq = value;
		}
		
		/**
		 * get 任务奖励绑定元宝
		 * @return 
		 */
		public function get q_rewards_bindyuanbao(): int{
			return _q_rewards_bindyuanbao;
		}
		
		/**
		 * set 任务奖励绑定元宝
		 */
		public function set q_rewards_bindyuanbao(value: int): void{
			this._q_rewards_bindyuanbao = value;
		}
		
		/**
		 * get 获得Buff编号
		 * @return 
		 */
		public function get q_buff_id(): int{
			return _q_buff_id;
		}
		
		/**
		 * set 获得Buff编号
		 */
		public function set q_buff_id(value: int): void{
			this._q_buff_id = value;
		}
		
		/**
		 * get buff几率
		 * @return 
		 */
		public function get q_buffrnd(): int{
			return _q_buffrnd;
		}
		
		/**
		 * set buff几率
		 */
		public function set q_buffrnd(value: int): void{
			this._q_buffrnd = value;
		}
		
	}
}