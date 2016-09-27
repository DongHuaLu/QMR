package com.game.cloak.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 披风信息,发送给周围玩家的
	 */
	public class ResCloakInfoOtherMessage extends Message {
	
		//角色ID
		private var _playerid: long;
		
		//披风信息
		private var _model: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色ID
			writeLong(_playerid);
			//披风信息
			writeInt(_model);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色ID
			_playerid = readLong();
			//披风信息
			_model = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 170104;
		}
		
		/**
		 * get 角色ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 角色ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 披风信息
		 * @return 
		 */
		public function get model(): int{
			return _model;
		}
		
		/**
		 * set 披风信息
		 */
		public function set model(value: int): void{
			this._model = value;
		}
		
	}
}