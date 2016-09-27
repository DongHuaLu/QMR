package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 服务器发起脚本通用类消息
	 */
	public class ResScriptCommonPlayerToClientMessage extends Message {
	
		//脚本编号
		private var _scriptid: int;
		
		//脚本消息类型
		private var _type: int;
		
		//脚本消息数据(json字符串)
		private var _messageData: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//脚本编号
			writeInt(_scriptid);
			//脚本消息类型
			writeInt(_type);
			//脚本消息数据(json字符串)
			writeString(_messageData);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//脚本编号
			_scriptid = readInt();
			//脚本消息类型
			_type = readInt();
			//脚本消息数据(json字符串)
			_messageData = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103130;
		}
		
		/**
		 * get 脚本编号
		 * @return 
		 */
		public function get scriptid(): int{
			return _scriptid;
		}
		
		/**
		 * set 脚本编号
		 */
		public function set scriptid(value: int): void{
			this._scriptid = value;
		}
		
		/**
		 * get 脚本消息类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 脚本消息类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 脚本消息数据(json字符串)
		 * @return 
		 */
		public function get messageData(): String{
			return _messageData;
		}
		
		/**
		 * set 脚本消息数据(json字符串)
		 */
		public function set messageData(value: String): void{
			this._messageData = value;
		}
		
	}
}