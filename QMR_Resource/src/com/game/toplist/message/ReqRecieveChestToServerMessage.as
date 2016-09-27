package com.game.toplist.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 领取宝箱
	 */
	public class ReqRecieveChestToServerMessage extends Message {
	
		//宝箱id
		private var _chestid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宝箱id
			writeInt(_chestid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宝箱id
			_chestid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142204;
		}
		
		/**
		 * get 宝箱id
		 * @return 
		 */
		public function get chestid(): int{
			return _chestid;
		}
		
		/**
		 * set 宝箱id
		 */
		public function set chestid(value: int): void{
			this._chestid = value;
		}
		
	}
}