package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 取消报名
	 */
	public class ReqZoneCancelSignupToGameMessage extends Message {
	
		//类型，1取消单人报名，2取消组队报名
		private var _type: int;
		
		//副本ID
		private var _zoneid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型，1取消单人报名，2取消组队报名
			writeInt(_type);
			//副本ID
			writeInt(_zoneid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型，1取消单人报名，2取消组队报名
			_type = readInt();
			//副本ID
			_zoneid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128216;
		}
		
		/**
		 * get 类型，1取消单人报名，2取消组队报名
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型，1取消单人报名，2取消组队报名
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 副本ID
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本ID
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
	}
}