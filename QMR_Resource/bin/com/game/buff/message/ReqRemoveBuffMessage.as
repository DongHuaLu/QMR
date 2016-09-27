package com.game.buff.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 移除Buff请求
	 */
	public class ReqRemoveBuffMessage extends Message {
	
		//buff Id
		private var _buffId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//buff Id
			writeLong(_buffId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//buff Id
			_buffId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 113201;
		}
		
		/**
		 * get buff Id
		 * @return 
		 */
		public function get buffId(): long{
			return _buffId;
		}
		
		/**
		 * set buff Id
		 */
		public function set buffId(value: long): void{
			this._buffId = value;
		}
		
	}
}