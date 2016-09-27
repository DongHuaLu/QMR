package com.game.guildflag.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送领地争夺战是否开启
	 */
	public class ResGuildFlagStatusToClientMessage extends Message {
	
		//时间（秒）大于0表示进行中
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//时间（秒）大于0表示进行中
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//时间（秒）大于0表示进行中
			_time = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 149102;
		}
		
		/**
		 * get 时间（秒）大于0表示进行中
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 时间（秒）大于0表示进行中
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}