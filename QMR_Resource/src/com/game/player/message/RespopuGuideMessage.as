package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通用弹出引导
	 */
	public class RespopuGuideMessage extends Message {
	
		//引导id
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//引导id
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//引导id
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103136;
		}
		
		/**
		 * get 引导id
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 引导id
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}