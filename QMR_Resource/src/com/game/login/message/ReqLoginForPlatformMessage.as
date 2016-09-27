package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 登陆
	 */
	public class ReqLoginForPlatformMessage extends Message {
	
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
		
		//登陆类型
		private var _logintype: String;
		
		//平台数据
		private var _agentPlusdata: String;
		
		//平台数据
		private var _agentColdatas: String;
		
		//平台数据
		private var _adregtime: String;
		
		
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
			//登陆类型
			writeString(_logintype);
			//平台数据
			writeString(_agentPlusdata);
			//平台数据
			writeString(_agentColdatas);
			//平台数据
			writeString(_adregtime);
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
			//登陆类型
			_logintype = readString();
			//平台数据
			_agentPlusdata = readString();
			//平台数据
			_agentColdatas = readString();
			//平台数据
			_adregtime = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100208;
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
		 * get 登陆类型
		 * @return 
		 */
		public function get logintype(): String{
			return _logintype;
		}
		
		/**
		 * set 登陆类型
		 */
		public function set logintype(value: String): void{
			this._logintype = value;
		}
		
		/**
		 * get 平台数据
		 * @return 
		 */
		public function get agentPlusdata(): String{
			return _agentPlusdata;
		}
		
		/**
		 * set 平台数据
		 */
		public function set agentPlusdata(value: String): void{
			this._agentPlusdata = value;
		}
		
		/**
		 * get 平台数据
		 * @return 
		 */
		public function get agentColdatas(): String{
			return _agentColdatas;
		}
		
		/**
		 * set 平台数据
		 */
		public function set agentColdatas(value: String): void{
			this._agentColdatas = value;
		}
		
		/**
		 * get 平台数据
		 * @return 
		 */
		public function get adregtime(): String{
			return _adregtime;
		}
		
		/**
		 * set 平台数据
		 */
		public function set adregtime(value: String): void{
			this._adregtime = value;
		}
		
	}
}