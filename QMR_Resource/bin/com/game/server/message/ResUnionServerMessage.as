package com.game.server.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 是否联服
	 */
	public class ResUnionServerMessage extends Message {
	
		//是否联服 0-不是 1-是
		private var _union: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//是否联服 0-不是 1-是
			writeInt(_union);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//是否联服 0-不是 1-是
			_union = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 300103;
		}
		
		/**
		 * get 是否联服 0-不是 1-是
		 * @return 
		 */
		public function get union(): int{
			return _union;
		}
		
		/**
		 * set 是否联服 0-不是 1-是
		 */
		public function set union(value: int): void{
			this._union = value;
		}
		
	}
}