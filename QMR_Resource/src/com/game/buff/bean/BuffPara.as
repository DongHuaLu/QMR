package com.game.buff.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * buff参数
	 */
	public class BuffPara extends Bean {
	
		//Buff 参数类型
		private var _type: int;
		
		//Buff 参数值
		private var _value: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//Buff 参数类型
			writeByte(_type);
			//Buff 参数值
			writeInt(_value);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//Buff 参数类型
			_type = readByte();
			//Buff 参数值
			_value = readInt();
			return true;
		}
		
		/**
		 * get Buff 参数类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set Buff 参数类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get Buff 参数值
		 * @return 
		 */
		public function get value(): int{
			return _value;
		}
		
		/**
		 * set Buff 参数值
		 */
		public function set value(value: int): void{
			this._value = value;
		}
		
	}
}