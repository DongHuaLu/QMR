package com.game.map.message{
	import com.game.utils.long;
	import com.game.structs.Position;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 角色跳跃坐标广播
	 */
	public class ResPetJumpPositionsMessage extends Message {
	
		//侍宠Id
		private var _petId: long;
		
		//跳跃状态 0 一跳  1 二跳
		private var _state: int;
		
		//跳跃剩余时间
		private var _time: int;
		
		//跳跃坐标集合 只有两个元素 起点 终点
		private var _positions: Vector.<com.game.structs.Position> = new Vector.<com.game.structs.Position>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//侍宠Id
			writeLong(_petId);
			//跳跃状态 0 一跳  1 二跳
			writeByte(_state);
			//跳跃剩余时间
			writeInt(_time);
			//跳跃坐标集合 只有两个元素 起点 终点
			writeShort(_positions.length);
			for (i = 0; i < _positions.length; i++) {
				writeBean(_positions[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//侍宠Id
			_petId = readLong();
			//跳跃状态 0 一跳  1 二跳
			_state = readByte();
			//跳跃剩余时间
			_time = readInt();
			//跳跃坐标集合 只有两个元素 起点 终点
			var positions_length : int = readShort();
			for (i = 0; i < positions_length; i++) {
				_positions[i] = readBean(com.game.structs.Position) as com.game.structs.Position;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101133;
		}
		
		/**
		 * get 侍宠Id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 侍宠Id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 跳跃状态 0 一跳  1 二跳
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 跳跃状态 0 一跳  1 二跳
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
		/**
		 * get 跳跃剩余时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 跳跃剩余时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 跳跃坐标集合 只有两个元素 起点 终点
		 * @return 
		 */
		public function get positions(): Vector.<com.game.structs.Position>{
			return _positions;
		}
		
		/**
		 * set 跳跃坐标集合 只有两个元素 起点 终点
		 */
		public function set positions(value: Vector.<com.game.structs.Position>): void{
			this._positions = value;
		}
		
	}
}