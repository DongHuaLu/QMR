package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_task_explorepalace
	 */
	public class Q_task_explorepalace extends Bean{
	
		//任务编号
		private var _q_id: int;
		
		//玩家等级区间min
		private var _q_mingrade: int;
		
		//玩家等级区间max
		private var _q_maxgrade: int;
		
		//任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
		private var _q_end_needkillmonster: String;
		
		//任务奖励经验
		private var _q_rewards_exp: int;
		
		//任务奖励铜币
		private var _q_rewards_coin: int;
		
		//任务奖励真气
		private var _q_rewards_zq: int;
		
		//任务奖励礼金
		private var _q_rewards_bindyuanbao: int;
		
		//任务奖励声望
		private var _q_rewards_prestige: int;
		
		//完成任务需消耗元宝
		private var _q_usegold: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务编号
			_q_id = readInt();
			//玩家等级区间min
			_q_mingrade = readInt();
			//玩家等级区间max
			_q_maxgrade = readInt();
			//任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
			_q_end_needkillmonster = readString();
			//任务奖励经验
			_q_rewards_exp = readInt();
			//任务奖励铜币
			_q_rewards_coin = readInt();
			//任务奖励真气
			_q_rewards_zq = readInt();
			//任务奖励礼金
			_q_rewards_bindyuanbao = readInt();
			//任务奖励声望
			_q_rewards_prestige = readInt();
			//完成任务需消耗元宝
			_q_usegold = readInt();
			return true;
		}
		
		/**
		 * get 任务编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 任务编号
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
		 * get 任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
		 * @return 
		 */
		public function get q_end_needkillmonster(): String{
			return _q_end_needkillmonster;
		}
		
		/**
		 * set 任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
		 */
		public function set q_end_needkillmonster(value: String): void{
			this._q_end_needkillmonster = value;
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
		 * get 任务奖励铜币
		 * @return 
		 */
		public function get q_rewards_coin(): int{
			return _q_rewards_coin;
		}
		
		/**
		 * set 任务奖励铜币
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
		 * get 任务奖励礼金
		 * @return 
		 */
		public function get q_rewards_bindyuanbao(): int{
			return _q_rewards_bindyuanbao;
		}
		
		/**
		 * set 任务奖励礼金
		 */
		public function set q_rewards_bindyuanbao(value: int): void{
			this._q_rewards_bindyuanbao = value;
		}
		
		/**
		 * get 任务奖励声望
		 * @return 
		 */
		public function get q_rewards_prestige(): int{
			return _q_rewards_prestige;
		}
		
		/**
		 * set 任务奖励声望
		 */
		public function set q_rewards_prestige(value: int): void{
			this._q_rewards_prestige = value;
		}
		
		/**
		 * get 完成任务需消耗元宝
		 * @return 
		 */
		public function get q_usegold(): int{
			return _q_usegold;
		}
		
		/**
		 * set 完成任务需消耗元宝
		 */
		public function set q_usegold(value: int): void{
			this._q_usegold = value;
		}
		
	}
}