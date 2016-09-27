package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_evencut
	 */
	public class Q_evencut extends Bean{
	
		//连斩环数
		private var _q_id: int;
		
		//连斩次数,小于当前数，则进入
		private var _q_evencut_num: int;
		
		//计数倒计时
		private var _q_countdown: int;
		
		//BUFFID
		private var _q_buff_id: int;
		
		//BUFF光环资源
		private var _q_buff_res_effect: int;
		
		//BUFF名称
		private var _q_buff_name: String;
		
		//经验加成
		private var _q_exp: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//连斩环数
			_q_id = readInt();
			//连斩次数,小于当前数，则进入
			_q_evencut_num = readInt();
			//计数倒计时
			_q_countdown = readInt();
			//BUFFID
			_q_buff_id = readInt();
			//BUFF光环资源
			_q_buff_res_effect = readInt();
			//BUFF名称
			_q_buff_name = readString();
			//经验加成
			_q_exp = readInt();
			return true;
		}
		
		/**
		 * get 连斩环数
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 连斩环数
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 连斩次数,小于当前数，则进入
		 * @return 
		 */
		public function get q_evencut_num(): int{
			return _q_evencut_num;
		}
		
		/**
		 * set 连斩次数,小于当前数，则进入
		 */
		public function set q_evencut_num(value: int): void{
			this._q_evencut_num = value;
		}
		
		/**
		 * get 计数倒计时
		 * @return 
		 */
		public function get q_countdown(): int{
			return _q_countdown;
		}
		
		/**
		 * set 计数倒计时
		 */
		public function set q_countdown(value: int): void{
			this._q_countdown = value;
		}
		
		/**
		 * get BUFFID
		 * @return 
		 */
		public function get q_buff_id(): int{
			return _q_buff_id;
		}
		
		/**
		 * set BUFFID
		 */
		public function set q_buff_id(value: int): void{
			this._q_buff_id = value;
		}
		
		/**
		 * get BUFF光环资源
		 * @return 
		 */
		public function get q_buff_res_effect(): int{
			return _q_buff_res_effect;
		}
		
		/**
		 * set BUFF光环资源
		 */
		public function set q_buff_res_effect(value: int): void{
			this._q_buff_res_effect = value;
		}
		
		/**
		 * get BUFF名称
		 * @return 
		 */
		public function get q_buff_name(): String{
			return _q_buff_name;
		}
		
		/**
		 * set BUFF名称
		 */
		public function set q_buff_name(value: String): void{
			this._q_buff_name = value;
		}
		
		/**
		 * get 经验加成
		 * @return 
		 */
		public function get q_exp(): int{
			return _q_exp;
		}
		
		/**
		 * set 经验加成
		 */
		public function set q_exp(value: int): void{
			this._q_exp = value;
		}
		
	}
}