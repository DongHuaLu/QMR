package com.game.map.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 选线
	 */
	public class ReqSelectLineMessage extends Message {
	
		//线编号
		private var _line: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//线编号
			writeInt(_line);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//线编号
			_line = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101212;
		}
		
		/**
		 * get 线编号
		 * @return 
		 */
		public function get line(): int{
			return _line;
		}
		
		/**
		 * set 线编号
		 */
		public function set line(value: int): void{
			this._line = value;
		}
		
	}
}