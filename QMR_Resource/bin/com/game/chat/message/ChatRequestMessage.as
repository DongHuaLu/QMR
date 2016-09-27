package com.game.chat.message{
	import com.game.chat.bean.GoodsInfoReq;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 聊天请求
	 */
	public class ChatRequestMessage extends Message {
	
		//消息类型
		private var _chattype: int;
		
		//私聊时需要填写角色名
		private var _roleName: String;
		
		//聊天消息
		private var _condition: String;
		
		//附加属性请求
		private var _other: Vector.<GoodsInfoReq> = new Vector.<GoodsInfoReq>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消息类型
			writeInt(_chattype);
			//私聊时需要填写角色名
			writeString(_roleName);
			//聊天消息
			writeString(_condition);
			//附加属性请求
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
			//消息类型
			_chattype = readInt();
			//私聊时需要填写角色名
			_roleName = readString();
			//聊天消息
			_condition = readString();
			//附加属性请求
			var other_length : int = readShort();
			for (i = 0; i < other_length; i++) {
				_other[i] = readBean(GoodsInfoReq) as GoodsInfoReq;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 111201;
		}
		
		/**
		 * get 消息类型
		 * @return 
		 */
		public function get chattype(): int{
			return _chattype;
		}
		
		/**
		 * set 消息类型
		 */
		public function set chattype(value: int): void{
			this._chattype = value;
		}
		
		/**
		 * get 私聊时需要填写角色名
		 * @return 
		 */
		public function get roleName(): String{
			return _roleName;
		}
		
		/**
		 * set 私聊时需要填写角色名
		 */
		public function set roleName(value: String): void{
			this._roleName = value;
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
		 * get 附加属性请求
		 * @return 
		 */
		public function get other(): Vector.<GoodsInfoReq>{
			return _other;
		}
		
		/**
		 * set 附加属性请求
		 */
		public function set other(value: Vector.<GoodsInfoReq>): void{
			this._other = value;
		}
		
	}
}