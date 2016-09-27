package com.game.toplist.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 膜拜玩家
	 */
	public class ReqWorShipToServerMessage extends Message {
	
		//膜拜玩家id
		private var _worshipplayerid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//膜拜玩家id
			writeLong(_worshipplayerid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//膜拜玩家id
			_worshipplayerid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142202;
		}
		
		/**
		 * get 膜拜玩家id
		 * @return 
		 */
		public function get worshipplayerid(): long{
			return _worshipplayerid;
		}
		
		/**
		 * set 膜拜玩家id
		 */
		public function set worshipplayerid(value: long): void{
			this._worshipplayerid = value;
		}
		
	}
}