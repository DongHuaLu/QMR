package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家防沉迷状态改变
	 */
	public class ResPlayerNonageStateMessage extends Message {
	
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		private var _nonage: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
			writeByte(_nonage);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
			_nonage = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103121;
		}
		
		/**
		 * get 玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		 * @return 
		 */
		public function get nonage(): int{
			return _nonage;
		}
		
		/**
		 * set 玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		 */
		public function set nonage(value: int): void{
			this._nonage = value;
		}
		
	}
}