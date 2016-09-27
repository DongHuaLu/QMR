package com.game.realm.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知game突破境界
	 */
	public class ReqBreakthroughToGameMessage extends Message {
	
		//0手动，1自动
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0手动，1自动
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0手动，1自动
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 158201;
		}
		
		/**
		 * get 0手动，1自动
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0手动，1自动
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}