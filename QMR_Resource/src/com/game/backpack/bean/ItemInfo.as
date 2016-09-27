package com.game.backpack.bean{
	import com.game.utils.long;
	import com.game.backpack.bean.GoodsAttribute;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 物品信息类
	 */
	public class ItemInfo extends Bean {
	
		//角色Id
		private var _itemId: long;
		
		//物品模板Id
		private var _itemModelId: int;
		
		//物品数量
		private var _num: int;
		
		//角色所在格子Id
		private var _gridId: int;
		
		//是否绑定 1是 0否 
		private var _isbind: int;
		
		//强化等级
		private var _intensify: int;
		
		//扩展属性数量
		private var _attributs: int;
		
		//是否顶级附加 1是 0否
		private var _isFullAppend: int;
		
		//过期时间
		private var _lostTime: int;
		
		//扩展属性
		private var _goodAttributes: Vector.<GoodsAttribute> = new Vector.<GoodsAttribute>();
		//参数，额外信息
		private var _parameters: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_itemId);
			//物品模板Id
			writeInt(_itemModelId);
			//物品数量
			writeInt(_num);
			//角色所在格子Id
			writeInt(_gridId);
			//是否绑定 1是 0否 
			writeByte(_isbind);
			//强化等级
			writeByte(_intensify);
			//扩展属性数量
			writeByte(_attributs);
			//是否顶级附加 1是 0否
			writeByte(_isFullAppend);
			//过期时间
			writeInt(_lostTime);
			//扩展属性
			writeShort(_goodAttributes.length);
			for (var i: int = 0; i < _goodAttributes.length; i++) {
				writeBean(_goodAttributes[i]);
			}
			//参数，额外信息
			writeString(_parameters);
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
			//物品数量
			_num = readInt();
			//角色所在格子Id
			_gridId = readInt();
			//是否绑定 1是 0否 
			_isbind = readByte();
			//强化等级
			_intensify = readByte();
			//扩展属性数量
			_attributs = readByte();
			//是否顶级附加 1是 0否
			_isFullAppend = readByte();
			//过期时间
			_lostTime = readInt();
			//扩展属性
			var goodAttributes_length : int = readShort();
			for (var i: int = 0; i < goodAttributes_length; i++) {
				_goodAttributes[i] = readBean(GoodsAttribute) as GoodsAttribute;
			}
			//参数，额外信息
			_parameters = readString();
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
		 * get 物品数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 物品数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 角色所在格子Id
		 * @return 
		 */
		public function get gridId(): int{
			return _gridId;
		}
		
		/**
		 * set 角色所在格子Id
		 */
		public function set gridId(value: int): void{
			this._gridId = value;
		}
		
		/**
		 * get 是否绑定 1是 0否 
		 * @return 
		 */
		public function get isbind(): int{
			return _isbind;
		}
		
		/**
		 * set 是否绑定 1是 0否 
		 */
		public function set isbind(value: int): void{
			this._isbind = value;
		}
		
		/**
		 * get 强化等级
		 * @return 
		 */
		public function get intensify(): int{
			return _intensify;
		}
		
		/**
		 * set 强化等级
		 */
		public function set intensify(value: int): void{
			this._intensify = value;
		}
		
		/**
		 * get 扩展属性数量
		 * @return 
		 */
		public function get attributs(): int{
			return _attributs;
		}
		
		/**
		 * set 扩展属性数量
		 */
		public function set attributs(value: int): void{
			this._attributs = value;
		}
		
		/**
		 * get 是否顶级附加 1是 0否
		 * @return 
		 */
		public function get isFullAppend(): int{
			return _isFullAppend;
		}
		
		/**
		 * set 是否顶级附加 1是 0否
		 */
		public function set isFullAppend(value: int): void{
			this._isFullAppend = value;
		}
		
		/**
		 * get 过期时间
		 * @return 
		 */
		public function get lostTime(): int{
			return _lostTime;
		}
		
		/**
		 * set 过期时间
		 */
		public function set lostTime(value: int): void{
			this._lostTime = value;
		}
		
		/**
		 * get 扩展属性
		 * @return 
		 */
		public function get goodAttributes(): Vector.<GoodsAttribute>{
			return _goodAttributes;
		}
		
		/**
		 * set 扩展属性
		 */
		public function set goodAttributes(value: Vector.<GoodsAttribute>): void{
			this._goodAttributes = value;
		}
		
		/**
		 * get 参数，额外信息
		 * @return 
		 */
		public function get parameters(): String{
			return _parameters;
		}
		
		/**
		 * set 参数，额外信息
		 */
		public function set parameters(value: String): void{
			this._parameters = value;
		}
		
	}
}