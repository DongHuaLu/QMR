package com.game.toplist.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送给客户端膜拜玩家
	 */
	public class ResWorShipToClientMessage extends Message {
	
		//错误代码
		private var _errorcode: int;
		
		//自己今天膜拜次数
		private var _worshipnum: int;
		
		//膜拜玩家id
		private var _worshipplayerid: long;
		
		//总膜拜次数
		private var _allworshipnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_errorcode);
			//自己今天膜拜次数
			writeByte(_worshipnum);
			//膜拜玩家id
			writeLong(_worshipplayerid);
			//总膜拜次数
			writeInt(_allworshipnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_errorcode = readByte();
			//自己今天膜拜次数
			_worshipnum = readByte();
			//膜拜玩家id
			_worshipplayerid = readLong();
			//总膜拜次数
			_allworshipnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142102;
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
		 * get 自己今天膜拜次数
		 * @return 
		 */
		public function get worshipnum(): int{
			return _worshipnum;
		}
		
		/**
		 * set 自己今天膜拜次数
		 */
		public function set worshipnum(value: int): void{
			this._worshipnum = value;
		}
		
		/**
		 * get 膜拜玩家id
		 * @return 
		 */
		public function get worshipplayerid(): long{
			return _worshipplayerid;
		}
		
		/**
		 * set 膜拜玩家id
		 */
		public function set worshipplayerid(value: long): void{
			this._worshipplayerid = value;
		}
		
		/**
		 * get 总膜拜次数
		 * @return 
		 */
		public function get allworshipnum(): int{
			return _allworshipnum;
		}
		
		/**
		 * set 总膜拜次数
		 */
		public function set allworshipnum(value: int): void{
			this._allworshipnum = value;
		}
		
	}
}