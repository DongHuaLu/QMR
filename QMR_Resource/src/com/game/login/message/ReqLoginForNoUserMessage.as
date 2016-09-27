package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 无账号登陆
	 */
	public class ReqLoginForNoUserMessage extends Message {
	
		//服务器Id
		private var _serverId: String;
		
		//玩家用户名
		private var _username: String;
		
		//平台标识
		private var _agent: String;
		
		//服务器Id
		private var _ad: String;
		
		//平台服务器的LINUX时间戳(为长整数, 单位为秒)
		private var _time: String;
		
		//1成年,0未成年,-1未知
		private var _isadult: String;
		
		//全小写MD5验证码
		private var _sign: String;
		
		//
		private var _localref: String;
		
		//
		private var _reserva1: String;
		
		//
		private var _reserva2: String;
		
		//角色名字
		private var _name: String;
		
		//角色性别 1-男 2-女
		private var _sex: int;
		
		//角色头像
		private var _icon: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//服务器Id
			writeString(_serverId);
			//玩家用户名
			writeString(_username);
			//平台标识
			writeString(_agent);
			//服务器Id
			writeString(_ad);
			//平台服务器的LINUX时间戳(为长整数, 单位为秒)
			writeString(_time);
			//1成年,0未成年,-1未知
			writeString(_isadult);
			//全小写MD5验证码
			writeString(_sign);
			//
			writeString(_localref);
			//
			writeString(_reserva1);
			//
			writeString(_reserva2);
			//角色名字
			writeString(_name);
			//角色性别 1-男 2-女
			writeByte(_sex);
			//角色头像
			writeString(_icon);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//服务器Id
			_serverId = readString();
			//玩家用户名
			_username = readString();
			//平台标识
			_agent = readString();
			//服务器Id
			_ad = readString();
			//平台服务器的LINUX时间戳(为长整数, 单位为秒)
			_time = readString();
			//1成年,0未成年,-1未知
			_isadult = readString();
			//全小写MD5验证码
			_sign = readString();
			//
			_localref = readString();
			//
			_reserva1 = readString();
			//
			_reserva2 = readString();
			//角色名字
			_name = readString();
			//角色性别 1-男 2-女
			_sex = readByte();
			//角色头像
			_icon = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100209;
		}
		
		/**
		 * get 服务器Id
		 * @return 
		 */
		public function get serverId(): String{
			return _serverId;
		}
		
		/**
		 * set 服务器Id
		 */
		public function set serverId(value: String): void{
			this._serverId = value;
		}
		
		/**
		 * get 玩家用户名
		 * @return 
		 */
		public function get username(): String{
			return _username;
		}
		
		/**
		 * set 玩家用户名
		 */
		public function set username(value: String): void{
			this._username = value;
		}
		
		/**
		 * get 平台标识
		 * @return 
		 */
		public function get agent(): String{
			return _agent;
		}
		
		/**
		 * set 平台标识
		 */
		public function set agent(value: String): void{
			this._agent = value;
		}
		
		/**
		 * get 服务器Id
		 * @return 
		 */
		public function get ad(): String{
			return _ad;
		}
		
		/**
		 * set 服务器Id
		 */
		public function set ad(value: String): void{
			this._ad = value;
		}
		
		/**
		 * get 平台服务器的LINUX时间戳(为长整数, 单位为秒)
		 * @return 
		 */
		public function get time(): String{
			return _time;
		}
		
		/**
		 * set 平台服务器的LINUX时间戳(为长整数, 单位为秒)
		 */
		public function set time(value: String): void{
			this._time = value;
		}
		
		/**
		 * get 1成年,0未成年,-1未知
		 * @return 
		 */
		public function get isadult(): String{
			return _isadult;
		}
		
		/**
		 * set 1成年,0未成年,-1未知
		 */
		public function set isadult(value: String): void{
			this._isadult = value;
		}
		
		/**
		 * get 全小写MD5验证码
		 * @return 
		 */
		public function get sign(): String{
			return _sign;
		}
		
		/**
		 * set 全小写MD5验证码
		 */
		public function set sign(value: String): void{
			this._sign = value;
		}
		
		/**
		 * get 
		 * @return 
		 */
		public function get localref(): String{
			return _localref;
		}
		
		/**
		 * set 
		 */
		public function set localref(value: String): void{
			this._localref = value;
		}
		
		/**
		 * get 
		 * @return 
		 */
		public function get reserva1(): String{
			return _reserva1;
		}
		
		/**
		 * set 
		 */
		public function set reserva1(value: String): void{
			this._reserva1 = value;
		}
		
		/**
		 * get 
		 * @return 
		 */
		public function get reserva2(): String{
			return _reserva2;
		}
		
		/**
		 * set 
		 */
		public function set reserva2(value: String): void{
			this._reserva2 = value;
		}
		
		/**
		 * get 角色名字
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 角色名字
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 角色性别 1-男 2-女
		 * @return 
		 */
		public function get sex(): int{
			return _sex;
		}
		
		/**
		 * set 角色性别 1-男 2-女
		 */
		public function set sex(value: int): void{
			this._sex = value;
		}
		
		/**
		 * get 角色头像
		 * @return 
		 */
		public function get icon(): String{
			return _icon;
		}
		
		/**
		 * set 角色头像
		 */
		public function set icon(value: String): void{
			this._icon = value;
		}
		
	}
}