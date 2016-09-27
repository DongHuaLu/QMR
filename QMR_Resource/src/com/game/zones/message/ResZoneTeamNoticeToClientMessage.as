package com.game.zones.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 组队多人进入副本通知队员
	 */
	public class ResZoneTeamNoticeToClientMessage extends Message {
	
		//队长ID
		private var _leaderid: long;
		
		//队长名字
		private var _leadername: String;
		
		//副本ID
		private var _zoneid: int;
		
		//等待选择倒计时
		private var _waittime: int;
		
		//进入倒计时
		private var _entertime: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队长ID
			writeLong(_leaderid);
			//队长名字
			writeString(_leadername);
			//副本ID
			writeInt(_zoneid);
			//等待选择倒计时
			writeInt(_waittime);
			//进入倒计时
			writeInt(_entertime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队长ID
			_leaderid = readLong();
			//队长名字
			_leadername = readString();
			//副本ID
			_zoneid = readInt();
			//等待选择倒计时
			_waittime = readInt();
			//进入倒计时
			_entertime = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128117;
		}
		
		/**
		 * get 队长ID
		 * @return 
		 */
		public function get leaderid(): long{
			return _leaderid;
		}
		
		/**
		 * set 队长ID
		 */
		public function set leaderid(value: long): void{
			this._leaderid = value;
		}
		
		/**
		 * get 队长名字
		 * @return 
		 */
		public function get leadername(): String{
			return _leadername;
		}
		
		/**
		 * set 队长名字
		 */
		public function set leadername(value: String): void{
			this._leadername = value;
		}
		
		/**
		 * get 副本ID
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本ID
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
		/**
		 * get 等待选择倒计时
		 * @return 
		 */
		public function get waittime(): int{
			return _waittime;
		}
		
		/**
		 * set 等待选择倒计时
		 */
		public function set waittime(value: int): void{
			this._waittime = value;
		}
		
		/**
		 * get 进入倒计时
		 * @return 
		 */
		public function get entertime(): int{
			return _entertime;
		}
		
		/**
		 * set 进入倒计时
		 */
		public function set entertime(value: int): void{
			this._entertime = value;
		}
		
	}
}