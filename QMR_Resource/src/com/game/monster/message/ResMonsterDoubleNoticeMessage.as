package com.game.monster.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 双倍经验消息
	 */
	public class ResMonsterDoubleNoticeMessage extends Message {
	
		//类型：1双倍经验，2其他。。。
		private var _type: int;
		
		//1开启，0关闭
		private var _status: int;
		
		//内容
		private var _content: String;
		
		//剩余时间（秒）
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型：1双倍经验，2其他。。。
			writeByte(_type);
			//1开启，0关闭
			writeByte(_status);
			//内容
			writeString(_content);
			//剩余时间（秒）
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型：1双倍经验，2其他。。。
			_type = readByte();
			//1开启，0关闭
			_status = readByte();
			//内容
			_content = readString();
			//剩余时间（秒）
			_time = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 114111;
		}
		
		/**
		 * get 类型：1双倍经验，2其他。。。
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：1双倍经验，2其他。。。
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 1开启，0关闭
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 1开启，0关闭
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
		/**
		 * get 内容
		 * @return 
		 */
		public function get content(): String{
			return _content;
		}
		
		/**
		 * set 内容
		 */
		public function set content(value: String): void{
			this._content = value;
		}
		
		/**
		 * get 剩余时间（秒）
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 剩余时间（秒）
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}