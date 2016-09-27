package com.game.backpack.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 物品附加项
	 */
	public class GoodsAttribute extends Bean {
	
		//属性类型
		private var _type: int;
		
		//属性值
		private var _value: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//属性类型
			writeInt(_type);
			//属性值
			writeInt(_value);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//属性类型
			_type = readInt();
			//属性值
			_value = readInt();
			return true;
		}
		
		/**
		 * get 属性类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 属性类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 属性值
		 * @return 
		 */
		public function get value(): int{
			return _value;
		}
		
		/**
		 * set 属性值
		 */
		public function set value(value: int): void{
			this._value = value;
		}
		
	}
}