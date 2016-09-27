package com.game.arrow.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 领取七曜战魂
	 */
	public class ReqFightSpiritGetMessage extends Message {
	
		//领取类型
		private var _gettype: int;
		
		//副本id
		private var _zoneid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//领取类型
			writeInt(_gettype);
			//副本id
			writeInt(_zoneid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//领取类型
			_gettype = readInt();
			//副本id
			_zoneid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 151205;
		}
		
		/**
		 * get 领取类型
		 * @return 
		 */
		public function get gettype(): int{
			return _gettype;
		}
		
		/**
		 * set 领取类型
		 */
		public function set gettype(value: int): void{
			this._gettype = value;
		}
		
		/**
		 * get 副本id
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本id
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
	}
}