package com.game.equip.bean{
	import com.game.utils.long;
	import com.game.equip.bean.EquipAttribute;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 装备信息类
	 */
	public class EquipInfo extends Bean {
	
		//角色Id
		private var _itemId: long;
		
		//物品模板Id
		private var _itemModelId: int;
		
		//物品强化等级
		private var _itemLevel: int;
		
		//物品附加属性
		private var _itemAttributes: Vector.<EquipAttribute> = new Vector.<EquipAttribute>();
		//物品绑定
		private var _itemBind: int;
		
		//物品过期时间
		private var _itemLosttime: long;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_itemId);
			//物品模板Id
			writeInt(_itemModelId);
			//物品强化等级
			writeByte(_itemLevel);
			//物品附加属性
			writeShort(_itemAttributes.length);
			for (var i: int = 0; i < _itemAttributes.length; i++) {
				writeBean(_itemAttributes[i]);
			}
			//物品绑定
			writeByte(_itemBind);
			//物品过期时间
			writeLong(_itemLosttime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_itemId = readLong();
			//物品模板Id
			_itemModelId = readInt();
			//物品强化等级
			_itemLevel = readByte();
			//物品附加属性
			var itemAttributes_length : int = readShort();
			for (var i: int = 0; i < itemAttributes_length; i++) {
				_itemAttributes[i] = readBean(EquipAttribute) as EquipAttribute;
			}
			//物品绑定
			_itemBind = readByte();
			//物品过期时间
			_itemLosttime = readLong();
			return true;
		}
		
		/**
		 * get 角色Id
		 * @return 
		 */
		public function get itemId(): long{
			return _itemId;
		}
		
		/**
		 * set 角色Id
		 */
		public function set itemId(value: long): void{
			this._itemId = value;
		}
		
		/**
		 * get 物品模板Id
		 * @return 
		 */
		public function get itemModelId(): int{
			return _itemModelId;
		}
		
		/**
		 * set 物品模板Id
		 */
		public function set itemModelId(value: int): void{
			this._itemModelId = value;
		}
		
		/**
		 * get 物品强化等级
		 * @return 
		 */
		public function get itemLevel(): int{
			return _itemLevel;
		}
		
		/**
		 * set 物品强化等级
		 */
		public function set itemLevel(value: int): void{
			this._itemLevel = value;
		}
		
		/**
		 * get 物品附加属性
		 * @return 
		 */
		public function get itemAttributes(): Vector.<EquipAttribute>{
			return _itemAttributes;
		}
		
		/**
		 * set 物品附加属性
		 */
		public function set itemAttributes(value: Vector.<EquipAttribute>): void{
			this._itemAttributes = value;
		}
		
		/**
		 * get 物品绑定
		 * @return 
		 */
		public function get itemBind(): int{
			return _itemBind;
		}
		
		/**
		 * set 物品绑定
		 */
		public function set itemBind(value: int): void{
			this._itemBind = value;
		}
		
		/**
		 * get 物品过期时间
		 * @return 
		 */
		public function get itemLosttime(): long{
			return _itemLosttime;
		}
		
		/**
		 * set 物品过期时间
		 */
		public function set itemLosttime(value: long): void{
			this._itemLosttime = value;
		}
		
	}
}