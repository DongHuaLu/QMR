package com.game.login.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知网关角色创建回报消息
	 */
	public class ResRolesCreateReportToClientMessage extends Message {
	
		//角色名字
		private var _playername: String;
		
		//角色id
		private var _playerId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色名字
			writeString(_playername);
			//角色id
			writeLong(_playerId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色名字
			_playername = readString();
			//角色id
			_playerId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100111;
		}
		
		/**
		 * get 角色名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 角色名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 角色id
		 * @return 
		 */
		public function get playerId(): long{
			return _playerId;
		}
		
		/**
		 * set 角色id
		 */
		public function set playerId(value: long): void{
			this._playerId = value;
		}
		
	}
}