package com.game.chat.message{
	import com.game.utils.long;
	import com.game.chat.bean.GoodsInfoRes;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 聊天消息
	 */
	public class AcrossChatResponseMessage extends Message {
	
		//消息类型 0 世界 1普通 2私聊 3队伍 4帮派 5喇叭 
		private var _chattype: int;
		
		//发送人id
		private var _chater: long;
		
		//发送人名字
		private var _chatername: String;
		
		//聊天消息
		private var _condition: String;
		
		//附加属性
		private var _other: Vector.<GoodsInfoRes> = new Vector.<GoodsInfoRes>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消息类型 0 世界 1普通 2私聊 3队伍 4帮派 5喇叭 
			writeInt(_chattype);
			//发送人id
			writeLong(_chater);
			//发送人名字
			writeString(_chatername);
			//聊天消息
			writeString(_condition);
			//附加属性
			writeShort(_other.length);
			for (i = 0; i < _other.length; i++) {
				writeBean(_other[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//消息类型 0 世界 1普通 2私聊 3队伍 4帮派 5喇叭 
			_chattype = readInt();
			//发送人id
			_chater = readLong();
			//发送人名字
			_chatername = readString();
			//聊天消息
			_condition = readString();
			//附加属性
			var other_length : int = readShort();
			for (i = 0; i < other_length; i++) {
				_other[i] = readBean(GoodsInfoRes) as GoodsInfoRes;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 111103;
		}
		
		/**
		 * get 消息类型 0 世界 1普通 2私聊 3队伍 4帮派 5喇叭 
		 * @return 
		 */
		public function get chattype(): int{
			return _chattype;
		}
		
		/**
		 * set 消息类型 0 世界 1普通 2私聊 3队伍 4帮派 5喇叭 
		 */
		public function set chattype(value: int): void{
			this._chattype = value;
		}
		
		/**
		 * get 发送人id
		 * @return 
		 */
		public function get chater(): long{
			return _chater;
		}
		
		/**
		 * set 发送人id
		 */
		public function set chater(value: long): void{
			this._chater = value;
		}
		
		/**
		 * get 发送人名字
		 * @return 
		 */
		public function get chatername(): String{
			return _chatername;
		}
		
		/**
		 * set 发送人名字
		 */
		public function set chatername(value: String): void{
			this._chatername = value;
		}
		
		/**
		 * get 聊天消息
		 * @return 
		 */
		public function get condition(): String{
			return _condition;
		}
		
		/**
		 * set 聊天消息
		 */
		public function set condition(value: String): void{
			this._condition = value;
		}
		
		/**
		 * get 附加属性
		 * @return 
		 */
		public function get other(): Vector.<GoodsInfoRes>{
			return _other;
		}
		
		/**
		 * set 附加属性
		 */
		public function set other(value: Vector.<GoodsInfoRes>): void{
			this._other = value;
		}
		
	}
}