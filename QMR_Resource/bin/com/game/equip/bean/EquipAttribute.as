package com.game.equip.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 装备附加属性类
	 */
	public class EquipAttribute extends Bean {
	
		//附加属性类型
		private var _attributeType: int;
		
		//附加属性值
		private var _attributeValue: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//附加属性类型
			writeByte(_attributeType);
			//附加属性值
			writeByte(_attributeValue);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//附加属性类型
			_attributeType = readByte();
			//附加属性值
			_attributeValue = readByte();
			return true;
		}
		
		/**
		 * get 附加属性类型
		 * @return 
		 */
		public function get attributeType(): int{
			return _attributeType;
		}
		
		/**
		 * set 附加属性类型
		 */
		public function set attributeType(value: int): void{
			this._attributeType = value;
		}
		
		/**
		 * get 附加属性值
		 * @return 
		 */
		public function get attributeValue(): int{
			return _attributeValue;
		}
		
		/**
		 * set 附加属性值
		 */
		public function set attributeValue(value: int): void{
			this._attributeValue = value;
		}
		
	}
}