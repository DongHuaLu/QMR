package com.game.player.message{
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
	 * 玩家死亡
	 */
	public class ResPlayerDieMessage extends Message {
	
		//死亡的角色Id
		private var _personId: long;
		
		//自动复活
		private var _autoRevive: int;
		
		//怪物模型ID
		private var _monstermodelid: int;
		
		//攻击者玩家ID
		private var _attackerid: long;
		
		//攻击者玩家名字
		private var _attackername: String;
		
		//死亡位置
		private var _position: com.game.structs.Position;
		
		//死亡时间
		private var _time: int;
		
		//0正常死亡，1攻城战期间在攻城战地图死亡
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//死亡的角色Id
			writeLong(_personId);
			//自动复活
			writeByte(_autoRevive);
			//怪物模型ID
			writeInt(_monstermodelid);
			//攻击者玩家ID
			writeLong(_attackerid);
			//攻击者玩家名字
			writeString(_attackername);
			//死亡位置
			writeBean(_position);
			//死亡时间
			writeInt(_time);
			//0正常死亡，1攻城战期间在攻城战地图死亡
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//死亡的角色Id
			_personId = readLong();
			//自动复活
			_autoRevive = readByte();
			//怪物模型ID
			_monstermodelid = readInt();
			//攻击者玩家ID
			_attackerid = readLong();
			//攻击者玩家名字
			_attackername = readString();
			//死亡位置
			_position = readBean(com.game.structs.Position) as com.game.structs.Position;
			//死亡时间
			_time = readInt();
			//0正常死亡，1攻城战期间在攻城战地图死亡
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103115;
		}
		
		/**
		 * get 死亡的角色Id
		 * @return 
		 */
		public function get personId(): long{
			return _personId;
		}
		
		/**
		 * set 死亡的角色Id
		 */
		public function set personId(value: long): void{
			this._personId = value;
		}
		
		/**
		 * get 自动复活
		 * @return 
		 */
		public function get autoRevive(): int{
			return _autoRevive;
		}
		
		/**
		 * set 自动复活
		 */
		public function set autoRevive(value: int): void{
			this._autoRevive = value;
		}
		
		/**
		 * get 怪物模型ID
		 * @return 
		 */
		public function get monstermodelid(): int{
			return _monstermodelid;
		}
		
		/**
		 * set 怪物模型ID
		 */
		public function set monstermodelid(value: int): void{
			this._monstermodelid = value;
		}
		
		/**
		 * get 攻击者玩家ID
		 * @return 
		 */
		public function get attackerid(): long{
			return _attackerid;
		}
		
		/**
		 * set 攻击者玩家ID
		 */
		public function set attackerid(value: long): void{
			this._attackerid = value;
		}
		
		/**
		 * get 攻击者玩家名字
		 * @return 
		 */
		public function get attackername(): String{
			return _attackername;
		}
		
		/**
		 * set 攻击者玩家名字
		 */
		public function set attackername(value: String): void{
			this._attackername = value;
		}
		
		/**
		 * get 死亡位置
		 * @return 
		 */
		public function get position(): com.game.structs.Position{
			return _position;
		}
		
		/**
		 * set 死亡位置
		 */
		public function set position(value: com.game.structs.Position): void{
			this._position = value;
		}
		
		/**
		 * get 死亡时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 死亡时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 0正常死亡，1攻城战期间在攻城战地图死亡
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0正常死亡，1攻城战期间在攻城战地图死亡
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}