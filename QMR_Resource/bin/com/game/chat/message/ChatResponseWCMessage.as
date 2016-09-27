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
	public class ChatResponseWCMessage extends Message {
	
		//消息类型
		private var _chattype: int;
		
		//发送人id
		private var _chater: long;
		
		//发送人名字
		private var _chatername: String;
		
		//发送人等级
		private var _chaterlevel: int;
		
		//发送人王城等级
		private var _chaterkinglv: int;
		
		//发送人皇城等级
		private var _chaterempirelv: int;
		
		//发送人国家id
		private var _country: int;
		
		//发送人VIP类型
		private var _viptype: int;
		
		//接收人id
		private var _receiver: long;
		
		//接收人等级
		private var _receiverlevel: int;
		
		//接收人名字
		private var _receivername: String;
		
		//接收人VIP类型
		private var _receiverviptype: int;
		
		//聊天消息
		private var _condition: String;
		
		//附加属性
		private var _other: Vector.<GoodsInfoRes> = new Vector.<GoodsInfoRes>();
		//是否GM1是 0否
		private var _isgm: int;
		
		//发送人WEBVIP类型
		private var _webvip: int;
		
		//接收人WEBVIP类型
		private var _receiverwebvip: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消息类型
			writeInt(_chattype);
			//发送人id
			writeLong(_chater);
			//发送人名字
			writeString(_chatername);
			//发送人等级
			writeInt(_chaterlevel);
			//发送人王城等级
			writeInt(_chaterkinglv);
			//发送人皇城等级
			writeInt(_chaterempirelv);
			//发送人国家id
			writeInt(_country);
			//发送人VIP类型
			writeShort(_viptype);
			//接收人id
			writeLong(_receiver);
			//接收人等级
			writeInt(_receiverlevel);
			//接收人名字
			writeString(_receivername);
			//接收人VIP类型
			writeShort(_receiverviptype);
			//聊天消息
			writeString(_condition);
			//附加属性
			writeShort(_other.length);
			for (i = 0; i < _other.length; i++) {
				writeBean(_other[i]);
			}
			//是否GM1是 0否
			writeByte(_isgm);
			//发送人WEBVIP类型
			writeInt(_webvip);
			//接收人WEBVIP类型
			writeInt(_receiverwebvip);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//消息类型
			_chattype = readInt();
			//发送人id
			_chater = readLong();
			//发送人名字
			_chatername = readString();
			//发送人等级
			_chaterlevel = readInt();
			//发送人王城等级
			_chaterkinglv = readInt();
			//发送人皇城等级
			_chaterempirelv = readInt();
			//发送人国家id
			_country = readInt();
			//发送人VIP类型
			_viptype = readShort();
			//接收人id
			_receiver = readLong();
			//接收人等级
			_receiverlevel = readInt();
			//接收人名字
			_receivername = readString();
			//接收人VIP类型
			_receiverviptype = readShort();
			//聊天消息
			_condition = readString();
			//附加属性
			var other_length : int = readShort();
			for (i = 0; i < other_length; i++) {
				_other[i] = readBean(GoodsInfoRes) as GoodsInfoRes;
			}
			//是否GM1是 0否
			_isgm = readByte();
			//发送人WEBVIP类型
			_webvip = readInt();
			//接收人WEBVIP类型
			_receiverwebvip = readInt();
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
		 * get 发送人等级
		 * @return 
		 */
		public function get chaterlevel(): int{
			return _chaterlevel;
		}
		
		/**
		 * set 发送人等级
		 */
		public function set chaterlevel(value: int): void{
			this._chaterlevel = value;
		}
		
		/**
		 * get 发送人王城等级
		 * @return 
		 */
		public function get chaterkinglv(): int{
			return _chaterkinglv;
		}
		
		/**
		 * set 发送人王城等级
		 */
		public function set chaterkinglv(value: int): void{
			this._chaterkinglv = value;
		}
		
		/**
		 * get 发送人皇城等级
		 * @return 
		 */
		public function get chaterempirelv(): int{
			return _chaterempirelv;
		}
		
		/**
		 * set 发送人皇城等级
		 */
		public function set chaterempirelv(value: int): void{
			this._chaterempirelv = value;
		}
		
		/**
		 * get 发送人国家id
		 * @return 
		 */
		public function get country(): int{
			return _country;
		}
		
		/**
		 * set 发送人国家id
		 */
		public function set country(value: int): void{
			this._country = value;
		}
		
		/**
		 * get 发送人VIP类型
		 * @return 
		 */
		public function get viptype(): int{
			return _viptype;
		}
		
		/**
		 * set 发送人VIP类型
		 */
		public function set viptype(value: int): void{
			this._viptype = value;
		}
		
		/**
		 * get 接收人id
		 * @return 
		 */
		public function get receiver(): long{
			return _receiver;
		}
		
		/**
		 * set 接收人id
		 */
		public function set receiver(value: long): void{
			this._receiver = value;
		}
		
		/**
		 * get 接收人等级
		 * @return 
		 */
		public function get receiverlevel(): int{
			return _receiverlevel;
		}
		
		/**
		 * set 接收人等级
		 */
		public function set receiverlevel(value: int): void{
			this._receiverlevel = value;
		}
		
		/**
		 * get 接收人名字
		 * @return 
		 */
		public function get receivername(): String{
			return _receivername;
		}
		
		/**
		 * set 接收人名字
		 */
		public function set receivername(value: String): void{
			this._receivername = value;
		}
		
		/**
		 * get 接收人VIP类型
		 * @return 
		 */
		public function get receiverviptype(): int{
			return _receiverviptype;
		}
		
		/**
		 * set 接收人VIP类型
		 */
		public function set receiverviptype(value: int): void{
			this._receiverviptype = value;
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
		
		/**
		 * get 是否GM1是 0否
		 * @return 
		 */
		public function get isgm(): int{
			return _isgm;
		}
		
		/**
		 * set 是否GM1是 0否
		 */
		public function set isgm(value: int): void{
			this._isgm = value;
		}
		
		/**
		 * get 发送人WEBVIP类型
		 * @return 
		 */
		public function get webvip(): int{
			return _webvip;
		}
		
		/**
		 * set 发送人WEBVIP类型
		 */
		public function set webvip(value: int): void{
			this._webvip = value;
		}
		
		/**
		 * get 接收人WEBVIP类型
		 * @return 
		 */
		public function get receiverwebvip(): int{
			return _receiverwebvip;
		}
		
		/**
		 * set 接收人WEBVIP类型
		 */
		public function set receiverwebvip(value: int): void{
			this._receiverwebvip = value;
		}
		
	}
}