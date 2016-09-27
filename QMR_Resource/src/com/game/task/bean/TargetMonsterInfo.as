package com.game.task.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 同步怪物的信息
	 */
	public class TargetMonsterInfo extends Bean {
	
		//怪物状态 0未刷新 1未死亡
		private var _state: int;
		
		//怪物模型ID
		private var _modelId: int;
		
		//服务器ID
		private var _serverId: int;
		
		//线ID
		private var _lineId: int;
		
		//怪物当前血量
		private var _hp: int;
		
		//地图模型ID
		private var _mapId: int;
		
		//上次击杀者名字
		private var _killer: String;
		
		//重生时间
		private var _reliveTime: int;
		
		//座标X
		private var _birthX: int;
		
		//座标Y
		private var _birthY: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//怪物状态 0未刷新 1未死亡
			writeByte(_state);
			//怪物模型ID
			writeInt(_modelId);
			//服务器ID
			writeInt(_serverId);
			//线ID
			writeInt(_lineId);
			//怪物当前血量
			writeInt(_hp);
			//地图模型ID
			writeInt(_mapId);
			//上次击杀者名字
			writeString(_killer);
			//重生时间
			writeInt(_reliveTime);
			//座标X
			writeShort(_birthX);
			//座标Y
			writeShort(_birthY);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//怪物状态 0未刷新 1未死亡
			_state = readByte();
			//怪物模型ID
			_modelId = readInt();
			//服务器ID
			_serverId = readInt();
			//线ID
			_lineId = readInt();
			//怪物当前血量
			_hp = readInt();
			//地图模型ID
			_mapId = readInt();
			//上次击杀者名字
			_killer = readString();
			//重生时间
			_reliveTime = readInt();
			//座标X
			_birthX = readShort();
			//座标Y
			_birthY = readShort();
			return true;
		}
		
		/**
		 * get 怪物状态 0未刷新 1未死亡
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 怪物状态 0未刷新 1未死亡
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
		/**
		 * get 怪物模型ID
		 * @return 
		 */
		public function get modelId(): int{
			return _modelId;
		}
		
		/**
		 * set 怪物模型ID
		 */
		public function set modelId(value: int): void{
			this._modelId = value;
		}
		
		/**
		 * get 服务器ID
		 * @return 
		 */
		public function get serverId(): int{
			return _serverId;
		}
		
		/**
		 * set 服务器ID
		 */
		public function set serverId(value: int): void{
			this._serverId = value;
		}
		
		/**
		 * get 线ID
		 * @return 
		 */
		public function get lineId(): int{
			return _lineId;
		}
		
		/**
		 * set 线ID
		 */
		public function set lineId(value: int): void{
			this._lineId = value;
		}
		
		/**
		 * get 怪物当前血量
		 * @return 
		 */
		public function get hp(): int{
			return _hp;
		}
		
		/**
		 * set 怪物当前血量
		 */
		public function set hp(value: int): void{
			this._hp = value;
		}
		
		/**
		 * get 地图模型ID
		 * @return 
		 */
		public function get mapId(): int{
			return _mapId;
		}
		
		/**
		 * set 地图模型ID
		 */
		public function set mapId(value: int): void{
			this._mapId = value;
		}
		
		/**
		 * get 上次击杀者名字
		 * @return 
		 */
		public function get killer(): String{
			return _killer;
		}
		
		/**
		 * set 上次击杀者名字
		 */
		public function set killer(value: String): void{
			this._killer = value;
		}
		
		/**
		 * get 重生时间
		 * @return 
		 */
		public function get reliveTime(): int{
			return _reliveTime;
		}
		
		/**
		 * set 重生时间
		 */
		public function set reliveTime(value: int): void{
			this._reliveTime = value;
		}
		
		/**
		 * get 座标X
		 * @return 
		 */
		public function get birthX(): int{
			return _birthX;
		}
		
		/**
		 * set 座标X
		 */
		public function set birthX(value: int): void{
			this._birthX = value;
		}
		
		/**
		 * get 座标Y
		 * @return 
		 */
		public function get birthY(): int{
			return _birthY;
		}
		
		/**
		 * set 座标Y
		 */
		public function set birthY(value: int): void{
			this._birthY = value;
		}
		
	}
}