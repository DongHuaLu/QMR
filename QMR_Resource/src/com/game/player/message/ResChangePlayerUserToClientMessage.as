package com.game.player.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家改帐号，完成后通知客户端
	 */
	public class ResChangePlayerUserToClientMessage extends Message {
	
		//角色ID
		private var _playerId: long;
		
		//新帐号
		private var _newname: String;
		
		//返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
		private var _result: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色ID
			writeLong(_playerId);
			//新帐号
			writeString(_newname);
			//返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
			writeByte(_result);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色ID
			_playerId = readLong();
			//新帐号
			_newname = readString();
			//返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
			_result = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103129;
		}
		
		/**
		 * get 角色ID
		 * @return 
		 */
		public function get playerId(): long{
			return _playerId;
		}
		
		/**
		 * set 角色ID
		 */
		public function set playerId(value: long): void{
			this._playerId = value;
		}
		
		/**
		 * get 新帐号
		 * @return 
		 */
		public function get newname(): String{
			return _newname;
		}
		
		/**
		 * set 新帐号
		 */
		public function set newname(value: String): void{
			this._newname = value;
		}
		
		/**
		 * get 返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
		 * @return 
		 */
		public function get result(): int{
			return _result;
		}
		
		/**
		 * set 返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
		 */
		public function set result(value: int): void{
			this._result = value;
		}
		
	}
}