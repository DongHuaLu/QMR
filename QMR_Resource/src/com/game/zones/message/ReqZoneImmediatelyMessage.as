package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 自动扫荡立即完成（使用元宝）
	 */
	public class ReqZoneImmediatelyMessage extends Message {
	
		//类型:1战役，4七曜战将
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型:1战役，4七曜战将
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型:1战役，4七曜战将
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128205;
		}
		
		/**
		 * get 类型:1战役，4七曜战将
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型:1战役，4七曜战将
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}