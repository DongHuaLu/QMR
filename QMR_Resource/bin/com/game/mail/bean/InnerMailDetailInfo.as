package com.game.mail.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 内部邮件具体信息
	 */
	public class InnerMailDetailInfo extends Bean {
	
		//邮件Id
		private var _mailid: long;
		
		//发送者Id
		private var _senderid: long;
		
		//接受者Id
		private var _receiverid: long;
		
		//发件人名字
		private var _szSenderName: String;
		
		//接收人名字
		private var _szReceiverName: String;
		
		//邮件标题
		private var _szTitle: String;
		
		//邮件内容
		private var _szNotice: String;
		
		//是否已读取
		private var _btRead: int;
		
		//金币类型
		private var _btGoldType: int;
		
		//金币数量
		private var _nGold: int;
		
		//是否有附件
		private var _btAccessory: int;
		
		//发送时间
		private var _nSendTime: int;
		
		//是否系统邮件
		private var _btSystem: int;
		
		//是否是回信
		private var _btReturn: int;
		
		//邮件物品列表(json字符串)
		private var _itemdata: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//邮件Id
			writeLong(_mailid);
			//发送者Id
			writeLong(_senderid);
			//接受者Id
			writeLong(_receiverid);
			//发件人名字
			writeString(_szSenderName);
			//接收人名字
			writeString(_szReceiverName);
			//邮件标题
			writeString(_szTitle);
			//邮件内容
			writeString(_szNotice);
			//是否已读取
			writeByte(_btRead);
			//金币类型
			writeByte(_btGoldType);
			//金币数量
			writeInt(_nGold);
			//是否有附件
			writeByte(_btAccessory);
			//发送时间
			writeInt(_nSendTime);
			//是否系统邮件
			writeByte(_btSystem);
			//是否是回信
			writeByte(_btReturn);
			//邮件物品列表(json字符串)
			writeString(_itemdata);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//邮件Id
			_mailid = readLong();
			//发送者Id
			_senderid = readLong();
			//接受者Id
			_receiverid = readLong();
			//发件人名字
			_szSenderName = readString();
			//接收人名字
			_szReceiverName = readString();
			//邮件标题
			_szTitle = readString();
			//邮件内容
			_szNotice = readString();
			//是否已读取
			_btRead = readByte();
			//金币类型
			_btGoldType = readByte();
			//金币数量
			_nGold = readInt();
			//是否有附件
			_btAccessory = readByte();
			//发送时间
			_nSendTime = readInt();
			//是否系统邮件
			_btSystem = readByte();
			//是否是回信
			_btReturn = readByte();
			//邮件物品列表(json字符串)
			_itemdata = readString();
			return true;
		}
		
		/**
		 * get 邮件Id
		 * @return 
		 */
		public function get mailid(): long{
			return _mailid;
		}
		
		/**
		 * set 邮件Id
		 */
		public function set mailid(value: long): void{
			this._mailid = value;
		}
		
		/**
		 * get 发送者Id
		 * @return 
		 */
		public function get senderid(): long{
			return _senderid;
		}
		
		/**
		 * set 发送者Id
		 */
		public function set senderid(value: long): void{
			this._senderid = value;
		}
		
		/**
		 * get 接受者Id
		 * @return 
		 */
		public function get receiverid(): long{
			return _receiverid;
		}
		
		/**
		 * set 接受者Id
		 */
		public function set receiverid(value: long): void{
			this._receiverid = value;
		}
		
		/**
		 * get 发件人名字
		 * @return 
		 */
		public function get szSenderName(): String{
			return _szSenderName;
		}
		
		/**
		 * set 发件人名字
		 */
		public function set szSenderName(value: String): void{
			this._szSenderName = value;
		}
		
		/**
		 * get 接收人名字
		 * @return 
		 */
		public function get szReceiverName(): String{
			return _szReceiverName;
		}
		
		/**
		 * set 接收人名字
		 */
		public function set szReceiverName(value: String): void{
			this._szReceiverName = value;
		}
		
		/**
		 * get 邮件标题
		 * @return 
		 */
		public function get szTitle(): String{
			return _szTitle;
		}
		
		/**
		 * set 邮件标题
		 */
		public function set szTitle(value: String): void{
			this._szTitle = value;
		}
		
		/**
		 * get 邮件内容
		 * @return 
		 */
		public function get szNotice(): String{
			return _szNotice;
		}
		
		/**
		 * set 邮件内容
		 */
		public function set szNotice(value: String): void{
			this._szNotice = value;
		}
		
		/**
		 * get 是否已读取
		 * @return 
		 */
		public function get btRead(): int{
			return _btRead;
		}
		
		/**
		 * set 是否已读取
		 */
		public function set btRead(value: int): void{
			this._btRead = value;
		}
		
		/**
		 * get 金币类型
		 * @return 
		 */
		public function get btGoldType(): int{
			return _btGoldType;
		}
		
		/**
		 * set 金币类型
		 */
		public function set btGoldType(value: int): void{
			this._btGoldType = value;
		}
		
		/**
		 * get 金币数量
		 * @return 
		 */
		public function get nGold(): int{
			return _nGold;
		}
		
		/**
		 * set 金币数量
		 */
		public function set nGold(value: int): void{
			this._nGold = value;
		}
		
		/**
		 * get 是否有附件
		 * @return 
		 */
		public function get btAccessory(): int{
			return _btAccessory;
		}
		
		/**
		 * set 是否有附件
		 */
		public function set btAccessory(value: int): void{
			this._btAccessory = value;
		}
		
		/**
		 * get 发送时间
		 * @return 
		 */
		public function get nSendTime(): int{
			return _nSendTime;
		}
		
		/**
		 * set 发送时间
		 */
		public function set nSendTime(value: int): void{
			this._nSendTime = value;
		}
		
		/**
		 * get 是否系统邮件
		 * @return 
		 */
		public function get btSystem(): int{
			return _btSystem;
		}
		
		/**
		 * set 是否系统邮件
		 */
		public function set btSystem(value: int): void{
			this._btSystem = value;
		}
		
		/**
		 * get 是否是回信
		 * @return 
		 */
		public function get btReturn(): int{
			return _btReturn;
		}
		
		/**
		 * set 是否是回信
		 */
		public function set btReturn(value: int): void{
			this._btReturn = value;
		}
		
		/**
		 * get 邮件物品列表(json字符串)
		 * @return 
		 */
		public function get itemdata(): String{
			return _itemdata;
		}
		
		/**
		 * set 邮件物品列表(json字符串)
		 */
		public function set itemdata(value: String): void{
			this._itemdata = value;
		}
		
	}
}