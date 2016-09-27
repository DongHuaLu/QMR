package com.game.biwudao.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 比武岛活动状态显示
	 */
	public class ResBiWuDaoStatusShowToClientMessage extends Message {
	
		//比武岛活动状态显示
		private var _statusshow: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//比武岛活动状态显示
			writeString(_statusshow);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//比武岛活动状态显示
			_statusshow = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 157107;
		}
		
		/**
		 * get 比武岛活动状态显示
		 * @return 
		 */
		public function get statusshow(): String{
			return _statusshow;
		}
		
		/**
		 * set 比武岛活动状态显示
		 */
		public function set statusshow(value: String): void{
			this._statusshow = value;
		}
		
	}
}