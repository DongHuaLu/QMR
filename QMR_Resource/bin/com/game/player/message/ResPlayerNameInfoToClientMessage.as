package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 服务器通知前端玩家是否可改名
	 */
	public class ResPlayerNameInfoToClientMessage extends Message {
	
		//是否可以改名
		private var _changeName: int;
		
		//是否可以改账号
		private var _changeUser: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//是否可以改名
			writeByte(_changeName);
			//是否可以改账号
			writeByte(_changeUser);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//是否可以改名
			_changeName = readByte();
			//是否可以改账号
			_changeUser = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103128;
		}
		
		/**
		 * get 是否可以改名
		 * @return 
		 */
		public function get changeName(): int{
			return _changeName;
		}
		
		/**
		 * set 是否可以改名
		 */
		public function set changeName(value: int): void{
			this._changeName = value;
		}
		
		/**
		 * get 是否可以改账号
		 * @return 
		 */
		public function get changeUser(): int{
			return _changeUser;
		}
		
		/**
		 * set 是否可以改账号
		 */
		public function set changeUser(value: int): void{
			this._changeUser = value;
		}
		
	}
}