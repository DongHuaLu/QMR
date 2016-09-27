package com.game.cloak.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 披风升阶
	 */
	public class ReqCloakLevelUpMessage extends Message {
	
		//0手动进阶，1自动进阶
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0手动进阶，1自动进阶
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0手动进阶，1自动进阶
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 170202;
		}
		
		/**
		 * get 0手动进阶，1自动进阶
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0手动进阶，1自动进阶
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}