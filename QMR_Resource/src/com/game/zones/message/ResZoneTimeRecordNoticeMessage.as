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
	 * 时间破记录公告
	 */
	public class ResZoneTimeRecordNoticeMessage extends Message {
	
		//全服最快通关时间（时间秒）
		private var _fulltime: int;
		
		//全服最快通关者名字
		private var _fullname: String;
		
		//副本编号
		private var _zoneid: int;
		
		//全服最快通关者id
		private var _playerid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//全服最快通关时间（时间秒）
			writeInt(_fulltime);
			//全服最快通关者名字
			writeString(_fullname);
			//副本编号
			writeInt(_zoneid);
			//全服最快通关者id
			writeLong(_playerid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//全服最快通关时间（时间秒）
			_fulltime = readInt();
			//全服最快通关者名字
			_fullname = readString();
			//副本编号
			_zoneid = readInt();
			//全服最快通关者id
			_playerid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128112;
		}
		
		/**
		 * get 全服最快通关时间（时间秒）
		 * @return 
		 */
		public function get fulltime(): int{
			return _fulltime;
		}
		
		/**
		 * set 全服最快通关时间（时间秒）
		 */
		public function set fulltime(value: int): void{
			this._fulltime = value;
		}
		
		/**
		 * get 全服最快通关者名字
		 * @return 
		 */
		public function get fullname(): String{
			return _fullname;
		}
		
		/**
		 * set 全服最快通关者名字
		 */
		public function set fullname(value: String): void{
			this._fullname = value;
		}
		
		/**
		 * get 副本编号
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本编号
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
		/**
		 * get 全服最快通关者id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 全服最快通关者id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
	}
}