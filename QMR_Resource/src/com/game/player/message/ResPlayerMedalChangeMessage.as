package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 勋章变化
	 */
	public class ResPlayerMedalChangeMessage extends Message {
	
		//当前勋章
		private var _medal: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前勋章
			writeInt(_medal);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前勋章
			_medal = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103138;
		}
		
		/**
		 * get 当前勋章
		 * @return 
		 */
		public function get medal(): int{
			return _medal;
		}
		
		/**
		 * set 当前勋章
		 */
		public function set medal(value: int): void{
			this._medal = value;
		}
		
	}
}