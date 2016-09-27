package com.game.dazuo.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 以两个玩家为中心点进行 打座状态变化  广播
	 */
	public class ResDazuoStateBroadCastMessage extends Message {
	
		//0未打座  1单人 1与宠物双修 2与玩家双修
		private var _state: int;
		
		//双修玩家A
		private var _roleAId: long;
		
		//双修玩家AX
		private var _roleAX: int;
		
		//双修玩家AY
		private var _roleAY: int;
		
		//玩家A出战的宠物列表
		private var _roleAPets: Vector.<long> = new Vector.<long>();
		//双修玩家B 如果不是与玩家双修 则只发A
		private var _roleBId: long;
		
		//双修玩家BX
		private var _roleBX: int;
		
		//双修玩家BY
		private var _roleBY: int;
		
		//玩家B出战的宠物列表
		private var _roleBPets: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//0未打座  1单人 1与宠物双修 2与玩家双修
			writeByte(_state);
			//双修玩家A
			writeLong(_roleAId);
			//双修玩家AX
			writeShort(_roleAX);
			//双修玩家AY
			writeShort(_roleAY);
			//玩家A出战的宠物列表
			writeShort(_roleAPets.length);
			for (i = 0; i < _roleAPets.length; i++) {
				writeLong(_roleAPets[i]);
			}
			//双修玩家B 如果不是与玩家双修 则只发A
			writeLong(_roleBId);
			//双修玩家BX
			writeShort(_roleBX);
			//双修玩家BY
			writeShort(_roleBY);
			//玩家B出战的宠物列表
			writeShort(_roleBPets.length);
			for (i = 0; i < _roleBPets.length; i++) {
				writeLong(_roleBPets[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//0未打座  1单人 1与宠物双修 2与玩家双修
			_state = readByte();
			//双修玩家A
			_roleAId = readLong();
			//双修玩家AX
			_roleAX = readShort();
			//双修玩家AY
			_roleAY = readShort();
			//玩家A出战的宠物列表
			var roleAPets_length : int = readShort();
			for (i = 0; i < roleAPets_length; i++) {
				_roleAPets[i] = readLong();
			}
			//双修玩家B 如果不是与玩家双修 则只发A
			_roleBId = readLong();
			//双修玩家BX
			_roleBX = readShort();
			//双修玩家BY
			_roleBY = readShort();
			//玩家B出战的宠物列表
			var roleBPets_length : int = readShort();
			for (i = 0; i < roleBPets_length; i++) {
				_roleBPets[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 136102;
		}
		
		/**
		 * get 0未打座  1单人 1与宠物双修 2与玩家双修
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 0未打座  1单人 1与宠物双修 2与玩家双修
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
		/**
		 * get 双修玩家A
		 * @return 
		 */
		public function get roleAId(): long{
			return _roleAId;
		}
		
		/**
		 * set 双修玩家A
		 */
		public function set roleAId(value: long): void{
			this._roleAId = value;
		}
		
		/**
		 * get 双修玩家AX
		 * @return 
		 */
		public function get roleAX(): int{
			return _roleAX;
		}
		
		/**
		 * set 双修玩家AX
		 */
		public function set roleAX(value: int): void{
			this._roleAX = value;
		}
		
		/**
		 * get 双修玩家AY
		 * @return 
		 */
		public function get roleAY(): int{
			return _roleAY;
		}
		
		/**
		 * set 双修玩家AY
		 */
		public function set roleAY(value: int): void{
			this._roleAY = value;
		}
		
		/**
		 * get 玩家A出战的宠物列表
		 * @return 
		 */
		public function get roleAPets(): Vector.<long>{
			return _roleAPets;
		}
		
		/**
		 * set 玩家A出战的宠物列表
		 */
		public function set roleAPets(value: Vector.<long>): void{
			this._roleAPets = value;
		}
		
		/**
		 * get 双修玩家B 如果不是与玩家双修 则只发A
		 * @return 
		 */
		public function get roleBId(): long{
			return _roleBId;
		}
		
		/**
		 * set 双修玩家B 如果不是与玩家双修 则只发A
		 */
		public function set roleBId(value: long): void{
			this._roleBId = value;
		}
		
		/**
		 * get 双修玩家BX
		 * @return 
		 */
		public function get roleBX(): int{
			return _roleBX;
		}
		
		/**
		 * set 双修玩家BX
		 */
		public function set roleBX(value: int): void{
			this._roleBX = value;
		}
		
		/**
		 * get 双修玩家BY
		 * @return 
		 */
		public function get roleBY(): int{
			return _roleBY;
		}
		
		/**
		 * set 双修玩家BY
		 */
		public function set roleBY(value: int): void{
			this._roleBY = value;
		}
		
		/**
		 * get 玩家B出战的宠物列表
		 * @return 
		 */
		public function get roleBPets(): Vector.<long>{
			return _roleBPets;
		}
		
		/**
		 * set 玩家B出战的宠物列表
		 */
		public function set roleBPets(value: Vector.<long>): void{
			this._roleBPets = value;
		}
		
	}
}