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
	 * 删除玩家后返回给前端
	 */
	public class ResDelPlayerStatusToClientMessage extends Message {
	
		//角色ID
		private var _playerId: long;
		
		//用户ID
		private var _userId: String;
		
		//服务器ID
		private var _createServer: int;
		
		//类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色ID
			writeLong(_playerId);
			//用户ID
			writeString(_userId);
			//服务器ID
			writeInt(_createServer);
			//类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色ID
			_playerId = readLong();
			//用户ID
			_userId = readString();
			//服务器ID
			_createServer = readInt();
			//类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100109;
		}
		
		/**
		 * get 角色ID
		 * @return 
		 */
		public function get playerId(): long{
			return _playerId;
		}
		
		/**
		 * set 角色ID
		 */
		public function set playerId(value: long): void{
			this._playerId = value;
		}
		
		/**
		 * get 用户ID
		 * @return 
		 */
		public function get userId(): String{
			return _userId;
		}
		
		/**
		 * set 用户ID
		 */
		public function set userId(value: String): void{
			this._userId = value;
		}
		
		/**
		 * get 服务器ID
		 * @return 
		 */
		public function get createServer(): int{
			return _createServer;
		}
		
		/**
		 * set 服务器ID
		 */
		public function set createServer(value: int): void{
			this._createServer = value;
		}
		
		/**
		 * get 类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}