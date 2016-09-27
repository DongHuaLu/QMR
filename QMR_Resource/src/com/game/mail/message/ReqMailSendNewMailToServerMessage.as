package com.game.mail.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送新邮件
	 */
	public class ReqMailSendNewMailToServerMessage extends Message {
	
		//接收人名字
		private var _szReceiverName: String;
		
		//邮件标题
		private var _szTitle: String;
		
		//邮件内容
		private var _szNotice: String;
		
		//金币类型
		private var _btGoldType: int;
		
		//金币数量
		private var _nGold: int;
		
		//邮件附件物品id列表
		private var _itemidlist: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//接收人名字
			writeString(_szReceiverName);
			//邮件标题
			writeString(_szTitle);
			//邮件内容
			writeString(_szNotice);
			//金币类型
			writeByte(_btGoldType);
			//金币数量
			writeInt(_nGold);
			//邮件附件物品id列表
			writeShort(_itemidlist.length);
			for (i = 0; i < _itemidlist.length; i++) {
				writeLong(_itemidlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//接收人名字
			_szReceiverName = readString();
			//邮件标题
			_szTitle = readString();
			//邮件内容
			_szNotice = readString();
			//金币类型
			_btGoldType = readByte();
			//金币数量
			_nGold = readInt();
			//邮件附件物品id列表
			var itemidlist_length : int = readShort();
			for (i = 0; i < itemidlist_length; i++) {
				_itemidlist[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124154;
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
		 * get 邮件附件物品id列表
		 * @return 
		 */
		public function get itemidlist(): Vector.<long>{
			return _itemidlist;
		}
		
		/**
		 * set 邮件附件物品id列表
		 */
		public function set itemidlist(value: Vector.<long>): void{
			this._itemidlist = value;
		}
		
	}
}