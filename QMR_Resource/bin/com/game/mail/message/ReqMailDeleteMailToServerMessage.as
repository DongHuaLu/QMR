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
	 * 删除邮件,删完会返回 ResMailQueryListToClient
	 */
	public class ReqMailDeleteMailToServerMessage extends Message {
	
		//1 全删除 全删的话后面不需要添写
		private var _btdeleteall: int;
		
		//或者 最大支持删除10封信 填写邮件ID
		private var _deleteMailIdList: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//1 全删除 全删的话后面不需要添写
			writeByte(_btdeleteall);
			//或者 最大支持删除10封信 填写邮件ID
			writeShort(_deleteMailIdList.length);
			for (i = 0; i < _deleteMailIdList.length; i++) {
				writeLong(_deleteMailIdList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//1 全删除 全删的话后面不需要添写
			_btdeleteall = readByte();
			//或者 最大支持删除10封信 填写邮件ID
			var deleteMailIdList_length : int = readShort();
			for (i = 0; i < deleteMailIdList_length; i++) {
				_deleteMailIdList[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124155;
		}
		
		/**
		 * get 1 全删除 全删的话后面不需要添写
		 * @return 
		 */
		public function get btdeleteall(): int{
			return _btdeleteall;
		}
		
		/**
		 * set 1 全删除 全删的话后面不需要添写
		 */
		public function set btdeleteall(value: int): void{
			this._btdeleteall = value;
		}
		
		/**
		 * get 或者 最大支持删除10封信 填写邮件ID
		 * @return 
		 */
		public function get deleteMailIdList(): Vector.<long>{
			return _deleteMailIdList;
		}
		
		/**
		 * set 或者 最大支持删除10封信 填写邮件ID
		 */
		public function set deleteMailIdList(value: Vector.<long>): void{
			this._deleteMailIdList = value;
		}
		
	}
}