package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_scene_obj
	 */
	public class Q_scene_obj extends Bean{
	
		//id
		private var _q_id: int;
		
		//NPCID
		private var _q_npc_id: String;
		
		//怪物ID[怪物ID,怪物ID]
		private var _q_monster_id: String;
		
		//刷新时间
		private var _q_refresh_time: String;
		
		//刷新地图
		private var _q_refresh_map: int;
		
		//刷新坐标
		private var _q_refresh_coordinate: String;
		
		//附带奖励
		private var _q_reward: String;
		
		//调用类型(服务器端调用的类)
		private var _q_class: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//id
			_q_id = readInt();
			//NPCID
			_q_npc_id = readString();
			//怪物ID[怪物ID,怪物ID]
			_q_monster_id = readString();
			//刷新时间
			_q_refresh_time = readString();
			//刷新地图
			_q_refresh_map = readInt();
			//刷新坐标
			_q_refresh_coordinate = readString();
			//附带奖励
			_q_reward = readString();
			//调用类型(服务器端调用的类)
			_q_class = readString();
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
		 * get NPCID
		 * @return 
		 */
		public function get q_npc_id(): String{
			return _q_npc_id;
		}
		
		/**
		 * set NPCID
		 */
		public function set q_npc_id(value: String): void{
			this._q_npc_id = value;
		}
		
		/**
		 * get 怪物ID[怪物ID,怪物ID]
		 * @return 
		 */
		public function get q_monster_id(): String{
			return _q_monster_id;
		}
		
		/**
		 * set 怪物ID[怪物ID,怪物ID]
		 */
		public function set q_monster_id(value: String): void{
			this._q_monster_id = value;
		}
		
		/**
		 * get 刷新时间
		 * @return 
		 */
		public function get q_refresh_time(): String{
			return _q_refresh_time;
		}
		
		/**
		 * set 刷新时间
		 */
		public function set q_refresh_time(value: String): void{
			this._q_refresh_time = value;
		}
		
		/**
		 * get 刷新地图
		 * @return 
		 */
		public function get q_refresh_map(): int{
			return _q_refresh_map;
		}
		
		/**
		 * set 刷新地图
		 */
		public function set q_refresh_map(value: int): void{
			this._q_refresh_map = value;
		}
		
		/**
		 * get 刷新坐标
		 * @return 
		 */
		public function get q_refresh_coordinate(): String{
			return _q_refresh_coordinate;
		}
		
		/**
		 * set 刷新坐标
		 */
		public function set q_refresh_coordinate(value: String): void{
			this._q_refresh_coordinate = value;
		}
		
		/**
		 * get 附带奖励
		 * @return 
		 */
		public function get q_reward(): String{
			return _q_reward;
		}
		
		/**
		 * set 附带奖励
		 */
		public function set q_reward(value: String): void{
			this._q_reward = value;
		}
		
		/**
		 * get 调用类型(服务器端调用的类)
		 * @return 
		 */
		public function get q_class(): String{
			return _q_class;
		}
		
		/**
		 * set 调用类型(服务器端调用的类)
		 */
		public function set q_class(value: String): void{
			this._q_class = value;
		}
		
	}
}