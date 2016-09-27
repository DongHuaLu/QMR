package com.game.setupmenu.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 系统设置设定状态
	 */
	public class ReqSetMenuStatusMessage extends Message {
	
		//客户端设定值
		private var _clientset: int;
		
		//服务端设定值
		private var _menustatus: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//客户端设定值
			writeInt(_clientset);
			//服务端设定值
			writeInt(_menustatus);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//客户端设定值
			_clientset = readInt();
			//服务端设定值
			_menustatus = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 125202;
		}
		
		/**
		 * get 客户端设定值
		 * @return 
		 */
		public function get clientset(): int{
			return _clientset;
		}
		
		/**
		 * set 客户端设定值
		 */
		public function set clientset(value: int): void{
			this._clientset = value;
		}
		
		/**
		 * get 服务端设定值
		 * @return 
		 */
		public function get menustatus(): int{
			return _menustatus;
		}
		
		/**
		 * set 服务端设定值
		 */
		public function set menustatus(value: int): void{
			this._menustatus = value;
		}
		
	}
}