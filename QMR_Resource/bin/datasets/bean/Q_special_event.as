package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_special_event
	 */
	public class Q_special_event extends Bean{
	
		//触发事件编号
		private var _q_id: int;
		
		//触发事件点,map_x_y_范围
		private var _q_event_pos: String;
		
		//触发事件脚本
		private var _q_event_scriptid: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//触发事件编号
			_q_id = readInt();
			//触发事件点,map_x_y_范围
			_q_event_pos = readString();
			//触发事件脚本
			_q_event_scriptid = readInt();
			return true;
		}
		
		/**
		 * get 触发事件编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 触发事件编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 触发事件点,map_x_y_范围
		 * @return 
		 */
		public function get q_event_pos(): String{
			return _q_event_pos;
		}
		
		/**
		 * set 触发事件点,map_x_y_范围
		 */
		public function set q_event_pos(value: String): void{
			this._q_event_pos = value;
		}
		
		/**
		 * get 触发事件脚本
		 * @return 
		 */
		public function get q_event_scriptid(): int{
			return _q_event_scriptid;
		}
		
		/**
		 * set 触发事件脚本
		 */
		public function set q_event_scriptid(value: int): void{
			this._q_event_scriptid = value;
		}
		
	}
}