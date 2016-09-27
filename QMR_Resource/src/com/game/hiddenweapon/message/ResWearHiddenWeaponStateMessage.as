package com.game.hiddenweapon.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送暗器装备状态
	 */
	public class ResWearHiddenWeaponStateMessage extends Message {
	
		//暗器使用者ID
		private var _playerid: long;
		
		//当前使用的暗器阶层
		private var _curlayer: int;
		
		//是否装备，1装备，0卸下
		private var _status: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//暗器使用者ID
			writeLong(_playerid);
			//当前使用的暗器阶层
			writeShort(_curlayer);
			//是否装备，1装备，0卸下
			writeByte(_status);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//暗器使用者ID
			_playerid = readLong();
			//当前使用的暗器阶层
			_curlayer = readShort();
			//是否装备，1装备，0卸下
			_status = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 162102;
		}
		
		/**
		 * get 暗器使用者ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 暗器使用者ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 当前使用的暗器阶层
		 * @return 
		 */
		public function get curlayer(): int{
			return _curlayer;
		}
		
		/**
		 * set 当前使用的暗器阶层
		 */
		public function set curlayer(value: int): void{
			this._curlayer = value;
		}
		
		/**
		 * get 是否装备，1装备，0卸下
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否装备，1装备，0卸下
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
	}
}