package com.game.map.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 格子坐标
	 */
	public class GridCoordinate extends Bean {
	
		//坐标X
		private var _x: int;
		
		//坐标Y
		private var _y: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
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
			//坐标X
			_x = readShort();
			//坐标Y
			_y = readShort();
			return true;
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