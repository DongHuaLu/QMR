package com.game.chestbox.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 客户端请求开始转动宝箱
	 */
	public class ReqChestBoxOpenToServerMessage extends Message {
	
		//动作类型
		private var _actiontype: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//动作类型
			writeInt(_actiontype);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//动作类型
			_actiontype = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 156202;
		}
		
		/**
		 * get 动作类型
		 * @return 
		 */
		public function get actiontype(): int{
			return _actiontype;
		}
		
		/**
		 * set 动作类型
		 */
		public function set actiontype(value: int): void{
			this._actiontype = value;
		}
		
	}
}