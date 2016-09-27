package com.game.epalace.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回前端转盘消息
	 */
	public class ResEpalaceDialToClientMessage extends Message {
	
		//当前位置
		private var _currentpos: int;
		
		//罗盘转动位置，上右下左0246，没有就-1
		private var _forkdirection: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前位置
			writeByte(_currentpos);
			//罗盘转动位置，上右下左0246，没有就-1
			writeByte(_forkdirection);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前位置
			_currentpos = readByte();
			//罗盘转动位置，上右下左0246，没有就-1
			_forkdirection = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 143105;
		}
		
		/**
		 * get 当前位置
		 * @return 
		 */
		public function get currentpos(): int{
			return _currentpos;
		}
		
		/**
		 * set 当前位置
		 */
		public function set currentpos(value: int): void{
			this._currentpos = value;
		}
		
		/**
		 * get 罗盘转动位置，上右下左0246，没有就-1
		 * @return 
		 */
		public function get forkdirection(): int{
			return _forkdirection;
		}
		
		/**
		 * set 罗盘转动位置，上右下左0246，没有就-1
		 */
		public function set forkdirection(value: int): void{
			this._forkdirection = value;
		}
		
	}
}