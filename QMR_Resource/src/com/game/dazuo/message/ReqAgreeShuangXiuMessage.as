package com.game.dazuo.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 同意其他玩家的双修申请
	 */
	public class ReqAgreeShuangXiuMessage extends Message {
	
		//他人ID
		private var _otherRoleId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//他人ID
			writeLong(_otherRoleId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//他人ID
			_otherRoleId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 136203;
		}
		
		/**
		 * get 他人ID
		 * @return 
		 */
		public function get otherRoleId(): long{
			return _otherRoleId;
		}
		
		/**
		 * set 他人ID
		 */
		public function set otherRoleId(value: long): void{
			this._otherRoleId = value;
		}
		
	}
}