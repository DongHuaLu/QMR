package com.game.contral.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 功能状态
	 */
	public class StateInfo extends Bean {
	
		//功能ID,对应ContralEnum
		private var _contral: int;
		
		//0:关闭 1:开启
		private var _state: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//功能ID,对应ContralEnum
			writeInt(_contral);
			//0:关闭 1:开启
			writeInt(_state);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//功能ID,对应ContralEnum
			_contral = readInt();
			//0:关闭 1:开启
			_state = readInt();
			return true;
		}
		
		/**
		 * get 功能ID,对应ContralEnum
		 * @return 
		 */
		public function get contral(): int{
			return _contral;
		}
		
		/**
		 * set 功能ID,对应ContralEnum
		 */
		public function set contral(value: int): void{
			this._contral = value;
		}
		
		/**
		 * get 0:关闭 1:开启
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 0:关闭 1:开启
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
	}
}