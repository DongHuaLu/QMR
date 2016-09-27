package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 删除留言
	 */
	public class ReqDeleteLeaveMsgToGameMessage extends Message {
	
		//当前留言索引ID
		private var _msgid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前留言索引ID
			writeLong(_msgid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前留言索引ID
			_msgid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163219;
		}
		
		/**
		 * get 当前留言索引ID
		 * @return 
		 */
		public function get msgid(): long{
			return _msgid;
		}
		
		/**
		 * set 当前留言索引ID
		 */
		public function set msgid(value: long): void{
			this._msgid = value;
		}
		
	}
}