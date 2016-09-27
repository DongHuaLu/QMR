package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_public_awards_limit
	 */
	public class Q_public_awards_limit extends Bean{
	
		//人物等级
		private var _q_level: int;
		
		//经验上限
		private var _q_exp_limit: int;
		
		//铜币奖励上限
		private var _q_money_limit: int;
		
		//真气奖励上限
		private var _q_zq_limit: int;
		
		//荣誉奖励上限
		private var _q_honor_limit: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//人物等级
			_q_level = readInt();
			//经验上限
			_q_exp_limit = readInt();
			//铜币奖励上限
			_q_money_limit = readInt();
			//真气奖励上限
			_q_zq_limit = readInt();
			//荣誉奖励上限
			_q_honor_limit = readInt();
			return true;
		}
		
		/**
		 * get 人物等级
		 * @return 
		 */
		public function get q_level(): int{
			return _q_level;
		}
		
		/**
		 * set 人物等级
		 */
		public function set q_level(value: int): void{
			this._q_level = value;
		}
		
		/**
		 * get 经验上限
		 * @return 
		 */
		public function get q_exp_limit(): int{
			return _q_exp_limit;
		}
		
		/**
		 * set 经验上限
		 */
		public function set q_exp_limit(value: int): void{
			this._q_exp_limit = value;
		}
		
		/**
		 * get 铜币奖励上限
		 * @return 
		 */
		public function get q_money_limit(): int{
			return _q_money_limit;
		}
		
		/**
		 * set 铜币奖励上限
		 */
		public function set q_money_limit(value: int): void{
			this._q_money_limit = value;
		}
		
		/**
		 * get 真气奖励上限
		 * @return 
		 */
		public function get q_zq_limit(): int{
			return _q_zq_limit;
		}
		
		/**
		 * set 真气奖励上限
		 */
		public function set q_zq_limit(value: int): void{
			this._q_zq_limit = value;
		}
		
		/**
		 * get 荣誉奖励上限
		 * @return 
		 */
		public function get q_honor_limit(): int{
			return _q_honor_limit;
		}
		
		/**
		 * set 荣誉奖励上限
		 */
		public function set q_honor_limit(value: int): void{
			this._q_honor_limit = value;
		}
		
	}
}