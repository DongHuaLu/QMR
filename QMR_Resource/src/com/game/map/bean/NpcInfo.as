package com.game.map.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * NPC信息
	 */
	public class NpcInfo extends Bean {
	
		//NPCID
		private var _npcId: long;
		
		//NPC模型ID
		private var _npcModelId: int;
		
		//NPC名字
		private var _npcName: String;
		
		//NPC资源造型
		private var _npcRes: String;
		
		//NPC头像造型
		private var _npcIcon: String;
		
		//方向
		private var _dir: int;
		
		//坐标X
		private var _x: int;
		
		//坐标Y
		private var _y: int;
		
		//NPC状态
		private var _state: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//NPCID
			writeLong(_npcId);
			//NPC模型ID
			writeInt(_npcModelId);
			//NPC名字
			writeString(_npcName);
			//NPC资源造型
			writeString(_npcRes);
			//NPC头像造型
			writeString(_npcIcon);
			//方向
			writeByte(_dir);
			//坐标X
			writeShort(_x);
			//坐标Y
			writeShort(_y);
			//NPC状态
			writeByte(_state);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//NPCID
			_npcId = readLong();
			//NPC模型ID
			_npcModelId = readInt();
			//NPC名字
			_npcName = readString();
			//NPC资源造型
			_npcRes = readString();
			//NPC头像造型
			_npcIcon = readString();
			//方向
			_dir = readByte();
			//坐标X
			_x = readShort();
			//坐标Y
			_y = readShort();
			//NPC状态
			_state = readByte();
			return true;
		}
		
		/**
		 * get NPCID
		 * @return 
		 */
		public function get npcId(): long{
			return _npcId;
		}
		
		/**
		 * set NPCID
		 */
		public function set npcId(value: long): void{
			this._npcId = value;
		}
		
		/**
		 * get NPC模型ID
		 * @return 
		 */
		public function get npcModelId(): int{
			return _npcModelId;
		}
		
		/**
		 * set NPC模型ID
		 */
		public function set npcModelId(value: int): void{
			this._npcModelId = value;
		}
		
		/**
		 * get NPC名字
		 * @return 
		 */
		public function get npcName(): String{
			return _npcName;
		}
		
		/**
		 * set NPC名字
		 */
		public function set npcName(value: String): void{
			this._npcName = value;
		}
		
		/**
		 * get NPC资源造型
		 * @return 
		 */
		public function get npcRes(): String{
			return _npcRes;
		}
		
		/**
		 * set NPC资源造型
		 */
		public function set npcRes(value: String): void{
			this._npcRes = value;
		}
		
		/**
		 * get NPC头像造型
		 * @return 
		 */
		public function get npcIcon(): String{
			return _npcIcon;
		}
		
		/**
		 * set NPC头像造型
		 */
		public function set npcIcon(value: String): void{
			this._npcIcon = value;
		}
		
		/**
		 * get 方向
		 * @return 
		 */
		public function get dir(): int{
			return _dir;
		}
		
		/**
		 * set 方向
		 */
		public function set dir(value: int): void{
			this._dir = value;
		}
		
		/**
		 * get 坐标X
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 坐标X
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 坐标Y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 坐标Y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
		/**
		 * get NPC状态
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set NPC状态
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
	}
}