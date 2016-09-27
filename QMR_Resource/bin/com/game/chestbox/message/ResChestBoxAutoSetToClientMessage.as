package com.game.chestbox.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宝箱盒子自动开始设置发送到客户端
	 */
	public class ResChestBoxAutoSetToClientMessage extends Message {
	
		//自动开始设置
		private var _autoset: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//自动开始设置
			writeInt(_autoset);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//自动开始设置
			_autoset = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 156104;
		}
		
		/**
		 * get 自动开始设置
		 * @return 
		 */
		public function get autoset(): int{
			return _autoset;
		}
		
		/**
		 * set 自动开始设置
		 */
		public function set autoset(value: int): void{
			this._autoset = value;
		}
		
	}
}