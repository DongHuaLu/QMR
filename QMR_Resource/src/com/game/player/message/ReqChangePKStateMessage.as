package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求更改pk状态
	 */
	public class ReqChangePKStateMessage extends Message {
	
		//pk状态
		private var _pkState: int;
		
		//是否自动切换
		private var _auto: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//pk状态
			writeInt(_pkState);
			//是否自动切换
			writeInt(_auto);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//pk状态
			_pkState = readInt();
			//是否自动切换
			_auto = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103204;
		}
		
		/**
		 * get pk状态
		 * @return 
		 */
		public function get pkState(): int{
			return _pkState;
		}
		
		/**
		 * set pk状态
		 */
		public function set pkState(value: int): void{
			this._pkState = value;
		}
		
		/**
		 * get 是否自动切换
		 * @return 
		 */
		public function get auto(): int{
			return _auto;
		}
		
		/**
		 * set 是否自动切换
		 */
		public function set auto(value: int): void{
			this._auto = value;
		}
		
	}
}