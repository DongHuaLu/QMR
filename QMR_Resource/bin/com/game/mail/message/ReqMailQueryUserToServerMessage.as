package com.game.mail.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 查询玩家
	 */
	public class ReqMailQueryUserToServerMessage extends Message {
	
		//查询玩家名
		private var _szName: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//查询玩家名
			writeString(_szName);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//查询玩家名
			_szName = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124156;
		}
		
		/**
		 * get 查询玩家名
		 * @return 
		 */
		public function get szName(): String{
			return _szName;
		}
		
		/**
		 * set 查询玩家名
		 */
		public function set szName(value: String): void{
			this._szName = value;
		}
		
	}
}