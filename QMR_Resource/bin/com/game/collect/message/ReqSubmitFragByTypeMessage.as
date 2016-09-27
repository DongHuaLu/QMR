package com.game.collect.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 提交碎片根据系列
	 */
	public class ReqSubmitFragByTypeMessage extends Message {
	
		//系列
		private var _collecttype: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//系列
			writeInt(_collecttype);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//系列
			_collecttype = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 153201;
		}
		
		/**
		 * get 系列
		 * @return 
		 */
		public function get collecttype(): int{
			return _collecttype;
		}
		
		/**
		 * set 系列
		 */
		public function set collecttype(value: int): void{
			this._collecttype = value;
		}
		
	}
}