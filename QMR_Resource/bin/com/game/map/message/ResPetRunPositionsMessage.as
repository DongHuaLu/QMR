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
	 * 宠物跑步度座标
	 */
	public class ResPetRunPositionsMessage extends Message {
	
		//宠物Id
		private var _petId: long;
		
		//当前坐标
		private var _position: com.game.structs.Position;
		
		//跑步坐标集合
		private var _positions: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//宠物Id
			writeLong(_petId);
			//当前坐标
			writeBean(_position);
			//跑步坐标集合
			writeShort(_positions.length);
			for (i = 0; i < _positions.length; i++) {
				writeByte(_positions[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//宠物Id
			_petId = readLong();
			//当前坐标
			_position = readBean(com.game.structs.Position) as com.game.structs.Position;
			//跑步坐标集合
			var positions_length : int = readShort();
			for (i = 0; i < positions_length; i++) {
				_positions[i] = readByte();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101122;
		}
		
		/**
		 * get 宠物Id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 宠物Id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 当前坐标
		 * @return 
		 */
		public function get position(): com.game.structs.Position{
			return _position;
		}
		
		/**
		 * set 当前坐标
		 */
		public function set position(value: com.game.structs.Position): void{
			this._position = value;
		}
		
		/**
		 * get 跑步坐标集合
		 * @return 
		 */
		public function get positions(): Vector.<int>{
			return _positions;
		}
		
		/**
		 * set 跑步坐标集合
		 */
		public function set positions(value: Vector.<int>): void{
			this._positions = value;
		}
		
	}
}