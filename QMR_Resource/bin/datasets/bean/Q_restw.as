package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_restw
	 */
	public class Q_restw extends Bean{
	
		//id
		private var _q_id: int;
		
		//中文名称
		private var _q_key: String;
		
		//其他语言
		private var _q_value: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//id
			_q_id = readInt();
			//中文名称
			_q_key = readString();
			//其他语言
			_q_value = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set id
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 中文名称
		 * @return 
		 */
		public function get q_key(): String{
			return _q_key;
		}
		
		/**
		 * set 中文名称
		 */
		public function set q_key(value: String): void{
			this._q_key = value;
		}
		
		/**
		 * get 其他语言
		 * @return 
		 */
		public function get q_value(): String{
			return _q_value;
		}
		
		/**
		 * set 其他语言
		 */
		public function set q_value(value: String): void{
			this._q_value = value;
		}
		
	}
}