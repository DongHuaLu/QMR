package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_task_daily_rewards
	 */
	public class Q_task_daily_rewards extends Bean{
	
		//奖励编号
		private var _q_id: int;
		
		//玩家等级区间min
		private var _q_mingrade: int;
		
		//玩家等级区间max
		private var _q_maxgrade: int;
		
		//任务奖励丰厚度级别
		private var _q_rich: int;
		
		//任务奖励达成某成就序列（成就编号;成就编号）
		private var _q_rewards_achieve: String;
		
		//任务奖励经验
		private var _q_rewards_exp: int;
		
		//任务奖励铜钱
		private var _q_rewards_coin: int;
		
		//任务奖励真气
		private var _q_rewards_zq: int;
		
		//任务奖励声望
		private var _q_rewards_prestige: int;
		
		//任务奖励绑定元宝
		private var _q_rewards_bindyuanbao: int;
		
		//任务奖励物品序列（物品ID_数量_性别需求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;物品ID_数量_性别需求;物品ID_数量）
		private var _q_rewards_goods: String;
		
		
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
			//任务奖励达成某成就序列（成就编号;成就编号）
			_q_rewards_achieve = readString();
			//任务奖励经验
			_q_rewards_exp = readInt();
			//任务奖励铜钱
			_q_rewards_coin = readInt();
			//任务奖励真气
			_q_rewards_zq = readInt();
			//任务奖励声望
			_q_rewards_prestige = readInt();
			//任务奖励绑定元宝
			_q_rewards_bindyuanbao = readInt();
			//任务奖励物品序列（物品ID_数量_性别需求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;物品ID_数量_性别需求;物品ID_数量）
			_q_rewards_goods = readString();
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
		 * get 任务奖励达成某成就序列（成就编号;成就编号）
		 * @return 
		 */
		public function get q_rewards_achieve(): String{
			return _q_rewards_achieve;
		}
		
		/**
		 * set 任务奖励达成某成就序列（成就编号;成就编号）
		 */
		public function set q_rewards_achieve(value: String): void{
			this._q_rewards_achieve = value;
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
		 * get 任务奖励物品序列（物品ID_数量_性别需求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;物品ID_数量_性别需求;物品ID_数量）
		 * @return 
		 */
		public function get q_rewards_goods(): String{
			return _q_rewards_goods;
		}
		
		/**
		 * set 任务奖励物品序列（物品ID_数量_性别需求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;物品ID_数量_性别需求;物品ID_数量）
		 */
		public function set q_rewards_goods(value: String): void{
			this._q_rewards_goods = value;
		}
		
	}
}