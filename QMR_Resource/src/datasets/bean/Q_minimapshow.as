package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_minimapshow
	 */
	public class Q_minimapshow extends Bean{
	
		//地图编号
		private var _q_mapid: int;
		
		//怪物("|":区分相同怪物,";":区分不同怪物 10087_100_100 表示：怪物ID为10087的怪物会在对应地图的X,Y坐标分别为100,100的位置刷新，相同类型怪物在同一张地图内会存在多个刷新点，配置的第一个刷新点为小地图上显示的标记位置。当玩家点击怪物名称执行自动寻径逻辑时，如果配置表中相同怪物存在多个刷新点则客户端会让玩家寻径到随机的刷怪点)
		private var _q_monster_coord: String;
		
		//适合练级等级（min）
		private var _q_min_lv: int;
		
		//适合练级等级（max）
		private var _q_max_lv: int;
		
		//跨地图寻路点
		private var _q_movepoint: String;
		
		//怪物刷新点不出现在该小地图中
		private var _q_noappear: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//地图编号
			_q_mapid = readInt();
			//怪物("|":区分相同怪物,";":区分不同怪物 10087_100_100 表示：怪物ID为10087的怪物会在对应地图的X,Y坐标分别为100,100的位置刷新，相同类型怪物在同一张地图内会存在多个刷新点，配置的第一个刷新点为小地图上显示的标记位置。当玩家点击怪物名称执行自动寻径逻辑时，如果配置表中相同怪物存在多个刷新点则客户端会让玩家寻径到随机的刷怪点)
			_q_monster_coord = readString();
			//适合练级等级（min）
			_q_min_lv = readInt();
			//适合练级等级（max）
			_q_max_lv = readInt();
			//跨地图寻路点
			_q_movepoint = readString();
			//怪物刷新点不出现在该小地图中
			_q_noappear = readString();
			return true;
		}
		
		/**
		 * get 地图编号
		 * @return 
		 */
		public function get q_mapid(): int{
			return _q_mapid;
		}
		
		/**
		 * set 地图编号
		 */
		public function set q_mapid(value: int): void{
			this._q_mapid = value;
		}
		
		/**
		 * get 怪物("|":区分相同怪物,";":区分不同怪物 10087_100_100 表示：怪物ID为10087的怪物会在对应地图的X,Y坐标分别为100,100的位置刷新，相同类型怪物在同一张地图内会存在多个刷新点，配置的第一个刷新点为小地图上显示的标记位置。当玩家点击怪物名称执行自动寻径逻辑时，如果配置表中相同怪物存在多个刷新点则客户端会让玩家寻径到随机的刷怪点)
		 * @return 
		 */
		public function get q_monster_coord(): String{
			return _q_monster_coord;
		}
		
		/**
		 * set 怪物("|":区分相同怪物,";":区分不同怪物 10087_100_100 表示：怪物ID为10087的怪物会在对应地图的X,Y坐标分别为100,100的位置刷新，相同类型怪物在同一张地图内会存在多个刷新点，配置的第一个刷新点为小地图上显示的标记位置。当玩家点击怪物名称执行自动寻径逻辑时，如果配置表中相同怪物存在多个刷新点则客户端会让玩家寻径到随机的刷怪点)
		 */
		public function set q_monster_coord(value: String): void{
			this._q_monster_coord = value;
		}
		
		/**
		 * get 适合练级等级（min）
		 * @return 
		 */
		public function get q_min_lv(): int{
			return _q_min_lv;
		}
		
		/**
		 * set 适合练级等级（min）
		 */
		public function set q_min_lv(value: int): void{
			this._q_min_lv = value;
		}
		
		/**
		 * get 适合练级等级（max）
		 * @return 
		 */
		public function get q_max_lv(): int{
			return _q_max_lv;
		}
		
		/**
		 * set 适合练级等级（max）
		 */
		public function set q_max_lv(value: int): void{
			this._q_max_lv = value;
		}
		
		/**
		 * get 跨地图寻路点
		 * @return 
		 */
		public function get q_movepoint(): String{
			return _q_movepoint;
		}
		
		/**
		 * set 跨地图寻路点
		 */
		public function set q_movepoint(value: String): void{
			this._q_movepoint = value;
		}
		
		/**
		 * get 怪物刷新点不出现在该小地图中
		 * @return 
		 */
		public function get q_noappear(): String{
			return _q_noappear;
		}
		
		/**
		 * set 怪物刷新点不出现在该小地图中
		 */
		public function set q_noappear(value: String): void{
			this._q_noappear = value;
		}
		
	}
}