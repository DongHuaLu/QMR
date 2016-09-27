package com.game.toplist.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送给客户端称号列表
	 */
	public class ResGetTopTitleToClientMessage extends Message {
	
		//错误代码
		private var _errorcode: int;
		
		//称号id列表
		private var _titleidlist: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//错误代码
			writeByte(_errorcode);
			//称号id列表
			writeShort(_titleidlist.length);
			for (i = 0; i < _titleidlist.length; i++) {
				writeInt(_titleidlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//错误代码
			_errorcode = readByte();
			//称号id列表
			var titleidlist_length : int = readShort();
			for (i = 0; i < titleidlist_length; i++) {
				_titleidlist[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142150;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get errorcode(): int{
			return _errorcode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set errorcode(value: int): void{
			this._errorcode = value;
		}
		
		/**
		 * get 称号id列表
		 * @return 
		 */
		public function get titleidlist(): Vector.<int>{
			return _titleidlist;
		}
		
		/**
		 * set 称号id列表
		 */
		public function set titleidlist(value: Vector.<int>): void{
			this._titleidlist = value;
		}
		
	}
}