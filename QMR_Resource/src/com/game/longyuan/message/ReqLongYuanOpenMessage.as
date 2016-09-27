package com.game.longyuan.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开面板开始查询
	 */
	public class ReqLongYuanOpenMessage extends Message {
	
		
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
			return 115201;
		}
		
	}
}