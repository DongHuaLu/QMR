package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_cloak
	 */
	public class Q_cloak extends Bean{
	
		//披风ID
		private var _q_model_id: int;
		
		//披风阶数
		private var _q_layer: int;
		
		//披风星级
		private var _q_star: int;
		
		//披风名称
		private var _q_name: String;
		
		//开启格子数
		private var _q_skill_num: int;
		
		//进阶成功是否全服公告（0不公告，1公告）
		private var _q_notify_flag: int;
		
		//锻造成功后进阶至披风ID
		private var _q_next_model: int;
		
		//披风最大星数
		private var _q_maxstar: int;
		
		//对应q_common_attribute的id
		private var _q_attribute: int;
		
		//对应q_common_levelup的id
		private var _q_levelup: int;
		
		//披风名称美术资源编号
		private var _q_name_src: String;
		
		//面板造型资源编号
		private var _q_panel_src: String;
		
		//场景造型资源编号
		private var _q_scene_src: String;
		
		//人物属性面板手持面板造型资源
		private var _q_main_panel_src: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//披风ID
			_q_model_id = readInt();
			//披风阶数
			_q_layer = readInt();
			//披风星级
			_q_star = readInt();
			//披风名称
			_q_name = readString();
			//开启格子数
			_q_skill_num = readInt();
			//进阶成功是否全服公告（0不公告，1公告）
			_q_notify_flag = readInt();
			//锻造成功后进阶至披风ID
			_q_next_model = readInt();
			//披风最大星数
			_q_maxstar = readInt();
			//对应q_common_attribute的id
			_q_attribute = readInt();
			//对应q_common_levelup的id
			_q_levelup = readInt();
			//披风名称美术资源编号
			_q_name_src = readString();
			//面板造型资源编号
			_q_panel_src = readString();
			//场景造型资源编号
			_q_scene_src = readString();
			//人物属性面板手持面板造型资源
			_q_main_panel_src = readString();
			return true;
		}
		
		/**
		 * get 披风ID
		 * @return 
		 */
		public function get q_model_id(): int{
			return _q_model_id;
		}
		
		/**
		 * set 披风ID
		 */
		public function set q_model_id(value: int): void{
			this._q_model_id = value;
		}
		
		/**
		 * get 披风阶数
		 * @return 
		 */
		public function get q_layer(): int{
			return _q_layer;
		}
		
		/**
		 * set 披风阶数
		 */
		public function set q_layer(value: int): void{
			this._q_layer = value;
		}
		
		/**
		 * get 披风星级
		 * @return 
		 */
		public function get q_star(): int{
			return _q_star;
		}
		
		/**
		 * set 披风星级
		 */
		public function set q_star(value: int): void{
			this._q_star = value;
		}
		
		/**
		 * get 披风名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 披风名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 开启格子数
		 * @return 
		 */
		public function get q_skill_num(): int{
			return _q_skill_num;
		}
		
		/**
		 * set 开启格子数
		 */
		public function set q_skill_num(value: int): void{
			this._q_skill_num = value;
		}
		
		/**
		 * get 进阶成功是否全服公告（0不公告，1公告）
		 * @return 
		 */
		public function get q_notify_flag(): int{
			return _q_notify_flag;
		}
		
		/**
		 * set 进阶成功是否全服公告（0不公告，1公告）
		 */
		public function set q_notify_flag(value: int): void{
			this._q_notify_flag = value;
		}
		
		/**
		 * get 锻造成功后进阶至披风ID
		 * @return 
		 */
		public function get q_next_model(): int{
			return _q_next_model;
		}
		
		/**
		 * set 锻造成功后进阶至披风ID
		 */
		public function set q_next_model(value: int): void{
			this._q_next_model = value;
		}
		
		/**
		 * get 披风最大星数
		 * @return 
		 */
		public function get q_maxstar(): int{
			return _q_maxstar;
		}
		
		/**
		 * set 披风最大星数
		 */
		public function set q_maxstar(value: int): void{
			this._q_maxstar = value;
		}
		
		/**
		 * get 对应q_common_attribute的id
		 * @return 
		 */
		public function get q_attribute(): int{
			return _q_attribute;
		}
		
		/**
		 * set 对应q_common_attribute的id
		 */
		public function set q_attribute(value: int): void{
			this._q_attribute = value;
		}
		
		/**
		 * get 对应q_common_levelup的id
		 * @return 
		 */
		public function get q_levelup(): int{
			return _q_levelup;
		}
		
		/**
		 * set 对应q_common_levelup的id
		 */
		public function set q_levelup(value: int): void{
			this._q_levelup = value;
		}
		
		/**
		 * get 披风名称美术资源编号
		 * @return 
		 */
		public function get q_name_src(): String{
			return _q_name_src;
		}
		
		/**
		 * set 披风名称美术资源编号
		 */
		public function set q_name_src(value: String): void{
			this._q_name_src = value;
		}
		
		/**
		 * get 面板造型资源编号
		 * @return 
		 */
		public function get q_panel_src(): String{
			return _q_panel_src;
		}
		
		/**
		 * set 面板造型资源编号
		 */
		public function set q_panel_src(value: String): void{
			this._q_panel_src = value;
		}
		
		/**
		 * get 场景造型资源编号
		 * @return 
		 */
		public function get q_scene_src(): String{
			return _q_scene_src;
		}
		
		/**
		 * set 场景造型资源编号
		 */
		public function set q_scene_src(value: String): void{
			this._q_scene_src = value;
		}
		
		/**
		 * get 人物属性面板手持面板造型资源
		 * @return 
		 */
		public function get q_main_panel_src(): String{
			return _q_main_panel_src;
		}
		
		/**
		 * set 人物属性面板手持面板造型资源
		 */
		public function set q_main_panel_src(value: String): void{
			this._q_main_panel_src = value;
		}
		
	}
}