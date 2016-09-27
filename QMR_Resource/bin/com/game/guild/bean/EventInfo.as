package com.game.guild.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 事件信息
	 */
	public class EventInfo extends Bean {
	
		//事件id
		private var _eventId: long;
		
		//帮会id
		private var _guildId: long;
		
		//帮会名
		private var _guildName: String;
		
		//消息时间
		private var _messageTime: int;
		
		//消息类型
		private var _messageType: String;
		
		//消息内容
		private var _message: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//事件id
			writeLong(_eventId);
			//帮会id
			writeLong(_guildId);
			//帮会名
			writeString(_guildName);
			//消息时间
			writeInt(_messageTime);
			//消息类型
			writeString(_messageType);
			//消息内容
			writeString(_message);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//事件id
			_eventId = readLong();
			//帮会id
			_guildId = readLong();
			//帮会名
			_guildName = readString();
			//消息时间
			_messageTime = readInt();
			//消息类型
			_messageType = readString();
			//消息内容
			_message = readString();
			return true;
		}
		
		/**
		 * get 事件id
		 * @return 
		 */
		public function get eventId(): long{
			return _eventId;
		}
		
		/**
		 * set 事件id
		 */
		public function set eventId(value: long): void{
			this._eventId = value;
		}
		
		/**
		 * get 帮会id
		 * @return 
		 */
		public function get guildId(): long{
			return _guildId;
		}
		
		/**
		 * set 帮会id
		 */
		public function set guildId(value: long): void{
			this._guildId = value;
		}
		
		/**
		 * get 帮会名
		 * @return 
		 */
		public function get guildName(): String{
			return _guildName;
		}
		
		/**
		 * set 帮会名
		 */
		public function set guildName(value: String): void{
			this._guildName = value;
		}
		
		/**
		 * get 消息时间
		 * @return 
		 */
		public function get messageTime(): int{
			return _messageTime;
		}
		
		/**
		 * set 消息时间
		 */
		public function set messageTime(value: int): void{
			this._messageTime = value;
		}
		
		/**
		 * get 消息类型
		 * @return 
		 */
		public function get messageType(): String{
			return _messageType;
		}
		
		/**
		 * set 消息类型
		 */
		public function set messageType(value: String): void{
			this._messageType = value;
		}
		
		/**
		 * get 消息内容
		 * @return 
		 */
		public function get message(): String{
			return _message;
		}
		
		/**
		 * set 消息内容
		 */
		public function set message(value: String): void{
			this._message = value;
		}
		
	}
}