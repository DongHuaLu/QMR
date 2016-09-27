package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_vip
	 */
	public class Q_vip extends Bean{
	
		//VIP编号
		private var _q_id: int;
		
		//VIP名称
		private var _q_name: String;
		
		//文字描述
		private var _q_desc: String;
		
		//VIP卡物品ID
		private var _q_itemid: int;
		
		//VIP对应buffid
		private var _q_buffid: int;
		
		//每日领取的礼包id
		private var _q_dayreceivegiftid: int;
		
		//每日领取铜币
		private var _q_dayreceive_money: int;
		
		//每日领取礼金
		private var _q_dayreceive_lijin: int;
		
		//免费传送次数(-1表示无次数限制)
		private var _q_fly: int;
		
		//称号资源图
		private var _q_ico: int;
		
		//加速仙露
		private var _q_xianlu: int;
		
		//正面BUFF增加时间
		private var _q_pos_buff: int;
		
		//自动摘取果实(0-不自动 1-自动)
		private var _q_auto_fruit: int;
		
		//自动给果实浇水(0-不自动 1-自动)
		private var _q_auto_water: int;
		
		//副本扫荡次数
		private var _q_zone_time: int;
		
		//附加功能
		private var _q_append: String;
		
		//领取军功
		private var _q_rank: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//VIP编号
			_q_id = readInt();
			//VIP名称
			_q_name = readString();
			//文字描述
			_q_desc = readString();
			//VIP卡物品ID
			_q_itemid = readInt();
			//VIP对应buffid
			_q_buffid = readInt();
			//每日领取的礼包id
			_q_dayreceivegiftid = readInt();
			//每日领取铜币
			_q_dayreceive_money = readInt();
			//每日领取礼金
			_q_dayreceive_lijin = readInt();
			//免费传送次数(-1表示无次数限制)
			_q_fly = readInt();
			//称号资源图
			_q_ico = readInt();
			//加速仙露
			_q_xianlu = readInt();
			//正面BUFF增加时间
			_q_pos_buff = readInt();
			//自动摘取果实(0-不自动 1-自动)
			_q_auto_fruit = readInt();
			//自动给果实浇水(0-不自动 1-自动)
			_q_auto_water = readInt();
			//副本扫荡次数
			_q_zone_time = readInt();
			//附加功能
			_q_append = readString();
			//领取军功
			_q_rank = readInt();
			return true;
		}
		
		/**
		 * get VIP编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set VIP编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get VIP名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set VIP名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 文字描述
		 * @return 
		 */
		public function get q_desc(): String{
			return _q_desc;
		}
		
		/**
		 * set 文字描述
		 */
		public function set q_desc(value: String): void{
			this._q_desc = value;
		}
		
		/**
		 * get VIP卡物品ID
		 * @return 
		 */
		public function get q_itemid(): int{
			return _q_itemid;
		}
		
		/**
		 * set VIP卡物品ID
		 */
		public function set q_itemid(value: int): void{
			this._q_itemid = value;
		}
		
		/**
		 * get VIP对应buffid
		 * @return 
		 */
		public function get q_buffid(): int{
			return _q_buffid;
		}
		
		/**
		 * set VIP对应buffid
		 */
		public function set q_buffid(value: int): void{
			this._q_buffid = value;
		}
		
		/**
		 * get 每日领取的礼包id
		 * @return 
		 */
		public function get q_dayreceivegiftid(): int{
			return _q_dayreceivegiftid;
		}
		
		/**
		 * set 每日领取的礼包id
		 */
		public function set q_dayreceivegiftid(value: int): void{
			this._q_dayreceivegiftid = value;
		}
		
		/**
		 * get 每日领取铜币
		 * @return 
		 */
		public function get q_dayreceive_money(): int{
			return _q_dayreceive_money;
		}
		
		/**
		 * set 每日领取铜币
		 */
		public function set q_dayreceive_money(value: int): void{
			this._q_dayreceive_money = value;
		}
		
		/**
		 * get 每日领取礼金
		 * @return 
		 */
		public function get q_dayreceive_lijin(): int{
			return _q_dayreceive_lijin;
		}
		
		/**
		 * set 每日领取礼金
		 */
		public function set q_dayreceive_lijin(value: int): void{
			this._q_dayreceive_lijin = value;
		}
		
		/**
		 * get 免费传送次数(-1表示无次数限制)
		 * @return 
		 */
		public function get q_fly(): int{
			return _q_fly;
		}
		
		/**
		 * set 免费传送次数(-1表示无次数限制)
		 */
		public function set q_fly(value: int): void{
			this._q_fly = value;
		}
		
		/**
		 * get 称号资源图
		 * @return 
		 */
		public function get q_ico(): int{
			return _q_ico;
		}
		
		/**
		 * set 称号资源图
		 */
		public function set q_ico(value: int): void{
			this._q_ico = value;
		}
		
		/**
		 * get 加速仙露
		 * @return 
		 */
		public function get q_xianlu(): int{
			return _q_xianlu;
		}
		
		/**
		 * set 加速仙露
		 */
		public function set q_xianlu(value: int): void{
			this._q_xianlu = value;
		}
		
		/**
		 * get 正面BUFF增加时间
		 * @return 
		 */
		public function get q_pos_buff(): int{
			return _q_pos_buff;
		}
		
		/**
		 * set 正面BUFF增加时间
		 */
		public function set q_pos_buff(value: int): void{
			this._q_pos_buff = value;
		}
		
		/**
		 * get 自动摘取果实(0-不自动 1-自动)
		 * @return 
		 */
		public function get q_auto_fruit(): int{
			return _q_auto_fruit;
		}
		
		/**
		 * set 自动摘取果实(0-不自动 1-自动)
		 */
		public function set q_auto_fruit(value: int): void{
			this._q_auto_fruit = value;
		}
		
		/**
		 * get 自动给果实浇水(0-不自动 1-自动)
		 * @return 
		 */
		public function get q_auto_water(): int{
			return _q_auto_water;
		}
		
		/**
		 * set 自动给果实浇水(0-不自动 1-自动)
		 */
		public function set q_auto_water(value: int): void{
			this._q_auto_water = value;
		}
		
		/**
		 * get 副本扫荡次数
		 * @return 
		 */
		public function get q_zone_time(): int{
			return _q_zone_time;
		}
		
		/**
		 * set 副本扫荡次数
		 */
		public function set q_zone_time(value: int): void{
			this._q_zone_time = value;
		}
		
		/**
		 * get 附加功能
		 * @return 
		 */
		public function get q_append(): String{
			return _q_append;
		}
		
		/**
		 * set 附加功能
		 */
		public function set q_append(value: String): void{
			this._q_append = value;
		}
		
		/**
		 * get 领取军功
		 * @return 
		 */
		public function get q_rank(): int{
			return _q_rank;
		}
		
		/**
		 * set 领取军功
		 */
		public function set q_rank(value: int): void{
			this._q_rank = value;
		}
		
	}
}