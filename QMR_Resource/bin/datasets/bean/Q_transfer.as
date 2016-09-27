package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_transfer
	 */
	public class Q_transfer extends Bean{
	
		//传送点编号
		private var _q_tran_id: int;
		
		//开始传送地图ID
		private var _q_tran_from_map: int;
		
		//传送出发点坐标，半径（格式：{X,Y}#半径）
		private var _q_tran_from_range: String;
		
		//传送至地图ID
		private var _q_tran_to_map: int;
		
		//传送至坐标点，半径，判定等级（格式：{X,Y}#半径@判断等级 ）
		private var _q_tran_to_range: String;
		
		//传送点造型资源编号
		private var _q_tran_icon: String;
		
		//地图名称描述（小地图TIPS，支持xml）
		private var _q_mapdesc: String;
		
		//脚本调用（填写后传送至坐标点将无效）
		private var _q_scriptid: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//传送点编号
			_q_tran_id = readInt();
			//开始传送地图ID
			_q_tran_from_map = readInt();
			//传送出发点坐标，半径（格式：{X,Y}#半径）
			_q_tran_from_range = readString();
			//传送至地图ID
			_q_tran_to_map = readInt();
			//传送至坐标点，半径，判定等级（格式：{X,Y}#半径@判断等级 ）
			_q_tran_to_range = readString();
			//传送点造型资源编号
			_q_tran_icon = readString();
			//地图名称描述（小地图TIPS，支持xml）
			_q_mapdesc = readString();
			//脚本调用（填写后传送至坐标点将无效）
			_q_scriptid = readInt();
			return true;
		}
		
		/**
		 * get 传送点编号
		 * @return 
		 */
		public function get q_tran_id(): int{
			return _q_tran_id;
		}
		
		/**
		 * set 传送点编号
		 */
		public function set q_tran_id(value: int): void{
			this._q_tran_id = value;
		}
		
		/**
		 * get 开始传送地图ID
		 * @return 
		 */
		public function get q_tran_from_map(): int{
			return _q_tran_from_map;
		}
		
		/**
		 * set 开始传送地图ID
		 */
		public function set q_tran_from_map(value: int): void{
			this._q_tran_from_map = value;
		}
		
		/**
		 * get 传送出发点坐标，半径（格式：{X,Y}#半径）
		 * @return 
		 */
		public function get q_tran_from_range(): String{
			return _q_tran_from_range;
		}
		
		/**
		 * set 传送出发点坐标，半径（格式：{X,Y}#半径）
		 */
		public function set q_tran_from_range(value: String): void{
			this._q_tran_from_range = value;
		}
		
		/**
		 * get 传送至地图ID
		 * @return 
		 */
		public function get q_tran_to_map(): int{
			return _q_tran_to_map;
		}
		
		/**
		 * set 传送至地图ID
		 */
		public function set q_tran_to_map(value: int): void{
			this._q_tran_to_map = value;
		}
		
		/**
		 * get 传送至坐标点，半径，判定等级（格式：{X,Y}#半径@判断等级 ）
		 * @return 
		 */
		public function get q_tran_to_range(): String{
			return _q_tran_to_range;
		}
		
		/**
		 * set 传送至坐标点，半径，判定等级（格式：{X,Y}#半径@判断等级 ）
		 */
		public function set q_tran_to_range(value: String): void{
			this._q_tran_to_range = value;
		}
		
		/**
		 * get 传送点造型资源编号
		 * @return 
		 */
		public function get q_tran_icon(): String{
			return _q_tran_icon;
		}
		
		/**
		 * set 传送点造型资源编号
		 */
		public function set q_tran_icon(value: String): void{
			this._q_tran_icon = value;
		}
		
		/**
		 * get 地图名称描述（小地图TIPS，支持xml）
		 * @return 
		 */
		public function get q_mapdesc(): String{
			return _q_mapdesc;
		}
		
		/**
		 * set 地图名称描述（小地图TIPS，支持xml）
		 */
		public function set q_mapdesc(value: String): void{
			this._q_mapdesc = value;
		}
		
		/**
		 * get 脚本调用（填写后传送至坐标点将无效）
		 * @return 
		 */
		public function get q_scriptid(): int{
			return _q_scriptid;
		}
		
		/**
		 * set 脚本调用（填写后传送至坐标点将无效）
		 */
		public function set q_scriptid(value: int): void{
			this._q_scriptid = value;
		}
		
	}
}