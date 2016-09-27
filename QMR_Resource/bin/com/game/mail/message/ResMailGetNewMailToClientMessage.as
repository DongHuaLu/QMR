package com.game.mail.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获得新邮件提示
	 */
	public class ResMailGetNewMailToClientMessage extends Message {
	
		//新邮件数量
		private var _nCount: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//新邮件数量
			writeInt(_nCount);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//新邮件数量
			_nCount = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124110;
		}
		
		/**
		 * get 新邮件数量
		 * @return 
		 */
		public function get nCount(): int{
			return _nCount;
		}
		
		/**
		 * set 新邮件数量
		 */
		public function set nCount(value: int): void{
			this._nCount = value;
		}
		
	}
}