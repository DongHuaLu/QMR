package com.game.toplist.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 改变称号
	 */
	public class ReqChangeTitleToServerMessage extends Message {
	
		//称号id
		private var _titleid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//称号id
			writeInt(_titleid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//称号id
			_titleid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142203;
		}
		
		/**
		 * get 称号id
		 * @return 
		 */
		public function get titleid(): int{
			return _titleid;
		}
		
		/**
		 * set 称号id
		 */
		public function set titleid(value: int): void{
			this._titleid = value;
		}
		
	}
}