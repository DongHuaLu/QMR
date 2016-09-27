package com.game.gm.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * GM指令
	 */
	public class GmCommandMessage extends Message {
	
		//Gm指令
		private var _command: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//Gm指令
			writeString(_command);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//Gm指令
			_command = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 200201;
		}
		
		/**
		 * get Gm指令
		 * @return 
		 */
		public function get command(): String{
			return _command;
		}
		
		/**
		 * set Gm指令
		 */
		public function set command(value: String): void{
			this._command = value;
		}
		
	}
}