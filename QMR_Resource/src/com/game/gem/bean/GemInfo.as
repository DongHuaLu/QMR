package com.game.gem.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 单个宝石信息
	 */
	public class GemInfo extends Bean {
	
		//宝石唯一ID
		private var _id: long;
		
		//宝石等级
		private var _level: int;
		
		//宝石类型
		private var _type: int;
		
		//当前经验值
		private var _exp: int;
		
		//宝石位置（0-4）
		private var _grid: int;
		
		//当前宝石是否激活
		private var _isact: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宝石唯一ID
			writeLong(_id);
			//宝石等级
			writeByte(_level);
			//宝石类型
			writeByte(_type);
			//当前经验值
			writeInt(_exp);
			//宝石位置（0-4）
			writeByte(_grid);
			//当前宝石是否激活
			writeByte(_isact);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宝石唯一ID
			_id = readLong();
			//宝石等级
			_level = readByte();
			//宝石类型
			_type = readByte();
			//当前经验值
			_exp = readInt();
			//宝石位置（0-4）
			_grid = readByte();
			//当前宝石是否激活
			_isact = readByte();
			return true;
		}
		
		/**
		 * get 宝石唯一ID
		 * @return 
		 */
		public function get id(): long{
			return _id;
		}
		
		/**
		 * set 宝石唯一ID
		 */
		public function set id(value: long): void{
			this._id = value;
		}
		
		/**
		 * get 宝石等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 宝石等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
		/**
		 * get 宝石类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 宝石类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 当前经验值
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 当前经验值
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 宝石位置（0-4）
		 * @return 
		 */
		public function get grid(): int{
			return _grid;
		}
		
		/**
		 * set 宝石位置（0-4）
		 */
		public function set grid(value: int): void{
			this._grid = value;
		}
		
		/**
		 * get 当前宝石是否激活
		 * @return 
		 */
		public function get isact(): int{
			return _isact;
		}
		
		/**
		 * set 当前宝石是否激活
		 */
		public function set isact(value: int): void{
			this._isact = value;
		}
		
	}
}