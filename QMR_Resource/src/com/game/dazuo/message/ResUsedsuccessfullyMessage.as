package com.game.dazuo.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 凝丹使用成功
	 */
	public class ResUsedsuccessfullyMessage extends Message {
	
		//0是自己的，1是他人的
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0是自己的，1是他人的
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0是自己的，1是他人的
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 136106;
		}
		
		/**
		 * get 0是自己的，1是他人的
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0是自己的，1是他人的
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}