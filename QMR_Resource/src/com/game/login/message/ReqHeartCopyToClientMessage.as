package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 心跳消息
	 */
	public class ReqHeartCopyToClientMessage extends Message {
	
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100213;
		}
		
	}
}