package com.game.country.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 全服广播拔起玉玺
	 */
	public class ResCountrySiegeUpYuxiToClientMessage extends Message {
	
		//拔起玉玺的人ID
		private var _playerid: long;
		
		//玉拔起玉玺的人名字
		private var _playername: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//拔起玉玺的人ID
			writeLong(_playerid);
			//玉拔起玉玺的人名字
			writeString(_playername);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//拔起玉玺的人ID
			_playerid = readLong();
			//玉拔起玉玺的人名字
			_playername = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146102;
		}
		
		/**
		 * get 拔起玉玺的人ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 拔起玉玺的人ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 玉拔起玉玺的人名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 玉拔起玉玺的人名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
	}
}