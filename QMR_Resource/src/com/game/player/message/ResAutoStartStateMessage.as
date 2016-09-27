package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回挂机状态
	 */
	public class ResAutoStartStateMessage extends Message {
	
		//挂机状态， 1-挂机 0-未挂机
		private var _result: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//挂机状态， 1-挂机 0-未挂机
			writeByte(_result);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//挂机状态， 1-挂机 0-未挂机
			_result = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103133;
		}
		
		/**
		 * get 挂机状态， 1-挂机 0-未挂机
		 * @return 
		 */
		public function get result(): int{
			return _result;
		}
		
		/**
		 * set 挂机状态， 1-挂机 0-未挂机
		 */
		public function set result(value: int): void{
			this._result = value;
		}
		
	}
}