package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_clone_activity
	 */
	public class Q_clone_activity extends Bean{
	
		//副本编号
		private var _q_id: int;
		
		//副本类型(小标签)，1小地图副本，2活动日常,3多人活动
		private var _q_zone_type: int;
		
		//副本关联地图编号[xxxx,xxxx]
		private var _q_mapid: String;
		
		//副本名称（支持HTML）
		private var _q_duplicate_name: String;
		
		//副本存在时间（毫秒）
		private var _q_exist_time: int;
		
		//开放时间
		private var _q_open_time: String;
		
		//副本重置时间(type=1日，2=周，3=月)time=秒数
		private var _q_reset_time: String;
		
		//进入所需最小等级
		private var _q_min_lv: int;
		
		//最高等级进入限制
		private var _q_max_lv: int;
		
		//进入最少人数限制
		private var _q_min_num: int;
		
		//进入最多人数限制
		private var _q_max_num: int;
		
		//进入状态（1为单人，2为组队，3为不限）
		private var _q_type: int;
		
		//每日手动挑战次数(-1表示未开放)
		private var _q_manual_num: int;
		
		//每日扫荡次数
		private var _q_raids_num: int;
		
		//扫荡收取元宝
		private var _q_raids_yuanbao: int;
		
		//扫荡每分钟所需元宝
		private var _q_raids_min_yuanbao: int;
		
		//通关奖励(-1到-5)和ITEM表里的对应(-1铜币，-2元宝，-3真气，-4经验  -5绑定元宝)
		private var _q_reward: String;
		
		//副本时间评价（填写时间，精确到毫秒，如果玩家通关时间小于填写时间，则获得一颗星）
		private var _q_time_evaluate: int;
		
		//副本说明（支持HTML）
		private var _q_explain: String;
		
		//进入地图的默认坐标x
		private var _q_x: int;
		
		//进入地图的默认坐标y
		private var _q_y: int;
		
		//是否调用脚本,1调用，0不用
		private var _q_isscript: int;
		
		//活动参与奖励
		private var _q_Participation_Award: String;
		
		//通关随机奖励描述
		private var _q_Random_Description: String;
		
		//地图存在的BOSS（BOSSid,x,y）
		private var _q_map_boss: String;
		
		//是否分为多组，0不分组，>1 表示分为几组
		private var _q_map_group: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//副本编号
			_q_id = readInt();
			//副本类型(小标签)，1小地图副本，2活动日常,3多人活动
			_q_zone_type = readInt();
			//副本关联地图编号[xxxx,xxxx]
			_q_mapid = readString();
			//副本名称（支持HTML）
			_q_duplicate_name = readString();
			//副本存在时间（毫秒）
			_q_exist_time = readInt();
			//开放时间
			_q_open_time = readString();
			//副本重置时间(type=1日，2=周，3=月)time=秒数
			_q_reset_time = readString();
			//进入所需最小等级
			_q_min_lv = readInt();
			//最高等级进入限制
			_q_max_lv = readInt();
			//进入最少人数限制
			_q_min_num = readInt();
			//进入最多人数限制
			_q_max_num = readInt();
			//进入状态（1为单人，2为组队，3为不限）
			_q_type = readInt();
			//每日手动挑战次数(-1表示未开放)
			_q_manual_num = readInt();
			//每日扫荡次数
			_q_raids_num = readInt();
			//扫荡收取元宝
			_q_raids_yuanbao = readInt();
			//扫荡每分钟所需元宝
			_q_raids_min_yuanbao = readInt();
			//通关奖励(-1到-5)和ITEM表里的对应(-1铜币，-2元宝，-3真气，-4经验  -5绑定元宝)
			_q_reward = readString();
			//副本时间评价（填写时间，精确到毫秒，如果玩家通关时间小于填写时间，则获得一颗星）
			_q_time_evaluate = readInt();
			//副本说明（支持HTML）
			_q_explain = readString();
			//进入地图的默认坐标x
			_q_x = readInt();
			//进入地图的默认坐标y
			_q_y = readInt();
			//是否调用脚本,1调用，0不用
			_q_isscript = readInt();
			//活动参与奖励
			_q_Participation_Award = readString();
			//通关随机奖励描述
			_q_Random_Description = readString();
			//地图存在的BOSS（BOSSid,x,y）
			_q_map_boss = readString();
			//是否分为多组，0不分组，>1 表示分为几组
			_q_map_group = readInt();
			return true;
		}
		
		/**
		 * get 副本编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 副本编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 副本类型(小标签)，1小地图副本，2活动日常,3多人活动
		 * @return 
		 */
		public function get q_zone_type(): int{
			return _q_zone_type;
		}
		
		/**
		 * set 副本类型(小标签)，1小地图副本，2活动日常,3多人活动
		 */
		public function set q_zone_type(value: int): void{
			this._q_zone_type = value;
		}
		
		/**
		 * get 副本关联地图编号[xxxx,xxxx]
		 * @return 
		 */
		public function get q_mapid(): String{
			return _q_mapid;
		}
		
		/**
		 * set 副本关联地图编号[xxxx,xxxx]
		 */
		public function set q_mapid(value: String): void{
			this._q_mapid = value;
		}
		
		/**
		 * get 副本名称（支持HTML）
		 * @return 
		 */
		public function get q_duplicate_name(): String{
			return _q_duplicate_name;
		}
		
		/**
		 * set 副本名称（支持HTML）
		 */
		public function set q_duplicate_name(value: String): void{
			this._q_duplicate_name = value;
		}
		
		/**
		 * get 副本存在时间（毫秒）
		 * @return 
		 */
		public function get q_exist_time(): int{
			return _q_exist_time;
		}
		
		/**
		 * set 副本存在时间（毫秒）
		 */
		public function set q_exist_time(value: int): void{
			this._q_exist_time = value;
		}
		
		/**
		 * get 开放时间
		 * @return 
		 */
		public function get q_open_time(): String{
			return _q_open_time;
		}
		
		/**
		 * set 开放时间
		 */
		public function set q_open_time(value: String): void{
			this._q_open_time = value;
		}
		
		/**
		 * get 副本重置时间(type=1日，2=周，3=月)time=秒数
		 * @return 
		 */
		public function get q_reset_time(): String{
			return _q_reset_time;
		}
		
		/**
		 * set 副本重置时间(type=1日，2=周，3=月)time=秒数
		 */
		public function set q_reset_time(value: String): void{
			this._q_reset_time = value;
		}
		
		/**
		 * get 进入所需最小等级
		 * @return 
		 */
		public function get q_min_lv(): int{
			return _q_min_lv;
		}
		
		/**
		 * set 进入所需最小等级
		 */
		public function set q_min_lv(value: int): void{
			this._q_min_lv = value;
		}
		
		/**
		 * get 最高等级进入限制
		 * @return 
		 */
		public function get q_max_lv(): int{
			return _q_max_lv;
		}
		
		/**
		 * set 最高等级进入限制
		 */
		public function set q_max_lv(value: int): void{
			this._q_max_lv = value;
		}
		
		/**
		 * get 进入最少人数限制
		 * @return 
		 */
		public function get q_min_num(): int{
			return _q_min_num;
		}
		
		/**
		 * set 进入最少人数限制
		 */
		public function set q_min_num(value: int): void{
			this._q_min_num = value;
		}
		
		/**
		 * get 进入最多人数限制
		 * @return 
		 */
		public function get q_max_num(): int{
			return _q_max_num;
		}
		
		/**
		 * set 进入最多人数限制
		 */
		public function set q_max_num(value: int): void{
			this._q_max_num = value;
		}
		
		/**
		 * get 进入状态（1为单人，2为组队，3为不限）
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 进入状态（1为单人，2为组队，3为不限）
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 每日手动挑战次数(-1表示未开放)
		 * @return 
		 */
		public function get q_manual_num(): int{
			return _q_manual_num;
		}
		
		/**
		 * set 每日手动挑战次数(-1表示未开放)
		 */
		public function set q_manual_num(value: int): void{
			this._q_manual_num = value;
		}
		
		/**
		 * get 每日扫荡次数
		 * @return 
		 */
		public function get q_raids_num(): int{
			return _q_raids_num;
		}
		
		/**
		 * set 每日扫荡次数
		 */
		public function set q_raids_num(value: int): void{
			this._q_raids_num = value;
		}
		
		/**
		 * get 扫荡收取元宝
		 * @return 
		 */
		public function get q_raids_yuanbao(): int{
			return _q_raids_yuanbao;
		}
		
		/**
		 * set 扫荡收取元宝
		 */
		public function set q_raids_yuanbao(value: int): void{
			this._q_raids_yuanbao = value;
		}
		
		/**
		 * get 扫荡每分钟所需元宝
		 * @return 
		 */
		public function get q_raids_min_yuanbao(): int{
			return _q_raids_min_yuanbao;
		}
		
		/**
		 * set 扫荡每分钟所需元宝
		 */
		public function set q_raids_min_yuanbao(value: int): void{
			this._q_raids_min_yuanbao = value;
		}
		
		/**
		 * get 通关奖励(-1到-5)和ITEM表里的对应(-1铜币，-2元宝，-3真气，-4经验  -5绑定元宝)
		 * @return 
		 */
		public function get q_reward(): String{
			return _q_reward;
		}
		
		/**
		 * set 通关奖励(-1到-5)和ITEM表里的对应(-1铜币，-2元宝，-3真气，-4经验  -5绑定元宝)
		 */
		public function set q_reward(value: String): void{
			this._q_reward = value;
		}
		
		/**
		 * get 副本时间评价（填写时间，精确到毫秒，如果玩家通关时间小于填写时间，则获得一颗星）
		 * @return 
		 */
		public function get q_time_evaluate(): int{
			return _q_time_evaluate;
		}
		
		/**
		 * set 副本时间评价（填写时间，精确到毫秒，如果玩家通关时间小于填写时间，则获得一颗星）
		 */
		public function set q_time_evaluate(value: int): void{
			this._q_time_evaluate = value;
		}
		
		/**
		 * get 副本说明（支持HTML）
		 * @return 
		 */
		public function get q_explain(): String{
			return _q_explain;
		}
		
		/**
		 * set 副本说明（支持HTML）
		 */
		public function set q_explain(value: String): void{
			this._q_explain = value;
		}
		
		/**
		 * get 进入地图的默认坐标x
		 * @return 
		 */
		public function get q_x(): int{
			return _q_x;
		}
		
		/**
		 * set 进入地图的默认坐标x
		 */
		public function set q_x(value: int): void{
			this._q_x = value;
		}
		
		/**
		 * get 进入地图的默认坐标y
		 * @return 
		 */
		public function get q_y(): int{
			return _q_y;
		}
		
		/**
		 * set 进入地图的默认坐标y
		 */
		public function set q_y(value: int): void{
			this._q_y = value;
		}
		
		/**
		 * get 是否调用脚本,1调用，0不用
		 * @return 
		 */
		public function get q_isscript(): int{
			return _q_isscript;
		}
		
		/**
		 * set 是否调用脚本,1调用，0不用
		 */
		public function set q_isscript(value: int): void{
			this._q_isscript = value;
		}
		
		/**
		 * get 活动参与奖励
		 * @return 
		 */
		public function get q_Participation_Award(): String{
			return _q_Participation_Award;
		}
		
		/**
		 * set 活动参与奖励
		 */
		public function set q_Participation_Award(value: String): void{
			this._q_Participation_Award = value;
		}
		
		/**
		 * get 通关随机奖励描述
		 * @return 
		 */
		public function get q_Random_Description(): String{
			return _q_Random_Description;
		}
		
		/**
		 * set 通关随机奖励描述
		 */
		public function set q_Random_Description(value: String): void{
			this._q_Random_Description = value;
		}
		
		/**
		 * get 地图存在的BOSS（BOSSid,x,y）
		 * @return 
		 */
		public function get q_map_boss(): String{
			return _q_map_boss;
		}
		
		/**
		 * set 地图存在的BOSS（BOSSid,x,y）
		 */
		public function set q_map_boss(value: String): void{
			this._q_map_boss = value;
		}
		
		/**
		 * get 是否分为多组，0不分组，>1 表示分为几组
		 * @return 
		 */
		public function get q_map_group(): int{
			return _q_map_group;
		}
		
		/**
		 * set 是否分为多组，0不分组，>1 表示分为几组
		 */
		public function set q_map_group(value: int): void{
			this._q_map_group = value;
		}
		
	}
}