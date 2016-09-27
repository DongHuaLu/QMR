package com.game.vip.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知客户端广播成为VIP公告
	 */
	public class ResVIPAnnounceMessage extends Message {
	
		//玩家角色名
		private var _playername: String;
		
		//玩家的vipid
		private var _vipid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家角色名
			writeString(_playername);
			//玩家的vipid
			writeInt(_vipid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家角色名
			_playername = readString();
			//玩家的vipid
			_vipid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 147103;
		}
		
		/**
		 * get 玩家角色名
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 玩家角色名
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 玩家的vipid
		 * @return 
		 */
		public function get vipid(): int{
			return _vipid;
		}
		
		/**
		 * set 玩家的vipid
		 */
		public function set vipid(value: int): void{
			this._vipid = value;
		}
		
	}
}