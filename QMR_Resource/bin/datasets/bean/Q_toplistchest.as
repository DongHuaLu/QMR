package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_toplistchest
	 */
	public class Q_toplistchest extends Bean{
	
		//宝箱编号
		private var _q_chest_id: int;
		
		//宝箱类型(1等级 2坐骑 3武功 4龙元 5连斩)
		private var _q_chest_type: int;
		
		//宝箱领取条件
		private var _q_recieve_cont: int;
		
		//对应礼包ID
		private var _q_gift: int;
		
		//领取时的按钮描述
		private var _q_receive_description: String;
		
		//不能领取时的按钮描述
		private var _q_notcompleted_description: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宝箱编号
			_q_chest_id = readInt();
			//宝箱类型(1等级 2坐骑 3武功 4龙元 5连斩)
			_q_chest_type = readInt();
			//宝箱领取条件
			_q_recieve_cont = readInt();
			//对应礼包ID
			_q_gift = readInt();
			//领取时的按钮描述
			_q_receive_description = readString();
			//不能领取时的按钮描述
			_q_notcompleted_description = readString();
			return true;
		}
		
		/**
		 * get 宝箱编号
		 * @return 
		 */
		public function get q_chest_id(): int{
			return _q_chest_id;
		}
		
		/**
		 * set 宝箱编号
		 */
		public function set q_chest_id(value: int): void{
			this._q_chest_id = value;
		}
		
		/**
		 * get 宝箱类型(1等级 2坐骑 3武功 4龙元 5连斩)
		 * @return 
		 */
		public function get q_chest_type(): int{
			return _q_chest_type;
		}
		
		/**
		 * set 宝箱类型(1等级 2坐骑 3武功 4龙元 5连斩)
		 */
		public function set q_chest_type(value: int): void{
			this._q_chest_type = value;
		}
		
		/**
		 * get 宝箱领取条件
		 * @return 
		 */
		public function get q_recieve_cont(): int{
			return _q_recieve_cont;
		}
		
		/**
		 * set 宝箱领取条件
		 */
		public function set q_recieve_cont(value: int): void{
			this._q_recieve_cont = value;
		}
		
		/**
		 * get 对应礼包ID
		 * @return 
		 */
		public function get q_gift(): int{
			return _q_gift;
		}
		
		/**
		 * set 对应礼包ID
		 */
		public function set q_gift(value: int): void{
			this._q_gift = value;
		}
		
		/**
		 * get 领取时的按钮描述
		 * @return 
		 */
		public function get q_receive_description(): String{
			return _q_receive_description;
		}
		
		/**
		 * set 领取时的按钮描述
		 */
		public function set q_receive_description(value: String): void{
			this._q_receive_description = value;
		}
		
		/**
		 * get 不能领取时的按钮描述
		 * @return 
		 */
		public function get q_notcompleted_description(): String{
			return _q_notcompleted_description;
		}
		
		/**
		 * set 不能领取时的按钮描述
		 */
		public function set q_notcompleted_description(value: String): void{
			this._q_notcompleted_description = value;
		}
		
	}
}