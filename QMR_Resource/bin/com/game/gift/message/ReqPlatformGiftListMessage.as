package com.game.gift.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求平台礼包列表
	 */
	public class ReqPlatformGiftListMessage extends Message {
	
		//平台名称
		private var _platform: String;
		
		//面板标识
		private var _tag: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//平台名称
			writeString(_platform);
			//面板标识
			writeString(_tag);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//平台名称
			_platform = readString();
			//面板标识
			_tag = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 129204;
		}
		
		/**
		 * get 平台名称
		 * @return 
		 */
		public function get platform(): String{
			return _platform;
		}
		
		/**
		 * set 平台名称
		 */
		public function set platform(value: String): void{
			this._platform = value;
		}
		
		/**
		 * get 面板标识
		 * @return 
		 */
		public function get tag(): String{
			return _tag;
		}
		
		/**
		 * set 面板标识
		 */
		public function set tag(value: String): void{
			this._tag = value;
		}
		
	}
}