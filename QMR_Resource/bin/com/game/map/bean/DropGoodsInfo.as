package com.game.map.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 掉落物品信息
	 */
	public class DropGoodsInfo extends Bean {
	
		//物品Id
		private var _dropGoodsId: long;
		
		//物品模板Id
		private var _itemModelId: int;
		
		//数量
		private var _num: int;
		
		//所有者ID 无主为0
		private var _ownerId: long;
		
		//掉落时间
		private var _dropTime: long;
		
		//强化等级
		private var _intensify: int;
		
		//扩展属性数量
		private var _attributs: int;
		
		//是否顶级附加 1是 0否
		private var _isFullAppend: int;
		
		//是否满强化 1是 0否
		private var _isFullStrength: int;
		
		//坐标X
		private var _x: int;
		
		//坐标Y
		private var _y: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品Id
			writeLong(_dropGoodsId);
			//物品模板Id
			writeInt(_itemModelId);
			//数量
			writeInt(_num);
			//所有者ID 无主为0
			writeLong(_ownerId);
			//掉落时间
			writeLong(_dropTime);
			//强化等级
			writeByte(_intensify);
			//扩展属性数量
			writeByte(_attributs);
			//是否顶级附加 1是 0否
			writeByte(_isFullAppend);
			//是否满强化 1是 0否
			writeByte(_isFullStrength);
			//坐标X
			writeShort(_x);
			//坐标Y
			writeShort(_y);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品Id
			_dropGoodsId = readLong();
			//物品模板Id
			_itemModelId = readInt();
			//数量
			_num = readInt();
			//所有者ID 无主为0
			_ownerId = readLong();
			//掉落时间
			_dropTime = readLong();
			//强化等级
			_intensify = readByte();
			//扩展属性数量
			_attributs = readByte();
			//是否顶级附加 1是 0否
			_isFullAppend = readByte();
			//是否满强化 1是 0否
			_isFullStrength = readByte();
			//坐标X
			_x = readShort();
			//坐标Y
			_y = readShort();
			return true;
		}
		
		/**
		 * get 物品Id
		 * @return 
		 */
		public function get dropGoodsId(): long{
			return _dropGoodsId;
		}
		
		/**
		 * set 物品Id
		 */
		public function set dropGoodsId(value: long): void{
			this._dropGoodsId = value;
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
		 * get 数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 所有者ID 无主为0
		 * @return 
		 */
		public function get ownerId(): long{
			return _ownerId;
		}
		
		/**
		 * set 所有者ID 无主为0
		 */
		public function set ownerId(value: long): void{
			this._ownerId = value;
		}
		
		/**
		 * get 掉落时间
		 * @return 
		 */
		public function get dropTime(): long{
			return _dropTime;
		}
		
		/**
		 * set 掉落时间
		 */
		public function set dropTime(value: long): void{
			this._dropTime = value;
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
		 * get 是否满强化 1是 0否
		 * @return 
		 */
		public function get isFullStrength(): int{
			return _isFullStrength;
		}
		
		/**
		 * set 是否满强化 1是 0否
		 */
		public function set isFullStrength(value: int): void{
			this._isFullStrength = value;
		}
		
		/**
		 * get 坐标X
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 坐标X
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 坐标Y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 坐标Y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
	}
}