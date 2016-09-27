package com.game.server.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 服务器关闭消息
	 */
	public class ResCloseServerMessage extends Message {
	
		//关服剩余时间
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//关服剩余时间
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//关服剩余时间
			_time = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 300101;
		}
		
		/**
		 * get 关服剩余时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 关服剩余时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}