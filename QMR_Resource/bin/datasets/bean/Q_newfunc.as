package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_newfunc
	 */
	public class Q_newfunc extends Bean{
	
		//编号id
		private var _q_id: int;
		
		//功能名字
		private var _q_string_name: String;
		
		//所属类型
		private var _q_main_type: String;
		
		//开放等级
		private var _q_level: int;
		
		//需要完成任务编号
		private var _q_need_taskid: int;
		
		//表现形式（0为不弹，1为弹出，2弹出tips框）
		private var _q_show_panel: int;
		
		//功能描述（支持html）
		private var _q_info: String;
		
		//图片路径
		private var _q_img_url: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//编号id
			_q_id = readInt();
			//功能名字
			_q_string_name = readString();
			//所属类型
			_q_main_type = readString();
			//开放等级
			_q_level = readInt();
			//需要完成任务编号
			_q_need_taskid = readInt();
			//表现形式（0为不弹，1为弹出，2弹出tips框）
			_q_show_panel = readInt();
			//功能描述（支持html）
			_q_info = readString();
			//图片路径
			_q_img_url = readString();
			return true;
		}
		
		/**
		 * get 编号id
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 编号id
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 功能名字
		 * @return 
		 */
		public function get q_string_name(): String{
			return _q_string_name;
		}
		
		/**
		 * set 功能名字
		 */
		public function set q_string_name(value: String): void{
			this._q_string_name = value;
		}
		
		/**
		 * get 所属类型
		 * @return 
		 */
		public function get q_main_type(): String{
			return _q_main_type;
		}
		
		/**
		 * set 所属类型
		 */
		public function set q_main_type(value: String): void{
			this._q_main_type = value;
		}
		
		/**
		 * get 开放等级
		 * @return 
		 */
		public function get q_level(): int{
			return _q_level;
		}
		
		/**
		 * set 开放等级
		 */
		public function set q_level(value: int): void{
			this._q_level = value;
		}
		
		/**
		 * get 需要完成任务编号
		 * @return 
		 */
		public function get q_need_taskid(): int{
			return _q_need_taskid;
		}
		
		/**
		 * set 需要完成任务编号
		 */
		public function set q_need_taskid(value: int): void{
			this._q_need_taskid = value;
		}
		
		/**
		 * get 表现形式（0为不弹，1为弹出，2弹出tips框）
		 * @return 
		 */
		public function get q_show_panel(): int{
			return _q_show_panel;
		}
		
		/**
		 * set 表现形式（0为不弹，1为弹出，2弹出tips框）
		 */
		public function set q_show_panel(value: int): void{
			this._q_show_panel = value;
		}
		
		/**
		 * get 功能描述（支持html）
		 * @return 
		 */
		public function get q_info(): String{
			return _q_info;
		}
		
		/**
		 * set 功能描述（支持html）
		 */
		public function set q_info(value: String): void{
			this._q_info = value;
		}
		
		/**
		 * get 图片路径
		 * @return 
		 */
		public function get q_img_url(): String{
			return _q_img_url;
		}
		
		/**
		 * set 图片路径
		 */
		public function set q_img_url(value: String): void{
			this._q_img_url = value;
		}
		
	}
}