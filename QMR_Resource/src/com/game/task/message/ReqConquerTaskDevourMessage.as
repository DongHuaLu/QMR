package com.game.task.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 讨伐任务吞噬请求
	 */
	public class ReqConquerTaskDevourMessage extends Message {
	
		//选择的任务ID
		private var _devourId: long;
		
		//是否保留100%比例
		private var _isfull: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//选择的任务ID
			writeLong(_devourId);
			//是否保留100%比例
			writeByte(_isfull);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//选择的任务ID
			_devourId = readLong();
			//是否保留100%比例
			_isfull = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120208;
		}
		
		/**
		 * get 选择的任务ID
		 * @return 
		 */
		public function get devourId(): long{
			return _devourId;
		}
		
		/**
		 * set 选择的任务ID
		 */
		public function set devourId(value: long): void{
			this._devourId = value;
		}
		
		/**
		 * get 是否保留100%比例
		 * @return 
		 */
		public function get isfull(): int{
			return _isfull;
		}
		
		/**
		 * set 是否保留100%比例
		 */
		public function set isfull(value: int): void{
			this._isfull = value;
		}
		
	}
}