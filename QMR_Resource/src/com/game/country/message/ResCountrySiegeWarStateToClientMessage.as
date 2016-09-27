package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 攻城战状态
	 */
	public class ResCountrySiegeWarStateToClientMessage extends Message {
	
		//0没有攻城战，1攻城战进行中
		private var _state: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0没有攻城战，1攻城战进行中
			writeByte(_state);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0没有攻城战，1攻城战进行中
			_state = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146107;
		}
		
		/**
		 * get 0没有攻城战，1攻城战进行中
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 0没有攻城战，1攻城战进行中
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
	}
}