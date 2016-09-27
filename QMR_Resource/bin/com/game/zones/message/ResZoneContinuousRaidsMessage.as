package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 连续扫荡信息
	 */
	public class ResZoneContinuousRaidsMessage extends Message {
	
		//总时间（秒）
		private var _sumtime: int;
		
		//已经过去的时间（秒）
		private var _passedime: int;
		
		//可领取的奖励次数
		private var _rewardnum: int;
		
		//当前扫荡的副本数量
		private var _zonenum: int;
		
		//类型，1剧情副本，4七曜战将
		private var _zonetype: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//总时间（秒）
			writeInt(_sumtime);
			//已经过去的时间（秒）
			writeInt(_passedime);
			//可领取的奖励次数
			writeInt(_rewardnum);
			//当前扫荡的副本数量
			writeInt(_zonenum);
			//类型，1剧情副本，4七曜战将
			writeInt(_zonetype);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//总时间（秒）
			_sumtime = readInt();
			//已经过去的时间（秒）
			_passedime = readInt();
			//可领取的奖励次数
			_rewardnum = readInt();
			//当前扫荡的副本数量
			_zonenum = readInt();
			//类型，1剧情副本，4七曜战将
			_zonetype = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128114;
		}
		
		/**
		 * get 总时间（秒）
		 * @return 
		 */
		public function get sumtime(): int{
			return _sumtime;
		}
		
		/**
		 * set 总时间（秒）
		 */
		public function set sumtime(value: int): void{
			this._sumtime = value;
		}
		
		/**
		 * get 已经过去的时间（秒）
		 * @return 
		 */
		public function get passedime(): int{
			return _passedime;
		}
		
		/**
		 * set 已经过去的时间（秒）
		 */
		public function set passedime(value: int): void{
			this._passedime = value;
		}
		
		/**
		 * get 可领取的奖励次数
		 * @return 
		 */
		public function get rewardnum(): int{
			return _rewardnum;
		}
		
		/**
		 * set 可领取的奖励次数
		 */
		public function set rewardnum(value: int): void{
			this._rewardnum = value;
		}
		
		/**
		 * get 当前扫荡的副本数量
		 * @return 
		 */
		public function get zonenum(): int{
			return _zonenum;
		}
		
		/**
		 * set 当前扫荡的副本数量
		 */
		public function set zonenum(value: int): void{
			this._zonenum = value;
		}
		
		/**
		 * get 类型，1剧情副本，4七曜战将
		 * @return 
		 */
		public function get zonetype(): int{
			return _zonetype;
		}
		
		/**
		 * set 类型，1剧情副本，4七曜战将
		 */
		public function set zonetype(value: int): void{
			this._zonetype = value;
		}
		
	}
}