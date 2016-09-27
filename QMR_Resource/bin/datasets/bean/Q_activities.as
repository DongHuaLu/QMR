package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_activities
	 */
	public class Q_activities extends Bean{
	
		//活动编号
		private var _q_id: int;
		
		//活动名称
		private var _q_name: String;
		
		//活动主类型(表示是什么类型的活动)
		private var _q_maintype: int;
		
		//活动类型(同一天的写一个类型，或者一直就开放的写一个类型)
		private var _q_type: int;
		
		//活动奖励类型（1为每天展示奖励(领完之后消失)，2为展示全部奖励）
		private var _q_show: int;
		
		//同面板内的活动ID关联
		private var _q_mainicon: String;
		
		//（1玩家领取，0账号领取）
		private var _q_titleimage: String;
		
		//区别老新服（1新区,2旧，0通用）
		private var _q_listimage: String;
		
		//是否为推荐活动（0为不推荐，1为推荐）
		private var _q_tuijian: int;
		
		//是否可重复领取（0不可1可）
		private var _q_canrepeated: int;
		
		//活动持续时间[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
		private var _q_duration: String;
		
		//定时开放活动[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
		private var _q_timingstart: String;
		
		//活动内容介绍
		private var _q_info: String;
		
		//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
		private var _q_items: String;
		
		//需要登入天数
		private var _q_login_limit: int;
		
		//需要充值元宝数([0(0 有没有充值 1 每日的充值 2 一段时间的充值 3 总充值 4 排行榜),1(充值 0 大于后面一个数字就可以 1 根据充值数字和后面数字取次数， 排行 排行类型), 3(充值 得到奖励的条件数字 ， 排行 名次)， 1212301059开始时间，1212311059结束时间])
		private var _q_pay_yuanbao: String;
		
		//需要等级
		private var _q_need_level: int;
		
		//需要坐骑品阶
		private var _q_need_horse: int;
		
		//需要武功层数和
		private var _q_need_skill: int;
		
		//其他数据([[1(VIP),3(VIP等级)],[2(王城),1(任期)，X（天数）]，[3（商城）,0（购买到数量领取）1（购买数量多次领取），物品ID，需购买数量],[4(军功领奖励)],[6军功，3倍数]])
		private var _q_other: String;
		
		//类型检测
		private var _q_flag: String;
		
		//活动资源控制
		private var _q_resflag: String;
		
		//达成在线时间（秒）
		private var _q_onlinetime: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//活动编号
			_q_id = readInt();
			//活动名称
			_q_name = readString();
			//活动主类型(表示是什么类型的活动)
			_q_maintype = readInt();
			//活动类型(同一天的写一个类型，或者一直就开放的写一个类型)
			_q_type = readInt();
			//活动奖励类型（1为每天展示奖励(领完之后消失)，2为展示全部奖励）
			_q_show = readInt();
			//同面板内的活动ID关联
			_q_mainicon = readString();
			//（1玩家领取，0账号领取）
			_q_titleimage = readString();
			//区别老新服（1新区,2旧，0通用）
			_q_listimage = readString();
			//是否为推荐活动（0为不推荐，1为推荐）
			_q_tuijian = readInt();
			//是否可重复领取（0不可1可）
			_q_canrepeated = readInt();
			//活动持续时间[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
			_q_duration = readString();
			//定时开放活动[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
			_q_timingstart = readString();
			//活动内容介绍
			_q_info = readString();
			//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
			_q_items = readString();
			//需要登入天数
			_q_login_limit = readInt();
			//需要充值元宝数([0(0 有没有充值 1 每日的充值 2 一段时间的充值 3 总充值 4 排行榜),1(充值 0 大于后面一个数字就可以 1 根据充值数字和后面数字取次数， 排行 排行类型), 3(充值 得到奖励的条件数字 ， 排行 名次)， 1212301059开始时间，1212311059结束时间])
			_q_pay_yuanbao = readString();
			//需要等级
			_q_need_level = readInt();
			//需要坐骑品阶
			_q_need_horse = readInt();
			//需要武功层数和
			_q_need_skill = readInt();
			//其他数据([[1(VIP),3(VIP等级)],[2(王城),1(任期)，X（天数）]，[3（商城）,0（购买到数量领取）1（购买数量多次领取），物品ID，需购买数量],[4(军功领奖励)],[6军功，3倍数]])
			_q_other = readString();
			//类型检测
			_q_flag = readString();
			//活动资源控制
			_q_resflag = readString();
			//达成在线时间（秒）
			_q_onlinetime = readInt();
			return true;
		}
		
		/**
		 * get 活动编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 活动编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 活动名称
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 活动名称
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 活动主类型(表示是什么类型的活动)
		 * @return 
		 */
		public function get q_maintype(): int{
			return _q_maintype;
		}
		
		/**
		 * set 活动主类型(表示是什么类型的活动)
		 */
		public function set q_maintype(value: int): void{
			this._q_maintype = value;
		}
		
		/**
		 * get 活动类型(同一天的写一个类型，或者一直就开放的写一个类型)
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 活动类型(同一天的写一个类型，或者一直就开放的写一个类型)
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 活动奖励类型（1为每天展示奖励(领完之后消失)，2为展示全部奖励）
		 * @return 
		 */
		public function get q_show(): int{
			return _q_show;
		}
		
		/**
		 * set 活动奖励类型（1为每天展示奖励(领完之后消失)，2为展示全部奖励）
		 */
		public function set q_show(value: int): void{
			this._q_show = value;
		}
		
		/**
		 * get 同面板内的活动ID关联
		 * @return 
		 */
		public function get q_mainicon(): String{
			return _q_mainicon;
		}
		
		/**
		 * set 同面板内的活动ID关联
		 */
		public function set q_mainicon(value: String): void{
			this._q_mainicon = value;
		}
		
		/**
		 * get （1玩家领取，0账号领取）
		 * @return 
		 */
		public function get q_titleimage(): String{
			return _q_titleimage;
		}
		
		/**
		 * set （1玩家领取，0账号领取）
		 */
		public function set q_titleimage(value: String): void{
			this._q_titleimage = value;
		}
		
		/**
		 * get 区别老新服（1新区,2旧，0通用）
		 * @return 
		 */
		public function get q_listimage(): String{
			return _q_listimage;
		}
		
		/**
		 * set 区别老新服（1新区,2旧，0通用）
		 */
		public function set q_listimage(value: String): void{
			this._q_listimage = value;
		}
		
		/**
		 * get 是否为推荐活动（0为不推荐，1为推荐）
		 * @return 
		 */
		public function get q_tuijian(): int{
			return _q_tuijian;
		}
		
		/**
		 * set 是否为推荐活动（0为不推荐，1为推荐）
		 */
		public function set q_tuijian(value: int): void{
			this._q_tuijian = value;
		}
		
		/**
		 * get 是否可重复领取（0不可1可）
		 * @return 
		 */
		public function get q_canrepeated(): int{
			return _q_canrepeated;
		}
		
		/**
		 * set 是否可重复领取（0不可1可）
		 */
		public function set q_canrepeated(value: int): void{
			this._q_canrepeated = value;
		}
		
		/**
		 * get 活动持续时间[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
		 * @return 
		 */
		public function get q_duration(): String{
			return _q_duration;
		}
		
		/**
		 * set 活动持续时间[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
		 */
		public function set q_duration(value: String): void{
			this._q_duration = value;
		}
		
		/**
		 * get 定时开放活动[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
		 * @return 
		 */
		public function get q_timingstart(): String{
			return _q_timingstart;
		}
		
		/**
		 * set 定时开放活动[[2012],[1,10],[22],[-1],[101,2358],[-1(区间)，-2(开服)，10(间隔时间)]]
		 */
		public function set q_timingstart(value: String): void{
			this._q_timingstart = value;
		}
		
		/**
		 * get 活动内容介绍
		 * @return 
		 */
		public function get q_info(): String{
			return _q_info;
		}
		
		/**
		 * set 活动内容介绍
		 */
		public function set q_info(value: String): void{
			this._q_info = value;
		}
		
		/**
		 * get 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
		 * @return 
		 */
		public function get q_items(): String{
			return _q_items;
		}
		
		/**
		 * set 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
		 */
		public function set q_items(value: String): void{
			this._q_items = value;
		}
		
		/**
		 * get 需要登入天数
		 * @return 
		 */
		public function get q_login_limit(): int{
			return _q_login_limit;
		}
		
		/**
		 * set 需要登入天数
		 */
		public function set q_login_limit(value: int): void{
			this._q_login_limit = value;
		}
		
		/**
		 * get 需要充值元宝数([0(0 有没有充值 1 每日的充值 2 一段时间的充值 3 总充值 4 排行榜),1(充值 0 大于后面一个数字就可以 1 根据充值数字和后面数字取次数， 排行 排行类型), 3(充值 得到奖励的条件数字 ， 排行 名次)， 1212301059开始时间，1212311059结束时间])
		 * @return 
		 */
		public function get q_pay_yuanbao(): String{
			return _q_pay_yuanbao;
		}
		
		/**
		 * set 需要充值元宝数([0(0 有没有充值 1 每日的充值 2 一段时间的充值 3 总充值 4 排行榜),1(充值 0 大于后面一个数字就可以 1 根据充值数字和后面数字取次数， 排行 排行类型), 3(充值 得到奖励的条件数字 ， 排行 名次)， 1212301059开始时间，1212311059结束时间])
		 */
		public function set q_pay_yuanbao(value: String): void{
			this._q_pay_yuanbao = value;
		}
		
		/**
		 * get 需要等级
		 * @return 
		 */
		public function get q_need_level(): int{
			return _q_need_level;
		}
		
		/**
		 * set 需要等级
		 */
		public function set q_need_level(value: int): void{
			this._q_need_level = value;
		}
		
		/**
		 * get 需要坐骑品阶
		 * @return 
		 */
		public function get q_need_horse(): int{
			return _q_need_horse;
		}
		
		/**
		 * set 需要坐骑品阶
		 */
		public function set q_need_horse(value: int): void{
			this._q_need_horse = value;
		}
		
		/**
		 * get 需要武功层数和
		 * @return 
		 */
		public function get q_need_skill(): int{
			return _q_need_skill;
		}
		
		/**
		 * set 需要武功层数和
		 */
		public function set q_need_skill(value: int): void{
			this._q_need_skill = value;
		}
		
		/**
		 * get 其他数据([[1(VIP),3(VIP等级)],[2(王城),1(任期)，X（天数）]，[3（商城）,0（购买到数量领取）1（购买数量多次领取），物品ID，需购买数量],[4(军功领奖励)],[6军功，3倍数]])
		 * @return 
		 */
		public function get q_other(): String{
			return _q_other;
		}
		
		/**
		 * set 其他数据([[1(VIP),3(VIP等级)],[2(王城),1(任期)，X（天数）]，[3（商城）,0（购买到数量领取）1（购买数量多次领取），物品ID，需购买数量],[4(军功领奖励)],[6军功，3倍数]])
		 */
		public function set q_other(value: String): void{
			this._q_other = value;
		}
		
		/**
		 * get 类型检测
		 * @return 
		 */
		public function get q_flag(): String{
			return _q_flag;
		}
		
		/**
		 * set 类型检测
		 */
		public function set q_flag(value: String): void{
			this._q_flag = value;
		}
		
		/**
		 * get 活动资源控制
		 * @return 
		 */
		public function get q_resflag(): String{
			return _q_resflag;
		}
		
		/**
		 * set 活动资源控制
		 */
		public function set q_resflag(value: String): void{
			this._q_resflag = value;
		}
		
		/**
		 * get 达成在线时间（秒）
		 * @return 
		 */
		public function get q_onlinetime(): int{
			return _q_onlinetime;
		}
		
		/**
		 * set 达成在线时间（秒）
		 */
		public function set q_onlinetime(value: int): void{
			this._q_onlinetime = value;
		}
		
	}
}