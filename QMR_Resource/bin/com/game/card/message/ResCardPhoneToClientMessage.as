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
	 * 请求手机绑定返回
	 */
	public class ResCardPhoneToClientMessage extends Message {
	
		//错误代码
		private var _errorcode: int;
		
		//状态
		private var _status: int;
		
		//账号
		private var _account: String;
		
		//角色id
		private var _playerid: long;
		
		//电话
		private var _phone: String;
		
		//平台id
		private var _agid: int;
		
		//礼包类型
		private var _type: int;
		
		//验证码
		private var _vercode: String;
		
		//手机验证次数，每天只有3次
		private var _phonevernum: int;
		
		//验证码错误次数，根据每个手机的更换清除
		private var _vererrornum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_errorcode);
			//状态
			writeByte(_status);
			//账号
			writeString(_account);
			//角色id
			writeLong(_playerid);
			//电话
			writeString(_phone);
			//平台id
			writeInt(_agid);
			//礼包类型
			writeInt(_type);
			//验证码
			writeString(_vercode);
			//手机验证次数，每天只有3次
			writeInt(_phonevernum);
			//验证码错误次数，根据每个手机的更换清除
			writeInt(_vererrornum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_errorcode = readByte();
			//状态
			_status = readByte();
			//账号
			_account = readString();
			//角色id
			_playerid = readLong();
			//电话
			_phone = readString();
			//平台id
			_agid = readInt();
			//礼包类型
			_type = readInt();
			//验证码
			_vercode = readString();
			//手机验证次数，每天只有3次
			_phonevernum = readInt();
			//验证码错误次数，根据每个手机的更换清除
			_vererrornum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 137251;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get errorcode(): int{
			return _errorcode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set errorcode(value: int): void{
			this._errorcode = value;
		}
		
		/**
		 * get 状态
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 状态
		 */
		public function set status(value: int): void{
			this._status = value;
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
		
		/**
		 * get 平台id
		 * @return 
		 */
		public function get agid(): int{
			return _agid;
		}
		
		/**
		 * set 平台id
		 */
		public function set agid(value: int): void{
			this._agid = value;
		}
		
		/**
		 * get 礼包类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 礼包类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 验证码
		 * @return 
		 */
		public function get vercode(): String{
			return _vercode;
		}
		
		/**
		 * set 验证码
		 */
		public function set vercode(value: String): void{
			this._vercode = value;
		}
		
		/**
		 * get 手机验证次数，每天只有3次
		 * @return 
		 */
		public function get phonevernum(): int{
			return _phonevernum;
		}
		
		/**
		 * set 手机验证次数，每天只有3次
		 */
		public function set phonevernum(value: int): void{
			this._phonevernum = value;
		}
		
		/**
		 * get 验证码错误次数，根据每个手机的更换清除
		 * @return 
		 */
		public function get vererrornum(): int{
			return _vererrornum;
		}
		
		/**
		 * set 验证码错误次数，根据每个手机的更换清除
		 */
		public function set vererrornum(value: int): void{
			this._vererrornum = value;
		}
		
	}
}