package com.game.spirittree.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 显示行会灵树上一棵和下一棵果实成熟时间信息
	 */
	public class ResShowGuildMatureClientMessage extends Message {
	
		//上一个果实成熟的玩家名字
		private var _lastname: String;
		
		//上一个果实成熟时间（当前时间-成熟时间）
		private var _lasttime: int;
		
		//下一个果实成熟的玩家名字
		private var _nextname: String;
		
		//下一个果实成熟时间（当前时间-成熟时间）
		private var _nexttime: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//上一个果实成熟的玩家名字
			writeString(_lastname);
			//上一个果实成熟时间（当前时间-成熟时间）
			writeInt(_lasttime);
			//下一个果实成熟的玩家名字
			writeString(_nextname);
			//下一个果实成熟时间（当前时间-成熟时间）
			writeInt(_nexttime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//上一个果实成熟的玩家名字
			_lastname = readString();
			//上一个果实成熟时间（当前时间-成熟时间）
			_lasttime = readInt();
			//下一个果实成熟的玩家名字
			_nextname = readString();
			//下一个果实成熟时间（当前时间-成熟时间）
			_nexttime = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133105;
		}
		
		/**
		 * get 上一个果实成熟的玩家名字
		 * @return 
		 */
		public function get lastname(): String{
			return _lastname;
		}
		
		/**
		 * set 上一个果实成熟的玩家名字
		 */
		public function set lastname(value: String): void{
			this._lastname = value;
		}
		
		/**
		 * get 上一个果实成熟时间（当前时间-成熟时间）
		 * @return 
		 */
		public function get lasttime(): int{
			return _lasttime;
		}
		
		/**
		 * set 上一个果实成熟时间（当前时间-成熟时间）
		 */
		public function set lasttime(value: int): void{
			this._lasttime = value;
		}
		
		/**
		 * get 下一个果实成熟的玩家名字
		 * @return 
		 */
		public function get nextname(): String{
			return _nextname;
		}
		
		/**
		 * set 下一个果实成熟的玩家名字
		 */
		public function set nextname(value: String): void{
			this._nextname = value;
		}
		
		/**
		 * get 下一个果实成熟时间（当前时间-成熟时间）
		 * @return 
		 */
		public function get nexttime(): int{
			return _nexttime;
		}
		
		/**
		 * set 下一个果实成熟时间（当前时间-成熟时间）
		 */
		public function set nexttime(value: int): void{
			this._nexttime = value;
		}
		
	}
}