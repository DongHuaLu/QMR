package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_map
	 */
	public class Q_map extends Bean{
	
		//地图中文名
		private var _q_map_name: String;
		
		//地图ID
		private var _q_map_id: int;
		
		//地图资源ID
		private var _q_mapresid: int;
		
		//地图宽度
		private var _q_map_width: int;
		
		//地图高度
		private var _q_map_height: int;
		
		//进入等级下限
		private var _q_map_min_level: int;
		
		//进入等级上限
		private var _q_map_max_level: int;
		
		//是否安全区(0战斗区域,1安全区域)
		private var _q_map_safe: int;
		
		//是否可跳跃(0不可跳跃,1可跳跃)
		private var _q_map_jump: int;
		
		//是否可骑乘(0不可骑乘,1可骑乘)
		private var _q_map_ride: int;
		
		//是否可展示坐骑(0不允许,1允许)
		private var _q_map_show: int;
		
		//被PK死亡后是否给予和平保护BUFF（0给予，1不给予）
		private var _q_map_buff: int;
		
		//等级差距过大是否可PK（0可PK，1不可PK）
		private var _q_map_pk: int;
		
		//是否可以PK新人（0可PK，1不可PK）
		private var _q_map_pkprotection: int;
		
		//夜晚挂机是否开启挂机保护（0不开启，1开启）
		private var _q_map_hangprotection: int;
		
		//死亡回到地图ID
		private var _q_map_die: int;
		
		//回到X坐标
		private var _q_map_die_x: int;
		
		//回到Y坐标
		private var _q_map_die_y: int;
		
		//下线回到地图ID
		private var _q_map_quit: int;
		
		//下线回到X坐标
		private var _q_map_quit_x: int;
		
		//下线回到Y坐标
		private var _q_map_quit_y: int;
		
		//地图开放线路（格式 线id|线id）
		private var _q_map_lines: String;
		
		//是否为公共区地图（0普通 1公共区）
		private var _q_map_public: int;
		
		//是否为副本（0普通 1副本）
		private var _q_map_zones: int;
		
		//承载最大人数
		private var _q_max_online: int;
		
		//本地图默认帮旗，0不可插，其他填指定怪物ID
		private var _q_default_flag: int;
		
		//帮旗坐标[x,y]
		private var _q_flag_pos: String;
		
		//默认开启线路
		private var _q_default_line: int;
		
		//轮询人数
		private var _q_poll_num: int;
		
		//音乐关联（音乐资源编号_播放完后空闲秒数;音乐资源编号_播放完后空闲秒数)
		private var _q_music: String;
		
		//地图是否是附属地图（1是，0否）
		private var _q_map_subsidiary: int;
		
		//附属地图的主地图id
		private var _q_subsidiary_main: int;
		
		//死亡随机回城坐标（x_y;x_y）
		private var _q_rnd_die_xy: String;
		
		//退出随机回城坐标（x_y;x_y）
		private var _q_rnd_quit_xy: String;
		
		//关联BOSSid（寻径时，找有BOSS的地图）
		private var _q_boss_id: int;
		
		//是否可用玫瑰复活（0是，1否）
		private var _q_rose_resurrection: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//地图中文名
			_q_map_name = readString();
			//地图ID
			_q_map_id = readInt();
			//地图资源ID
			_q_mapresid = readInt();
			//地图宽度
			_q_map_width = readInt();
			//地图高度
			_q_map_height = readInt();
			//进入等级下限
			_q_map_min_level = readInt();
			//进入等级上限
			_q_map_max_level = readInt();
			//是否安全区(0战斗区域,1安全区域)
			_q_map_safe = readInt();
			//是否可跳跃(0不可跳跃,1可跳跃)
			_q_map_jump = readInt();
			//是否可骑乘(0不可骑乘,1可骑乘)
			_q_map_ride = readInt();
			//是否可展示坐骑(0不允许,1允许)
			_q_map_show = readInt();
			//被PK死亡后是否给予和平保护BUFF（0给予，1不给予）
			_q_map_buff = readInt();
			//等级差距过大是否可PK（0可PK，1不可PK）
			_q_map_pk = readInt();
			//是否可以PK新人（0可PK，1不可PK）
			_q_map_pkprotection = readInt();
			//夜晚挂机是否开启挂机保护（0不开启，1开启）
			_q_map_hangprotection = readInt();
			//死亡回到地图ID
			_q_map_die = readInt();
			//回到X坐标
			_q_map_die_x = readInt();
			//回到Y坐标
			_q_map_die_y = readInt();
			//下线回到地图ID
			_q_map_quit = readInt();
			//下线回到X坐标
			_q_map_quit_x = readInt();
			//下线回到Y坐标
			_q_map_quit_y = readInt();
			//地图开放线路（格式 线id|线id）
			_q_map_lines = readString();
			//是否为公共区地图（0普通 1公共区）
			_q_map_public = readInt();
			//是否为副本（0普通 1副本）
			_q_map_zones = readInt();
			//承载最大人数
			_q_max_online = readInt();
			//本地图默认帮旗，0不可插，其他填指定怪物ID
			_q_default_flag = readInt();
			//帮旗坐标[x,y]
			_q_flag_pos = readString();
			//默认开启线路
			_q_default_line = readInt();
			//轮询人数
			_q_poll_num = readInt();
			//音乐关联（音乐资源编号_播放完后空闲秒数;音乐资源编号_播放完后空闲秒数)
			_q_music = readString();
			//地图是否是附属地图（1是，0否）
			_q_map_subsidiary = readInt();
			//附属地图的主地图id
			_q_subsidiary_main = readInt();
			//死亡随机回城坐标（x_y;x_y）
			_q_rnd_die_xy = readString();
			//退出随机回城坐标（x_y;x_y）
			_q_rnd_quit_xy = readString();
			//关联BOSSid（寻径时，找有BOSS的地图）
			_q_boss_id = readInt();
			//是否可用玫瑰复活（0是，1否）
			_q_rose_resurrection = readInt();
			return true;
		}
		
		/**
		 * get 地图中文名
		 * @return 
		 */
		public function get q_map_name(): String{
			return _q_map_name;
		}
		
		/**
		 * set 地图中文名
		 */
		public function set q_map_name(value: String): void{
			this._q_map_name = value;
		}
		
		/**
		 * get 地图ID
		 * @return 
		 */
		public function get q_map_id(): int{
			return _q_map_id;
		}
		
		/**
		 * set 地图ID
		 */
		public function set q_map_id(value: int): void{
			this._q_map_id = value;
		}
		
		/**
		 * get 地图资源ID
		 * @return 
		 */
		public function get q_mapresid(): int{
			return _q_mapresid;
		}
		
		/**
		 * set 地图资源ID
		 */
		public function set q_mapresid(value: int): void{
			this._q_mapresid = value;
		}
		
		/**
		 * get 地图宽度
		 * @return 
		 */
		public function get q_map_width(): int{
			return _q_map_width;
		}
		
		/**
		 * set 地图宽度
		 */
		public function set q_map_width(value: int): void{
			this._q_map_width = value;
		}
		
		/**
		 * get 地图高度
		 * @return 
		 */
		public function get q_map_height(): int{
			return _q_map_height;
		}
		
		/**
		 * set 地图高度
		 */
		public function set q_map_height(value: int): void{
			this._q_map_height = value;
		}
		
		/**
		 * get 进入等级下限
		 * @return 
		 */
		public function get q_map_min_level(): int{
			return _q_map_min_level;
		}
		
		/**
		 * set 进入等级下限
		 */
		public function set q_map_min_level(value: int): void{
			this._q_map_min_level = value;
		}
		
		/**
		 * get 进入等级上限
		 * @return 
		 */
		public function get q_map_max_level(): int{
			return _q_map_max_level;
		}
		
		/**
		 * set 进入等级上限
		 */
		public function set q_map_max_level(value: int): void{
			this._q_map_max_level = value;
		}
		
		/**
		 * get 是否安全区(0战斗区域,1安全区域)
		 * @return 
		 */
		public function get q_map_safe(): int{
			return _q_map_safe;
		}
		
		/**
		 * set 是否安全区(0战斗区域,1安全区域)
		 */
		public function set q_map_safe(value: int): void{
			this._q_map_safe = value;
		}
		
		/**
		 * get 是否可跳跃(0不可跳跃,1可跳跃)
		 * @return 
		 */
		public function get q_map_jump(): int{
			return _q_map_jump;
		}
		
		/**
		 * set 是否可跳跃(0不可跳跃,1可跳跃)
		 */
		public function set q_map_jump(value: int): void{
			this._q_map_jump = value;
		}
		
		/**
		 * get 是否可骑乘(0不可骑乘,1可骑乘)
		 * @return 
		 */
		public function get q_map_ride(): int{
			return _q_map_ride;
		}
		
		/**
		 * set 是否可骑乘(0不可骑乘,1可骑乘)
		 */
		public function set q_map_ride(value: int): void{
			this._q_map_ride = value;
		}
		
		/**
		 * get 是否可展示坐骑(0不允许,1允许)
		 * @return 
		 */
		public function get q_map_show(): int{
			return _q_map_show;
		}
		
		/**
		 * set 是否可展示坐骑(0不允许,1允许)
		 */
		public function set q_map_show(value: int): void{
			this._q_map_show = value;
		}
		
		/**
		 * get 被PK死亡后是否给予和平保护BUFF（0给予，1不给予）
		 * @return 
		 */
		public function get q_map_buff(): int{
			return _q_map_buff;
		}
		
		/**
		 * set 被PK死亡后是否给予和平保护BUFF（0给予，1不给予）
		 */
		public function set q_map_buff(value: int): void{
			this._q_map_buff = value;
		}
		
		/**
		 * get 等级差距过大是否可PK（0可PK，1不可PK）
		 * @return 
		 */
		public function get q_map_pk(): int{
			return _q_map_pk;
		}
		
		/**
		 * set 等级差距过大是否可PK（0可PK，1不可PK）
		 */
		public function set q_map_pk(value: int): void{
			this._q_map_pk = value;
		}
		
		/**
		 * get 是否可以PK新人（0可PK，1不可PK）
		 * @return 
		 */
		public function get q_map_pkprotection(): int{
			return _q_map_pkprotection;
		}
		
		/**
		 * set 是否可以PK新人（0可PK，1不可PK）
		 */
		public function set q_map_pkprotection(value: int): void{
			this._q_map_pkprotection = value;
		}
		
		/**
		 * get 夜晚挂机是否开启挂机保护（0不开启，1开启）
		 * @return 
		 */
		public function get q_map_hangprotection(): int{
			return _q_map_hangprotection;
		}
		
		/**
		 * set 夜晚挂机是否开启挂机保护（0不开启，1开启）
		 */
		public function set q_map_hangprotection(value: int): void{
			this._q_map_hangprotection = value;
		}
		
		/**
		 * get 死亡回到地图ID
		 * @return 
		 */
		public function get q_map_die(): int{
			return _q_map_die;
		}
		
		/**
		 * set 死亡回到地图ID
		 */
		public function set q_map_die(value: int): void{
			this._q_map_die = value;
		}
		
		/**
		 * get 回到X坐标
		 * @return 
		 */
		public function get q_map_die_x(): int{
			return _q_map_die_x;
		}
		
		/**
		 * set 回到X坐标
		 */
		public function set q_map_die_x(value: int): void{
			this._q_map_die_x = value;
		}
		
		/**
		 * get 回到Y坐标
		 * @return 
		 */
		public function get q_map_die_y(): int{
			return _q_map_die_y;
		}
		
		/**
		 * set 回到Y坐标
		 */
		public function set q_map_die_y(value: int): void{
			this._q_map_die_y = value;
		}
		
		/**
		 * get 下线回到地图ID
		 * @return 
		 */
		public function get q_map_quit(): int{
			return _q_map_quit;
		}
		
		/**
		 * set 下线回到地图ID
		 */
		public function set q_map_quit(value: int): void{
			this._q_map_quit = value;
		}
		
		/**
		 * get 下线回到X坐标
		 * @return 
		 */
		public function get q_map_quit_x(): int{
			return _q_map_quit_x;
		}
		
		/**
		 * set 下线回到X坐标
		 */
		public function set q_map_quit_x(value: int): void{
			this._q_map_quit_x = value;
		}
		
		/**
		 * get 下线回到Y坐标
		 * @return 
		 */
		public function get q_map_quit_y(): int{
			return _q_map_quit_y;
		}
		
		/**
		 * set 下线回到Y坐标
		 */
		public function set q_map_quit_y(value: int): void{
			this._q_map_quit_y = value;
		}
		
		/**
		 * get 地图开放线路（格式 线id|线id）
		 * @return 
		 */
		public function get q_map_lines(): String{
			return _q_map_lines;
		}
		
		/**
		 * set 地图开放线路（格式 线id|线id）
		 */
		public function set q_map_lines(value: String): void{
			this._q_map_lines = value;
		}
		
		/**
		 * get 是否为公共区地图（0普通 1公共区）
		 * @return 
		 */
		public function get q_map_public(): int{
			return _q_map_public;
		}
		
		/**
		 * set 是否为公共区地图（0普通 1公共区）
		 */
		public function set q_map_public(value: int): void{
			this._q_map_public = value;
		}
		
		/**
		 * get 是否为副本（0普通 1副本）
		 * @return 
		 */
		public function get q_map_zones(): int{
			return _q_map_zones;
		}
		
		/**
		 * set 是否为副本（0普通 1副本）
		 */
		public function set q_map_zones(value: int): void{
			this._q_map_zones = value;
		}
		
		/**
		 * get 承载最大人数
		 * @return 
		 */
		public function get q_max_online(): int{
			return _q_max_online;
		}
		
		/**
		 * set 承载最大人数
		 */
		public function set q_max_online(value: int): void{
			this._q_max_online = value;
		}
		
		/**
		 * get 本地图默认帮旗，0不可插，其他填指定怪物ID
		 * @return 
		 */
		public function get q_default_flag(): int{
			return _q_default_flag;
		}
		
		/**
		 * set 本地图默认帮旗，0不可插，其他填指定怪物ID
		 */
		public function set q_default_flag(value: int): void{
			this._q_default_flag = value;
		}
		
		/**
		 * get 帮旗坐标[x,y]
		 * @return 
		 */
		public function get q_flag_pos(): String{
			return _q_flag_pos;
		}
		
		/**
		 * set 帮旗坐标[x,y]
		 */
		public function set q_flag_pos(value: String): void{
			this._q_flag_pos = value;
		}
		
		/**
		 * get 默认开启线路
		 * @return 
		 */
		public function get q_default_line(): int{
			return _q_default_line;
		}
		
		/**
		 * set 默认开启线路
		 */
		public function set q_default_line(value: int): void{
			this._q_default_line = value;
		}
		
		/**
		 * get 轮询人数
		 * @return 
		 */
		public function get q_poll_num(): int{
			return _q_poll_num;
		}
		
		/**
		 * set 轮询人数
		 */
		public function set q_poll_num(value: int): void{
			this._q_poll_num = value;
		}
		
		/**
		 * get 音乐关联（音乐资源编号_播放完后空闲秒数;音乐资源编号_播放完后空闲秒数)
		 * @return 
		 */
		public function get q_music(): String{
			return _q_music;
		}
		
		/**
		 * set 音乐关联（音乐资源编号_播放完后空闲秒数;音乐资源编号_播放完后空闲秒数)
		 */
		public function set q_music(value: String): void{
			this._q_music = value;
		}
		
		/**
		 * get 地图是否是附属地图（1是，0否）
		 * @return 
		 */
		public function get q_map_subsidiary(): int{
			return _q_map_subsidiary;
		}
		
		/**
		 * set 地图是否是附属地图（1是，0否）
		 */
		public function set q_map_subsidiary(value: int): void{
			this._q_map_subsidiary = value;
		}
		
		/**
		 * get 附属地图的主地图id
		 * @return 
		 */
		public function get q_subsidiary_main(): int{
			return _q_subsidiary_main;
		}
		
		/**
		 * set 附属地图的主地图id
		 */
		public function set q_subsidiary_main(value: int): void{
			this._q_subsidiary_main = value;
		}
		
		/**
		 * get 死亡随机回城坐标（x_y;x_y）
		 * @return 
		 */
		public function get q_rnd_die_xy(): String{
			return _q_rnd_die_xy;
		}
		
		/**
		 * set 死亡随机回城坐标（x_y;x_y）
		 */
		public function set q_rnd_die_xy(value: String): void{
			this._q_rnd_die_xy = value;
		}
		
		/**
		 * get 退出随机回城坐标（x_y;x_y）
		 * @return 
		 */
		public function get q_rnd_quit_xy(): String{
			return _q_rnd_quit_xy;
		}
		
		/**
		 * set 退出随机回城坐标（x_y;x_y）
		 */
		public function set q_rnd_quit_xy(value: String): void{
			this._q_rnd_quit_xy = value;
		}
		
		/**
		 * get 关联BOSSid（寻径时，找有BOSS的地图）
		 * @return 
		 */
		public function get q_boss_id(): int{
			return _q_boss_id;
		}
		
		/**
		 * set 关联BOSSid（寻径时，找有BOSS的地图）
		 */
		public function set q_boss_id(value: int): void{
			this._q_boss_id = value;
		}
		
		/**
		 * get 是否可用玫瑰复活（0是，1否）
		 * @return 
		 */
		public function get q_rose_resurrection(): int{
			return _q_rose_resurrection;
		}
		
		/**
		 * set 是否可用玫瑰复活（0是，1否）
		 */
		public function set q_rose_resurrection(value: int): void{
			this._q_rose_resurrection = value;
		}
		
	}
}