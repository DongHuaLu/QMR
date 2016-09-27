package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 协议离婚选择
	 */
	public class ReqDivorceSelectToGameMessage extends Message {
	
		//对协议离婚做选择，1同意，0拒绝
		private var _select: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//对协议离婚做选择，1同意，0拒绝
			writeByte(_select);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//对协议离婚做选择，1同意，0拒绝
			_select = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163208;
		}
		
		/**
		 * get 对协议离婚做选择，1同意，0拒绝
		 * @return 
		 */
		public function get select(): int{
			return _select;
		}
		
		/**
		 * set 对协议离婚做选择，1同意，0拒绝
		 */
		public function set select(value: int): void{
			this._select = value;
		}
		
	}
}