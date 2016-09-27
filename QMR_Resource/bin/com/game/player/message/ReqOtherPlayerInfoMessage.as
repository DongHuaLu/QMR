package com.game.player.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求其他玩家详细信息
	 */
	public class ReqOtherPlayerInfoMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//类型： 0角色，1坐骑
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//类型： 0角色，1坐骑
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//类型： 0角色，1坐骑
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103203;
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
		 * get 类型： 0角色，1坐骑
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型： 0角色，1坐骑
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}