package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_scene_monster
	 */
	public class Q_scene_monster extends Bean{
	
		//刷怪编号
		private var _q_id: int;
		
		//刷怪地图编号
		private var _q_mapid: int;
		
		//怪物ID
		private var _q_monster_model: int;
		
		//刷新坐标点X
		private var _q_x: int;
		
		//刷新坐标点Y
		private var _q_y: int;
		
		//重生时间脱离怪物数据库控制变更为：0本规则不启用， >0则为变更后的重生时间，单位：秒
		private var _q_relive: int;
		
		//攻击类型脱离怪物数据库控制变更为：0本规则不启用，1变更为被动攻击类怪物，2变更为主动攻击类怪物
		private var _q_attack: int;
		
		//巡逻间隔
		private var _q_patrol_time: int;
		
		//巡逻几率
		private var _q_patrol_pro: int;
		
		//是否显示怪物
		private var _q_whether_display: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//刷怪编号
			_q_id = readInt();
			//刷怪地图编号
			_q_mapid = readInt();
			//怪物ID
			_q_monster_model = readInt();
			//刷新坐标点X
			_q_x = readInt();
			//刷新坐标点Y
			_q_y = readInt();
			//重生时间脱离怪物数据库控制变更为：0本规则不启用， >0则为变更后的重生时间，单位：秒
			_q_relive = readInt();
			//攻击类型脱离怪物数据库控制变更为：0本规则不启用，1变更为被动攻击类怪物，2变更为主动攻击类怪物
			_q_attack = readInt();
			//巡逻间隔
			_q_patrol_time = readInt();
			//巡逻几率
			_q_patrol_pro = readInt();
			//是否显示怪物
			_q_whether_display = readInt();
			return true;
		}
		
		/**
		 * get 刷怪编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 刷怪编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 刷怪地图编号
		 * @return 
		 */
		public function get q_mapid(): int{
			return _q_mapid;
		}
		
		/**
		 * set 刷怪地图编号
		 */
		public function set q_mapid(value: int): void{
			this._q_mapid = value;
		}
		
		/**
		 * get 怪物ID
		 * @return 
		 */
		public function get q_monster_model(): int{
			return _q_monster_model;
		}
		
		/**
		 * set 怪物ID
		 */
		public function set q_monster_model(value: int): void{
			this._q_monster_model = value;
		}
		
		/**
		 * get 刷新坐标点X
		 * @return 
		 */
		public function get q_x(): int{
			return _q_x;
		}
		
		/**
		 * set 刷新坐标点X
		 */
		public function set q_x(value: int): void{
			this._q_x = value;
		}
		
		/**
		 * get 刷新坐标点Y
		 * @return 
		 */
		public function get q_y(): int{
			return _q_y;
		}
		
		/**
		 * set 刷新坐标点Y
		 */
		public function set q_y(value: int): void{
			this._q_y = value;
		}
		
		/**
		 * get 重生时间脱离怪物数据库控制变更为：0本规则不启用， >0则为变更后的重生时间，单位：秒
		 * @return 
		 */
		public function get q_relive(): int{
			return _q_relive;
		}
		
		/**
		 * set 重生时间脱离怪物数据库控制变更为：0本规则不启用， >0则为变更后的重生时间，单位：秒
		 */
		public function set q_relive(value: int): void{
			this._q_relive = value;
		}
		
		/**
		 * get 攻击类型脱离怪物数据库控制变更为：0本规则不启用，1变更为被动攻击类怪物，2变更为主动攻击类怪物
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 攻击类型脱离怪物数据库控制变更为：0本规则不启用，1变更为被动攻击类怪物，2变更为主动攻击类怪物
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 巡逻间隔
		 * @return 
		 */
		public function get q_patrol_time(): int{
			return _q_patrol_time;
		}
		
		/**
		 * set 巡逻间隔
		 */
		public function set q_patrol_time(value: int): void{
			this._q_patrol_time = value;
		}
		
		/**
		 * get 巡逻几率
		 * @return 
		 */
		public function get q_patrol_pro(): int{
			return _q_patrol_pro;
		}
		
		/**
		 * set 巡逻几率
		 */
		public function set q_patrol_pro(value: int): void{
			this._q_patrol_pro = value;
		}
		
		/**
		 * get 是否显示怪物
		 * @return 
		 */
		public function get q_whether_display(): int{
			return _q_whether_display;
		}
		
		/**
		 * set 是否显示怪物
		 */
		public function set q_whether_display(value: int): void{
			this._q_whether_display = value;
		}
		
	}
}