package com.game.signwage.bean{
	import com.game.spirittree.bean.FruitRewardinfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 工资消息
	 */
	public class WageInfo extends Bean {
	
		//开服时间
		private var _svrstarttime: String;
		
		//当前月
		private var _curmonth: int;
		
		//今日累计在线时间（秒）
		private var _daytime: int;
		
		//本月累计在线时间（秒）
		private var _monthtime: int;
		
		//本月领取状态(1领取，0未领取)
		private var _curstatus: int;
		
		//上月领取状态（1领取，0未领取）
		private var _oldstatus: int;
		
		//本月累计工资
		private var _curwage: int;
		
		//上月累计工资
		private var _oldwage: int;
		
		//摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
		private var _ernie: Vector.<int> = new Vector.<int>();
		//已经领取的道具奖励
		private var _fruitRewardinfo: Vector.<com.game.spirittree.bean.FruitRewardinfo> = new Vector.<com.game.spirittree.bean.FruitRewardinfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//开服时间
			writeString(_svrstarttime);
			//当前月
			writeInt(_curmonth);
			//今日累计在线时间（秒）
			writeInt(_daytime);
			//本月累计在线时间（秒）
			writeInt(_monthtime);
			//本月领取状态(1领取，0未领取)
			writeByte(_curstatus);
			//上月领取状态（1领取，0未领取）
			writeByte(_oldstatus);
			//本月累计工资
			writeInt(_curwage);
			//上月累计工资
			writeInt(_oldwage);
			//摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
			writeShort(_ernie.length);
			for (var i: int = 0; i < _ernie.length; i++) {
				writeInt(_ernie[i]);
			}
			//已经领取的道具奖励
			writeShort(_fruitRewardinfo.length);
			for (var i: int = 0; i < _fruitRewardinfo.length; i++) {
				writeBean(_fruitRewardinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//开服时间
			_svrstarttime = readString();
			//当前月
			_curmonth = readInt();
			//今日累计在线时间（秒）
			_daytime = readInt();
			//本月累计在线时间（秒）
			_monthtime = readInt();
			//本月领取状态(1领取，0未领取)
			_curstatus = readByte();
			//上月领取状态（1领取，0未领取）
			_oldstatus = readByte();
			//本月累计工资
			_curwage = readInt();
			//上月累计工资
			_oldwage = readInt();
			//摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
			var ernie_length : int = readShort();
			for (var i: int = 0; i < ernie_length; i++) {
				_ernie[i] = readInt();
			}
			//已经领取的道具奖励
			var fruitRewardinfo_length : int = readShort();
			for (var i: int = 0; i < fruitRewardinfo_length; i++) {
				_fruitRewardinfo[i] = readBean(com.game.spirittree.bean.FruitRewardinfo) as com.game.spirittree.bean.FruitRewardinfo;
			}
			return true;
		}
		
		/**
		 * get 开服时间
		 * @return 
		 */
		public function get svrstarttime(): String{
			return _svrstarttime;
		}
		
		/**
		 * set 开服时间
		 */
		public function set svrstarttime(value: String): void{
			this._svrstarttime = value;
		}
		
		/**
		 * get 当前月
		 * @return 
		 */
		public function get curmonth(): int{
			return _curmonth;
		}
		
		/**
		 * set 当前月
		 */
		public function set curmonth(value: int): void{
			this._curmonth = value;
		}
		
		/**
		 * get 今日累计在线时间（秒）
		 * @return 
		 */
		public function get daytime(): int{
			return _daytime;
		}
		
		/**
		 * set 今日累计在线时间（秒）
		 */
		public function set daytime(value: int): void{
			this._daytime = value;
		}
		
		/**
		 * get 本月累计在线时间（秒）
		 * @return 
		 */
		public function get monthtime(): int{
			return _monthtime;
		}
		
		/**
		 * set 本月累计在线时间（秒）
		 */
		public function set monthtime(value: int): void{
			this._monthtime = value;
		}
		
		/**
		 * get 本月领取状态(1领取，0未领取)
		 * @return 
		 */
		public function get curstatus(): int{
			return _curstatus;
		}
		
		/**
		 * set 本月领取状态(1领取，0未领取)
		 */
		public function set curstatus(value: int): void{
			this._curstatus = value;
		}
		
		/**
		 * get 上月领取状态（1领取，0未领取）
		 * @return 
		 */
		public function get oldstatus(): int{
			return _oldstatus;
		}
		
		/**
		 * set 上月领取状态（1领取，0未领取）
		 */
		public function set oldstatus(value: int): void{
			this._oldstatus = value;
		}
		
		/**
		 * get 本月累计工资
		 * @return 
		 */
		public function get curwage(): int{
			return _curwage;
		}
		
		/**
		 * set 本月累计工资
		 */
		public function set curwage(value: int): void{
			this._curwage = value;
		}
		
		/**
		 * get 上月累计工资
		 * @return 
		 */
		public function get oldwage(): int{
			return _oldwage;
		}
		
		/**
		 * set 上月累计工资
		 */
		public function set oldwage(value: int): void{
			this._oldwage = value;
		}
		
		/**
		 * get 摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
		 * @return 
		 */
		public function get ernie(): Vector.<int>{
			return _ernie;
		}
		
		/**
		 * set 摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
		 */
		public function set ernie(value: Vector.<int>): void{
			this._ernie = value;
		}
		
		/**
		 * get 已经领取的道具奖励
		 * @return 
		 */
		public function get fruitRewardinfo(): Vector.<com.game.spirittree.bean.FruitRewardinfo>{
			return _fruitRewardinfo;
		}
		
		/**
		 * set 已经领取的道具奖励
		 */
		public function set fruitRewardinfo(value: Vector.<com.game.spirittree.bean.FruitRewardinfo>): void{
			this._fruitRewardinfo = value;
		}
		
	}
}