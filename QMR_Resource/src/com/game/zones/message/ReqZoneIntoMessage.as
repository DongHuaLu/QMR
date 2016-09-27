package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 进入副本
	 */
	public class ReqZoneIntoMessage extends Message {
	
		//副本编号
		private var _zoneid: int;
		
		//是否自动：0手动进入，1自动扫荡
		private var _isauto: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//副本编号
			writeInt(_zoneid);
			//是否自动：0手动进入，1自动扫荡
			writeByte(_isauto);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//副本编号
			_zoneid = readInt();
			//是否自动：0手动进入，1自动扫荡
			_isauto = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128202;
		}
		
		/**
		 * get 副本编号
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本编号
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
		/**
		 * get 是否自动：0手动进入，1自动扫荡
		 * @return 
		 */
		public function get isauto(): int{
			return _isauto;
		}
		
		/**
		 * set 是否自动：0手动进入，1自动扫荡
		 */
		public function set isauto(value: int): void{
			this._isauto = value;
		}
		
	}
}