package com.game.map.message{
	import com.game.map.bean.GridCoordinate;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 动态格子列表
	 */
	public class ResDynamicgridsMessage extends Message {
	
		//格子坐标列表
		private var _grids: Vector.<GridCoordinate> = new Vector.<GridCoordinate>();
		//格子类型，0取消阻挡，1变成阻挡禁止跳跃
		private var _type: int;
		
		//是否全部都存在
		private var _all: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//格子坐标列表
			writeShort(_grids.length);
			for (i = 0; i < _grids.length; i++) {
				writeBean(_grids[i]);
			}
			//格子类型，0取消阻挡，1变成阻挡禁止跳跃
			writeInt(_type);
			//是否全部都存在
			writeInt(_all);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//格子坐标列表
			var grids_length : int = readShort();
			for (i = 0; i < grids_length; i++) {
				_grids[i] = readBean(GridCoordinate) as GridCoordinate;
			}
			//格子类型，0取消阻挡，1变成阻挡禁止跳跃
			_type = readInt();
			//是否全部都存在
			_all = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101136;
		}
		
		/**
		 * get 格子坐标列表
		 * @return 
		 */
		public function get grids(): Vector.<GridCoordinate>{
			return _grids;
		}
		
		/**
		 * set 格子坐标列表
		 */
		public function set grids(value: Vector.<GridCoordinate>): void{
			this._grids = value;
		}
		
		/**
		 * get 格子类型，0取消阻挡，1变成阻挡禁止跳跃
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 格子类型，0取消阻挡，1变成阻挡禁止跳跃
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 是否全部都存在
		 * @return 
		 */
		public function get all(): int{
			return _all;
		}
		
		/**
		 * set 是否全部都存在
		 */
		public function set all(value: int): void{
			this._all = value;
		}
		
	}
}