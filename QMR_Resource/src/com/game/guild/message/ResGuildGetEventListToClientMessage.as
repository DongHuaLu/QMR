package com.game.guild.message{
	import com.game.guild.bean.EventInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获取帮会事件列表返回
	 */
	public class ResGuildGetEventListToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//帮会事件列表
		private var _eventList: Vector.<EventInfo> = new Vector.<EventInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//错误代码
			writeByte(_btErrorCode);
			//帮会事件列表
			writeShort(_eventList.length);
			for (i = 0; i < _eventList.length; i++) {
				writeBean(_eventList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//错误代码
			_btErrorCode = readByte();
			//帮会事件列表
			var eventList_length : int = readShort();
			for (i = 0; i < eventList_length; i++) {
				_eventList[i] = readBean(EventInfo) as EventInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121121;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get btErrorCode(): int{
			return _btErrorCode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set btErrorCode(value: int): void{
			this._btErrorCode = value;
		}
		
		/**
		 * get 帮会事件列表
		 * @return 
		 */
		public function get eventList(): Vector.<EventInfo>{
			return _eventList;
		}
		
		/**
		 * set 帮会事件列表
		 */
		public function set eventList(value: Vector.<EventInfo>): void{
			this._eventList = value;
		}
		
	}
}