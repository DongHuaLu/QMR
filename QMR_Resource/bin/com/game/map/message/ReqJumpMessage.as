package com.game.map.message{
	import com.game.structs.Position;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家跳跃信息
	 */
	public class ReqJumpMessage extends Message {
	
		//跳跃类型
		private var _type: int;
		
		//跳跃坐标集合
		private var _positions: Vector.<com.game.structs.Position> = new Vector.<com.game.structs.Position>();
		//是否后端强制
		private var _force: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//跳跃类型
			writeInt(_type);
			//跳跃坐标集合
			writeShort(_positions.length);
			for (i = 0; i < _positions.length; i++) {
				writeBean(_positions[i]);
			}
			//是否后端强制
			writeInt(_force);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//跳跃类型
			_type = readInt();
			//跳跃坐标集合
			var positions_length : int = readShort();
			for (i = 0; i < positions_length; i++) {
				_positions[i] = readBean(com.game.structs.Position) as com.game.structs.Position;
			}
			//是否后端强制
			_force = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101202;
		}
		
		/**
		 * get 跳跃类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 跳跃类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 跳跃坐标集合
		 * @return 
		 */
		public function get positions(): Vector.<com.game.structs.Position>{
			return _positions;
		}
		
		/**
		 * set 跳跃坐标集合
		 */
		public function set positions(value: Vector.<com.game.structs.Position>): void{
			this._positions = value;
		}
		
		/**
		 * get 是否后端强制
		 * @return 
		 */
		public function get force(): int{
			return _force;
		}
		
		/**
		 * set 是否后端强制
		 */
		public function set force(value: int): void{
			this._force = value;
		}
		
	}
}