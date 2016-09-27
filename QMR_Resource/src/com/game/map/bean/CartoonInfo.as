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
	 * 场景动画信息
	 */
	public class CartoonInfo extends Bean {
	
		//动画ID
		private var _cartoonId: long;
		
		//动画模型ID
		private var _cartoonModelId: int;
		
		//播放类型， 0-播放一次然后回复原型， 1-播放一次，维持最后一帧
		private var _type: int;
		
		//坐标X
		private var _x: int;
		
		//坐标Y
		private var _y: int;
		
		//方向
		private var _direction: int;
		
		//实施者类型 1-玩家 2-npc 3-怪物
		private var _sourceType: int;
		
		//实施者
		private var _source: long;
		
		//对象类型
		private var _targetType: int;
		
		//对象
		private var _target: long;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//动画ID
			writeLong(_cartoonId);
			//动画模型ID
			writeInt(_cartoonModelId);
			//播放类型， 0-播放一次然后回复原型， 1-播放一次，维持最后一帧
			writeByte(_type);
			//坐标X
			writeShort(_x);
			//坐标Y
			writeShort(_y);
			//方向
			writeInt(_direction);
			//实施者类型 1-玩家 2-npc 3-怪物
			writeInt(_sourceType);
			//实施者
			writeLong(_source);
			//对象类型
			writeInt(_targetType);
			//对象
			writeLong(_target);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//动画ID
			_cartoonId = readLong();
			//动画模型ID
			_cartoonModelId = readInt();
			//播放类型， 0-播放一次然后回复原型， 1-播放一次，维持最后一帧
			_type = readByte();
			//坐标X
			_x = readShort();
			//坐标Y
			_y = readShort();
			//方向
			_direction = readInt();
			//实施者类型 1-玩家 2-npc 3-怪物
			_sourceType = readInt();
			//实施者
			_source = readLong();
			//对象类型
			_targetType = readInt();
			//对象
			_target = readLong();
			return true;
		}
		
		/**
		 * get 动画ID
		 * @return 
		 */
		public function get cartoonId(): long{
			return _cartoonId;
		}
		
		/**
		 * set 动画ID
		 */
		public function set cartoonId(value: long): void{
			this._cartoonId = value;
		}
		
		/**
		 * get 动画模型ID
		 * @return 
		 */
		public function get cartoonModelId(): int{
			return _cartoonModelId;
		}
		
		/**
		 * set 动画模型ID
		 */
		public function set cartoonModelId(value: int): void{
			this._cartoonModelId = value;
		}
		
		/**
		 * get 播放类型， 0-播放一次然后回复原型， 1-播放一次，维持最后一帧
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 播放类型， 0-播放一次然后回复原型， 1-播放一次，维持最后一帧
		 */
		public function set type(value: int): void{
			this._type = value;
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
		
		/**
		 * get 方向
		 * @return 
		 */
		public function get direction(): int{
			return _direction;
		}
		
		/**
		 * set 方向
		 */
		public function set direction(value: int): void{
			this._direction = value;
		}
		
		/**
		 * get 实施者类型 1-玩家 2-npc 3-怪物
		 * @return 
		 */
		public function get sourceType(): int{
			return _sourceType;
		}
		
		/**
		 * set 实施者类型 1-玩家 2-npc 3-怪物
		 */
		public function set sourceType(value: int): void{
			this._sourceType = value;
		}
		
		/**
		 * get 实施者
		 * @return 
		 */
		public function get source(): long{
			return _source;
		}
		
		/**
		 * set 实施者
		 */
		public function set source(value: long): void{
			this._source = value;
		}
		
		/**
		 * get 对象类型
		 * @return 
		 */
		public function get targetType(): int{
			return _targetType;
		}
		
		/**
		 * set 对象类型
		 */
		public function set targetType(value: int): void{
			this._targetType = value;
		}
		
		/**
		 * get 对象
		 * @return 
		 */
		public function get target(): long{
			return _target;
		}
		
		/**
		 * set 对象
		 */
		public function set target(value: long): void{
			this._target = value;
		}
		
	}
}