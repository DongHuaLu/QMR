package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_explorepalace_map
	 */
	public class Q_explorepalace_map extends Bean{
	
		//地宫格子di
		private var _q_id: int;
		
		//上边格子
		private var _q_on: int;
		
		//下边格子
		private var _q_under: int;
		
		//左边格子
		private var _q_left: int;
		
		//右边格子
		private var _q_right: int;
		
		//事件ID
		private var _q_eventid: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//地宫格子di
			_q_id = readInt();
			//上边格子
			_q_on = readInt();
			//下边格子
			_q_under = readInt();
			//左边格子
			_q_left = readInt();
			//右边格子
			_q_right = readInt();
			//事件ID
			_q_eventid = readInt();
			return true;
		}
		
		/**
		 * get 地宫格子di
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 地宫格子di
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 上边格子
		 * @return 
		 */
		public function get q_on(): int{
			return _q_on;
		}
		
		/**
		 * set 上边格子
		 */
		public function set q_on(value: int): void{
			this._q_on = value;
		}
		
		/**
		 * get 下边格子
		 * @return 
		 */
		public function get q_under(): int{
			return _q_under;
		}
		
		/**
		 * set 下边格子
		 */
		public function set q_under(value: int): void{
			this._q_under = value;
		}
		
		/**
		 * get 左边格子
		 * @return 
		 */
		public function get q_left(): int{
			return _q_left;
		}
		
		/**
		 * set 左边格子
		 */
		public function set q_left(value: int): void{
			this._q_left = value;
		}
		
		/**
		 * get 右边格子
		 * @return 
		 */
		public function get q_right(): int{
			return _q_right;
		}
		
		/**
		 * set 右边格子
		 */
		public function set q_right(value: int): void{
			this._q_right = value;
		}
		
		/**
		 * get 事件ID
		 * @return 
		 */
		public function get q_eventid(): int{
			return _q_eventid;
		}
		
		/**
		 * set 事件ID
		 */
		public function set q_eventid(value: int): void{
			this._q_eventid = value;
		}
		
	}
}