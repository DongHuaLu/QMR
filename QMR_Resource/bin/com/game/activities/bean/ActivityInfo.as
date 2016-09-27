package com.game.activities.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 活动信息
	 */
	public class ActivityInfo extends Bean {
	
		//活动id
		private var _activityId: int;
		
		//活动类型
		private var _activityType: int;
		
		//活动描述
		private var _activityDescribe: String;
		
		//活动奖励
		private var _activityReward: String;
		
		//是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
		private var _status: int;
		
		//活动持续时间 单位:秒  0表示没有结束时间
		private var _duration: int;
		
		//是否推荐
		private var _recommend: int;
		
		//开始时间
		private var _starttime: int;
		
		//结束时间
		private var _endtime: int;
		
		//是否可重复领取
		private var _canrepeated: int;
		
		//可领取次数
		private var _canreceive: int;
		
		//分组类型
		private var _grouptype: int;
		
		//标识
		private var _sign: String;
		
		//扩展属性
		private var _infoExpandList: Vector.<String> = new Vector.<String>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//活动id
			writeInt(_activityId);
			//活动类型
			writeInt(_activityType);
			//活动描述
			writeString(_activityDescribe);
			//活动奖励
			writeString(_activityReward);
			//是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
			writeInt(_status);
			//活动持续时间 单位:秒  0表示没有结束时间
			writeInt(_duration);
			//是否推荐
			writeInt(_recommend);
			//开始时间
			writeInt(_starttime);
			//结束时间
			writeInt(_endtime);
			//是否可重复领取
			writeInt(_canrepeated);
			//可领取次数
			writeInt(_canreceive);
			//分组类型
			writeInt(_grouptype);
			//标识
			writeString(_sign);
			//扩展属性
			writeShort(_infoExpandList.length);
			for (var i: int = 0; i < _infoExpandList.length; i++) {
				writeString(_infoExpandList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//活动id
			_activityId = readInt();
			//活动类型
			_activityType = readInt();
			//活动描述
			_activityDescribe = readString();
			//活动奖励
			_activityReward = readString();
			//是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
			_status = readInt();
			//活动持续时间 单位:秒  0表示没有结束时间
			_duration = readInt();
			//是否推荐
			_recommend = readInt();
			//开始时间
			_starttime = readInt();
			//结束时间
			_endtime = readInt();
			//是否可重复领取
			_canrepeated = readInt();
			//可领取次数
			_canreceive = readInt();
			//分组类型
			_grouptype = readInt();
			//标识
			_sign = readString();
			//扩展属性
			var infoExpandList_length : int = readShort();
			for (var i: int = 0; i < infoExpandList_length; i++) {
				_infoExpandList[i] = readString();
			}
			return true;
		}
		
		/**
		 * get 活动id
		 * @return 
		 */
		public function get activityId(): int{
			return _activityId;
		}
		
		/**
		 * set 活动id
		 */
		public function set activityId(value: int): void{
			this._activityId = value;
		}
		
		/**
		 * get 活动类型
		 * @return 
		 */
		public function get activityType(): int{
			return _activityType;
		}
		
		/**
		 * set 活动类型
		 */
		public function set activityType(value: int): void{
			this._activityType = value;
		}
		
		/**
		 * get 活动描述
		 * @return 
		 */
		public function get activityDescribe(): String{
			return _activityDescribe;
		}
		
		/**
		 * set 活动描述
		 */
		public function set activityDescribe(value: String): void{
			this._activityDescribe = value;
		}
		
		/**
		 * get 活动奖励
		 * @return 
		 */
		public function get activityReward(): String{
			return _activityReward;
		}
		
		/**
		 * set 活动奖励
		 */
		public function set activityReward(value: String): void{
			this._activityReward = value;
		}
		
		/**
		 * get 是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
		/**
		 * get 活动持续时间 单位:秒  0表示没有结束时间
		 * @return 
		 */
		public function get duration(): int{
			return _duration;
		}
		
		/**
		 * set 活动持续时间 单位:秒  0表示没有结束时间
		 */
		public function set duration(value: int): void{
			this._duration = value;
		}
		
		/**
		 * get 是否推荐
		 * @return 
		 */
		public function get recommend(): int{
			return _recommend;
		}
		
		/**
		 * set 是否推荐
		 */
		public function set recommend(value: int): void{
			this._recommend = value;
		}
		
		/**
		 * get 开始时间
		 * @return 
		 */
		public function get starttime(): int{
			return _starttime;
		}
		
		/**
		 * set 开始时间
		 */
		public function set starttime(value: int): void{
			this._starttime = value;
		}
		
		/**
		 * get 结束时间
		 * @return 
		 */
		public function get endtime(): int{
			return _endtime;
		}
		
		/**
		 * set 结束时间
		 */
		public function set endtime(value: int): void{
			this._endtime = value;
		}
		
		/**
		 * get 是否可重复领取
		 * @return 
		 */
		public function get canrepeated(): int{
			return _canrepeated;
		}
		
		/**
		 * set 是否可重复领取
		 */
		public function set canrepeated(value: int): void{
			this._canrepeated = value;
		}
		
		/**
		 * get 可领取次数
		 * @return 
		 */
		public function get canreceive(): int{
			return _canreceive;
		}
		
		/**
		 * set 可领取次数
		 */
		public function set canreceive(value: int): void{
			this._canreceive = value;
		}
		
		/**
		 * get 分组类型
		 * @return 
		 */
		public function get grouptype(): int{
			return _grouptype;
		}
		
		/**
		 * set 分组类型
		 */
		public function set grouptype(value: int): void{
			this._grouptype = value;
		}
		
		/**
		 * get 标识
		 * @return 
		 */
		public function get sign(): String{
			return _sign;
		}
		
		/**
		 * set 标识
		 */
		public function set sign(value: String): void{
			this._sign = value;
		}
		
		/**
		 * get 扩展属性
		 * @return 
		 */
		public function get infoExpandList(): Vector.<String>{
			return _infoExpandList;
		}
		
		/**
		 * set 扩展属性
		 */
		public function set infoExpandList(value: Vector.<String>): void{
			this._infoExpandList = value;
		}
		
	}
}