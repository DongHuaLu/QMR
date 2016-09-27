package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 结婚成功消息
	 */
	public class ResMarriedSuccessToClientMessage extends Message {
	
		//对方名字
		private var _playername: String;
		
		//对方头像
		private var _avatarid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//对方名字
			writeString(_playername);
			//对方头像
			writeInt(_avatarid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//对方名字
			_playername = readString();
			//对方头像
			_avatarid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163109;
		}
		
		/**
		 * get 对方名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 对方名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 对方头像
		 * @return 
		 */
		public function get avatarid(): int{
			return _avatarid;
		}
		
		/**
		 * set 对方头像
		 */
		public function set avatarid(value: int): void{
			this._avatarid = value;
		}
		
	}
}