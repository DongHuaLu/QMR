package com.game.longyuan.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 自定义特效展示（通用）
	 */
	public class ShowEffectInfo extends Bean {
	
		//类型，1地面效果，2人物效果，3怪物效果
		private var _type: int;
		
		//怪物ID或者玩家ID
		private var _id: long;
		
		//特效ID，和前端定
		private var _effectid: int;
		
		//坐标X，像素
		private var _x: int;
		
		//坐标Y，像素
		private var _y: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型，1地面效果，2人物效果，3怪物效果
			writeByte(_type);
			//怪物ID或者玩家ID
			writeLong(_id);
			//特效ID，和前端定
			writeInt(_effectid);
			//坐标X，像素
			writeShort(_x);
			//坐标Y，像素
			writeShort(_y);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型，1地面效果，2人物效果，3怪物效果
			_type = readByte();
			//怪物ID或者玩家ID
			_id = readLong();
			//特效ID，和前端定
			_effectid = readInt();
			//坐标X，像素
			_x = readShort();
			//坐标Y，像素
			_y = readShort();
			return true;
		}
		
		/**
		 * get 类型，1地面效果，2人物效果，3怪物效果
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型，1地面效果，2人物效果，3怪物效果
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 怪物ID或者玩家ID
		 * @return 
		 */
		public function get id(): long{
			return _id;
		}
		
		/**
		 * set 怪物ID或者玩家ID
		 */
		public function set id(value: long): void{
			this._id = value;
		}
		
		/**
		 * get 特效ID，和前端定
		 * @return 
		 */
		public function get effectid(): int{
			return _effectid;
		}
		
		/**
		 * set 特效ID，和前端定
		 */
		public function set effectid(value: int): void{
			this._effectid = value;
		}
		
		/**
		 * get 坐标X，像素
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 坐标X，像素
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 坐标Y，像素
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 坐标Y，像素
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
	}
}