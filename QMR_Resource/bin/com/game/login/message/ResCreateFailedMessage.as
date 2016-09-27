package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 创建角色失败
	 */
	public class ResCreateFailedMessage extends Message {
	
		//失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
		private var _reason: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
			writeByte(_reason);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
			_reason = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100104;
		}
		
		/**
		 * get 失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
		 * @return 
		 */
		public function get reason(): int{
			return _reason;
		}
		
		/**
		 * set 失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
		 */
		public function set reason(value: int): void{
			this._reason = value;
		}
		
	}
}