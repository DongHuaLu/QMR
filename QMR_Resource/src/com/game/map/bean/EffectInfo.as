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
	 * 场景效果信息
	 */
	public class EffectInfo extends Bean {
	
		//EffectID
		private var _effectId: long;
		
		//Effect模型ID
		private var _effectModelId: int;
		
		//播放类型
		private var _type: int;
		
		//坐标X
		private var _x: int;
		
		//坐标Y
		private var _y: int;
		
		//播放时间
		private var _play: int;
		
		//对象
		private var _target: long;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//EffectID
			writeLong(_effectId);
			//Effect模型ID
			writeInt(_effectModelId);
			//播放类型
			writeByte(_type);
			//坐标X
			writeShort(_x);
			//坐标Y
			writeShort(_y);
			//播放时间
			writeInt(_play);
			//对象
			writeLong(_target);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//EffectID
			_effectId = readLong();
			//Effect模型ID
			_effectModelId = readInt();
			//播放类型
			_type = readByte();
			//坐标X
			_x = readShort();
			//坐标Y
			_y = readShort();
			//播放时间
			_play = readInt();
			//对象
			_target = readLong();
			return true;
		}
		
		/**
		 * get EffectID
		 * @return 
		 */
		public function get effectId(): long{
			return _effectId;
		}
		
		/**
		 * set EffectID
		 */
		public function set effectId(value: long): void{
			this._effectId = value;
		}
		
		/**
		 * get Effect模型ID
		 * @return 
		 */
		public function get effectModelId(): int{
			return _effectModelId;
		}
		
		/**
		 * set Effect模型ID
		 */
		public function set effectModelId(value: int): void{
			this._effectModelId = value;
		}
		
		/**
		 * get 播放类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 播放类型
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
		 * get 播放时间
		 * @return 
		 */
		public function get play(): int{
			return _play;
		}
		
		/**
		 * set 播放时间
		 */
		public function set play(value: int): void{
			this._play = value;
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