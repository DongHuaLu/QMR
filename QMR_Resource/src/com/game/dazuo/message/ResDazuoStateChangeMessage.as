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
	 * 打座状态变化 发给自己
	 */
	public class ResDazuoStateChangeMessage extends Message {
	
		//0未打座  1单人 1与宠物双修 2与玩家双修
		private var _state: int;
		
		//双修对象ID
		private var _otherid: long;
		
		//双修对象坐标X
		private var _otherx: int;
		
		//双修对象坐标Y
		private var _othery: int;
		
		//打座开始时间
		private var _startTime: long;
		
		//暴击次数
		private var _eruptCount: int;
		
		//暴击获得经验
		private var _eruptExp: int;
		
		//暴击获得真气
		private var _eruptZQ: int;
		
		//自己出战的宠物列表
		private var _onwerPets: Vector.<long> = new Vector.<long>();
		//自己出战的宠物列表
		private var _otherPets: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//0未打座  1单人 1与宠物双修 2与玩家双修
			writeByte(_state);
			//双修对象ID
			writeLong(_otherid);
			//双修对象坐标X
			writeShort(_otherx);
			//双修对象坐标Y
			writeShort(_othery);
			//打座开始时间
			writeLong(_startTime);
			//暴击次数
			writeInt(_eruptCount);
			//暴击获得经验
			writeInt(_eruptExp);
			//暴击获得真气
			writeInt(_eruptZQ);
			//自己出战的宠物列表
			writeShort(_onwerPets.length);
			for (i = 0; i < _onwerPets.length; i++) {
				writeLong(_onwerPets[i]);
			}
			//自己出战的宠物列表
			writeShort(_otherPets.length);
			for (i = 0; i < _otherPets.length; i++) {
				writeLong(_otherPets[i]);
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
			//双修对象ID
			_otherid = readLong();
			//双修对象坐标X
			_otherx = readShort();
			//双修对象坐标Y
			_othery = readShort();
			//打座开始时间
			_startTime = readLong();
			//暴击次数
			_eruptCount = readInt();
			//暴击获得经验
			_eruptExp = readInt();
			//暴击获得真气
			_eruptZQ = readInt();
			//自己出战的宠物列表
			var onwerPets_length : int = readShort();
			for (i = 0; i < onwerPets_length; i++) {
				_onwerPets[i] = readLong();
			}
			//自己出战的宠物列表
			var otherPets_length : int = readShort();
			for (i = 0; i < otherPets_length; i++) {
				_otherPets[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 136101;
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
		 * get 双修对象ID
		 * @return 
		 */
		public function get otherid(): long{
			return _otherid;
		}
		
		/**
		 * set 双修对象ID
		 */
		public function set otherid(value: long): void{
			this._otherid = value;
		}
		
		/**
		 * get 双修对象坐标X
		 * @return 
		 */
		public function get otherx(): int{
			return _otherx;
		}
		
		/**
		 * set 双修对象坐标X
		 */
		public function set otherx(value: int): void{
			this._otherx = value;
		}
		
		/**
		 * get 双修对象坐标Y
		 * @return 
		 */
		public function get othery(): int{
			return _othery;
		}
		
		/**
		 * set 双修对象坐标Y
		 */
		public function set othery(value: int): void{
			this._othery = value;
		}
		
		/**
		 * get 打座开始时间
		 * @return 
		 */
		public function get startTime(): long{
			return _startTime;
		}
		
		/**
		 * set 打座开始时间
		 */
		public function set startTime(value: long): void{
			this._startTime = value;
		}
		
		/**
		 * get 暴击次数
		 * @return 
		 */
		public function get eruptCount(): int{
			return _eruptCount;
		}
		
		/**
		 * set 暴击次数
		 */
		public function set eruptCount(value: int): void{
			this._eruptCount = value;
		}
		
		/**
		 * get 暴击获得经验
		 * @return 
		 */
		public function get eruptExp(): int{
			return _eruptExp;
		}
		
		/**
		 * set 暴击获得经验
		 */
		public function set eruptExp(value: int): void{
			this._eruptExp = value;
		}
		
		/**
		 * get 暴击获得真气
		 * @return 
		 */
		public function get eruptZQ(): int{
			return _eruptZQ;
		}
		
		/**
		 * set 暴击获得真气
		 */
		public function set eruptZQ(value: int): void{
			this._eruptZQ = value;
		}
		
		/**
		 * get 自己出战的宠物列表
		 * @return 
		 */
		public function get onwerPets(): Vector.<long>{
			return _onwerPets;
		}
		
		/**
		 * set 自己出战的宠物列表
		 */
		public function set onwerPets(value: Vector.<long>): void{
			this._onwerPets = value;
		}
		
		/**
		 * get 自己出战的宠物列表
		 * @return 
		 */
		public function get otherPets(): Vector.<long>{
			return _otherPets;
		}
		
		/**
		 * set 自己出战的宠物列表
		 */
		public function set otherPets(value: Vector.<long>): void{
			this._otherPets = value;
		}
		
	}
}