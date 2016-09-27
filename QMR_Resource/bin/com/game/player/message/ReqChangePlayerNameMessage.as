package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家发起改名
	 */
	public class ReqChangePlayerNameMessage extends Message {
	
		//新名字
		private var _newname: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//新名字
			writeString(_newname);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//新名字
			_newname = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103212;
		}
		
		/**
		 * get 新名字
		 * @return 
		 */
		public function get newname(): String{
			return _newname;
		}
		
		/**
		 * set 新名字
		 */
		public function set newname(value: String): void{
			this._newname = value;
		}
		
	}
}