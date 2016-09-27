package com.game.protect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 修改密码
	 */
	public class ReqModifyPasswordToGameMessage extends Message {
	
		//新密码
		private var _newpassword: String;
		
		//原密码
		private var _oldpassword: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//新密码
			writeString(_newpassword);
			//原密码
			writeString(_oldpassword);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//新密码
			_newpassword = readString();
			//原密码
			_oldpassword = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 164205;
		}
		
		/**
		 * get 新密码
		 * @return 
		 */
		public function get newpassword(): String{
			return _newpassword;
		}
		
		/**
		 * set 新密码
		 */
		public function set newpassword(value: String): void{
			this._newpassword = value;
		}
		
		/**
		 * get 原密码
		 * @return 
		 */
		public function get oldpassword(): String{
			return _oldpassword;
		}
		
		/**
		 * set 原密码
		 */
		public function set oldpassword(value: String): void{
			this._oldpassword = value;
		}
		
	}
}