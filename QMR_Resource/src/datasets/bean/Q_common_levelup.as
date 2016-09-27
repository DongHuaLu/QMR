package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_common_levelup
	 */
	public class Q_common_levelup extends Bean{
	
		//编号
		private var _q_id: int;
		
		//类型(1:披风)
		private var _q_type: int;
		
		//消耗材料(格式[[10001,2],[10002,2]])
		private var _q_need_items: String;
		
		//消耗铜钱
		private var _q_need_money: int;
		
		//所需次数(下限)
		private var _q_times_min: int;
		
		//所需次数(上限)
		private var _q_times_max: int;
		
		//成功几率(万分比)
		private var _q_prob: int;
		
		//增加祝福值(下限)
		private var _q_bless_min: int;
		
		//增加祝福值(上限)
		private var _q_bless_max: int;
		
		//祝福值满值
		private var _q_bless_full: int;
		
		//增加经验
		private var _q_exp: int;
		
		//增加经验上限
		private var _q_exp_max: int;
		
		//清空类型(0:每天24点清除 1:第一次点击开始的24小时后清除)
		private var _q_clear: int;
		
		//回退标识(0:普通 1:二次确认)
		private var _q_rollback: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//编号
			_q_id = readInt();
			//类型(1:披风)
			_q_type = readInt();
			//消耗材料(格式[[10001,2],[10002,2]])
			_q_need_items = readString();
			//消耗铜钱
			_q_need_money = readInt();
			//所需次数(下限)
			_q_times_min = readInt();
			//所需次数(上限)
			_q_times_max = readInt();
			//成功几率(万分比)
			_q_prob = readInt();
			//增加祝福值(下限)
			_q_bless_min = readInt();
			//增加祝福值(上限)
			_q_bless_max = readInt();
			//祝福值满值
			_q_bless_full = readInt();
			//增加经验
			_q_exp = readInt();
			//增加经验上限
			_q_exp_max = readInt();
			//清空类型(0:每天24点清除 1:第一次点击开始的24小时后清除)
			_q_clear = readInt();
			//回退标识(0:普通 1:二次确认)
			_q_rollback = readInt();
			return true;
		}
		
		/**
		 * get 编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 类型(1:披风)
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 类型(1:披风)
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 消耗材料(格式[[10001,2],[10002,2]])
		 * @return 
		 */
		public function get q_need_items(): String{
			return _q_need_items;
		}
		
		/**
		 * set 消耗材料(格式[[10001,2],[10002,2]])
		 */
		public function set q_need_items(value: String): void{
			this._q_need_items = value;
		}
		
		/**
		 * get 消耗铜钱
		 * @return 
		 */
		public function get q_need_money(): int{
			return _q_need_money;
		}
		
		/**
		 * set 消耗铜钱
		 */
		public function set q_need_money(value: int): void{
			this._q_need_money = value;
		}
		
		/**
		 * get 所需次数(下限)
		 * @return 
		 */
		public function get q_times_min(): int{
			return _q_times_min;
		}
		
		/**
		 * set 所需次数(下限)
		 */
		public function set q_times_min(value: int): void{
			this._q_times_min = value;
		}
		
		/**
		 * get 所需次数(上限)
		 * @return 
		 */
		public function get q_times_max(): int{
			return _q_times_max;
		}
		
		/**
		 * set 所需次数(上限)
		 */
		public function set q_times_max(value: int): void{
			this._q_times_max = value;
		}
		
		/**
		 * get 成功几率(万分比)
		 * @return 
		 */
		public function get q_prob(): int{
			return _q_prob;
		}
		
		/**
		 * set 成功几率(万分比)
		 */
		public function set q_prob(value: int): void{
			this._q_prob = value;
		}
		
		/**
		 * get 增加祝福值(下限)
		 * @return 
		 */
		public function get q_bless_min(): int{
			return _q_bless_min;
		}
		
		/**
		 * set 增加祝福值(下限)
		 */
		public function set q_bless_min(value: int): void{
			this._q_bless_min = value;
		}
		
		/**
		 * get 增加祝福值(上限)
		 * @return 
		 */
		public function get q_bless_max(): int{
			return _q_bless_max;
		}
		
		/**
		 * set 增加祝福值(上限)
		 */
		public function set q_bless_max(value: int): void{
			this._q_bless_max = value;
		}
		
		/**
		 * get 祝福值满值
		 * @return 
		 */
		public function get q_bless_full(): int{
			return _q_bless_full;
		}
		
		/**
		 * set 祝福值满值
		 */
		public function set q_bless_full(value: int): void{
			this._q_bless_full = value;
		}
		
		/**
		 * get 增加经验
		 * @return 
		 */
		public function get q_exp(): int{
			return _q_exp;
		}
		
		/**
		 * set 增加经验
		 */
		public function set q_exp(value: int): void{
			this._q_exp = value;
		}
		
		/**
		 * get 增加经验上限
		 * @return 
		 */
		public function get q_exp_max(): int{
			return _q_exp_max;
		}
		
		/**
		 * set 增加经验上限
		 */
		public function set q_exp_max(value: int): void{
			this._q_exp_max = value;
		}
		
		/**
		 * get 清空类型(0:每天24点清除 1:第一次点击开始的24小时后清除)
		 * @return 
		 */
		public function get q_clear(): int{
			return _q_clear;
		}
		
		/**
		 * set 清空类型(0:每天24点清除 1:第一次点击开始的24小时后清除)
		 */
		public function set q_clear(value: int): void{
			this._q_clear = value;
		}
		
		/**
		 * get 回退标识(0:普通 1:二次确认)
		 * @return 
		 */
		public function get q_rollback(): int{
			return _q_rollback;
		}
		
		/**
		 * set 回退标识(0:普通 1:二次确认)
		 */
		public function set q_rollback(value: int): void{
			this._q_rollback = value;
		}
		
	}
}