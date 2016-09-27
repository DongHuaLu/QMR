package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 战车攻击提前移动镜像观察
	 */
	public class ReqCountryWarCarInAdvanceToGameMessage extends Message {
	
		//NPCid
		private var _npcid: int;
		
		//战车类型
		private var _type: int;
		
		//攻击坐标X
		private var _x: int;
		
		//攻击坐标Y
		private var _y: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//NPCid
			writeInt(_npcid);
			//战车类型
			writeByte(_type);
			//攻击坐标X
			writeInt(_x);
			//攻击坐标Y
			writeInt(_y);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//NPCid
			_npcid = readInt();
			//战车类型
			_type = readByte();
			//攻击坐标X
			_x = readInt();
			//攻击坐标Y
			_y = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146206;
		}
		
		/**
		 * get NPCid
		 * @return 
		 */
		public function get npcid(): int{
			return _npcid;
		}
		
		/**
		 * set NPCid
		 */
		public function set npcid(value: int): void{
			this._npcid = value;
		}
		
		/**
		 * get 战车类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 战车类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 攻击坐标X
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 攻击坐标X
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 攻击坐标Y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 攻击坐标Y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
	}
}