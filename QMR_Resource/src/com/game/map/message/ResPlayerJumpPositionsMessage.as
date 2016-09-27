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
	public class ResPlayerJumpPositionsMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//角色状态
		private var _state: int;
		
		//跳跃剩余时间
		private var _time: int;
		
		//跳跃坐标集合
		private var _positions: Vector.<com.game.structs.Position> = new Vector.<com.game.structs.Position>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//角色Id
			writeLong(_personId);
			//角色状态
			writeByte(_state);
			//跳跃剩余时间
			writeInt(_time);
			//跳跃坐标集合
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
			//角色Id
			_personId = readLong();
			//角色状态
			_state = readByte();
			//跳跃剩余时间
			_time = readInt();
			//跳跃坐标集合
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
			return 101112;
		}
		
		/**
		 * get 角色Id
		 * @return 
		 */
		public function get personId(): long{
			return _personId;
		}
		
		/**
		 * set 角色Id
		 */
		public function set personId(value: long): void{
			this._personId = value;
		}
		
		/**
		 * get 角色状态
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 角色状态
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
		
	}
}