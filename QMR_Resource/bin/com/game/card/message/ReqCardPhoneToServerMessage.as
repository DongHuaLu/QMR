package com.game.card.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求手机绑定
	 */
	public class ReqCardPhoneToServerMessage extends Message {
	
		//账号
		private var _account: String;
		
		//角色id
		private var _playerid: long;
		
		//电话
		private var _phone: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//账号
			writeString(_account);
			//角色id
			writeLong(_playerid);
			//电话
			writeString(_phone);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//账号
			_account = readString();
			//角色id
			_playerid = readLong();
			//电话
			_phone = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 137102;
		}
		
		/**
		 * get 账号
		 * @return 
		 */
		public function get account(): String{
			return _account;
		}
		
		/**
		 * set 账号
		 */
		public function set account(value: String): void{
			this._account = value;
		}
		
		/**
		 * get 角色id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 角色id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 电话
		 * @return 
		 */
		public function get phone(): String{
			return _phone;
		}
		
		/**
		 * set 电话
		 */
		public function set phone(value: String): void{
			this._phone = value;
		}
		
	}
}