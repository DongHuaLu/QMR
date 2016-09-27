package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家战斗状态改变
	 */
	public class ResPlayerStateChangeMessage extends Message {
	
		//玩家战斗状态 0-进入 1-退出
		private var _state: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家战斗状态 0-进入 1-退出
			writeByte(_state);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家战斗状态 0-进入 1-退出
			_state = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103120;
		}
		
		/**
		 * get 玩家战斗状态 0-进入 1-退出
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 玩家战斗状态 0-进入 1-退出
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
	}
}