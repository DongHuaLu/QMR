package com.game.team.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 自动组队邀请
	 */
	public class ReqAutoTeaminvitedGameMessage extends Message {
	
		//0手动，1自动邀请
		private var _autoTeaminvited: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0手动，1自动邀请
			writeByte(_autoTeaminvited);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0手动，1自动邀请
			_autoTeaminvited = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118209;
		}
		
		/**
		 * get 0手动，1自动邀请
		 * @return 
		 */
		public function get autoTeaminvited(): int{
			return _autoTeaminvited;
		}
		
		/**
		 * set 0手动，1自动邀请
		 */
		public function set autoTeaminvited(value: int): void{
			this._autoTeaminvited = value;
		}
		
	}
}