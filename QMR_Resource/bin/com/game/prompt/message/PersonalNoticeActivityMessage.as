package com.game.prompt.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 活动提示消息
	 */
	public class PersonalNoticeActivityMessage extends Message {
	
		//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		private var _type: int;
		
		//活动ID
		private var _activityId: int;
		
		//信息内容
		private var _content: String;
		
		//参数
		private var _values: Vector.<String> = new Vector.<String>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
			writeByte(_type);
			//活动ID
			writeInt(_activityId);
			//信息内容
			writeString(_content);
			//参数
			writeShort(_values.length);
			for (i = 0; i < _values.length; i++) {
				writeString(_values[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
			_type = readByte();
			//活动ID
			_activityId = readInt();
			//信息内容
			_content = readString();
			//参数
			var values_length : int = readShort();
			for (i = 0; i < values_length; i++) {
				_values[i] = readString();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 109111;
		}
		
		/**
		 * get 信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 活动ID
		 * @return 
		 */
		public function get activityId(): int{
			return _activityId;
		}
		
		/**
		 * set 活动ID
		 */
		public function set activityId(value: int): void{
			this._activityId = value;
		}
		
		/**
		 * get 信息内容
		 * @return 
		 */
		public function get content(): String{
			return _content;
		}
		
		/**
		 * set 信息内容
		 */
		public function set content(value: String): void{
			this._content = value;
		}
		
		/**
		 * get 参数
		 * @return 
		 */
		public function get values(): Vector.<String>{
			return _values;
		}
		
		/**
		 * set 参数
		 */
		public function set values(value: Vector.<String>): void{
			this._values = value;
		}
		
	}
}